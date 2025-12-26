package spring.design.data.patterns.function.service;

import nyla.solutions.core.patterns.creational.generator.JavaBeanGeneratorCreator;
import nyla.solutions.core.patterns.repository.SaveRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import spring.data.patterns.employee.domains.employee.records.Employee;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SaveEmployeeTest {

    private SaveEmployee subject;
    @Mock
    private SaveRepository<Employee> repository;
    private static final Employee employee = JavaBeanGeneratorCreator.of(Employee.class).create();

    @BeforeEach
    void setUp() {
        subject = new SaveEmployee(repository);
    }

    @Test
    void save() {

        subject.accept(employee);

        verify(repository).save(employee);

    }
}