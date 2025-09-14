package com.example.proyectate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proyectate.feature.etiquetas.Etiqueta;
import com.example.proyectate.feature.etiquetas.EtiquetaRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EtiquetaRepositoryTest {

    @Autowired
    private EtiquetaRepository etiquetaRepository;

    @Test
    public void testCrudEtiquetas() {
        //insertara "programacion", lo guardara y luego verficiara que nosea nulo y comparara isEqualTo("programacion") 
        Etiqueta etiqueta = new Etiqueta();
        etiqueta.setNombre("programacion");
        Etiqueta guardar = etiquetaRepository.save(etiqueta);
        assertThat(guardar.getId()).isNotNull();
        assertThat(guardar.getNombre()).isEqualTo("programacion");

        //buscar la etiqueta por id, verificar que este presente y que su nombre sea "programacion"
        Optional<Etiqueta> encontrar = etiquetaRepository.findById(guardar.getId());
        assertThat(encontrar).isPresent();
        assertThat(encontrar.get().getNombre()).isEqualTo("programacion");

        //actualizar la etiqueta encontrada, cambiar su nombre a "documentacion", guardarla y verificar que el cambio se haya realizado
        Etiqueta actualizar = encontrar.get();
        actualizar.setNombre("documentacion");
        etiquetaRepository.save(actualizar);
        Etiqueta actualizado = etiquetaRepository.findById(guardar.getId()).get();
        assertThat(actualizado.getNombre()).isEqualTo("documentacion");

        //eliminar la etiqueta por id y verificar que ya no este presente uwu
        etiquetaRepository.deleteById(actualizado.getId());
        Optional<Etiqueta> eliminado = etiquetaRepository.findById(actualizado.getId());
        assertThat(eliminado).isNotPresent();

    }
}
