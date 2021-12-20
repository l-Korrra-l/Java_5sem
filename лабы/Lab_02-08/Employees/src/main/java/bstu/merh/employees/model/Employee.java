package bstu.merh.employees.model;

import java.util.List;
import javax.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Employees")
@Getter
@Setter
@RequiredArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String first_name;

    @Column(name = "last_name")
    private String last_name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "salary")
    private Float salary;

    @Column(name = "mail")
    private String mail;

//    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Integer company_id;

    //getters
    public Long getId() {
        return id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public Integer getAge() {
        return age;
    }

    public Float getSalary() {
        return salary;
    }

    public String getMail() {
        return mail;
    }

    public Integer getCompany_id() {
        return company_id;
    }

    //setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setCompany_id(Integer company_id) {
        this.company_id = company_id;
    }
}
