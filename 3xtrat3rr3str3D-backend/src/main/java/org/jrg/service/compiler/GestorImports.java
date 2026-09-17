package org.jrg.service.compiler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.model.error.TipoError;
import org.jrg.model.resultado.CuartetaResultado;
import org.jrg.model.resultado.ResultadoAnalisis;
import org.jrg.service.error.RecolectorErrores;

public class GestorImports {

    private final String rutaBase;
    private final RecolectorErrores recolectorErrores;
    private final Set<String> archivosYaProcesados;
    private final CompiladorYLenguajeService compiladorY;
    private final CompiladorZetarianoService compiladorZ;
    // cuartetas acumuladas de todos los imports procesados
    private final List<Cuarteta> cuartetasAcumuladas;

    /**
     * Crear un gestor de imports para un proyecto en la ruta base indicada.
     */
    public GestorImports(String rutaBase, RecolectorErrores recolectorErrores) {
        this.rutaBase = rutaBase;
        this.recolectorErrores = recolectorErrores;
        this.archivosYaProcesados = new HashSet<>();
        this.compiladorY = new CompiladorYLenguajeService();
        this.compiladorZ = new CompiladorZetarianoService();
        this.cuartetasAcumuladas = new ArrayList<>();
    }

    /**
     * Obtener las cuartetas acumuladas de los imports procesados.
     */
    public List<Cuarteta> getCuartetasAcumuladas() {
        return this.cuartetasAcumuladas;
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
            ResultadoAnalisis resultado = this.compiladorY.analizar(contenido);
            acumularCuartetas(resultado);
            return resultado;
        }
        if ("z".equals(extension)) {
            ResultadoAnalisis resultado = this.compiladorZ.analizar(contenido);
            acumularCuartetas(resultado);
            return resultado;
        }

        this.recolectorErrores.agregar(TipoError.SEMANTICO, linea, columna,
                "extension no soportada en import: " + extension);
        return null;
    }

    // acumular las cuartetas del resultado importado
    private void acumularCuartetas(ResultadoAnalisis resultado) {
        if (resultado == null) {
            return;
        }
        // obtener las cuartetas como CuartetaResultado
        List<CuartetaResultado> cuartetasResultado = resultado.getCuartetas();
        if (cuartetasResultado == null) {
            return;
        }
        // convertir cada CuartetaResultado a Cuarteta y acumular
        for (int i = 0; i < cuartetasResultado.size(); i++) {
            CuartetaResultado cr = cuartetasResultado.get(i);
            Cuarteta c = new Cuarteta(cr.getOperador(), cr.getArg1(), cr.getArg2(), cr.getResultado(), cr.getTipoArg1(), cr.getTipoArg2(), cr.getTipoResultado());
            this.cuartetasAcumuladas.add(c);
        }
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

        // primero probar la ruta relativa al archivo
        Path directa = base.resolve(rutaRelativa);
        if (Files.exists(directa)) {
            return directa.toString().replace("\\", "/");
        }

        // si no existe subir por los directorios padres buscando el archivo
        Path actual = base.getParent();
        while (actual != null) {
            Path candidata = actual.resolve(rutaRelativa);
            if (Files.exists(candidata)) {
                return candidata.toString().replace("\\", "/");
            }
            actual = actual.getParent();
        }

        // devolver la ruta directa para un mensaje de error entendible
        return directa.toString().replace("\\", "/");
    }
}