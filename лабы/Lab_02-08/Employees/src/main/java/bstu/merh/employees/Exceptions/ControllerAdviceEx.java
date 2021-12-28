package bstu.merh.employees.Exceptions;

import com.google.common.base.Throwables;
import bstu.merh.employees.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ControllerAdviceEx {
    @ExceptionHandler(value = ControllerException.class)
    public ResponseEntity<Object> handleControllerException(ControllerException exception, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, WebRequest request) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("errorMessage", Throwables.getRootCause(exception));
        return modelAndView;
    }
}
