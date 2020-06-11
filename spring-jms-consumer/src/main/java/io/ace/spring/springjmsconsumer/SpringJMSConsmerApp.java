package io.ace.spring.springjmsconsumer;

import io.ace.spring.springjmsconsumer.config.ApplicationConfigurartion;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringJMSConsmerApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfigurartion.class);
    }
}
