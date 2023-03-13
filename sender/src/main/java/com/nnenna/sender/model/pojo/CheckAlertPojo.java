package com.nnenna.sender.model.pojo;

import lombok.Data;

@Data
public class CheckAlertPojo {
    long totalFromReceiver;
    long totalFromSender;
    boolean isSame;

}
