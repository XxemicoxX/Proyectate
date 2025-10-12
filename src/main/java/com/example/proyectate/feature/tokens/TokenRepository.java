package com.example.proyectate.feature.tokens;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Integer>{

    @Query(value = "select t from Token t inner join Usuarios u on t.usuario.id = u.id where u.id = :id and (t.revocado = false or t.expirado = false)")
    List<Token> tokensValidos(Integer id);

    Optional<Token> findByToken(String token);
}
