package com.patrullaje.service;

/**
 * * * @author Valentina
 */
import com.patrullaje.dto.ComunaDTO;
import com.patrullaje.exception.ResourceNotFoundException;
import com.patrullaje.model.Comuna;
import com.patrullaje.repository.ComunaRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComunaService {

    private final ComunaRepository repository;

    public ComunaService(ComunaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ComunaDTO crear(ComunaDTO dto) {
        Comuna comuna = new Comuna(dto.getNombre(), dto.getDescripcion());
        return toDto(repository.save(comuna));
    }

    @Transactional(readOnly = true)
    public ComunaDTO obtener(Long id) {
        return toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comuna no encontrada: " + id)));
    }

    @Transactional(readOnly = true)
    public List<ComunaDTO> listar() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    private ComunaDTO toDto(Comuna comuna) {
        ComunaDTO dto = new ComunaDTO();
        dto.setNombre(comuna.getNombre());
        dto.setDescripcion(comuna.getDescripcion());
        dto.setId(comuna.getId());
        return dto;
    }
}
