package spring.design.patterns.repository;

import nyla.solutions.core.patterns.repository.FindByIdRepository;
import nyla.solutions.core.patterns.repository.SaveRepository;
import spring.data.patterns.employee.domains.employee.records.Location;

/**
 * @author Gregory Green
 */
public interface EmployeeRepository
        extends SaveRepository<Location>, FindByIdRepository<Location,String> {
}
