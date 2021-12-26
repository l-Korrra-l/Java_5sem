package bstu.merh.employees.repository;

import bstu.merh.employees.model.EmailForm;
import bstu.merh.employees.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailFormRepository extends JpaRepository<EmailForm, Long> {
}
