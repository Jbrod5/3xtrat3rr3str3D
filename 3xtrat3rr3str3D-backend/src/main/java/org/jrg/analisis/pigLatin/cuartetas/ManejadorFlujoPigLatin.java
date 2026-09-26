package org.jrg.analisis.pigLatin.cuartetas;

import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.ciclo.CicloDum;
import org.jrg.model.ast.pigLatin.ciclo.CicloFacere;
import org.jrg.model.ast.pigLatin.ciclo.CicloPer;
import org.jrg.model.ast.pigLatin.condicional.StatementSi;
import org.jrg.model.ast.pigLatin.init_per.InitPerAsig;
import org.jrg.model.ast.pigLatin.init_per.InitPerDecl;
import org.jrg.model.ast.pigLatin.instruccion_flujo.StmtAsignacion;
import org.jrg.model.ast.pigLatin.instruccion_flujo.StmtCiclo;
import org.jrg.model.ast.pigLatin.instruccion_flujo.StmtCondicional;
import org.jrg.model.ast.pigLatin.instruccion_flujo.StmtExpresion;
import org.jrg.model.ast.pigLatin.instruccion_flujo.StmtImpresion;
import org.jrg.model.ast.pigLatin.instruccion_flujo.StmtInterrumpe;
import org.jrg.model.ast.pigLatin.instruccion_flujo.StmtLectura;
import org.jrg.model.ast.pigLatin.instruccion_flujo.StmtPerge;
import org.jrg.model.ast.pigLatin.instruccion_impresion.ImpresionEncadenada;
import org.jrg.model.ast.pigLatin.instruccion_lectura.LecturaConsolaAVariable;
import org.jrg.model.ast.pigLatin.instruccion_lectura.LecturaConsolaSimple;
import org.jrg.model.ast.pigLatin.paso_per.PasoPerAsig;
import org.jrg.model.ast.pigLatin.paso_per.PasoPerExpr;
import org.jrg.model.cuarteta.Cuarteta;

// generar cuartetas de flujo lectura e impresion en Pig Latin
public class ManejadorFlujoPigLatin {

    private final ContextoCuartetasPigLatin ctx;
    private final GeneradorCuartetasPigLatin generador;

    // crear la manejadora con contexto y generador
    public ManejadorFlujoPigLatin(ContextoCuartetasPigLatin ctx, GeneradorCuartetasPigLatin generador) {
        this.ctx = ctx;
        this.generador = generador;
    }

    // visitar la asignacion interna de la instruccion
    public String visitStmtAsignacion(StmtAsignacion stmt) {
        if (stmt.getAsignacion() != null) {
            stmt.getAsignacion().accept(generador);
        }
        return null;
    }

    // visitar el condicional interno de la instruccion
    public String visitStmtCondicional(StmtCondicional stmt) {
        // visitar el condicional
        if (stmt.getCondicional() != null) {
            stmt.getCondicional().accept(generador);
        }
        return null;
    }

    // visitar el ciclo interno de la instruccion
    public String visitStmtCiclo(StmtCiclo stmt) {
        if (stmt.getCiclo() != null) {
            stmt.getCiclo().accept(generador);
        }
        return null;
    }

    // visitar la lectura interna de la instruccion
    public String visitStmtLectura(StmtLectura stmt) {
        if (stmt.getLectura() != null) {
            stmt.getLectura().accept(generador);
        }
        return null;
    }

    // visitar la impresion interna de la instruccion
    public String visitStmtImpresion(StmtImpresion stmt) {
        if (stmt.getImpresion() != null) {
            stmt.getImpresion().accept(generador);
        }
        return null;
    }

    // generar salto a la etiqueta de break actual
    public String visitStmtInterrumpe(StmtInterrumpe stmt) {
        // agregar salto a la etiqueta de break actual si existe a la lista de cuartetas
        if (ctx.getEtiquetaBreakActual() != null) {
            ctx.getCuartetas().add(new Cuarteta("goto", ctx.getEtiquetaBreakActual(), "_", "_", "_", "_", "_"));
        }
        return null;
    }

    // generar salto a la etiqueta de continue actual
    public String visitStmtPerge(StmtPerge stmt) {
        // agregar salto a la etiqueta de continue actual si existe a la lista de cuartetas
        if (ctx.getEtiquetaContinueActual() != null) {
            ctx.getCuartetas().add(new Cuarteta("goto", ctx.getEtiquetaContinueActual(), "_", "_", "_", "_", "_"));
        }
        return null;
    }

    // visitar la expresion interna de la instruccion
    public String visitStmtExpresion(StmtExpresion stmt) {
        if (stmt.getExpresion() != null) {
            stmt.getExpresion().accept(generador);
        }
        return null;
    }

    // generar el si con ramas aliter y aliter final
    // version anterior: solo sacaba la rama principal y el aliter final, botaba los aliter intermedios
    // public String visitStatementSi(StatementSi stmt) {
    //     // regla para if: evaluar cond, luego if_false, bloque, goto, label else, bloque else, label fin
    //     String cond = null;
    //     if (stmt.getCondicion() != null) {
    //         cond = stmt.getCondicion().accept(generador);
    //     }
    //     String Lelse = ctx.getTemporales().nuevaEtiqueta();
    //     String Lfin = ctx.getTemporales().nuevaEtiqueta();
    //     // usar guion bajo si la condicion es nula
    //     String textoCond = "_";
    //     if (cond != null) {
    //         textoCond = cond;
    //     }
    //     // generar salto a else si es falso
    //     ctx.getCuartetas().add(new Cuarteta("if_false", textoCond, Lelse, "_", "booleano", "_", "_"));
    //     // bloque principal
    //     if (stmt.getBloque() != null) {
    //         stmt.getBloque().accept(generador);
    //     }
    //     // salto al final
    //     ctx.getCuartetas().add(new Cuarteta("goto", Lfin, "_", "_", "_", "_", "_"));
    //     // etiqueta else
    //     ctx.getCuartetas().add(new Cuarteta("label", Lelse, "_", "_", "_", "_", "_"));
    //     // bloque else
    //     if (stmt.getBloqueAliter() != null) {
    //         stmt.getBloqueAliter().accept(generador);
    //     }
    //     // etiqueta final
    //     ctx.getCuartetas().add(new Cuarteta("label", Lfin, "_", "_", "_", "_", "_"));
    //     return null;
    // }

    // generar el si con ramas aliter y aliter final
    public String visitStatementSi(StatementSi stmt) {
        // evaluar la condicion principal
        String cond = null;
        if (stmt.getCondicion() != null) {
            cond = stmt.getCondicion().accept(generador);
        }
        // crear la etiqueta final
        String Lfin = ctx.getTemporales().nuevaEtiqueta();
        // crear la etiqueta de la rama que sigue
        String LSiguiente = ctx.getTemporales().nuevaEtiqueta();
        // usar guion bajo si la condicion es nula
        String textoCond = "_";
        if (cond != null) {
            textoCond = cond;
        }
        // generar salto a la rama que sigue si es falso
        ctx.getCuartetas().add(new Cuarteta("if_false", textoCond, LSiguiente, "_", "booleano", "_", "_"));
        // bloque principal
        if (stmt.getBloque() != null) {
            stmt.getBloque().accept(generador);
        }
        // salto al final
        ctx.getCuartetas().add(new Cuarteta("goto", Lfin, "_", "_", "_", "_", "_"));
        // etiqueta de la rama que sigue
        ctx.getCuartetas().add(new Cuarteta("label", LSiguiente, "_", "_", "_", "_", "_"));
        // recorrer las ramas aliter si existen
        if (stmt.getCondicionesAliter() != null) {
            for (int i = 0; i < stmt.getCondicionesAliter().size(); i++) {
                // evaluar la condicion de la rama actual
                String condAliter = "_";
                if (stmt.getCondicionesAliter().get(i) != null) {
                    condAliter = stmt.getCondicionesAliter().get(i).accept(generador);
                }
                if (condAliter == null) {
                    condAliter = "_";
                }
                // crear la etiqueta de la rama que sigue
                String LSiguienteAliter = ctx.getTemporales().nuevaEtiqueta();
                // generar salto si la condicion es falsa
                ctx.getCuartetas().add(new Cuarteta("if_false", condAliter, LSiguienteAliter, "_", "booleano", "_", "_"));
                // visitar el bloque de la rama actual si existe
                if (stmt.getBloquesAliter() != null) {
                    if (i < stmt.getBloquesAliter().size()) {
                        if (stmt.getBloquesAliter().get(i) != null) {
                            stmt.getBloquesAliter().get(i).accept(generador);
                        }
                    }
                }
                // salto al final
                ctx.getCuartetas().add(new Cuarteta("goto", Lfin, "_", "_", "_", "_", "_"));
                // etiqueta de la rama que sigue
                ctx.getCuartetas().add(new Cuarteta("label", LSiguienteAliter, "_", "_", "_", "_", "_"));
            }
        }
        // bloque aliter final si existe
        if (stmt.getBloqueAliter() != null) {
            stmt.getBloqueAliter().accept(generador);
        }
        // etiqueta final
        ctx.getCuartetas().add(new Cuarteta("label", Lfin, "_", "_", "_", "_", "_"));
        return null;
    }
    // generar el ciclo dum con etiquetas de inicio y fin
    public String visitCicloDum(CicloDum ciclo) {
        String anteriorBreak = ctx.getEtiquetaBreakActual();
        String anteriorContinue = ctx.getEtiquetaContinueActual();
        String Linicio = ctx.getTemporales().nuevaEtiqueta();
        String Lfin = ctx.getTemporales().nuevaEtiqueta();
        ctx.setEtiquetaBreakActual(Lfin);
        ctx.setEtiquetaContinueActual(Linicio);
        // etiqueta inicio
        ctx.getCuartetas().add(new Cuarteta("label", Linicio, "_", "_", "_", "_", "_"));
        // evaluar condicion
        String cond = null;
        if (ciclo.getCondicion() != null) {
            cond = ciclo.getCondicion().accept(generador);
        }
        // usar guion bajo si la condicion es nula
        String textoCond = "_";
        if (cond != null) {
            textoCond = cond;
        }
        // salto si es falso
        ctx.getCuartetas().add(new Cuarteta("if_false", textoCond, Lfin, "_", "booleano", "_", "_"));
        // bloque
        if (ciclo.getBloque() != null) {
            ciclo.getBloque().accept(generador);
        }
        // salto al inicio
        ctx.getCuartetas().add(new Cuarteta("goto", Linicio, "_", "_", "_", "_", "_"));
        // etiqueta final
        ctx.getCuartetas().add(new Cuarteta("label", Lfin, "_", "_", "_", "_", "_"));
        // restaurar etiquetas
        ctx.setEtiquetaBreakActual(anteriorBreak);
        ctx.setEtiquetaContinueActual(anteriorContinue);
        return null;
    }

    // generar el ciclo facere que ejecuta el bloque al menos una vez
    public String visitCicloFacere(CicloFacere ciclo) {
        // guardar etiquetas anteriores
        String anteriorBreak = ctx.getEtiquetaBreakActual();
        String anteriorContinue = ctx.getEtiquetaContinueActual();
        String Linicio = ctx.getTemporales().nuevaEtiqueta();
        String Lcont = ctx.getTemporales().nuevaEtiqueta();
        String Lfin = ctx.getTemporales().nuevaEtiqueta();
        ctx.setEtiquetaBreakActual(Lfin);
        ctx.setEtiquetaContinueActual(Lcont);
        // etiqueta inicio
        ctx.getCuartetas().add(new Cuarteta("label", Linicio, "_", "_", "_", "_", "_"));
        // bloque (se ejecuta al menos una vez)
        if (ciclo.getBloque() != null) {
            ciclo.getBloque().accept(generador);
        }
        // etiqueta continue (donde salta perge)
        ctx.getCuartetas().add(new Cuarteta("label", Lcont, "_", "_", "_", "_", "_"));
        // evaluar condicion
        String cond = null;
        if (ciclo.getCondicion() != null) {
            cond = ciclo.getCondicion().accept(generador);
        }
        // usar guion bajo si la condicion es nula
        String textoCond = "_";
        if (cond != null) {
            textoCond = cond;
        }
        // si la condicion es verdadera, volver al inicio
        ctx.getCuartetas().add(new Cuarteta("if_false", textoCond, Lfin, "_", "booleano", "_", "_"));
        ctx.getCuartetas().add(new Cuarteta("goto", Linicio, "_", "_", "_", "_", "_"));
        // etiqueta final
        ctx.getCuartetas().add(new Cuarteta("label", Lfin, "_", "_", "_", "_", "_"));
        // restaurar etiquetas
        ctx.setEtiquetaBreakActual(anteriorBreak);
        ctx.setEtiquetaContinueActual(anteriorContinue);
        return null;
    }

    // generar el ciclo per con inicializacion condicion paso y bloque
    public String visitCicloPer(CicloPer ciclo) {
        String anteriorBreak = ctx.getEtiquetaBreakActual();
        String anteriorContinue = ctx.getEtiquetaContinueActual();
        String Linicio = ctx.getTemporales().nuevaEtiqueta();
        String Lfin = ctx.getTemporales().nuevaEtiqueta();
        ctx.setEtiquetaBreakActual(Lfin);
        ctx.setEtiquetaContinueActual(Linicio);
        // inicializacion
        if (ciclo.getInicializacion() != null) {
            ciclo.getInicializacion().accept(generador);
        }
        // etiqueta inicio
        ctx.getCuartetas().add(new Cuarteta("label", Linicio, "_", "_", "_", "_", "_"));
        // evaluar condicion
        String cond = null;
        if (ciclo.getCondicion() != null) {
            cond = ciclo.getCondicion().accept(generador);
        }
        // usar guion bajo si la condicion es nula
        String textoCond = "_";
        if (cond != null) {
            textoCond = cond;
        }
        // salto si falso
        ctx.getCuartetas().add(new Cuarteta("if_false", textoCond, Lfin, "_", "booleano", "_", "_"));
        // bloque
        if (ciclo.getBloque() != null) {
            ciclo.getBloque().accept(generador);
        }
        // paso
        if (ciclo.getPaso() != null) {
            ciclo.getPaso().accept(generador);
        }
        // salto al inicio
        ctx.getCuartetas().add(new Cuarteta("goto", Linicio, "_", "_", "_", "_", "_"));
        // etiqueta final
        ctx.getCuartetas().add(new Cuarteta("label", Lfin, "_", "_", "_", "_", "_"));
        // restaurar etiquetas
        ctx.setEtiquetaBreakActual(anteriorBreak);
        ctx.setEtiquetaContinueActual(anteriorContinue);
        return null;
    }

    // generar la inicializacion con declaracion del per
    public String visitInitPerDecl(InitPerDecl init) {
        // si hay valor inicial, agregar asignacion a la lista de cuartetas
        if (init.getValor() != null) {
            String val = init.getValor().accept(generador);
            // usar guion bajo si el valor es nulo
            String textoVal = "_";
            if (val != null) {
                textoVal = val;
            }
            ctx.getCuartetas().add(new Cuarteta(":=", textoVal, "_", init.getIdentificador(), ctx.inferirTipoDe(val, ctx.getTiposConocidos()), "_", "_"));
        }
        return null;
    }

    // generar la inicializacion con asignacion del per
    public String visitInitPerAsig(InitPerAsig init) {
        String var = init.getVariable().accept(generador);
        String val = init.getValor().accept(generador);
        // usar guion bajo si la variable es nula
        String textoVar = "_";
        if (var != null) {
            textoVar = var;
        }
        // usar guion bajo si el valor es nulo
        String textoVal = "_";
        if (val != null) {
            textoVal = val;
        }
        ctx.getCuartetas().add(new Cuarteta(":=", textoVal, "_", textoVar, ctx.inferirTipoDe(val, ctx.getTiposConocidos()), "_", "_"));
        return null;
    }

    // visitar la expresion del paso del per
    public String visitPasoPerExpr(PasoPerExpr paso) {
        if (paso.getExpresion() != null) {
            paso.getExpresion().accept(generador);
        }
        return null;
    }

    // generar la asignacion del paso del per
    public String visitPasoPerAsig(PasoPerAsig paso) {
        String var = paso.getVariable().accept(generador);
        String val = paso.getValor().accept(generador);
        // usar guion bajo si la variable es nula
        String textoVar = "_";
        if (var != null) {
            textoVar = var;
        }
        // usar guion bajo si el valor es nulo
        String textoVal = "_";
        if (val != null) {
            textoVal = val;
        }
        ctx.getCuartetas().add(new Cuarteta(":=", textoVal, "_", textoVar, ctx.inferirTipoDe(val, ctx.getTiposConocidos()), "_", "_"));
        return null;
    }

    // generar lectura de consola sin variable destino
    public String visitLecturaConsolaSimple(LecturaConsolaSimple lectura) {
        // agregar lectura sin variable destino a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("read", "_", "_", "_", "_", "_", "_"));
        return null;
    }

    // generar lectura de consola hacia una variable
    public String visitLecturaConsolaAVariable(LecturaConsolaAVariable lectura) {
        // leer a variable
        String var = null;
        if (lectura.getVariable() != null) {
            var = lectura.getVariable().accept(generador);
        }
        // usar guion bajo si la variable es nula
        String textoVar = "_";
        if (var != null) {
            textoVar = var;
        }
        ctx.getCuartetas().add(new Cuarteta("read", "_", "_", textoVar, "_", "_", "_"));
        return null;
    }

    // generar impresion encadenada con tipo por elemento
    public String visitImpresionEncadenada(ImpresionEncadenada impresion) {
        // visitar elementos
        if (impresion.getElementos() != null) {
            for (NodoAST elem : impresion.getElementos()) {
                if (elem != null) {
                    String val = elem.accept(generador);
                    // adivinar el tipo de lo que se imprime
                    String tipoValor = ctx.inferirTipoDe(val, ctx.getTiposConocidos());
                    // buscar en variables si el tipo sigue desconocido
                    if (tipoValor == null || "_".equals(tipoValor)) {
                        String tipoVar = ctx.getTiposDeVariables().get(val);
                        if (tipoVar != null) {
                            tipoValor = tipoVar;
                        }
                    }
                    // usar guion bajo si el tipo sigue nulo
                    if (tipoValor == null) {
                        tipoValor = "_";
                    }
                    // usar guion bajo si el valor es nulo
                    String textoVal = "_";
                    if (val != null) {
                        textoVal = val;
                    }
                    ctx.getCuartetas().add(new Cuarteta("print", textoVal, "_", "_", tipoValor, "_", "_"));
                }
            }
        }
        return null;
    }
}
