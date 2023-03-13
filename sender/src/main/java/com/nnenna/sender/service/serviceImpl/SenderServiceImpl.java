package com.nnenna.sender.service.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nnenna.sender.messageService.SendMessageService;
import com.nnenna.sender.model.Dto.MessageDto;
import com.nnenna.sender.model.Message;
import com.nnenna.sender.model.pojo.CheckAlertPojo;
import com.nnenna.sender.model.pojo.MessagePojo;
import com.nnenna.sender.repository.MessageRepository;
import com.nnenna.sender.service.SenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;

@Service
public class SenderServiceImpl implements SenderService {

    private static final Logger logger = LoggerFactory.getLogger(SenderServiceImpl.class);

    private final MessageRepository messageRepository;
    private final SendMessageService sendMessageService;
    private final RestTemplate restTemplate;

    private final Environment environment;

    public SenderServiceImpl(MessageRepository messageRepository, SendMessageService sendMessageService, RestTemplate restTemplate, Environment environment) {
        this.messageRepository = messageRepository;
        this.sendMessageService = sendMessageService;
        this.restTemplate = restTemplate;
        this.environment = environment;
    }

    @Override
    public MessagePojo sendMessage(MessageDto messageDto) throws JsonProcessingException {

        Message saved = messageRepository.save(getSender(messageDto));

        sendMessageService.sendMessage(saved);

        return getSenderPojo(saved);
    }

    @Override
    public CheckAlertPojo checkAlerts() {

        long totalFromReceiver = getReceiverTotalMessage();

        long totalFromSender = messageRepository.count();

        CheckAlertPojo checkAlertPojo = new CheckAlertPojo();
        checkAlertPojo.setTotalFromSender(totalFromSender);
        checkAlertPojo.setTotalFromReceiver(totalFromReceiver);
        checkAlertPojo.setSame(totalFromReceiver == totalFromSender);

        return checkAlertPojo;
    }

    public Long getReceiverTotalMessage() {

        Long totalFromReceiver = 0l;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        String receiverBaseUrl = environment.getProperty("RECEIVER_BASE_URL");
        if (!receiverBaseUrl.endsWith("/")) {
            receiverBaseUrl += "/";
        }

        String url = receiverBaseUrl + "check" ;

        logger.info("url: {}", url);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Long> response = this.restTemplate.exchange(url, HttpMethod.GET, entity, Long.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                totalFromReceiver = response.getBody();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return totalFromReceiver;
    }


    @Override
    public Message getMessage(String ref) {
        Optional<Message> getByRef = messageRepository.findByRef(ref);
        return getByRef.isEmpty() ? null : getByRef.get();
    }


    private MessagePojo getSenderPojo(Message saved) {
        MessagePojo pojo = new MessagePojo();
        pojo.setSenderName(saved.getSenderName());
        pojo.setReceiverName(saved.getReceiverName());
        pojo.setAmount(saved.getAmount());
        pojo.setCharges(saved.getCharges());

        pojo.setDateCreated(saved.getDateCreated());
        pojo.setDateUpdated(saved.getDateUpdated());
        pojo.setReference(saved.getRef());
        return pojo;
    }

    private Message getSender(MessageDto messageDto) {
        Message message = new Message();
        message.setSenderName(messageDto.getSenderName());
        message.setReceiverName(messageDto.getReceiverName());
        message.setAmount(messageDto.getAmount());

        BigDecimal rate = new BigDecimal(10);
        BigDecimal charge = rate.divide(new BigDecimal(100)).multiply(messageDto.getAmount());
        message.setCharges(charge);

        message.setDateCreated(new Date());
        message.setDateUpdated(new Date());
        message.setRef(UUID.randomUUID().toString().replace("-", ""));
        return message;
    }
}
