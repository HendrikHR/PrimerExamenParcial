package benchmark;

import algorithms.BusquedaLineal;
import algorithms.Factorial;
import algorithms.Fibonacci;
import algorithms.OrdenamientoBurbuja;
import utils.GeneradorDatos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ============================================================
 * PUNTO DE ENTRADA - BENCHMARK (4 ALGORITMOS)
 * ============================================================
 * Implementa y mide:
 *   A1 Factorial (iterativo + recursivo)
 *   A2 Fibonacci (iterativo + recursivo)
 *   A3 Busqueda Lineal (iterativo + recursivo)
 *   A4 Burbuja (iterativo + recursivo)
 *
 * Exporta resultados a: resultados/tiempos.csv
 *
 * COMO COMPILAR:
 *   javac -d out \
 *     src/algorithms/*.java \
 *     src/utils/*.java \
 *     src/benchmark/*.java
 *
 * COMO EJECUTAR:
 *   java -cp out benchmark.Main
 * ============================================================
 */
public class Main {

    // n para factorial y fibonacci
    private static final int[] N_SMALL = {5, 10, 15, 20, 25, 30};

    // n para busqueda lineal y burbuja
    private static final int[] N_LARGE = {100, 500, 1000, 5000, 10000};

    private static final String CSV_PATH = "resultados/tiempos.csv";

    public static void main(String[] args) {
        imprimirBanner();
        asegurarCarpetaResultados();

        try (PrintWriter out = new PrintWriter(new FileWriter(CSV_PATH))) {
            out.println("Algoritmo,Version,n,Ej1_ms,Ej2_ms,Ej3_ms,Ej4_ms,Ej5_ms,Promedio_ms");

            // ========================================================
            // A1 - Factorial
            // ========================================================
            System.out.println("\nA1 - FACTORIAL");
            Medidor.imprimirEncabezado();
            for (int n : N_SMALL) {
                if (n > 20) continue; // por restriccion de overflow (ver enunciado)

                var iter = Medidor.medirEjecuciones(() -> () -> Factorial.iterativo(n));
                imprimirYExportar(out, "A1 - Factorial", "Iterativo", n, iter);

                var rec = Medidor.medirEjecuciones(() -> () -> Factorial.recursivo(n));
                imprimirYExportar(out, "A1 - Factorial", "Recursivo", n, rec);
            }

            // ========================================================
            // A2 - Fibonacci
            // ========================================================
            System.out.println("\nA2 - FIBONACCI");
            Medidor.imprimirEncabezado();
            for (int n : N_SMALL) {
                var iter = Medidor.medirEjecuciones(() -> () -> Fibonacci.iterativo(n));
                imprimirYExportar(out, "A2 - Fibonacci", "Iterativo", n, iter);

                // recursivo esta limitado a n <= 30 (validacion interna)
                var rec = Medidor.medirEjecuciones(() -> () -> Fibonacci.recursivo(n));
                imprimirYExportar(out, "A2 - Fibonacci", "Recursivo", n, rec);
            }

            // ========================================================
            // A3 - Busqueda Lineal
            // ========================================================
            System.out.println("\nA3 - BUSQUEDA LINEAL");
            Medidor.imprimirEncabezado();
            for (int n : N_LARGE) {
                int[] arr = GeneradorDatos.arregloAleatorio(n, 12345L + n);
                int objetivo = arr[n - 1]; // peor caso (esta al final)

                var iter = Medidor.medirEjecuciones(() -> () -> BusquedaLineal.iterativo(arr, objetivo));
                imprimirYExportar(out, "A3 - Busqueda Lineal", "Iterativo", n, iter);

                var rec = Medidor.medirEjecuciones(() -> () -> BusquedaLineal.recursivo(arr, objetivo));
                imprimirYExportar(out, "A3 - Busqueda Lineal", "Recursivo", n, rec);
            }

            // ========================================================
            // A4 - Burbuja
            // ========================================================
            System.out.println("\nA4 - ORDENAMIENTO BURBUJA");
            Medidor.imprimirEncabezado();
            for (int n : N_LARGE) {
                int[] base = GeneradorDatos.arregloAleatorio(n, 67890L + n);

                // En cada repeticion, clonamos ANTES de medir para no incluir inicializacion.
                var iter = Medidor.medirEjecuciones(() -> {
                    int[] copia = base.clone();
                    return () -> OrdenamientoBurbuja.iterativo(copia);
                });
                imprimirYExportar(out, "A4 - Burbuja", "Iterativo", n, iter);

                var rec = Medidor.medirEjecuciones(() -> {
                    int[] copia = base.clone();
                    return () -> OrdenamientoBurbuja.recursivo(copia);
                });
                imprimirYExportar(out, "A4 - Burbuja", "Recursivo", n, rec);
            }

        } catch (IOException e) {
            System.err.println("Error escribiendo CSV: " + e.getMessage());
        }

        System.out.println("\nListo. Archivo generado: " + CSV_PATH);
    }

    private static void imprimirYExportar(
            PrintWriter out,
            String algoritmo,
            String version,
            int n,
            Medidor.ResultadoMedicion r
    ) {
        // Mostrar promedio en consola
        Medidor.imprimirFila(algoritmo, version, n, r.promedioMs);

        // CSV: 5 ejecuciones + promedio
        out.printf(
                "%s,%s,%d,%.6f,%.6f,%.6f,%.6f,%.6f,%.6f%n",
                algoritmo,
                version,
                n,
                r.ejecucionesMs[0],
                r.ejecucionesMs[1],
                r.ejecucionesMs[2],
                r.ejecucionesMs[3],
                r.ejecucionesMs[4],
                r.promedioMs
        );
    }

    private static void asegurarCarpetaResultados() {
        File dir = new File("resultados");
        if (!dir.exists()) {
            //noinspection ResultOfMethodCallIgnored
            dir.mkdirs();
        }
    }

    private static void imprimirBanner() {
        System.out.println("=".repeat(70));
        System.out.println("  EXAMEN PARCIAL - ALGORITMOS ITERATIVOS vs RECURSIVOS");
        System.out.println("  Benchmark + exportacion CSV para graficas en Excel");
        System.out.println("=".repeat(70));
    }
}
