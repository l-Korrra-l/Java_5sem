package bstu.merh.employees.service;

import bstu.merh.employees.Exceptions.RepositoryException;
import bstu.merh.employees.model.Employee;
import bstu.merh.employees.repository.EmployeeRepository;
import bstu.merh.employees.service.interfaces.IEmployeeInterface;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements IEmployeeInterface {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public void deleteById(Long id) throws ServiceException {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee create(Employee employee) throws ServiceException {
        return employeeRepository.save(employee);
    }

    @Override
    public boolean existsById(Long id) throws ServiceException {
        try {
            return employeeRepository.existsById(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public List<Employee> getAll() throws ServiceException {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getById(Long id) throws ServiceException {
        try {
            return employeeRepository.getById(id);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }
    }

    @Override
    public void updateEmployeeById(Long id, String firstName, String lastName, Integer age, Float salary, String email) throws ServiceException {
        try {
            employeeRepository.updateEmployeeById(id, firstName, lastName, age, salary, email);
        } catch (RepositoryException ex) {
            throw new ServiceException(ex.getMessage());

        }
    }

    @Override
    public void updateEmployeeRateById(Long id, float rate) throws ServiceException {

    }
}
