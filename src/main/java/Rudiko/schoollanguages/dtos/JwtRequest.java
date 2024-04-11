package Rudiko.schoollanguages.dtos;

import lombok.Data;

@Data
public class JwtRequest {
    private String fullName;
    private String password;
}
