package com.example.proyectate.repository;

import com.example.proyectate.feature.etiquetas.Etiqueta;
import com.example.proyectate.feature.etiquetas.EtiquetaRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EtiquetaRepositoryTest {

    @Autowired
    private EtiquetaRepository etiquetaRepository;

    @Test
    public void testCrudEtiquetas() throws Exception{

        Etiqueta etiqueta = new Etiqueta();
        etiqueta.setNombre("programacion");
        Etiqueta guardar = etiquetaRepository.save(etiqueta);
        assertThat(guardar.getId()).isNotNull();
        assertThat(guardar.getNombre()).isEqualTo("programacion");

        Optional<Etiqueta> encontrar = etiquetaRepository.findById(guardar.getId());
        assertThat(encontrar).isPresent();
        assertThat(encontrar.get().getNombre()).isEqualTo("programacion");

        Etiqueta actualizar = encontrar.get();
        actualizar.setNombre("documentacion");
        etiquetaRepository.save(actualizar);
        Etiqueta actualizado = etiquetaRepository.findById(guardar.getId()).get();
        assertThat(actualizado.getNombre()).isEqualTo("documentacion");

        etiquetaRepository.deleteById(actualizado.getId());
        Optional<Etiqueta> eliminado = etiquetaRepository.findById(actualizado.getId());
        assertThat(eliminado).isNotPresent();

    }
}
