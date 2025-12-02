package com.example.proyectate.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.proyectate.config.JwtConfig;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public String generateToken(UserDetails userDetails, Long idUser) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("idUsuario", idUser); // Se agregar el ID al token
        claims.put("roles", userDetails.getAuthorities());
        return generateToken(claims, userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtConfig.getTokenExpirationInMillis());
    }

    public String generateRefreshToken(UserDetails userDetails, Long idUser) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("idUsuario", idUser);
        return buildToken(new HashMap<>(), userDetails, jwtConfig.getRefreshTokenExpirationInMillis());
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expirationMillis // milisegundos
    ) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationMillis))
                .signWith(secretKey)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public void printTokenDates(String token) {
        Claims claims = extractAllClaims(token);
        System.out.println("Token creado: " + claims.getIssuedAt());
        System.out.println("Token expira: " + claims.getExpiration());
        System.out.println("Fecha actual: " + new Date());
        System.out.println("Milisegundos restantes: " +
                (claims.getExpiration().getTime() - System.currentTimeMillis()));
    }

    // Agregar este método a tu JwtService
    public Long extractUserId(String token) {
        Claims claims = extractAllClaims(token);
        // Extraer el claim "idUsuario" que agregaste al generar el token
        Object userId = claims.get("idUsuario");

        if (userId instanceof Integer) {
            return ((Integer) userId).longValue();
        } else if (userId instanceof Long) {
            return (Long) userId;
        }

        throw new RuntimeException("No se pudo extraer el ID del usuario del token");
    }
    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        List<Map<String, String>> roles = (List<Map<String, String>>) claims.get("roles");
        return roles.stream().map(r -> r.get("authority")).toList();
    }

}
