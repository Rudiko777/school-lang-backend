package Rudiko.schoollanguages.config;

import Rudiko.schoollanguages.service.UserService;
import Rudiko.schoollanguages.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/v1/languageCourses").permitAll()
                .requestMatchers("/api/v1/languageCourses/**").permitAll()
                .requestMatchers(request -> request.getRequestURI().equals("/registration")).permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/").hasRole("USER")
                .requestMatchers("/", "/resources/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(formLogin -> formLogin
                    .loginPage("/login")
                    .defaultSuccessUrl("/")
                    .permitAll()
            )
            .logout(logout -> logout
                    .permitAll()
                    .logoutSuccessUrl("/")
            );
        return http.build();
    };
}
