package bstu.merh.employees.repository;

import bstu.merh.employees.model.Employee;
import bstu.merh.employees.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
