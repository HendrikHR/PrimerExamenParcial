package algorithms;

/**
 * ============================================================
 * A2 - SERIE DE FIBONACCI
 * ============================================================
 * Calcula el n-esimo termino de la serie: 0, 1, 1, 2, 3, 5, 8, 13 ...
 *
 * Complejidad temporal:
 *   - Iterativo:  O(n)   (ciclo lineal)
 *   - Recursivo:  O(2^n) (arbol de recursion crece exponencialmente)
 *
 * Complejidad espacial:
 *   - Iterativo:  O(1)
 *   - Recursivo:  O(n) (profundidad del stack)
 *
 * Nota: la version recursiva SIN memoizacion es intencional.
 * Por eso se limita n <= 30.
 * ============================================================
 */
public class Fibonacci {

    // ----------------------------------------------------------------
    // VERSION ITERATIVA
    // ----------------------------------------------------------------
    /**
     * Calcula el n-esimo Fibonacci con dos variables deslizantes.
     *
     * Traza para n = 6:
     *   anterior=0, actual=1
     *   i=2 -> sig=1,  anterior=1, actual=1
     *   i=3 -> sig=2,  anterior=1, actual=2
     *   i=4 -> sig=3,  anterior=2, actual=3
     *   i=5 -> sig=5,  anterior=3, actual=5
     *   i=6 -> sig=8,  anterior=5, actual=8  (retorna 8)
     *
     * @param n indice del termino (0-indexado)
     * @return el n-esimo numero de Fibonacci
     */
    public static long iterativo(int n) {
        if (n < 0) throw new IllegalArgumentException("n no puede ser negativo");

        if (n == 0) return 0;
        if (n == 1) return 1;

        long anterior = 0;
        long actual   = 1;

        for (int i = 2; i <= n; i++) {
            long siguiente = anterior + actual;
            anterior = actual;
            actual   = siguiente;
        }
        return actual;
    }

    // ----------------------------------------------------------------
    // VERSION RECURSIVA (sin memoizacion - intencional para analisis)
    // ----------------------------------------------------------------
    /**
     * Calcula el n-esimo Fibonacci con recursion directa.
     *
     * Formula recursiva:
     *   fib(0) = 0  (caso base)
     *   fib(1) = 1  (caso base)
     *   fib(n) = fib(n-1) + fib(n-2)  (dos llamadas recursivas)
     *
     * Arbol de llamadas para n = 5:
     *             fib(5)
     *           /         \
     *        fib(4)       fib(3)
     *       /     \       /    \
     *    fib(3) fib(2) fib(2) fib(1)
     *    ...
     *
     * Nota: fib(3) se calcula DOS VECES -> trabajo duplicado = O(2^n)
     *
     * @param n indice del termino (0 <= n <= 30 para tiempos razonables)
     * @return el n-esimo numero de Fibonacci
     */
    public static long recursivo(int n) {
        if (n < 0) throw new IllegalArgumentException("n no puede ser negativo");
        if (n > 30) throw new IllegalArgumentException("Limita n <= 30 en version recursiva (O(2^n))");

        // --- CASOS BASE ---
        if (n == 0) return 0;
        if (n == 1) return 1;

        // --- DOS LLAMADAS RECURSIVAS = crecimiento exponencial ---
        return recursivo(n - 1) + recursivo(n - 2);
    }
}
