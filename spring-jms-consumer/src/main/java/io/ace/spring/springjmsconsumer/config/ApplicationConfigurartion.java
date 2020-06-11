package io.ace.spring.springjmsconsumer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jms.listener.MessageListenerContainer;

@Configuration
@Import({MessageConfiguration.class, MessageListenerConfiguration.class})
@ComponentScan(basePackages = "io.ace.spring.springjmsconsumer")
public class ApplicationConfigurartion {

}
