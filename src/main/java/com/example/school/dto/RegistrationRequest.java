package com.example.school.dto;

public class RegistrationRequest {
    private final String firstName;
    private final String lastName;
    private final String password;
    private final String email;

    public RegistrationRequest(String firstName, String lastName, String password, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
    }
}
