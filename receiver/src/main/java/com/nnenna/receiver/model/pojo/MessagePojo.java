package com.nnenna.receiver.model.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class MessagePojo {

    private Integer id;

    private String senderName;

    private String receiverName;

    private BigDecimal amount;

    private BigDecimal charges;

    private String ref;
}
