package bstu.merh.employees.repository;

import bstu.merh.employees.Exceptions.RepositoryException;
import bstu.merh.employees.model.EmailForm;
import bstu.merh.employees.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmailFormRepository extends JpaRepository<EmailForm, Long> {
    @Modifying
    @Transactional
    @Query("delete from EmailForm c where c.employees.id=:employee_id")
    void deleteByEmployeeId(@Param("employee_id") Long id) throws RepositoryException;
    @Modifying
    @Transactional
    @Query("delete from EmailForm c where c.user.id=:user_id")
    void deleteByUserId(@Param("user_id") Long id) throws RepositoryException;
}
