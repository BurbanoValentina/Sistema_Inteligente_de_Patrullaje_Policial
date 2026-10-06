package com.patrullaje.controller;

/**
 * * * @author Valentina
 */

import com.patrullaje.dto.ComunaDTO;
import com.patrullaje.service.ComunaService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comunas")
public class ComunaController {

    private final ComunaService service;

    public ComunaController(ComunaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ComunaDTO> crear(@Valid @RequestBody ComunaDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComunaDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @GetMapping
    public ResponseEntity<List<ComunaDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }
}
