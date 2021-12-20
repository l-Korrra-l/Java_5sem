package bstu.merh.employees.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "user")
@Data
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String login;

    @Column
    private String password;

//    ManyToOne
    @JoinColumn(name = "role_id")
    private Role userRole;

    @Column
    private String email;
}
