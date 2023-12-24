package com.shekhovtsov.auth.controller;

import com.shekhovtsov.auth.dao.UserRepository;
import com.shekhovtsov.auth.dto.JwtRequestDto;
import com.shekhovtsov.auth.dto.RegistrationUserDto;
import com.shekhovtsov.auth.model.User;
import com.shekhovtsov.auth.service.JwtAuthorizationFilter;
import com.shekhovtsov.auth.service.JwtTokenService;
import com.shekhovtsov.auth.service.JwtUtil;
import io.jsonwebtoken.*;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final JwtTokenService jwtTokenService;
    private final JwtUtil jwtUtil;
    private final JwtAuthorizationFilter jwtAuthorizationFilter;
    private final UserRepository userRepository;


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


    @GetMapping("/profile")
    public Optional<User> getProfileFromToken(@RequestHeader("Authorization") String authorizationHeader) throws NotFoundException, JwtException {
        try {
            String token = authorizationHeader.replace("Bearer ", "");
            Authentication authentication = jwtAuthorizationFilter.convertClaimsToAuthentication(jwtUtil.getAllClaimsFromToken(token));
            String username = authentication.getName();
            System.out.println("проверка " + username);
            if (username != null) {
                return userRepository.findByUsername(username);
            } else {
                throw new NotFoundException("Пользователь не найден");
            }
        } catch (JwtException e) {
            throw new JwtException("Invalid token", e);
        }
    }

    //просмотр профиля
    //проверка токена
    //выход  - сделать токен истекшим*
    //редактирование (post/patch)
    //+порты исправить


}