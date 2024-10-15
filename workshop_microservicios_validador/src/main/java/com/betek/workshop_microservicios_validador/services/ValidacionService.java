package com.betek.workshop_microservicios_validador.services;

import com.betek.workshop_microservicios_validador.models.Persona;
import com.betek.workshop_microservicios_validador.models.Validacion;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ValidacionService {
    public Validacion validarPersonas(List<Persona> listaPersona){

        Validacion validando = new Validacion();
        int valido = 1;
        int invalido = 1;

        for (Persona persona : listaPersona){

            if (!validationPhone(persona.getPhone())){
                validando.setNumeroInvalido(invalido++);
            } else if (!validationCorreo(persona.getEmail())){
                validando.setNumeroInvalido(invalido++);
            } else if (!validacionDateBirth(persona.getDateOfBirth())){
                validando.setNumeroInvalido(invalido++);
            } else if (!validacionProfesion(persona.getJobTitle().trim().toLowerCase())){
                validando.setNumeroInvalido(invalido++);
            } else {
                validando.setNumeroValidos(valido++);
            }

//            if (!validationPhone(persona.getPhone())){
//                validando.setNumeroInvalido(invalido++);
//            } else {
//                validando.setNumeroValidos(valido++);
//            }

//            if (!validationCorreo(persona.getEmail())){
//                validando.setNumeroInvalido(invalido++);
//            }  else {
//                validando.setNumeroValidos(valido++);
//            }

//            if (!validacionDateBirth(persona.getDateOfBirth())){
//                validando.setNumeroInvalido(invalido++);
//            } else {
//                validando.setNumeroValidos(valido++);
//            }

//            System.out.println(persona.getJobTitle().trim().toLowerCase());
//            if (!validacionProfesion(persona.getJobTitle().trim().toLowerCase())){
//                validando.setNumeroInvalido(invalido++);
//            } else {
//                validando.setNumeroValidos(valido++);
//            }
        }
        return validando;
    }

    private boolean validationPhone(String phone){
        return phone.matches("^\\+1-\\d{3}-\\d{3}-\\d{4}x\\d{5}$");
    }

    private boolean validationCorreo(String correo){
        return correo.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-z]{2,}$");
    }

    private boolean validacionDateBirth(String fecha){

        DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate fechaHaFormatear = LocalDate.parse(fecha, formatoEntrada);
        DateTimeFormatter formatoSalida = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String fechaFormateada = fechaHaFormatear.format(formatoSalida);

        String regex = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/(\\d{4})$";

        if (!fechaFormateada.matches(regex)) {
            return false;
        }

        try {
            LocalDate fechaLimite = LocalDate.of(1980, 1, 1);

            return fechaHaFormatear.isAfter(fechaLimite);

        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean validacionProfesion(String profesion){
        List<String> listaProfesionesValidas = new ArrayList<>(Arrays.asList(
                "haematologist", //21
                "phytotherapist", //13
                "building surveyor", // 13
                "insurance account manager", // 20
                "educational psychologist") // 18
        );

        return listaProfesionesValidas.contains(profesion);
    }
}
