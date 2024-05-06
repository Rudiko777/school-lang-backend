package Rudiko.schoollanguages.dtos;

import Rudiko.schoollanguages.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {
    public String token;
    public User user;
}
