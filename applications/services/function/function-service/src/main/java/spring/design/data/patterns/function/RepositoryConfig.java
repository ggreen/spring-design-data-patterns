package spring.design.data.patterns.function;

import nyla.solutions.core.patterns.repository.memory.InMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.data.patterns.employee.domains.employee.records.Employee;

/**
 * @author Gregory Green
 */
@Configuration
public class RepositoryConfig {

    @Bean InMemoryRepository<Employee,String> repository()
    {
        return  InMemoryRepository.builder()
                .withIdProperty("id")
                .build();
    }
}
