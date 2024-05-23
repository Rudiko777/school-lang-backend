package Rudiko.schoollanguages.service;

import Rudiko.schoollanguages.dtos.JwtRequest;
import Rudiko.schoollanguages.dtos.JwtResponse;
import Rudiko.schoollanguages.model.User;
import Rudiko.schoollanguages.service.impl.AuthServiceImpl;
import Rudiko.schoollanguages.service.impl.UserServiceImpl;
import Rudiko.schoollanguages.utils.JwtTokenUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class AuthServiceTest {
    @InjectMocks
    private AuthServiceImpl authService;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private JwtTokenUtils jwtTokenUtils;
    @Mock
    private AuthenticationManager authenticationManager;
    @Captor
    private ArgumentCaptor<UsernamePasswordAuthenticationToken> authenticationTokenCaptor;

    @Test
    public void testCreateAuthToken_WithValidCredentials_ReturnsToken() {
        JwtRequest authRequest = new JwtRequest();
        authRequest.setFullName("TestName");
        authRequest.setPassword("password");
        User user = new User();
        user.setFullName("TestName");
        UserDetails userDetails = userService.loadUserByUsername(user.getFullName());
        String token = "testToken";

        Mockito.when(userService.loadUserByUsername("TestName")).thenReturn(userDetails);
        Mockito.when(jwtTokenUtils.generateToken(userDetails)).thenReturn(token);
        Mockito.doAnswer(invocation -> null).when(authenticationManager)
                .authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class));
        ResponseEntity<?> response = authService.createAuthToken(authRequest);
        Mockito.verify(authenticationManager).authenticate(authenticationTokenCaptor. capture());
        UsernamePasswordAuthenticationToken authenticationToken = authenticationTokenCaptor.getValue();
        Assertions.assertEquals(authRequest.getFullName(), authenticationToken.getPrincipal());
        Assertions.assertEquals(authRequest.getPassword(), authenticationToken.getCredentials());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(new JwtResponse(token, user), response.getBody());
    }

    @Test
    public void testCreateAuthToken_WithInvalidCredentials_ReturnsUnauthorized() {
        JwtRequest authRequest = new JwtRequest();
        authRequest.setFullName("Test");
        authRequest.setPassword("badPassword");
        Mockito.doThrow(BadCredentialsException.class).when(authenticationManager) .authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class));
        ResponseEntity<?> response = authService.createAuthToken(authRequest);
        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}
