package bstu.merh.employees.controller;

import bstu.merh.employees.Exceptions.ControllerException;
import bstu.merh.employees.dto.AuthRequest;
import bstu.merh.employees.dto.AuthResponse;
import bstu.merh.employees.dto.RegistrationRequest;
import bstu.merh.employees.dto.UserResponse;
import bstu.merh.employees.jwt.JwtProvider;
import bstu.merh.employees.model.User;
import bstu.merh.employees.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class MainRestController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/users")
    public List<User> getUsers() throws ControllerException {
        try {
            //logger.debug("getting all users");

            return userService.findAll();
        } catch (Exception e) {
            //logger.error("error get all users");

            throw new ControllerException("getUsers", e);
        }
    }

    @GetMapping("/login")
    public @ResponseBody ModelAndView loginEmpty(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest authRequest) throws ControllerException
    {
        try{
            //logger.debug("try to login user");
            User user = userService.findByUsernameAndPassword(authRequest.getUsername(), authRequest.getPassword());
            if(user != null)
            {
                String token = jwtProvider.generateToken(user.getUsername());
                AuthResponse response = new AuthResponse(token, user.getRole().getName());
                System.out.println(user.getRole().getName());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else
            {
                throw new ControllerException("not such user");
            }
        } catch (ControllerException e) {
            //logger.error("error login");

            throw new ControllerException("auth", e);
        }
    }
    @GetMapping("/registration")
    public @ResponseBody ModelAndView registerEmpty(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("registration");
        return modelAndView;
    }
    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest)
    {
        //logger.debug("try to register user");
        if(!userService.existsUserByUsername(registrationRequest.getUsername()))
        {
            User user = new User();
            user.setPassword(registrationRequest.getPassword());
            user.setUsername(registrationRequest.getUsername());
            user.setEmail(registrationRequest.getEmail());
            user.setActive(true);
            userService.saveUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
    }

    @PostMapping("/authorized")
    public ResponseEntity<?> isAuthorized() throws ControllerException {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/getUser")
    public UserResponse getUser(@RequestHeader(name = "Authorisation") String jwt) throws ControllerException {
        try {

            String userName = jwtProvider.getUsernameFromToken(jwt.substring(7));
            User user = userService.findByUsername(userName);

            return new UserResponse(user.getId(), user.getUsername(), user.getRole().getName());
        } catch (Exception e) {
            throw new ControllerException("getUser", e);
        }
    }

}
