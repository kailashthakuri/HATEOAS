package io.ace.spring.springjmsproducer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(MessageConfig.class)
@ComponentScan(basePackages = "io.ace.spring.springjmsproducer")
public class ApplicationConfig {

}
