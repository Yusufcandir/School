package com.example.school.config;

import com.example.school.service.StudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final StudentService service;
    private final PasswordEncoder encoder;

    public SecurityConfig(StudentService service, PasswordEncoder encoder) {

        this.service = service;
        this.encoder = encoder;
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//
//        // it is just for readability
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("password")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//
//
//    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .authorizeRequests(auth ->
                {
                    auth.anyRequest().authenticated();
//
//                    auth.antMatchers(HttpMethod.GET, "/v1/instructor/**").hasAnyRole("ADMIN", "USER");
//                    auth.antMatchers(HttpMethod.POST, "/v1/instructor/**").hasRole("ADMIN");
//                    auth.antMatchers(HttpMethod.DELETE, "/v1/instructor/**").hasRole("ADMIN");
//                    auth.antMatchers(HttpMethod.PUT, "/v1/instructor/**").hasRole("ADMIN");
//
//                    auth.antMatchers(HttpMethod.GET, "/v1/student/**").hasAnyRole("ADMIN", "USER");
//                    auth.antMatchers(HttpMethod.POST, "/v1/student/**").hasRole("ADMIN");
//                    auth.antMatchers(HttpMethod.DELETE, "/v1/student/**").hasRole("ADMIN");
//                    auth.antMatchers(HttpMethod.PUT, "/v1/student/**").hasRole("ADMIN");
//
//
//                    auth.antMatchers(HttpMethod.POST, "/v1/registration/**").hasRole("ADMIN");
//                    auth.antMatchers(HttpMethod.GET, "/v1/registration/**").hasAnyRole("ADMIN", "USER");


                })
                .httpBasic(Customizer.withDefaults())
                .build();


    }


    protected void auth(AuthenticationManagerBuilder builder){
        builder.authenticationProvider(provider());
    }

    @Bean
    DaoAuthenticationProvider provider(){
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder.bCryptPasswordEncoder());
        provider.setUserDetailsService(service);
        return provider;
    }



}
