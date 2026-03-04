# Proyecto Estructura de Datos — Fibonacci
**Universidad Da Vinci de Guatemala**
Ing. Brandon Chitay

---

## Requisitos

- **Java 11** o superior (el proyecto usa `String.repeat()` que no existe en Java 8)
- **Terminal Bash** (Git Bash en Windows, Terminal en macOS/Linux)
- **VS Code** con la extensin [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack)

---

## Estructura del proyecto

```
ProyectoED_Demo/
├── src/
│   ├── algorithms/
│   │   ├── Factorial.java        ← A1: iterativo O(n) y recursivo O(n)
│   │   ├── Fibonacci.java        ← A2: iterativo O(n) y recursivo O(2^n)
│   │   ├── BusquedaLineal.java   ← A3: iterativo O(n) y recursivo O(n)
│   │   └── OrdenamientoBurbuja.java ← A4: iterativo O(n^2) y recursivo O(n^2)
│   └── benchmark/
│       ├── Medidor.java          ← Mide tiempos con promedios
│       └── Main.java             ← Punto de entrada, exporta CSV
│   └── utils/
│       └── GeneradorDatos.java   ← Genera arreglos para las pruebas
├── resultados/
│   └── tiempos.csv               ← Se genera al ejecutar
├── out/                          ← Archivos .class compilados
├── .sdkmanrc                     ← Configura Java 11 automáticamente con SDKMAN
└── README.md
```

---

## Guia de instalacion paso a paso (Windows)

### Paso 1 — Instalar Git Bash

SDKMAN necesita una terminal Bash. En Windows usaremos **Git Bash** que viene incluido con Git.

1. Descargar Git para Windows: https://git-scm.com/download/win
2. Ejecutar el instalador y **aceptar todas las opciones por defecto**
3. Al terminar, buscar **"Git Bash"** en el menu de inicio y abrirlo
4. Verificar que funciona:

```bash
echo "Hola desde Git Bash"
```

> **Importante:** Todos los comandos de esta guia se ejecutan en **Git Bash**, NO en CMD ni PowerShell.

---

### Paso 2 — Instalar SDKMAN

En Git Bash, ejecutar:

```bash
curl -s "https://get.sdkman.io" | bash
```

Cuando termine, **cerrar Git Bash y abrirlo de nuevo**. Luego verificar:

```bash
sdk version
```

Debe mostrar algo como `SDKMAN! 5.x.x`. Si dice "command not found", ejecutar:

```bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
```

---

### Paso 3 — Instalar Java 11

```bash
sdk install java 11.0.25-amzn
```

Cuando pregunte si desea establecerlo como default, escribir **Y** (si).

Verificar la instalacion:

```bash
java -version
```

Debe mostrar algo como:

```
openjdk version "11.0.25" 2024-10-15 LTS
```

---

### Paso 4 — Clonar o descargar el proyecto

**Opcion A — Con git (recomendado):**

```bash
cd ~/Desktop
git clone <URL_DEL_REPOSITORIO>
cd ProyectoED_Demo
```

**Opcion B — Descarga manual:**

1. Descargar el ZIP del repositorio
2. Extraerlo en el Escritorio
3. En Git Bash:

```bash
cd ~/Desktop/ProyectoED_Demo
```

---

### Paso 5 — Activar Java 11 automaticamente

El proyecto incluye un archivo `.sdkmanrc` que configura Java 11 automaticamente. Para habilitar esta funcion:

```bash
sdk env install
```

Si SDKMAN pregunta activar `sdkman_auto_env`, ejecutar:

```bash
sdk config
```

Esto abre un archivo de configuracion. Buscar la linea `sdkman_auto_env=false` y cambiarla a:

```
sdkman_auto_env=true
```

Guardar y cerrar. Ahora, cada vez que entres a la carpeta del proyecto, SDKMAN cambiara a Java 11 automaticamente.

---

### Paso 6 — Compilar y ejecutar

Desde la carpeta raiz del proyecto (`ProyectoED_Demo/`):

```bash
# Compilar (recomendado: especificar UTF-8)
javac -encoding UTF-8 -d out src/algorithms/*.java src/utils/*.java src/benchmark/*.java

# Ejecutar
java -cp out benchmark.Main
```

Si todo sale bien, veras la tabla de tiempos y se generara el archivo `resultados/tiempos.csv`.

---

## Configuracion de VS Code

### 1. Instalar extensiones

Abrir VS Code e instalar:
- **Extension Pack for Java** (buscar `vscjava.vscode-java-pack`)

### 2. Configurar el JDK del proyecto

Abrir la carpeta del proyecto en VS Code. El archivo `.vscode/settings.json` ya apunta a Java 11. Si VS Code no encuentra el JDK, necesitas ajustar la ruta.

Abrir `.vscode/settings.json` y cambiar la ruta al JDK de tu maquina:

**Windows (con SDKMAN en Git Bash):**
```json
{
    "java.jdt.ls.java.home": "C:\\Users\\TU_USUARIO\\.sdkman\\candidates\\java\\11.0.25-amzn"
}
```

**macOS:**
```json
{
    "java.jdt.ls.java.home": "/Users/TU_USUARIO/.sdkman/candidates/java/11.0.25-amzn"
}
```

> Reemplaza `TU_USUARIO` con tu nombre de usuario. Para encontrar la ruta exacta, ejecuta en Git Bash:
> ```bash
> sdk home java 11.0.25-amzn
> ```

### 3. Ejecutar desde VS Code

- Abrir `src/benchmark/Main.java`
- Hacer clic en **"Run"** encima del metodo `main` o presionar `F5`

---

## Que esperar en la salida

El programa muestra tres secciones:

1. **Fibonacci Iterativo** — tiempos para n = 5, 10, 15, 20, 25, 30
2. **Fibonacci Recursivo** — mismos valores de n
3. **Tabla comparativa** — cuantas veces mas lento es el recursivo

Al finalizar, genera el archivo `resultados/tiempos.csv`.

---

## Por que O(n) vs O(2^n) importa

| n  | Llamadas Iterativo | Llamadas Recursivo |
|----|--------------------|--------------------|
| 5  | 5                  | 15                 |
| 10 | 10                 | 177                |
| 20 | 20                 | 21,891             |
| 30 | 30                 | 2,692,537          |

A partir de n = 35 la version recursiva empieza a tardar **segundos**.
A partir de n = 50 puede tardar **horas**.

---

## Solucion de problemas comunes

### `The method repeat(int) is undefined for the type String`

Estas usando Java 8. Este proyecto requiere **Java 11 o superior**.

```bash
java -version          # verificar version actual
sdk use java 11.0.25-amzn   # cambiar a Java 11
```

### `javac: command not found`

SDKMAN no esta inicializado en tu terminal. Ejecutar:

```bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
```

### `sdk: command not found`

Asegurate de estar usando **Git Bash** y no CMD o PowerShell. Si ya estas en Git Bash, reinstala SDKMAN (Paso 2).

### `error: package algorithms does not exist`

Asegurate de compilar desde la carpeta raiz del proyecto:

```bash
cd ~/Desktop/ProyectoED_Demo
javac -d out src/algorithms/Fibonacci.java src/benchmark/Medidor.java src/benchmark/Main.java
```

### La carpeta `resultados/` no existe

Crearla antes de ejecutar:

```bash
mkdir -p resultados
```

### VS Code no reconoce Java 11

1. Presionar `Ctrl+Shift+P` (o `Cmd+Shift+P` en Mac)
2. Buscar **"Java: Clean Java Language Server Workspace"**
3. Seleccionar **"Restart and delete"**

---

## Tarea para el estudiante

Modifica `TAMANOS` en `Main.java` para experimentar con distintos valores de n y observa como crecen los tiempos de forma lineal (iterativo) vs exponencial (recursivo).
