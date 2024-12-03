package com.dev.identify.dev.configuration;

import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private static final String[] PUBLIC_ENDPOINTS = {
            "/users",
            "/auth/token",
            "/auth/introspect",
            "/auth/logout",
            "/auth/refreshtoken"
    };

    @NonFinal
    @Value("${jwt.signerKey}")
    String SIGNER_KEY;

    CustomJwtDecoder customJwtDecoder; // error when inject with @Autowired

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeHttpRequests(request ->
                request.requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS).permitAll()
                        .anyRequest().authenticated())

        // decode chuoi jwt truyen vao
        // khi config oauth2ResourceServer se dang ki 1 cai provideManager de support jwtToken
        // khi want to validation jwt do can define 1 jwtDecoder
                .oauth2ResourceServer(oauth2 ->
                    oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(customJwtDecoder)
                                .jwtAuthenticationConverter(jwtAuthenticationConverter()))
                        // config when authen failed
                        .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
        );

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }

    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();

//        corsConfiguration.addAllowedOrigin("http://localhost:8080");
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:8080","http://localhost:8888"));
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration); // allowed all endpoint

        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

    // CAu hinh prefix cua claim ROLE_
    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");// add pre fix for roles

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

}
