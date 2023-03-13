package com.nnenna.sender.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class MessageBase {
    private String senderName;
    private String receiverName;
    private Date dateCreated;
    private Date dateUpdated;
    private BigDecimal amount;
}
