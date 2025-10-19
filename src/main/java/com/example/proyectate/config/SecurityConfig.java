package com.example.proyectate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true) 
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    //Se setea cómo el método de encriptamiento
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Filtro de seguridad
    @Bean //Cadena de configuración
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) //Deshabilito el sistema de sesiones
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/**").permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    
    /*SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
        return http
            .authorizeHttpRequests(//Acá le indicamos a cuáles va a poder acceder y a cuáles no
                auth -> auth.requestMatchers("/").permitAll() //Rútas públicas
                        .requestMatchers("/comentarios/**").hasAuthority("ADMIN") //Autenticado y ADMIN
                        .requestMatchers("/tareas/**").hasAuthority("USER") //Autenticado y USER
                        .requestMatchers("/etiquetas/**").hasAnyAuthority("ADMIN","USER") //Autenticado, ser ADMIN o USER
                        .anyRequest().authenticated()                   //Solo autenticado
            ).httpBasic(Customizer.withDefaults()) //Aparecerá un login por default (Ventana flotante)
            .build();
    }*/
}
