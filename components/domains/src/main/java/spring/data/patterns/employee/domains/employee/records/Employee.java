package spring.data.patterns.employee.domains.employee.records;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Gregory Green
 */
@Builder
public record Employee(String id, String empName, String jobName, LocalDateTime hireDate, BigDecimal salary, BigDecimal commission, String department, String managerId) {
    public Employee {
        java.util.Objects.requireNonNull(id, "id required");
        java.util.Objects.requireNonNull(empName, "employee name required");
    }
}
