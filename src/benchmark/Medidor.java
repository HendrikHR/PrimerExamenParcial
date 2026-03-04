package benchmark;

/**
 * ============================================================
 * MEDIDOR DE TIEMPO
 * ============================================================
 * Mide cuantos milisegundos tarda en ejecutarse un algoritmo.
 * Ejecuta el algoritmo REPETICIONES veces y calcula el promedio
 * para reducir el ruido de la JVM (JIT, GC, cache warming, etc.)
 *
 * USO PARA ESTUDIANTES:
 *   double tiempo = Medidor.medir(() -> Fibonacci.iterativo(n));
 * ============================================================
 */
public class Medidor {

    /** Cuantas veces se repite cada prueba para promediar */
    private static final int REPETICIONES = 5;

    // ----------------------------------------------------------------
    // INTERFAZ FUNCIONAL (permite pasar un metodo como parametro)
    // ----------------------------------------------------------------
    /**
     * Representa cualquier bloque de codigo a medir.
     * Se usa con expresiones lambda: () -> miAlgoritmo(n)
     */
    @FunctionalInterface
    public interface Algoritmo {
        void ejecutar();
    }

    /**
     * Permite preparar datos por repeticion fuera del bloque medido.
     * Por ejemplo: clonar un arreglo para que cada medicion ordene una copia.
     */
    @FunctionalInterface
    public interface Preparador {
        Algoritmo preparar();
    }

    // ----------------------------------------------------------------
    // METODO PRINCIPAL DE MEDICION
    // ----------------------------------------------------------------
    /**
     * Mide el tiempo promedio de ejecucion de un algoritmo.
     *
     * @param algoritmo bloque de codigo a medir (lambda)
     * @return tiempo promedio en milisegundos (ms)
     */
    public static double medir(Algoritmo algoritmo) {
        return medirEjecuciones(() -> algoritmo).promedioMs;
    }

    /**
     * Ejecuta el algoritmo REPETICIONES veces, captura cada tiempo y calcula promedio.
     *
     * @param preparador devuelve un Algoritmo listo para ejecutar (datos ya preparados)
     */
    public static ResultadoMedicion medirEjecuciones(Preparador preparador) {
        double[] tiemposMs = new double[REPETICIONES];
        long totalNanos = 0;

        for (int i = 0; i < REPETICIONES; i++) {
            Algoritmo algoritmo = preparador.preparar();

            long inicio = System.nanoTime();
            algoritmo.ejecutar();
            long fin = System.nanoTime();

            long durNanos = fin - inicio;
            totalNanos += durNanos;
            tiemposMs[i] = durNanos / 1_000_000.0;
        }

        double promedioMs = (totalNanos / (double) REPETICIONES) / 1_000_000.0;
        return new ResultadoMedicion(tiemposMs, promedioMs);
    }

    /** Resultado: 5 ejecuciones + promedio. */
    public static class ResultadoMedicion {
        public final double[] ejecucionesMs;
        public final double promedioMs;

        public ResultadoMedicion(double[] ejecucionesMs, double promedioMs) {
            this.ejecucionesMs = ejecucionesMs;
            this.promedioMs = promedioMs;
        }
    }

    // ----------------------------------------------------------------
    // UTILIDADES DE PRESENTACION
    // ----------------------------------------------------------------
    /**
     * Imprime el encabezado de la tabla de resultados.
     */
    public static void imprimirEncabezado() {
        System.out.println("-".repeat(60));
        System.out.printf("%-14s | %-12s | %-8s | %s%n",
                "Algoritmo", "Version", "n", "Tiempo (ms)");
        System.out.println("-".repeat(60));
    }

    /**
     * Imprime una fila de resultado con formato alineado.
     *
     * @param algoritmo nombre del algoritmo
     * @param version   "Iterativo" o "Recursivo"
     * @param n         tamano de entrada
     * @param tiempoMs  tiempo medido en ms
     */
    public static void imprimirFila(String algoritmo, String version, int n, double tiempoMs) {
        System.out.printf("%-14s | %-12s | n=%-5d | %.6f ms%n",
                algoritmo, version, n, tiempoMs);
    }
}
