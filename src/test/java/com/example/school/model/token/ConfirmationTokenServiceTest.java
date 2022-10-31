package com.example.school.model.token;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ConfirmationTokenServiceTest {

 private ConfirmationTokenRepository repository;
 private ConfirmationTokenService service;

 @BeforeEach
    public void setUp(){
     repository=mock(ConfirmationTokenRepository.class);
     service=new ConfirmationTokenService(repository);
 }

 @Test
    public void whenTokenExits_getToken_shouldReturnConfirmationToken(){
     Optional<ConfirmationToken> token= Optional.of(new ConfirmationToken());
     when(repository.findByToken("token")).thenReturn(token);


     Optional<ConfirmationToken> result= service.getToken("token");
     assertEquals(result,token);

 }

 @Test
    public void setConfirmedAt_shouldReturnInteger(){
     when(repository.updateConfirmedAt("token", LocalDateTime.now())).thenReturn(0);

     int result=service.setConfirmedAt("token");
     assertEquals(result,0);
 }
}