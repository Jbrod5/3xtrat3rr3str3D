package org.jrg.analisis.yLenguaje.cuartetas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jrg.model.ast.yLenguaje.Asignacion;
import org.jrg.model.ast.yLenguaje.Bloque;
import org.jrg.model.ast.yLenguaje.CasoDefecto;
import org.jrg.model.ast.yLenguaje.CasoSeleccion;
import org.jrg.model.ast.yLenguaje.CuerpoFuncion;
import org.jrg.model.ast.yLenguaje.ListaExpresiones;
import org.jrg.model.ast.yLenguaje.Parametros;
import org.jrg.model.ast.yLenguaje.Programa;
import org.jrg.model.ast.yLenguaje.SeccionEstructuras;
import org.jrg.model.ast.yLenguaje.SeccionFunciones;
import org.jrg.model.ast.yLenguaje.TipoDato;
import org.jrg.model.ast.yLenguaje.ValorPrimitivo;
import org.jrg.model.ast.yLenguaje.atributo_struct.AtributoArray;
import org.jrg.model.ast.yLenguaje.atributo_struct.AtributoSimple;
import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.base.YAstVisitor;
import org.jrg.model.ast.yLenguaje.ciclo.CicloHacer;
import org.jrg.model.ast.yLenguaje.ciclo.CicloMientras;
import org.jrg.model.ast.yLenguaje.ciclo.CicloPara;
import org.jrg.model.ast.yLenguaje.condicional.StatementSi;
import org.jrg.model.ast.yLenguaje.declaracion_variable.DeclArrayConValores;
import org.jrg.model.ast.yLenguaje.declaracion_variable.DeclArraySinValores;
import org.jrg.model.ast.yLenguaje.declaracion_variable.DeclConTipoYValor;
import org.jrg.model.ast.yLenguaje.declaracion_variable.DeclMatriz;
import org.jrg.model.ast.yLenguaje.definicion_funcion.DefFuncionConRetorno;
import org.jrg.model.ast.yLenguaje.definicion_funcion.DefFuncionSinRetorno;
import org.jrg.model.ast.yLenguaje.definicion_struct.DefEstructura;
import org.jrg.model.ast.yLenguaje.expresion.ExprAccesoArray;
import org.jrg.model.ast.yLenguaje.expresion.ExprAccesoMiembro;
import org.jrg.model.ast.yLenguaje.expresion.ExprAnd;
import org.jrg.model.ast.yLenguaje.expresion.ExprLlamadaFuncion;
import org.jrg.model.ast.yLenguaje.expresion.ExprListaLiteral;
import org.jrg.model.ast.yLenguaje.expresion.ExprMultiplicacionDivision;
import org.jrg.model.ast.yLenguaje.expresion.ExprNegada;
import org.jrg.model.ast.yLenguaje.expresion.ExprNegativa;
import org.jrg.model.ast.yLenguaje.expresion.ExprOr;
import org.jrg.model.ast.yLenguaje.expresion.ExprParentesis;
import org.jrg.model.ast.yLenguaje.expresion.ExprPostDecremento;
import org.jrg.model.ast.yLenguaje.expresion.ExprPostIncremento;
import org.jrg.model.ast.yLenguaje.expresion.ExprPrimitivo;
import org.jrg.model.ast.yLenguaje.expresion.ExprRelacional;
import org.jrg.model.ast.yLenguaje.expresion.ExprSumaResta;
import org.jrg.model.ast.yLenguaje.init_para.InitParaAsig;
import org.jrg.model.ast.yLenguaje.init_para.InitParaDecl;
import org.jrg.model.ast.yLenguaje.instruccion.StmtAsignacion;
import org.jrg.model.ast.yLenguaje.instruccion.StmtCiclo;
import org.jrg.model.ast.yLenguaje.instruccion.StmtCondicional;
import org.jrg.model.ast.yLenguaje.instruccion.StmtContinuar;
import org.jrg.model.ast.yLenguaje.instruccion.StmtDeclaracion;
import org.jrg.model.ast.yLenguaje.instruccion.StmtEstructuraLocal;
import org.jrg.model.ast.yLenguaje.instruccion.StmtExpresion;
import org.jrg.model.ast.yLenguaje.instruccion.StmtRetorno;
import org.jrg.model.ast.yLenguaje.instruccion.StmtRomper;
import org.jrg.model.ast.yLenguaje.instruccion.StmtSeleccion;
import org.jrg.model.ast.yLenguaje.parametro.ParamArray;
import org.jrg.model.ast.yLenguaje.parametro.ParamEstructura;
import org.jrg.model.ast.yLenguaje.parametro.ParamSimple;
import org.jrg.model.ast.yLenguaje.paso_para.PasoParaAsig;
import org.jrg.model.ast.yLenguaje.paso_para.PasoParaExpr;
import org.jrg.model.ast.yLenguaje.seleccion.StatementElegir;
import org.jrg.model.ast.yLenguaje.variable_asignable.VarArray;
import org.jrg.model.ast.yLenguaje.variable_asignable.VarMiembro;
import org.jrg.model.ast.yLenguaje.variable_asignable.VarSimple;
import org.jrg.model.base.TipoPrimitivo;
import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.model.cuarteta.GeneradorTemporales;

// generador de cuartetas para el lenguaje Y
public class GeneradorCuartetasY implements YAstVisitor<String> {

    // lista de cuartetas generadas
    private final List<Cuarteta> cuartetas;
    // generador de temporales y etiquetas
    private final GeneradorTemporales temporales;
    // etiqueta actual para romper
    private String etiquetaBreakActual;
    // etiqueta actual para continuar
    private String etiquetaContinueActual;
    // tipos conocidos de temporales y variables
    private final Map<String, String> tiposConocidos;

    /**
     * Crear el generador de cuartetas para el lenguaje Y.
     */
    public GeneradorCuartetasY() {
        // inicializar la lista y el generador
        this.cuartetas = new ArrayList<>();
        this.temporales = new GeneradorTemporales();
        this.etiquetaBreakActual = null;
        this.etiquetaContinueActual = null;
        this.tiposConocidos = new HashMap<>();
    }

    /**
     * Obtener la lista de cuartetas generadas.
     */
    public List<Cuarteta> getCuartetas() {
        return cuartetas;
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
        // inferir por la forma del literal
        return inferirTipoLiteral(nombre);
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
    public String visitarPrograma(Programa nodo) {
        // visitar la seccion de estructuras si existe
        if (nodo.getSeccionEstructuras() != null) {
            nodo.getSeccionEstructuras().accept(this);
        }
        // visitar la seccion de funciones si existe
        if (nodo.getSeccionFunciones() != null) {
            nodo.getSeccionFunciones().accept(this);
        }
        return null;
    }

    @Override
    public String visitarSeccionEstructuras(SeccionEstructuras nodo) {
        // recorrer cada estructura de la seccion
        if (nodo.getEstructuras() != null) {
            for (NodoASTY estructura : nodo.getEstructuras()) {
                // visitar la estructura actual si existe
                if (estructura != null) {
                    estructura.accept(this);
                }
            }
        }
        return null;
    }

    @Override
    public String visitarSeccionFunciones(SeccionFunciones nodo) {
        // recorrer cada funcion de la seccion
        if (nodo.getFunciones() != null) {
            for (NodoASTY funcion : nodo.getFunciones()) {
                // visitar la funcion actual si existe
                if (funcion != null) {
                    funcion.accept(this);
                }
            }
        }
        return null;
    }

    @Override
    public String visitarTipoDato(TipoDato nodo) {
        // TODO
        return null;
    }

    @Override
    public String visitarParametros(Parametros nodo) {
        // TODO
        return null;
    }

    @Override
    public String visitarCuerpoFuncion(CuerpoFuncion nodo) {
        // recorrer las instrucciones del cuerpo
        if (nodo.getInstrucciones() != null) {
            for (NodoASTY instruccion : nodo.getInstrucciones()) {
                // visitar la instruccion actual si existe
                if (instruccion != null) {
                    instruccion.accept(this);
                }
            }
        }
        return null;
    }

    @Override
    public String visitarBloque(Bloque nodo) {
        // recorrer las instrucciones del bloque
        if (nodo.getInstrucciones() != null) {
            for (NodoASTY instruccion : nodo.getInstrucciones()) {
                // visitar la instruccion actual si existe
                if (instruccion != null) {
                    instruccion.accept(this);
                }
            }
        }
        return null;
    }

    @Override
    public String visitarAsignacion(Asignacion nodo) {
        // evaluar la variable destino
        String izquierda = "_";
        if (nodo.getVariable() != null) {
            izquierda = nodo.getVariable().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (izquierda == null) {
            izquierda = "_";
        }
        // evaluar el valor a asignar
        String derecha = "_";
        if (nodo.getValor() != null) {
            derecha = nodo.getValor().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (derecha == null) {
            derecha = "_";
        }
        // emitir la asignacion
        cuartetas.add(new Cuarteta(":=", derecha, "_", izquierda, inferirTipoDe(derecha, tiposConocidos), "_", "_"));
        return null;
    }

    @Override
    public String visitarCasoSeleccion(CasoSeleccion nodo) {
        // recorrer las instrucciones del caso
        if (nodo.getInstrucciones() != null) {
            for (NodoASTY instruccion : nodo.getInstrucciones()) {
                // visitar la instruccion actual si existe
                if (instruccion != null) {
                    instruccion.accept(this);
                }
            }
        }
        return null;
    }

    @Override
    public String visitarCasoDefecto(CasoDefecto nodo) {
        // recorrer las instrucciones del caso por defecto
        if (nodo.getInstrucciones() != null) {
            for (NodoASTY instruccion : nodo.getInstrucciones()) {
                // visitar la instruccion actual si existe
                if (instruccion != null) {
                    instruccion.accept(this);
                }
            }
        }
        return null;
    }

    @Override
    public String visitarValorPrimitivo(ValorPrimitivo nodo) {
        // TODO
        return null;
    }

    @Override
    public String visitarListaExpresiones(ListaExpresiones nodo) {
        // TODO
        return null;
    }

    // ==================== ESTRUCTURAS ====================

    @Override
    public String visitarDefEstructura(DefEstructura nodo) {
        // no emitir cuartetas porque los structs son tipos
        return null;
    }

    @Override
    public String visitarAtributoSimple(AtributoSimple nodo) {
        // no genera cuarteta por si solo
        return null;
    }

    @Override
    public String visitarAtributoArray(AtributoArray nodo) {
        // no genera cuarteta por si solo
        return null;
    }

    // ==================== FUNCIONES ====================

    @Override
    public String visitarDefFuncionSinRetorno(DefFuncionSinRetorno nodo) {
        // construir el string de tipos de parametros
        String tiposParams = extraerTiposParametros(nodo.getParametros());
        // emitir marcador de inicio
        cuartetas.add(new Cuarteta("func_begin", nodo.getNombre(), tiposParams, "void", tiposParams, "_", "void"));
        // visitar el cuerpo de la funcion si existe
        if (nodo.getCuerpo() != null) {
            nodo.getCuerpo().accept(this);
        }
        // emitir marcador de fin
        cuartetas.add(new Cuarteta("func_end", nodo.getNombre(), "_", "_", "_", "_", "_"));
        return null;
    }

    @Override
    public String visitarDefFuncionConRetorno(DefFuncionConRetorno nodo) {
        // construir el string de tipos de parametros
        String tiposParams = extraerTiposParametros(nodo.getParametros());
        // extraer el tipo de retorno
        String tipoRetorno = "_";
        if (nodo.getTipoRetorno() instanceof TipoDato) {
            tipoRetorno = ((TipoDato) nodo.getTipoRetorno()).getNombre();
        }
        if (tipoRetorno == null) {
            tipoRetorno = "_";
        }
        // emitir marcador de inicio
        cuartetas.add(new Cuarteta("func_begin", nodo.getNombre(), tiposParams, tipoRetorno, tiposParams, "_", tipoRetorno));
        // visitar el cuerpo de la funcion si existe
        if (nodo.getCuerpo() != null) {
            nodo.getCuerpo().accept(this);
        }
        // emitir marcador de fin
        cuartetas.add(new Cuarteta("func_end", nodo.getNombre(), "_", "_", "_", "_", "_"));
        return null;
    }

    // construir el string de tipos de parametros separados por coma
    private String extraerTiposParametros(NodoASTY parametrosNodo) {
        // devolver guion bajo si no hay parametros
        if (parametrosNodo == null) {
            return "_";
        }
        // verificar que sea Parametros
        if (!(parametrosNodo instanceof Parametros)) {
            return "_";
        }
        // convertir al tipo concreto
        Parametros parametros = (Parametros) parametrosNodo;
        // verificar que la lista no sea nula
        if (parametros.getParametros() == null || parametros.getParametros().isEmpty()) {
            return "_";
        }
        // acumular los tipos separados por coma
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parametros.getParametros().size(); i++) {
            NodoASTY p = parametros.getParametros().get(i);
            String tipo = "_";
            if (p instanceof ParamSimple) {
                NodoASTY tipoNodo = ((ParamSimple) p).getTipo();
                if (tipoNodo instanceof TipoDato) {
                    tipo = ((TipoDato) tipoNodo).getNombre();
                }
            } else if (p instanceof ParamArray) {
                NodoASTY tipoNodo = ((ParamArray) p).getTipo();
                if (tipoNodo instanceof TipoDato) {
                    tipo = ((TipoDato) tipoNodo).getNombre() + "[]";
                }
            } else if (p instanceof ParamEstructura) {
                tipo = ((ParamEstructura) p).getTipoEstructura();
            }
            if (tipo == null) {
                tipo = "_";
            }
            if (i > 0) {
                sb.append(",");
            }
            sb.append(tipo);
        }
        return sb.toString();
    }

    @Override
    public String visitarParamSimple(ParamSimple nodo) {
        // TODO
        return null;
    }

    @Override
    public String visitarParamArray(ParamArray nodo) {
        // TODO
        return null;
    }

    @Override
    public String visitarParamEstructura(ParamEstructura nodo) {
        // TODO
        return null;
    }

    // ==================== INSTRUCCIONES ====================

    @Override
    public String visitarStmtDeclaracion(StmtDeclaracion nodo) {
        // visitar la declaracion si existe
        if (nodo.getDeclaracion() != null) {
            nodo.getDeclaracion().accept(this);
        }
        return null;
    }

    @Override
    public String visitarStmtAsignacion(StmtAsignacion nodo) {
        // visitar la asignacion si existe
        if (nodo.getAsignacion() != null) {
            nodo.getAsignacion().accept(this);
        }
        return null;
    }

    @Override
    public String visitarStmtEstructuraLocal(StmtEstructuraLocal nodo) {
        // no emitir cuartetas porque los structs locales son solo tipos
        return null;
    }

    @Override
    public String visitarStmtCondicional(StmtCondicional nodo) {
        // visitar el condicional si existe
        if (nodo.getCondicional() != null) {
            nodo.getCondicional().accept(this);
        }
        return null;
    }

    @Override
    public String visitarStmtSeleccion(StmtSeleccion nodo) {
        // visitar la seleccion si existe
        if (nodo.getSeleccion() != null) {
            nodo.getSeleccion().accept(this);
        }
        return null;
    }

    @Override
    public String visitarStmtCiclo(StmtCiclo nodo) {
        // visitar el ciclo si existe
        if (nodo.getCiclo() != null) {
            nodo.getCiclo().accept(this);
        }
        return null;
    }

    @Override
    public String visitarStmtRetorno(StmtRetorno nodo) {
        // evaluar la expresion de retorno si existe
        if (nodo.getExpresion() != null) {
            // obtener el valor de retorno
            String valor = nodo.getExpresion().accept(this);
            // usar valor por defecto si el resultado es nulo
            if (valor == null) {
                valor = "_";
            }
            // emitir el retorno con valor
            cuartetas.add(new Cuarteta("return", valor, "_", "_", inferirTipoDe(valor, tiposConocidos), "_", "_"));
        } else {
            // emitir el retorno sin valor
            cuartetas.add(new Cuarteta("return", "_", "_", "_", "_", "_", "_"));
        }
        return null;
    }

    @Override
    public String visitarStmtContinuar(StmtContinuar nodo) {
        // emitir salto a la etiqueta de continuar si existe
        if (this.etiquetaContinueActual != null) {
            cuartetas.add(new Cuarteta("goto", this.etiquetaContinueActual, "_", "_", "_", "_", "_"));
        }
        return null;
    }

    @Override
    public String visitarStmtRomper(StmtRomper nodo) {
        // emitir salto a la etiqueta de romper si existe
        if (this.etiquetaBreakActual != null) {
            cuartetas.add(new Cuarteta("goto", this.etiquetaBreakActual, "_", "_", "_", "_", "_"));
        }
        return null;
    }

    @Override
    public String visitarStmtExpresion(StmtExpresion nodo) {
        // visitar la expresion si existe
        if (nodo.getExpresion() != null) {
            nodo.getExpresion().accept(this);
        }
        return null;
    }

    // ==================== DECLARACIONES ====================

    @Override
    public String visitarDeclConTipoYValor(DeclConTipoYValor nodo) {
        // emitir la asignacion inicial si hay valor
        if (nodo.getValor() != null) {
            // evaluar el valor inicial
            String valor = nodo.getValor().accept(this);
            // usar valor por defecto si el resultado es nulo
            if (valor == null) {
                valor = "_";
            }
            // emitir la asignacion a la variable
            cuartetas.add(new Cuarteta(":=", valor, "_", nodo.getNombre(), inferirTipoDe(valor, tiposConocidos), "_", "_"));
        }
        return null;
    }

    @Override
    public String visitarDeclArraySinValores(DeclArraySinValores nodo) {
        // evaluar el tamano del arreglo
        String tamano = "_";
        if (nodo.getTamano() != null) {
            tamano = nodo.getTamano().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (tamano == null) {
            tamano = "_";
        }
        // emitir la reserva de memoria
        cuartetas.add(new Cuarteta("alloc", tamano, "_", nodo.getNombre(), inferirTipoDe(tamano, tiposConocidos), "_", "_"));
        return null;
    }

    @Override
    public String visitarDeclArrayConValores(DeclArrayConValores nodo) {
        // evaluar el tamano del arreglo
        String tamano = "_";
        if (nodo.getTamano() != null) {
            tamano = nodo.getTamano().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (tamano == null) {
            tamano = "_";
        }
        // emitir la reserva de memoria
        cuartetas.add(new Cuarteta("alloc", tamano, "_", nodo.getNombre(), inferirTipoDe(tamano, tiposConocidos), "_", "_"));
        // obtener la lista de valores iniciales
        NodoASTY lista = nodo.getListaValores();
        // recorrer los valores si la lista existe
        if (lista instanceof ListaExpresiones) {
            // convertir la lista al tipo concreto
            ListaExpresiones listaExpresiones = (ListaExpresiones) lista;
            // recorrer cada valor de la lista
            if (listaExpresiones.getExpresiones() != null) {
                for (int i = 0; i < listaExpresiones.getExpresiones().size(); i++) {
                    // evaluar el valor actual
                    String valor = listaExpresiones.getExpresiones().get(i).accept(this);
                    // usar valor por defecto si el resultado es nulo
                    if (valor == null) {
                        valor = "_";
                    }
                    // emitir la asignacion a la posicion actual
                    cuartetas.add(new Cuarteta("[]=", nodo.getNombre(), String.valueOf(i), valor, "_", "entero", "_"));
                }
            }
        } else {
            // visitar la lista si tiene otro formato
            if (lista != null) {
                lista.accept(this);
            }
        }
        return null;
    }

    @Override
    public String visitarDeclMatriz(DeclMatriz nodo) {
        // evaluar el tamano de filas
        String filas = "_";
        if (nodo.getTamanoFilas() != null) {
            filas = nodo.getTamanoFilas().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (filas == null) {
            filas = "_";
        }
        // evaluar el tamano de columnas
        String columnas = "_";
        if (nodo.getTamanoColumnas() != null) {
            columnas = nodo.getTamanoColumnas().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (columnas == null) {
            columnas = "_";
        }
        // emitir la reserva de memoria con ambas dimensiones
        cuartetas.add(new Cuarteta("alloc", filas, columnas, nodo.getNombre(), inferirTipoDe(filas, tiposConocidos), inferirTipoDe(columnas, tiposConocidos), "_"));
        return null;
    }

    // ==================== VARIABLES ASIGNABLES ====================

    @Override
    public String visitarVarSimple(VarSimple nodo) {
        // devolver el nombre directamente
        return nodo.getNombre();
    }

    @Override
    public String visitarVarArray(VarArray nodo) {
        // evaluar la base del acceso
        String base = "_";
        if (nodo.getBase() != null) {
            base = nodo.getBase().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (base == null) {
            base = "_";
        }
        // evaluar el indice del acceso
        String indice = "_";
        if (nodo.getIndice() != null) {
            indice = nodo.getIndice().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (indice == null) {
            indice = "_";
        }
        // devolver la referencia compuesta sin emitir cuarteta
        return base + "[" + indice + "]";
    }

    @Override
    public String visitarVarMiembro(VarMiembro nodo) {
        // evaluar la base del acceso
        String base = "_";
        if (nodo.getBase() != null) {
            base = nodo.getBase().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (base == null) {
            base = "_";
        }
        // devolver la referencia compuesta sin emitir cuarteta
        return base + "." + nodo.getMiembro();
    }

    // ==================== CONDICIONAL ====================

    @Override
    public String visitarStatementSi(StatementSi nodo) {
        // evaluar la condicion principal
        String condicion = "_";
        if (nodo.getCondicionPrincipal() != null) {
            condicion = nodo.getCondicionPrincipal().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (condicion == null) {
            condicion = "_";
        }
        // crear la etiqueta final
        String lfin = temporales.nuevaEtiqueta();
        // crear la etiqueta de la rama que sigue
        String lSiguiente = temporales.nuevaEtiqueta();
        // emitir el salto a la rama que sigue si la condicion es falsa
        cuartetas.add(new Cuarteta("if_false", condicion, lSiguiente, "_", "booleano", "_", "_"));
        // visitar el bloque principal si existe
        if (nodo.getBloquePrincipal() != null) {
            nodo.getBloquePrincipal().accept(this);
        }
        // emitir el salto al final
        cuartetas.add(new Cuarteta("goto", lfin, "_", "_", "_", "_", "_"));
        // emitir la etiqueta de la rama que sigue
        cuartetas.add(new Cuarteta("label", lSiguiente, "_", "_", "_", "_", "_"));
        // recorrer las ramas sino si si existen
        if (nodo.getCondicionesSino() != null) {
            for (int i = 0; i < nodo.getCondicionesSino().size(); i++) {
                // evaluar la condicion de la rama actual
                String condicionSino = "_";
                if (nodo.getCondicionesSino().get(i) != null) {
                    condicionSino = nodo.getCondicionesSino().get(i).accept(this);
                }
                // usar valor por defecto si el resultado es nulo
                if (condicionSino == null) {
                    condicionSino = "_";
                }
                // crear la etiqueta de la rama que sigue
                String lSiguienteSino = temporales.nuevaEtiqueta();
                // emitir el salto si la condicion es falsa
                cuartetas.add(new Cuarteta("if_false", condicionSino, lSiguienteSino, "_", "booleano", "_", "_"));
                // visitar el bloque de la rama actual si existe
                if (nodo.getBloquesSino() != null) {
                    if (i < nodo.getBloquesSino().size()) {
                        if (nodo.getBloquesSino().get(i) != null) {
                            nodo.getBloquesSino().get(i).accept(this);
                        }
                    }
                }
                // emitir el salto al final
                cuartetas.add(new Cuarteta("goto", lfin, "_", "_", "_", "_", "_"));
                // emitir la etiqueta de la rama que sigue
                cuartetas.add(new Cuarteta("label", lSiguienteSino, "_", "_", "_", "_", "_"));
            }
        }
        // visitar el bloque contrario si existe
        if (nodo.getBloqueContrario() != null) {
            nodo.getBloqueContrario().accept(this);
        }
        // emitir la etiqueta final
        cuartetas.add(new Cuarteta("label", lfin, "_", "_", "_", "_", "_"));
        return null;
    }

    // ==================== SELECCION ====================

    @Override
    public String visitarStatementElegir(StatementElegir nodo) {
        // evaluar la expresion de seleccion
        String selector = "_";
        if (nodo.getExpresion() != null) {
            selector = nodo.getExpresion().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (selector == null) {
            selector = "_";
        }
        // crear la etiqueta final
        String lfin = temporales.nuevaEtiqueta();
        // recorrer cada caso de la seleccion
        if (nodo.getCasos() != null) {
            for (int i = 0; i < nodo.getCasos().size(); i++) {
                // obtener el caso actual
                NodoASTY caso = nodo.getCasos().get(i);
                // omitir el caso si es nulo
                if (caso == null) {
                    continue;
                }
                // comparar el selector cuando el caso trae valor
                if (caso instanceof CasoSeleccion) {
                    // convertir el caso al tipo concreto
                    CasoSeleccion casoSeleccion = (CasoSeleccion) caso;
                    // evaluar el valor del caso
                    String valorCaso = "_";
                    if (casoSeleccion.getValor() != null) {
                        valorCaso = casoSeleccion.getValor().accept(this);
                    }
                    // usar valor por defecto si el resultado es nulo
                    if (valorCaso == null) {
                        valorCaso = "_";
                    }
                    // comparar el selector con el valor del caso
                    String temp = temporales.nuevoTemporal();
                    // registrar el temporal como booleano
                    tiposConocidos.put(temp, "booleano");
                    cuartetas.add(new Cuarteta("==", selector, valorCaso, temp, inferirTipoDe(selector, tiposConocidos), inferirTipoDe(valorCaso, tiposConocidos), "booleano"));
                    // crear la etiqueta del caso que sigue
                    String lSiguiente = temporales.nuevaEtiqueta();
                    // emitir el salto si no hay coincidencia
                    cuartetas.add(new Cuarteta("if_false", temp, lSiguiente, "_", "booleano", "_", "_"));
                    // visitar las instrucciones del caso
                    if (casoSeleccion.getInstrucciones() != null) {
                        for (NodoASTY instruccion : casoSeleccion.getInstrucciones()) {
                            // visitar la instruccion actual si existe
                            if (instruccion != null) {
                                instruccion.accept(this);
                            }
                        }
                    }
                    // emitir el salto al final
                    cuartetas.add(new Cuarteta("goto", lfin, "_", "_", "_", "_", "_"));
                    // emitir la etiqueta del caso que sigue
                    cuartetas.add(new Cuarteta("label", lSiguiente, "_", "_", "_", "_", "_"));
                } else {
                    // visitar el caso directamente
                    caso.accept(this);
                }
            }
        }
        // visitar el caso por defecto si existe
        if (nodo.getCasoDefecto() != null) {
            nodo.getCasoDefecto().accept(this);
        }
        // emitir la etiqueta final
        cuartetas.add(new Cuarteta("label", lfin, "_", "_", "_", "_", "_"));
        return null;
    }

    // ==================== CICLOS ====================

    @Override
    public String visitarCicloPara(CicloPara nodo) {
        // guardar las etiquetas anteriores
        String anteriorBreak = this.etiquetaBreakActual;
        String anteriorContinue = this.etiquetaContinueActual;
        // crear las etiquetas del ciclo
        String lInicio = temporales.nuevaEtiqueta();
        String lFin = temporales.nuevaEtiqueta();
        // asignar las etiquetas actuales
        this.etiquetaBreakActual = lFin;
        this.etiquetaContinueActual = lInicio;
        // visitar la inicializacion si existe
        if (nodo.getInicializacion() != null) {
            nodo.getInicializacion().accept(this);
        }
        // emitir la etiqueta de inicio
        cuartetas.add(new Cuarteta("label", lInicio, "_", "_", "_", "_", "_"));
        // evaluar la condicion si existe
        if (nodo.getCondicion() != null) {
            // obtener el resultado de la condicion
            String condicion = nodo.getCondicion().accept(this);
            // usar valor por defecto si el resultado es nulo
            if (condicion == null) {
                condicion = "_";
            }
            // emitir el salto al final si la condicion es falsa
            cuartetas.add(new Cuarteta("if_false", condicion, lFin, "_", "booleano", "_", "_"));
        }
        // visitar el bloque si existe
        if (nodo.getBloque() != null) {
            nodo.getBloque().accept(this);
        }
        // visitar el paso si existe
        if (nodo.getPaso() != null) {
            nodo.getPaso().accept(this);
        }
        // emitir el salto al inicio
        cuartetas.add(new Cuarteta("goto", lInicio, "_", "_", "_", "_", "_"));
        // emitir la etiqueta final
        cuartetas.add(new Cuarteta("label", lFin, "_", "_", "_", "_", "_"));
        // restaurar las etiquetas anteriores
        this.etiquetaBreakActual = anteriorBreak;
        this.etiquetaContinueActual = anteriorContinue;
        return null;
    }

    @Override
    public String visitarCicloMientras(CicloMientras nodo) {
        // guardar las etiquetas anteriores
        String anteriorBreak = this.etiquetaBreakActual;
        String anteriorContinue = this.etiquetaContinueActual;
        // crear las etiquetas del ciclo
        String lInicio = temporales.nuevaEtiqueta();
        String lFin = temporales.nuevaEtiqueta();
        // asignar las etiquetas actuales
        this.etiquetaBreakActual = lFin;
        this.etiquetaContinueActual = lInicio;
        // emitir la etiqueta de inicio
        cuartetas.add(new Cuarteta("label", lInicio, "_", "_", "_", "_", "_"));
        // evaluar la condicion
        String condicion = "_";
        if (nodo.getCondicion() != null) {
            condicion = nodo.getCondicion().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (condicion == null) {
            condicion = "_";
        }
        // emitir el salto al final si la condicion es falsa
        cuartetas.add(new Cuarteta("if_false", condicion, lFin, "_", "booleano", "_", "_"));
        // visitar el bloque si existe
        if (nodo.getBloque() != null) {
            nodo.getBloque().accept(this);
        }
        // emitir el salto al inicio
        cuartetas.add(new Cuarteta("goto", lInicio, "_", "_", "_", "_", "_"));
        // emitir la etiqueta final
        cuartetas.add(new Cuarteta("label", lFin, "_", "_", "_", "_", "_"));
        // restaurar las etiquetas anteriores
        this.etiquetaBreakActual = anteriorBreak;
        this.etiquetaContinueActual = anteriorContinue;
        return null;
    }

    @Override
    public String visitarCicloHacer(CicloHacer nodo) {
        // guardar las etiquetas anteriores
        String anteriorBreak = this.etiquetaBreakActual;
        String anteriorContinue = this.etiquetaContinueActual;
        // crear las etiquetas del ciclo
        String lInicio = temporales.nuevaEtiqueta();
        String lContinuar = temporales.nuevaEtiqueta();
        String lFin = temporales.nuevaEtiqueta();
        // asignar las etiquetas actuales
        this.etiquetaBreakActual = lFin;
        this.etiquetaContinueActual = lContinuar;
        // emitir la etiqueta de inicio
        cuartetas.add(new Cuarteta("label", lInicio, "_", "_", "_", "_", "_"));
        // visitar el bloque si existe
        if (nodo.getBloque() != null) {
            nodo.getBloque().accept(this);
        }
        // emitir la etiqueta de continuar
        cuartetas.add(new Cuarteta("label", lContinuar, "_", "_", "_", "_", "_"));
        // evaluar la condicion
        String condicion = "_";
        if (nodo.getCondicion() != null) {
            condicion = nodo.getCondicion().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (condicion == null) {
            condicion = "_";
        }
        // emitir el salto al final si la condicion es falsa
        cuartetas.add(new Cuarteta("if_false", condicion, lFin, "_", "booleano", "_", "_"));
        // emitir el salto al inicio
        cuartetas.add(new Cuarteta("goto", lInicio, "_", "_", "_", "_", "_"));
        // emitir la etiqueta final
        cuartetas.add(new Cuarteta("label", lFin, "_", "_", "_", "_", "_"));
        // restaurar las etiquetas anteriores
        this.etiquetaBreakActual = anteriorBreak;
        this.etiquetaContinueActual = anteriorContinue;
        return null;
    }

    // ==================== INIT Y PASO DEL PARA ====================

    @Override
    public String visitarInitParaDecl(InitParaDecl nodo) {
        // emitir la asignacion inicial si hay expresion
        if (nodo.getExpresion() != null) {
            // evaluar la expresion inicial
            String valor = nodo.getExpresion().accept(this);
            // usar valor por defecto si el resultado es nulo
            if (valor == null) {
                valor = "_";
            }
            // emitir la asignacion a la variable
            cuartetas.add(new Cuarteta(":=", valor, "_", nodo.getNombre(), inferirTipoDe(valor, tiposConocidos), "_", "_"));
        }
        return null;
    }

    @Override
    public String visitarInitParaAsig(InitParaAsig nodo) {
        // evaluar la variable destino
        String variable = "_";
        if (nodo.getVariable() != null) {
            variable = nodo.getVariable().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (variable == null) {
            variable = "_";
        }
        // evaluar la expresion inicial
        String valor = "_";
        if (nodo.getExpresion() != null) {
            valor = nodo.getExpresion().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (valor == null) {
            valor = "_";
        }
        // emitir la asignacion
        cuartetas.add(new Cuarteta(":=", valor, "_", variable, inferirTipoDe(valor, tiposConocidos), "_", "_"));
        return null;
    }

    @Override
    public String visitarPasoParaExpr(PasoParaExpr nodo) {
        // visitar la expresion del paso si existe
        if (nodo.getExpresion() != null) {
            nodo.getExpresion().accept(this);
        }
        return null;
    }

    @Override
    public String visitarPasoParaAsig(PasoParaAsig nodo) {
        // evaluar la variable destino
        String variable = "_";
        if (nodo.getVariable() != null) {
            variable = nodo.getVariable().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (variable == null) {
            variable = "_";
        }
        // evaluar la expresion del paso
        String valor = "_";
        if (nodo.getExpresion() != null) {
            valor = nodo.getExpresion().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (valor == null) {
            valor = "_";
        }
        // emitir la asignacion
        cuartetas.add(new Cuarteta(":=", valor, "_", variable, inferirTipoDe(valor, tiposConocidos), "_", "_"));
        return null;
    }

    // ==================== EXPRESIONES ====================

    @Override
    public String visitarExprParentesis(ExprParentesis nodo) {
        // visitar la expresion interna si existe
        if (nodo.getExpresion() != null) {
            return nodo.getExpresion().accept(this);
        }
        return null;
    }

    @Override
    public String visitarExprLlamadaFuncion(ExprLlamadaFuncion nodo) {
        // evaluar los argumentos de la llamada
        List<String> argumentos = new ArrayList<>();
        if (nodo.getArgumentos() != null) {
            for (int i = 0; i < nodo.getArgumentos().size(); i++) {
                // evaluar el argumento actual
                String argumento = nodo.getArgumentos().get(i).accept(this);
                // usar valor por defecto si el resultado es nulo
                if (argumento == null) {
                    argumento = "_";
                }
                // agregar el argumento a la lista
                argumentos.add(argumento);
            }
        }
        // emitir un param por cada argumento
        for (int i = 0; i < argumentos.size(); i++) {
            cuartetas.add(new Cuarteta("param", argumentos.get(i), "_", "_", inferirTipoDe(argumentos.get(i), tiposConocidos), "_", "_"));
        }
        // emitir la llamada y guardar el resultado en un temporal
        String temp = temporales.nuevoTemporal();
        cuartetas.add(new Cuarteta("call", nodo.getNombre(), String.valueOf(argumentos.size()), temp, "_", "_", "_"));
        return temp;
    }

    @Override
    public String visitarExprAccesoArray(ExprAccesoArray nodo) {
        // evaluar el objeto del acceso
        String objeto = "_";
        if (nodo.getObjeto() != null) {
            objeto = nodo.getObjeto().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (objeto == null) {
            objeto = "_";
        }
        // evaluar el indice del acceso
        String indice = "_";
        if (nodo.getIndice() != null) {
            indice = nodo.getIndice().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (indice == null) {
            indice = "_";
        }
        // generar el acceso a arreglo con temporal
        String temp = temporales.nuevoTemporal();
        cuartetas.add(new Cuarteta("=[]", objeto, indice, temp, "_", "entero", "_"));
        return temp;
    }

    @Override
    public String visitarExprAccesoMiembro(ExprAccesoMiembro nodo) {
        // evaluar el objeto del acceso
        String objeto = "_";
        if (nodo.getObjeto() != null) {
            objeto = nodo.getObjeto().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (objeto == null) {
            objeto = "_";
        }
        // emitir el acceso a miembro con temporal
        String temp = temporales.nuevoTemporal();
        cuartetas.add(new Cuarteta(".", objeto, nodo.getMiembro(), temp, "_", "_", "_"));
        return temp;
    }

    @Override
    public String visitarExprPostIncremento(ExprPostIncremento nodo) {
        // evaluar la variable
        String variable = "_";
        if (nodo.getVariable() != null) {
            variable = nodo.getVariable().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (variable == null) {
            variable = "_";
        }
        // emitir el incremento sobre la misma variable
        cuartetas.add(new Cuarteta("+", variable, "1", variable, inferirTipoDe(variable, tiposConocidos), "entero", inferirTipoDe(variable, tiposConocidos)));
        return variable;
    }

    @Override
    public String visitarExprPostDecremento(ExprPostDecremento nodo) {
        // evaluar la variable
        String variable = "_";
        if (nodo.getVariable() != null) {
            variable = nodo.getVariable().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (variable == null) {
            variable = "_";
        }
        // emitir el decremento sobre la misma variable
        cuartetas.add(new Cuarteta("-", variable, "1", variable, inferirTipoDe(variable, tiposConocidos), "entero", inferirTipoDe(variable, tiposConocidos)));
        return variable;
    }

    @Override
    public String visitarExprListaLiteral(ExprListaLiteral nodo) {
        // no genera cuarteta por si solo en este contexto
        return null;
    }

    @Override
    public String visitarExprNegativa(ExprNegativa nodo) {
        // evaluar la expresion interna
        String valor = "_";
        if (nodo.getExpresion() != null) {
            valor = nodo.getExpresion().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (valor == null) {
            valor = "_";
        }
        // emitir la negacion aritmetica con temporal
        String temp = temporales.nuevoTemporal();
        // inferir el tipo desde el operando
        String tipoNeg = inferirTipoDe(valor, tiposConocidos);
        // registrar el temporal con el tipo inferido
        tiposConocidos.put(temp, tipoNeg);
        cuartetas.add(new Cuarteta("-", valor, "_", temp, tipoNeg, "_", tipoNeg));
        return temp;
    }

    @Override
    public String visitarExprNegada(ExprNegada nodo) {
        // evaluar la expresion interna
        String valor = "_";
        if (nodo.getExpresion() != null) {
            valor = nodo.getExpresion().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (valor == null) {
            valor = "_";
        }
        // emitir la negacion logica con temporal
        String temp = temporales.nuevoTemporal();
        // registrar el temporal como booleano
        tiposConocidos.put(temp, "booleano");
        cuartetas.add(new Cuarteta("!", valor, "_", temp, inferirTipoDe(valor, tiposConocidos), "_", "booleano"));
        return temp;
    }

    @Override
    public String visitarExprMultiplicacionDivision(ExprMultiplicacionDivision nodo) {
        // evaluar el operando izquierdo
        String izquierdo = "_";
        if (nodo.getIzquierdo() != null) {
            izquierdo = nodo.getIzquierdo().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (izquierdo == null) {
            izquierdo = "_";
        }
        // evaluar el operando derecho
        String derecho = "_";
        if (nodo.getDerecho() != null) {
            derecho = nodo.getDerecho().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (derecho == null) {
            derecho = "_";
        }
        // emitir la operacion con temporal
        String temp = temporales.nuevoTemporal();
        // inferir el tipo resultado de la operacion
        String tipoResMult = tipoResultadoAritmetico(izquierdo, derecho);
        // registrar el temporal con el tipo inferido
        tiposConocidos.put(temp, tipoResMult);
        cuartetas.add(new Cuarteta(nodo.getOperador(), izquierdo, derecho, temp, tipoAritmetico(izquierdo), tipoAritmetico(derecho), tipoResMult));
        return temp;
    }

    @Override
    public String visitarExprSumaResta(ExprSumaResta nodo) {
        // evaluar el operando izquierdo
        String izquierdo = "_";
        if (nodo.getIzquierdo() != null) {
            izquierdo = nodo.getIzquierdo().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (izquierdo == null) {
            izquierdo = "_";
        }
        // evaluar el operando derecho
        String derecho = "_";
        if (nodo.getDerecho() != null) {
            derecho = nodo.getDerecho().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (derecho == null) {
            derecho = "_";
        }
        // emitir la operacion con temporal
        String temp = temporales.nuevoTemporal();
        // inferir el tipo resultado de la operacion
        String tipoResSuma = tipoResultadoAritmetico(izquierdo, derecho);
        // registrar el temporal con el tipo inferido
        tiposConocidos.put(temp, tipoResSuma);
        cuartetas.add(new Cuarteta(nodo.getOperador(), izquierdo, derecho, temp, tipoAritmetico(izquierdo), tipoAritmetico(derecho), tipoResSuma));
        return temp;
    }

    @Override
    public String visitarExprRelacional(ExprRelacional nodo) {
        // evaluar el operando izquierdo
        String izquierdo = "_";
        if (nodo.getIzquierdo() != null) {
            izquierdo = nodo.getIzquierdo().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (izquierdo == null) {
            izquierdo = "_";
        }
        // evaluar el operando derecho
        String derecho = "_";
        if (nodo.getDerecho() != null) {
            derecho = nodo.getDerecho().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (derecho == null) {
            derecho = "_";
        }
        // emitir la comparacion con temporal
        String temp = temporales.nuevoTemporal();
        // registrar el temporal como booleano
        tiposConocidos.put(temp, "booleano");
        cuartetas.add(new Cuarteta(nodo.getOperador(), izquierdo, derecho, temp, inferirTipoDe(izquierdo, tiposConocidos), inferirTipoDe(derecho, tiposConocidos), "booleano"));
        return temp;
    }

    @Override
    public String visitarExprAnd(ExprAnd nodo) {
        // evaluar el operando izquierdo
        String izquierdo = "_";
        if (nodo.getIzquierdo() != null) {
            izquierdo = nodo.getIzquierdo().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (izquierdo == null) {
            izquierdo = "_";
        }
        // evaluar el operando derecho
        String derecho = "_";
        if (nodo.getDerecho() != null) {
            derecho = nodo.getDerecho().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (derecho == null) {
            derecho = "_";
        }
        // emitir la conjuncion con temporal
        String temp = temporales.nuevoTemporal();
        // registrar el temporal como booleano
        tiposConocidos.put(temp, "booleano");
        cuartetas.add(new Cuarteta("&&", izquierdo, derecho, temp, inferirTipoDe(izquierdo, tiposConocidos), inferirTipoDe(derecho, tiposConocidos), "booleano"));
        return temp;
    }

    @Override
    public String visitarExprOr(ExprOr nodo) {
        // evaluar el operando izquierdo
        String izquierdo = "_";
        if (nodo.getIzquierdo() != null) {
            izquierdo = nodo.getIzquierdo().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (izquierdo == null) {
            izquierdo = "_";
        }
        // evaluar el operando derecho
        String derecho = "_";
        if (nodo.getDerecho() != null) {
            derecho = nodo.getDerecho().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (derecho == null) {
            derecho = "_";
        }
        // emitir la disyuncion con temporal
        String temp = temporales.nuevoTemporal();
        // registrar el temporal como booleano
        tiposConocidos.put(temp, "booleano");
        cuartetas.add(new Cuarteta("||", izquierdo, derecho, temp, inferirTipoDe(izquierdo, tiposConocidos), inferirTipoDe(derecho, tiposConocidos), "booleano"));
        return temp;
    }

    @Override
    public String visitarExprPrimitivo(ExprPrimitivo nodo) {
        // verificar si el valor es primitivo
        if (nodo.getValor() instanceof ValorPrimitivo) {
            // convertir el valor al tipo concreto
            ValorPrimitivo primitivo = (ValorPrimitivo) nodo.getValor();
            // devolver el identificador directo sin crear temporal
            if (primitivo.getTipo() == TipoPrimitivo.IDENTIFICADOR) {
                return primitivo.getValor();
            }
            // guardar el literal en un temporal
            String temp = temporales.nuevoTemporal();
            // inferir el tipo del literal
            String tipoLiteral = inferirTipoLiteral(primitivo.getValor());
            // registrar el temporal con el tipo inferido
            tiposConocidos.put(temp, tipoLiteral);
            cuartetas.add(new Cuarteta("=", primitivo.getValor(), "_", temp, tipoLiteral, "_", tipoLiteral));
            return temp;
        }
        return null;
    }
}
