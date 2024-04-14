package Rudiko.schoollanguages.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private String fullName;
    private String password;
    private String confirmPassword;
    private String phoneNumber;
    private String birthday;
    private String gender;
    private String login;
    private String email;
}
