package io.ace.spring.springjmsproducer;

import io.ace.spring.springjmsproducer.config.ApplicationConfig;
import io.ace.spring.springjmsproducer.producer.MessageSender;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class SpringJMSProducerApp {
    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        MessageSender bean = context.getBean(MessageSender.class);
        bean.sendMessage("Hello Dude!");
        context.close();
    }
}
