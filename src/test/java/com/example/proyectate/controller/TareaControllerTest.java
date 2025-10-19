package com.example.proyectate.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class TareaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getTareasReturnsOk() throws Exception {
        mockMvc.perform(get("/api/tareas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void insertTareaReturnsOk() throws Exception {
        String json = """
                {
                    "titulo": "Nuevo modulo de ropa",
                    "descripcion": "Agregar seccion para pantalones",
                    "prioridad": "Alta",
                    "estado": "Pendiente",
                    "id_proyecto": 1,
                    "id_etiqueta": 1
                }
                                """;
        mockMvc.perform(post("/api/tareas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void updateTareaReturnsOk() throws Exception {
        String json = """
                    {
                        "id": 4,
                        "titulo": "Nuevo modulo de ropa",
                        "descripcion": "Agregar seccion para pantalones",
                        "prioridad": "Alta",
                        "estado": "En proceso",
                        "id_proyecto": 1,
                        "id_etiqueta": 1
                    }
                """;
        mockMvc.perform(put("/api/tareas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deleteTareaReturnsOk() throws Exception {
        mockMvc.perform(delete("/api/tareas/4"))
                .andExpect(status().isOk());
    }
}