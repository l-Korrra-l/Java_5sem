package bstu.merh.employees.repository;

import bstu.merh.employees.Exceptions.RepositoryException;
import bstu.merh.employees.model.Employee;
import bstu.merh.employees.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //Employee findByFirst_nameContains(String first_name) throws RepositoryException;
    List<Employee> findByFirstNameContains(String name);
    List<Employee> findByAge(Integer age);
}
