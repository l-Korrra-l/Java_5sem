package bstu.merh.employees.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegistrationRequest {
    //   @NotEmpty
    private String username;

    //@NotEmpty
    private String password;

    //@NotEmpty
    private String email;
}