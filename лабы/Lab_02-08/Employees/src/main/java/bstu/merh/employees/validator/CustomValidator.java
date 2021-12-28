package bstu.merh.employees.validator;

import bstu.merh.employees.model.EmailForm;
import bstu.merh.employees.model.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CustomValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EmailForm some=(EmailForm)target;
        if(some.getId()<0){
            errors.rejectValue("id","negative value");
        }
    }
}
