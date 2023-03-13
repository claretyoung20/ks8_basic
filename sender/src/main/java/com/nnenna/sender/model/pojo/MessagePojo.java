package com.nnenna.sender.model.pojo;

import com.nnenna.sender.model.MessageBase;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MessagePojo extends MessageBase {
    private BigDecimal charges;
    private String reference;
}
