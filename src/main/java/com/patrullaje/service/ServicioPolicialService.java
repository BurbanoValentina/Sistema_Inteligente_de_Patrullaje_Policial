package com.patrullaje.service;

/**
 * * * @author Valentina
 */
import com.patrullaje.dto.ServicioPolicialDTO;
import com.patrullaje.exception.ResourceNotFoundException;
import com.patrullaje.model.ServicioPolicial;
import com.patrullaje.repository.ServicioPolicialRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicioPolicialService {

    private final ServicioPolicialRepository repository;

    public ServicioPolicialService(ServicioPolicialRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ServicioPolicialDTO crear(ServicioPolicialDTO dto) {
        ServicioPolicial servicio = new ServicioPolicial(dto.getNombre(), dto.getDescripcion(), dto.isActivo());
        return toDto(repository.save(servicio));
    }

    @Transactional(readOnly = true)
    public ServicioPolicialDTO obtener(Long id) {
        ServicioPolicial servicio = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado: " + id));
        return toDto(servicio);
    }

    @Transactional(readOnly = true)
    public List<ServicioPolicialDTO> listar() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional
    public ServicioPolicialDTO actualizar(Long id, ServicioPolicialDTO dto) {
        ServicioPolicial servicio = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado: " + id));
        servicio.setNombre(dto.getNombre());
        servicio.setDescripcion(dto.getDescripcion());
        servicio.setActivo(dto.isActivo());
        return toDto(repository.save(servicio));
    }

    @Transactional
    public ServicioPolicialDTO activar(Long id) {
        ServicioPolicial servicio = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado: " + id));
        servicio.activar();
        return toDto(repository.save(servicio));
    }

    @Transactional
    public ServicioPolicialDTO desactivar(Long id) {
        ServicioPolicial servicio = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado: " + id));
        servicio.desactivar();
        return toDto(repository.save(servicio));
    }

    private ServicioPolicialDTO toDto(ServicioPolicial servicio) {
        ServicioPolicialDTO dto = new ServicioPolicialDTO();
        dto.setNombre(servicio.getNombre());
        dto.setDescripcion(servicio.getDescripcion());
        dto.setActivo(servicio.isActivo());
        dto.setId(servicio.getId());
        return dto;
    }
}
