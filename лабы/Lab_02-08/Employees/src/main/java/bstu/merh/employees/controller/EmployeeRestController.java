package bstu.merh.employees.controller;

import bstu.merh.employees.Exceptions.ControllerException;
import bstu.merh.employees.dto.AuthRequest;
import bstu.merh.employees.dto.EmailRequest;
import bstu.merh.employees.jwt.JwtProvider;
import bstu.merh.employees.model.EmailForm;
import bstu.merh.employees.model.Employee;
import bstu.merh.employees.repository.EmailFormRepository;
import bstu.merh.employees.repository.EmployeeRepository;
import bstu.merh.employees.service.EmployeeService;
import bstu.merh.employees.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private EmployeeRepository emplRepo;
    @Autowired
    private EmailFormRepository emailRepo;

//    private static final Logger logger = Logger.getLogger(MainRestController.class);

    @PostMapping("/emailForm")
    public ResponseEntity<?> addEmail(@RequestBody EmailRequest emailRequest) throws ControllerException {
        try{
            EmailForm emailForm = new EmailForm();
            List<String> l = emailRequest.getEmployeeList();
            List<Employee> em = emailForm.getEmployees();
            for (String id: l) {
                Employee e = emplRepo.findById(Long.parseLong(id)).get();
                em.add(e);
            }

            //emailForm.setMessage();
            //emailForm.setUser();
            emailRepo.save(emailForm);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            throw new ControllerException("unknown exception", e);
        }
    }



    @GetMapping("/emailForm")
    public @ResponseBody
    ModelAndView emailFormEmpty(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("email");
        model.addAttribute("allEmployees", emplRepo.findAll());
        model.addAttribute("emailForm", new EmailForm());
        return modelAndView;
    }

    @PostMapping("/admin/getAllEmployees")
    public List<Employee> getEmployeesForAdmin() throws ControllerException {
        try {
//            logger.debug("getting all users");
            return employeeService.getAll();
        } catch (Exception e) {
//            logger.error("error get all users");
            throw new ControllerException("getEmployees", e);
        }
    }
    @PostMapping("/user/getAllEmployees")
    public List<Employee> getEmployeesForUser() throws ControllerException {
        try {
//            logger.debug("getting all users");
            return employeeService.getAll();
        } catch (Exception e) {
//            logger.error("error get all users");
            throw new ControllerException("getEmployees", e);
        }
    }
}
