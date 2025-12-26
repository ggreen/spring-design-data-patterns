package spring.design.patterns.domain;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Gregory Green
 */
@Builder
public record EmployeeInput(String id, String empName, String jobName, LocalDateTime hireDate, BigDecimal salary, BigDecimal commission, String department, String managerId) {
    public EmployeeInput {
        java.util.Objects.requireNonNull(id, "id required");
        java.util.Objects.requireNonNull(empName, "employee name required");
    }
}
