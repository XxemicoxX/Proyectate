package com.example.proyectate.feature.usersproyects;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserProyectRepository extends JpaRepository<UserProyect, Long> {

    List<UserProyect> findByUser_Id(Long userId);

    List<UserProyect> findByProyect_Id(Long proyectoId);

    boolean existsByUser_IdAndProyect_Id(Long userId, Long proyectId);
}