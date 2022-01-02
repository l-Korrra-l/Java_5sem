package bstu.merh.employees.controller;

import bstu.merh.employees.Exceptions.ControllerException;
import bstu.merh.employees.dto.AuthRequest;
import bstu.merh.employees.dto.EmailRequest;
import bstu.merh.employees.dto.EmployeeRequest;
import bstu.merh.employees.jwt.JwtProvider;
import bstu.merh.employees.model.EmailForm;
import bstu.merh.employees.model.Employee;
import bstu.merh.employees.repository.EmailFormRepository;
import bstu.merh.employees.repository.EmployeeRepository;
import bstu.merh.employees.service.EmployeeService;
import bstu.merh.employees.service.UserService;
import bstu.merh.employees.validator.EmployeeValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.validation.Valid;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Slf4j
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
    @Autowired
    private EmployeeValidator employeeValidator;

//    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRestController.class);

//    @PostMapping("/emailForm")
//    public ResponseEntity<?> addEmail(@RequestBody EmailRequest emailRequest) throws ControllerException {
//        try{
//            EmailForm emailForm = new EmailForm();
//            List<String> l = emailRequest.getEmployeeList();
//            List<Employee> em = emailForm.getEmployees();
//            for (String id: l) {
//                Employee e = emplRepo.findById(Long.parseLong(id)).get();
//                em.add(e);
//            }
//
//            //emailForm.setMessage();
//            //emailForm.setUser();
//            emailRepo.save(emailForm);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//        catch (Exception e) {
//            throw new ControllerException("unknown exception", e);
//        }
//    }



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

    @GetMapping("/user/getEmployeeById/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable(name="id") Long id)throws ControllerException {
        Employee stuff = null;
        try {
            stuff = employeeService.getById(id);
            return new ResponseEntity<>(stuff,HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }


    @GetMapping("/admin/getEmployeeById/{id}")
    public ResponseEntity<?> getEmployeeByIdForAdmin(@PathVariable(name="id") Long id)throws ControllerException {
        Employee stuff = null;
        EmployeeRequest t = new EmployeeRequest();
        try {
            stuff = employeeService.getById(id);
            return new ResponseEntity<>(stuff,HttpStatus.OK);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
    }

    @GetMapping(value = { "/"})
    public ModelAndView indexhome(Model model) throws URISyntaxException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping(value = { "/index"})
    public ModelAndView index(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("employees", emplRepo.findAll());
        log.info("/ was called");
        return modelAndView;
    }

    @PostMapping("/index")
    public @ResponseBody ModelAndView addNewEmployee (@RequestParam String first_name
            , @RequestParam String last_name, @RequestParam Integer age, @RequestParam Float salary, @RequestParam String email, Model model,  BindingResult bindingResult) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        Employee n = new Employee();
        n.setFirstName(first_name);
        n.setLastName(last_name);
        n.setEmail(email);
        n.setAge(age != null ? age : 0);
        n.setSalary(salary != null ? salary : 0);
        employeeValidator.validate(n, bindingResult);
        emplRepo.save(n);
        model.addAttribute("employees", emplRepo.findAll());
        log.info("/index was called");
        return modelAndView;
    }
    @PostMapping("/addEmployee")
    public ResponseEntity<?> addNewEmployee (@RequestBody @Validated EmployeeRequest empl, BindingResult bindingResult) throws ControllerException {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        try {
            log.info("/index was called");
        Employee n = new Employee();
        n.setSalary(empl.getSalary());
        n.setAge(empl.getAge());
        n.setFirstName(empl.getFirstName());
        n.setEmail(empl.getEmail());
        n.setLastName(empl.getLastName());
        employeeValidator.validate(empl, bindingResult);
        emplRepo.save(n);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            log.error("Error after admin/updateEmployee called...");
            throw new ControllerException(e);
        }
    }
    @PutMapping("/admin/updateEmployee")
    public ResponseEntity<?> updateEmployee(@RequestBody EmployeeRequest employeeRequest)throws ControllerException {
        try {
            log.info("admin/updateEmployee called...");
            Employee man = employeeService.getById(employeeRequest.getId());
            employeeService.updateEmployeeById(
                    employeeRequest.getId(),
                    employeeRequest.getFirstName(),
                    employeeRequest.getLastName(),
                    employeeRequest.getAge(),
                    employeeRequest.getSalary(),
                    employeeRequest.getEmail()
            );
            return new ResponseEntity<>(man, HttpStatus.OK);
        } catch (ServiceException e) {
            log.error("Error after admin/updateEmployee called...");
            throw new ControllerException(e);
        }
    }

    @DeleteMapping("/admin/deleteEmployeeById/{id}")
    public ResponseEntity<?> deleteTutorById(@PathVariable(name="id")Long id)throws ControllerException {
        try {
            log.info("admin/deleteEmployeeById called...");
            employeeService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            log.error("Error after admin/deleteEmployeeById called...");
            throw new ControllerException(e);
        }
    }
}
