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
public class EtiquetaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getEtiquetasReturnsOk() throws Exception {
        mockMvc.perform(get("/api/etiquetas"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void insertEtiquetaReturnsOk() throws Exception {
        String json = """
                    {
                        "nombre": "programacion"
                    }
                """;
        mockMvc.perform(post("/api/etiquetas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

     @Test
    public void updateEtiquetaReturnsOk() throws Exception {
        String json = """
                    {
                        "id": 1,
                        "nombre": "Programacion"
                    }
                """;
        mockMvc.perform(put("/api/etiquetas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void deleteEtiquetaReturnsOk() throws Exception{
        mockMvc.perform(delete("/api/etiquetas/1"))
                .andExpect(status().isOk());
    }
}