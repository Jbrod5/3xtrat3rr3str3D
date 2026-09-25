package org.jrg.analisis.zetariano.cuartetas;

import org.jrg.model.ast.zetariano.CasoSwitch;
import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.ciclo.CicloDoWhile;
import org.jrg.model.ast.zetariano.ciclo.CicloFor;
import org.jrg.model.ast.zetariano.ciclo.CicloWhile;
import org.jrg.model.ast.zetariano.condicional.StatementIf;
import org.jrg.model.ast.zetariano.init_for.InitForAsig;
import org.jrg.model.ast.zetariano.init_for.InitForDecl;
import org.jrg.model.ast.zetariano.instruccion.StmtCiclo;
import org.jrg.model.ast.zetariano.instruccion.StmtCondicional;
import org.jrg.model.ast.zetariano.instruccion.StmtSeleccion;
import org.jrg.model.ast.zetariano.paso_for.PasoForAsig;
import org.jrg.model.ast.zetariano.paso_for.PasoForExpr;
import org.jrg.model.ast.zetariano.seleccion.StatementSwitch;
import org.jrg.model.cuarteta.Cuarteta;

// generar cuartetas de flujo seleccion y ciclos en Zetariano
public class ManejadorFlujoZetariano {

    // estado compartido de la generacion
    private final ContextoCuartetasZetariano ctx;
    // generador duenio para el descenso recursivo
    private final GeneradorCuartetasZetariano generador;

    // crear la manejadora con contexto y generador
    public ManejadorFlujoZetariano(ContextoCuartetasZetariano ctx, GeneradorCuartetasZetariano generador) {
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

    // generar el if con ramas sino si y sino
    public String visitarStatementIf(StatementIf nodo) {
        // evaluar la condicion principal
        String condicion = "_";
        if (nodo.getCondicion() != null) {
            condicion = nodo.getCondicion().accept(generador);
        }

        // usar valor por defecto si el resultado es nulo
        if (condicion == null) {
            condicion = "_";
        }

        // crear la etiqueta final
        String lFin = ctx.getTemporales().nuevaEtiqueta();

        // crear la etiqueta de la rama que sigue
        String lSiguiente = ctx.getTemporales().nuevaEtiqueta();

        // agregar el salto a la rama que sigue si la condicion es falsa a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("if_false", condicion, lSiguiente, "_", "booleano", "_", "_"));

        // visitar el bloque principal si existe
        if (nodo.getBloque() != null) {
            nodo.getBloque().accept(generador);
        }

        // agregar el salto al final a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("goto", lFin, "_", "_", "_", "_", "_"));

        // agregar la etiqueta de la rama que sigue a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("label", lSiguiente, "_", "_", "_", "_", "_"));

        // recorrer las ramas sino si si existen
        if (nodo.getCondicionesSinoSi() != null) {

            for (int i = 0; i < nodo.getCondicionesSinoSi().size(); i++) {

                // evaluar la condicion de la rama actual
                String condicionSino = "_";
                if (nodo.getCondicionesSinoSi().get(i) != null) {
                    condicionSino = nodo.getCondicionesSinoSi().get(i).accept(generador);
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
                if (nodo.getBloquesSinoSi() != null) {
                    if (i < nodo.getBloquesSinoSi().size()) {
                        if (nodo.getBloquesSinoSi().get(i) != null) {
                            nodo.getBloquesSinoSi().get(i).accept(generador);
                        }
                    }
                }

                // agregar el salto al final a la lista de cuartetas
                ctx.getCuartetas().add(new Cuarteta("goto", lFin, "_", "_", "_", "_", "_"));

                // agregar la etiqueta de la rama que sigue a la lista de cuartetas
                ctx.getCuartetas().add(new Cuarteta("label", lSiguienteSino, "_", "_", "_", "_", "_"));
            }

        }

        // visitar el bloque sino si existe
        if (nodo.getBloqueSino() != null) {
            nodo.getBloqueSino().accept(generador);
        }

        // agregar la etiqueta final a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("label", lFin, "_", "_", "_", "_", "_"));
        return null;
    }

    // generar el switch con comparaciones por caso
    public String visitarStatementSwitch(StatementSwitch nodo) {
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
        String lFin = ctx.getTemporales().nuevaEtiqueta();

        // recorrer cada caso de la seleccion
        if (nodo.getCasos() != null) {

            for (int i = 0; i < nodo.getCasos().size(); i++) {

                // obtener el caso actual
                NodoASTZetariano caso = nodo.getCasos().get(i);

                // omitir el caso si es nulo
                if (caso == null) {
                    continue;
                }

                // comparar el selector cuando el caso trae valor
                if (caso instanceof CasoSwitch) {

                    // convertir el caso al tipo concreto
                    CasoSwitch casoSwitch = (CasoSwitch) caso;

                    // evaluar el valor del caso
                    String valorCaso = "_";
                    if (casoSwitch.getValor() != null) {
                        valorCaso = casoSwitch.getValor().accept(generador);
                    }

                    // usar valor por defecto si el resultado es nulo
                    if (valorCaso == null) {
                        valorCaso = "_";
                    }

                    // guardar el valor del caso en un temporal
                    String tCaso = ctx.getTemporales().nuevoTemporal();

                    // inferir el tipo del valor del caso
                    String tipoCaso = ctx.inferirTipoDe(valorCaso, ctx.getTiposConocidos());

                    // registrar el temporal con el tipo inferido
                    ctx.getTiposConocidos().put(tCaso, tipoCaso);
                    ctx.getCuartetas().add(new Cuarteta("=", valorCaso, "_", tCaso, tipoCaso, "_", tipoCaso));

                    // comparar el selector con el valor del caso
                    String tComparacion = ctx.getTemporales().nuevoTemporal();

                    // registrar el temporal como booleano
                    ctx.getTiposConocidos().put(tComparacion, "booleano");
                    ctx.getCuartetas().add(new Cuarteta("==", selector, tCaso, tComparacion, ctx.inferirTipoDe(selector, ctx.getTiposConocidos()), ctx.inferirTipoDe(tCaso, ctx.getTiposConocidos()), "booleano"));

                    // crear la etiqueta del caso que sigue
                    String lSiguiente = ctx.getTemporales().nuevaEtiqueta();

                    // agregar el salto si no hay coincidencia a la lista de cuartetas
                    ctx.getCuartetas().add(new Cuarteta("if_false", tComparacion, lSiguiente, "_", "booleano", "_", "_"));

                    // visitar las instrucciones del caso
                    if (casoSwitch.getInstrucciones() != null) {
                        for (NodoASTZetariano instruccion : casoSwitch.getInstrucciones()) {

                            // visitar la instruccion actual si existe
                            if (instruccion != null) {
                                instruccion.accept(generador);

                            }
                        }
                    }

                    // agregar el salto al final a la lista de cuartetas
                    ctx.getCuartetas().add(new Cuarteta("goto", lFin, "_", "_", "_", "_", "_"));

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
        ctx.getCuartetas().add(new Cuarteta("label", lFin, "_", "_", "_", "_", "_"));

        return null;
    }

    // generar el ciclo for con etiquetas de inicio y fin
    public String visitarCicloFor(CicloFor nodo) {
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

        // evaluar la condicion si existe
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

    // generar el ciclo while con etiquetas de inicio y fin
    public String visitarCicloWhile(CicloWhile nodo) {
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

    // generar el ciclo do while que ejecuta el bloque al menos una vez
    public String visitarCicloDoWhile(CicloDoWhile nodo) {
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

    // generar la inicializacion con declaracion del for
    public String visitarInitForDecl(InitForDecl nodo) {
        // agregar la asignacion inicial si hay expresion a la lista de cuartetas
        if (nodo.getExpresion() != null) {

            // evaluar la expresion inicial
            String valor = nodo.getExpresion().accept(generador);

            // usar valor por defecto si el resultado es nulo
            if (valor == null) {
                valor = "_";
            }

            // agregar la asignacion a la variable a la lista de cuartetas
            ctx.getCuartetas().add(new Cuarteta(":=", valor, "_", nodo.getIdentificador(), ctx.inferirTipoDe(valor, ctx.getTiposConocidos()), "_", "_"));
        }

        return null;
    }

    // generar la inicializacion con asignacion del for
    public String visitarInitForAsig(InitForAsig nodo) {
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

    // visitar la expresion del paso del for
    public String visitarPasoForExpr(PasoForExpr nodo) {
        // visitar la expresion del paso si existe
        if (nodo.getExpresion() != null) {
            nodo.getExpresion().accept(generador);
        }

        return null;
    }

    // generar la asignacion del paso del for
    public String visitarPasoForAsig(PasoForAsig nodo) {
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
