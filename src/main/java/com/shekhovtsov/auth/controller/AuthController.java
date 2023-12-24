package com.shekhovtsov.auth.controller;

import com.shekhovtsov.auth.dto.JwtRequestDto;
import com.shekhovtsov.auth.dto.RegistrationUserDto;
import com.shekhovtsov.auth.service.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenService jwtTokenService;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequestDto jwtRequestDto) {
        System.out.println("контроллер createAuthToken" + jwtRequestDto);
        ResponseEntity<?> token = jwtTokenService.createAuthToken(jwtRequestDto);
        System.out.println(token);
        return token;
    }

    @PostMapping("/reg")
    public ResponseEntity<?> createAuthTokenAndRegisterUser(@RequestBody RegistrationUserDto registrationUserDto) {
        return jwtTokenService.createAuthTokenAndRegisterUser(registrationUserDto);
    }

    @GetMapping("/public")
    public ResponseEntity<?> getPublicResource() {
        return ResponseEntity.ok("This is a public resource");
    }


    @GetMapping("/protected")
    public ResponseEntity<?> getProtectedResource() {
        return ResponseEntity.ok("This is a protected resource");
    }

    //просмотр профиля
    //проверка токена
    //выход  - сделать токен истекшим*
    //редактирование (post/patch)
    //порты исправить


}