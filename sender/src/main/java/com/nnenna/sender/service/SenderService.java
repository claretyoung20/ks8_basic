package com.nnenna.sender.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nnenna.sender.model.Dto.MessageDto;
import com.nnenna.sender.model.Message;
import com.nnenna.sender.model.pojo.CheckAlertPojo;
import com.nnenna.sender.model.pojo.MessagePojo;

public interface SenderService {

    Message getMessage(String ref);
    MessagePojo sendMessage(MessageDto messageDto) throws JsonProcessingException;

    CheckAlertPojo checkAlerts();
}
