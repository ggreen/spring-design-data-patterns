package spring.design.data.patterns.employee.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity{
    @Id
    private String id;
    private String empName;
    private String jobName;
    private LocalDateTime hireDate;
    private BigDecimal salary;
    private BigDecimal commission;
    private String department;
    private String managerId;
}
