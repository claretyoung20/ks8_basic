package com.nnenna.sender.messageService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nnenna.sender.model.Message;

public interface SendMessageService {
    String sendMessage(Message message) throws JsonProcessingException;
}
