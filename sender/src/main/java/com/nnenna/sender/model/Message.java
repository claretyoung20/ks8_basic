package com.nnenna.sender.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "sender")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message extends MessageBase {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String senderName;

    private String receiverName;

    private Date dateCreated;

    private Date dateUpdated;

    private BigDecimal amount;

    private BigDecimal charges;

    private String ref;

}
