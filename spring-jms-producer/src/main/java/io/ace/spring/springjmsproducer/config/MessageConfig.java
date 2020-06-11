package io.ace.spring.springjmsproducer.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import javax.jms.ConnectionFactory;

@Configuration
public class MessageConfig {
    private static String DEFAULT_BROKER_URL = "tcp://localhost:61616";
    private static String MESSAGE_QUEUE = "spring-message-queue";


    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(DEFAULT_BROKER_URL);
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setDefaultDestinationName(MESSAGE_QUEUE);
        return jmsTemplate;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new SimpleMessageConverter();
    }
}
