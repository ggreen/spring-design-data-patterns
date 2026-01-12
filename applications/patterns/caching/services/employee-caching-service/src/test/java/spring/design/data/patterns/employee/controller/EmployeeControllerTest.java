package spring.design.data.patterns.employee.controller;

import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.data.patterns.employee.domains.employee.records.Location;
import spring.design.data.patterns.employee.entities.EmployeeEntity;
import spring.design.data.patterns.employee.repository.EmployeeRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    private EmployeeController subject;

    private final Location employee = JavaBeanGeneratorCreator.of(Location.class).create();
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeEntity employeeEntity;

    @BeforeEach
    void setUp() {
        subject = new EmployeeController(employeeRepository);
    }

    @Test
    void writeRead() {

        when(employeeRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(employeeEntity));
        when(employeeEntity.getId()).thenReturn(employee.id());
        when(employeeEntity.getEmpName()).thenReturn(employee.empName());

        subject.saveEmployer(employee);

        var actual = subject.findEmployeeById(employee.id());
        assertThat(actual).isNotNull();
        assertThat(actual.id()).isEqualTo(employee.id());

         verify(employeeRepository).save(any());

    }


}