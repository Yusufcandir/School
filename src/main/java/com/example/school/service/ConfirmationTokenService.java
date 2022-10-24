package com.example.school.service;

import com.example.school.model.ConfirmationToken;
import com.example.school.repository.ConfirmationTokenRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository repository;


    public ConfirmationTokenService(ConfirmationTokenRepository repository) {
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
