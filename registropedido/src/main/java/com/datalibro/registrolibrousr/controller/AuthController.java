package com.datalibro.registrolibrousr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datalibro.registrolibrousr.dto.AuthResponse;
import com.datalibro.registrolibrousr.dto.LoginRequest;
import com.datalibro.registrolibrousr.model.User;
import com.datalibro.registrolibrousr.security.JwtService;
import com.datalibro.registrolibrousr.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path="/api/v1/auth")
@Tag (name="Inicio de sesion", description = "Operaciones relacionadas con el inicio de sesion")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value="/login")
    @Operation(summary = "Inicio de sesion", description = "Verifica credenciales ingresadas y genera token")
    public AuthResponse login(@RequestBody LoginRequest request){

        User user = userService.findByUsername(request.getUsername());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Credenciales invalidas");
        }

        String token = jwtService.generatedToken(user.getUsername());

        return new AuthResponse(token);
    }
    
}
