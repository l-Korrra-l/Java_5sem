package bstu.merh.employees.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "email_form")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
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
    @JoinColumn(name = "employee_id")
    private List<Employee> employee;
}
