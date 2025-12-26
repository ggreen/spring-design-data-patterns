package spring.design.data.patterns.function.service;

import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator;
import nyla.solutions.core.patterns.repository.FindByIdRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.data.patterns.employee.domains.employee.records.Employee;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetEmployeeTest {


    private final static Employee employee = JavaBeanGeneratorCreator.of(Employee.class).create();
    private GetEmployee subject;

    @BeforeEach
    void setUp() {
        subject = new GetEmployee(repository);
    }

    @Mock
    private FindByIdRepository<Employee,String> repository;

    @Test
    void findEmployeeById() {

        when(repository.findById(any())).thenReturn(Optional.of(employee));

        Employee actual = subject.apply(employee.id());

        assertNotNull(actual);
        assertEquals(employee,actual);

    }
}