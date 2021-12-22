package bstu.merh.employees.repository;

import bstu.merh.employees.model.Role;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

//Repository
public interface UserRoleRepository {
        //extends JpaRepository<Role, Integer> {
    Role findByName(Role name);
}
