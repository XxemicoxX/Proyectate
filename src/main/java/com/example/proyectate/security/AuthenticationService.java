package com.example.proyectate.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.proyectate.feature.users.UserRepository;
import com.example.proyectate.feature.users.User;
import com.example.proyectate.util.AuthenticationRequest;
import com.example.proyectate.util.AuthenticationResponse;
import com.example.proyectate.util.RefreshTokenRequest;
import com.example.proyectate.util.RegisterRequest;
import com.example.proyectate.util.RolSistema;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthenticationResponse register(RegisterRequest request) {
                var user = User.builder()
                                .name(request.name())
                                .email(request.email())
                                .password(passwordEncoder.encode(request.password()))
                                .rol(RolSistema.USER)
                                .build();
                userRepository.save(user);
                
                var jwtToken = jwtService.generateToken(new CustomUserDetail(user), user.getId());
                var refreshToken = jwtService.generateRefreshToken(new CustomUserDetail(user), user.getId());
                return new AuthenticationResponse(jwtToken, refreshToken);
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(request.email(), request.contrasena()));
                var user = userRepository.findByEmail(request.email()).orElseThrow();
                var jwtToken = jwtService.generateToken(new CustomUserDetail(user), user.getId());
                var refreshToken = jwtService.generateRefreshToken(new CustomUserDetail(user), user.getId());
                return new AuthenticationResponse(jwtToken, refreshToken);
        }

        public AuthenticationResponse refreshToken(RefreshTokenRequest request) {
                String userEmail = jwtService.extractUsername(request.refreshToken());
                if (userEmail != null) {
                        var user = userRepository.findByEmail(userEmail).orElseThrow();
                        if (jwtService.isTokenValid(request.refreshToken(), new CustomUserDetail(user))) {
                                var accessToken = jwtService.generateToken(new CustomUserDetail(user), user.getId());
                                // jwtService.printTokenDates(accessToken);
                                return new AuthenticationResponse(accessToken, request.refreshToken());
                        }
                }
                throw new RuntimeException("Token de refreso INVALIDO");
        }
}
