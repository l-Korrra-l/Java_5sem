package bstu.merh.employees.validator;

import bstu.merh.employees.dto.EmployeeRequest;
import bstu.merh.employees.model.EmailForm;
import bstu.merh.employees.model.Employee;
import bstu.merh.employees.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EmployeeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmployeeRequest some=(EmployeeRequest) target;
        if(some.getAge()<0 || some.getAge() > 140){
            errors.rejectValue("id","nonono");
        }
    }
}
