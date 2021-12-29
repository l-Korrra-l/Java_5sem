package bstu.merh.employees.service.interfaces;

import bstu.merh.employees.Exceptions.ControllerException;
import bstu.merh.employees.dto.EmailRequest;
import bstu.merh.employees.model.EmailForm;
import org.springframework.stereotype.Service;

import javax.sql.rowset.serial.SerialException;

@Service
public interface IEmailService {
    EmailForm createEmail(EmailRequest emailRequest, String Username) throws SerialException, ControllerException;
}
