package com.tenet.controller;

import com.tenet.dao.AuthenticationRequest;
import com.tenet.dao.AuthenticationResponse;
import com.tenet.dao.RegisterRequest;
import com.tenet.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest registerRequest) {
        return ResponseEntity.ok(authService.authenticate(registerRequest));
    }
}
