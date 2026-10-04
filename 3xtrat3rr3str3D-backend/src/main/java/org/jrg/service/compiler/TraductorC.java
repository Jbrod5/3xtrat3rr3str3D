package org.jrg.service.compiler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;

// armar la traduccion de cuartetas a maquina Heap Stack
public class TraductorC {

    private ContextoTraduccion ctx;
    private StringBuilder cuerpo;
    private List<String> prototipos;
    private List<Cuarteta> preMain;
    private String funcionActual;
    private List<Cuarteta> crudasFuncion;
    private List<String> nombresParams;
    private List<String> tiposParams;
    private boolean preMainColocado;
    private boolean hayMain;

    /**
     * Crear un traductor de cuartetas a maquina Heap Stack.
     */
    public TraductorC() {

        // iniciar sin contexto hasta traducir
        this.ctx = null;

    }

    /**
     * Traducir una lista de cuartetas a maquina Heap Stack.
     */
    public String traducir(List<Cuarteta> cuartetas) {

        // crear un contexto fresco para esta traduccion
        this.ctx = new ContextoTraduccion();

        // devolver un programa minimo si no hay cuartetas
        if (cuartetas == null || cuartetas.isEmpty()) {

            // return encabezado() + "int main(void) {\n    stackpointer = 0;\n    heappointer = 0;\n    framepointer = 0;\n    return 0;\n}\n";
            // reservar la base cero del heap para el nulo
            return encabezado() + "int main(void) {\n    stackpointer = 0;\n    heappointer = 1;\n    framepointer = 0;\n    return 0;\n}\n";

        }

        // preprocesar definiciones de structs para offsets
        ctx.preprocesarStructs(cuartetas);

        // prepasar funciones conocidas y retornos para llamadas adelantadas
        for (int i = 0; i < cuartetas.size(); i++) {

            Cuarteta c = cuartetas.get(i);

            // omitir cuartetas nulas
            if (c == null) {
                continue;
            }

            // registrar cada inicio de funcion
            if ("func_begin".equals(c.getOperador())) {

                if (c.getArg1() != null && c.getArg1().isEmpty() == false) {

                    if (ctx.funcionesConocidas.contains(c.getArg1()) == false) {
                        ctx.funcionesConocidas.add(c.getArg1());
                    }

                    ctx.retornosFuncion.put(c.getArg1(), c.getResultado());

                }

            }

        }

        // iniciar los acumuladores
        this.cuerpo = new StringBuilder();
        this.prototipos = new ArrayList<>();
        this.preMain = new ArrayList<>();
        this.funcionActual = "";
        this.crudasFuncion = new ArrayList<>();
        this.nombresParams = new ArrayList<>();
        this.tiposParams = new ArrayList<>();
        this.preMainColocado = false;

        // detectar si el programa trae main para las globales
        this.hayMain = false;
        for (int i = 0; i < cuartetas.size(); i++) {

            Cuarteta c = cuartetas.get(i);
            if (c != null && "func_begin".equals(c.getOperador()) && "main".equals(c.getArg1())) {
                this.hayMain = true;
            }

        }

        // recorrer cada cuarteta de la lista
        for (int i = 0; i < cuartetas.size(); i++) {

            Cuarteta c = cuartetas.get(i);

            // omitir cuartetas nulas
            if (c == null) {
                continue;
            }

            String operador = c.getOperador();

            // omitir definiciones de structs ya procesadas
            if ("struct_def".equals(operador)) {
                continue;
            }

            // procesar marcadores de funcion por separado
            if ("func_begin".equals(operador)) {
                iniciarFuncion(c);
                continue;
            }

            if ("func_end".equals(operador)) {
                terminarFuncion();
                continue;
            }

            // guardar previas fuera de funciones para la siguiente
            if (this.funcionActual.isEmpty()) {
                this.preMain.add(c);
                continue;
            }

            // guardar la cuarteta cruda para traducir al cerrar
            this.crudasFuncion.add(c);

        }

        // cerrar la funcion si falto su marcador de fin
        if (this.funcionActual.isEmpty() == false) {
            terminarFuncion();
        }

        // ensamblar el programa completo
        return ensamblar();

    }


    // preparar la funcion con sus params
    private void iniciarFuncion(Cuarteta c) {

        // guardar el nombre de la funcion actual
        this.funcionActual = c.getArg1();

        // espejar el nombre en el contexto para retornos y llamadas
        ctx.funcionActual = this.funcionActual;

        // recordar retorno y nombre para llamadas futuras
        if (this.funcionActual != null && this.funcionActual.isEmpty() == false) {

            ctx.retornosFuncion.put(this.funcionActual, c.getResultado());
            if (ctx.funcionesConocidas.contains(this.funcionActual) == false) {
                ctx.funcionesConocidas.add(this.funcionActual);
            }

        }

        // detectar la clase desde el prefijo con struct propio
        ctx.nombreClaseActual = "";
        if (this.funcionActual != null) {

            int guion = this.funcionActual.indexOf('_');
            if (guion > 0) {

                String posible = this.funcionActual.substring(0, guion);
                if (ctx.structs.containsKey(posible)) {
                    ctx.nombreClaseActual = posible;
                }

            }

        }

        // limpiar params anteriores
        this.nombresParams = new ArrayList<>();
        this.tiposParams = new ArrayList<>();
        ctx.tiposDeParams.clear();

        // agregar this primero cuando es metodo de clase
        if (ctx.nombreClaseActual.isEmpty() == false) {
            this.nombresParams.add("this");
            this.tiposParams.add(ctx.nombreClaseActual);
            ctx.tiposDeParams.put("this", ctx.nombreClaseActual);
        }

        // partir los params por coma cuando existan
        String texto = c.getArg2();
        if (texto != null && texto.equals("_") == false && texto.isEmpty() == false) {

            String[] partes = texto.split(",");
            for (int i = 0; i < partes.length; i++) {

                String parte = partes[i].trim();
                int dosPuntos = parte.indexOf(':');

                // usar formato nombre tipo cuando trae dos puntos
                if (dosPuntos > 0) {
                    this.nombresParams.add(parte.substring(0, dosPuntos).trim());
                    this.tiposParams.add(parte.substring(dosPuntos + 1).trim());
                    ctx.tiposDeParams.put(parte.substring(0, dosPuntos).trim(), parte.substring(dosPuntos + 1).trim());
                } else {

                    // numerar cuando solo trae tipos
                    this.nombresParams.add("p" + (i + 1));
                    this.tiposParams.add(parte);
                    ctx.tiposDeParams.put("p" + (i + 1), parte);

                }

            }

        }

        // limpiar las crudas de la funcion anterior
        this.crudasFuncion = new ArrayList<>();

        // agregar el prototipo si no existe
        String firma = firma(this.funcionActual);
        if (this.prototipos.contains(firma) == false) {
            this.prototipos.add(firma);
        }

    }

    // construir la firma void con el nombre real
    private String firma(String nombre) {

        // forzar int en main aunque el marcador diga void
        if ("main".equals(nombre)) {
            return "int main(void)";
        }

        return "void " + nombre + "(void)";

    }

    // cerrar la funcion traduciendo sus crudas al cuerpo
    private void terminarFuncion() {

        // registrar los params en los slots de entrada
        ctx.iniciarFuncion(this.nombresParams.size());
        for (int i = 0; i < this.nombresParams.size(); i++) {
            ctx.registrarParam(this.nombresParams.get(i), this.tiposParams.get(i));
        }

        // juntar previas globales al main o a la primera funcion
        List<Cuarteta> todas = new ArrayList<>();
        boolean esMain = "main".equals(this.funcionActual);
        if (esMain || (this.hayMain == false && this.preMainColocado == false)) {

            todas.addAll(this.preMain);
            this.preMain = new ArrayList<>();
            this.preMainColocado = true;

        }

        todas.addAll(this.crudasFuncion);

        // precontar locales para reservar pila en el prologo
        precontarLocales(todas);

        // abrir la funcion con su marco
        this.cuerpo.append(firma(this.funcionActual)).append(" {\n");

        // iniciar punteros solo una vez en main
        if ("main".equals(this.funcionActual)) {
            // reservar la base cero del heap para el nulo
            this.cuerpo.append("    stackpointer = 0;\n    heappointer = 1;\n    framepointer = 0;\n    framestackpointer = 0;\n");
        }

        // armar el prologo con reserva de locales
        this.cuerpo.append("    framestack[framestackpointer] = framepointer;\n    framestackpointer = framestackpointer + 1;\n");
        this.cuerpo.append("    framepointer = stackpointer - ").append(String.valueOf(this.nombresParams.size())).append(";\n");
        this.cuerpo.append("    stackpointer = stackpointer + ").append(String.valueOf(ctx.cuentaLocales)).append(";\n");

        // traducir cada cruda en orden
        for (int i = 0; i < todas.size(); i++) {

            // omitir cuartetas nulas
            if (todas.get(i) == null) {
                continue;
            }

            // pedir el codigo a la cuarteta tipada
            Cuarteta cuarteta = todas.get(i);
            String linea = cuarteta.obtenerCodigoC(ctx);

            // omitir lineas vacias
            if (linea == null || linea.isEmpty()) {
                continue;
            }

            // agregar labels sin indentacion al cuerpo
            if (linea.endsWith(":;")) {
                this.cuerpo.append(linea).append("\n");
            } else {
                this.cuerpo.append("    ").append(linea).append("\n");
            }

        }

        // cerrar con epilogo que limpia el marco
        if ("main".equals(this.funcionActual)) {
            this.cuerpo.append("    stackpointer = framepointer;\n    framestackpointer = framestackpointer - 1;\n    framepointer = framestack[framestackpointer];\n    return 0;\n}");
        } else {

            this.cuerpo.append("    stackpointer = framepointer;\n    framestackpointer = framestackpointer - 1;\n    framepointer = framestack[framestackpointer];\n    return;\n}");
            

        }

        // separar funciones con linea en blanco
        this.cuerpo.append("\n");

        // limpiar la funcion actual
        this.funcionActual = "";
        this.crudasFuncion = new ArrayList<>();

    }

    // contar locales y temporales antes de reservar
    private void precontarLocales(List<Cuarteta> crudas) {

        // recorrer cada cuarteta buscando destinos con tipo
        for (int i = 0; i < crudas.size(); i++) {

            Cuarteta c = crudas.get(i);
            if (c == null) {
                continue;
            }

            // omitir marcadores sin destino real
            String op = c.getOperador();
            if ("func_begin".equals(op) || "func_end".equals(op) || "struct_def".equals(op)) {
                continue;
            }

            // registrar el destino con su tipo
            String tipo = c.getTipoResultado();
            if ("read".equals(op)) {
                // respetar el tipo que trae la lectura y caer a entero
                if (tipo == null || tipo.isEmpty() || tipo.equals("_")) {
                    tipo = "entero";
                }
            }

            // en copias el resultado hereda el tipo del origen
            if (("=".equals(op) || ":=".equals(op)) && (tipo == null || tipo.isEmpty() || tipo.equals("_"))) {
                tipo = c.getTipoArg1();
            }

            // en alloc la base siempre es entera y el elemento va al mapa
            if ("alloc".equals(op)) {

                // resolver el tipo elemento del primer argumento
                String elemento = c.getArg1();
                if (elemento == null || elemento.isEmpty() || elemento.equals("_")) {
                    elemento = c.getTipoArg1();
                }

                // guardar el elemento para accesos con tipo
                if (c.getResultado() != null && c.getResultado().isEmpty() == false && c.getResultado().equals("_") == false) {
                    if (elemento != null && elemento.isEmpty() == false && elemento.equals("_") == false) {
                        ctx.mapaBases.put(c.getResultado(), elemento);
                    }
                }

                tipo = "entero";

            }

            registrarNombre(c.getResultado(), tipo);

        }

    }

    // registrar un nombre si parece variable o temporal
    private void registrarNombre(String nombre, String tipo) {

        // omitir nulos vacios y guiones
        if (nombre == null || nombre.isEmpty() || nombre.equals("_")) {
            return;
        }

        // omitir etiquetas y numeros
        if (ctx.esEtiqueta(nombre)) {
            return;
        }

        if (nombre.length() > 0 && Character.isDigit(nombre.charAt(0))) {
            return;
        }

        // omitir cadenas y booleanos literales
        if (nombre.startsWith("\"") || nombre.startsWith("'")) {
            return;
        }

        if (nombre.equals("verum") || nombre.equals("verdadero") || nombre.equals("true") || nombre.equals("falsus") || nombre.equals("falso") || nombre.equals("false")) {
            return;
        }

        // omitir nulos
        if (nombre.equals("null") || nombre.equals("NULL")) {
            return;
        }

        // omitir params y repetidos guardando el orden
        if (ctx.slots.containsKey(nombre)) {
            return;
        }

        if (ctx.ordenLocales.contains(nombre)) {
            return;
        }

        // omitir campos de la clase actual que van al heap
        if (ctx.nombreClaseActual != null && ctx.nombreClaseActual.isEmpty() == false) {

            Map<String, String> campos = ctx.structs.get(ctx.nombreClaseActual);
            if (campos != null && campos.containsKey(nombre)) {
                return;
            }

        }

        // anotar el nombre para declarar despues
        ctx.ordenLocales.add(nombre);
        if (tipo == null) {
            tipo = "_";
        }

        // declarar el slot con su tipo
        ctx.declararSlot(nombre, tipo);

    }

    // crear el encabezado con registros y arreglos
    private String encabezado() {

        // acumular los includes y el estado global
        StringBuilder sb = new StringBuilder();
        sb.append("#include <stdio.h>\n");
        sb.append("#include <stdlib.h>\n");
        sb.append("#include <string.h>\n");
        sb.append("#include <stdbool.h>\n");
        sb.append("int stackpointer = 0;\n");
        sb.append("int heappointer = 0;\n");
        sb.append("int framepointer = 0;\n");
        sb.append("int framestack[65536];\n");
        sb.append("int framestackpointer = 0;\n");
        sb.append("int stackinteger[65536];\n");
        sb.append("char *stackstring[65536];\n");
        sb.append("float stackfloat[65536];\n");
        sb.append("char stackchar[65536];\n");
        sb.append("int stackboolean[65536];\n");
        sb.append("int heapinteger[65536];\n");
        sb.append("char *heapstring[65536];\n");
        sb.append("float heapfloat[65536];\n");
        sb.append("char heapchar[65536];\n");
        sb.append("int heapboolean[65536];\n");
        sb.append("int AX_INT, BX_INT, CX_INT;\n");
        sb.append("char *AX_STRING, *BX_STRING, *CX_STRING;\n");
        sb.append("float AX_FLOAT, BX_FLOAT, CX_FLOAT;\n");
        sb.append("char AX_CHAR, BX_CHAR, CX_CHAR;\n");
        sb.append("int AX_BOOLEAN, BX_BOOLEAN, CX_BOOLEAN;\n");
        return sb.toString();

    }

    // ensamblar tabla de cadenas mas prototipos mas cuerpo
    private String ensamblar() {

        // acumular la salida completa
        StringBuilder salida = new StringBuilder();
        salida.append(encabezado());

        // agregar la tabla de cadenas
        for (Map.Entry<String, String> entrada : ctx.tablaCadenas.entrySet()) {
            salida.append("char *").append(entrada.getValue()).append(" = ").append(entrada.getKey()).append(";\n");
        }

        // agregar el leer si se uso
        if (ctx.builtinsUsados.contains("leer")) {

            // abrir la funcion y reservar el buffer estatico
            salida.append("char* leer(void) { ");
            salida.append("static char buf[256]; ");

            // declarar el caracter en curso
            salida.append("int c; ");

            // saltar blancos pendientes de lecturas previas
            salida.append("while ((c = getchar()) == '\\n' || c == '\\r'); ");

            // devolver vacio si se acabo la entrada
            salida.append("if (c == EOF) { buf[0] = '\\0'; } ");

            // devolver el caracter y leer la linea completa
            salida.append("else { ungetc(c, stdin); if (!fgets(buf, sizeof(buf), stdin)) buf[0] = '\\0'; } ");

            // quitar el salto final de la linea leida
            salida.append("buf[strcspn(buf, \"\\r\\n\")] = '\\0'; ");

            // copiar el buffer a memoria nueva para devolver
            salida.append("char* s = malloc(strlen(buf) + 1); ");
            salida.append("strcpy(s, buf); ");
            salida.append("return s; ");

            // cerrar la funcion del builtin
            salida.append("}\n");

        }

        // separar la cabecera del cuerpo con linea en blanco
        if (ctx.tablaCadenas.isEmpty() == false || this.prototipos.isEmpty() == false) {
            salida.append("\n");
        }

        // agregar los prototipos
        for (int i = 0; i < this.prototipos.size(); i++) {
            salida.append(this.prototipos.get(i)).append(";\n");
        }

        // separar prototipos del cuerpo con linea en blanco
        if (this.prototipos.isEmpty() == false) {
            salida.append("\n");
        }

        // agregar el cuerpo de las funciones
        salida.append(this.cuerpo.toString());
        return salida.toString();

    }

}
