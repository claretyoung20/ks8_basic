package com.nnenna.receiver.service.serviceImpl;

import com.nnenna.receiver.model.Message;
import com.nnenna.receiver.model.pojo.MessagePojo;
import com.nnenna.receiver.repository.MessageRepository;
import com.nnenna.receiver.service.ReceiverService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ReceiverServiceImpl implements ReceiverService {

    private final MessageRepository messageRepository;

    public ReceiverServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    @Override
    public Message save(MessagePojo msg) {

        Message message = getMessage(msg);

        return messageRepository.save(message);
    }

    @Override
    public long getTotalMessage() {
        return messageRepository.count();
    }

    private Message getMessage(MessagePojo msg) {
        Message message = new Message();
        message.setSenderName(msg.getSenderName());
        message.setReceiverName(msg.getReceiverName());
        message.setAmount(msg.getAmount());
        message.setCharges(msg.getCharges());
        message.setDateCreated(new Date());
        message.setDateUpdated(new Date());
        message.setRef(msg.getRef());
        return message;
    }
}
