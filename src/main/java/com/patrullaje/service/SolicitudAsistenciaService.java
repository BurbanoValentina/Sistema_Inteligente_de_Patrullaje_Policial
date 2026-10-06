package com.patrullaje.service;

/**
 * * * @author Valentina
 */
import com.patrullaje.dto.SeguimientoDTO;
import com.patrullaje.dto.SolicitudAsistenciaDTO;
import com.patrullaje.exception.BusinessException;
import com.patrullaje.exception.ResourceNotFoundException;
import com.patrullaje.mapper.SolicitudAsistenciaMapper;
import com.patrullaje.model.CAI;
import com.patrullaje.model.Ciudadano;
import com.patrullaje.model.EstadoSolicitud;
import com.patrullaje.model.PersonalPolicial;
import com.patrullaje.model.Seguimiento;
import com.patrullaje.model.SolicitudAsistencia;
import com.patrullaje.repository.CaiRepository;
import com.patrullaje.repository.CiudadanoRepository;
import com.patrullaje.repository.PersonalPolicialRepository;
import com.patrullaje.repository.SeguimientoRepository;
import com.patrullaje.repository.SolicitudAsistenciaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SolicitudAsistenciaService {

    private final SolicitudAsistenciaRepository repository;
    private final CiudadanoRepository ciudadanoRepository;
    private final CaiRepository caiRepository;
    private final PersonalPolicialRepository personalRepository;
    private final SeguimientoRepository seguimientoRepository;
    private final SolicitudAsistenciaMapper mapper;

    public SolicitudAsistenciaService(SolicitudAsistenciaRepository repository, CiudadanoRepository ciudadanoRepository, CaiRepository caiRepository, PersonalPolicialRepository personalRepository, SeguimientoRepository seguimientoRepository, SolicitudAsistenciaMapper mapper) {
        this.repository = repository;
        this.ciudadanoRepository = ciudadanoRepository;
        this.caiRepository = caiRepository;
        this.personalRepository = personalRepository;
        this.seguimientoRepository = seguimientoRepository;
        this.mapper = mapper;
    }

    @Transactional
    public SolicitudAsistenciaDTO crear(SolicitudAsistenciaDTO dto) {
        Ciudadano ciudadano = ciudadanoRepository.findById(dto.getCiudadanoId()).orElseThrow(() -> new ResourceNotFoundException("Ciudadano no encontrado: " + dto.getCiudadanoId()));
        if (dto.getCaiId() != null || dto.getPersonalId() != null) {
            throw new BusinessException("La asignación de CAI y personal se realiza después de crear la solicitud.");
        }
        SolicitudAsistencia solicitud = mapper.toEntity(dto);
        solicitud.setCiudadano(ciudadano);
        solicitud.setDescripcion(dto.getDescripcion());
        solicitud.setPrioridad(dto.getPrioridad());
        SolicitudAsistencia guardada = repository.save(solicitud);
        ciudadano.registrarSolicitud(guardada);
        registrarSeguimientoInterno(guardada, EstadoSolicitud.PENDIENTE, "Solicitud creada y registrada en el sistema.", null);
        return mapper.toDto(guardada);
    }

    @Transactional(readOnly = true)
    public SolicitudAsistenciaDTO obtener(UUID id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada: " + id)));
    }

    @Transactional(readOnly = true)
    public List<SolicitudAsistenciaDTO> listar() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<SolicitudAsistenciaDTO> listarPorCiudadano(UUID ciudadanoId) {
        if (!ciudadanoRepository.existsById(ciudadanoId)) {
            throw new ResourceNotFoundException("Ciudadano no encontrado: " + ciudadanoId);
        }
        return repository.findByCiudadanoId(ciudadanoId).stream().map(mapper::toDto).toList();
    }

    @Transactional
    public SolicitudAsistenciaDTO asignar(UUID solicitudId, Long caiId, Long personalId) {
        SolicitudAsistencia solicitud = obtenerEntidad(solicitudId);
        if (solicitud.getEstado() != EstadoSolicitud.PENDIENTE) {
            throw new BusinessException("Solo una solicitud PENDIENTE puede ser asignada.");
        }
        CAI cai = caiRepository.findById(caiId).orElseThrow(() -> new ResourceNotFoundException("CAI no encontrado: " + caiId));
        solicitud.asignarCai(cai);
        PersonalPolicial personal = null;
        if (personalId != null) {
            personal = personalRepository.findById(personalId).orElseThrow(() -> new ResourceNotFoundException("Personal policial no encontrado: " + personalId));
            personal.gestionarSolicitud(solicitud);
        }
        solicitud.cambiarEstado(EstadoSolicitud.ASIGNADA);
        registrarSeguimientoInterno(solicitud, EstadoSolicitud.ASIGNADA, "Solicitud asignada al CAI " + cai.getId() + ".", personal);
        return mapper.toDto(repository.save(solicitud));
    }

    @Transactional
    public SolicitudAsistenciaDTO cambiarEstado(UUID solicitudId, EstadoSolicitud nuevoEstado, Long personalId, String comentario) {
        SolicitudAsistencia solicitud = obtenerEntidad(solicitudId);
        PersonalPolicial personal = null;
        if (personalId != null) {
            personal = personalRepository.findById(personalId).orElseThrow(() -> new ResourceNotFoundException("Personal policial no encontrado: " + personalId));
            personal.gestionarSolicitud(solicitud);
        }
        if ((nuevoEstado == EstadoSolicitud.EN_ATENCION || nuevoEstado == EstadoSolicitud.ATENDIDA) && personal == null) {
            throw new BusinessException("Se requiere personal policial para gestionar este cambio de estado.");
        }
        solicitud.cambiarEstado(nuevoEstado);
        String texto = comentario == null || comentario.isBlank() ? "Estado actualizado a " + nuevoEstado + "." : comentario;
        registrarSeguimientoInterno(solicitud, nuevoEstado, texto, personal);
        return mapper.toDto(repository.save(solicitud));
    }

    @Transactional
    public SolicitudAsistenciaDTO cancelar(UUID solicitudId) {
        SolicitudAsistencia solicitud = obtenerEntidad(solicitudId);
        if (!solicitud.puedeCancelarse()) {
            throw new BusinessException("La solicitud no puede cancelarse en su estado actual.");
        }
        solicitud.cambiarEstado(EstadoSolicitud.CANCELADA);
        registrarSeguimientoInterno(solicitud, EstadoSolicitud.CANCELADA, "Solicitud cancelada.", null);
        return mapper.toDto(repository.save(solicitud));
    }

    @Transactional
    public SeguimientoDTO registrarSeguimiento(UUID solicitudId, SeguimientoDTO dto) {
        SolicitudAsistencia solicitud = obtenerEntidad(solicitudId);
        PersonalPolicial personal = null;
        if (dto.getPersonalId() != null) {
            personal = personalRepository.findById(dto.getPersonalId()).orElseThrow(() -> new ResourceNotFoundException("Personal policial no encontrado: " + dto.getPersonalId()));
            personal.gestionarSolicitud(solicitud);
        }
        if (dto.getEstado() != null && dto.getEstado() != solicitud.getEstado()) {
            solicitud.cambiarEstado(dto.getEstado());
        }
        Seguimiento seguimiento = new Seguimiento();
        seguimiento.registrarActualizacion(dto.getComentario(), solicitud.getEstado());
        seguimiento.setSolicitud(solicitud);
        seguimiento.setPersonal(personal);
        Seguimiento guardado = seguimientoRepository.save(seguimiento);
        solicitud.agregarSeguimiento(guardado);
        repository.save(solicitud);
        return toDto(guardado);
    }

    @Transactional(readOnly = true)
    public List<SeguimientoDTO> consultarSeguimiento(UUID solicitudId) {
        obtenerEntidad(solicitudId);
        return seguimientoRepository.findBySolicitudIdOrderByFechaAsc(solicitudId).stream().map(this::toDto).toList();
    }

    private SolicitudAsistencia obtenerEntidad(UUID id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Solicitud no encontrada: " + id));
    }

    private void registrarSeguimientoInterno(SolicitudAsistencia solicitud, EstadoSolicitud estado, String comentario, PersonalPolicial personal) {
        Seguimiento seguimiento = new Seguimiento(comentario, estado);
        seguimiento.setSolicitud(solicitud);
        seguimiento.setPersonal(personal);
        Seguimiento guardado = seguimientoRepository.save(seguimiento);
        solicitud.agregarSeguimiento(guardado);
    }

    private SeguimientoDTO toDto(Seguimiento seguimiento) {
        SeguimientoDTO dto = new SeguimientoDTO();
        dto.setComentario(seguimiento.getComentario());
        dto.setEstado(seguimiento.getEstado());
        dto.setSolicitudId(seguimiento.getSolicitud().getId());
        dto.setPersonalId(seguimiento.getPersonal() == null ? null : seguimiento.getPersonal().getId());
        dto.setId(seguimiento.getId());
        dto.setFecha(seguimiento.getFecha() == null ? LocalDateTime.now() : seguimiento.getFecha());
        return dto;
    }
}
