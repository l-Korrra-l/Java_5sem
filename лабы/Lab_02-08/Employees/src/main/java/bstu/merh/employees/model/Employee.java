package bstu.merh.employees.model;

import java.util.List;
import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "Employees")
@Getter
@Setter
@RequiredArgsConstructor
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "salary")
    private Float salary;

    @Column(name = "mail")
    private String mail;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "company_id")
//    private Integer company_id;

    //

    public Employee(String first_name, String last_name, Integer age, Float salary, String mail) {
        this.firstName = first_name;
        this.lastName = last_name;
        this.age = age;
        this.salary = salary;
        this.mail = mail;
    }


}
