package com.achref.banking.services;

import com.achref.banking.dto.JwtAuthenticationResponse;
import com.achref.banking.dto.SigninRequest;
import com.achref.banking.dto.UserDto;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(UserDto user);

    JwtAuthenticationResponse signin(SigninRequest request);


}