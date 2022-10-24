package com.example.school.service;

import com.example.school.model.ConfirmationToken;
import com.example.school.repository.ConfirmationTokenRepository;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationEmailService {

    private final ConfirmationTokenRepository repository;


    public ConfirmationEmailService(ConfirmationTokenRepository repository) {
        this.repository = repository;
    }

    public void save(ConfirmationToken token){
        repository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token){

        return repository.findByToken(token);
    }

    public int confirmedAt(String token){
        return repository.updateConfirmedAt(token, LocalDateTime.now());
    }
}
