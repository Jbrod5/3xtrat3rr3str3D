package org.jrg.analisis.zetariano.cuartetas;

import java.util.ArrayList;
import java.util.List;

import org.jrg.model.ast.zetariano.ListaExpresiones;
import org.jrg.model.ast.zetariano.TipoDato;
import org.jrg.model.ast.zetariano.ValorPrimitivo;
import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.expresion.ExprAccesoArray;
import org.jrg.model.ast.zetariano.expresion.ExprAccesoMiembro;
import org.jrg.model.ast.zetariano.expresion.ExprAnd;
import org.jrg.model.ast.zetariano.expresion.ExprInstanciaArreglo;
import org.jrg.model.ast.zetariano.expresion.ExprInstanciaObjeto;
import org.jrg.model.ast.zetariano.expresion.ExprLlamadaFuncion;
import org.jrg.model.ast.zetariano.expresion.ExprLlamadaMetodo;
import org.jrg.model.ast.zetariano.expresion.ExprMultiplicacionDivisionModulo;
import org.jrg.model.ast.zetariano.expresion.ExprNegada;
import org.jrg.model.ast.zetariano.expresion.ExprNegativa;
import org.jrg.model.ast.zetariano.expresion.ExprOr;
import org.jrg.model.ast.zetariano.expresion.ExprParentesis;
import org.jrg.model.ast.zetariano.expresion.ExprPostDecremento;
import org.jrg.model.ast.zetariano.expresion.ExprPostIncremento;
import org.jrg.model.ast.zetariano.expresion.ExprPrimitivo;
import org.jrg.model.ast.zetariano.expresion.ExprRelacional;
import org.jrg.model.ast.zetariano.expresion.ExprSumaResta;
import org.jrg.model.ast.zetariano.expresion.ExprTernario;
import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.variable_asignable.VarArray;
import org.jrg.model.ast.zetariano.variable_asignable.VarMiembro;
import org.jrg.model.base.TipoPrimitivo;
import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.service.compiler.cuartetaC.implementacion.CuartetaConcatenacion;
import org.jrg.service.compiler.cuartetaC.implementacion.CuartetaUminus;
import org.jrg.service.compiler.cuartetaC.implementacion.CuartetaParametro;
import org.jrg.service.compiler.cuartetaC.implementacion.CuartetaNew;
import org.jrg.service.compiler.cuartetaC.implementacion.CuartetaNegacion;
import org.jrg.service.compiler.cuartetaC.implementacion.CuartetaLogica;
import org.jrg.service.compiler.cuartetaC.implementacion.CuartetaLlamada;
import org.jrg.service.compiler.cuartetaC.implementacion.CuartetaIfFalse;
import org.jrg.service.compiler.cuartetaC.implementacion.CuartetaGoto;
import org.jrg.service.compiler.cuartetaC.implementacion.CuartetaEtiqueta;
import org.jrg.service.compiler.cuartetaC.implementacion.CuartetaComparacion;
import org.jrg.service.compiler.cuartetaC.implementacion.CuartetaAsignacionSimple;
import org.jrg.service.compiler.cuartetaC.implementacion.CuartetaAsignacionArreglo;
import org.jrg.service.compiler.cuartetaC.implementacion.CuartetaAritmetica;
import org.jrg.service.compiler.cuartetaC.implementacion.CuartetaAlloc;
import org.jrg.service.compiler.cuartetaC.implementacion.CuartetaAccesoMiembro;
import org.jrg.service.compiler.cuartetaC.implementacion.CuartetaAccesoArreglo;

// generar cuartetas de expresiones en Zetariano
public class ManejadorExpresionesZetariano {

    private final ContextoCuartetasZetariano ctx;

    private final GeneradorCuartetasZetariano generador;

    // crear la manejadora con contexto y generador
    public ManejadorExpresionesZetariano(ContextoCuartetasZetariano ctx, GeneradorCuartetasZetariano generador) {

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

    // instanciar un objeto con new y sus parametros
    public String visitarExprInstanciaObjeto(ExprInstanciaObjeto nodo) {

        // evaluar los argumentos del constructor
        List<String> argumentos = new ArrayList<>();
        if (nodo.getArgumentos() instanceof ListaExpresiones) {

            // convertir los argumentos al tipo concreto
            ListaExpresiones lista = (ListaExpresiones) nodo.getArgumentos();

            // recorrer cada argumento de la lista
            if (lista.getExpresiones() != null) {

                for (int i = 0; i < lista.getExpresiones().size(); i++) {

                    // evaluar el argumento actual
                    String argumento = lista.getExpresiones().get(i).accept(generador);

                    if (argumento == null) {
                        argumento = "_";
                    }

                    // agregar el argumento a la lista
                    argumentos.add(argumento);

                }

            }

        }

        // agregar un param por cada argumento a la lista de cuartetas
        for (int i = 0; i < argumentos.size(); i++) {
            ctx.getCuartetas().add(new CuartetaParametro("param", argumentos.get(i), "_", "_", ctx.inferirTipoDe(argumentos.get(i), ctx.getTiposConocidos()), "_", "_"));
        }

        // agregar la instancia y guardar el resultado en un temporal a la lista de cuartetas
        String temp = ctx.getTemporales().nuevoTemporal();

        // registrar el temporal con el tipo de la clase
        ctx.getTiposConocidos().put(temp, nodo.getNombreClase());
        ctx.getCuartetas().add(new CuartetaNew("new", nodo.getNombreClase(), String.valueOf(argumentos.size()), temp, nodo.getNombreClase(), "_", nodo.getNombreClase()));

        return temp;

    }

    // reservar un arreglo con sus dimensiones evaluadas
    public String visitarExprInstanciaArreglo(ExprInstanciaArreglo nodo) {

        // extraer el nombre del tipo base
        String tipoBase = "_";
        if (nodo.getTipo() instanceof TipoDato) {
            tipoBase = ((TipoDato) nodo.getTipo()).getTipo();
        }

        // usar valor por defecto si el nombre es nulo
        if (tipoBase == null) {
            tipoBase = "_";
        }

        // evaluar cada dimension del arreglo
        String dimension = "1";
        if (nodo.getDimensiones() != null) {

            if (nodo.getDimensiones().isEmpty() == false) {

                for (int i = 0; i < nodo.getDimensiones().size(); i++) {

                    // evaluar la dimension actual
                    String valorDimension = nodo.getDimensiones().get(i).accept(generador);

                    if (valorDimension == null) {
                        valorDimension = "_";
                    }

                    // multiplicar el acumulado por la dimension actual
                    String tempMul = ctx.getTemporales().nuevoTemporal();
                    ctx.getCuartetas().add(new CuartetaAritmetica("*", dimension, valorDimension, tempMul, ctx.tipoAritmetico(dimension), ctx.tipoAritmetico(valorDimension), ctx.tipoResultadoAritmetico(dimension, valorDimension)));
                    dimension = tempMul;

                }

            }

        }

        // agregar la reserva de memoria con temporal a la lista de cuartetas
        String temp = ctx.getTemporales().nuevoTemporal();

        // reservar filas anidadas cuando hay dos dimensiones
        if (nodo.getDimensiones() != null && nodo.getDimensiones().size() == 2) {
            return reservarAnidado(tipoBase, nodo.getDimensiones());
        }

        // registrar el temporal con el tipo base
        ctx.getTiposConocidos().put(temp, tipoBase);
        ctx.getCuartetas().add(new CuartetaAlloc("alloc", tipoBase, dimension, temp, tipoBase, "entero", tipoBase));

        return temp;

    }

    // reservar un arreglo de dos dimensiones como filas anidadas
    private String reservarAnidado(String tipoBase, List<NodoASTZetariano> dimensiones) {

        // evaluar la dimension externa una sola vez
        String dimExterna = "_";
        if (dimensiones.get(0) != null) {
            dimExterna = dimensiones.get(0).accept(generador);
        }

        if (dimExterna == null) {
            dimExterna = "_";
        }

        // evaluar la dimension interna una sola vez
        String dimInterna = "_";
        if (dimensiones.get(1) != null) {
            dimInterna = dimensiones.get(1).accept(generador);
        }

        if (dimInterna == null) {
            dimInterna = "_";
        }

        // reservar el arreglo externo de bases enteras
        String tempExterno = ctx.getTemporales().nuevoTemporal();
        ctx.getTiposConocidos().put(tempExterno, "entero");
        ctx.getCuartetas().add(new CuartetaAlloc("alloc", "entero", dimExterna, tempExterno, "entero", "entero", "entero"));

        // preparar el contador del ciclo de filas
        String tempContador = ctx.getTemporales().nuevoTemporal();
        ctx.getTiposConocidos().put(tempContador, "entero");
        ctx.getCuartetas().add(new CuartetaAsignacionSimple("=", "0", "_", tempContador, "entero", "_", "entero"));

        // crear las etiquetas del ciclo de filas
        String etiquetaInicio = ctx.getTemporales().nuevaEtiqueta();
        String etiquetaFin = ctx.getTemporales().nuevaEtiqueta();
        ctx.getCuartetas().add(new CuartetaEtiqueta("label", etiquetaInicio, "_", "_", "_", "_", "_"));

        // comparar el contador contra la dimension externa
        String tempCondicion = ctx.getTemporales().nuevoTemporal();
        ctx.getTiposConocidos().put(tempCondicion, "booleano");
        ctx.getCuartetas().add(new CuartetaComparacion("<", tempContador, dimExterna, tempCondicion, "entero", "entero", "booleano"));
        ctx.getCuartetas().add(new CuartetaIfFalse("if_false", tempCondicion, etiquetaFin, "_", "booleano", "_", "_"));

        // reservar una fila con el tipo base
        String tempFila = ctx.getTemporales().nuevoTemporal();
        ctx.getTiposConocidos().put(tempFila, tipoBase);
        ctx.getCuartetas().add(new CuartetaAlloc("alloc", tipoBase, dimInterna, tempFila, tipoBase, "entero", tipoBase));

        // guardar la fila en el arreglo externo
        ctx.getCuartetas().add(new CuartetaAsignacionArreglo("[]=", tempExterno, tempContador, tempFila, "_", "entero", "_"));

        // avanzar el contador y repetir
        String tempSiguiente = ctx.getTemporales().nuevoTemporal();
        ctx.getTiposConocidos().put(tempSiguiente, "entero");
        ctx.getCuartetas().add(new CuartetaAritmetica("+", tempContador, "1", tempSiguiente, "entero", "entero", "entero"));
        ctx.getCuartetas().add(new CuartetaAsignacionSimple("=", tempSiguiente, "_", tempContador, "entero", "_", "entero"));
        ctx.getCuartetas().add(new CuartetaGoto("goto", etiquetaInicio, "_", "_", "_", "_", "_"));
        ctx.getCuartetas().add(new CuartetaEtiqueta("label", etiquetaFin, "_", "_", "_", "_", "_"));

        return tempExterno;

    }

    // llamar a una funcion con call y sus parametros
    public String visitarExprLlamadaFuncion(ExprLlamadaFuncion nodo) {

        // evaluar los argumentos de la llamada
        List<String> argumentos = new ArrayList<>();

        if (nodo.getArgumentos() instanceof ListaExpresiones) {

            // convertir los argumentos al tipo concreto
            ListaExpresiones lista = (ListaExpresiones) nodo.getArgumentos();

            // recorrer cada argumento de la lista
            if (lista.getExpresiones() != null) {

                for (int i = 0; i < lista.getExpresiones().size(); i++) {

                    // evaluar el argumento actual
                    String argumento = lista.getExpresiones().get(i).accept(generador);

                    // si es nulo usar lo de respaldo
                    if (argumento == null) {
                        argumento = "_";
                    }

                    // agregar el argumento a la lista
                    argumentos.add(argumento);

                }

            }

        }

        // agregar un param por cada argumento a la lista de cuartetas
        for (int i = 0; i < argumentos.size(); i++) {
            ctx.getCuartetas().add(new CuartetaParametro("param", argumentos.get(i), "_", "_", ctx.inferirTipoDe(argumentos.get(i), ctx.getTiposConocidos()), "_", "_"));
        }

        // agregar la llamada y guardar el resultado en un temporal a la lista de cuartetas
        String temp = ctx.getTemporales().nuevoTemporal();
        ctx.getCuartetas().add(new CuartetaLlamada("call", nodo.getNombre(), String.valueOf(argumentos.size()), temp, "_", "_", "_"));

        return temp;

    }

    // llamar a un metodo con el objeto como primer parametro
    public String visitarExprLlamadaMetodo(ExprLlamadaMetodo nodo) {

        // evaluar el objeto del metodo
        String objeto = "_";
        if (nodo.getObjeto() != null) {
            objeto = nodo.getObjeto().accept(generador);
        }

        if (objeto == null) {
            objeto = "_";
        }

        // evaluar los argumentos de la llamada
        List<String> argumentos = new ArrayList<>();
        if (nodo.getArgumentos() instanceof ListaExpresiones) {

            // convertir los argumentos al tipo concreto
            ListaExpresiones lista = (ListaExpresiones) nodo.getArgumentos();

            // recorrer cada argumento de la lista
            if (lista.getExpresiones() != null) {

                for (int i = 0; i < lista.getExpresiones().size(); i++) {

                    // evaluar el argumento actual
                    String argumento = lista.getExpresiones().get(i).accept(generador);

                    if (argumento == null) {
                        argumento = "_";
                    }

                    // agregar el argumento a la lista
                    argumentos.add(argumento);

                }

            }

        }

        // agregar el param del objeto primero a la lista de cuartetas
        ctx.getCuartetas().add(new CuartetaParametro("param", objeto, "_", "_", ctx.inferirTipoDe(objeto, ctx.getTiposConocidos()), "_", "_"));

        // agregar un param por cada argumento a la lista de cuartetas
        for (int i = 0; i < argumentos.size(); i++) {
            ctx.getCuartetas().add(new CuartetaParametro("param", argumentos.get(i), "_", "_", ctx.inferirTipoDe(argumentos.get(i), ctx.getTiposConocidos()), "_", "_"));
        }

        // agregar la llamada al metodo y guardar el resultado en un temporal a la lista de cuartetas
        String temp = ctx.getTemporales().nuevoTemporal();
        ctx.getCuartetas().add(new CuartetaLlamada("call_method", nodo.getNombre(), String.valueOf(argumentos.size() + 1), temp, "_", "_", "_"));

        return temp;

    }

    // generar acceso a arreglo con temporal
    public String visitarExprAccesoArray(ExprAccesoArray nodo) {

        // evaluar el objeto del acceso
        String objeto = "_";
        if (nodo.getObjeto() instanceof VarArray || nodo.getObjeto() instanceof VarMiembro) {
            objeto = materializarBase(nodo.getObjeto());
        } else if (nodo.getObjeto() != null) {
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

        // usar valor por defecto si el resultado es nulo
        if (indice == null) {
            indice = "_";
        }

        // generar el acceso a arreglo con temporal
        String temp = ctx.getTemporales().nuevoTemporal();
        ctx.getCuartetas().add(new CuartetaAccesoArreglo("=[]", objeto, indice, temp, "_", "entero", "_"));

        return temp;

    }

    // generar acceso a miembro con temporal
    public String visitarExprAccesoMiembro(ExprAccesoMiembro nodo) {

        // evaluar el objeto del acceso
        String objeto = "_";
        if (nodo.getObjeto() instanceof VarArray || nodo.getObjeto() instanceof VarMiembro) {
            objeto = materializarBase(nodo.getObjeto());
        } else if (nodo.getObjeto() != null) {
            objeto = nodo.getObjeto().accept(generador);
        }

        if (objeto == null) {
            objeto = "_";
        }

        // agregar el acceso a miembro con temporal a la lista de cuartetas
        String temp = ctx.getTemporales().nuevoTemporal();
        ctx.getCuartetas().add(new CuartetaAccesoMiembro(".", objeto, nodo.getMiembro(), temp, "_", "_", "_"));

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
        ctx.getCuartetas().add(new CuartetaAritmetica("+", variable, "1", variable, ctx.inferirTipoDe(variable, ctx.getTiposConocidos()), "entero", ctx.inferirTipoDe(variable, ctx.getTiposConocidos())));

        return variable;

    }

    // generar post decremento sobre la misma variable
    public String visitarExprPostDecremento(ExprPostDecremento nodo) {

        // evaluar la variable
        String variable = "_";
        if (nodo.getVariable() != null) {
            variable = nodo.getVariable().accept(generador);
        }

        // si es nulo usar lo de respaldo
        if (variable == null) {
            variable = "_";
        }

        // agregar el decremento sobre la misma variable a la lista de cuartetas
        ctx.getCuartetas().add(new CuartetaAritmetica("-", variable, "1", variable, ctx.inferirTipoDe(variable, ctx.getTiposConocidos()), "entero", ctx.inferirTipoDe(variable, ctx.getTiposConocidos())));

        return variable;

    }

    // generar menos unario con opcode propio
    public String visitarExprNegativa(ExprNegativa nodo) {

        // evaluar la expresion interna
        String valor = "_";
        if (nodo.getExpresion() != null) {
            valor = nodo.getExpresion().accept(generador);
        }

        if (valor == null) {
            valor = "_";
        }

        // agregar la negacion aritmetica con temporal a la lista de cuartetas
        String temp = ctx.getTemporales().nuevoTemporal();

        // adivinar el tipo desde el operando
        String tipoNeg = ctx.inferirTipoDe(valor, ctx.getTiposConocidos());

        // registrar el temporal con el tipo inferido
        ctx.getTiposConocidos().put(temp, tipoNeg);

        // agregar menos unario con opcode propio a la lista de cuartetas
        ctx.getCuartetas().add(new CuartetaUminus("uminus", valor, "_", temp, tipoNeg, "_", tipoNeg));

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
        ctx.getCuartetas().add(new CuartetaNegacion("!", valor, "_", temp, ctx.inferirTipoDe(valor, ctx.getTiposConocidos()), "_", "booleano"));

        return temp;

    }

    // generar multiplicacion division o modulo con tipo inferido
    public String visitarExprMultiplicacionDivisionModulo(ExprMultiplicacionDivisionModulo nodo) {

        // evaluar el operando izquierdo
        String izquierdo = "_";
        if (nodo.getOperandoIzquierdo() != null) {
            izquierdo = nodo.getOperandoIzquierdo().accept(generador);
        }

        if (izquierdo == null) {
            izquierdo = "_";
        }

        // evaluar el operando derecho
        String derecho = "_";
        if (nodo.getOperandoDerecho() != null) {
            derecho = nodo.getOperandoDerecho().accept(generador);
        }

        // usar valor por defecto si el resultado es nulo
        if (derecho == null) {
            derecho = "_";
        }

        // agregar la operacion con temporal a la lista de cuartetas
        String temp = ctx.getTemporales().nuevoTemporal();

        // adivinar que tipo sale de la cuenta
        String tipoResMult = ctx.tipoResultadoAritmetico(izquierdo, derecho);

        // registrar el temporal con el tipo inferido
        ctx.getTiposConocidos().put(temp, tipoResMult);
        ctx.getCuartetas().add(new CuartetaAritmetica(nodo.getOperador(), izquierdo, derecho, temp, ctx.tipoAritmetico(izquierdo), ctx.tipoAritmetico(derecho), tipoResMult));

        return temp;

    }

    // generar suma resta o concatenacion con tipo inferido
    public String visitarExprSumaResta(ExprSumaResta nodo) {

        // evaluar el operando izquierdo
        String izquierdo = "_";
        if (nodo.getOperandoIzquierdo() != null) {
            izquierdo = nodo.getOperandoIzquierdo().accept(generador);
        }

        if (izquierdo == null) {
            izquierdo = "_";
        }

        // evaluar el operando derecho
        String derecho = "_";
        if (nodo.getOperandoDerecho() != null) {
            derecho = nodo.getOperandoDerecho().accept(generador);
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

        // detectar texto en los lados para elegir concat
        boolean izqEsCadenaSuma = ctx.esTipoCadena(tipoIzqSuma);
        boolean derEsCadenaSuma = ctx.esTipoCadena(tipoDerSuma);

        // usar cadena cuando se concatena texto con mas
        if ("+".equals(nodo.getOperador())) {

            if (izqEsCadenaSuma || derEsCadenaSuma) {
                tipoResSuma = "cadena";
            }

        }

        // registrar el temporal con el tipo inferido
        ctx.getTiposConocidos().put(temp, tipoResSuma);

        // usar concat cuando el mas junta texto
        if ("+".equals(nodo.getOperador()) && (izqEsCadenaSuma || derEsCadenaSuma)) {
            ctx.getCuartetas().add(new CuartetaConcatenacion(nodo.getOperador(), izquierdo, derecho, temp, ctx.tipoAritmetico(izquierdo), ctx.tipoAritmetico(derecho), tipoResSuma));
        } else {
            ctx.getCuartetas().add(new CuartetaAritmetica(nodo.getOperador(), izquierdo, derecho, temp, ctx.tipoAritmetico(izquierdo), ctx.tipoAritmetico(derecho), tipoResSuma));
        }

        return temp;

    }

    // generar comparacion relacional con temporal booleano
    public String visitarExprRelacional(ExprRelacional nodo) {

        // evaluar el operando izquierdo
        String izquierdo = "_";
        if (nodo.getOperandoIzquierdo() != null) {
            izquierdo = nodo.getOperandoIzquierdo().accept(generador);
        }

        // si es nulo usar lo de respaldo
        if (izquierdo == null) {
            izquierdo = "_";
        }

        // evaluar el operando derecho
        String derecho = "_";
        if (nodo.getOperandoDerecho() != null) {
            derecho = nodo.getOperandoDerecho().accept(generador);
        }

        if (derecho == null) {
            derecho = "_";
        }

        // agregar la comparacion con temporal a la lista de cuartetas
        String temp = ctx.getTemporales().nuevoTemporal();

        // registrar el temporal como booleano
        ctx.getTiposConocidos().put(temp, "booleano");
        ctx.getCuartetas().add(new CuartetaComparacion(nodo.getOperador(), izquierdo, derecho, temp, ctx.inferirTipoDe(izquierdo, ctx.getTiposConocidos()), ctx.inferirTipoDe(derecho, ctx.getTiposConocidos()), "booleano"));

        return temp;

    }

    // generar conjuncion logica con temporal booleano
    public String visitarExprAnd(ExprAnd nodo) {

        // evaluar el operando izquierdo
        String izquierdo = "_";
        if (nodo.getOperandoIzquierdo() != null) {
            izquierdo = nodo.getOperandoIzquierdo().accept(generador);
        }

        if (izquierdo == null) {
            izquierdo = "_";
        }

        // evaluar el operando derecho
        String derecho = "_";
        if (nodo.getOperandoDerecho() != null) {
            derecho = nodo.getOperandoDerecho().accept(generador);
        }

        if (derecho == null) {
            derecho = "_";
        }

        // agregar la conjuncion con temporal a la lista de cuartetas
        String temp = ctx.getTemporales().nuevoTemporal();

        // registrar el temporal como booleano
        ctx.getTiposConocidos().put(temp, "booleano");
        ctx.getCuartetas().add(new CuartetaLogica("&&", izquierdo, derecho, temp, ctx.inferirTipoDe(izquierdo, ctx.getTiposConocidos()), ctx.inferirTipoDe(derecho, ctx.getTiposConocidos()), "booleano"));

        return temp;

    }

    // generar disyuncion logica con temporal booleano
    public String visitarExprOr(ExprOr nodo) {

        // evaluar el operando izquierdo
        String izquierdo = "_";
        if (nodo.getOperandoIzquierdo() != null) {
            izquierdo = nodo.getOperandoIzquierdo().accept(generador);
        }

        // usar valor por defecto si el resultado es nulo
        if (izquierdo == null) {
            izquierdo = "_";
        }

        // evaluar el operando derecho
        String derecho = "_";
        if (nodo.getOperandoDerecho() != null) {
            derecho = nodo.getOperandoDerecho().accept(generador);
        }

        if (derecho == null) {
            derecho = "_";
        }

        // agregar la disyuncion con temporal a la lista de cuartetas
        String temp = ctx.getTemporales().nuevoTemporal();

        // registrar el temporal como booleano
        ctx.getTiposConocidos().put(temp, "booleano");
        ctx.getCuartetas().add(new CuartetaLogica("||", izquierdo, derecho, temp, ctx.inferirTipoDe(izquierdo, ctx.getTiposConocidos()), ctx.inferirTipoDe(derecho, ctx.getTiposConocidos()), "booleano"));

        return temp;

    }

    // generar el ternario con etiquetas de rama falsa y fin
    public String visitarExprTernario(ExprTernario nodo) {

        // evaluar la condicion
        String condicion = "_";
        if (nodo.getCondicion() != null) {
            condicion = nodo.getCondicion().accept(generador);
        }

        if (condicion == null) {
            condicion = "_";
        }

        // crear el temporal del resultado
        String temp = ctx.getTemporales().nuevoTemporal();

        // crear la etiqueta de la rama falsa
        String lFalso = ctx.getTemporales().nuevaEtiqueta();

        // crear la etiqueta final
        String lFin = ctx.getTemporales().nuevaEtiqueta();

        // agregar el salto a la rama falsa si la condicion es falsa a la lista de cuartetas
        ctx.getCuartetas().add(new CuartetaIfFalse("if_false", condicion, lFalso, "_", "booleano", "_", "_"));

        // evaluar el valor verdadero
        String verdadero = "_";
        if (nodo.getValorVerdadero() != null) {
            verdadero = nodo.getValorVerdadero().accept(generador);
        }

        // si es nulo usar lo de respaldo
        if (verdadero == null) {
            verdadero = "_";
        }

        // adivinar los tipos de las dos ramas
        String tipoVerdadero = ctx.inferirTipoDe(verdadero, ctx.getTiposConocidos());

        // agregar la asignacion del valor verdadero a la lista de cuartetas
        ctx.getCuartetas().add(new CuartetaAsignacionSimple(":=", verdadero, "_", temp, tipoVerdadero, "_", "_"));

        // agregar el salto al final a la lista de cuartetas
        ctx.getCuartetas().add(new CuartetaGoto("goto", lFin, "_", "_", "_", "_", "_"));

        // agregar la etiqueta de la rama falsa a la lista de cuartetas
        ctx.getCuartetas().add(new CuartetaEtiqueta("label", lFalso, "_", "_", "_", "_", "_"));

        // evaluar el valor falso
        String falso = "_";
        if (nodo.getValorFalso() != null) {
            falso = nodo.getValorFalso().accept(generador);
        }

        if (falso == null) {
            falso = "_";
        }

        // adivinar los tipos de las dos ramas
        String tipoFalso = ctx.inferirTipoDe(falso, ctx.getTiposConocidos());

        // agregar la asignacion del valor falso a la lista de cuartetas
        ctx.getCuartetas().add(new CuartetaAsignacionSimple(":=", falso, "_", temp, tipoFalso, "_", "_"));

        // agregar la etiqueta final a la lista de cuartetas
        ctx.getCuartetas().add(new CuartetaEtiqueta("label", lFin, "_", "_", "_", "_", "_"));

        // registrar el temporal con el tipo comun de las ramas
        if (tipoVerdadero.equals(tipoFalso)) {
            ctx.getTiposConocidos().put(temp, tipoVerdadero);
        }

        return temp;

    }

    // generar carga de literal en temporal o casos especiales
    public String visitarExprPrimitivo(ExprPrimitivo nodo) {

        // verificar si el valor es primitivo
        if (nodo.getValor() instanceof ValorPrimitivo) {

            // convertir el valor al tipo concreto
            ValorPrimitivo primitivo = (ValorPrimitivo) nodo.getValor();

            // devolver el identificador directo sin crear temporal
            if (primitivo.getTipoDato() == TipoPrimitivo.IDENTIFICADOR) {
                return primitivo.getValor();
            }

            // guardar el nulo como literal especial
            if (primitivo.getTipoDato() == TipoPrimitivo.NULO) {

                String tempNulo = ctx.getTemporales().nuevoTemporal();
                ctx.getCuartetas().add(new CuartetaAsignacionSimple("=", "null", "_", tempNulo, "_", "_", "_"));

                return tempNulo;

            }

            // guardar el literal en un temporal
            String temp = ctx.getTemporales().nuevoTemporal();

            // adivinar el tipo del literal
            String tipoLiteral = ctx.inferirTipoLiteral(primitivo.getValor());

            // registrar el temporal con el tipo inferido
            ctx.getTiposConocidos().put(temp, tipoLiteral);
            ctx.getCuartetas().add(new CuartetaAsignacionSimple("=", primitivo.getValor(), "_", temp, tipoLiteral, "_", tipoLiteral));

            return temp;

        }

        return null;

    }

    // materializar una base compuesta en un temporal listo para usar
    private String materializarBase(NodoASTZetariano nodo) {

        // verificar si el nodo es nulo
        if (nodo == null) {
            return "_";
        }

        // anidar lectura de arreglo para bases con indice
        if (nodo instanceof VarArray) {

            // convertir la variable al tipo concreto
            VarArray acceso = (VarArray) nodo;

            // materializar la base interna primero para encadenar al infinito
            String baseInterna = materializarBase(acceso.getVariable());

            // evaluar el indice del acceso
            String indice = "_";
            if (acceso.getIndice() != null) {
                indice = acceso.getIndice().accept(generador);
            }

            if (indice == null) {
                indice = "_";
            }

            // leer la base intermedia como entero
            String tempBase = ctx.getTemporales().nuevoTemporal();
            ctx.getTiposConocidos().put(tempBase, "entero");
            ctx.getCuartetas().add(new CuartetaAccesoArreglo("=[]", baseInterna, indice, tempBase, "_", "_", "entero"));

            return tempBase;

        }

        // anidar lectura de miembro para bases con punto
        if (nodo instanceof VarMiembro) {

            // convertir la variable al tipo concreto
            VarMiembro acceso = (VarMiembro) nodo;

            // materializar la base interna primero para encadenar al infinito
            String baseInterna = materializarBase(acceso.getVariable());

            // leer el miembro intermedio con temporal
            String tempBase = ctx.getTemporales().nuevoTemporal();
            ctx.getCuartetas().add(new CuartetaAccesoMiembro(".", baseInterna, acceso.getMiembro(), tempBase, "_", "_", "_"));

            return tempBase;

        }

        // devolver nombres y temporales directos sin tocar nada
        String directo = nodo.accept(generador);
        if (directo == null) {
            return "_";
        }

        return directo;

    }

}
