package bstu.merh.employees.dto;

import bstu.merh.employees.model.Employee;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class EmailRequest {
    private List<String> employeeList;
}
