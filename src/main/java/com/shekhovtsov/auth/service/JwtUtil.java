package com.shekhovtsov.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    public Claims getAllClaimsFromToken(String token) {
        System.out.println("Token received: " + token);
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        System.out.println("Token parsed: " + claims);
        return claims;
    }

    private boolean isTokenExpired(String token) {
        System.out.println("Checking token expiration");
        Date expirationDate = this.getAllClaimsFromToken(token).getExpiration();
        boolean isExpired = expirationDate != null && expirationDate.before(new Date());
        System.out.println("Token expiration status: " + (isExpired ? "Expired" : "Not expired"));
        return isExpired;
    }

    public boolean isInvalid(String token) {
        System.out.println("Checking token validity");
        boolean isInvalid = isTokenExpired(token);
        System.out.println("Token validity: " + (isInvalid ? "Invalid" : "Valid"));
        return isInvalid;
    }

    public boolean isValid(String token) {
        System.out.println("Checking token validity");
        boolean isValid = !isInvalid(token);
        System.out.println("Token validity: " + (isValid ? "Valid" : "Invalid"));
        return isValid;
    }
}
