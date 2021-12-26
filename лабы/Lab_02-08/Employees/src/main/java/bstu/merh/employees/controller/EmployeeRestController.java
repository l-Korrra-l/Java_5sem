package bstu.merh.employees.controller;

import bstu.merh.employees.jwt.JwtProvider;
import bstu.merh.employees.model.EmailForm;
import bstu.merh.employees.repository.EmailFormRepository;
import bstu.merh.employees.repository.EmployeeRepository;
import bstu.merh.employees.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
public class EmployeeRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private EmployeeRepository emplRepo;
    @Autowired
    private EmailFormRepository emailRepo;

    @PostMapping("/emailForm")
    public String addEmail(EmailForm emailForm, Model model) {

        emailRepo.save(emailForm);
        return "ok";
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
}
