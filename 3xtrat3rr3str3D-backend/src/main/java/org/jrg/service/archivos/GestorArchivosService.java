package org.jrg.service.archivos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class GestorArchivosService {

    /**
     * Guardar el contenido recibido en la ruta indicada sobreescribiendo si existe.
     */
    public void guardarArchivo(String ruta, String contenido) throws IOException {
        // construir la ruta destino
        Path path = Paths.get(ruta);

        // crear directorios padre si no existen
        if (path.getParent() != null) {
            Files.createDirectories(path.getParent());
        }

        // normalizar contenido nulo a una cadena vacia
        if (contenido == null) {
            contenido = "";
        }

        // escribir el archivo
        Files.writeString(path, contenido, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    /**
     * Leer el contenido de un archivo desde el disco.
     */
    public String leerArchivo(String ruta) throws IOException {
        // construir la ruta y retornar el contenido
        Path path = Paths.get(ruta);
        return Files.readString(path);
    }

    /**
     * Listar archivos y carpetas de un directorio.
     */
    public List<Map<String, Object>> listarDirectorio(String rutaBase) throws IOException {
        // preparar la lista de resultados
        Path path = Paths.get(rutaBase);
        List<Map<String, Object>> lista = new ArrayList<>();

        // retornar lista vacia si la ruta no existe
        if (!Files.exists(path)) {
            return lista;
        }

        // recorrer el arbol de archivos hasta 5 niveles (pa que no explote xd)
        Stream<Path> flujo = Files.walk(path, 5);
        List<Path> rutas = flujo.toList();

        // construir un nodo por cada ruta encontrada
        for (int i = 0; i < rutas.size(); i++) {
            Path p = rutas.get(i);
            if (!p.equals(path)) {
                Map<String, Object> nodo = new HashMap<>();
                nodo.put("ruta", p.toAbsolutePath().toString().replace("\\", "/"));
                nodo.put("nombre", p.getFileName().toString());
                nodo.put("esDirectorio", Files.isDirectory(p));

                lista.add(nodo);
            }
        }

        return lista;
    }

    /**
     * Cargar todos los archivos de texto de un proyecto con su contenido.
     */
    public List<Map<String, Object>> cargarProyecto(String rutaBase) throws IOException {
        // preparar la lista de resultados
        Path base = Paths.get(rutaBase);
        List<Map<String, Object>> lista = new ArrayList<>();

        // validar que la ruta exista y sea directorio
        if (!Files.exists(base) || !Files.isDirectory(base)) {
            return lista;
        }

        // recorrer el arbol de archivos hasta 10 niveles
        Stream<Path> flujo = Files.walk(base, 10);
        List<Path> rutas = flujo.toList();

        // leer cada archivo regular como texto
        for (int i = 0; i < rutas.size(); i++) {
            Path p = rutas.get(i);
            if (Files.isRegularFile(p)) {
                try {
                    // leer el contenido como texto plano
                    String contenido = Files.readString(p);

                    // construir el nodo con archivo ruta y contenido
                    Map<String, Object> nodo = new HashMap<>();
                    nodo.put("archivo", p.getFileName().toString());
                    nodo.put("ruta", p.toAbsolutePath().toString().replace("\\", "/"));
                    nodo.put("contenido", contenido);

                    lista.add(nodo);

                } catch (IOException ex) {
                    // ignorar archivos que no se puedan leer como texto :3
                    System.out.println("El archivo " + p.getFileName() + " se intento leer como texto en GestorArchivosService.java pero no contiene texto :c.");
                }
            }
        }

        return lista;
    }
}