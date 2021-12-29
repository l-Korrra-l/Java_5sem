package bstu.merh.employees.controller;

import bstu.merh.employees.Exceptions.ControllerException;
import bstu.merh.employees.Exceptions.RepositoryException;
import bstu.merh.employees.dto.EmailRequest;
import bstu.merh.employees.jwt.JwtFilter;
import bstu.merh.employees.repository.EmailFormRepository;
import bstu.merh.employees.service.EmailService;
import bstu.merh.employees.service.MailSender;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.rowset.serial.SerialException;

@RestController
public class MessageRestController {
    @Autowired
    private MailSender mailSender;
    @Autowired
    private EmailService emailService;
    @Autowired
    private EmailFormRepository emailFormRepository;

    @PostMapping("/user/sendMessage")
    public ResponseEntity<?> createContract(@RequestBody EmailRequest emailRequest) throws ControllerException, SerialException {

        System.out.println(emailService.createEmail(emailRequest, JwtFilter.getCurrentUserUsername()));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/admin/deleteEmailByEmployeeId/{id}")
    public ResponseEntity<?> deleteEmailByTutorId(@PathVariable(name="id")Long id)throws ControllerException {
        try {
            emailFormRepository.deleteByEmployeeId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException | RepositoryException e) {
            throw new ControllerException(e);

        }
    }

    @DeleteMapping("/admin/deleteEmailByUserId/{id}")
    public ResponseEntity<?> deleteEmailByUserId(@PathVariable(name="id")Long id)throws ControllerException {
        try {
            emailFormRepository.deleteByUserId(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException | RepositoryException e) {
            throw new ControllerException(e);

        }
    }
}
