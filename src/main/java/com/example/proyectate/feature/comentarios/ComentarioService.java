package com.example.proyectate.feature.comentarios;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComentarioService {
    
    private final ComentarioRepository comentarioRepository;
    private final ComentarioMapper comentarioMapper;

    public List<ComentarioReaderDTO> getAllComentarios(){
        return comentarioRepository
            .findAll()
            .stream()
            .map(comentarioMapper::toDto)
            .toList();
    }

    public ComentarioReaderDTO getComentarioById(Long id){
        return comentarioMapper.toDto(comentarioRepository.findById(id).orElseThrow());
    }

    public ComentarioReaderDTO addComentario(ComentarioWriterDTO comentario){
       return save(comentario);
    }

    public ComentarioReaderDTO updComentario(ComentarioWriterDTO comentario) throws Exception{
        if (!comentarioRepository.existsById(comentario.id())) {
            throw new Exception("ID no encontrado");
        }
        return save(comentario);
    }

    public String deleteComentario(Long id) throws Exception{
        if (!comentarioRepository.existsById(id)) {
            throw new Exception("ID no encontrado");
        }
        comentarioRepository.deleteById(id);
        return String.format("Comentario eliminada con el ID: %d", id);
    }


    //metodo guardar
    private ComentarioReaderDTO save(ComentarioWriterDTO etiqueta){
        return comentarioMapper.toDto(comentarioRepository.save(comentarioMapper.toEntity(etiqueta)));
    }

}
