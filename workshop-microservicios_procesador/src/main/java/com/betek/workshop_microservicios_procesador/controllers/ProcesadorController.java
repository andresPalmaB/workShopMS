package com.betek.workshop_microservicios_procesador.controllers;

import com.betek.workshop_microservicios_procesador.Service.ProcesadorService;
import com.betek.workshop_microservicios_procesador.models.RutaArchivo;
import com.betek.workshop_microservicios_procesador.models.Validacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpHeaders;
import java.util.List;

@RestController
@RequestMapping("/api/procesador")
public class ProcesadorController {

    @Autowired
    private ProcesadorService procesadorService;

    @PostMapping("/archivo")
    public ResponseEntity<Validacion> procesadorCsv(@RequestBody RutaArchivo path){
        return ResponseEntity.ok(procesadorService.enviarValidarArchivo(path));
    }
}
