package io.ace.spring.springjmsconsumer.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class MessageReceiver {

    @Autowired
    MessageConverter messageConverter;


    public void onMessage(Message message) {
        try {
            String response = (String) messageConverter.fromMessage(message);
            System.out.println("Message  : " + response);
        } catch (JMSException e) {
            System.out.println("Invalid Message");
        }
    }

}
