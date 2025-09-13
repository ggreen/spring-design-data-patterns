package spring.design.patterns.reactive.controller;

import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import spring.data.patterns.employee.domains.employee.records.Employee;

import static org.junit.jupiter.api.Assertions.*;


@WebFluxTest(EmployeeReactiveController.class)
class EmployeeReactiveControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = JavaBeanGeneratorCreator.of(Employee.class).create();
    }

    @Test
    void saveAndFindEmployeeById() {
        // Save employee
        webTestClient.post()
                .uri("/employees")
                .bodyValue(employee)
                .exchange()
                .expectStatus().isOk();

        // Find employee by id
        webTestClient.get()
                .uri("/employees/{id}", employee.id())
                .exchange()
                .expectStatus().isOk()
                .expectBody(Employee.class)
                .value(e -> {
                    assertEquals(employee.id(), e.id());
                    assertEquals(employee.empName(), e.empName());
                    assertEquals(employee.department(), e.department());
                });
    }
}