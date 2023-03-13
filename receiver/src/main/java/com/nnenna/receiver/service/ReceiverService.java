package com.nnenna.receiver.service;

import com.nnenna.receiver.model.Message;
import com.nnenna.receiver.model.pojo.MessagePojo;

public interface ReceiverService {

    Message save(MessagePojo msg);

    long getTotalMessage();
}
