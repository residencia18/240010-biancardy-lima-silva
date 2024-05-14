package br.com.cepedi.Voll.api.security.controller;

import br.com.cepedi.Voll.api.security.model.records.details.DataDetailsRegisterUser;
import br.com.cepedi.Voll.api.security.model.records.input.DataRegisterUser;
import br.com.cepedi.Voll.api.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;

    @Autowired
    public RegisterController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<DataDetailsRegisterUser> registerUser(@RequestBody @Valid DataRegisterUser data) {
        DataDetailsRegisterUser dataDetailsRegisterUser = authService.register(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(dataDetailsRegisterUser);
    }

}
