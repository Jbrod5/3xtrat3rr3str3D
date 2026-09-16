package org.jrg.analisis.pigLatin.cuartetas;

import java.util.ArrayList;
import java.util.List;

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

    // crear el generador
    public GeneradorCuartetasPigLatin() {
        // inicializar la lista y el generador
        this.cuartetas = new ArrayList<>();
        this.temporales = new GeneradorTemporales();
        this.etiquetaBreakActual = null;
        this.etiquetaContinueActual = null;
    }

    // obtener la lista de cuartetas generadas
    public List<Cuarteta> getCuartetas() {
        return cuartetas;
    }

    // ==================== PROGRAMA Y SECCIONES ====================

    @Override
    public String visitPrograma(Programa nodo) {
        // visitar seccion maior si existe
        if (nodo.getSeccionMaior() != null) {
            nodo.getSeccionMaior().accept(this);
        }
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
            cuartetas.add(new Cuarteta("param", args.get(i), "_", "_"));
        }
        // emitir instancia
        String temp = temporales.nuevoTemporal();
        cuartetas.add(new Cuarteta("new", expr.getTipo(), String.valueOf(args.size()), temp));
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
            cuartetas.add(new Cuarteta("param", args.get(i), "_", "_"));
        }
        // emitir llamada
        String temp = temporales.nuevoTemporal();
        cuartetas.add(new Cuarteta("call", expr.getNombre(), String.valueOf(args.size()), temp));
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
        cuartetas.add(new Cuarteta("param", obj != null ? obj : "_", "_", "_"));
        // emitir parametros de argumentos
        for (int i = 0; i < args.size(); i++) {
            cuartetas.add(new Cuarteta("param", args.get(i), "_", "_"));
        }
        // emitir llamada al metodo
        String temp = temporales.nuevoTemporal();
        cuartetas.add(new Cuarteta("call_method", expr.getNombre(), String.valueOf(args.size() + 1), temp));
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
        cuartetas.add(new Cuarteta("=[]", arr != null ? arr : "_", idx != null ? idx : "_", temp));
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
        cuartetas.add(new Cuarteta(".", obj != null ? obj : "_", expr.getMiembro(), temp));
        return temp;
    }

    @Override
    public String visitExprPostIncremento(ExprPostIncremento expr) {
        String var = expr.getVariable().accept(this);
        cuartetas.add(new Cuarteta("+", var != null ? var : "_", "1", var != null ? var : "_"));
        return var;
    }

    @Override
    public String visitExprPostDecremento(ExprPostDecremento expr) {
        String var = expr.getVariable().accept(this);
        cuartetas.add(new Cuarteta("-", var != null ? var : "_", "1", var != null ? var : "_"));
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
        // usar el operador negativo
        String op = expr.getOperador();
        String temp = temporales.nuevoTemporal();
        cuartetas.add(new Cuarteta(op, val != null ? val : "_", "_", temp));
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
        cuartetas.add(new Cuarteta("!", val != null ? val : "_", "_", temp));
        return temp;
    }

    @Override
    public String visitExprPreIncremento(ExprPreIncremento expr) {
        String var = expr.getVariable().accept(this);
        cuartetas.add(new Cuarteta("+", var != null ? var : "_", "1", var != null ? var : "_"));
        return var;
    }

    @Override
    public String visitExprPreDecremento(ExprPreDecremento expr) {
        String var = expr.getVariable().accept(this);
        cuartetas.add(new Cuarteta("-", var != null ? var : "_", "1", var != null ? var : "_"));
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
        cuartetas.add(new Cuarteta(op, izq != null ? izq : "_", der != null ? der : "_", temp));
        return temp;
    }

    @Override
    public String visitExprSumaResta(ExprSumaResta expr) {
        String izq = expr.getOperandoIzquierdo().accept(this);
        String der = expr.getOperandoDerecho().accept(this);
        String temp = temporales.nuevoTemporal();
        cuartetas.add(new Cuarteta(expr.getOperador(), izq, der, temp));
        return temp;
    }

    @Override
    public String visitExprRelacional(ExprRelacional expr) {
        String izq = expr.getOperandoIzquierdo().accept(this);
        String der = expr.getOperandoDerecho().accept(this);
        String temp = temporales.nuevoTemporal();
        cuartetas.add(new Cuarteta(expr.getOperador(), izq, der, temp));
        return temp;
    }

    @Override
    public String visitExprAnd(ExprAnd expr) {
        String izq = expr.getOperandoIzquierdo().accept(this);
        String der = expr.getOperandoDerecho().accept(this);
        String temp = temporales.nuevoTemporal();
        cuartetas.add(new Cuarteta("&&", izq, der, temp));
        return temp;
    }

    @Override
    public String visitExprOr(ExprOr expr) {
        String izq = expr.getOperandoIzquierdo().accept(this);
        String der = expr.getOperandoDerecho().accept(this);
        String temp = temporales.nuevoTemporal();
        cuartetas.add(new Cuarteta("||", izq, der, temp));
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
            cuartetas.add(new Cuarteta("=", vp.getValor(), "_", temp));
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
            cuartetas.add(new Cuarteta("param", args.get(i), "_", "_"));
        }
        // crear objeto
        String temp = temporales.nuevoTemporal();
        cuartetas.add(new Cuarteta("new", nodo.getTipo(), String.valueOf(args.size()), temp));
        // asignar a variable
        cuartetas.add(new Cuarteta(":=", temp, "_", nodo.getIdentificador()));
        return null;
    }

    @Override
    public String visitDeclEstructuraConValores(DeclEstructuraConValores nodo) {
        // crear nueva instancia de estructura
        String temp = temporales.nuevoTemporal();
        cuartetas.add(new Cuarteta("new_struct", nodo.getTipo(), "_", temp));
        // evaluar los atributos
        if (nodo.getAtributos() instanceof ListaAtributosInstancia) {
            List<NodoAST> attrs = ((ListaAtributosInstancia) nodo.getAtributos()).getAtributos();
            for (int i = 0; i < attrs.size(); i++) {
                String val = attrs.get(i).accept(this);
                cuartetas.add(new Cuarteta(".,=", temp, String.valueOf(i), val != null ? val : "_"));
            }
        }
        // asignar la estructura a la variable
        cuartetas.add(new Cuarteta(":=", temp, "_", nodo.getIdentificador()));
        return null;
    }

    @Override
    public String visitDeclConTipoYValor(DeclConTipoYValor nodo) {
        // si hay valor inicial, emitir asignacion
        if (nodo.getValor() != null) {
            String val = nodo.getValor().accept(this);
            cuartetas.add(new Cuarteta(":=", val != null ? val : "_", "_", nodo.getIdentificador()));
        }
        return null;
    }

    @Override
    public String visitDeclBooleanaImplicita(DeclBooleanaImplicita nodo) {
        // emitir asignacion con el valor implicito del nodo
        cuartetas.add(new Cuarteta(":=", nodo.getValor(), "_", nodo.getIdentificador()));
        return null;
    }

    @Override
    public String visitDeclArraySinDatos(DeclArraySinDatos nodo) {
        String tamano = null;
        if (nodo.getTamano() != null) {
            tamano = nodo.getTamano().accept(this);
        }
        cuartetas.add(new Cuarteta("alloc", tamano != null ? tamano : "_", "_", nodo.getIdentificador()));
        return null;
    }

    @Override
    public String visitDeclArrayConDatos(DeclArrayConDatos nodo) {
        String tamano = null;
        if (nodo.getTamano() != null) {
            tamano = nodo.getTamano().accept(this);
        }
        cuartetas.add(new Cuarteta("alloc", tamano != null ? tamano : "_", "_", nodo.getIdentificador()));
        if (nodo.getValores() != null) {
            for (int i = 0; i < nodo.getValores().size(); i++) {
                String val = nodo.getValores().get(i).accept(this);
                cuartetas.add(new Cuarteta("[]=", nodo.getIdentificador(), String.valueOf(i), val != null ? val : "_"));
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
        cuartetas.add(new Cuarteta("alloc", tamano != null ? tamano : "_", "_", nodo.getIdentificador()));
        return null;
    }

    // ==================== ASIGNACIONES ====================

    @Override
    public String visitAsignacionGeneral(AsignacionGeneral a) {
        String izq = a.getVariable().accept(this);
        String der = a.getValor().accept(this);
        cuartetas.add(new Cuarteta(":=", der, "_", izq));
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
            cuartetas.add(new Cuarteta("goto", this.etiquetaBreakActual, "_", "_"));
        }
        return null;
    }

    @Override
    public String visitStmtPerge(StmtPerge stmt) {
        // emitir salto a la etiqueta de continue actual si existe
        if (this.etiquetaContinueActual != null) {
            cuartetas.add(new Cuarteta("goto", this.etiquetaContinueActual, "_", "_"));
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
        cuartetas.add(new Cuarteta("if_false", cond != null ? cond : "_", Lelse, "_"));
        // bloque principal
        if (stmt.getBloque() != null) {
            stmt.getBloque().accept(this);
        }
        // salto al final
        cuartetas.add(new Cuarteta("goto", Lfin, "_", "_"));
        // etiqueta else
        cuartetas.add(new Cuarteta("label", Lelse, "_", "_"));
        // bloque else
        if (stmt.getBloqueAliter() != null) {
            stmt.getBloqueAliter().accept(this);
        }
        // etiqueta final
        cuartetas.add(new Cuarteta("label", Lfin, "_", "_"));
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
        cuartetas.add(new Cuarteta("label", Linicio, "_", "_"));
        // evaluar condicion
        String cond = null;
        if (ciclo.getCondicion() != null) {
            cond = ciclo.getCondicion().accept(this);
        }
        // salto si es falso
        cuartetas.add(new Cuarteta("if_false", cond != null ? cond : "_", Lfin, "_"));
        // bloque
        if (ciclo.getBloque() != null) {
            ciclo.getBloque().accept(this);
        }
        // salto al inicio
        cuartetas.add(new Cuarteta("goto", Linicio, "_", "_"));
        // etiqueta final
        cuartetas.add(new Cuarteta("label", Lfin, "_", "_"));
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
        cuartetas.add(new Cuarteta("label", Linicio, "_", "_"));
        // bloque (se ejecuta al menos una vez)
        if (ciclo.getBloque() != null) {
            ciclo.getBloque().accept(this);
        }
        // etiqueta continue (donde salta perge)
        cuartetas.add(new Cuarteta("label", Lcont, "_", "_"));
        // evaluar condicion
        String cond = null;
        if (ciclo.getCondicion() != null) {
            cond = ciclo.getCondicion().accept(this);
        }
        // si la condicion es verdadera, volver al inicio
        cuartetas.add(new Cuarteta("if_false", cond != null ? cond : "_", Lfin, "_"));
        cuartetas.add(new Cuarteta("goto", Linicio, "_", "_"));
        // etiqueta final
        cuartetas.add(new Cuarteta("label", Lfin, "_", "_"));
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
        cuartetas.add(new Cuarteta("label", Linicio, "_", "_"));
        // evaluar condicion
        String cond = null;
        if (ciclo.getCondicion() != null) {
            cond = ciclo.getCondicion().accept(this);
        }
        // salto si falso
        cuartetas.add(new Cuarteta("if_false", cond != null ? cond : "_", Lfin, "_"));
        // bloque
        if (ciclo.getBloque() != null) {
            ciclo.getBloque().accept(this);
        }
        // paso
        if (ciclo.getPaso() != null) {
            ciclo.getPaso().accept(this);
        }
        // salto al inicio
        cuartetas.add(new Cuarteta("goto", Linicio, "_", "_"));
        // etiqueta final
        cuartetas.add(new Cuarteta("label", Lfin, "_", "_"));
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
            cuartetas.add(new Cuarteta(":=", val != null ? val : "_", "_", init.getIdentificador()));
        }
        return null;
    }

    @Override
    public String visitInitPerAsig(InitPerAsig init) {
        String var = init.getVariable().accept(this);
        String val = init.getValor().accept(this);
        cuartetas.add(new Cuarteta(":=", val != null ? val : "_", "_", var != null ? var : "_"));
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
        cuartetas.add(new Cuarteta(":=", val != null ? val : "_", "_", var != null ? var : "_"));
        return null;
    }

    // ==================== LECTURA ====================

    @Override
    public String visitLecturaConsolaSimple(LecturaConsolaSimple lectura) {
        // emitir lectura sin variable destino
        cuartetas.add(new Cuarteta("read", "_", "_", "_"));
        return null;
    }

    @Override
    public String visitLecturaConsolaAVariable(LecturaConsolaAVariable lectura) {
        // leer a variable
        String var = null;
        if (lectura.getVariable() != null) {
            var = lectura.getVariable().accept(this);
        }
        cuartetas.add(new Cuarteta("read", "_", "_", var != null ? var : "_"));
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
                    cuartetas.add(new Cuarteta("print", val != null ? val : "_", "_", "_"));
                }
            }
        }
        return null;
    }
}
