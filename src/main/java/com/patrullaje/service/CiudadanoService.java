package com.patrullaje.service;

/**
 * * * @author Valentina
 */
import com.patrullaje.dto.CiudadanoDTO;
import com.patrullaje.exception.BusinessException;
import com.patrullaje.exception.ResourceNotFoundException;
import com.patrullaje.mapper.CiudadanoMapper;
import com.patrullaje.model.Ciudadano;
import com.patrullaje.repository.CiudadanoRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CiudadanoService {

    private final CiudadanoRepository repository;
    private final CiudadanoMapper mapper;

    public CiudadanoService(CiudadanoRepository repository, CiudadanoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public CiudadanoDTO crear(CiudadanoDTO dto) {
        repository.findByDocumento(dto.getDocumento()).ifPresent(c -> {
            throw new BusinessException("Ya existe un ciudadano con el documento indicado.");
        });
        Ciudadano entity = mapper.toEntity(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional(readOnly = true)
    public CiudadanoDTO obtener(UUID id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ciudadano no encontrado: " + id)));
    }

    @Transactional(readOnly = true)
    public List<CiudadanoDTO> listar() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }
}
