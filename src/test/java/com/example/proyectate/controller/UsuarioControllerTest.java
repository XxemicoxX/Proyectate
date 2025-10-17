package com.example.proyectate.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class UsuarioControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllUsuariosReturnsOk() throws Exception{
        mockMvc.perform(get("/api/usuarios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    
     @Test
    public void insertUsuarioReturnOk() throws Exception{
        String json = """
                {
                    "nombre": "Jimmy McGuill",
                    "email": "saulgoodman@gmail.com",
                    "contrasena": "itstimetoshow",
                    "rol": "USUARIO"
                }
                """;
        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void updateUsuarioReturnOk() throws Exception {
        String json = """
                {   
                    "id": 2,
                    "nombre": "Walter White",
                    "email": "whitewalter@gmail.com",
                    "contrasena": "whereisthemoneyskyler",
                    "rol": "USUARIO"
                }
                """;
        mockMvc.perform(put("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deleteUsuarioReturnok() throws Exception {
        mockMvc.perform(delete("/api/usuarios/3"))
            .andExpect(status().isOk());
    }
}
