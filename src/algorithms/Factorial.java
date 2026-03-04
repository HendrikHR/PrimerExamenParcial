package algorithms;

/**
 * ============================================================
 * A1 - FACTORIAL
 * ============================================================
 * Calcula n! (n factorial).
 *
 * Reglas del examen:
 *   - Caso base: 0! = 1
 *   - Limitar n a 20 (20! cabe en long; 21! ya desborda)
 *
 * Complejidad temporal:
 *   - Iterativo: O(n)
 *   - Recursivo: O(n)
 *
 * Complejidad espacial:
 *   - Iterativo: O(1)
 *   - Recursivo: O(n) por el call stack
 * ============================================================
 */
public class Factorial {

    private static final int MAX_N = 20;

    public static long iterativo(int n) {
        validar(n);
        long resultado = 1L;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    public static long recursivo(int n) {
        validar(n);
        if (n == 0) return 1L;
        return n * recursivo(n - 1);
    }

    private static void validar(int n) {
        if (n < 0) throw new IllegalArgumentException("n no puede ser negativo");
        if (n > MAX_N) throw new IllegalArgumentException("Limita n <= " + MAX_N + " (overflow en long)");
    }
}
