package com.betek.workshop_microservicios_procesador;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        String ruta = "C:\\Users\\andre\\OneDrive\\Documentos\\programacion2\\WorkShopMicroServicios\\peopleCSV.csv";

        System.out.println(Files.exists(Paths.get(ruta)));
    }
}
