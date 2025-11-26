package com.example.proyectate.feature.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository usuarioRepository;
    private final UserMapper usuarioMapper;

    //Buscar Email
    public User getUserByEmail (String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
  
    public List<UserReaderDTO> getAllUsers(){
        return usuarioRepository.findAll().stream().map(usuarioMapper::toDto).toList();
    }

    public UserReaderDTO getUserById(Long id){
        return usuarioMapper.toDto(usuarioRepository.findById(id).orElseThrow());
    }

    public UserReaderDTO addUser(UserWriterDTO usuario){
       return save(usuario);
    }

    public UserReaderDTO updUser(UserWriterDTO usuario) throws Exception{
        if (!usuarioRepository.existsById(usuario.id())) {
            throw new Exception("ID no encontrado");
        }
        return save(usuario);
    }

    public String deleteUser(Long id) throws Exception{
        if (!usuarioRepository.existsById(id)) {
            throw new Exception("ID no encontrado");
        }
        usuarioRepository.deleteById(id);
        return String.format("Usuario eliminada con el ID: %d", id);
    }


    //metodo guardar
    private UserReaderDTO save(UserWriterDTO usuario){
        return usuarioMapper.toDto(usuarioRepository.save(usuarioMapper.toEntity(usuario)));
    }

}
