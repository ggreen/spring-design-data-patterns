package spring.design.data.patterns.employee.repository;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.design.data.patterns.employee.entities.EmployeeEntity;

/**
 * @author Gregory Green
 */
@Repository
public interface EmployeeRepository extends JpaRepository<@NonNull EmployeeEntity,@NonNull String> {
}
