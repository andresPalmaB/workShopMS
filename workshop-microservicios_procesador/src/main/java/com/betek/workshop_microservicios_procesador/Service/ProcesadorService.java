package com.betek.workshop_microservicios_procesador.Service;

import com.betek.workshop_microservicios_procesador.models.Persona;
import com.betek.workshop_microservicios_procesador.models.RutaArchivo;
import com.betek.workshop_microservicios_procesador.models.Validacion;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProcesadorService {

    @Autowired
    private RestTemplate restTemplate;

    private List<Persona> mapearArchivo(RutaArchivo path){

        List<Persona> listaPersona = new ArrayList<>();
        try(
                FileReader reader = new FileReader(path.getPath());
                CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .withFirstRecordAsHeader())) {


            for (CSVRecord csvRecord : csvParser){
                Persona persona = new Persona();

                persona.setIndex(csvRecord.get("Index"));
                persona.setUserId(csvRecord.get("User Id"));
                persona.setFirsName(csvRecord.get("First Name"));
                persona.setLastName(csvRecord.get("Last Name"));
                persona.setSex(csvRecord.get("Sex"));
                persona.setEmail(csvRecord.get("Email"));
                persona.setPhone(csvRecord.get("Phone"));
                persona.setDateOfBirth(csvRecord.get("Date of birth"));
                persona.setJobTitle(csvRecord.get("Job Title"));

                listaPersona.add(persona);
            }

        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }

        return listaPersona;
    }

    public Validacion enviarValidarArchivo(RutaArchivo path){
        //iterar sobre la lista, coger cada registro y enviarlo al servicio de validacion
        //con la respuesta hacer contador de true y false
        //retornar validacion

        List<Persona> listaPersona = this.mapearArchivo(path);

        String url = "http://localhost:8082/api/validacion/validar";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<Persona>> requestEntity = new HttpEntity<>(listaPersona, headers);

        return restTemplate.postForObject(url, requestEntity, Validacion.class);
    }
}