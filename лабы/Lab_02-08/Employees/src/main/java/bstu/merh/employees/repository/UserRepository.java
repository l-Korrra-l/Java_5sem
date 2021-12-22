package bstu.merh.employees.repository;

import bstu.merh.employees.model.Employee;
import bstu.merh.employees.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
