package com.example.proyectate.feature.tokens;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Integer>{

        @Query(value = "SELECT t FROM Token t JOIN t.idUser u WHERE u.id = :id  AND (t.revocado = false OR t.expirado = false)")
        List<Token> tokensValidos(Integer id);

    Optional<Token> findByToken(String token);
}
