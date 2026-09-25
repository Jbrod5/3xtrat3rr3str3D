package org.jrg.analisis.yLenguaje.cuartetas;

import org.jrg.model.ast.yLenguaje.CasoSeleccion;
import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.ciclo.CicloHacer;
import org.jrg.model.ast.yLenguaje.ciclo.CicloMientras;
import org.jrg.model.ast.yLenguaje.ciclo.CicloPara;
import org.jrg.model.ast.yLenguaje.condicional.StatementSi;
import org.jrg.model.ast.yLenguaje.init_para.InitParaAsig;
import org.jrg.model.ast.yLenguaje.init_para.InitParaDecl;
import org.jrg.model.ast.yLenguaje.instruccion.StmtCiclo;
import org.jrg.model.ast.yLenguaje.instruccion.StmtCondicional;
import org.jrg.model.ast.yLenguaje.instruccion.StmtSeleccion;
import org.jrg.model.ast.yLenguaje.paso_para.PasoParaAsig;
import org.jrg.model.ast.yLenguaje.paso_para.PasoParaExpr;
import org.jrg.model.ast.yLenguaje.seleccion.StatementElegir;
import org.jrg.model.cuarteta.Cuarteta;

// generar cuartetas de flujo y ciclos en el lenguaje Y
public class ManejadorFlujoY {

    // estado compartido de la generacion
    private final ContextoCuartetasY ctx;
    // generador duenio para el descenso recursivo
    private final GeneradorCuartetasY generador;

    // crear la manejadora con contexto y generador
    public ManejadorFlujoY(ContextoCuartetasY ctx, GeneradorCuartetasY generador) {
        // asignar las dependencias recibidas
        this.ctx = ctx;
        this.generador = generador;
    }

    // visitar el condicional interno de la instruccion
    public String visitarStmtCondicional(StmtCondicional nodo) {

        // visitar el condicional si existe
        if (nodo.getCondicional() != null) {
            nodo.getCondicional().accept(generador);
        }

        return null;
    }

    // visitar la seleccion interna de la instruccion
    public String visitarStmtSeleccion(StmtSeleccion nodo) {

        // visitar la seleccion si existe
        if (nodo.getSeleccion() != null) {
            nodo.getSeleccion().accept(generador);
        }

        return null;
    }

    // visitar el ciclo interno de la instruccion
    public String visitarStmtCiclo(StmtCiclo nodo) {

        // visitar el ciclo si existe
        if (nodo.getCiclo() != null) {
            nodo.getCiclo().accept(generador);
        }

        return null;
    }

    // generar el si con ramas sino y contrario
    public String visitarStatementSi(StatementSi nodo) {
        // evaluar la condicion principal
        String condicion = "_";
        if (nodo.getCondicionPrincipal() != null) {
            condicion = nodo.getCondicionPrincipal().accept(generador);
        }

        // usar valor por defecto si el resultado es nulo
        if (condicion == null) {
            condicion = "_";
        }

        // crear la etiqueta final
        String lfin = ctx.getTemporales().nuevaEtiqueta();

        // crear la etiqueta de la rama que sigue
        String lSiguiente = ctx.getTemporales().nuevaEtiqueta();

        // agregar el salto a la rama que sigue si la condicion es falsa a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("if_false", condicion, lSiguiente, "_", "booleano", "_", "_"));

        // visitar el bloque principal si existe
        if (nodo.getBloquePrincipal() != null) {
            nodo.getBloquePrincipal().accept(generador);
        }

        // agregar el salto al final a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("goto", lfin, "_", "_", "_", "_", "_"));

        // agregar la etiqueta de la rama que sigue a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("label", lSiguiente, "_", "_", "_", "_", "_"));

        // recorrer las ramas sino si si existen
        if (nodo.getCondicionesSino() != null) {

            for (int i = 0; i < nodo.getCondicionesSino().size(); i++) {
                // evaluar la condicion de la rama actual
                String condicionSino = "_";
                if (nodo.getCondicionesSino().get(i) != null) {
                    condicionSino = nodo.getCondicionesSino().get(i).accept(generador);
                }

                // usar valor por defecto si el resultado es nulo
                if (condicionSino == null) {
                    condicionSino = "_";
                }

                // crear la etiqueta de la rama que sigue
                String lSiguienteSino = ctx.getTemporales().nuevaEtiqueta();

                // agregar el salto si la condicion es falsa a la lista de cuartetas
                ctx.getCuartetas().add(new Cuarteta("if_false", condicionSino, lSiguienteSino, "_", "booleano", "_", "_"));

                // visitar el bloque de la rama actual si existe
                if (nodo.getBloquesSino() != null) {
                    if (i < nodo.getBloquesSino().size()) {
                        if (nodo.getBloquesSino().get(i) != null) {
                            nodo.getBloquesSino().get(i).accept(generador);
                        }
                    }
                }

                // agregar el salto al final a la lista de cuartetas
                ctx.getCuartetas().add(new Cuarteta("goto", lfin, "_", "_", "_", "_", "_"));

                // agregar la etiqueta de la rama que sigue a la lista de cuartetas
                ctx.getCuartetas().add(new Cuarteta("label", lSiguienteSino, "_", "_", "_", "_", "_"));
            }
        }

        // visitar el bloque contrario si existe
        if (nodo.getBloqueContrario() != null) {
            nodo.getBloqueContrario().accept(generador);
        }

        // agregar la etiqueta final a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("label", lfin, "_", "_", "_", "_", "_"));

        return null;
    }

    // generar la seleccion elegir con comparaciones por caso
    public String visitarStatementElegir(StatementElegir nodo) {
        // evaluar la expresion de seleccion
        String selector = "_";
        if (nodo.getExpresion() != null) {
            selector = nodo.getExpresion().accept(generador);
        }

        // usar valor por defecto si el resultado es nulo
        if (selector == null) {
            selector = "_";
        }

        // crear la etiqueta final
        String lfin = ctx.getTemporales().nuevaEtiqueta();

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
                        valorCaso = casoSeleccion.getValor().accept(generador);
                    }

                    // usar valor por defecto si el resultado es nulo
                    if (valorCaso == null) {
                        valorCaso = "_";
                    }

                    // comparar el selector con el valor del caso
                    String temp = ctx.getTemporales().nuevoTemporal();

                    // registrar el temporal como booleano
                    ctx.getTiposConocidos().put(temp, "booleano");
                    ctx.getCuartetas().add(new Cuarteta("==", selector, valorCaso, temp, ctx.inferirTipoDe(selector, ctx.getTiposConocidos()), ctx.inferirTipoDe(valorCaso, ctx.getTiposConocidos()), "booleano"));

                    // crear la etiqueta del caso que sigue
                    String lSiguiente = ctx.getTemporales().nuevaEtiqueta();

                    // agregar el salto si no hay coincidencia a la lista de cuartetas
                    ctx.getCuartetas().add(new Cuarteta("if_false", temp, lSiguiente, "_", "booleano", "_", "_"));

                    // visitar las instrucciones del caso
                    if (casoSeleccion.getInstrucciones() != null) {

                        for (NodoASTY instruccion : casoSeleccion.getInstrucciones()) {

                            // visitar la instruccion actual si existe
                            if (instruccion != null) {
                                instruccion.accept(generador);
                            }

                        }
                    }

                    // agregar el salto al final a la lista de cuartetas
                    ctx.getCuartetas().add(new Cuarteta("goto", lfin, "_", "_", "_", "_", "_"));

                    // agregar la etiqueta del caso que sigue a la lista de cuartetas
                    ctx.getCuartetas().add(new Cuarteta("label", lSiguiente, "_", "_", "_", "_", "_"));

                } else {

                    // visitar el caso directamente
                    caso.accept(generador);

                }
            }
        }

        // visitar el caso por defecto si existe
        if (nodo.getCasoDefecto() != null) {
            nodo.getCasoDefecto().accept(generador);
        }

        // agregar la etiqueta final a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("label", lfin, "_", "_", "_", "_", "_"));

        return null;
    }

    // generar el ciclo para con etiquetas de inicio y fin
    public String visitarCicloPara(CicloPara nodo) {

        // guardar las etiquetas anteriores
        String anteriorBreak = ctx.getEtiquetaBreakActual();
        String anteriorContinue = ctx.getEtiquetaContinueActual();

        // crear las etiquetas del ciclo
        String lInicio = ctx.getTemporales().nuevaEtiqueta();
        String lFin = ctx.getTemporales().nuevaEtiqueta();

        // asignar las etiquetas actuales
        ctx.setEtiquetaBreakActual(lFin);
        ctx.setEtiquetaContinueActual(lInicio);

        // visitar la inicializacion si existe
        if (nodo.getInicializacion() != null) {
            nodo.getInicializacion().accept(generador);
        }

        // agregar la etiqueta de inicio a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("label", lInicio, "_", "_", "_", "_", "_"));

        // evaluar la condicion si existeeeeeeeeeeeee
        if (nodo.getCondicion() != null) {

            // obtener el resultado de la condicion
            String condicion = nodo.getCondicion().accept(generador);

            // usar valor por defecto si el resultado es nulo
            if (condicion == null) {
                condicion = "_";
            }

            // agregar el salto al final si la condicion es falsa a la lista de cuartetas
            ctx.getCuartetas().add(new Cuarteta("if_false", condicion, lFin, "_", "booleano", "_", "_"));
        }
        // visitar el bloque si existe
        if (nodo.getBloque() != null) {
            nodo.getBloque().accept(generador);
        }

        // visitar el paso si existe
        if (nodo.getPaso() != null) {
            nodo.getPaso().accept(generador);
        }

        // agregar el salto al inicio a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("goto", lInicio, "_", "_", "_", "_", "_"));

        // agregar la etiqueta final a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("label", lFin, "_", "_", "_", "_", "_"));

        // restaurar las etiquetas anteriores
        ctx.setEtiquetaBreakActual(anteriorBreak);
        ctx.setEtiquetaContinueActual(anteriorContinue);

        return null;
    }

    // generar el ciclo mientras con etiquetas de inicio y fin
    public String visitarCicloMientras(CicloMientras nodo) {

        // guardar las etiquetas anteriores
        String anteriorBreak = ctx.getEtiquetaBreakActual();
        String anteriorContinue = ctx.getEtiquetaContinueActual();

        // crear las etiquetas del ciclo
        String lInicio = ctx.getTemporales().nuevaEtiqueta();
        String lFin = ctx.getTemporales().nuevaEtiqueta();

        // asignar las etiquetas actuales
        ctx.setEtiquetaBreakActual(lFin);
        ctx.setEtiquetaContinueActual(lInicio);

        // agregar la etiqueta de inicio a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("label", lInicio, "_", "_", "_", "_", "_"));

        // evaluar la condicion
        String condicion = "_";
        if (nodo.getCondicion() != null) {
            condicion = nodo.getCondicion().accept(generador);
        }

        // usar valor por defecto si el resultado es nulo
        if (condicion == null) {
            condicion = "_";
        }

        // agregar el salto al final si la condicion es falsa a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("if_false", condicion, lFin, "_", "booleano", "_", "_"));

        // visitar el bloque si existe
        if (nodo.getBloque() != null) {
            nodo.getBloque().accept(generador);
        }

        // agregar el salto al inicio a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("goto", lInicio, "_", "_", "_", "_", "_"));

        // agregar la etiqueta final a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("label", lFin, "_", "_", "_", "_", "_"));

        // restaurar las etiquetas anteriores
        ctx.setEtiquetaBreakActual(anteriorBreak);
        ctx.setEtiquetaContinueActual(anteriorContinue);

        return null;
    }

    // generar el ciclo hacer que ejecuta el bloque al menos una vez
    public String visitarCicloHacer(CicloHacer nodo) {

        // guardar las etiquetas anteriores
        String anteriorBreak = ctx.getEtiquetaBreakActual();
        String anteriorContinue = ctx.getEtiquetaContinueActual();

        // crear las etiquetas del ciclo
        String lInicio = ctx.getTemporales().nuevaEtiqueta();
        String lContinuar = ctx.getTemporales().nuevaEtiqueta();
        String lFin = ctx.getTemporales().nuevaEtiqueta();

        // asignar las etiquetas actuales
        ctx.setEtiquetaBreakActual(lFin);
        ctx.setEtiquetaContinueActual(lContinuar);

        // agregar la etiqueta de inicio a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("label", lInicio, "_", "_", "_", "_", "_"));

        // visitar el bloque si existe
        if (nodo.getBloque() != null) {
            nodo.getBloque().accept(generador);
        }

        // agregar la etiqueta de continuar a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("label", lContinuar, "_", "_", "_", "_", "_"));

        // evaluar la condicion
        String condicion = "_";
        if (nodo.getCondicion() != null) {
            condicion = nodo.getCondicion().accept(generador);
        }

        // usar valor por defecto si el resultado es nulo
        if (condicion == null) {
            condicion = "_";
        }

        // agregar el salto al final si la condicion es falsa a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("if_false", condicion, lFin, "_", "booleano", "_", "_"));

        // agregar el salto al inicio a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("goto", lInicio, "_", "_", "_", "_", "_"));

        // agregar la etiqueta final a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("label", lFin, "_", "_", "_", "_", "_"));

        // restaurar las etiquetas anteriores
        ctx.setEtiquetaBreakActual(anteriorBreak);
        ctx.setEtiquetaContinueActual(anteriorContinue);

        return null;
    }

    // generar la inicializacion con declaracion del para
    public String visitarInitParaDecl(InitParaDecl nodo) {

        // agregar la asignacion a la lista de cuartetas inicial si hay expresion
        if (nodo.getExpresion() != null) {

            // evaluar la expresion inicial
            String valor = nodo.getExpresion().accept(generador);

            // usar valor por defecto si el resultado es nulo
            if (valor == null) {
                valor = "_";
            }

            // agregar la asignacion a la variable a la lista de cuartetas
            ctx.getCuartetas().add(new Cuarteta(":=", valor, "_", nodo.getNombre(), ctx.inferirTipoDe(valor, ctx.getTiposConocidos()), "_", "_"));
        }

        return null;
    }

    // generar la inicializacion con asignacion del para
    public String visitarInitParaAsig(InitParaAsig nodo) {

        // evaluar la variable destino
        String variable = "_";
        if (nodo.getVariable() != null) {
            variable = nodo.getVariable().accept(generador);
        }

        // usar valor por defecto si el resultado es nulo
        if (variable == null) {
            variable = "_";
        }

        // evaluar la expresion inicial
        String valor = "_";
        if (nodo.getExpresion() != null) {
            valor = nodo.getExpresion().accept(generador);
        }

        // usar valor por defecto si el resultado es nulo
        if (valor == null) {
            valor = "_";
        }

        // agregar la asignacion a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta(":=", valor, "_", variable, ctx.inferirTipoDe(valor, ctx.getTiposConocidos()), "_", "_"));

        return null;
    }

    // visitar la expresion del paso del para
    public String visitarPasoParaExpr(PasoParaExpr nodo) {
        // visitar la expresion del paso si existe
        if (nodo.getExpresion() != null) {
            nodo.getExpresion().accept(generador);
        }

        return null;
    }

    // generar la asignacion del paso del para
    public String visitarPasoParaAsig(PasoParaAsig nodo) {

        // evaluar la variable destino
        String variable = "_";
        if (nodo.getVariable() != null) {
            variable = nodo.getVariable().accept(generador);
        }

        // usar valor por defecto si el resultado es nulo
        if (variable == null) {
            variable = "_";
        }

        // evaluar la expresion del paso
        String valor = "_";
        if (nodo.getExpresion() != null) {
            valor = nodo.getExpresion().accept(generador);
        }

        // usar valor por defecto si el resultado es nulo
        if (valor == null) {
            valor = "_";
        }

        // agregar la asignacion a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta(":=", valor, "_", variable, ctx.inferirTipoDe(valor, ctx.getTiposConocidos()), "_", "_"));

        return null;
    }
}
