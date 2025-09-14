package com.example.proyectate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class EtiquetaControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    public void getEtiquetasTest() throws Exception {
        mockMvc.perform(get("/api/etiquetas"))
               .andExpect(status().isOk()) // tiene q devolver 200 ok
               .andExpect(content().contentType(MediaType.APPLICATION_JSON)); // tiene q devolver json (ten en cuenta q sebe tener datos)
              
    }

}
