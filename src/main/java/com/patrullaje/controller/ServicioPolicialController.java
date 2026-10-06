package com.patrullaje.controller;

/**
 * * * @author Valentina
 */
import com.patrullaje.dto.ServicioPolicialDTO;
import com.patrullaje.service.ServicioPolicialService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/servicios")
public class ServicioPolicialController {

    private final ServicioPolicialService service;

    public ServicioPolicialController(ServicioPolicialService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ServicioPolicialDTO> crear(@Valid @RequestBody ServicioPolicialDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioPolicialDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @GetMapping
    public ResponseEntity<List<ServicioPolicialDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioPolicialDTO> actualizar(@PathVariable Long id, @Valid @RequestBody ServicioPolicialDTO dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @PutMapping("/{id}/activar")
    public ResponseEntity<ServicioPolicialDTO> activar(@PathVariable Long id) {
        return ResponseEntity.ok(service.activar(id));
    }

    @PutMapping("/{id}/desactivar")
    public ResponseEntity<ServicioPolicialDTO> desactivar(@PathVariable Long id) {
        return ResponseEntity.ok(service.desactivar(id));
    }
}
