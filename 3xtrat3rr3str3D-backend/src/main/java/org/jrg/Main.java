package org.jrg;

import org.jrg.controller.archivos.ArchivoController;
import org.jrg.controller.colores.ColoresController;
import org.jrg.service.archivos.GestorArchivosService;
import org.jrg.service.colores.ColoresService;
import io.javalin.Javalin;

public class Main {

    // iniciar el servidor web con javalin
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(rule -> {
                    rule.anyHost();
                });
            });
        }).start(7070);




        // instanciar los servicios del sistema - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        GestorArchivosService gestorArchivosService = new GestorArchivosService();
        ColoresService coloresService = new ColoresService();



        // instanciar los controladores con sus servicios - - - - - - - - - - - - - - - - - - - - - - - - - -
        ArchivoController archivoController = new ArchivoController(gestorArchivosService);
        ColoresController coloresController = new ColoresController(coloresService);





        // registrar endpoints - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // para archivos
        app.post("/api/archivos/guardar", archivoController::guardar);
        app.post("/api/archivos/leer", archivoController::leer);
        app.post("/api/archivos/listar", archivoController::listar);
        app.post("/api/archivos/proyecto", archivoController::cargarProyecto);

        // para colores
        app.post("/colores", coloresController::obtenerColores);

        // registrar ruta raiz de verificacion
        app.get("/", ctx -> ctx.result("backend activo :D"));
    }
}