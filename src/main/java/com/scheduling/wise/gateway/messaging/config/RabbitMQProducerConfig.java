package com.scheduling.wise.gateway.messaging.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.scheduling.wise.gateway.messaging.constants.QueueConstants.*;

@Configuration
public class RabbitMQProducerConfig {

    @Bean
    public Queue emailQueue() {
        return new Queue(NOTIFICATION_EMAIL_QUEUE, true);
    }

    @Bean
    public Queue smsQueue() {
        return new Queue(NOTIFICATION_SMS_QUEUE, true);
    }

    @Bean
    public Queue whatsappQueue() {
        return new Queue(NOTIFICATION_WHATSAPP_QUEUE, true);
    }

    @Bean
    public Queue historyNewQueue() {
        return new Queue(HISTORY_NEW_QUEUE, true);
    }

    @Bean
    public Queue historyUpdateQueue() {
        return new Queue(HISTORY_UPDATE_QUEUE, true);
    }

    @Bean
    public MessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}