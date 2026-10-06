package com.patrullaje.controller;

/**
 * * * @author Valentina
 */

import com.patrullaje.dto.CiudadanoDTO;
import com.patrullaje.service.CiudadanoService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ciudadanos")
public class CiudadanoController {

    private final CiudadanoService service;

    public CiudadanoController(CiudadanoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CiudadanoDTO> crear(@Valid @RequestBody CiudadanoDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CiudadanoDTO> obtener(@PathVariable UUID id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @GetMapping
    public ResponseEntity<List<CiudadanoDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }
}
