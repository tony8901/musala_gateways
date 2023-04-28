package com.musala.gateways.security.controllers;

import com.musala.gateways.security.payload.LoginRequest;
import com.musala.gateways.security.payload.RegisterRequest;
import com.musala.gateways.security.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para llevar a cabo la autenticación utilizando JWT *
 * Se utiliza AuthenticationManager para autenticar las credenciales que son el
 * usuario y password que llegan por POST en el cuerpo de la petición
 * Si las credenciales son válidas se genera un token JWT como respuesta
 */
// @CrossOrigin(origins = "http://localhost:8080/api/auth")

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("/login")
    public ResponseEntity<String> loginGet(){
        return ResponseEntity.ok("Welcome to login :) ");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest signUpRequest) {
        return authService.register(signUpRequest);
    }
}
