package utils;

import java.util.Random;

/**
 * ============================================================
 * GENERADOR DE DATOS
 * ============================================================
 * Genera arreglos de distintos tamanos para las pruebas.
 *
 * Nota: el objetivo es que la inicializacion NO se mida dentro
 * del bloque cronometrado.
 * ============================================================
 */
public class GeneradorDatos {

    /**
     * Genera un arreglo de tamano n con enteros pseudoaleatorios.
     *
     * @param n tamano
     * @param seed semilla para reproducibilidad
     */
    public static int[] arregloAleatorio(int n, long seed) {
        Random rnd = new Random(seed);
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            // rango moderado para evitar overflow y mantener comparabilidad
            arr[i] = rnd.nextInt(1_000_000);
        }
        return arr;
    }
}
