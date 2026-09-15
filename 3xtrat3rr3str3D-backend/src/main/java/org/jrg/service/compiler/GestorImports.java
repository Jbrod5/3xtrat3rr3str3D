package org.jrg.service.compiler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jrg.model.error.TipoError;
import org.jrg.model.resultado.ResultadoAnalisis;
import org.jrg.service.error.RecolectorErrores;

public class GestorImports {

    private final String rutaBase;
    private final RecolectorErrores recolectorErrores;
    private final Set<String> archivosYaProcesados;
    private final CompiladorYLenguajeService compiladorY;
    private final CompiladorZetarianoService compiladorZ;

    /**
     * Crear un gestor de imports para un proyecto en la ruta base indicada.
     */
    public GestorImports(String rutaBase, RecolectorErrores recolectorErrores) {
        this.rutaBase = rutaBase;
        this.recolectorErrores = recolectorErrores;
        this.archivosYaProcesados = new HashSet<>();
        this.compiladorY = new CompiladorYLenguajeService();
        this.compiladorZ = new CompiladorZetarianoService();
    }

    /**
     * Procesar un import y devolver el resultado del archivo importado.
     */
    public ResultadoAnalisis procesarImport(List<String> partesRuta, int linea, int columna) {
        if (partesRuta == null || partesRuta.size() < 2) {
            this.recolectorErrores.agregar(TipoError.SEMANTICO, linea, columna, "ruta de import invalida");
            return null;
        }

        // el ultimo elemento es la extension, el penultimo el nombre del archivo
        String extension = partesRuta.get(partesRuta.size() - 1).toLowerCase();
        String nombreArchivo = partesRuta.get(partesRuta.size() - 2);

        // construir la ruta relativa desde los segmentos de carpeta
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < partesRuta.size() - 2; i++) {
            if (i > 0) sb.append("/");
            sb.append(partesRuta.get(i));
        }
        if (sb.length() > 0) sb.append("/");
        sb.append(nombreArchivo);
        sb.append(".");
        sb.append(extension);
        String rutaRelativa = sb.toString();

        String rutaAbsoluta = resolverRutaAbsoluta(rutaRelativa);

        if (this.archivosYaProcesados.contains(rutaAbsoluta)) {
            return null;
        }
        this.archivosYaProcesados.add(rutaAbsoluta);

        String contenido;
        try {
            contenido = Files.readString(Paths.get(rutaAbsoluta));
        } catch (IOException e) {
            this.recolectorErrores.agregar(TipoError.SEMANTICO, linea, columna,
                    "no se pudo leer el archivo importado: " + rutaRelativa);
            return null;
        }

        if ("y".equals(extension)) {
            return this.compiladorY.analizar(contenido);
        }
        if ("z".equals(extension)) {
            return this.compiladorZ.analizar(contenido);
        }

        this.recolectorErrores.agregar(TipoError.SEMANTICO, linea, columna,
                "extension no soportada en import: " + extension);
        return null;
    }

    // resolver la ruta relativa contra la ruta base del proyecto
    private String resolverRutaAbsoluta(String rutaRelativa) {
        if (this.rutaBase == null || this.rutaBase.isEmpty()) {
            return rutaRelativa;
        }

        Path base = Paths.get(this.rutaBase);

        // si rutaBase apunta a un archivo, usar su carpeta padre
        if (Files.isRegularFile(base)) {
            base = base.getParent();
        }

        Path resuelta = base.resolve(rutaRelativa);
        return resuelta.toString().replace("\\", "/");
    }
}