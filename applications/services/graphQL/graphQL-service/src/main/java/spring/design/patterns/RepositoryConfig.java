package spring.design.patterns;

import nyla.solutions.core.patterns.repository.memory.InMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.data.patterns.employee.domains.employee.records.Employee;
import spring.design.patterns.repository.EmployeeRepository;

import java.util.Optional;

/**
 * @author Gregory Green
 */
@Configuration
public class RepositoryConfig {

    @Bean
    InMemoryRepository<Employee, String> inMemoryRepository()
    {
        return InMemoryRepository.builder()
                .withIdProperty("id")
                .build();
    }

    @Bean
    EmployeeRepository employeeRepository(InMemoryRepository<Employee, String> inMemoryRepository) {
        return new EmployeeRepository() {
            @Override
            public Optional<Employee> findById(String id) {
                return inMemoryRepository.findById(id);
            }

            @Override
            public Employee save(Employee employee) {
                return inMemoryRepository.save(employee);
            }
        };
    }
}
