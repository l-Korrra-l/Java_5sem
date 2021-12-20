package bstu.merh.employees.controller;

import bstu.merh.employees.Exceptions.ControllerException;
import bstu.merh.employees.model.User;
import bstu.merh.employees.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainRestController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public List<User> getUsers() throws ControllerException {
//        try {
//            logger.debug("getting all users");
//
            return userService.findAll();
//        } catch (Exception e) {
//            logger.error("error get all users");
//
//            throw new ControllerException("getUsers", e);
//        }
    }
}
