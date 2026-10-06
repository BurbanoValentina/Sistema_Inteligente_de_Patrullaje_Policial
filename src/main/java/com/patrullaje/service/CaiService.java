package com.patrullaje.service;

/**
 * * * @author Valentina
 */
import com.patrullaje.dto.CaiDTO;
import com.patrullaje.dto.ServicioPolicialDTO;
import com.patrullaje.exception.BusinessException;
import com.patrullaje.exception.ResourceNotFoundException;
import com.patrullaje.mapper.CaiMapper;
import com.patrullaje.model.CAI;
import com.patrullaje.model.Comuna;
import com.patrullaje.model.ServicioPolicial;
import com.patrullaje.repository.CaiRepository;
import com.patrullaje.repository.ComunaRepository;
import com.patrullaje.repository.ServicioPolicialRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CaiService {

    private final CaiRepository repository;
    private final ComunaRepository comunaRepository;
    private final ServicioPolicialRepository servicioRepository;
    private final CaiMapper mapper;

    public CaiService(CaiRepository repository, ComunaRepository comunaRepository, ServicioPolicialRepository servicioRepository, CaiMapper mapper) {
        this.repository = repository;
        this.comunaRepository = comunaRepository;
        this.servicioRepository = servicioRepository;
        this.mapper = mapper;
    }

    @Transactional
    public CaiDTO crear(CaiDTO dto) {
        Comuna comuna = comunaRepository.findById(dto.getComunaId()).orElseThrow(() -> new ResourceNotFoundException("Comuna no encontrada: " + dto.getComunaId()));
        CAI cai = mapper.toEntity(dto);
        cai.setComuna(comuna);
        asignarServicios(cai, dto.getServicioIds());
        return mapper.toDto(repository.save(cai));
    }

    @Transactional(readOnly = true)
    public CaiDTO obtener(Long id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CAI no encontrado: " + id)));
    }

    @Transactional(readOnly = true)
    public List<CaiDTO> listar() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<CaiDTO> listarPorComuna(Long comunaId) {
        if (!comunaRepository.existsById(comunaId)) {
            throw new ResourceNotFoundException("Comuna no encontrada: " + comunaId);
        }
        return repository.findByComunaId(comunaId).stream().map(mapper::toDto).toList();
    }

    @Transactional
    public CaiDTO cambiarDisponibilidad(Long id, boolean disponible) {
        CAI cai = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("CAI no encontrado: " + id));
        cai.cambiarDisponibilidad(disponible);
        return mapper.toDto(repository.save(cai));
    }

    @Transactional(readOnly = true)
    public List<ServicioPolicialDTO> consultarServicios(Long caiId) {
        CAI cai = repository.findById(caiId).orElseThrow(() -> new ResourceNotFoundException("CAI no encontrado: " + caiId));
        return cai.getServicios().stream().map(servicio -> {
            ServicioPolicialDTO dto = new ServicioPolicialDTO();
            dto.setNombre(servicio.getNombre());
            dto.setDescripcion(servicio.getDescripcion());
            dto.setActivo(servicio.isActivo());
            dto.setId(servicio.getId());
            return dto;
        }).toList();
    }

    private void asignarServicios(CAI cai, Set<Long> servicioIds) {
        if (servicioIds == null || servicioIds.isEmpty()) {
            return;
        }
        Set<ServicioPolicial> servicios = servicioIds.stream().map(id -> servicioRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado: " + id))).collect(Collectors.toSet());
        servicios.forEach(servicio -> {
            if (!servicio.isActivo()) {
                throw new BusinessException("No se puede asociar un servicio inactivo al CAI: " + servicio.getId());
            }
            cai.agregarServicio(servicio);
        });
    }
}
