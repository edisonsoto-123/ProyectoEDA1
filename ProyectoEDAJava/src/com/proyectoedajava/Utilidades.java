package com.proyectoedajava;

import java.util.*;

public class Utilidades {
    public static Map<String, Long> clasificarPorOMS(List<Persona> personas) {
        Map<String, Long> clasificacion = new LinkedHashMap<>();
        clasificacion.put("Bajo peso", personas.stream().filter(p -> p.getImc() < 18.5).count());
        clasificacion.put("Peso normal", personas.stream().filter(p -> p.getImc() >= 18.5 && p.getImc() < 25).count());
        clasificacion.put("Sobrepeso", personas.stream().filter(p -> p.getImc() >= 25 && p.getImc() < 30).count());
        clasificacion.put("Obesidad I", personas.stream().filter(p -> p.getImc() >= 30 && p.getImc() < 35).count());
        clasificacion.put("Obesidad II", personas.stream().filter(p -> p.getImc() >= 35 && p.getImc() < 40).count());
        clasificacion.put("Obesidad III", personas.stream().filter(p -> p.getImc() >= 40).count());
        return clasificacion;
    }

    public static void mostrarClasificacion(Map<String, Long> clasificacion) {
        System.out.println("===== CLASIFICACIÓN OMS =====");
        clasificacion.forEach((k, v) -> System.out.printf("%s: %d registros%n", k, v));
    }
}
