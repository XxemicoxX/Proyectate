package com.example.proyectate.feature.etiquetas;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("api/etiquetas")
@RequiredArgsConstructor
public class EtiquetaApiController {
    private final EtiquetaService etiquetaService;

    @GetMapping()
    public List<EtiquetaReaderDTO> getAll() {
        return etiquetaService.getAllEtiquetas();
    }

    @GetMapping("/{id}")
    public EtiquetaReaderDTO getOne(@PathVariable Long id) {
       try {
         return etiquetaService.getEtiquetaById(id);
       } catch (Exception e) {
            return new EtiquetaReaderDTO(null, null);
       }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public EtiquetaReaderDTO insertEtiqueta(@Valid @RequestBody EtiquetaWriterDTO etiqueta){
       try {
            return etiquetaService.addEtiqueta(etiqueta);
       } catch (Exception e) {
            return null;
       }
    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public EtiquetaReaderDTO updateEtiqueta(@Valid @RequestBody EtiquetaWriterDTO etiqueta){
       try {
            return etiquetaService.updEtiqueta(etiqueta);
       } catch (Exception e) {
            return null;
       }
    }   

    @DeleteMapping("/{id}")
    public String deleteEtiqueta(@PathVariable Long id) {
        try {
            return etiquetaService.deleteEtiqueta(id);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}
