package algorithms;

/**
 * ============================================================
 * A4 - ORDENAMIENTO BURBUJA
 * ============================================================
 * Ordena un arreglo de enteros de menor a mayor usando Burbuja.
 *
 * Complejidad temporal:
 *   - Iterativo: O(n^2)
 *   - Recursivo: O(n^2)
 *
 * Complejidad espacial:
 *   - Iterativo: O(1)
 *   - Recursivo: O(n) por el call stack (n pasadas)
 * ============================================================
 */
public class OrdenamientoBurbuja {

    public static void iterativo(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    swapped = true;
                }
            }
            if (!swapped) break; // optimizacion: ya esta ordenado
        }
    }

    public static void recursivo(int[] arr) {
        recursivo(arr, arr.length);
    }

    /**
     * Realiza 1 pasada de burbuja y luego reduce el problema a n-1.
     */
    private static void recursivo(int[] arr, int n) {
        if (n <= 1) return;

        boolean swapped = false;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                int tmp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = tmp;
                swapped = true;
            }
        }

        if (!swapped) return;
        recursivo(arr, n - 1);
    }
}
