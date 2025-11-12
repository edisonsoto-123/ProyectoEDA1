package com.proyectoedajava;

import java.util.List;
import java.util.concurrent.*;

public class QuickSort {
    public static void ordenar(List<Persona> lista) {
        quickSort(lista, 0, lista.size() - 1);
    }

    private static void quickSort(List<Persona> lista, int low, int high) {
        if (low < high) {
            int pi = particion(lista, low, high);
            quickSort(lista, low, pi - 1);
            quickSort(lista, pi + 1, high);
        }
    }

    private static int particion(List<Persona> lista, int low, int high) {
        Persona pivote = lista.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (lista.get(j).compareTo(pivote) <= 0) {
                i++;
                Persona temp = lista.get(i);
                lista.set(i, lista.get(j));
                lista.set(j, temp);
            }
        }
        Persona temp = lista.get(i + 1);
        lista.set(i + 1, lista.get(high));
        lista.set(high, temp);
        return i + 1;
    }

    public static void ordenarParalelo(List<Persona> lista) {
        ForkJoinPool pool = ForkJoinPool.commonPool();
        pool.invoke(new QuickSortTask(lista, 0, lista.size() - 1));
    }

    private static class QuickSortTask extends RecursiveAction {
        private final List<Persona> lista;
        private final int low, high;

        QuickSortTask(List<Persona> lista, int low, int high) {
            this.lista = lista;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (low < high) {
                int pi = particion(lista, low, high);
                invokeAll(new QuickSortTask(lista, low, pi - 1),
                          new QuickSortTask(lista, pi + 1, high));
            }
        }
    }
}
