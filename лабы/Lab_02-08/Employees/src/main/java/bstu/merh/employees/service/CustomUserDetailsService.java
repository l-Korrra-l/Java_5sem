package bstu.merh.employees.service;

import bstu.merh.employees.model.User;
import bstu.merh.employees.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService  implements UserDetailsService {
    @Autowired
    private IUserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.findByUsername(username);
            return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
        } catch (Exception e) {
            throw new UsernameNotFoundException("user" + username +" not found");
        }
    }
}
