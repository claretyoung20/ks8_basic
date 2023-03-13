package com.nnenna.sender.messageService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnenna.sender.model.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class SendMessageServiceImpl implements SendMessageService {

    private final RabbitTemplate rabbitTemplate;


    public SendMessageServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public String sendMessage(Message message) throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        String msg = mapper.writeValueAsString(message);

        rabbitTemplate.convertAndSend("alert_exchange", "account_alert", msg);
        return "sent";
    }
}
