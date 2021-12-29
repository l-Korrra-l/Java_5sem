package bstu.merh.employees.service.interfaces;

import bstu.merh.employees.model.Employee;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public interface IEmployeeInterface {
    @Transactional
    void deleteById(Long id) throws ServiceException;

    Employee create(Employee tutorOffer)throws ServiceException;

    boolean existsById(Long id) throws ServiceException;

    List<Employee> getAll()throws ServiceException;

    Employee getById(Long id)throws ServiceException;

    @Transactional
    void updateEmployeeById(
            Long id,
            String firstName,
            String lastName,
            Integer age,
            Float salary,
            String email)throws ServiceException;
    @Transactional
    void updateEmployeeRateById(Long id, float rate)throws ServiceException;
}
