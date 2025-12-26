package spring.design.patterns.controller;

import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.data.patterns.employee.domains.employee.records.Employee;
import spring.design.patterns.domain.EmployeeInput;
import spring.design.patterns.repository.EmployeeRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Gregory Green
 */
@ExtendWith(MockitoExtension.class)
class EmployControllerTest {

    private EmployController subject;
    @Mock
    private EmployeeRepository repository;
    private Employee employee = JavaBeanGeneratorCreator.of(Employee.class).create();

    @BeforeEach
    void setUp() {
        subject = new EmployController(repository);
    }

    @Test
    void findById() {
        when(repository.findById(anyString())).thenReturn(Optional.of(employee));
        var actual = subject.employeeById("1");

        assertThat(actual).isEqualTo(employee);
    }

    @Test
    void saveEmployee() {
        when(repository.save(employee)).thenReturn(employee);

        var actual = subject.saveEmployee(
                EmployeeInput.builder()
                        .id(employee.id())
                        .salary(employee.salary())
                        .empName(employee.empName())
                        .jobName(employee.jobName())
                        .hireDate(employee.hireDate())
                        .commission(employee.commission())
                        .department(employee.department())
                        .managerId(employee.managerId())
                        .build());

        assertThat(actual).isEqualTo(employee);
        verify(repository).save(any());
    }
}