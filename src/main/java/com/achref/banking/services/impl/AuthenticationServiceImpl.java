package com.achref.banking.services.impl;

import com.achref.banking.dto.JwtAuthenticationResponse;
import com.achref.banking.dto.SigninRequest;
import com.achref.banking.dto.UserDto;
import com.achref.banking.models.Role;
import com.achref.banking.models.User;
import com.achref.banking.repositories.UserRepository;
import com.achref.banking.services.AuthenticationService;
import com.achref.banking.services.JwtService;
import com.achref.banking.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final ObjectsValidator<UserDto> validator;

    @Override
    public JwtAuthenticationResponse signup(UserDto userDto) {
        validator.validate(userDto);
        User user = UserDto.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        var savedUser = userRepository.save(user);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedUser.getId());
        claims.put("fullName", savedUser.getFirstname() + " " + savedUser.getLastname());
        String token = jwtService.generateToken(claims,savedUser);
        return JwtAuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}

