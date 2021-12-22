package bstu.merh.employees.service.interfaces;

import bstu.merh.employees.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUserService {
    User saveUser(User user);
    User findByUsername(String username);
    List<User> findAll();
    User findById(Long id);
    User findByUsernameAndPassword(String username, String password);
    boolean existsUserByUsername(String username);
    boolean existsUserByUsernameAndPassword(String username, String password);
    void delete(Long id);
}
