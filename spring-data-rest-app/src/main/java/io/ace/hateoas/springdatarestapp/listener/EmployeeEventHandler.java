package io.ace.hateoas.springdatarestapp.listener;


import io.ace.hateoas.springdatarestapp.entity.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@RepositoryEventHandler(Employee.class)
public class EmployeeEventHandler {


    public static Logger logger = LoggerFactory.getLogger(EmployeeEventHandler.class);

    @HandleBeforeCreate
    public void handlerEmployeeSave(Employee employee) {
        System.out.println(employee.toString());
        logger.info(employee.toString());
    }
}
