package spring.data.patterns.employee.domains.employee.records;

import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Employee record
 * @author Gregory Green
 */
@Builder
public record Location(String id, String empName, String jobName, LocalDateTime hireDate, BigDecimal salary, BigDecimal commission, String department, String managerId) implements Serializable {
    public Location {
        java.util.Objects.requireNonNull(id, "id required");
        java.util.Objects.requireNonNull(empName, "employee name required");
    }
}
