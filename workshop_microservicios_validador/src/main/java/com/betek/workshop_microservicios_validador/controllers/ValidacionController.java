package com.betek.workshop_microservicios_validador.controllers;

import com.betek.workshop_microservicios_validador.models.Persona;
import com.betek.workshop_microservicios_validador.models.Validacion;
import com.betek.workshop_microservicios_validador.services.ValidacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/validacion")
public class ValidacionController {

    @Autowired
    private ValidacionService validacionService;

    @PostMapping("/validar")
    public ResponseEntity<Validacion> validarPersonas(@RequestBody List<Persona> listaPersonas){
        return ResponseEntity.ok(validacionService.validarPersonas(listaPersonas));
    }
}
