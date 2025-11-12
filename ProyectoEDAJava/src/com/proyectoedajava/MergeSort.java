package com.proyectoedajava;

import java.util.*;
import java.util.concurrent.*;

public class MergeSort {
    public static void ordenar(List<Persona> lista) {
        if (lista.size() > 1) {
            int mid = lista.size() / 2;
            List<Persona> izquierda = new ArrayList<>(lista.subList(0, mid));
            List<Persona> derecha = new ArrayList<>(lista.subList(mid, lista.size()));

            ordenar(izquierda);
            ordenar(derecha);
            combinar(lista, izquierda, derecha);
        }
    }

    private static void combinar(List<Persona> lista, List<Persona> izq, List<Persona> der) {
        int i = 0, j = 0, k = 0;
        while (i < izq.size() && j < der.size()) {
            if (izq.get(i).compareTo(der.get(j)) <= 0) {
                lista.set(k++, izq.get(i++));
            } else {
                lista.set(k++, der.get(j++));
            }
        }
        while (i < izq.size()) lista.set(k++, izq.get(i++));
        while (j < der.size()) lista.set(k++, der.get(j++));
    }

    public static void ordenarParalelo(List<Persona> lista) {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.invoke(new MergeSortTask(lista));
    }

    private static class MergeSortTask extends RecursiveAction {
        private final List<Persona> lista;

        MergeSortTask(List<Persona> lista) {
            this.lista = lista;
        }

        @Override
        protected void compute() {
            if (lista.size() <= 1) return;

            int mid = lista.size() / 2;
            List<Persona> izquierda = new ArrayList<>(lista.subList(0, mid));
            List<Persona> derecha = new ArrayList<>(lista.subList(mid, lista.size()));

            MergeSortTask izqTask = new MergeSortTask(izquierda);
            MergeSortTask derTask = new MergeSortTask(derecha);
            invokeAll(izqTask, derTask);

            combinar(lista, izquierda, derecha);
        }
    }
}
