package spring.design.data.patterns.event.source.controller;

import lombok.RequiredArgsConstructor;
import nyla.solutions.core.patterns.integration.Publisher;
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
@RequiredArgsConstructor
public class EmployeeSourceController {

    private final Publisher<Location> output;

    /**
     * Sends an Employee payload to the message channel.
     * @param employee the employee payload
     */
    @PostMapping
    public void sendEmployee(@RequestBody Location employee) {
        output.send(employee);
    }
}
