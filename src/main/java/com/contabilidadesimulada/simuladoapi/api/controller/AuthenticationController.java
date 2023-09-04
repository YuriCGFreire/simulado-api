package com.contabilidadesimulada.simuladoapi.api.controller;


import com.contabilidadesimulada.simuladoapi.api.model.RegistrationBody;
import com.contabilidadesimulada.simuladoapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser( @RequestBody @Valid RegistrationBody registrationBody) throws Exception{
        return new ResponseEntity<>(userService.registerUser(registrationBody), HttpStatus.CREATED);
    }
}
