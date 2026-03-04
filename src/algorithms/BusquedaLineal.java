package algorithms;

/**
 * ============================================================
 * A3 - BUSQUEDA LINEAL
 * ============================================================
 * Busca un valor en un arreglo y retorna el indice, o -1 si no existe.
 *
 * Complejidad temporal:
 *   - Iterativo: O(n)
 *   - Recursivo: O(n)
 *
 * Complejidad espacial:
 *   - Iterativo: O(1)
 *   - Recursivo: O(n) por profundidad de llamadas (peor caso)
 * ============================================================
 */
public class BusquedaLineal {

    public static int iterativo(int[] arr, int objetivo) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == objetivo) return i;
        }
        return -1;
    }

    public static int recursivo(int[] arr, int objetivo) {
        return recursivo(arr, objetivo, 0);
    }

    private static int recursivo(int[] arr, int objetivo, int idx) {
        if (idx >= arr.length) return -1;
        if (arr[idx] == objetivo) return idx;
        return recursivo(arr, objetivo, idx + 1);
    }
}
