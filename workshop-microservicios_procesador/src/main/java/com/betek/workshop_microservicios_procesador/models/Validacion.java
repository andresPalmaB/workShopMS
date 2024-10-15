package com.betek.workshop_microservicios_procesador.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Validacion {
    private int numeroValidos;
    private int numeroInvalido;
}
