package bstu.merh.employees.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EmployeeRequest {
    public Long id;
    public String firstName;
    public String lastName;
    public Integer age;
    public Float salary;
    public String email;
}
