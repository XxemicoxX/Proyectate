package com.example.proyectate.feature.tareas;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.proyectate.feature.etiquetas.Etiqueta;
import com.example.proyectate.feature.proyectos.Proyecto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TareaService {
    private final TareaRepository tareaRepository;
    private final TareaMapper tareaMapper;

    @PersistenceContext
    private EntityManager entityManager;

    public List<TareaReaderDTO> getAllTareas(){
        return tareaRepository.findAll().stream().map(tareaMapper::toDto).toList();
    }

    public TareaReaderDTO getTareaById(Long id){
        return tareaMapper.toDto(tareaRepository.findById(id).orElseThrow());
    }

    public List<TareaReaderDTO> getTareaByTitulo(String titulo){
        return tareaRepository.findByTitulo(titulo)
        .stream()
        .map(tareaMapper::toDto).toList();
    }

   public List<TareaReaderDTO> getTareaByPrioridad(String prioridad){
        return tareaRepository.findByPrioridad(prioridad)
        .stream()
        .map(tareaMapper::toDto).toList();
    }

    @Transactional
    public TareaReaderDTO addTarea(TareaWriterDTO tarea){
       return save(tarea);
    }

    @Transactional
    public TareaReaderDTO updTarea(TareaWriterDTO tarea) throws Exception{
        if (!tareaRepository.existsById(tarea.id())) {
            throw new Exception("ID no encontrado");
        }
        return save(tarea);
    }

    @Transactional
    public String deleteTarea(Long id) throws Exception{
        if (!tareaRepository.existsById(id)) {
            throw new Exception("ID no encontrado");
        }
        tareaRepository.deleteById(id);
        return String.format("Tarea eliminada con el ID: %d", id);
    }

    //metodo guardar
    private TareaReaderDTO save(TareaWriterDTO dto){
        Tarea tarea = tareaMapper.toEntity(dto);
        Proyecto proyectoRef = entityManager.getReference(Proyecto.class, dto.idProyecto());
        tarea.setProyecto(proyectoRef);
        Etiqueta etiquetaRef = entityManager.getReference(Etiqueta.class, dto.idEtiqueta());
        tarea.setEtiqueta(etiquetaRef);
        return tareaMapper.toDto(tareaRepository.save(tarea));
    }
    
}
