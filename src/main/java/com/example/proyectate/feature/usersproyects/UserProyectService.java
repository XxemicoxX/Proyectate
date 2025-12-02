package com.example.proyectate.feature.usersproyects;

import java.util.List;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserProyectService {

    private final UserProyectRepository userProyectRepository;
    private final UserProyectMapper userProyectMapper;

    public List<UserProyectReaderDTO> getAllUserProyects() {
        return userProyectRepository.findAll()
                .stream()
                .map(userProyectMapper::toDto)
                .toList();
    }

    public UserProyectReaderDTO getUserProyectById(Long id) {
        return userProyectMapper.toDto(
                userProyectRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException("Relación usuario-proyecto no encontrada con ID: " + id)));
    }

    public List<UserProyectReaderDTO> getUserProyectsByUserId(Long userId) {
        return userProyectRepository.findByUser_Id(userId)
                .stream()
                .map(userProyectMapper::toDto)
                .toList();
    }

    public List<UserProyectReaderDTO> getUserProyectsByProyectoId(Long proyectoId) {
        return userProyectRepository.findByProyect_Id(proyectoId)
                .stream()
                .map(userProyectMapper::toDto)
                .toList();
    }

    public UserProyectReaderDTO addUserProyect(UserProyectWriterDTO userProyect) {
        // Validar que no exista ya la relación
        if (existsUserProyect(userProyect.user(), userProyect.proyect())) {
            throw new RuntimeException("Ya existe una relación entre este usuario y proyecto");
        }
        return save(userProyect);
    }

    public UserProyectReaderDTO updateUserProyect(UserProyectWriterDTO userProyect) {
        if (userProyect.id() == null || !userProyectRepository.existsById(userProyect.id())) {
            throw new RuntimeException("ID no encontrado");
        }
        return save(userProyect);
    }

    public String deleteUserProyect(Long id) {
        if (!userProyectRepository.existsById(id)) {
            throw new RuntimeException("ID no encontrado");
        }
        userProyectRepository.deleteById(id);
        return String.format("Relación usuario-proyecto eliminada con el ID: %d", id);
    }

    public boolean existsUserProyect(Long userId, Long proyectoId) {
        return userProyectRepository.existsByUser_IdAndProyect_Id(userId, proyectoId);
    }

    private UserProyectReaderDTO save(UserProyectWriterDTO userProyect) {
        return userProyectMapper.toDto(
                userProyectRepository.save(userProyectMapper.toEntity(userProyect)));
    }

    // NUEVO MÉTODO
    public List<UserProyectDetailDTO> getUserProyectDetailsById(Long proyectoId) {
        List<Object[]> results = userProyectRepository.findUserProyectDetailsById(proyectoId);
        
        return results.stream()
                .map(row -> new UserProyectDetailDTO(
                        ((Number) row[0]).longValue(),     // id
                        ((Number) row[1]).longValue(),     // idUsuario
                        ((Number) row[2]).longValue(),     // idProyecto
                        (String) row[3],                   // rol
                        (String) row[4],                   // nombreUsuario
                        (String) row[5]                    // emailUsuario
                ))
                .toList();
    }
}
