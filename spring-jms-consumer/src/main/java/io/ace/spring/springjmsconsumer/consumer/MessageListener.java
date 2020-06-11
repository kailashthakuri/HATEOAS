package io.ace.spring.springjmsconsumer.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private static final String MESSAGE_QUEUE = "spring-message-queue";

    @JmsListener(destination = MESSAGE_QUEUE)
    public void listenMessage(Message<String> message) {
        System.out.println(message.getPayload());
        System.out.println(message.getHeaders());
    }
}
