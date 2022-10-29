package com.example.school.controller;

import com.example.school.model.RegistrationRequest;
import com.example.school.service.RegistrationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/registration")
public class RegistrationController {
    private final RegistrationService service;

    public RegistrationController(RegistrationService service) {
        this.service = service;
    }

    @PostMapping
    public String register(@RequestBody RegistrationRequest request){
        return service.register(request);
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam ("token") String token){
        return service.confirmToken(token);
    }
}
