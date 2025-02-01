package com.tenet.service;


import com.tenet.dao.AuthenticationRequest;
import com.tenet.dao.AuthenticationResponse;
import com.tenet.dao.RegisterRequest;
import com.tenet.exceptions.UserNotFoundException;
import com.tenet.model.Role;
import com.tenet.model.User;
import com.tenet.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .enabled(true)
                .build();
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UserNotFoundException("User with Email " + request.getEmail() + " not found"));
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token("Bearer " + token).build();
    }
}
