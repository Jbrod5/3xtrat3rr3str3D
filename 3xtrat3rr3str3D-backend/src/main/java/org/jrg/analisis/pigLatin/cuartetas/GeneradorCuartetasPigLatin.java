package org.jrg.analisis.pigLatin.cuartetas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jrg.model.ast.pigLatin.*;
import org.jrg.model.ast.pigLatin.asignacion.AsignacionGeneral;
import org.jrg.model.ast.pigLatin.atributo_instancia.CampoConNombre;
import org.jrg.model.ast.pigLatin.atributo_instancia.CampoPosicional;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;
import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.ciclo.CicloDum;
import org.jrg.model.ast.pigLatin.ciclo.CicloFacere;
import org.jrg.model.ast.pigLatin.ciclo.CicloPer;
import org.jrg.model.ast.pigLatin.condicional.StatementSi;
import org.jrg.model.ast.pigLatin.declaracion_variable.*;
import org.jrg.model.ast.pigLatin.expresion.*;
import org.jrg.model.ast.pigLatin.init_per.InitPerAsig;
import org.jrg.model.ast.pigLatin.init_per.InitPerDecl;
import org.jrg.model.ast.pigLatin.instruccion_flujo.*;
import org.jrg.model.ast.pigLatin.instruccion_impresion.ImpresionEncadenada;
import org.jrg.model.ast.pigLatin.instruccion_lectura.LecturaConsolaAVariable;
import org.jrg.model.ast.pigLatin.instruccion_lectura.LecturaConsolaSimple;
import org.jrg.model.ast.pigLatin.paso_per.PasoPerAsig;
import org.jrg.model.ast.pigLatin.paso_per.PasoPerExpr;
import org.jrg.model.ast.pigLatin.variable_asignable.*;
import org.jrg.model.base.TipoPrimitivo;
import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.model.cuarteta.GeneradorTemporales;

// generador de cuartetas para Pig Latin
public class GeneradorCuartetasPigLatin implements LatinusAstVisitor<String> {

    // lista de cuartetas generadas
    private final List<Cuarteta> cuartetas;
    // generador de temporales y etiquetas
    private final GeneradorTemporales temporales;
    // etiqueta actual para break
    private String etiquetaBreakActual;
    // etiqueta actual para continue
    private String etiquetaContinueActual;
    // tipos conocidos de temporales y variables
    private final Map<String, String> tiposConocidos;
    // nombres de campos por nombre de struct en orden
    private final Map<String, List<String>> camposDeStructs;
    // tipos de variables declaradas por nombre
    private final Map<String, String> tiposDeVariables;

    // crear el generador
    public GeneradorCuartetasPigLatin() {
        // inicializar la lista y el generador
        this.cuartetas = new ArrayList<>();
        this.temporales = new GeneradorTemporales();
        this.etiquetaBreakActual = null;
        this.etiquetaContinueActual = null;
        this.tiposConocidos = new HashMap<>();
        this.camposDeStructs = new HashMap<>();
        this.tiposDeVariables = new HashMap<>();
    }

    // obtener la lista de cuartetas generadas
    public List<Cuarteta> getCuartetas() {
        return cuartetas;
    }

    // registrar los campos de un struct importado en orden
    public void registrarCamposDeStruct(String nombreStruct, List<String> campos) {
        // omitir nombres o listas nulas
        if (nombreStruct == null || campos == null) {
            return;
        }
        // guardar los campos para resolver escrituras por nombre
        this.camposDeStructs.put(nombreStruct, campos);
    }

    // registrar el tipo de una variable declarada
    public void registrarTipoVariable(String nombre, String tipo) {
        // omitir nombres o tipos nulos
        if (nombre == null || tipo == null) {
            return;
        }
        // guardar el tipo para usos posteriores
        this.tiposDeVariables.put(nombre, tipo);
    }

    // inferir el tipo de un literal por su forma
    private String inferirTipoLiteral(String valor) {
        // devolver guion bajo si el valor es nulo o vacio de tipo
        if (valor == null || valor.equals("_")) {
            return "_";
        }
        // detectar cadena por comilla doble inicial
        if (valor.startsWith("\"")) {
            return "cadena";
        }
        // detectar caracter por comilla simple inicial
        if (valor.startsWith("'")) {
            return "caracter";
        }
        // detectar booleanos de los tres lenguajes
        if (valor.equals("verum") || valor.equals("verdadero") || valor.equals("true") || valor.equals("falsus") || valor.equals("falso") || valor.equals("false")) {
            return "booleano";
        }
        // detectar flotante por punto decimal
        if (valor.contains(".")) {
            return "flotante";
        }
        // detectar entero si empieza con digito
        if (valor.length() > 0 && Character.isDigit(valor.charAt(0))) {
            return "entero";
        }
        // cualquier otra cosa es de tipo desconocido
        return "_";
    }

    // inferir el tipo de un nombre usando el mapa o su forma literal
    private String inferirTipoDe(String nombre, Map<String, String> tipos) {
        // devolver guion bajo si el nombre es nulo o vacio de tipo
        if (nombre == null || nombre.equals("_")) {
            return "_";
        }
        // buscar en el mapa de tipos conocidos
        String tipo = tipos.get(nombre);
        if (tipo != null) {
            return tipo;
        }
        // buscar en los tipos de variables declaradas
        String tipoVariable = tiposDeVariables.get(nombre);
        if (tipoVariable != null) {
            return tipoVariable;
        }
        // inferir por la forma del literal
        return inferirTipoLiteral(nombre);
    }

    // verificar si un tipo corresponde a cadena de texto
    private boolean esTipoCadena(String tipo) {
        // comparar contra los nombres de cadena de los tres lenguajes
        if ("cadena".equals(tipo) || "textum".equals(tipo) || "String".equals(tipo)) {
            return true;
        }
        return false;
    }

    // inferir el tipo de un operando aritmetico con entero por defecto
    private String tipoAritmetico(String nombre) {
        // inferir el tipo conocido del operando
        String tipo = inferirTipoDe(nombre, tiposConocidos);
        // usar entero cuando el tipo es desconocido
        if (tipo.equals("_")) {
            return "entero";
        }
        return tipo;
    }

    // inferir el tipo resultado de una operacion aritmetica
    private String tipoResultadoAritmetico(String a, String b) {
        // inferir los tipos de ambos operandos
        String tipoA = inferirTipoDe(a, tiposConocidos);
        String tipoB = inferirTipoDe(b, tiposConocidos);
        // usar el tipo comun cuando ambos coinciden y es conocido
        if (tipoA.equals(tipoB)) {
            if (tipoA.equals("_")) {
                return "entero";
            }
            return tipoA;
        }
        // usar entero por defecto en caso mixto
        return "entero";
    }

    // ==================== PROGRAMA Y SECCIONES ====================

    @Override
    public String visitPrograma(Programa nodo) {
        // visitar seccion de variables globales si existe
        if (nodo.getSeccionGlobalVariables() != null) {
            nodo.getSeccionGlobalVariables().accept(this);
        }

        // emitir marcador de inicio del main
        cuartetas.add(new Cuarteta("func_begin", "main", "_", "void", "_", "_", "void"));

        // visitar seccion maior si existe
        if (nodo.getSeccionMaior() != null) {
            nodo.getSeccionMaior().accept(this);
        }

        // emitir marcador de fin del main
        cuartetas.add(new Cuarteta("func_end", "main", "_", "_", "_", "_", "_"));

        return null;
    }

    @Override
    public String visitSeccionImportaciones(SeccionImportaciones nodo) {
        // no genera cuartetas
        return null;
    }

    @Override
    public String visitRutaImportacion(RutaImportacion nodo) {
        // TODO: implementar si es necesario
        return null;
    }

    @Override
    public String visitSeccionGlobalVariables(SeccionGlobalVariables nodo) {
        // visitar declaraciones si existen
        if (nodo.getDeclaraciones() != null) {
            for (NodoAST decl : nodo.getDeclaraciones()) {
                if (decl != null) {
                    decl.accept(this);
                }
            }
        }
        return null;
    }

    @Override
    public String visitSeccionMaior(SeccionMaior nodo) {
        // visitar instrucciones del bloque principal
        if (nodo.getInstrucciones() != null) {
            for (NodoAST inst : nodo.getInstrucciones()) {
                if (inst != null) {
                    inst.accept(this);
                }
            }
        }
        return null;
    }

    @Override
    public String visitTipoDato(TipoDato nodo) {
        // TODO: implementar
        return null;
    }

    @Override
    public String visitValorPrimitivo(ValorPrimitivo nodo) {
        // TODO: implementar
        return null;
    }

    @Override
    public String visitListaExpresiones(ListaExpresiones nodo) {
        // TODO: implementar
        return null;
    }

    @Override
    public String visitListaAtributosInstancia(ListaAtributosInstancia nodo) {
        // no genera cuarteta por si solo
        return null;
    }

    @Override
    public String visitBloque(Bloque nodo) {
        // visitar instrucciones del bloque
        if (nodo.getInstrucciones() != null) {
            for (NodoAST inst : nodo.getInstrucciones()) {
                if (inst != null) {
                    inst.accept(this);
                }
            }
        }
        return null;
    }

    @Override
    public String visitElementoImprimir(ElementoImprimir nodo) {
        // visitar la expresion interna y devolver su resultado
        return nodo.getExpresion().accept(this);
    }

    // ==================== VARIABLES ASIGNABLES ====================

    @Override
    public String visitValorAsignableSimple(ValorAsignableSimple v) {
        // devolver el identificador directamente
        return v.getIdentificador();
    }

    @Override
    public String visitValorAsignableArray(ValorAsignableArray v) {
        String base = null;
        if (v.getBase() != null) {
            base = v.getBase().accept(this);
        }
        String idx = null;
        if (v.getIndice() != null) {
            idx = v.getIndice().accept(this);
        }
        return (base != null ? base : "_") + "[" + (idx != null ? idx : "_") + "]";
    }

    @Override
    public String visitValorAsignableMiembroEstructura(ValorAsignableMiembroEstructura v) {
        // obtener la base del miembro
        String base = null;
        if (v.getBase() != null) {
            base = v.getBase().accept(this);
        }
        // devolver la referencia compuesta sin emitir cuarteta
        return (base != null ? base : "_") + "." + v.getMiembro();
    }

    // ==================== EXPRESIONES ====================

    @Override
    public String visitExprParentesis(ExprParentesis expr) {
        // visitar la expresion interna
        if (expr.getExpresion() != null) {
            return expr.getExpresion().accept(this);
        }
        return null;
    }

    @Override
    public String visitExprInstanciaObjeto(ExprInstanciaObjeto expr) {
        // evaluar argumentos igual que en llamada a funcion
        List<String> args = new ArrayList<>();
        if (expr.getArgumentos() != null) {
            if (expr.getArgumentos() instanceof ListaExpresiones) {
                List<NodoAST> lista = ((ListaExpresiones) expr.getArgumentos()).getExpresiones();
                for (int i = 0; i < lista.size(); i++) {
                    String a = lista.get(i).accept(this);
                    args.add(a != null ? a : "_");
                }
            }
        }
        // emitir parametros
        for (int i = 0; i < args.size(); i++) {
            cuartetas.add(new Cuarteta("param", args.get(i), "_", "_", inferirTipoDe(args.get(i), tiposConocidos), "_", "_"));
        }
        // emitir instancia
        String temp = temporales.nuevoTemporal();
        // registrar el temporal con el tipo de la clase
        tiposConocidos.put(temp, expr.getTipo());
        cuartetas.add(new Cuarteta("new", expr.getTipo(), String.valueOf(args.size()), temp, expr.getTipo(), "_", expr.getTipo()));
        return temp;
    }

    @Override
    public String visitExprLlamadaFuncion(ExprLlamadaFuncion expr) {
        // evaluar argumentos
        List<String> args = new ArrayList<>();
        if (expr.getArgumentos() != null) {
            if (expr.getArgumentos() instanceof ListaExpresiones) {
                List<NodoAST> lista = ((ListaExpresiones) expr.getArgumentos()).getExpresiones();
                for (int i = 0; i < lista.size(); i++) {
                    String a = lista.get(i).accept(this);
                    args.add(a != null ? a : "_");
                }
            }
        }
        // emitir parametros
        for (int i = 0; i < args.size(); i++) {
            cuartetas.add(new Cuarteta("param", args.get(i), "_", "_", inferirTipoDe(args.get(i), tiposConocidos), "_", "_"));
        }
        // emitir llamada
        String temp = temporales.nuevoTemporal();
        cuartetas.add(new Cuarteta("call", expr.getNombre(), String.valueOf(args.size()), temp, "_", "_", "_"));
        return temp;
    }

    @Override
    public String visitExprLlamadaMetodo(ExprLlamadaMetodo expr) {
        String obj = null;
        if (expr.getObjeto() != null) {
            obj = expr.getObjeto().accept(this);
        }
        // evaluar argumentos
        List<String> args = new ArrayList<>();
        if (expr.getArgumentos() != null) {
            if (expr.getArgumentos() instanceof ListaExpresiones) {
                List<NodoAST> lista = ((ListaExpresiones) expr.getArgumentos()).getExpresiones();
                for (int i = 0; i < lista.size(); i++) {
                    String a = lista.get(i).accept(this);
                    args.add(a != null ? a : "_");
                }
            }
        }
        // emitir parametro del objeto
        cuartetas.add(new Cuarteta("param", obj != null ? obj : "_", "_", "_", inferirTipoDe(obj, tiposConocidos), "_", "_"));
        // emitir parametros de argumentos
        for (int i = 0; i < args.size(); i++) {
            cuartetas.add(new Cuarteta("param", args.get(i), "_", "_", inferirTipoDe(args.get(i), tiposConocidos), "_", "_"));
        }
        // emitir llamada al metodo
        String temp = temporales.nuevoTemporal();
        cuartetas.add(new Cuarteta("call_method", expr.getNombre(), String.valueOf(args.size() + 1), temp, "_", "_", "_"));
        return temp;
    }

    @Override
    public String visitExprAccesoPosicionArray(ExprAccesoPosicionArray expr) {
        // evaluar array e indice
        String arr = null;
        String idx = null;
        if (expr.getArray() != null) {
            arr = expr.getArray().accept(this);
        }
        if (expr.getIndice() != null) {
            idx = expr.getIndice().accept(this);
        }
        // generar acceso a array con temporal
        String temp = temporales.nuevoTemporal();
        cuartetas.add(new Cuarteta("=[]", arr != null ? arr : "_", idx != null ? idx : "_", temp, "_", "entero", "_"));
        return temp;
    }

    @Override
    public String visitExprAccesoMiembroEstructura(ExprAccesoMiembroEstructura expr) {
        // visitar el objeto
        String obj = null;
        if (expr.getObjeto() != null) {
            obj = expr.getObjeto().accept(this);
        }
        // emitir acceso a miembro
        String temp = temporales.nuevoTemporal();
        cuartetas.add(new Cuarteta(".", obj != null ? obj : "_", expr.getMiembro(), temp, "_", "_", "_"));
        return temp;
    }

    @Override
    public String visitExprPostIncremento(ExprPostIncremento expr) {
        String var = expr.getVariable().accept(this);
        cuartetas.add(new Cuarteta("+", var != null ? var : "_", "1", var != null ? var : "_", inferirTipoDe(var, tiposConocidos), "entero", inferirTipoDe(var, tiposConocidos)));
        return var;
    }

    @Override
    public String visitExprPostDecremento(ExprPostDecremento expr) {
        String var = expr.getVariable().accept(this);
        cuartetas.add(new Cuarteta("-", var != null ? var : "_", "1", var != null ? var : "_", inferirTipoDe(var, tiposConocidos), "entero", inferirTipoDe(var, tiposConocidos)));
        return var;
    }

    @Override
    public String visitExprListaLiteral(ExprListaLiteral expr) {
        // no genera cuarteta por si solo en este contexto
        return null;
    }

    @Override
    public String visitExprNegativa(ExprNegativa expr) {
        // visitar expresion
        String val = null;
        if (expr.getExpresion() != null) {
            val = expr.getExpresion().accept(this);
        }
        // crear temporal para el resultado
        String temp = temporales.nuevoTemporal();
        // inferir el tipo desde el operando
        String tipoNeg = inferirTipoDe(val, tiposConocidos);
        // registrar el temporal con el tipo inferido
        tiposConocidos.put(temp, tipoNeg);
        // emitir menos unario con opcode propio
        cuartetas.add(new Cuarteta("uminus", val != null ? val : "_", "_", temp, tipoNeg, "_", tipoNeg));
        return temp;
    }

    @Override
    public String visitExprNegada(ExprNegada expr) {
        // visitar expresion
        String val = null;
        if (expr.getExpresion() != null) {
            val = expr.getExpresion().accept(this);
        }
        String op = expr.getOperador();
        String temp = temporales.nuevoTemporal();
        // registrar el temporal como booleano
        tiposConocidos.put(temp, "booleano");
        cuartetas.add(new Cuarteta("!", val != null ? val : "_", "_", temp, inferirTipoDe(val, tiposConocidos), "_", "booleano"));
        return temp;
    }

    @Override
    public String visitExprPreIncremento(ExprPreIncremento expr) {
        String var = expr.getVariable().accept(this);
        cuartetas.add(new Cuarteta("+", var != null ? var : "_", "1", var != null ? var : "_", inferirTipoDe(var, tiposConocidos), "entero", inferirTipoDe(var, tiposConocidos)));
        return var;
    }

    @Override
    public String visitExprPreDecremento(ExprPreDecremento expr) {
        String var = expr.getVariable().accept(this);
        cuartetas.add(new Cuarteta("-", var != null ? var : "_", "1", var != null ? var : "_", inferirTipoDe(var, tiposConocidos), "entero", inferirTipoDe(var, tiposConocidos)));
        return var;
    }

    @Override
    public String visitExprMultiplicacionDivision(ExprMultiplicacionDivision expr) {
        // evaluar operandos
        String izq = null;
        String der = null;
        if (expr.getOperandoIzquierdo() != null) {
            izq = expr.getOperandoIzquierdo().accept(this);
        }
        if (expr.getOperandoDerecho() != null) {
            der = expr.getOperandoDerecho().accept(this);
        }
        String op = expr.getOperador();
        String temp = temporales.nuevoTemporal();
        // inferir el tipo resultado de la operacion
        String tipoResMult = tipoResultadoAritmetico(izq, der);
        // registrar el temporal con el tipo inferido
        tiposConocidos.put(temp, tipoResMult);
        cuartetas.add(new Cuarteta(op, izq != null ? izq : "_", der != null ? der : "_", temp, tipoAritmetico(izq), tipoAritmetico(der), tipoResMult));
        return temp;
    }

    @Override
    public String visitExprSumaResta(ExprSumaResta expr) {
        String izq = expr.getOperandoIzquierdo().accept(this);
        String der = expr.getOperandoDerecho().accept(this);
        String temp = temporales.nuevoTemporal();
        // inferir los tipos de los operandos
        String tipoIzq = inferirTipoDe(izq, tiposConocidos);
        String tipoDer = inferirTipoDe(der, tiposConocidos);
        // inferir el tipo resultado de la operacion
        String tipoResSuma = tipoResultadoAritmetico(izq, der);
        // usar cadena cuando se concatena texto con mas
        if ("+".equals(expr.getOperador())) {
            boolean izqEsCadena = esTipoCadena(tipoIzq);
            boolean derEsCadena = esTipoCadena(tipoDer);
            if (izqEsCadena || derEsCadena) {
                tipoResSuma = "cadena";
            }
        }
        // registrar el temporal con el tipo inferido
        tiposConocidos.put(temp, tipoResSuma);
        cuartetas.add(new Cuarteta(expr.getOperador(), izq, der, temp, tipoAritmetico(izq), tipoAritmetico(der), tipoResSuma));
        return temp;
    }

    @Override
    public String visitExprRelacional(ExprRelacional expr) {
        String izq = expr.getOperandoIzquierdo().accept(this);
        String der = expr.getOperandoDerecho().accept(this);
        String temp = temporales.nuevoTemporal();
        // registrar el temporal como booleano
        tiposConocidos.put(temp, "booleano");
        cuartetas.add(new Cuarteta(expr.getOperador(), izq, der, temp, inferirTipoDe(izq, tiposConocidos), inferirTipoDe(der, tiposConocidos), "booleano"));
        return temp;
    }

    @Override
    public String visitExprAnd(ExprAnd expr) {
        String izq = expr.getOperandoIzquierdo().accept(this);
        String der = expr.getOperandoDerecho().accept(this);
        String temp = temporales.nuevoTemporal();
        // registrar el temporal como booleano
        tiposConocidos.put(temp, "booleano");
        cuartetas.add(new Cuarteta("&&", izq, der, temp, inferirTipoDe(izq, tiposConocidos), inferirTipoDe(der, tiposConocidos), "booleano"));
        return temp;
    }

    @Override
    public String visitExprOr(ExprOr expr) {
        String izq = expr.getOperandoIzquierdo().accept(this);
        String der = expr.getOperandoDerecho().accept(this);
        String temp = temporales.nuevoTemporal();
        // registrar el temporal como booleano
        tiposConocidos.put(temp, "booleano");
        cuartetas.add(new Cuarteta("||", izq, der, temp, inferirTipoDe(izq, tiposConocidos), inferirTipoDe(der, tiposConocidos), "booleano"));
        return temp;
    }

    @Override
    public String visitExprPrimitivo(ExprPrimitivo expr) {
        if (expr.getValor() instanceof ValorPrimitivo) {
            ValorPrimitivo vp = (ValorPrimitivo) expr.getValor();
            // si es identificador, devolverlo directo sin crear temporal
            if (vp.getTipoDato() == TipoPrimitivo.IDENTIFICADOR) {
                return vp.getValor();
            }
            // si es literal, meterlo en un temporal
            String temp = temporales.nuevoTemporal();
            // inferir el tipo del literal
            String tipoLiteral = inferirTipoLiteral(vp.getValor());
            // registrar el temporal con el tipo inferido
            tiposConocidos.put(temp, tipoLiteral);
            cuartetas.add(new Cuarteta("=", vp.getValor(), "_", temp, tipoLiteral, "_", tipoLiteral));
            return temp;
        }
        return null;
    }

    // ==================== ATRIBUTOS ====================

    @Override
    public String visitCampoConNombre(CampoConNombre nodo) {
        // visitar el valor del campo
        return nodo.getValor().accept(this);
    }

    @Override
    public String visitCampoPosicional(CampoPosicional nodo) {
        // visitar el valor posicional
        return nodo.getValor().accept(this);
    }

    // ==================== DECLARACIONES ====================

    @Override
    public String visitDeclObjetoNovus(DeclObjetoNovus nodo) {
        // evaluar argumentos
        List<String> args = new ArrayList<>();
        if (nodo.getArgumentos() != null) {
            if (nodo.getArgumentos() instanceof ListaExpresiones) {
                List<NodoAST> lista = ((ListaExpresiones) nodo.getArgumentos()).getExpresiones();
                for (int i = 0; i < lista.size(); i++) {
                    String a = lista.get(i).accept(this);
                    args.add(a != null ? a : "_");
                }
            }
        }
        // emitir parametros
        for (int i = 0; i < args.size(); i++) {
            cuartetas.add(new Cuarteta("param", args.get(i), "_", "_", inferirTipoDe(args.get(i), tiposConocidos), "_", "_"));
        }
        // crear objeto
        String temp = temporales.nuevoTemporal();
        // registrar el temporal con el tipo del objeto
        tiposConocidos.put(temp, nodo.getTipo());
        cuartetas.add(new Cuarteta("new", nodo.getTipo(), String.valueOf(args.size()), temp, nodo.getTipo(), "_", nodo.getTipo()));
        // asignar a variable
        cuartetas.add(new Cuarteta(":=", temp, "_", nodo.getIdentificador(), inferirTipoDe(temp, tiposConocidos), "_", "_"));
        return null;
    }

    @Override
    public String visitDeclEstructuraConValores(DeclEstructuraConValores nodo) {
        // crear nueva instancia de estructura
        String temp = temporales.nuevoTemporal();
        // registrar el temporal con el tipo de la estructura
        tiposConocidos.put(temp, nodo.getTipo());
        cuartetas.add(new Cuarteta("new_struct", nodo.getTipo(), "_", temp, nodo.getTipo(), "_", nodo.getTipo()));
        // evaluar los atributos
        if (nodo.getAtributos() instanceof ListaAtributosInstancia) {
            List<NodoAST> attrs = ((ListaAtributosInstancia) nodo.getAtributos()).getAtributos();
            for (int i = 0; i < attrs.size(); i++) {
                String val = attrs.get(i).accept(this);
                // obtener el tipo del struct actual
                String tipoStruct = nodo.getTipo();
                // obtener los nombres de campos registrados
                List<String> nombresCampos = this.camposDeStructs.get(tipoStruct);
                // resolver el nombre del campo por indice
                String nombreCampo = String.valueOf(i);
                if (nombresCampos != null && i < nombresCampos.size()) {
                    nombreCampo = nombresCampos.get(i);
                }
                cuartetas.add(new Cuarteta(".,=", temp, nombreCampo, val != null ? val : "_", "_", "_", inferirTipoDe(val, tiposConocidos)));
            }
        }
        // asignar la estructura a la variable
        cuartetas.add(new Cuarteta(":=", temp, "_", nodo.getIdentificador(), inferirTipoDe(temp, tiposConocidos), "_", "_"));
        return null;
    }

    @Override
    public String visitDeclConTipoYValor(DeclConTipoYValor nodo) {
        // si hay valor inicial, emitir asignacion
        if (nodo.getValor() != null) {
            String val = nodo.getValor().accept(this);
            cuartetas.add(new Cuarteta(":=", val != null ? val : "_", "_", nodo.getIdentificador(), inferirTipoDe(val, tiposConocidos), "_", "_"));
        }
        return null;
    }

    @Override
    public String visitDeclBooleanaImplicita(DeclBooleanaImplicita nodo) {
        // emitir asignacion con el valor implicito del nodo
        cuartetas.add(new Cuarteta(":=", nodo.getValor(), "_", nodo.getIdentificador(), inferirTipoLiteral(nodo.getValor()), "_", "_"));
        return null;
    }

    @Override
    public String visitDeclArraySinDatos(DeclArraySinDatos nodo) {
        String tamano = null;
        if (nodo.getTamano() != null) {
            tamano = nodo.getTamano().accept(this);
        }
        cuartetas.add(new Cuarteta("alloc", tamano != null ? tamano : "_", "_", nodo.getIdentificador(), inferirTipoDe(tamano, tiposConocidos), "_", "_"));
        return null;
    }

    @Override
    public String visitDeclArrayConDatos(DeclArrayConDatos nodo) {
        String tamano = null;
        if (nodo.getTamano() != null) {
            tamano = nodo.getTamano().accept(this);
        }
        cuartetas.add(new Cuarteta("alloc", tamano != null ? tamano : "_", "_", nodo.getIdentificador(), inferirTipoDe(tamano, tiposConocidos), "_", "_"));
        if (nodo.getValores() != null) {
            for (int i = 0; i < nodo.getValores().size(); i++) {
                String val = nodo.getValores().get(i).accept(this);
                cuartetas.add(new Cuarteta("[]=", nodo.getIdentificador(), String.valueOf(i), val != null ? val : "_", "_", "entero", "_"));
            }
        }
        return null;
    }

    @Override
    public String visitDeclArrayEstructura(DeclArrayEstructura nodo) {
        String tamano = null;
        if (nodo.getTamano() != null) {
            tamano = nodo.getTamano().accept(this);
        }
        cuartetas.add(new Cuarteta("alloc", tamano != null ? tamano : "_", "_", nodo.getIdentificador(), inferirTipoDe(tamano, tiposConocidos), "_", "_"));
        return null;
    }

    // ==================== ASIGNACIONES ====================

    @Override
    public String visitAsignacionGeneral(AsignacionGeneral a) {
        String izq = a.getVariable().accept(this);
        String der = a.getValor().accept(this);
        cuartetas.add(new Cuarteta(":=", der, "_", izq, inferirTipoDe(der, tiposConocidos), "_", "_"));
        return null;
    }

    // ==================== INSTRUCCIONES DE FLUJO ====================

    @Override
    public String visitStmtAsignacion(StmtAsignacion stmt) {
        if (stmt.getAsignacion() != null) {
            stmt.getAsignacion().accept(this);
        }
        return null;
    }

    @Override
    public String visitStmtCondicional(StmtCondicional stmt) {
        // visitar el condicional
        if (stmt.getCondicional() != null) {
            stmt.getCondicional().accept(this);
        }
        return null;
    }

    @Override
    public String visitStmtCiclo(StmtCiclo stmt) {
        if (stmt.getCiclo() != null) {
            stmt.getCiclo().accept(this);
        }
        return null;
    }

    @Override
    public String visitStmtLectura(StmtLectura stmt) {
        if (stmt.getLectura() != null) {
            stmt.getLectura().accept(this);
        }
        return null;
    }

    @Override
    public String visitStmtImpresion(StmtImpresion stmt) {
        if (stmt.getImpresion() != null) {
            stmt.getImpresion().accept(this);
        }
        return null;
    }

    @Override
    public String visitStmtInterrumpe(StmtInterrumpe stmt) {
        // emitir salto a la etiqueta de break actual si existe
        if (this.etiquetaBreakActual != null) {
            cuartetas.add(new Cuarteta("goto", this.etiquetaBreakActual, "_", "_", "_", "_", "_"));
        }
        return null;
    }

    @Override
    public String visitStmtPerge(StmtPerge stmt) {
        // emitir salto a la etiqueta de continue actual si existe
        if (this.etiquetaContinueActual != null) {
            cuartetas.add(new Cuarteta("goto", this.etiquetaContinueActual, "_", "_", "_", "_", "_"));
        }
        return null;
    }

    @Override
    public String visitStmtExpresion(StmtExpresion stmt) {
        if (stmt.getExpresion() != null) {
            stmt.getExpresion().accept(this);
        }
        return null;
    }

    // ==================== CONDICIONALES ====================

    @Override
    public String visitStatementSi(StatementSi stmt) {
        // regla para if: evaluar cond, luego if_false, bloque, goto, label else, bloque else, label fin
        String cond = null;
        if (stmt.getCondicion() != null) {
            cond = stmt.getCondicion().accept(this);
        }
        String Lelse = temporales.nuevaEtiqueta();
        String Lfin = temporales.nuevaEtiqueta();
        // generar salto a else si es falso
        cuartetas.add(new Cuarteta("if_false", cond != null ? cond : "_", Lelse, "_", "booleano", "_", "_"));
        // bloque principal
        if (stmt.getBloque() != null) {
            stmt.getBloque().accept(this);
        }
        // salto al final
        cuartetas.add(new Cuarteta("goto", Lfin, "_", "_", "_", "_", "_"));
        // etiqueta else
        cuartetas.add(new Cuarteta("label", Lelse, "_", "_", "_", "_", "_"));
        // bloque else
        if (stmt.getBloqueAliter() != null) {
            stmt.getBloqueAliter().accept(this);
        }
        // etiqueta final
        cuartetas.add(new Cuarteta("label", Lfin, "_", "_", "_", "_", "_"));
        return null;
    }

    // ==================== CICLOS ====================

    @Override
    public String visitCicloDum(CicloDum ciclo) {
        String anteriorBreak = this.etiquetaBreakActual;
        String anteriorContinue = this.etiquetaContinueActual;
        String Linicio = temporales.nuevaEtiqueta();
        String Lfin = temporales.nuevaEtiqueta();
        this.etiquetaBreakActual = Lfin;
        this.etiquetaContinueActual = Linicio;
        // etiqueta inicio
        cuartetas.add(new Cuarteta("label", Linicio, "_", "_", "_", "_", "_"));
        // evaluar condicion
        String cond = null;
        if (ciclo.getCondicion() != null) {
            cond = ciclo.getCondicion().accept(this);
        }
        // salto si es falso
        cuartetas.add(new Cuarteta("if_false", cond != null ? cond : "_", Lfin, "_", "booleano", "_", "_"));
        // bloque
        if (ciclo.getBloque() != null) {
            ciclo.getBloque().accept(this);
        }
        // salto al inicio
        cuartetas.add(new Cuarteta("goto", Linicio, "_", "_", "_", "_", "_"));
        // etiqueta final
        cuartetas.add(new Cuarteta("label", Lfin, "_", "_", "_", "_", "_"));
        // restaurar etiquetas
        this.etiquetaBreakActual = anteriorBreak;
        this.etiquetaContinueActual = anteriorContinue;
        return null;
    }

    @Override
    public String visitCicloFacere(CicloFacere ciclo) {
        // guardar etiquetas anteriores
        String anteriorBreak = this.etiquetaBreakActual;
        String anteriorContinue = this.etiquetaContinueActual;
        String Linicio = temporales.nuevaEtiqueta();
        String Lcont = temporales.nuevaEtiqueta();
        String Lfin = temporales.nuevaEtiqueta();
        this.etiquetaBreakActual = Lfin;
        this.etiquetaContinueActual = Lcont;
        // etiqueta inicio
        cuartetas.add(new Cuarteta("label", Linicio, "_", "_", "_", "_", "_"));
        // bloque (se ejecuta al menos una vez)
        if (ciclo.getBloque() != null) {
            ciclo.getBloque().accept(this);
        }
        // etiqueta continue (donde salta perge)
        cuartetas.add(new Cuarteta("label", Lcont, "_", "_", "_", "_", "_"));
        // evaluar condicion
        String cond = null;
        if (ciclo.getCondicion() != null) {
            cond = ciclo.getCondicion().accept(this);
        }
        // si la condicion es verdadera, volver al inicio
        cuartetas.add(new Cuarteta("if_false", cond != null ? cond : "_", Lfin, "_", "booleano", "_", "_"));
        cuartetas.add(new Cuarteta("goto", Linicio, "_", "_", "_", "_", "_"));
        // etiqueta final
        cuartetas.add(new Cuarteta("label", Lfin, "_", "_", "_", "_", "_"));
        // restaurar etiquetas
        this.etiquetaBreakActual = anteriorBreak;
        this.etiquetaContinueActual = anteriorContinue;
        return null;
    }

    @Override
    public String visitCicloPer(CicloPer ciclo) {
        String anteriorBreak = this.etiquetaBreakActual;
        String anteriorContinue = this.etiquetaContinueActual;
        String Linicio = temporales.nuevaEtiqueta();
        String Lfin = temporales.nuevaEtiqueta();
        this.etiquetaBreakActual = Lfin;
        this.etiquetaContinueActual = Linicio;
        // inicializacion
        if (ciclo.getInicializacion() != null) {
            ciclo.getInicializacion().accept(this);
        }
        // etiqueta inicio
        cuartetas.add(new Cuarteta("label", Linicio, "_", "_", "_", "_", "_"));
        // evaluar condicion
        String cond = null;
        if (ciclo.getCondicion() != null) {
            cond = ciclo.getCondicion().accept(this);
        }
        // salto si falso
        cuartetas.add(new Cuarteta("if_false", cond != null ? cond : "_", Lfin, "_", "booleano", "_", "_"));
        // bloque
        if (ciclo.getBloque() != null) {
            ciclo.getBloque().accept(this);
        }
        // paso
        if (ciclo.getPaso() != null) {
            ciclo.getPaso().accept(this);
        }
        // salto al inicio
        cuartetas.add(new Cuarteta("goto", Linicio, "_", "_", "_", "_", "_"));
        // etiqueta final
        cuartetas.add(new Cuarteta("label", Lfin, "_", "_", "_", "_", "_"));
        // restaurar etiquetas
        this.etiquetaBreakActual = anteriorBreak;
        this.etiquetaContinueActual = anteriorContinue;
        return null;
    }

    // ==================== INIT Y PASO DEL FOR ====================

    @Override
    public String visitInitPerDecl(InitPerDecl init) {
        // si hay valor inicial, emitir asignacion
        if (init.getValor() != null) {
            String val = init.getValor().accept(this);
            cuartetas.add(new Cuarteta(":=", val != null ? val : "_", "_", init.getIdentificador(), inferirTipoDe(val, tiposConocidos), "_", "_"));
        }
        return null;
    }

    @Override
    public String visitInitPerAsig(InitPerAsig init) {
        String var = init.getVariable().accept(this);
        String val = init.getValor().accept(this);
        cuartetas.add(new Cuarteta(":=", val != null ? val : "_", "_", var != null ? var : "_", inferirTipoDe(val, tiposConocidos), "_", "_"));
        return null;
    }

    @Override
    public String visitPasoPerExpr(PasoPerExpr paso) {
        if (paso.getExpresion() != null) {
            paso.getExpresion().accept(this);
        }
        return null;
    }

    @Override
    public String visitPasoPerAsig(PasoPerAsig paso) {
        String var = paso.getVariable().accept(this);
        String val = paso.getValor().accept(this);
        cuartetas.add(new Cuarteta(":=", val != null ? val : "_", "_", var != null ? var : "_", inferirTipoDe(val, tiposConocidos), "_", "_"));
        return null;
    }

    // ==================== LECTURA ====================

    @Override
    public String visitLecturaConsolaSimple(LecturaConsolaSimple lectura) {
        // emitir lectura sin variable destino
        cuartetas.add(new Cuarteta("read", "_", "_", "_", "_", "_", "_"));
        return null;
    }

    @Override
    public String visitLecturaConsolaAVariable(LecturaConsolaAVariable lectura) {
        // leer a variable
        String var = null;
        if (lectura.getVariable() != null) {
            var = lectura.getVariable().accept(this);
        }
        cuartetas.add(new Cuarteta("read", "_", "_", var != null ? var : "_", "_", "_", "_"));
        return null;
    }

    // ==================== IMPRESION ====================

    @Override
    public String visitImpresionEncadenada(ImpresionEncadenada impresion) {
        // visitar elementos
        if (impresion.getElementos() != null) {
            for (NodoAST elem : impresion.getElementos()) {
                if (elem != null) {
                    String val = elem.accept(this);
                    // inferir el tipo del valor a imprimir
                    String tipoValor = inferirTipoDe(val, tiposConocidos);
                    // buscar en variables si el tipo sigue desconocido
                    if (tipoValor == null || "_".equals(tipoValor)) {
                        String tipoVar = this.tiposDeVariables.get(val);
                        if (tipoVar != null) {
                            tipoValor = tipoVar;
                        }
                    }
                    // usar guion bajo si el tipo sigue nulo
                    if (tipoValor == null) {
                        tipoValor = "_";
                    }
                    cuartetas.add(new Cuarteta("print", val != null ? val : "_", "_", "_", tipoValor, "_", "_"));
                }
            }
        }
        return null;
    }
}
