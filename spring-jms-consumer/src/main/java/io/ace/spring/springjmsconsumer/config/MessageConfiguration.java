package io.ace.spring.springjmsconsumer.config;

import io.ace.spring.springjmsconsumer.consumer.MessageReceiver;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import javax.jms.ConnectionFactory;

@Configuration
public class MessageConfiguration {
    private static String DEFAULT_BROKER_URL = "tcp://localhost:61616";
    private static String MESSAGE_QUEUE = "spring-message-queue";


    @Autowired
    MessageReceiver messageReceiver;

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(DEFAULT_BROKER_URL);
        return factory;
    }
//    @Bean
//    public MessageListenerContainer messageListenerContainer() {
//        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory());
//        container.setupMessageListener(messageReceiver);
//        container.setDestinationName(MESSAGE_QUEUE);
//        return container;
//    }

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
