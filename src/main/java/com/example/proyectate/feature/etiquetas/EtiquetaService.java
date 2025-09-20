package com.example.proyectate.feature.etiquetas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EtiquetaService {
    
    private final EtiquetaRepository etiquetaRepository;
    private final EtiquetaMapper etiquetaMapper;

    public List<EtiquetaReaderDTO> getAllEtiquetas(){
        return etiquetaRepository.findAll().stream().map(etiquetaMapper::toDto).toList();
    }

    public EtiquetaReaderDTO getEtiquetaById(Long id){
        return etiquetaMapper.toDto(etiquetaRepository.findById(id).orElseThrow());
    }

    public EtiquetaReaderDTO addEtiqueta(EtiquetaWriterDTO etiqueta){
       return save(etiqueta);
    }

    public EtiquetaReaderDTO updEtiqueta(EtiquetaWriterDTO etiqueta) throws Exception{
        if (!etiquetaRepository.existsById(etiqueta.id())) {
            throw new Exception("ID no encontrado");
        }
        return save(etiqueta);
    }

    public String deleteEtiqueta(Long id) throws Exception{
        if (!etiquetaRepository.existsById(id)) {
            throw new Exception("ID no encontrado");
        }
        etiquetaRepository.deleteById(id);
        return String.format("Etiqueta eliminada con el ID: %d", id);
    }


    //metodo guardar
    private EtiquetaReaderDTO save(EtiquetaWriterDTO etiqueta){
        return etiquetaMapper.toDto(etiquetaRepository.save(etiquetaMapper.toEntity(etiqueta)));
    }

}
