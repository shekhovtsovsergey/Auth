package com.shekhovtsov.auth.dto;


import lombok.*;

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
}