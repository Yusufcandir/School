package com.example.school.config;

import com.example.school.service.StudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
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


    //can be used

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

    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/v*/student/**")
                .hasRole("USER")
                .antMatchers("/v*/registration/**", "/h2/**", "/h2/*", "/h2-console/**", "/h2-console/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/homepage.html", true)
                .failureUrl("/login.html?error=true")
                .and()
                .logout()
                .logoutUrl("/perform_logout")
                .deleteCookies("JSESSIONID");


        return http.build();

     // for more specific authentication
//                    auth.antMatchers(HttpMethod.GET, "/v1/instructor/**").hasAnyRole("ADMIN", "USER");
//                    auth.antMatchers(HttpMethod.POST, "/v1/instructor/**").hasRole("ADMIN");
//                    auth.antMatchers(HttpMethod.DELETE, "/v1/instructor/**").hasRole("ADMIN");
//                    auth.antMatchers(HttpMethod.PUT, "/v1/instructor/**").hasRole("ADMIN");
//
//                    auth.antMatchers(HttpMethod.GET, "/v1/student/**").hasAnyRole("ADMIN", "USER");
//                    auth.antMatchers(HttpMethod.POST, "/v1/student/**").hasRole("ADMIN");
//                    auth.antMatchers(HttpMethod.DELETE, "/v1/student/**").hasRole("ADMIN");
//                    auth.antMatchers(HttpMethod.PUT, "/v1/student/**").hasRole("ADMIN");

//                })
//                .httpBasic(Customizer.withDefaults())
//                .build();
    }


    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(encoder.bCryptPasswordEncoder());
        provider.setUserDetailsService(service);

        return provider;
    }
}
