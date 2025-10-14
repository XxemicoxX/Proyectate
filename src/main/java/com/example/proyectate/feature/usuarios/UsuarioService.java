package com.example.proyectate.feature.usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

      
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    //Buscar Email
    public Usuarios getUserByEmail (String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
  
    public List<UsuarioReaderDTO> getAllUsuarios(){
        return usuarioRepository.findAll().stream().map(usuarioMapper::toDto).toList();
    }

    public UsuarioReaderDTO getUsuarioById(Long id){
        return usuarioMapper.toDto(usuarioRepository.findById(id).orElseThrow());
    }

    public UsuarioReaderDTO addUsuario(UsuarioWriterDTO usuario){
       return save(usuario);
    }

    public UsuarioReaderDTO updUsuario(UsuarioWriterDTO usuario) throws Exception{
        if (!usuarioRepository.existsById(usuario.id())) {
            throw new Exception("ID no encontrado");
        }
        return save(usuario);
    }

    public String deleteUsuario(Long id) throws Exception{
        if (!usuarioRepository.existsById(id)) {
            throw new Exception("ID no encontrado");
        }
        usuarioRepository.deleteById(id);
        return String.format("Usuario eliminada con el ID: %d", id);
    }


    //metodo guardar
    private UsuarioReaderDTO save(UsuarioWriterDTO usuario){
        return usuarioMapper.toDto(usuarioRepository.save(usuarioMapper.toEntity(usuario)));
    }

}
