package com.dev.profile.configuration;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;

public class CustomJwtDecoder implements JwtDecoder {


    @Override
    public Jwt decode(String token) throws JwtException {
        return null;
    }
}
