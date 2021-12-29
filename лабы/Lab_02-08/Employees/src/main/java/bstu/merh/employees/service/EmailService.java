package bstu.merh.employees.service;

import bstu.merh.employees.Exceptions.ControllerException;
import bstu.merh.employees.dto.EmailRequest;
import bstu.merh.employees.model.EmailForm;
import bstu.merh.employees.model.Employee;
import bstu.merh.employees.model.User;
import bstu.merh.employees.repository.EmailFormRepository;
import bstu.merh.employees.repository.EmployeeRepository;
import bstu.merh.employees.repository.UserRepository;
import bstu.merh.employees.service.interfaces.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialException;

@Service
public class EmailService implements IEmailService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmailFormRepository emailFormRepository;
    @Autowired
    private MailSender mailSender;
    @Override
    public EmailForm createEmail(EmailRequest emailRequest, String Username) throws SerialException, ControllerException {
        User user = userRepository.findByUsername(Username);
        Employee employee = employeeRepository.getById(emailRequest.employee);
        EmailForm email = new EmailForm();
        email.setUser(user);
        email.setMessage(emailRequest.message);
        email.setEmployees(employee);
        System.out.println(employee);
        mailSender.sendMail(employee.getEmail(), Username, emailRequest.message);
        return emailFormRepository.save(email);
    }
}
