package com.example.proyectate.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class ComentarioControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getComentariosReturnsOk() throws Exception {
        mockMvc.perform(get("/api/comentarios"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void insertComentarioReturnsOk() throws Exception {
        String json = """
                {
                    "contenido": "mejorar la descripcion",
                    "fecha_creacion": "2025-12-31",
                    "usuario_id": 2,
                    "tarea_id": 4
                }
                                """;
        mockMvc.perform(post("/api/comentarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void updateComentarioReturnsOk() throws Exception {
        String json = """
                    {   
                        "id": 2,
                        "contenido": "Mejorar la descripcion",
                        "fecha_creacion": "2025-12-31",
                        "usuario_id": 2,
                        "tarea_id": 4
                    }
                """;
        mockMvc.perform(put("/api/comentarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deleteComentarioReturnsOk() throws Exception {
        mockMvc.perform(delete("/api/comentarios/2"))
                .andExpect(status().isOk());
    }

}
