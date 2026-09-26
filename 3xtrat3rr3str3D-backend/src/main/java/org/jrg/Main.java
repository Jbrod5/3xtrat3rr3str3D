package org.jrg;

import org.jrg.controller.archivos.ArchivoController;
import org.jrg.controller.colores.ColoresController;
import org.jrg.controller.compilador.CompiladorController;
import org.jrg.service.archivos.GestorArchivosService;
import org.jrg.service.colores.ColoresService;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import org.jrg.service.compiler.CompiladorPigLatinService;
import org.jrg.service.compiler.CompiladorYLenguajeService;
import org.jrg.service.compiler.CompiladorZetarianoService;

public class Main {

    // iniciar el servidor web con javalin
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(rule -> {
                    rule.anyHost();
                });
            });
            // servir el frontend compilado desde la raiz
            config.staticFiles.add("/public", Location.CLASSPATH);
        }).start(7070);




        // instanciar los servicios del sistema - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        GestorArchivosService gestorArchivosService = new GestorArchivosService();
        ColoresService coloresService = new ColoresService();


        CompiladorPigLatinService compiladorPigLatin = new CompiladorPigLatinService();
        CompiladorZetarianoService compiladorZetariano = new CompiladorZetarianoService();
        // instanciar el servicio del lenguaje Y
        CompiladorYLenguajeService compiladorY = new CompiladorYLenguajeService();

        // instanciar los controladores con sus servicios - - - - - - - - - - - - - - - - - - - - - - - - - -
        ArchivoController archivoController = new ArchivoController(gestorArchivosService);
        ColoresController coloresController = new ColoresController(coloresService);


        CompiladorController compiladorController = new CompiladorController(compiladorPigLatin, compiladorZetariano, compiladorY);




        // registrar endpoints - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        // para archivos
        app.post("/api/archivos/guardar", archivoController::guardar);
        app.post("/api/archivos/leer", archivoController::leer);
        app.post("/api/archivos/listar", archivoController::listar);
        app.post("/api/archivos/proyecto", archivoController::cargarProyecto);

        // para colores
        app.post("/colores", coloresController::obtenerColores);

        // para compilar zetariano, pig e y
        app.post("/analizar", compiladorController::analizar);
        app.post("/traducir", compiladorController::traducir);
        app.post("/api/compilar", compiladorController::compilar);


        // registrar ruta raiz de verificacion
        // app.get("/", ctx -> ctx.result("backend activo :D"));
        // version nueva: la raiz sirve el index del frontend compilado :D
    }
}