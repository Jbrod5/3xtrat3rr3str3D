package org.jrg.analisis.yLenguaje.cuartetas;

import java.util.ArrayList;
import java.util.List;

import org.jrg.model.ast.yLenguaje.ValorPrimitivo;
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
import org.jrg.model.base.TipoPrimitivo;
import org.jrg.model.cuarteta.Cuarteta;

// generar cuartetas de expresiones en el lenguaje Y
public class ManejadorExpresionesY {

    private final ContextoCuartetasY ctx;
    private final GeneradorCuartetasY generador;

    // crear la manejadora con contexto y generador
    public ManejadorExpresionesY(ContextoCuartetasY ctx, GeneradorCuartetasY generador) {
        this.ctx = ctx;
        this.generador = generador;
    }

    // visitar la expresion interna del parentesis
    public String visitarExprParentesis(ExprParentesis nodo) {
        // visitar la expresion interna si existe
        if (nodo.getExpresion() != null) {
            return nodo.getExpresion().accept(generador);
        }

        return null;
    }

    // llamar a una funcion con call y sus parametros
    public String visitarExprLlamadaFuncion(ExprLlamadaFuncion nodo) {
        // evaluar los argumentos de la llamada
        List<String> argumentos = new ArrayList<>();

        if (nodo.getArgumentos() != null) {

            for (int i = 0; i < nodo.getArgumentos().size(); i++) {

                // evaluar el argumento actual
                String argumento = nodo.getArgumentos().get(i).accept(generador);

                if (argumento == null) {
                    argumento = "_";
                }

                // agregar el argumento a la lista
                argumentos.add(argumento);

            }

        }
        // agregar un param por cada argumento a la lista de cuartetas
        for (int i = 0; i < argumentos.size(); i++) {
            ctx.getCuartetas().add(new Cuarteta("param", argumentos.get(i), "_", "_", ctx.inferirTipoDe(argumentos.get(i), ctx.getTiposConocidos()), "_", "_"));
        }

        // agregar la llamada y guardar el resultado en un temporal a la lista de cuartetas
        String temp = ctx.getTemporales().nuevoTemporal();
        ctx.getCuartetas().add(new Cuarteta("call", nodo.getNombre(), String.valueOf(argumentos.size()), temp, "_", "_", "_"));

        return temp;
    }

    // generar acceso a arreglo con temporal
    public String visitarExprAccesoArray(ExprAccesoArray nodo) {
        // evaluar el objeto del acceso
        String objeto = "_";
        if (nodo.getObjeto() != null) {
            objeto = nodo.getObjeto().accept(generador);
        }

        if (objeto == null) {
            objeto = "_";
        }

        // evaluar el indice del acceso
        String indice = "_";
        if (nodo.getIndice() != null) {
            indice = nodo.getIndice().accept(generador);
        }

        // si es nulo usar lo de respaldo
        if (indice == null) {
            indice = "_";
        }

        // generar el acceso a arreglo con temporal
        String temp = ctx.getTemporales().nuevoTemporal();
        ctx.getCuartetas().add(new Cuarteta("=[]", objeto, indice, temp, "_", "entero", "_"));

        return temp;
    }

    // generar acceso a miembro con temporal
    public String visitarExprAccesoMiembro(ExprAccesoMiembro nodo) {
        // evaluar el objeto del acceso
        String objeto = "_";
        if (nodo.getObjeto() != null) {
            objeto = nodo.getObjeto().accept(generador);
        }

        if (objeto == null) {
            objeto = "_";
        }

        // agregar el acceso a miembro con temporal a la lista de cuartetas
        String temp = ctx.getTemporales().nuevoTemporal();
        ctx.getCuartetas().add(new Cuarteta(".", objeto, nodo.getMiembro(), temp, "_", "_", "_"));

        return temp;
    }

    // generar post incremento sobre la misma variable
    public String visitarExprPostIncremento(ExprPostIncremento nodo) {
        // evaluar la variable
        String variable = "_";
        if (nodo.getVariable() != null) {
            variable = nodo.getVariable().accept(generador);
        }

        if (variable == null) {
            variable = "_";
        }

        // agregar el incremento sobre la misma variable a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("+", variable, "1", variable, ctx.inferirTipoDe(variable, ctx.getTiposConocidos()), "entero", ctx.inferirTipoDe(variable, ctx.getTiposConocidos())));

        return variable;
    }

    // generar post decremento sobre la misma variable
    public String visitarExprPostDecremento(ExprPostDecremento nodo) {
        // evaluar la variable
        String variable = "_";
        if (nodo.getVariable() != null) {
            variable = nodo.getVariable().accept(generador);
        }

        if (variable == null) {
            variable = "_";
        }

        // agregar el decremento sobre la misma variable a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("-", variable, "1", variable, ctx.inferirTipoDe(variable, ctx.getTiposConocidos()), "entero", ctx.inferirTipoDe(variable, ctx.getTiposConocidos())));

        return variable;
    }

    // visitar lista literal sin generar cuartetas
    public String visitarExprListaLiteral(ExprListaLiteral nodo) {
        // no genera cuarteta por si solo en este contexto
        return null;
    }

    // generar menos unario con opcode propio
    public String visitarExprNegativa(ExprNegativa nodo) {
        // evaluar la expresion interna
        String valor = "_";
        if (nodo.getExpresion() != null) {
            valor = nodo.getExpresion().accept(generador);
        }

        // usar valor por defecto si el resultado es nulo
        if (valor == null) {
            valor = "_";
        }

        // agregar la negacion aritmetica con temporal a la lista de cuartetas :D
        String temp = ctx.getTemporales().nuevoTemporal();

        // adivinar el tipo desde el operando
        String tipoNeg = ctx.inferirTipoDe(valor, ctx.getTiposConocidos());

        // registrar el temporal con el tipo inferido
        ctx.getTiposConocidos().put(temp, tipoNeg);

        // agregar menos unario con opcode propio a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("uminus", valor, "_", temp, tipoNeg, "_", tipoNeg));

        return temp;
    }

    // generar negacion logica con temporal booleano
    public String visitarExprNegada(ExprNegada nodo) {

        // evaluar la expresion interna
        String valor = "_";
        if (nodo.getExpresion() != null) {
            valor = nodo.getExpresion().accept(generador);
        }

        if (valor == null) {
            valor = "_";
        }

        // agregar la negacion logica con temporal a la lista de cuartetas
        String temp = ctx.getTemporales().nuevoTemporal();
        // registrar el temporal como booleano
        ctx.getTiposConocidos().put(temp, "booleano");
        ctx.getCuartetas().add(new Cuarteta("!", valor, "_", temp, ctx.inferirTipoDe(valor, ctx.getTiposConocidos()), "_", "booleano"));

        return temp;

    }

    // generar multiplicacion o division con tipo inferido
    public String visitarExprMultiplicacionDivision(ExprMultiplicacionDivision nodo) {

        // evaluar el operando izquierdo
        String izquierdo = "_";
        if (nodo.getIzquierdo() != null) {
            izquierdo = nodo.getIzquierdo().accept(generador);
        }

        if (izquierdo == null) {
            izquierdo = "_";
        }

        // evaluar el operando derecho
        String derecho = "_";
        if (nodo.getDerecho() != null) {
            derecho = nodo.getDerecho().accept(generador);
        }

        // si es nulo usar lo de respaldo
        if (derecho == null) {
            derecho = "_";
        }

        // agregar la operacion con temporal a la lista de cuartetas
        String temp = ctx.getTemporales().nuevoTemporal();

        // adivinar que tipo sale de la cuenta
        String tipoResMult = ctx.tipoResultadoAritmetico(izquierdo, derecho);

        // registrar el temporal con el tipo inferido
        ctx.getTiposConocidos().put(temp, tipoResMult);
        ctx.getCuartetas().add(new Cuarteta(nodo.getOperador(), izquierdo, derecho, temp, ctx.tipoAritmetico(izquierdo), ctx.tipoAritmetico(derecho), tipoResMult));

        return temp;
    }

    // generar suma resta o concatenacion con tipo inferido
    public String visitarExprSumaResta(ExprSumaResta nodo) {
        // evaluar el operando izquierdo
        String izquierdo = "_";
        if (nodo.getIzquierdo() != null) {
            izquierdo = nodo.getIzquierdo().accept(generador);
        }

        if (izquierdo == null) {
            izquierdo = "_";
        }

        // evaluar el operando derecho
        String derecho = "_";
        if (nodo.getDerecho() != null) {
            derecho = nodo.getDerecho().accept(generador);
        }

        if (derecho == null) {
            derecho = "_";
        }

        // agregar la operacion con temporal a la lista de cuartetas
        String temp = ctx.getTemporales().nuevoTemporal();

        // adivinar los tipos de los dos lados
        String tipoIzqSuma = ctx.inferirTipoDe(izquierdo, ctx.getTiposConocidos());
        String tipoDerSuma = ctx.inferirTipoDe(derecho, ctx.getTiposConocidos());

        // adivinar que tipo sale de la cuenta
        String tipoResSuma = ctx.tipoResultadoAritmetico(izquierdo, derecho);

        // usar cadena cuando se concatena texto con mas
        if ("+".equals(nodo.getOperador())) {
            boolean izqEsCadenaSuma = ctx.esTipoCadena(tipoIzqSuma);
            boolean derEsCadenaSuma = ctx.esTipoCadena(tipoDerSuma);

            if (izqEsCadenaSuma || derEsCadenaSuma) {
                tipoResSuma = "cadena";
            }
        }

        // registrar el temporal con el tipo inferido
        ctx.getTiposConocidos().put(temp, tipoResSuma);
        ctx.getCuartetas().add(new Cuarteta(nodo.getOperador(), izquierdo, derecho, temp, ctx.tipoAritmetico(izquierdo), ctx.tipoAritmetico(derecho), tipoResSuma));

        return temp;
    }

    // generar comparacion relacional con temporal booleano
    public String visitarExprRelacional(ExprRelacional nodo) {
        // evaluar el operando izquierdo
        String izquierdo = "_";
        if (nodo.getIzquierdo() != null) {
            izquierdo = nodo.getIzquierdo().accept(generador);
        }

        if (izquierdo == null) {
            izquierdo = "_";
        }

        // evaluar el operando derecho
        String derecho = "_";
        if (nodo.getDerecho() != null) {
            derecho = nodo.getDerecho().accept(generador);
        }

        // usar valor por defecto si el resultado es nulo
        if (derecho == null) {
            derecho = "_";
        }

        // agregar la comparacion con temporal a la lista de cuartetas
        String temp = ctx.getTemporales().nuevoTemporal();

        // registrar el temporal como booleano
        ctx.getTiposConocidos().put(temp, "booleano");
        ctx.getCuartetas().add(new Cuarteta(nodo.getOperador(), izquierdo, derecho, temp, ctx.inferirTipoDe(izquierdo, ctx.getTiposConocidos()), ctx.inferirTipoDe(derecho, ctx.getTiposConocidos()), "booleano"));

        return temp;
    }

    // generar conjuncion logica con temporal booleano
    public String visitarExprAnd(ExprAnd nodo) {
        // evaluar el operando izquierdo
        String izquierdo = "_";
        if (nodo.getIzquierdo() != null) {
            izquierdo = nodo.getIzquierdo().accept(generador);
        }

        if (izquierdo == null) {
            izquierdo = "_";
        }

        // evaluar el operando derecho
        String derecho = "_";
        if (nodo.getDerecho() != null) {
            derecho = nodo.getDerecho().accept(generador);
        }

        if (derecho == null) {
            derecho = "_";
        }

        // agregar la conjuncion con temporal a la lista de cuartetas
        String temp = ctx.getTemporales().nuevoTemporal();

        // registrar el temporal como booleano
        ctx.getTiposConocidos().put(temp, "booleano");
        ctx.getCuartetas().add(new Cuarteta("&&", izquierdo, derecho, temp, ctx.inferirTipoDe(izquierdo, ctx.getTiposConocidos()), ctx.inferirTipoDe(derecho, ctx.getTiposConocidos()), "booleano"));

        return temp;
    }

    // generar disyuncion logica con temporal booleano
    public String visitarExprOr(ExprOr nodo) {
        // evaluar el operando izquierdo
        String izquierdo = "_";
        if (nodo.getIzquierdo() != null) {
            izquierdo = nodo.getIzquierdo().accept(generador);
        }

        // si es nulo usar lo de respaldo
        if (izquierdo == null) {
            izquierdo = "_";
        }

        // evaluar el operando derecho
        String derecho = "_";
        if (nodo.getDerecho() != null) {
            derecho = nodo.getDerecho().accept(generador);
        }

        if (derecho == null) {
            derecho = "_";
        }

        // agregar la disyuncion con temporal a la lista de cuartetas
        String temp = ctx.getTemporales().nuevoTemporal();

        // registrar el temporal como booleano
        ctx.getTiposConocidos().put(temp, "booleano");
        ctx.getCuartetas().add(new Cuarteta("||", izquierdo, derecho, temp, ctx.inferirTipoDe(izquierdo, ctx.getTiposConocidos()), ctx.inferirTipoDe(derecho, ctx.getTiposConocidos()), "booleano"));

        return temp;
    }

    // generar carga de literal en temporal o devolver identificador
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
            String temp = ctx.getTemporales().nuevoTemporal();

            // adivinar el tipo del literal
            String tipoLiteral = ctx.inferirTipoLiteral(primitivo.getValor());

            // registrar el temporal con el tipo inferido
            ctx.getTiposConocidos().put(temp, tipoLiteral);
            ctx.getCuartetas().add(new Cuarteta("=", primitivo.getValor(), "_", temp, tipoLiteral, "_", tipoLiteral));

            return temp;
        }

        return null;
    }
}
