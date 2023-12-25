package com.shekhovtsov.auth.dto;


import lombok.*;
import org.springframework.jdbc.core.SqlReturnType;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
public class RegistrationUserDto {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
    private String request;
}