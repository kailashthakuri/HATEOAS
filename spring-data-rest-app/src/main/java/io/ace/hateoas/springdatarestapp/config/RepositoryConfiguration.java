package io.ace.hateoas.springdatarestapp.config;

import io.ace.hateoas.springdatarestapp.listener.EmployeeEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    @Bean
    public EmployeeEventHandler employeeEventHandler() {
        return new EmployeeEventHandler();
    }

}
