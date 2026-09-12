package org.jrg.controller.archivos;

import org.jrg.service.archivos.GestorArchivosService;
import io.javalin.http.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArchivoController {

    private final GestorArchivosService gestorArchivosService;

    /**
     * Inicializar el controlador con el servicio de archivos.
     */
    public ArchivoController(GestorArchivosService gestorArchivosService) {
        this.gestorArchivosService = gestorArchivosService;
    }

    /**
     * Listar un directorio con metadata basica de cada entrada.
     */
    public void listar(Context ctx) {
        try {
            // extraer la ruta del cuerpo de la peticion
            Map<String, String> cuerpo = ctx.bodyAsClass(Map.class);
            String rutaBase = cuerpo.get("ruta");

            // asignar ruta por defecto si no viene
            if (rutaBase == null || rutaBase.isEmpty()) {
                rutaBase = ".";
            }

            System.out.println("backend listando directorio: " + rutaBase);

            // invocar el servicio para obtener el listado
            List<Map<String, Object>> lista = gestorArchivosService.listarDirectorio(rutaBase);

            // construir la respuesta exitosa
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("exito", true);
            respuesta.put("archivos", lista);

            ctx.status(200).json(respuesta);


        } catch (Exception e) {
            System.err.println("Error al listar directorio: " + e.getMessage());

            // construir la respuesta de error
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("exito", false);
            respuesta.put("mensaje", e.getMessage());

            ctx.status(500).json(respuesta);


        }
    }

    /**
     * Devolver el proyecto completo con todos los archivos y su contenido.
     */
    public void cargarProyecto(Context ctx) {
        try {
            // extraer la ruta base del cuerpo de la peticion
            Map<String, String> cuerpo = ctx.bodyAsClass(Map.class);
            String rutaBase = cuerpo.get("ruta");

            // validar que la ruta no venga vacia
            if (rutaBase == null || rutaBase.isEmpty()) {
                throw new IllegalArgumentException("El campo ruta es obligatorio");
            }

            System.out.println("Backend cargando proyecto desde: " + rutaBase);

            // invocar el servicio para cargar todos los archivos
            List<Map<String, Object>> archivos = gestorArchivosService.cargarProyecto(rutaBase);

            // construir la respuesta exitosa
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("exito", true);
            respuesta.put("archivos", archivos);

            ctx.status(200).json(respuesta);


        } catch (Exception e) {
            System.err.println("Error al cargar proyecto: " + e.getMessage());

            // construir la respuesta de error
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("exito", false);
            respuesta.put("mensaje", e.getMessage());

            ctx.status(500).json(respuesta);
        }
    }

    /**
     * Guardar un archivo recibiendo archivo ruta y contenido.
     */
    public void guardar(Context ctx) {
        try {
            // extraer los campos del cuerpo de la peticion
            Map<String, String> cuerpo = ctx.bodyAsClass(Map.class);
            String archivo = cuerpo.get("archivo");
            String ruta = cuerpo.get("ruta");
            String contenido = cuerpo.get("contenido");

            //              if (ruta == null || ruta.isEmpty()) {
//                  if (archivo != null && !archivo.isEmpty()) {
//                      ruta = archivo;
//                  } else {
//                      ruta = "principal.lat";
//                  }
//              }
            // asignar ruta por defecto si no viene
            if (ruta == null || ruta.isEmpty()) {
                if (archivo != null && !archivo.isEmpty()) {
                    ruta = archivo;
                } else {
                    ruta = "principal.z";
                }
            }

            System.out.println("backend guardando archivo=" + archivo + " ruta=" + ruta);

            // invocar el servicio para escribir el archivo en disco
            gestorArchivosService.guardarArchivo(ruta, contenido);
            System.out.println("archivo guardado: " + ruta);

            // construir la respuesta exitosa
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("exito", true);
            respuesta.put("mensaje", "archivo guardado correctamente");

            ctx.status(200).json(respuesta);


        } catch (Exception e) {
            System.err.println("error al guardar archivo: " + e.getMessage());

            // construir la respuesta de error
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("exito", false);
            respuesta.put("mensaje", e.getMessage());

            ctx.status(500).json(respuesta);
        }
    }

    /**
     * Leer el contenido de un archivo desde disco.
     */
    public void leer(Context ctx) {
        try {
            // extraer la ruta del cuerpo de la peticion
            Map<String, String> cuerpo = ctx.bodyAsClass(Map.class);
            String ruta = cuerpo.get("ruta");

            // invocar el servicio para leer el archivo
            String contenido = gestorArchivosService.leerArchivo(ruta);

            // construir la respuesta exitosa
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("exito", true);
            respuesta.put("contenido", contenido);

            ctx.status(200).json(respuesta);

        } catch (Exception e) {
            // construir la respuesta de error
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("exito", false);
            respuesta.put("mensaje", e.getMessage());

            ctx.status(500).json(respuesta);
        }
    }
}