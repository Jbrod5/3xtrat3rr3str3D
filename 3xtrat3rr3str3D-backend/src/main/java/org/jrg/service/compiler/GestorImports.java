package org.jrg.service.compiler;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
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

        // compilar el import Y con sus hermanos para ver structs vecinas
        if ("y".equals(extension)) {
            ResultadoAnalisis resultadoY = analizarConjuntoY(rutaAbsoluta, linea, columna);
            acumularCuartetas(resultadoY);
            return resultadoY;
        }

        String contenido;
        try {
            contenido = Files.readString(Paths.get(rutaAbsoluta));
        } catch (IOException e) {
            this.recolectorErrores.agregar(TipoError.SEMANTICO, linea, columna, "no se pudo leer el archivo importado: " + rutaRelativa);
            return null;
        }

        // if ("y".equals(extension)) {
        //     ResultadoAnalisis resultado = this.compiladorY.analizar(contenido);
        //     acumularCuartetas(resultado);
        //     return resultado;
        // }
        // version nueva: se compila arriba con analizarConjuntoY para ver hermanos
        if ("z".equals(extension)) {
            ResultadoAnalisis resultado = analizarConjuntoZetariano(rutaAbsoluta, linea, columna);
            // quedarse solo con errores de ese archivo :c
            filtrarErroresPedidos(resultado, rutaAbsoluta);
            acumularCuartetas(resultado);
            return resultado;
        }

        this.recolectorErrores.agregar(TipoError.SEMANTICO, linea, columna, "extension no soportada en import: " + extension);
        return null;
    }

    // compilar el import con sus hermanos compartiendo ambito
    private ResultadoAnalisis analizarConjuntoZetariano(String rutaAbsoluta, int linea, int columna) {
        // conservar el error clasico si el archivo no existe
        if (Files.isRegularFile(Paths.get(rutaAbsoluta)) == false) {
            this.recolectorErrores.agregar(TipoError.SEMANTICO, linea, columna, "no se pudo leer el archivo importado: " + rutaAbsoluta);
            return null;
        }
        // juntar niveles de carpetas desde el archivo hacia arriba con tope
        List<List<Path>> niveles = new ArrayList<>();
        Path actual = Paths.get(rutaAbsoluta).getParent();
        for (int i = 0; i < 3 && actual != null; i++) {
            niveles.add(listarZetas(actual));
            actual = actual.getParent();
        }
        // acumular niveles hasta cerrar los tipos desconocidos
        Set<String> incluidas = new HashSet<>();
        List<Path> conjunto = new ArrayList<>();
        // incluir siempre el archivo importado aunque ya este marcado
        String propia = Paths.get(rutaAbsoluta).toString().replace("\\", "/");
        incluidas.add(propia);
        conjunto.add(Paths.get(rutaAbsoluta));
        ResultadoAnalisis resultado = null;
        for (int i = 0; i < niveles.size(); i++) {
            // agregar el nivel sin duplicar rutas ni reprocesar imports previos
            for (int j = 0; j < niveles.get(i).size(); j++) {
                String ruta = niveles.get(i).get(j).toString().replace("\\", "/");
                if (incluidas.contains(ruta) || this.archivosYaProcesados.contains(ruta)) {
                    continue;
                }
                incluidas.add(ruta);
                this.archivosYaProcesados.add(ruta);
                conjunto.add(niveles.get(i).get(j));
            }
            // compilar el conjunto actual
            resultado = this.compiladorZ.analizarProyecto(conjunto);
            // detener si ya no quedan tipos desconocidos
            if (quedanTiposDesconocidos(resultado) == false) {
                break;
            }
        }
        return resultado;
    }

    // compilar el import Y con sus hermanos para ver structs y funciones vecinas
    private ResultadoAnalisis analizarConjuntoY(String rutaAbsoluta, int linea, int columna) {
        // conservar el error clasico si el archivo no existe
        if (Files.isRegularFile(Paths.get(rutaAbsoluta)) == false) {
            this.recolectorErrores.agregar(TipoError.SEMANTICO, linea, columna, "no se pudo leer el archivo importado: " + rutaAbsoluta);
            return null;
        }
        // leer el archivo pedido
        String contenidoPedido = null;
        try {
            contenidoPedido = Files.readString(Paths.get(rutaAbsoluta));
        } catch (IOException e) {
            contenidoPedido = null;
        }
        // analizar solo el pedido primero
        ResultadoAnalisis soloPedido = null;
        if (contenidoPedido != null) {
            soloPedido = this.compiladorY.analizar(contenidoPedido);
        }
        // si no hay tipos sin resolver, no barrer hermanos
        if (soloPedido != null && quedanTiposDesconocidos(soloPedido) == false) {
            return soloPedido;
        }
        // juntar niveles de carpetas desde el archivo hacia arriba con tope
        List<List<Path>> niveles = new ArrayList<>();
        Path actual = Paths.get(rutaAbsoluta).getParent();
        for (int i = 0; i < 3 && actual != null; i++) {
            niveles.add(listarYes(actual));
            actual = actual.getParent();
        }
        // acumular niveles con el pedido siempre incluido
        Set<String> incluidas = new HashSet<>();
        List<Path> conjunto = new ArrayList<>();
        // incluir siempre el archivo importado aunque ya este marcado
        String propia = Paths.get(rutaAbsoluta).toString().replace("\\", "/");
        incluidas.add(propia);
        // agregar niveles sin duplicar rutas ni reprocesar imports previos
        for (int i = 0; i < niveles.size(); i++) {
            for (int j = 0; j < niveles.get(i).size(); j++) {
                String ruta = niveles.get(i).get(j).toString().replace("\\", "/");
                if (incluidas.contains(ruta) || this.archivosYaProcesados.contains(ruta)) {
                    continue;
                }
                incluidas.add(ruta);
                this.archivosYaProcesados.add(ruta);
                conjunto.add(niveles.get(i).get(j));
            }
        }
        // ordenar hermanos primero y el pedido al final para que vea todo
        List<Path> orden = new ArrayList<>(conjunto);
        orden.add(Paths.get(rutaAbsoluta));
        // compilar el conjunto con ambito compartido
        return this.compiladorY.analizarConjunto(orden, orden.size() - 1);
    }

    // listar los archivos del lenguaje Y de una carpeta en orden
    private List<Path> listarYes(Path carpeta) {
        // devolver vacio si la carpeta es nula
        List<Path> yes = new ArrayList<>();
        if (carpeta == null) {
            return yes;
        }
        // recorrer la carpeta filtrando por extension
        try (DirectoryStream<Path> flujo = Files.newDirectoryStream(carpeta)) {
            flujo.forEach(ruta -> {
                // aceptar solo archivos regulares con extension del lenguaje Y
                if (Files.isRegularFile(ruta) == false) {
                    return;
                }
                String nombre = ruta.getFileName().toString().toLowerCase();
                if (nombre.endsWith(".y") == false) {
                    return;
                }
                yes.add(ruta);
            });
        } catch (IOException e) {
            // omitir carpetas ilegibles
        }
        // ordenar para un resultado estable
        Collections.sort(yes);
        return yes;
    }

    // listar los archivos zetariano de una carpeta en orden
    private List<Path> listarZetas(Path carpeta) {
        // devolver vacio si la carpeta es nula
        List<Path> zetas = new ArrayList<>();
        if (carpeta == null) {
            return zetas;
        }
        // recorrer la carpeta filtrando por extension
        try (DirectoryStream<Path> flujo = Files.newDirectoryStream(carpeta)) {
            flujo.forEach(ruta -> {
                // aceptar solo archivos regulares con extension zetariana
                if (Files.isRegularFile(ruta) == false) {
                    return;
                }
                String nombre = ruta.getFileName().toString().toLowerCase();
                if (nombre.endsWith(".z") == false) {
                    return;
                }
                zetas.add(ruta);
            });
        } catch (IOException e) {
            // omitir carpetas ilegibles
        }
        // ordenar para un resultado estable
        Collections.sort(zetas);
        return zetas;
    }

    // verificar si quedan errores de tipos sin resolver
    private boolean quedanTiposDesconocidos(ResultadoAnalisis resultado) {
        // asumir cerrado si no hay resultado
        if (resultado == null || resultado.getErrores() == null) {
            return false;
        }
        // buscar marcas de referencias sin resolver
        for (int i = 0; i < resultado.getErrores().size(); i++) {
            String descripcion = resultado.getErrores().get(i).getDescripcion();
            if (descripcion == null) {
                continue;
            }
            if (descripcion.contains("no definido") || descripcion.contains("no declarada") || descripcion.contains("no accesible") || descripcion.contains("no existe")) {
                return true;
            }
        }
        return false;
    }

    // quedarse solo con errores de ese archivo
    private void filtrarErroresPedidos(ResultadoAnalisis resultado, String rutaAbsoluta) {
        // omitir resultados nulos o sin errores
        if (resultado == null || resultado.getErrores() == null) {
            return;
        }
        // extraer el nombre base sin extension para armar la marca
        String base = Paths.get(rutaAbsoluta).getFileName().toString();
        int punto = base.lastIndexOf('.');
        String nombre = base;
        if (punto > 0) {
            nombre = base.substring(0, punto);
        }
        String marca = "[" + nombre + "] ";
        // quedarse solo con errores de ese archivo
        resultado.getErrores().removeIf(error -> error == null || error.getDescripcion() == null || error.getDescripcion().startsWith(marca) == false);
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