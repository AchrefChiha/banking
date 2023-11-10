package com.achref.banking.services;

import com.achref.banking.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends AbstractService<UserDto> {

    Integer validateAccount(Integer id);
    Integer invalidateAccount(Integer id);

    UserDetailsService userDetailsService();

}
