package bstu.merh.employees.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "emailForm")
@Data
@Getter
@Setter
public class EmailForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //list or cycle
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "emailForm_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees;
}
