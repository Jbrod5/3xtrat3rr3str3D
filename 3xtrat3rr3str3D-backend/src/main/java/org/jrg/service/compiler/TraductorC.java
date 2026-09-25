package org.jrg.service.compiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.jrg.model.resultado.CuartetaResultado;
import org.jrg.service.compiler.cuartetaC.ContextoTraduccion;
import org.jrg.service.compiler.cuartetaC.CuartetaC;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaAccesoArreglo;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaAccesoMiembro;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaAlloc;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaAritmetica;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaAsignacionArreglo;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaAsignacionLiteral;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaAsignacionMiembro;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaAsignacionSimple;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaComparacion;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaConcatenacion;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaEtiqueta;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaFuncBegin;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaFuncEnd;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaGoto;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaHalt;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaIfFalse;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaLlamada;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaLogica;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaNegacion;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaNew;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaParametro;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaPrint;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaRead;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaRetorno;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaStructDef;
import org.jrg.service.compiler.cuartetaC.impl.CuartetaUminus;

// orquestar la traduccion de cuartetas de codigo de tres direcciones a codigo C
public class TraductorC {

    // estado compartido de la traduccion en curso
    private ContextoTraduccion ctx;

    /**
     * Crear un traductor de cuartetas a codigo C.
     */
    public TraductorC() {
        // iniciar sin contexto hasta traducir
        this.ctx = null;
    }

    /**
     * Traducir una lista de cuartetas a codigo C.
     */
    public String traducir(List<CuartetaResultado> cuartetas) {
        // crear un contexto fresco para esta traduccion
        this.ctx = new ContextoTraduccion();
        // devolver un programa minimo si no hay cuartetas
        if (cuartetas == null || cuartetas.isEmpty()) {
            return encabezado() + "int main(void) {\n    return 0;\n}\n";
        }
        // preprocesar definiciones de structs y sus temporales
        ctx.preprocesarStructs(cuartetas);
        // detectar clases de Zetariano por prefijo en funciones
        ctx.detectarClases(cuartetas);
        // acumular el cuerpo de las funciones
        StringBuilder cuerpo = new StringBuilder();
        // recorrer cada cuarteta de la lista
        for (int i = 0; i < cuartetas.size(); i++) {
            CuartetaResultado c = cuartetas.get(i);
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
                terminarFuncion(cuerpo);
                continue;
            }
            // registrar globales fuera de funciones
            if (ctx.dentroDeFuncion == false) {
                procesarLineaGlobal(c);
                continue;
            }
            // guardar la cuarteta cruda para traducir al cerrar
            ctx.crudasFuncion.add(c);
        }
        // cerrar la funcion si falto su marcador de fin
        if (ctx.bufferFuncion != null) {
            terminarFuncion(cuerpo);
        }
        // ensamblar el programa completo
        return ensamblar(cuerpo.toString());
    }

    // crear la cuarteta concreta segun el operador con polimorfismo :D
    private CuartetaC crearCuarteta(CuartetaResultado c) {
        // extraer el operador y los campos de la cuarteta
        String op = c.getOperador();
        String a1 = c.getArg1();
        String a2 = c.getArg2();
        String res = c.getResultado();
        String t1 = c.getTipoArg1();
        String t2 = c.getTipoArg2();
        String tr = c.getTipoResultado();
        // acumular params sin generar linea
        if ("param".equals(op)) {
            return new CuartetaParametro(op, a1, a2, res, t1, t2, tr);
        }
        // delegar llamadas a funcion y a metodo
        if ("call".equals(op) || "call_method".equals(op)) {
            return new CuartetaLlamada(op, a1, a2, res, t1, t2, tr);
        }
        // delegar carga de literal
        if ("=".equals(op)) {
            return new CuartetaAsignacionLiteral(op, a1, a2, res, t1, t2, tr);
        }
        // delegar asignacion simple
        if (":=".equals(op)) {
            return new CuartetaAsignacionSimple(op, a1, a2, res, t1, t2, tr);
        }
        // delegar suma segun sea texto o numero
        if ("+".equals(op)) {
            if (ctx.esTipoTexto(tr)) {
                return new CuartetaConcatenacion(op, a1, a2, res, t1, t2, tr);
            }
            return new CuartetaAritmetica(op, a1, a2, res, t1, t2, tr);
        }
        // delegar resto de operaciones aritmeticas
        if ("-".equals(op) || "*".equals(op) || "/".equals(op) || "%".equals(op)) {
            return new CuartetaAritmetica(op, a1, a2, res, t1, t2, tr);
        }
        // delegar comparaciones
        if ("==".equals(op) || "!=".equals(op) || "<".equals(op) || ">".equals(op) || "<=".equals(op) || ">=".equals(op)) {
            return new CuartetaComparacion(op, a1, a2, res, t1, t2, tr);
        }
        // delegar operadores logicos binarios
        if ("&&".equals(op) || "||".equals(op)) {
            return new CuartetaLogica(op, a1, a2, res, t1, t2, tr);
        }
        // delegar negacion logica
        if ("!".equals(op)) {
            return new CuartetaNegacion(op, a1, a2, res, t1, t2, tr);
        }
        // delegar menos unario con opcode propio
        if ("uminus".equals(op)) {
            return new CuartetaUminus(op, a1, a2, res, t1, t2, tr);
        }
        // delegar etiquetas y saltos
        if ("label".equals(op)) {
            return new CuartetaEtiqueta(op, a1, a2, res, t1, t2, tr);
        }
        if ("goto".equals(op)) {
            return new CuartetaGoto(op, a1, a2, res, t1, t2, tr);
        }
        if ("if_false".equals(op)) {
            return new CuartetaIfFalse(op, a1, a2, res, t1, t2, tr);
        }
        // delegar retorno
        if ("return".equals(op)) {
            return new CuartetaRetorno(op, a1, a2, res, t1, t2, tr);
        }
        // delegar impresion
        if ("print".equals(op)) {
            return new CuartetaPrint(op, a1, a2, res, t1, t2, tr);
        }
        // delegar lectura
        if ("read".equals(op)) {
            return new CuartetaRead(op, a1, a2, res, t1, t2, tr);
        }
        // delegar acceso a miembro
        if (".".equals(op)) {
            return new CuartetaAccesoMiembro(op, a1, a2, res, t1, t2, tr);
        }
        // delegar asignacion a miembro
        if (".,=".equals(op)) {
            return new CuartetaAsignacionMiembro(op, a1, a2, res, t1, t2, tr);
        }
        // delegar acceso a arreglo
        if ("=[]".equals(op)) {
            return new CuartetaAccesoArreglo(op, a1, a2, res, t1, t2, tr);
        }
        // delegar asignacion a arreglo
        if ("[]=".equals(op)) {
            return new CuartetaAsignacionArreglo(op, a1, a2, res, t1, t2, tr);
        }
        // delegar reserva de memoria
        if ("alloc".equals(op)) {
            return new CuartetaAlloc(op, a1, a2, res, t1, t2, tr);
        }
        // delegar instanciacion de objeto y de struct
        if ("new".equals(op) || "new_struct".equals(op)) {
            return new CuartetaNew(op, a1, a2, res, t1, t2, tr);
        }
        // delegar halt
        if ("halt".equals(op)) {
            return new CuartetaHalt(op, a1, a2, res, t1, t2, tr);
        }
        // delegar marcadores que gestiona el orquestador
        if ("func_begin".equals(op)) {
            return new CuartetaFuncBegin(op, a1, a2, res, t1, t2, tr);
        }
        if ("func_end".equals(op)) {
            return new CuartetaFuncEnd(op, a1, a2, res, t1, t2, tr);
        }
        if ("struct_def".equals(op)) {
            return new CuartetaStructDef(op, a1, a2, res, t1, t2, tr);
        }
        // devolver nulo para operadores desconocidos
        return null;
    }

    // generar los includes del programa
    private String encabezado() {
        // concatenar los includes necesarios
        StringBuilder sb = new StringBuilder();
        sb.append("#include <stdio.h>\n");
        sb.append("#include <stdlib.h>\n");
        sb.append("#include <string.h>\n");
        sb.append("#include <stdbool.h>\n");
        return sb.toString();
    }

    // ensamblar includes mas builtins mas prototipos mas globales mas cuerpo
    private String ensamblar(String cuerpo) {
        // crear el acumulador de salida
        StringBuilder salida = new StringBuilder();
        // agregar los includes
        salida.append(encabezado());
        // agregar las definiciones de builtins usados
        for (int i = 0; i < ctx.builtinsUsados.size(); i++) {
            salida.append(ctx.definicionBuiltin(ctx.builtinsUsados.get(i)));
        }
        // agregar las definiciones de structs
        for (int i = 0; i < ctx.ordenStructs.size(); i++) {
            String nombreStruct = ctx.ordenStructs.get(i);
            salida.append("struct ").append(nombreStruct).append(" {\n");
            // agregar cada campo en su orden a la salida
            List<String> orden = ctx.ordenCampos.get(nombreStruct);
            if (orden != null) {
                Map<String, String> campos = ctx.structs.get(nombreStruct);
                for (int j = 0; j < orden.size(); j++) {
                    String campo = orden.get(j);
                    String tipoC = ctx.mapearTipo(campos.get(campo));
                    // agregar arreglos con corchetes al final a la salida
                    if (tipoC.endsWith("[]")) {
                        String base = tipoC.substring(0, tipoC.length() - 2);
                        salida.append("    ").append(base).append(" ").append(campo).append("[];\n");
                    } else {
                        salida.append("    ").append(tipoC).append(" ").append(campo).append(";\n");
                    }
                }
            }
            salida.append("};\n");
        }
        // agregar los prototipos de funciones
        for (int i = 0; i < ctx.prototipos.size(); i++) {
            salida.append(ctx.prototipos.get(i)).append(";\n");
        }
        // agregar las variables globales
        for (Map.Entry<String, String> entrada : ctx.globales.entrySet()) {
            salida.append(entrada.getValue()).append(" ").append(entrada.getKey()).append(";\n");
        }
        // separar la cabecera del cuerpo con linea en blanco
        if (ctx.builtinsUsados.isEmpty() == false || ctx.ordenStructs.isEmpty() == false || ctx.prototipos.isEmpty() == false || ctx.globales.isEmpty() == false) {
            salida.append("\n");
        }
        // agregar el cuerpo de las funciones
        salida.append(cuerpo);
        return salida.toString();
    }

    // iniciar el buffer de una funcion sin agregar la firma aun
    private void iniciarFuncion(CuartetaResultado c) {
        // marcar que ya se entro a una funcion
        ctx.dentroDeFuncion = true;
        // guardar el nombre de la funcion actual
        ctx.funcionActual = c.getArg1();
        // detectar la clase actual desde el prefijo del nombre
        String claseDetectada = ctx.claseDeFuncion(c.getArg1());
        ctx.nombreClaseActual = "";
        if (claseDetectada != null) {
            ctx.nombreClaseActual = claseDetectada;
        }
        // guardar los tipos y el retorno del marcador
        ctx.tiposParamsFuncion = c.getArg2();
        ctx.tipoRetornoFuncion = c.getResultado();
        // registrar el retorno conocido por nombre
        if (c.getArg1() != null) {
            ctx.retornosFuncion.put(c.getArg1(), c.getResultado());
        }
        // reiniciar las declaraciones con las globales
        ctx.declaradas = new HashSet<>();
        for (String global : ctx.globales.keySet()) {
            ctx.declaradas.add(global);
        }
        // reiniciar los tipos conocidos con las globales
        ctx.tiposDeclarados = new HashMap<>();
        for (Map.Entry<String, String> entrada : ctx.globales.entrySet()) {
            ctx.tiposDeclarados.put(entrada.getKey(), entrada.getValue());
        }
        // reiniciar el mapa de structs por ambito de funcion
        ctx.structDeNombre = new HashMap<>();
        // reiniciar los punteros locales de la funcion
        ctx.punteros.clear();
        // conservar los params pendientes porque preMain los necesita
        // this.paramsPendientes = new ArrayList<>();
        // this.tiposParamsPendientes = new ArrayList<>();
        // crear el buffer del cuerpo y las listas de apoyo
        ctx.bufferFuncion = new StringBuilder();
        ctx.crudasFuncion = new ArrayList<>();
        ctx.llamadasFuncion = new HashSet<>();
        ctx.tiposParamsActuales = new HashMap<>();
    }

    // cerrar la funcion agregando la firma con nombres reales
    private void terminarFuncion(StringBuilder cuerpo) {
        // recolectar los nombres llamados en el cuerpo
        for (int i = 0; i < ctx.crudasFuncion.size(); i++) {
            CuartetaResultado c = ctx.crudasFuncion.get(i);
            // omitir cuartetas nulas
            if (c == null) {
                continue;
            }
            // guardar nombres de funciones y metodos llamados
            if ("call".equals(c.getOperador()) || "call_method".equals(c.getOperador())) {
                if (c.getArg1() != null) {
                    ctx.llamadasFuncion.add(c.getArg1());
                }
            }
        }
        // recolectar identificadores candidatos en orden de aparicion
        List<String> identificadores = new ArrayList<>();
        for (int i = 0; i < ctx.crudasFuncion.size(); i++) {
            ctx.recolectarIdentificadores(ctx.crudasFuncion.get(i), identificadores);
        }
        // contar los parametros desde los tipos del marcador
        int cantidadParams = ctx.contarParametros(ctx.tiposParamsFuncion);
        // construir nombres y tipos de params en orden
        List<String> nombresParams = new ArrayList<>();
        List<String> tiposParamsC = new ArrayList<>();
        // usar formato nuevo con nombres cuando trae dos puntos
        if (ctx.contieneNombres(ctx.tiposParamsFuncion)) {
            ctx.parsearParamsConNombres(ctx.tiposParamsFuncion, nombresParams, tiposParamsC);
        } else {
            // dividir los tipos por coma cuando existan
            String[] partesTipos = ctx.dividirTipos(ctx.tiposParamsFuncion);
            for (int i = 0; i < cantidadParams; i++) {
                // extraer el tipo del parametro actual
                String tipoParam = "int";
                if (i < partesTipos.length) {
                    tipoParam = ctx.mapearTipo(partesTipos[i].trim());
                }
                // usar el identificador real si se logro recoger
                String nombreParam = "p" + (i + 1);
                if (i < identificadores.size()) {
                    nombreParam = identificadores.get(i);
                }
                nombresParams.add(nombreParam);
                tiposParamsC.add(tipoParam);
            }
        }
        // guardar los tipos por nombre para los builtins
        for (int i = 0; i < nombresParams.size(); i++) {
            ctx.tiposParamsActuales.put(nombresParams.get(i), tiposParamsC.get(i));
        }
        // agregar this como primer parametro en metodos de clase
        String claseMetodo = ctx.claseDeFuncion(ctx.funcionActual);
        if (claseMetodo != null) {
            nombresParams.add(0, "this");
            tiposParamsC.add(0, "struct " + claseMetodo + "*");
            ctx.tiposParamsActuales.put("this", "struct " + claseMetodo + "*");
        }
        // marcar los params como declarados para no redeclararlos
        for (int i = 0; i < nombresParams.size(); i++) {
            ctx.declaradas.add(nombresParams.get(i));
            ctx.tiposDeclarados.put(nombresParams.get(i), tiposParamsC.get(i));
        }
        // construir la firma con los nombres reales
        String firma = ctx.firmaConListas(ctx.funcionActual, ctx.tipoRetornoFuncion, nombresParams, tiposParamsC);
        // separar funciones con linea en blanco
        if (ctx.primeraFuncionAgregada) {
            cuerpo.append("\n");
        }
        ctx.primeraFuncionAgregada = true;
        // agregar el prototipo si no existe
        if (ctx.prototipos.contains(firma) == false) {
            ctx.prototipos.add(firma);
        }
        // agregar la firma con llave de apertura al cuerpo
        cuerpo.append(firma).append(" {\n");
        // agregar las cuartetas previas traducidas al cuerpo
        traducirRaws(ctx.preMain);
        ctx.preMain = new ArrayList<>();
        // limpiar params de preMain para no contaminar el cuerpo
        ctx.limpiarParams();
        traducirRaws(ctx.crudasFuncion);
        // vaciar el cuerpo acumulado
        cuerpo.append(ctx.bufferFuncion.toString());
        // agregar retorno en main
        if ("main".equals(ctx.funcionActual)) {
            cuerpo.append("    return 0;\n}");
        } else {
            cuerpo.append("}");
        }
        // limpiar el estado de la funcion actual
        ctx.funcionActual = "";
        ctx.nombreClaseActual = "";
        ctx.dentroDeFuncion = false;
        ctx.bufferFuncion = null;
        ctx.crudasFuncion = new ArrayList<>();
        ctx.llamadasFuncion = new HashSet<>();
        // agregar salto de linea final
        cuerpo.append("\n");
    }

    // traducir una lista de cuartetas crudas al buffer de la funcion
    private void traducirRaws(List<CuartetaResultado> raws) {
        // recorrer cada cuarteta de la lista
        for (int i = 0; i < raws.size(); i++) {
            CuartetaResultado c = raws.get(i);
            // omitir cuartetas nulas
            if (c == null) {
                continue;
            }
            // crear la cuarteta concreta segun el operador
            CuartetaC cuarteta = crearCuarteta(c);
            // marcar operadores desconocidos con comentario
            String linea = null;
            if (cuarteta != null) {
                linea = cuarteta.obtenerCodigoC(ctx);
            } else {
                linea = "// cuarteta no soportada: " + c.getOperador();
            }
            // omitir lineas vacias como param
            if (linea == null || linea.isEmpty()) {
                continue;
            }
            // agregar labels sin indentacion al buffer
            if (linea.endsWith(":;")) {
                ctx.bufferFuncion.append(linea).append("\n");
            } else {
                ctx.bufferFuncion.append("    ").append(linea).append("\n");
            }
        }
    }

    // procesar una cuarteta fuera de funciones
    private void procesarLineaGlobal(CuartetaResultado c) {
        String operador = c.getOperador();
        // marcar objetos creados con new para el heap
        if ("new".equals(operador)) {
            if (c.getArg1() != null && c.getResultado() != null) {
                if (ctx.clases.contains(c.getArg1()) || ctx.structs.containsKey(c.getArg1())) {
                    ctx.structDeNombre.put(c.getResultado(), c.getArg1());
                    ctx.punteros.add(c.getResultado());
                }
            }
            // guardar la cuarteta para agregarla al cuerpo despues
            ctx.preMain.add(c);
            return;
        }
        // mapear el resultado de new_struct a su struct
        if ("new_struct".equals(operador)) {
            if (c.getArg1() != null && ctx.structs.containsKey(c.getArg1()) && c.getResultado() != null) {
                ctx.structDeNombre.put(c.getResultado(), c.getArg1());
            }
            // guardar la cuarteta para agregarla al cuerpo despues
            ctx.preMain.add(c);
            return;
        }
        // registrar destinos de asignacion como globales
        if (":=".equals(operador)) {
            // propagar el struct del valor al destino
            if (c.getArg1() != null && c.getResultado() != null) {
                String structOrigen = ctx.structDeNombre.get(c.getArg1());
                if (structOrigen != null) {
                    ctx.structDeNombre.put(c.getResultado(), structOrigen);
                }
            }
            String destino = c.getResultado();
            // ignorar destinos temporales o vacios
            if (destino == null || destino.equals("_") || ctx.esTemporal(destino)) {
                // guardar la cuarteta para agregarla al cuerpo despues
                ctx.preMain.add(c);
                return;
            }
            // registrar la global con su tipo si es nueva
            if (ctx.globales.containsKey(destino) == false) {
                ctx.globales.put(destino, ctx.tipoGlobalPara(c.getArg1(), c.getTipoArg1()));
                ctx.declaradas.add(destino);
            }
        }
        // guardar la cuarteta para agregarla al cuerpo en la siguiente funcion
        ctx.preMain.add(c);
        // omitir cualquier agregado directo fuera de funciones
    }
}
