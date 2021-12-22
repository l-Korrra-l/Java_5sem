package bstu.merh.employees.repository;

import bstu.merh.employees.model.Role;
import bstu.merh.employees.model.UserRole;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByName(Role name);
}
