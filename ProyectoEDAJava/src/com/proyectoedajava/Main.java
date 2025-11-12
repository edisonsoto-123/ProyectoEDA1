package com.proyectoedajava;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        String csvPath = seleccionarArchivo();

        List<Persona> personas = leerCSV(csvPath);

        if (personas.isEmpty()) {
            System.out.println("No se pudieron leer datos del archivo CSV.");
            return;
        }

        Map<String, Long> clasificacion = Utilidades.clasificarPorOMS(personas);
        Utilidades.mostrarClasificacion(clasificacion);

        // Copias para cada algoritmo
        List<Persona> quickSeq = new ArrayList<>(personas);
        List<Persona> mergeSeq = new ArrayList<>(personas);
        List<Persona> quickPar = new ArrayList<>(personas);
        List<Persona> mergePar = new ArrayList<>(personas);

        // Tiempos
        double tiempoQuickSeq = medirTiempo(() -> QuickSort.ordenar(quickSeq));
        double tiempoMergeSeq = medirTiempo(() -> MergeSort.ordenar(mergeSeq));
        double tiempoQuickPar = medirTiempo(() -> QuickSort.ordenarParalelo(quickPar));
        double tiempoMergePar = medirTiempo(() -> MergeSort.ordenarParalelo(mergePar));

        System.out.println("----------------------------------");
        System.out.printf("QUICKSORT SECUENCIAL:  %.2f segundos%n", tiempoQuickSeq);
        System.out.printf("MERGESORT SECUENCIAL:  %.2f segundos%n", tiempoMergeSeq);
        System.out.printf("QUICKSORT PARALELO:    %.2f segundos%n", tiempoQuickPar);
        System.out.printf("MERGESORT PARALELO:    %.2f segundos%n", tiempoMergePar);
        System.out.println("----------------------------------");

        Map<String, Double> resultados = new LinkedHashMap<>();
        resultados.put("QuickSort secuencial", tiempoQuickSeq);
        resultados.put("MergeSort secuencial", tiempoMergeSeq);
        resultados.put("QuickSort paralelo", tiempoQuickPar);
        resultados.put("MergeSort paralelo", tiempoMergePar);

        String mejor = resultados.entrySet().stream()
                .min(Comparator.comparingDouble(Map.Entry::getValue))
                .get().getKey();

        System.out.println("El algoritmo más rápido fue: " + mejor);
    }

    private static String seleccionarArchivo() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccionar archivo CSV de obesidad mundial");
        int resultado = chooser.showOpenDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        } else {
            System.out.println("No se seleccionó archivo. Usando el CSV por defecto...");
            return "data/obesidad_mundial_.csv";
        }
    }

    private static List<Persona> leerCSV(String ruta) {
        List<Persona> personas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String header = br.readLine(); // omitir encabezado
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length < 8) continue;

                double altura = Double.parseDouble(partes[6]);
                double peso = Double.parseDouble(partes[7]);
                double imc = peso / Math.pow(altura / 100.0, 2);

                personas.add(new Persona(peso, altura, imc));
            }
        } catch (Exception e) {
            System.out.println("Error al leer el CSV: " + e.getMessage());
        }
        return personas;
    }

    private static double medirTiempo(Runnable tarea) {
        long inicio = System.nanoTime();
        tarea.run();
        long fin = System.nanoTime();
        return (fin - inicio) / 1_000_000_000.0;
    }
}
