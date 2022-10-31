package com.example.school.service;

import com.example.school.email.EmailSender;
import com.example.school.email.EmailValidator;
import com.example.school.model.RegistrationRequest;
import com.example.school.model.Student;
import com.example.school.model.UserRole;
import com.example.school.model.token.ConfirmationTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RegistrationServiceTest {
    private StudentService studentService;
    private EmailValidator validator;
    private EmailSender sender;
    private ConfirmationTokenService confirmationTokenService;
    private RegistrationService registrationService;


    @BeforeEach
    public void setUp() {
        studentService = mock(StudentService.class);
        validator = mock(EmailValidator.class);
        sender = mock(EmailSender.class);
        confirmationTokenService=mock(ConfirmationTokenService.class);
        registrationService = new RegistrationService(studentService, validator, sender, confirmationTokenService);
    }


  @Test
  public void whenEmailIsValid_register_shouldReturnString(){

    RegistrationRequest request= new RegistrationRequest("name","surname","email","password");
    Student student= new Student( request.name(),
            request.surname(),
            request.email(),
            request.password(),
            UserRole.USER);

    when(validator.test("email")).thenReturn(true);
    when(studentService.signUpStudent(student)).thenReturn("token");
    verifyNoInteractions(sender);

    String result= registrationService.register(request);
    assertEquals(result,"token");


  }


    @Test
    public void whenEmailIsNotValid_register_shouldThrowIllegalStateException() {
        RegistrationRequest request = new RegistrationRequest("name", "surname", "email", "password");
        when(validator.test("email")).thenReturn(false);
        verifyNoInteractions(validator, studentService, sender);
        assertThrows(IllegalStateException.class, () -> registrationService.register(request));
    }


     // commented out because Transactional methods do not have methods
//    @Test
//    public void whenTokenExits_confirmToken_shouldReturnString() {
//       Student student= new Student();
//       ConfirmationToken token =
//               new ConfirmationToken("token",LocalDateTime.now(),LocalDateTime.now().plusMinutes(15),student);
//        when(confirmationTokenService.getToken("token")).thenReturn(Optional.of(token));
//
//
//        assertNull(token.getConfirmedAt());
//        assertFalse(token.getExpiresAt().isBefore(LocalDateTime.now()));
//        when(confirmationTokenService.setConfirmedAt("token")).thenReturn(1);
//        when(studentService.enableAppUser("email")).thenReturn(1);
//        String result = registrationService.confirmToken("your email has been confirmed");
//
//        assertEquals(result, "string");
//    }
//
//    @Test
//    public void whenTokenDoesNotExits_confirmToken_shouldThrowIllegalStateException() {
//        RegistrationRequest request= new RegistrationRequest("name","surname","email","password");
//        when(confirmationTokenService.getToken("token")).thenReturn(Optional.empty());
//        verifyNoInteractions(studentService);
//        assertThrows(IllegalStateException.class, () -> registrationService.confirmToken("token"));
//
//    }







}