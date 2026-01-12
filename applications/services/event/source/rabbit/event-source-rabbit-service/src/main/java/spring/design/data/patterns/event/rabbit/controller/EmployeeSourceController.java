package spring.design.data.patterns.event.rabbit.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.data.patterns.employee.domains.employee.records.Location;

/**
 * EmployeeSourceController - REST controller for sending Employee events to a message channel.
 * @author Gregory Green
 */
@RestController
@RequestMapping("employees")
public class EmployeeSourceController {


    private final MessageChannel messageChannel;

    public EmployeeSourceController(@Qualifier("messageChannel") MessageChannel messageChannel) {
        this.messageChannel = messageChannel;
    }

    /**
     * Sends an Employee payload to the message channel.
     * @param employee the employee payload
     */
    @PostMapping
    public void sendEmployee(@RequestBody Location employee) {
        messageChannel.send(MessageBuilder.withPayload(employee).build());
    }
}
