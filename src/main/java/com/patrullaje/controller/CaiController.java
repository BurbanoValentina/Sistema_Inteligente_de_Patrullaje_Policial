package com.patrullaje.controller;

/**
 * * * @author Valentina
 */

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.patrullaje.dto.CaiDTO;
import com.patrullaje.dto.ServicioPolicialDTO;
import com.patrullaje.service.CaiService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cai")
public class CaiController {

    private final CaiService service;

    public CaiController(CaiService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CaiDTO> crear(@Valid @RequestBody CaiDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaiDTO> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtener(id));
    }

    @GetMapping
    public ResponseEntity<List<CaiDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/comuna/{comunaId}")
    public ResponseEntity<List<CaiDTO>> listarPorComuna(@PathVariable Long comunaId) {
        return ResponseEntity.ok(service.listarPorComuna(comunaId));
    }

    @GetMapping("/{id}/servicios")
    public ResponseEntity<List<ServicioPolicialDTO>> consultarServicios(@PathVariable Long id) {
        return ResponseEntity.ok(service.consultarServicios(id));
    }

    @PatchMapping("/{id}/disponibilidad")
    public ResponseEntity<CaiDTO> cambiarDisponibilidad(@PathVariable Long id, @RequestParam boolean disponible) {
        return ResponseEntity.ok(service.cambiarDisponibilidad(id, disponible));
    }
}
