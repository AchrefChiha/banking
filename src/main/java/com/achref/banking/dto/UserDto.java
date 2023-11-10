package com.achref.banking.dto;

import com.achref.banking.models.User;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDto {

    private Integer id;

    @NotNull(message = "FIRSTNAME_IS_NOT_BLANK")
    @NotBlank(message = "FIRSTNAME_IS_NOT_BLANK")
    @NotEmpty(message = "FIRSTNAME_IS_NOT_BLANK")
    private String firstname;

    @NotNull(message = "LASTNAME_IS_NOT_BLANK")
    @NotBlank(message = "LASTNAME_IS_NOT_BLANK")
    @NotEmpty(message = "LASTNAME_IS_NOT_BLANK")
    private String lastname;

    @NotNull(message = "EMAIL_IS_NOT_BLANK")
    @NotBlank(message = "EMAIL_IS_NOT_BLANK")
    @NotEmpty(message = "EMAIL_IS_NOT_BLANK")
    @Email(message = "Must be in email format")
    private String email;

    @NotNull(message = "PASSWORD_IS_NOT_BLANK")
    @NotBlank(message = "PASSWORD_IS_NOT_BLANK")
    @NotEmpty(message = "PASSWORD_IS_NOT_BLANK")
    @Size(min = 8,max = 16, message = "Password length must be between 8 and 16 Characters.")
    private String password;

    @Past
    private LocalDateTime birthday;

    public static UserDto fromEntity(User user){
        return UserDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public static User toEntity(UserDto user){
        return User.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

}
