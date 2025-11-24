package spring.design.data.patterns.event.source.controller;

import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator;
import nyla.solutions.core.patterns.integration.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.data.patterns.employee.domains.employee.records.Employee;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class EmployeeSourceControllerTest {

    private Employee employee = JavaBeanGeneratorCreator.of(Employee.class).create();
    @Mock
    private Publisher<Employee> employeePublisher;
    private EmployeeSourceController subject;

    @BeforeEach
    void setUp() {
        subject = new EmployeeSourceController(employeePublisher);
    }

    @Test
    void postEmployee() {
        subject.sendEmployee(employee);

        verify(employeePublisher).send(any());
    }
}