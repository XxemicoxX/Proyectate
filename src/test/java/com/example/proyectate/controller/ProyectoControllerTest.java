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
public class ProyectoControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void insertProyectos() throws Exception{
        String json = """
                
                {
                    "id_proyecto": 1,
                    "nombre": "Plataforma Red Social",
                    "descripcion": "Pagina web donde los usuarios puedan chatear y postear",
                    "id_usuario": 2
                }

                """;
        mockMvc.perform(post("/api/proyectos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getAllProyectos() throws Exception{
        mockMvc.perform(get("/api/proyectos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void updateProyectoReturnOk() throws Exception {
        String json = """
                {   
                    "id": 1,
                    "nombre": "Plataforma E-Commerce",
                    "descripcion": "Pagina web de tienda de ropa",
                    "id_usuario": 2
                }
                """;
        mockMvc.perform(put("/api/proyectos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deleteProyectoReturnok() throws Exception {
        mockMvc.perform(delete("/api/proyectos/2"))
            .andExpect(status().isOk());
    }

}
