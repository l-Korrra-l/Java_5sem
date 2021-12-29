package bstu.merh.employees.repository;

import bstu.merh.employees.Exceptions.RepositoryException;
import bstu.merh.employees.model.Employee;
import bstu.merh.employees.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //Employee findByFirst_nameContains(String first_name) throws RepositoryException;
    List<Employee> findByFirstNameContains(String name);
    List<Employee> findByAge(Integer age);

    @Modifying
    @Query("update Employee t set t.firstName=:firstName, t.lastName=:lastName, t.age=:age, t.salary=:salary, t.email=:email where t.id=:id")
    void updateEmployeeById(
            @Param("id") Long id,
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("age") Integer age,
            @Param("salary") Float salary,
            @Param("email") String email
    ) throws RepositoryException;
}
