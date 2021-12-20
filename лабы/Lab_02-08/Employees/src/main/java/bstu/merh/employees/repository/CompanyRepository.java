package bstu.merh.employees.repository;

import bstu.merh.employees.model.Companies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Companies, Long> {
}
