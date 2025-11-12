# ProyectoEDAJava

Este proyecto implementa un análisis y comparación de algoritmos de ordenamiento aplicados a un dataset de obesidad mundial.

## Descripción
Cargamos un archivo CSV (por defecto `data/obesidad_mundial_.csv`) que contiene datos de altura y peso. Calculamos el IMC y clasificamos según la OMS.

Luego aplicamos **QuickSort** y **MergeSort** en versiones **secuenciales** y **paralelas**, midiendo los tiempos de ejecución y mostrando cuál es el algoritmo más eficiente.

## Ejecución
1. Abre el proyecto en IntelliJ IDEA.
2. Ejecuta la clase `Main`.
3. Selecciona un archivo CSV o usa el predeterminado.
4. Observa los resultados en la consola.

## Salida esperada
```
    CLASIFICACIÓN OMS 
Bajo peso: 125000 registros
Peso normal: 210000 registros
Sobrepeso: 300000 registros
Obesidad I: 160000 registros
Obesidad II: 100000 registros
Obesidad III: 55000 registros
----------------------------------
QUICKSORT SECUENCIAL:  2.31 segundos
MERGESORT SECUENCIAL:  2.89 segundos
QUICKSORT PARALELO:    0.98 segundos
MERGESORT PARALELO:    1.10 segundos
----------------------------------
El algoritmo más rápido fue: QuickSort paralelo
```
