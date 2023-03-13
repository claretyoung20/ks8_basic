package com.nnenna.sender.messageService;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingRabbitmqConfig {

    @Bean
    Queue SendAlert() {
        return new Queue("alert", true, false, false);
    }

    @Bean
    TopicExchange alertExchange() {
        return new TopicExchange("alert_exchange", true, false);
    }

    @Bean
    Binding binding(Queue SendAlert, TopicExchange alertExchange) {
        return BindingBuilder.bind(SendAlert).to(alertExchange).with("account_alert");
    }
}
