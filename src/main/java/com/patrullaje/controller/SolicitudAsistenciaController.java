package com.patrullaje.controller;

/**
 * * * @author Valentina
 */
import com.patrullaje.dto.SeguimientoDTO;
import com.patrullaje.dto.SolicitudAsistenciaDTO;
import com.patrullaje.model.EstadoSolicitud;
import com.patrullaje.service.SolicitudAsistenciaService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudAsistenciaController {

    private final SolicitudAsistenciaService service;

    public SolicitudAsistenciaController(SolicitudAsistenciaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<SolicitudAsistenciaDTO> crear(@Valid @RequestBody SolicitudAsistenciaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudAsistenciaDTO> obtener(@PathVariable UUID id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @GetMapping
    public ResponseEntity<List<SolicitudAsistenciaDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/ciudadano/{ciudadanoId}")
    public ResponseEntity<List<SolicitudAsistenciaDTO>> listarPorCiudadano(@PathVariable UUID ciudadanoId) {
        return ResponseEntity.ok(service.listarPorCiudadano(ciudadanoId));
    }

    @PutMapping("/{id}/asignar")
    public ResponseEntity<SolicitudAsistenciaDTO> asignar(@PathVariable UUID id, @RequestParam Long caiId, @RequestParam(required = false) Long personalId) {
        return ResponseEntity.ok(service.asignar(id, caiId, personalId));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<SolicitudAsistenciaDTO> cambiarEstado(@PathVariable UUID id, @RequestParam EstadoSolicitud estado, @RequestParam(required = false) Long personalId, @RequestParam(required = false) String comentario) {
        return ResponseEntity.ok(service.cambiarEstado(id, estado, personalId, comentario));
    }

    @PostMapping("/{id}/seguimiento")
    public ResponseEntity<SeguimientoDTO> registrarSeguimiento(@PathVariable UUID id, @Valid @RequestBody SeguimientoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrarSeguimiento(id, dto));
    }

    @GetMapping("/{id}/seguimiento")
    public ResponseEntity<List<SeguimientoDTO>> consultarSeguimiento(@PathVariable UUID id) {
        return ResponseEntity.ok(service.consultarSeguimiento(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SolicitudAsistenciaDTO> cancelar(@PathVariable UUID id) {
        return ResponseEntity.ok(service.cancelar(id));
    }
}

