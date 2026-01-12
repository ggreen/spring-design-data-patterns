package spring.design.patterns.controller;

import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spring.data.patterns.employee.domains.employee.records.Location;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeControllerTest {


    private EmployeeController subject;
    private static final Location employee = JavaBeanGeneratorCreator.of(Location.class).create();

    @BeforeEach
    void setUp() {
        subject = new EmployeeController();
    }

    @Test
    @DisplayName("Given a employee when save then you can read the saved data")
    void writeRead() {
        subject.saveEmployee(employee);

        assertThat(subject.findEmployeeById(employee.id())).isEqualTo(employee);

    }
}