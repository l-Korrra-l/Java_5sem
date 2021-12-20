package bstu.merh.employees.service;

import bstu.merh.employees.model.User;
import org.springframework.stereotype.Service;
import bstu.merh.employees.service.interfaces.IUserInterface;

import java.util.List;

@Service
public class UserService implements IUserInterface {

    @Override
    public List<User> findAll() {
        return null;
    }
}
