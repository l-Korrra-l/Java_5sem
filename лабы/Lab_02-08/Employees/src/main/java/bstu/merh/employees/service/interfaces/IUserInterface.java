package bstu.merh.employees.service.interfaces;

import bstu.merh.employees.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserInterface {
    List<User> findAll();
}
