package bstu.merh.employees.service;

import bstu.merh.employees.model.Role;
import bstu.merh.employees.model.User;
import bstu.merh.employees.model.UserRole;
import bstu.merh.employees.repository.UserRepository;
import bstu.merh.employees.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bstu.merh.employees.service.interfaces.IUserService;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepo;

    public User saveUser(User user)
    {
        UserRole userRole = userRoleRepo.findByName(Role.ROLE_USER);
        user.setRole(userRole);
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public List<User> findAll(){
        return userRepository.findAll();
    }
    public User findByUsernameAndPassword(String username, String password){
        User user = findByUsername(username);
        if (user != null) {
            if (password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }
    public boolean existsUserByUsername(String login)
    {
        return userRepository.existsUserByUsername(login);
    }
    public boolean existsUserByUsernameAndPassword(String username, String password){
        return findByUsernameAndPassword(username, password) != null;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findById(Long id){
        return userRepository.getById(id);
    }
}
