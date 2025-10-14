package com.example.proyectate.feature.proyectos;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.proyectate.feature.usuarios.Usuarios;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProyectoService {
    private final ProyectoRepository proyectoRepository;
    private final ProyectoMapper proyectoMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public List<ProyectoReaderDTO> getAllProyectos(){
        return proyectoRepository.findAll().stream().map(proyectoMapper::toDto).toList();
    }

    public ProyectoReaderDTO getProyectoById(Long id){
        return proyectoMapper.toDto(proyectoRepository.findById(id).orElseThrow());
    }

    @Transactional
    public ProyectoReaderDTO addProyecto(ProyectoWriterDTO proyecto){
       return save(proyecto);
    }

    @Transactional
    public ProyectoReaderDTO updProyecto(ProyectoWriterDTO proyecto) throws Exception{
        if (!proyectoRepository.existsById(proyecto.id())) {
            throw new Exception("ID no encontrado");
        }
        return save(proyecto);
    }

    @Transactional
    public String deleteProyecto(Long id) throws Exception{
        if (!proyectoRepository.existsById(id)) {
            throw new Exception("ID no encontrado");
        }
        proyectoRepository.deleteById(id);
        return String.format("proyecto eliminada con el ID: %d", id);
    }

    //metodo guardar
    private ProyectoReaderDTO save(ProyectoWriterDTO proyectoDTO){
        Proyecto proyecto = proyectoMapper.toEntity(proyectoDTO);
        Usuarios usuarioRef = entityManager.getReference(Usuarios.class, proyectoDTO.idUsuario());
        proyecto.setIdUsuario(usuarioRef);
        return proyectoMapper.toDto(proyectoRepository.save(proyecto));
    }
}
