package com.example.proyectate.feature.tokens;

import java.time.LocalDateTime;

import com.example.proyectate.feature.users.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String token;
    @Column(name = "refresh_token",nullable = false)
    private String refreshToken;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private TipoToken tipo = TipoToken.BEARER;
    @Column(nullable = false)
    private Boolean revocado;
    @Column(nullable = false)
    private Boolean expirado;
    @Column(name = "fecha_creacion",nullable = false)
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaExpiracion; 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private User user;

    public enum TipoToken {
        BEARER
    }
}
