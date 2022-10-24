package com.example.school.model;

import lombok.Data;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
@Entity
@Data
public class Student implements UserDetails {
        @Id
        @GeneratedValue(generator = "UUID")
        @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
        String id;

        String name;
        String surname;
        String email;

        boolean locked;
        boolean enabled;
        String password;

        @Enumerated(EnumType.STRING)
        UserRole userRole;
        @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
        Set<Instructor> instructors;




        public Student(String name, String surname, String email,Set<Instructor> instructor) {
                this.name = name;
                this.surname = surname;
                this.email = email;
                this.instructors=instructor;

        }

        public Student(String id, String name, String surname, String email, Set<Instructor> instructors) {
                this.id = id;
                this.name = name;
                this.surname = surname;
                this.email = email;
                this.instructors = instructors;
        }

        public Student() {

        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                SimpleGrantedAuthority authority= new SimpleGrantedAuthority(userRole.name());
                return Collections.singletonList(authority);
        }

        @Override
        public String getPassword() {
                return password;
        }

        @Override
        public String getUsername() {
                return name;
        }

        @Override
        public boolean isAccountNonExpired() {
                return true;
        }

        @Override
        public boolean isAccountNonLocked() {
                return !locked;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return true;
        }

        @Override
        public boolean isEnabled() {
                return enabled;
        }
}
