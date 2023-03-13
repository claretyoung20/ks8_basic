package com.nnenna.receiver.messageService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nnenna.receiver.model.pojo.MessagePojo;
import com.nnenna.receiver.service.ReceiverService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Service;

@Service
public class MessageReceiveHandler implements ChannelAwareMessageListener {

    private final ReceiverService receiverService;

    public MessageReceiveHandler(ReceiverService receiverService) {
        this.receiverService = receiverService;
    }

    @Override
    public void onMessage(Message message, Channel channel) {

        String dateFormat = "yyyy-MM-dd HH:mm:ss.SSSZ";
        Gson gson = new GsonBuilder()
                .setDateFormat(dateFormat)
                .create();

        String convertReceivedMsg = new String(message.getBody());

        MessagePojo msg = gson.fromJson(convertReceivedMsg,  MessagePojo.class);
        receiverService.save(msg);
    }

}
