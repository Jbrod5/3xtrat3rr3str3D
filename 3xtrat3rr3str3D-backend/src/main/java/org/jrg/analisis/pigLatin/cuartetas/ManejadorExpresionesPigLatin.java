package org.jrg.analisis.pigLatin.cuartetas;

import java.util.ArrayList;
import java.util.List;

import org.jrg.model.ast.pigLatin.ListaExpresiones;
import org.jrg.model.ast.pigLatin.ValorPrimitivo;
import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.expresion.ExprAccesoMiembroEstructura;
import org.jrg.model.ast.pigLatin.expresion.ExprAccesoPosicionArray;
import org.jrg.model.ast.pigLatin.expresion.ExprAnd;
import org.jrg.model.ast.pigLatin.expresion.ExprInstanciaObjeto;
import org.jrg.model.ast.pigLatin.expresion.ExprLlamadaFuncion;
import org.jrg.model.ast.pigLatin.expresion.ExprLlamadaMetodo;
import org.jrg.model.ast.pigLatin.expresion.ExprListaLiteral;
import org.jrg.model.ast.pigLatin.expresion.ExprMultiplicacionDivision;
import org.jrg.model.ast.pigLatin.expresion.ExprNegada;
import org.jrg.model.ast.pigLatin.expresion.ExprNegativa;
import org.jrg.model.ast.pigLatin.expresion.ExprOr;
import org.jrg.model.ast.pigLatin.expresion.ExprParentesis;
import org.jrg.model.ast.pigLatin.expresion.ExprPostDecremento;
import org.jrg.model.ast.pigLatin.expresion.ExprPostIncremento;
import org.jrg.model.ast.pigLatin.expresion.ExprPreDecremento;
import org.jrg.model.ast.pigLatin.expresion.ExprPreIncremento;
import org.jrg.model.ast.pigLatin.expresion.ExprPrimitivo;
import org.jrg.model.ast.pigLatin.expresion.ExprRelacional;
import org.jrg.model.ast.pigLatin.expresion.ExprSumaResta;
import org.jrg.model.ast.pigLatin.variable_asignable.ValorAsignableArray;
import org.jrg.model.ast.pigLatin.variable_asignable.ValorAsignableMiembroEstructura;
import org.jrg.model.ast.pigLatin.variable_asignable.ValorAsignableSimple;
import org.jrg.model.base.TipoPrimitivo;
import org.jrg.model.cuarteta.Cuarteta;

// generar cuartetas de expresiones y asignables en Pig Latin
public class ManejadorExpresionesPigLatin {

    // estado compartido de la generacion
    private final ContextoCuartetasPigLatin ctx;
    // generador duenio para el descenso recursivo
    private final GeneradorCuartetasPigLatin generador;

    // crear la manejadora con contexto y generador
    public ManejadorExpresionesPigLatin(ContextoCuartetasPigLatin ctx, GeneradorCuartetasPigLatin generador) {
        // asignar las dependencias recibidas
        this.ctx = ctx;
        this.generador = generador;
    }

    // devolver el identificador directamente sin cuartetas
    public String visitValorAsignableSimple(ValorAsignableSimple v) {
        // devolver el identificador directamente
        return v.getIdentificador();
    }

    // componer la referencia de arreglo con base e indice
    public String visitValorAsignableArray(ValorAsignableArray v) {
        // evaluar base del acceso
        String base = null;
        if (v.getBase() != null) {
            base = v.getBase().accept(generador);
        }

        // evaluar indice del acceso
        String idx = null;
        if (v.getIndice() != null) {
            idx = v.getIndice().accept(generador);
        }

        // usar guion bajo si la base es nula
        String textoBase = "_";
        if (base != null) {
            textoBase = base;
        }
        // usar guion bajo si el indice es nulo
        String textoIndice = "_";
        if (idx != null) {
            textoIndice = idx;
        }
        // componer referencia de arreglo
        return textoBase + "[" + textoIndice + "]";
    }

    // componer la referencia de miembro sin agregar cuartetas
    public String visitValorAsignableMiembroEstructura(ValorAsignableMiembroEstructura v) {
        // obtener la base del miembro
        String base = null;
        if (v.getBase() != null) {
            base = v.getBase().accept(generador);
        }
        // usar guion bajo si la base es nula
        String textoBase = "_";
        if (base != null) {
            textoBase = base;
        }
        // devolver la referencia compuesta sin agregar cuarteta a la lista
        return textoBase + "." + v.getMiembro();
    }

    // visitar la expresion interna del parentesis
    public String visitExprParentesis(ExprParentesis expr) {
        // visitar la expresion interna
        if (expr.getExpresion() != null) {
            return expr.getExpresion().accept(generador);
        }
        return null;
    }

    // instanciar un objeto con new y sus parametros
    public String visitExprInstanciaObjeto(ExprInstanciaObjeto expr) {
        // evaluar argumentos igual que en llamada a funcion
        List<String> args = new ArrayList<>();
        if (expr.getArgumentos() != null) {
            if (expr.getArgumentos() instanceof ListaExpresiones) {
                List<NodoAST> lista = ((ListaExpresiones) expr.getArgumentos()).getExpresiones();
                for (int i = 0; i < lista.size(); i++) {
                    String a = lista.get(i).accept(generador);
                    // usar guion bajo si el argumento es nulo
                    if (a == null) {
                        args.add("_");
                    } else {
                        args.add(a);
                    }
                }
            }
        }
        // agregar parametros a la lista de cuartetas
        for (int i = 0; i < args.size(); i++) {
            ctx.getCuartetas().add(new Cuarteta("param", args.get(i), "_", "_", ctx.inferirTipoDe(args.get(i), ctx.getTiposConocidos()), "_", "_"));
        }

        // agregar instancia a la lista de cuartetas
        String temp = ctx.getTemporales().nuevoTemporal();

        // registrar el temporal con el tipo de la clase
        ctx.getTiposConocidos().put(temp, expr.getTipo());
        ctx.getCuartetas().add(new Cuarteta("new", expr.getTipo(), String.valueOf(args.size()), temp, expr.getTipo(), "_", expr.getTipo()));
        return temp;
    }

    // llamar a una funcion con call y sus parametros
    public String visitExprLlamadaFuncion(ExprLlamadaFuncion expr) {
        // evaluar argumentos
        List<String> args = new ArrayList<>();
        if (expr.getArgumentos() != null) {
            if (expr.getArgumentos() instanceof ListaExpresiones) {
                List<NodoAST> lista = ((ListaExpresiones) expr.getArgumentos()).getExpresiones();
                for (int i = 0; i < lista.size(); i++) {
                    String a = lista.get(i).accept(generador);
                    // usar guion bajo si el argumento es nulo
                    if (a == null) {
                        args.add("_");
                    } else {
                        args.add(a);
                    }
                }
            }
        }
        // agregar parametros a la lista de cuartetas
        for (int i = 0; i < args.size(); i++) {
            ctx.getCuartetas().add(new Cuarteta("param", args.get(i), "_", "_", ctx.inferirTipoDe(args.get(i), ctx.getTiposConocidos()), "_", "_"));
        }
        // agregar llamada a la lista de cuartetas
        String temp = ctx.getTemporales().nuevoTemporal();
        ctx.getCuartetas().add(new Cuarteta("call", expr.getNombre(), String.valueOf(args.size()), temp, "_", "_", "_"));
        return temp;
    }

    // llamar a un metodo con el objeto como primer parametro
    public String visitExprLlamadaMetodo(ExprLlamadaMetodo expr) {
        String obj = null;
        if (expr.getObjeto() != null) {
            obj = expr.getObjeto().accept(generador);
        }
        // evaluar argumentos
        List<String> args = new ArrayList<>();
        if (expr.getArgumentos() != null) {
            if (expr.getArgumentos() instanceof ListaExpresiones) {
                List<NodoAST> lista = ((ListaExpresiones) expr.getArgumentos()).getExpresiones();
                for (int i = 0; i < lista.size(); i++) {
                    String a = lista.get(i).accept(generador);
                    // usar guion bajo si el argumento es nulo
                    if (a == null) {
                        args.add("_");
                    } else {
                        args.add(a);
                    }
                }
            }
        }
        // usar guion bajo si el objeto es nulo
        String textoObjeto = "_";
        if (obj != null) {
            textoObjeto = obj;
        }
        // agregar parametro del objeto a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("param", textoObjeto, "_", "_", ctx.inferirTipoDe(obj, ctx.getTiposConocidos()), "_", "_"));
        // agregar parametros de argumentos a la lista de cuartetas
        for (int i = 0; i < args.size(); i++) {
            ctx.getCuartetas().add(new Cuarteta("param", args.get(i), "_", "_", ctx.inferirTipoDe(args.get(i), ctx.getTiposConocidos()), "_", "_"));
        }
        // agregar la llamada al metodo a la lista de cuartetas
        String temp = ctx.getTemporales().nuevoTemporal();
        ctx.getCuartetas().add(new Cuarteta("call_method", expr.getNombre(), String.valueOf(args.size() + 1), temp, "_", "_", "_"));
        return temp;
    }

    // generar acceso a array con temporal
    public String visitExprAccesoPosicionArray(ExprAccesoPosicionArray expr) {
        // evaluar array e indice
        String arr = null;
        String idx = null;
        if (expr.getArray() != null) {
            arr = expr.getArray().accept(generador);
        }
        if (expr.getIndice() != null) {
            idx = expr.getIndice().accept(generador);
        }
        // usar guion bajo si el array es nulo
        String textoArray = "_";
        if (arr != null) {
            textoArray = arr;
        }
        // usar guion bajo si el indice es nulo
        String textoIndice = "_";
        if (idx != null) {
            textoIndice = idx;
        }
        // generar acceso a array con temporal
        String temp = ctx.getTemporales().nuevoTemporal();
        ctx.getCuartetas().add(new Cuarteta("=[]", textoArray, textoIndice, temp, "_", "entero", "_"));
        return temp;
    }

    // generar acceso a miembro con temporal
    public String visitExprAccesoMiembroEstructura(ExprAccesoMiembroEstructura expr) {
        // visitar el objeto
        String obj = null;
        if (expr.getObjeto() != null) {
            obj = expr.getObjeto().accept(generador);
        }
        // usar guion bajo si el objeto es nulo
        String textoObjeto = "_";
        if (obj != null) {
            textoObjeto = obj;
        }
        // agregar acceso a miembro a la lista de cuartetas
        String temp = ctx.getTemporales().nuevoTemporal();
        ctx.getCuartetas().add(new Cuarteta(".", textoObjeto, expr.getMiembro(), temp, "_", "_", "_"));
        return temp;
    }

    // generar post incremento con suma de uno
    public String visitExprPostIncremento(ExprPostIncremento expr) {
        // evaluar variable a incrementar
        String var = expr.getVariable().accept(generador);
        // usar la variable o guion bajo si es nula
        String textoVar = "_";
        if (var != null) {
            textoVar = var;
        }
        ctx.getCuartetas().add(new Cuarteta("+", textoVar, "1", textoVar, ctx.inferirTipoDe(var, ctx.getTiposConocidos()), "entero", ctx.inferirTipoDe(var, ctx.getTiposConocidos())));

        return var;
    }

    // generar post decremento con resta de uno
    public String visitExprPostDecremento(ExprPostDecremento expr) {
        // evaluar variable a decrementar
        String var = expr.getVariable().accept(generador);
        // usar la variable o guion bajo si es nula
        String textoVar = "_";
        if (var != null) {
            textoVar = var;
        }
        ctx.getCuartetas().add(new Cuarteta("-", textoVar, "1", textoVar, ctx.inferirTipoDe(var, ctx.getTiposConocidos()), "entero", ctx.inferirTipoDe(var, ctx.getTiposConocidos())));

        return var;
    }

    // visitar lista literal sin generar cuartetas
    public String visitExprListaLiteral(ExprListaLiteral expr) {
        // no genera cuarteta por si solo en este contexto
        return null;
    }

    // generar menos unario con opcode propio
    public String visitExprNegativa(ExprNegativa expr) {
        // visitar expresion
        String val = null;
        if (expr.getExpresion() != null) {
            val = expr.getExpresion().accept(generador);
        }
        // crear temporal para el resultado
        String temp = ctx.getTemporales().nuevoTemporal();
        // inferir el tipo desde el operando
        String tipoNeg = ctx.inferirTipoDe(val, ctx.getTiposConocidos());
        // registrar el temporal con el tipo inferido
        ctx.getTiposConocidos().put(temp, tipoNeg);
        // usar guion bajo si el valor es nulo
        String textoVal = "_";
        if (val != null) {
            textoVal = val;
        }
        // agregar menos unario con opcode propio a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("uminus", textoVal, "_", temp, tipoNeg, "_", tipoNeg));
        return temp;
    }

    // generar negacion logica con temporal booleano
    public String visitExprNegada(ExprNegada expr) {
        // visitar expresion
        String val = null;
        if (expr.getExpresion() != null) {
            val = expr.getExpresion().accept(generador);
        }
        String op = expr.getOperador();
        String temp = ctx.getTemporales().nuevoTemporal();
        // registrar el temporal como booleano
        ctx.getTiposConocidos().put(temp, "booleano");
        // usar guion bajo si el valor es nulo
        String textoVal = "_";
        if (val != null) {
            textoVal = val;
        }
        ctx.getCuartetas().add(new Cuarteta("!", textoVal, "_", temp, ctx.inferirTipoDe(val, ctx.getTiposConocidos()), "_", "booleano"));
        return temp;
    }

    // generar pre incremento con suma de uno
    public String visitExprPreIncremento(ExprPreIncremento expr) {
        String var = expr.getVariable().accept(generador);
        // usar la variable o guion bajo si es nula
        String textoVar = "_";
        if (var != null) {
            textoVar = var;
        }
        ctx.getCuartetas().add(new Cuarteta("+", textoVar, "1", textoVar, ctx.inferirTipoDe(var, ctx.getTiposConocidos()), "entero", ctx.inferirTipoDe(var, ctx.getTiposConocidos())));
        return var;
    }

    // generar pre decremento con resta de uno
    public String visitExprPreDecremento(ExprPreDecremento expr) {
        String var = expr.getVariable().accept(generador);
        // usar la variable o guion bajo si es nula
        String textoVar = "_";
        if (var != null) {
            textoVar = var;
        }
        ctx.getCuartetas().add(new Cuarteta("-", textoVar, "1", textoVar, ctx.inferirTipoDe(var, ctx.getTiposConocidos()), "entero", ctx.inferirTipoDe(var, ctx.getTiposConocidos())));
        return var;
    }

    // generar multiplicacion o division con tipo inferido
    public String visitExprMultiplicacionDivision(ExprMultiplicacionDivision expr) {
        // evaluar operandos
        String izq = null;
        String der = null;
        if (expr.getOperandoIzquierdo() != null) {
            izq = expr.getOperandoIzquierdo().accept(generador);
        }
        if (expr.getOperandoDerecho() != null) {
            der = expr.getOperandoDerecho().accept(generador);
        }
        String op = expr.getOperador();
        String temp = ctx.getTemporales().nuevoTemporal();
        // inferir el tipo resultado de la operacion
        String tipoResMult = ctx.tipoResultadoAritmetico(izq, der);
        // registrar el temporal con el tipo inferido
        ctx.getTiposConocidos().put(temp, tipoResMult);
        // usar guion bajo si el izquierdo es nulo
        String textoIzq = "_";
        if (izq != null) {
            textoIzq = izq;
        }
        // usar guion bajo si el derecho es nulo
        String textoDer = "_";
        if (der != null) {
            textoDer = der;
        }
        ctx.getCuartetas().add(new Cuarteta(op, textoIzq, textoDer, temp, ctx.tipoAritmetico(izq), ctx.tipoAritmetico(der), tipoResMult));
        return temp;
    }

    // generar suma resta o concatenacion con tipo inferido
    public String visitExprSumaResta(ExprSumaResta expr) {
        String izq = expr.getOperandoIzquierdo().accept(generador);
        String der = expr.getOperandoDerecho().accept(generador);
        String temp = ctx.getTemporales().nuevoTemporal();
        // inferir los tipos de los operandos
        String tipoIzq = ctx.inferirTipoDe(izq, ctx.getTiposConocidos());
        String tipoDer = ctx.inferirTipoDe(der, ctx.getTiposConocidos());
        // inferir el tipo resultado de la operacion
        String tipoResSuma = ctx.tipoResultadoAritmetico(izq, der);
        // usar cadena cuando se concatena texto con mas
        if ("+".equals(expr.getOperador())) {
            boolean izqEsCadena = ctx.esTipoCadena(tipoIzq);
            boolean derEsCadena = ctx.esTipoCadena(tipoDer);
            if (izqEsCadena || derEsCadena) {
                tipoResSuma = "cadena";
            }
        }
        // registrar el temporal con el tipo inferido
        ctx.getTiposConocidos().put(temp, tipoResSuma);
        ctx.getCuartetas().add(new Cuarteta(expr.getOperador(), izq, der, temp, ctx.tipoAritmetico(izq), ctx.tipoAritmetico(der), tipoResSuma));
        return temp;
    }

    // generar comparacion relacional con temporal booleano
    public String visitExprRelacional(ExprRelacional expr) {
        String izq = expr.getOperandoIzquierdo().accept(generador);
        String der = expr.getOperandoDerecho().accept(generador);
        String temp = ctx.getTemporales().nuevoTemporal();
        // registrar el temporal como booleano
        ctx.getTiposConocidos().put(temp, "booleano");
        ctx.getCuartetas().add(new Cuarteta(expr.getOperador(), izq, der, temp, ctx.inferirTipoDe(izq, ctx.getTiposConocidos()), ctx.inferirTipoDe(der, ctx.getTiposConocidos()), "booleano"));
        return temp;
    }

    // generar conjuncion logica con temporal booleano
    public String visitExprAnd(ExprAnd expr) {
        String izq = expr.getOperandoIzquierdo().accept(generador);
        String der = expr.getOperandoDerecho().accept(generador);
        String temp = ctx.getTemporales().nuevoTemporal();
        // registrar el temporal como booleano
        ctx.getTiposConocidos().put(temp, "booleano");
        ctx.getCuartetas().add(new Cuarteta("&&", izq, der, temp, ctx.inferirTipoDe(izq, ctx.getTiposConocidos()), ctx.inferirTipoDe(der, ctx.getTiposConocidos()), "booleano"));
        return temp;
    }

    // generar disyuncion logica con temporal booleano
    public String visitExprOr(ExprOr expr) {
        String izq = expr.getOperandoIzquierdo().accept(generador);
        String der = expr.getOperandoDerecho().accept(generador);
        String temp = ctx.getTemporales().nuevoTemporal();
        // registrar el temporal como booleano
        ctx.getTiposConocidos().put(temp, "booleano");
        ctx.getCuartetas().add(new Cuarteta("||", izq, der, temp, ctx.inferirTipoDe(izq, ctx.getTiposConocidos()), ctx.inferirTipoDe(der, ctx.getTiposConocidos()), "booleano"));
        return temp;
    }

    // generar carga de literal en temporal o devolver identificador
    public String visitExprPrimitivo(ExprPrimitivo expr) {
        if (expr.getValor() instanceof ValorPrimitivo) {
            ValorPrimitivo vp = (ValorPrimitivo) expr.getValor();
            // si es identificador, devolverlo directo sin crear temporal
            if (vp.getTipoDato() == TipoPrimitivo.IDENTIFICADOR) {
                return vp.getValor();
            }
            // si es literal, meterlo en un temporal
            String temp = ctx.getTemporales().nuevoTemporal();
            // inferir el tipo del literal
            String tipoLiteral = ctx.inferirTipoLiteral(vp.getValor());
            // registrar el temporal con el tipo inferido
            ctx.getTiposConocidos().put(temp, tipoLiteral);
            ctx.getCuartetas().add(new Cuarteta("=", vp.getValor(), "_", temp, tipoLiteral, "_", tipoLiteral));
            return temp;
        }
        return null;
    }
}
