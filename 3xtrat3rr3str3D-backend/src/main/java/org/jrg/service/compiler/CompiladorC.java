package org.jrg.service.compiler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.jrg.model.resultado.ResultadoGcc;

// compilar codigo C con gcc del sistema y capturar el resultado
public class CompiladorC {

    private static final String ARCHIVO_C = "/tmp/programa_3xtrat3rr3str3D.c";
    private static final String ARCHIVO_BIN = "/tmp/programa_3xtrat3rr3str3D.bin";
    private static final String COMANDO_GCC = "gcc";
    private static final int TIMEOUT_SEGUNDOS = 10;

    /**
     * Compilar el codigo C con gcc y capturar el resultado.
     */
    public ResultadoGcc compilar(String codigoC) {

        // ignorar codigo  si viene vacio :3
        if (codigoC == null || codigoC.trim().isEmpty()) {
            return new ResultadoGcc(false, "codigo C vacio", "", "");
        }

        // escribir el codigo a un archivo temporal
        Path archivoC = Paths.get(ARCHIVO_C);
        try {
            Files.writeString(archivoC, codigoC, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return new ResultadoGcc(false, "no se pudo escribir el archivo temporal: " + e.getMessage(), "", "");
        }

        // construir el comando
        String comando = COMANDO_GCC + " -Wall -o " + ARCHIVO_BIN + " " + ARCHIVO_C;

        // ejecutar con timeout
        try {
            ProcessBuilder constructor = new ProcessBuilder();
            constructor.command("sh", "-c", comando);
            constructor.redirectErrorStream(true);
            Process proceso = constructor.start();
            boolean termino = proceso.waitFor(TIMEOUT_SEGUNDOS, TimeUnit.SECONDS);

            // detener el proceso si excede el tiempo
            if (termino == false) {
                proceso.destroyForcibly();
                return new ResultadoGcc(false, "timeout: gcc tardo mas de " + TIMEOUT_SEGUNDOS + " segundos", comando, "");
            }

            // leer la salida
            byte[] salidaBytes = proceso.getInputStream().readAllBytes();
            String salida = new String(salidaBytes, StandardCharsets.UTF_8);
            if (salida.isEmpty()) {
                salida = "sin errores ni advertencias";
            }

            int exitCode = proceso.exitValue();
            boolean compilo = exitCode == 0;
            String rutaBinario = "";

            if (compilo) {
                rutaBinario = ARCHIVO_BIN;
            }

            return new ResultadoGcc(compilo, salida, comando, rutaBinario);

        } catch (IOException e) {
            return new ResultadoGcc(false, "error al ejecutar gcc: " + e.getMessage() + "\nVerifica que gcc este instalado en el sistema", comando, "");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

            return new ResultadoGcc(false, "compilacion interrumpida: " + e.getMessage(), comando, "");
        }
    }
}
