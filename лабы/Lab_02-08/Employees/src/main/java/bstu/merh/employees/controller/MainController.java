package bstu.merh.employees.controller;

import bstu.merh.employees.model.Employee;
import bstu.merh.employees.model.Role;
import bstu.merh.employees.model.User;
import bstu.merh.employees.repository.EmployeeRepository;
import bstu.merh.employees.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;

@Slf4j
@Controller
@RequestMapping
public class MainController {

    @Value("${welcome.message}")
    private String message;

//    @GetMapping(value = {"/", "/index"})
//    public ModelAndView index(Model model) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("index");
//        model.addAttribute("message", message);
//        log.info("/index was called");
//        return  modelAndView;
//    }

    @Autowired
    private EmployeeRepository emplRepo;

    @Autowired
    private UserRepository userRepo;

    private static List<Employee> empl;
    @GetMapping(value = { "/index"})
    public ModelAndView index(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        model.addAttribute("employees", emplRepo.findAll());
        log.info("/allempl was called");
        return modelAndView;
    }

    @PostMapping("/index")
    public @ResponseBody ModelAndView addNewEmployee (@RequestParam String first_name
            , @RequestParam String last_name, @RequestParam Integer age, @RequestParam String email, Model model) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        Employee n = new Employee();
        n.setFirstName(first_name);
        n.setLastName(last_name);
        n.setEmail(email);
        n.setAge(age);
        emplRepo.save(n);
        model.addAttribute("employees", emplRepo.findAll());
        log.info("/allempl was called");
        return modelAndView;
    }

    @PostMapping("/")
    public @ResponseBody ModelAndView addNewEmployeedef (@RequestParam String first_name
            , @RequestParam String last_name, @RequestParam Integer age, @RequestParam String email, Model model) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        Employee n = new Employee();
        n.setFirstName(first_name);
        n.setLastName(last_name);
        n.setEmail(email);
        n.setAge(age);
        emplRepo.save(n);
        model.addAttribute("employees", emplRepo.findAll());
        log.info("/allempl was called");
        return modelAndView;
    }
    @PostMapping("filter")
    public @ResponseBody ModelAndView addNewEmployee (@RequestParam String filter,  Model model) {

        if (filter != null && !filter.isEmpty()){
            model.addAttribute("employees", emplRepo.findByFirstNameContains(filter));
        }
        else {
            model.addAttribute("employees", emplRepo.findAll());
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");

        log.info("/filter was called");
        return modelAndView;
    }
//
//    @GetMapping("/registration")
//    public @ResponseBody ModelAndView registration(Model model) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("registration");
//        return modelAndView;
//    }
//
////    @PostMapping("/registration")
////    public @ResponseBody ModelAndView addUser(Model model, User user) {
////        ModelAndView modelAndView = new ModelAndView();
////        User userFromDb = userRepo.findByUsername(user.getUsername());
////        if (userFromDb != null){
////            model.addAttribute("message", "User exists");
////            modelAndView.setViewName("registration");
////        } else{
////            user.setActive(true);
////            user.setUserRole(Collections.singleton(Role.USER));
////            userRepo.save(user);
////            modelAndView.setViewName("login");
////        }
////
////        return modelAndView;
////    }
//
}
