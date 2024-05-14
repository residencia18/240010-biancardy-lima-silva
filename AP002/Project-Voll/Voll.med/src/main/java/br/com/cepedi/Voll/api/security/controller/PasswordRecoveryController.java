package br.com.cepedi.Voll.api.security.controller;

import br.com.cepedi.Voll.api.security.model.entitys.User;
import br.com.cepedi.Voll.api.security.model.records.input.DataRequestResetPassword;
import br.com.cepedi.Voll.api.security.model.records.input.DataResetPassword;
import br.com.cepedi.Voll.api.security.service.EmailService;
import br.com.cepedi.Voll.api.security.service.TokenService;
import br.com.cepedi.Voll.api.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequestMapping("/reset-password")
public class PasswordRecoveryController {

    private final UserService userService;
    private final TokenService tokenService;
    private final EmailService emailService;

    @Autowired
    public PasswordRecoveryController(UserService userService, TokenService tokenService, EmailService emailService) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.emailService = emailService;
    }

    @PostMapping("/request")
    public ResponseEntity<String> resetPasswordRequest(@RequestBody @Valid DataRequestResetPassword dataResetPassword) {
        User user = userService.getUserActivatedByEmail(dataResetPassword.getEmail());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("E-mail not found");
        }

        // Generate a token for the user
        String token = tokenService.generateTokenRecoverPassword(user);

        try {
            // Send an email with the recovery link containing the token
            emailService.sendResetPasswordEmail(user.getName(), dataResetPassword.getEmail(), token);
            String responseMessage = "A password reset email has been sent to " + dataResetPassword.getEmail();
            return ResponseEntity.ok(responseMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email");
        }
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestBody @Valid DataResetPassword dataResetPassword) {
        // Validate the token
        if (tokenService.isValidToken(dataResetPassword.getToken())) {
            // Get the email associated with the token
            String email = tokenService.getEmailByToken(dataResetPassword.getToken());
            // Update user's password
            userService.updatePassword(email, dataResetPassword.getPassword());
            tokenService.revokeToken(dataResetPassword.getToken());
            return ResponseEntity.ok("Password updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired token");
        }
    }
}
