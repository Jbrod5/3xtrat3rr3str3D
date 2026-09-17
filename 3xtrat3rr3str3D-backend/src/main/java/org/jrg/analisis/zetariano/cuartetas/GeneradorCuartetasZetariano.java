package org.jrg.analisis.zetariano.cuartetas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jrg.model.ast.zetariano.Bloque;
import org.jrg.model.ast.zetariano.CasoDefault;
import org.jrg.model.ast.zetariano.CasoSwitch;
import org.jrg.model.ast.zetariano.ListaExpresiones;
import org.jrg.model.ast.zetariano.Parametros;
import org.jrg.model.ast.zetariano.Programa;
import org.jrg.model.ast.zetariano.TipoDato;
import org.jrg.model.ast.zetariano.ValorPrimitivo;
import org.jrg.model.ast.zetariano.asignacion.AsignacionCompuesta;
import org.jrg.model.ast.zetariano.asignacion.AsignacionSimple;
import org.jrg.model.ast.zetariano.atributo_clase.AtributoArray;
import org.jrg.model.ast.zetariano.atributo_clase.AtributoSimple;
import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.base.ZetarianoAstVisitor;
import org.jrg.model.ast.zetariano.ciclo.CicloDoWhile;
import org.jrg.model.ast.zetariano.ciclo.CicloFor;
import org.jrg.model.ast.zetariano.ciclo.CicloWhile;
import org.jrg.model.ast.zetariano.condicional.StatementIf;
import org.jrg.model.ast.zetariano.constructor.DefConstructor;
import org.jrg.model.ast.zetariano.declaracion_variable.DeclConListaLiteral;
import org.jrg.model.ast.zetariano.declaracion_variable.DeclConTipo;
import org.jrg.model.ast.zetariano.definicion_clase.DefClase;
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
import org.jrg.model.ast.zetariano.init_for.InitForAsig;
import org.jrg.model.ast.zetariano.init_for.InitForDecl;
import org.jrg.model.ast.zetariano.instruccion.StmtAsignacion;
import org.jrg.model.ast.zetariano.instruccion.StmtBreak;
import org.jrg.model.ast.zetariano.instruccion.StmtCiclo;
import org.jrg.model.ast.zetariano.instruccion.StmtCondicional;
import org.jrg.model.ast.zetariano.instruccion.StmtContinue;
import org.jrg.model.ast.zetariano.instruccion.StmtDeclaracion;
import org.jrg.model.ast.zetariano.instruccion.StmtExpresion;
import org.jrg.model.ast.zetariano.instruccion.StmtReturn;
import org.jrg.model.ast.zetariano.instruccion.StmtSeleccion;
import org.jrg.model.ast.zetariano.metodo.MetodoConRetorno;
import org.jrg.model.ast.zetariano.metodo.MetodoSinRetorno;
import org.jrg.model.ast.zetariano.miembro_clase.MiembroAtributo;
import org.jrg.model.ast.zetariano.miembro_clase.MiembroConstructor;
import org.jrg.model.ast.zetariano.miembro_clase.MiembroMetodo;
import org.jrg.model.ast.zetariano.parametro.ParamArray;
import org.jrg.model.ast.zetariano.parametro.ParamSimple;
import org.jrg.model.ast.zetariano.paso_for.PasoForAsig;
import org.jrg.model.ast.zetariano.paso_for.PasoForExpr;
import org.jrg.model.ast.zetariano.seleccion.StatementSwitch;
import org.jrg.model.ast.zetariano.variable_asignable.VarArray;
import org.jrg.model.ast.zetariano.variable_asignable.VarMiembro;
import org.jrg.model.ast.zetariano.variable_asignable.VarSimple;
import org.jrg.model.base.TipoPrimitivo;
import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.model.cuarteta.GeneradorTemporales;

// generador de cuartetas para el lenguaje Zetariano
public class GeneradorCuartetasZetariano implements ZetarianoAstVisitor<String> {

    // lista de cuartetas generadas
    private final List<Cuarteta> cuartetas;
    // generador de temporales y etiquetas
    private final GeneradorTemporales temporales;
    // etiqueta actual para romper
    private String etiquetaBreakActual;
    // etiqueta actual para continuar
    private String etiquetaContinueActual;
    // nombre de la clase actual para prefijar funciones
    private String nombreClaseActual;
    // tipos conocidos de temporales y variables
    private final Map<String, String> tiposConocidos;

    /**
     * Crear el generador de cuartetas para el lenguaje Zetariano.
     */
    public GeneradorCuartetasZetariano() {
        // inicializar la lista y el generador
        this.cuartetas = new ArrayList<>();
        this.temporales = new GeneradorTemporales();
        this.etiquetaBreakActual = null;
        this.etiquetaContinueActual = null;
        this.nombreClaseActual = null;
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

    // ==================== PROGRAMA Y BASE ====================

    @Override
    public String visitarPrograma(Programa nodo) {
        // visitar la definicion de clase si existe
        if (nodo.getDefinicionClase() != null) {
            nodo.getDefinicionClase().accept(this);
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
    public String visitarBloque(Bloque nodo) {
        // recorrer las instrucciones del bloque
        if (nodo.getInstrucciones() != null) {
            for (NodoASTZetariano instruccion : nodo.getInstrucciones()) {
                // visitar la instruccion actual si existe
                if (instruccion != null) {
                    instruccion.accept(this);
                }
            }
        }
        return null;
    }

    @Override
    public String visitarCasoSwitch(CasoSwitch nodo) {
        // recorrer las instrucciones del caso
        if (nodo.getInstrucciones() != null) {
            for (NodoASTZetariano instruccion : nodo.getInstrucciones()) {
                // visitar la instruccion actual si existe
                if (instruccion != null) {
                    instruccion.accept(this);
                }
            }
        }
        return null;
    }

    @Override
    public String visitarCasoDefault(CasoDefault nodo) {
        // recorrer las instrucciones del caso por defecto
        if (nodo.getInstrucciones() != null) {
            for (NodoASTZetariano instruccion : nodo.getInstrucciones()) {
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

    // ==================== CLASE Y MIEMBROS ====================

    @Override
    public String visitarDefClase(DefClase nodo) {
        // guardar el nombre de la clase actual
        this.nombreClaseActual = nodo.getNombre();
        // construir la lista de campos con sus tipos
        String campos = construirCamposClase(nodo.getMiembros());
        // emitir la definicion de la clase como struct
        cuartetas.add(new Cuarteta("struct_def", nodo.getNombre(), campos, "_", "_", "_", "_"));
        // recorrer cada miembro de la clase
        if (nodo.getMiembros() != null) {
            for (NodoASTZetariano miembro : nodo.getMiembros()) {
                // visitar el miembro actual si existe
                if (miembro != null) {
                    miembro.accept(this);
                }
            }
        }
        // limpiar el nombre de la clase actual
        this.nombreClaseActual = null;
        return null;
    }

    // construir el string de campos separados por coma con formato nombre:tipo
    private String construirCamposClase(List<NodoASTZetariano> miembros) {
        // devolver guion bajo si no hay miembros
        if (miembros == null || miembros.isEmpty()) {
            return "_";
        }
        // acumular cada campo con su tipo
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < miembros.size(); i++) {
            NodoASTZetariano miembro = miembros.get(i);
            // omitir miembros nulos o que no son atributos
            if (miembro instanceof MiembroAtributo == false) {
                continue;
            }
            // extraer el atributo interno
            NodoASTZetariano atributo = ((MiembroAtributo) miembro).getAtributo();
            // omitir atributos nulos
            if (atributo == null) {
                continue;
            }
            String campo = "";
            // extraer nombre y tipo segun la clase del atributo
            if (atributo instanceof AtributoSimple) {
                AtributoSimple simple = (AtributoSimple) atributo;
                campo = simple.getIdentificador() + ":" + extraerNombreTipoClase(simple.getTipo());
            } else if (atributo instanceof AtributoArray) {
                AtributoArray arreglo = (AtributoArray) atributo;
                campo = arreglo.getIdentificador() + ":" + extraerNombreTipoClase(arreglo.getTipo()) + "[]";
            }
            // omitir atributos de tipo desconocido
            if (campo.isEmpty()) {
                continue;
            }
            // separar campos con coma
            if (sb.length() > 0) {
                sb.append(",");
            }
            sb.append(campo);
        }
        // devolver guion bajo si no se recolecto ningun campo
        if (sb.length() == 0) {
            return "_";
        }
        return sb.toString();
    }

    // extraer el nombre del tipo desde un nodo de tipo
    private String extraerNombreTipoClase(NodoASTZetariano tipoNodo) {
        // devolver guion bajo si el nodo es nulo
        if (tipoNodo == null) {
            return "_";
        }
        // extraer el nombre cuando es TipoDato
        if (tipoNodo instanceof TipoDato) {
            String nombre = ((TipoDato) tipoNodo).getTipo();
            // usar guion bajo si el nombre es nulo
            if (nombre == null) {
                return "_";
            }
            return nombre;
        }
        return "_";
    }

    @Override
    public String visitarMiembroAtributo(MiembroAtributo nodo) {
        // no emitir cuartetas porque los atributos solo ocupan memoria
        return null;
    }

    @Override
    public String visitarMiembroConstructor(MiembroConstructor nodo) {
        // visitar el constructor interno si existe
        if (nodo.getConstructor() != null) {
            nodo.getConstructor().accept(this);
        }
        return null;
    }

    @Override
    public String visitarMiembroMetodo(MiembroMetodo nodo) {
        // visitar el metodo interno si existe
        if (nodo.getMetodo() != null) {
            nodo.getMetodo().accept(this);
        }
        return null;
    }

    @Override
    public String visitarAtributoSimple(AtributoSimple nodo) {
        // TODO
        return null;
    }

    @Override
    public String visitarAtributoArray(AtributoArray nodo) {
        // TODO
        return null;
    }

    // ==================== CONSTRUCTOR Y METODOS ====================

    @Override
    public String visitarDefConstructor(DefConstructor nodo) {
        // construir el string de tipos de parametros
        String tiposParams = extraerTiposParametrosZet(nodo.getParametros());
        // construir el nombre completo incluyendo el nombre del constructor
        String nombreFuncion = nodo.getNombre() + "_" + nodo.getNombre();
        // emitir marcador de inicio
        cuartetas.add(new Cuarteta("func_begin", nombreFuncion, tiposParams, "void", tiposParams, "_", "void"));
        // recorrer las instrucciones del cuerpo
        if (nodo.getInstrucciones() != null) {
            for (NodoASTZetariano instruccion : nodo.getInstrucciones()) {
                if (instruccion != null) {
                    instruccion.accept(this);
                }
            }
        }
        // emitir marcador de fin
        cuartetas.add(new Cuarteta("func_end", nombreFuncion, "_", "_", "_", "_", "_"));
        return null;
    }

    @Override
    public String visitarMetodoSinRetorno(MetodoSinRetorno nodo) {
        // construir el string de tipos de parametros
        String tiposParams = extraerTiposParametrosZet(nodo.getParametros());
        // construir el prefijo con el nombre de la clase actual
        String prefijo = "Clase";
        if (this.nombreClaseActual != null) {
            prefijo = this.nombreClaseActual;
        }
        // construir el nombre completo
        String nombreFuncion = prefijo + "_" + nodo.getNombre();
        // emitir marcador de inicio
        cuartetas.add(new Cuarteta("func_begin", nombreFuncion, tiposParams, "void", tiposParams, "_", "void"));
        // recorrer las instrucciones del cuerpo
        if (nodo.getInstrucciones() != null) {
            for (NodoASTZetariano instruccion : nodo.getInstrucciones()) {
                if (instruccion != null) {
                    instruccion.accept(this);
                }
            }
        }
        // emitir marcador de fin
        cuartetas.add(new Cuarteta("func_end", nombreFuncion, "_", "_", "_", "_", "_"));
        return null;
    }

    @Override
    public String visitarMetodoConRetorno(MetodoConRetorno nodo) {
        // construir el string de tipos de parametros
        String tiposParams = extraerTiposParametrosZet(nodo.getParametros());
        // extraer el tipo de retorno
        String tipoRetorno = "_";
        if (nodo.getTipo() instanceof TipoDato) {
            tipoRetorno = ((TipoDato) nodo.getTipo()).getTipo();
        }
        if (tipoRetorno == null) {
            tipoRetorno = "_";
        }
        // construir el prefijo con el nombre de la clase actual
        String prefijo = "Clase";
        if (this.nombreClaseActual != null) {
            prefijo = this.nombreClaseActual;
        }
        // construir el nombre completo
        String nombreFuncion = prefijo + "_" + nodo.getNombre();
        // emitir marcador de inicio
        cuartetas.add(new Cuarteta("func_begin", nombreFuncion, tiposParams, tipoRetorno, tiposParams, "_", tipoRetorno));
        // recorrer las instrucciones del cuerpo
        if (nodo.getInstrucciones() != null) {
            for (NodoASTZetariano instruccion : nodo.getInstrucciones()) {
                if (instruccion != null) {
                    instruccion.accept(this);
                }
            }
        }
        // emitir marcador de fin
        cuartetas.add(new Cuarteta("func_end", nombreFuncion, "_", "_", "_", "_", "_"));
        return null;
    }

    // construir el string de params con formato nombre tipo separados por coma
    private String extraerTiposParametrosZet(NodoASTZetariano parametrosNodo) {
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
        // acumular los params separados por coma
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parametros.getParametros().size(); i++) {
            NodoASTZetariano p = parametros.getParametros().get(i);
            String nombre = "_";
            String tipo = "_";
            if (p instanceof ParamSimple) {
                nombre = ((ParamSimple) p).getIdentificador();
                NodoASTZetariano tipoNodo = ((ParamSimple) p).getTipo();
                if (tipoNodo instanceof TipoDato) {
                    tipo = ((TipoDato) tipoNodo).getTipo();
                }
            } else if (p instanceof ParamArray) {
                nombre = ((ParamArray) p).getIdentificador();
                NodoASTZetariano tipoNodo = ((ParamArray) p).getTipo();
                if (tipoNodo instanceof TipoDato) {
                    tipo = ((TipoDato) tipoNodo).getTipo() + "[]";
                }
            }
            if (nombre == null) {
                nombre = "_";
            }
            if (tipo == null) {
                tipo = "_";
            }
            if (i > 0) {
                sb.append(",");
            }
            sb.append(nombre).append(":").append(tipo);
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
    public String visitarStmtReturn(StmtReturn nodo) {
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
    public String visitarStmtBreak(StmtBreak nodo) {
        // emitir salto a la etiqueta de romper si existe
        if (this.etiquetaBreakActual != null) {
            cuartetas.add(new Cuarteta("goto", this.etiquetaBreakActual, "_", "_", "_", "_", "_"));
        }
        return null;
    }

    @Override
    public String visitarStmtContinue(StmtContinue nodo) {
        // emitir salto a la etiqueta de continuar si existe
        if (this.etiquetaContinueActual != null) {
            cuartetas.add(new Cuarteta("goto", this.etiquetaContinueActual, "_", "_", "_", "_", "_"));
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
    public String visitarDeclConTipo(DeclConTipo nodo) {
        // verificar si la declaracion es un arreglo
        if (nodo.getDimensiones() > 0) {
            // extraer el nombre del tipo base
            String tipoBase = "_";
            if (nodo.getTipo() instanceof TipoDato) {
                tipoBase = ((TipoDato) nodo.getTipo()).getTipo();
            }
            // usar valor por defecto si el nombre es nulo
            if (tipoBase == null) {
                tipoBase = "_";
            }
            // emitir la reserva de memoria con la cantidad de dimensiones
            cuartetas.add(new Cuarteta("alloc", tipoBase, String.valueOf(nodo.getDimensiones()), nodo.getIdentificador(), tipoBase, "entero", tipoBase));
            // emitir la asignacion inicial si hay valor
            if (nodo.getValor() != null) {
                // evaluar el valor inicial
                String valor = nodo.getValor().accept(this);
                // usar valor por defecto si el resultado es nulo
                if (valor == null) {
                    valor = "_";
                }
                // emitir la asignacion a la variable
                cuartetas.add(new Cuarteta(":=", valor, "_", nodo.getIdentificador(), inferirTipoDe(valor, tiposConocidos), "_", "_"));
            }
            return null;
        }
        // emitir la asignacion inicial si hay valor
        if (nodo.getValor() != null) {
            // evaluar el valor inicial
            String valor = nodo.getValor().accept(this);
            // usar valor por defecto si el resultado es nulo
            if (valor == null) {
                valor = "_";
            }
            // emitir la asignacion a la variable
            cuartetas.add(new Cuarteta(":=", valor, "_", nodo.getIdentificador(), inferirTipoDe(valor, tiposConocidos), "_", "_"));
        }
        return null;
    }

    @Override
    public String visitarDeclConListaLiteral(DeclConListaLiteral nodo) {
        // obtener la lista de valores iniciales
        NodoASTZetariano lista = nodo.getListaExpresiones();
        // recorrer los valores si la lista existe
        if (lista instanceof ListaExpresiones) {
            // convertir la lista al tipo concreto
            ListaExpresiones listaExpresiones = (ListaExpresiones) lista;
            // contar la cantidad de valores
            int cantidad = 0;
            if (listaExpresiones.getExpresiones() != null) {
                cantidad = listaExpresiones.getExpresiones().size();
            }
            // emitir la reserva de memoria con la cantidad de valores
            cuartetas.add(new Cuarteta("alloc", String.valueOf(cantidad), "_", nodo.getIdentificador(), "entero", "_", "_"));
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
                    cuartetas.add(new Cuarteta("[]=", nodo.getIdentificador(), String.valueOf(i), valor, "_", "entero", "_"));
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

    // ==================== ASIGNACIONES ====================

    @Override
    public String visitarAsignacionSimple(AsignacionSimple nodo) {
        // evaluar el valor a asignar
        String derecha = "_";
        if (nodo.getExpresion() != null) {
            derecha = nodo.getExpresion().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (derecha == null) {
            derecha = "_";
        }
        // manejar la asignacion a posicion de arreglo con []=
        if (nodo.getVariable() instanceof VarArray) {
            // convertir la variable al tipo concreto
            VarArray acceso = (VarArray) nodo.getVariable();
            // evaluar la base del acceso
            String base = "_";
            if (acceso.getVariable() != null) {
                base = acceso.getVariable().accept(this);
            }
            // usar valor por defecto si el resultado es nulo
            if (base == null) {
                base = "_";
            }
            // evaluar el indice del acceso
            String indice = "_";
            if (acceso.getIndice() != null) {
                indice = acceso.getIndice().accept(this);
            }
            // usar valor por defecto si el resultado es nulo
            if (indice == null) {
                indice = "_";
            }
            // emitir la asignacion a la posicion
            cuartetas.add(new Cuarteta("[]=", base, indice, derecha, "_", "entero", "_"));
            return null;
        }
        // evaluar la variable destino
        String izquierda = "_";
        if (nodo.getVariable() != null) {
            izquierda = nodo.getVariable().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (izquierda == null) {
            izquierda = "_";
        }
        // emitir la asignacion
        cuartetas.add(new Cuarteta(":=", derecha, "_", izquierda, inferirTipoDe(derecha, tiposConocidos), "_", "_"));
        return null;
    }

    @Override
    public String visitarAsignacionCompuesta(AsignacionCompuesta nodo) {
        // extraer el operador aritmetico quitando el igual final
        String aritmetico = "_";
        if (nodo.getOperador() != null) {
            aritmetico = nodo.getOperador();
            // quitar el igual final si existe
            if (aritmetico.endsWith("=")) {
                aritmetico = aritmetico.substring(0, aritmetico.length() - 1);
            }
        }
        // evaluar la variable destino
        String izquierda = "_";
        if (nodo.getVariable() != null) {
            izquierda = nodo.getVariable().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (izquierda == null) {
            izquierda = "_";
        }
        // evaluar la expresion derecha
        String derecha = "_";
        if (nodo.getExpresion() != null) {
            derecha = nodo.getExpresion().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (derecha == null) {
            derecha = "_";
        }
        // emitir la operacion con temporal
        String temp = temporales.nuevoTemporal();
        // inferir el tipo resultado de la operacion
        String tipoResComp = tipoResultadoAritmetico(izquierda, derecha);
        // registrar el temporal con el tipo inferido
        tiposConocidos.put(temp, tipoResComp);
        cuartetas.add(new Cuarteta(aritmetico, izquierda, derecha, temp, tipoAritmetico(izquierda), tipoAritmetico(derecha), tipoResComp));
        // manejar la asignacion a posicion de arreglo con []=
        if (nodo.getVariable() instanceof VarArray) {
            // convertir la variable al tipo concreto
            VarArray acceso = (VarArray) nodo.getVariable();
            // evaluar la base del acceso
            String base = "_";
            if (acceso.getVariable() != null) {
                base = acceso.getVariable().accept(this);
            }
            // usar valor por defecto si el resultado es nulo
            if (base == null) {
                base = "_";
            }
            // evaluar el indice del acceso
            String indice = "_";
            if (acceso.getIndice() != null) {
                indice = acceso.getIndice().accept(this);
            }
            // usar valor por defecto si el resultado es nulo
            if (indice == null) {
                indice = "_";
            }
            // emitir la asignacion a la posicion
            cuartetas.add(new Cuarteta("[]=", base, indice, temp, "_", "entero", "_"));
            return null;
        }
        // emitir la asignacion del temporal a la variable
        cuartetas.add(new Cuarteta(":=", temp, "_", izquierda, inferirTipoDe(temp, tiposConocidos), "_", "_"));
        return null;
    }

    // ==================== VARIABLES ASIGNABLES ====================

    @Override
    public String visitarVarSimple(VarSimple nodo) {
        // devolver el identificador directamente
        return nodo.getIdentificador();
    }

    @Override
    public String visitarVarArray(VarArray nodo) {
        // evaluar la base del acceso
        String base = "_";
        if (nodo.getVariable() != null) {
            base = nodo.getVariable().accept(this);
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
        if (nodo.getVariable() != null) {
            base = nodo.getVariable().accept(this);
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
    public String visitarStatementIf(StatementIf nodo) {
        // evaluar la condicion principal
        String condicion = "_";
        if (nodo.getCondicion() != null) {
            condicion = nodo.getCondicion().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (condicion == null) {
            condicion = "_";
        }
        // crear la etiqueta final
        String lFin = temporales.nuevaEtiqueta();
        // crear la etiqueta de la rama que sigue
        String lSiguiente = temporales.nuevaEtiqueta();
        // emitir el salto a la rama que sigue si la condicion es falsa
        cuartetas.add(new Cuarteta("if_false", condicion, lSiguiente, "_", "booleano", "_", "_"));
        // visitar el bloque principal si existe
        if (nodo.getBloque() != null) {
            nodo.getBloque().accept(this);
        }
        // emitir el salto al final
        cuartetas.add(new Cuarteta("goto", lFin, "_", "_", "_", "_", "_"));
        // emitir la etiqueta de la rama que sigue
        cuartetas.add(new Cuarteta("label", lSiguiente, "_", "_", "_", "_", "_"));
        // recorrer las ramas sino si si existen
        if (nodo.getCondicionesSinoSi() != null) {
            for (int i = 0; i < nodo.getCondicionesSinoSi().size(); i++) {
                // evaluar la condicion de la rama actual
                String condicionSino = "_";
                if (nodo.getCondicionesSinoSi().get(i) != null) {
                    condicionSino = nodo.getCondicionesSinoSi().get(i).accept(this);
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
                if (nodo.getBloquesSinoSi() != null) {
                    if (i < nodo.getBloquesSinoSi().size()) {
                        if (nodo.getBloquesSinoSi().get(i) != null) {
                            nodo.getBloquesSinoSi().get(i).accept(this);
                        }
                    }
                }
                // emitir el salto al final
                cuartetas.add(new Cuarteta("goto", lFin, "_", "_", "_", "_", "_"));
                // emitir la etiqueta de la rama que sigue
                cuartetas.add(new Cuarteta("label", lSiguienteSino, "_", "_", "_", "_", "_"));
            }
        }
        // visitar el bloque sino si existe
        if (nodo.getBloqueSino() != null) {
            nodo.getBloqueSino().accept(this);
        }
        // emitir la etiqueta final
        cuartetas.add(new Cuarteta("label", lFin, "_", "_", "_", "_", "_"));
        return null;
    }

    // ==================== SELECCION ====================

    @Override
    public String visitarStatementSwitch(StatementSwitch nodo) {
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
        String lFin = temporales.nuevaEtiqueta();
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
                        valorCaso = casoSwitch.getValor().accept(this);
                    }
                    // usar valor por defecto si el resultado es nulo
                    if (valorCaso == null) {
                        valorCaso = "_";
                    }
                    // guardar el valor del caso en un temporal
                    String tCaso = temporales.nuevoTemporal();
                    // inferir el tipo del valor del caso
                    String tipoCaso = inferirTipoDe(valorCaso, tiposConocidos);
                    // registrar el temporal con el tipo inferido
                    tiposConocidos.put(tCaso, tipoCaso);
                    cuartetas.add(new Cuarteta("=", valorCaso, "_", tCaso, tipoCaso, "_", tipoCaso));
                    // comparar el selector con el valor del caso
                    String tComparacion = temporales.nuevoTemporal();
                    // registrar el temporal como booleano
                    tiposConocidos.put(tComparacion, "booleano");
                    cuartetas.add(new Cuarteta("==", selector, tCaso, tComparacion, inferirTipoDe(selector, tiposConocidos), inferirTipoDe(tCaso, tiposConocidos), "booleano"));
                    // crear la etiqueta del caso que sigue
                    String lSiguiente = temporales.nuevaEtiqueta();
                    // emitir el salto si no hay coincidencia
                    cuartetas.add(new Cuarteta("if_false", tComparacion, lSiguiente, "_", "booleano", "_", "_"));
                    // visitar las instrucciones del caso
                    if (casoSwitch.getInstrucciones() != null) {
                        for (NodoASTZetariano instruccion : casoSwitch.getInstrucciones()) {
                            // visitar la instruccion actual si existe
                            if (instruccion != null) {
                                instruccion.accept(this);
                            }
                        }
                    }
                    // emitir el salto al final
                    cuartetas.add(new Cuarteta("goto", lFin, "_", "_", "_", "_", "_"));
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
        cuartetas.add(new Cuarteta("label", lFin, "_", "_", "_", "_", "_"));
        return null;
    }

    // ==================== CICLOS ====================

    @Override
    public String visitarCicloFor(CicloFor nodo) {
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
    public String visitarCicloWhile(CicloWhile nodo) {
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
    public String visitarCicloDoWhile(CicloDoWhile nodo) {
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

    // ==================== INIT Y PASO DEL FOR ====================

    @Override
    public String visitarInitForDecl(InitForDecl nodo) {
        // emitir la asignacion inicial si hay expresion
        if (nodo.getExpresion() != null) {
            // evaluar la expresion inicial
            String valor = nodo.getExpresion().accept(this);
            // usar valor por defecto si el resultado es nulo
            if (valor == null) {
                valor = "_";
            }
            // emitir la asignacion a la variable
            cuartetas.add(new Cuarteta(":=", valor, "_", nodo.getIdentificador(), inferirTipoDe(valor, tiposConocidos), "_", "_"));
        }
        return null;
    }

    @Override
    public String visitarInitForAsig(InitForAsig nodo) {
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
    public String visitarPasoForExpr(PasoForExpr nodo) {
        // visitar la expresion del paso si existe
        if (nodo.getExpresion() != null) {
            nodo.getExpresion().accept(this);
        }
        return null;
    }

    @Override
    public String visitarPasoForAsig(PasoForAsig nodo) {
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
                    String argumento = lista.getExpresiones().get(i).accept(this);
                    // usar valor por defecto si el resultado es nulo
                    if (argumento == null) {
                        argumento = "_";
                    }
                    // agregar el argumento a la lista
                    argumentos.add(argumento);
                }
            }
        }
        // emitir un param por cada argumento
        for (int i = 0; i < argumentos.size(); i++) {
            cuartetas.add(new Cuarteta("param", argumentos.get(i), "_", "_", inferirTipoDe(argumentos.get(i), tiposConocidos), "_", "_"));
        }
        // emitir la instancia y guardar el resultado en un temporal
        String temp = temporales.nuevoTemporal();
        // registrar el temporal con el tipo de la clase
        tiposConocidos.put(temp, nodo.getNombreClase());
        cuartetas.add(new Cuarteta("new", nodo.getNombreClase(), String.valueOf(argumentos.size()), temp, nodo.getNombreClase(), "_", nodo.getNombreClase()));
        return temp;
    }

    @Override
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
        String dimension = "_";
        if (nodo.getDimensiones() != null) {
            if (nodo.getDimensiones().isEmpty() == false) {
                // acumular las dimensiones separadas por coma
                String acumulado = "";
                String separador = "";
                for (int i = 0; i < nodo.getDimensiones().size(); i++) {
                    // evaluar la dimension actual
                    String valorDimension = nodo.getDimensiones().get(i).accept(this);
                    // usar valor por defecto si el resultado es nulo
                    if (valorDimension == null) {
                        valorDimension = "_";
                    }
                    // agregar la dimension al acumulado
                    acumulado = acumulado + separador + valorDimension;
                    separador = ",";
                }
                dimension = acumulado;
            }
        }
        // emitir la reserva de memoria con temporal
        String temp = temporales.nuevoTemporal();
        // registrar el temporal con el tipo base
        tiposConocidos.put(temp, tipoBase);
        cuartetas.add(new Cuarteta("alloc", tipoBase, dimension, temp, tipoBase, inferirTipoDe(dimension, tiposConocidos), tipoBase));
        return temp;
    }

    @Override
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
                    String argumento = lista.getExpresiones().get(i).accept(this);
                    // usar valor por defecto si el resultado es nulo
                    if (argumento == null) {
                        argumento = "_";
                    }
                    // agregar el argumento a la lista
                    argumentos.add(argumento);
                }
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
    public String visitarExprLlamadaMetodo(ExprLlamadaMetodo nodo) {
        // evaluar el objeto del metodo
        String objeto = "_";
        if (nodo.getObjeto() != null) {
            objeto = nodo.getObjeto().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
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
                    String argumento = lista.getExpresiones().get(i).accept(this);
                    // usar valor por defecto si el resultado es nulo
                    if (argumento == null) {
                        argumento = "_";
                    }
                    // agregar el argumento a la lista
                    argumentos.add(argumento);
                }
            }
        }
        // emitir el param del objeto primero
        cuartetas.add(new Cuarteta("param", objeto, "_", "_", inferirTipoDe(objeto, tiposConocidos), "_", "_"));
        // emitir un param por cada argumento
        for (int i = 0; i < argumentos.size(); i++) {
            cuartetas.add(new Cuarteta("param", argumentos.get(i), "_", "_", inferirTipoDe(argumentos.get(i), tiposConocidos), "_", "_"));
        }
        // emitir la llamada al metodo y guardar el resultado en un temporal
        String temp = temporales.nuevoTemporal();
        cuartetas.add(new Cuarteta("call_method", nodo.getNombre(), String.valueOf(argumentos.size() + 1), temp, "_", "_", "_"));
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
        cuartetas.add(new Cuarteta(nodo.getOperador(), valor, "_", temp, tipoNeg, "_", tipoNeg));
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
    public String visitarExprMultiplicacionDivisionModulo(ExprMultiplicacionDivisionModulo nodo) {
        // evaluar el operando izquierdo
        String izquierdo = "_";
        if (nodo.getOperandoIzquierdo() != null) {
            izquierdo = nodo.getOperandoIzquierdo().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (izquierdo == null) {
            izquierdo = "_";
        }
        // evaluar el operando derecho
        String derecho = "_";
        if (nodo.getOperandoDerecho() != null) {
            derecho = nodo.getOperandoDerecho().accept(this);
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
        if (nodo.getOperandoIzquierdo() != null) {
            izquierdo = nodo.getOperandoIzquierdo().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (izquierdo == null) {
            izquierdo = "_";
        }
        // evaluar el operando derecho
        String derecho = "_";
        if (nodo.getOperandoDerecho() != null) {
            derecho = nodo.getOperandoDerecho().accept(this);
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
        if (nodo.getOperandoIzquierdo() != null) {
            izquierdo = nodo.getOperandoIzquierdo().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (izquierdo == null) {
            izquierdo = "_";
        }
        // evaluar el operando derecho
        String derecho = "_";
        if (nodo.getOperandoDerecho() != null) {
            derecho = nodo.getOperandoDerecho().accept(this);
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
        if (nodo.getOperandoIzquierdo() != null) {
            izquierdo = nodo.getOperandoIzquierdo().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (izquierdo == null) {
            izquierdo = "_";
        }
        // evaluar el operando derecho
        String derecho = "_";
        if (nodo.getOperandoDerecho() != null) {
            derecho = nodo.getOperandoDerecho().accept(this);
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
        if (nodo.getOperandoIzquierdo() != null) {
            izquierdo = nodo.getOperandoIzquierdo().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (izquierdo == null) {
            izquierdo = "_";
        }
        // evaluar el operando derecho
        String derecho = "_";
        if (nodo.getOperandoDerecho() != null) {
            derecho = nodo.getOperandoDerecho().accept(this);
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
    public String visitarExprTernario(ExprTernario nodo) {
        // evaluar la condicion
        String condicion = "_";
        if (nodo.getCondicion() != null) {
            condicion = nodo.getCondicion().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (condicion == null) {
            condicion = "_";
        }
        // crear el temporal del resultado
        String temp = temporales.nuevoTemporal();
        // crear la etiqueta de la rama falsa
        String lFalso = temporales.nuevaEtiqueta();
        // crear la etiqueta final
        String lFin = temporales.nuevaEtiqueta();
        // emitir el salto a la rama falsa si la condicion es falsa
        cuartetas.add(new Cuarteta("if_false", condicion, lFalso, "_", "booleano", "_", "_"));
        // evaluar el valor verdadero
        String verdadero = "_";
        if (nodo.getValorVerdadero() != null) {
            verdadero = nodo.getValorVerdadero().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (verdadero == null) {
            verdadero = "_";
        }
        // inferir los tipos de ambas ramas
        String tipoVerdadero = inferirTipoDe(verdadero, tiposConocidos);
        // emitir la asignacion del valor verdadero
        cuartetas.add(new Cuarteta(":=", verdadero, "_", temp, tipoVerdadero, "_", "_"));
        // emitir el salto al final
        cuartetas.add(new Cuarteta("goto", lFin, "_", "_", "_", "_", "_"));
        // emitir la etiqueta de la rama falsa
        cuartetas.add(new Cuarteta("label", lFalso, "_", "_", "_", "_", "_"));
        // evaluar el valor falso
        String falso = "_";
        if (nodo.getValorFalso() != null) {
            falso = nodo.getValorFalso().accept(this);
        }
        // usar valor por defecto si el resultado es nulo
        if (falso == null) {
            falso = "_";
        }
        // inferir los tipos de ambas ramas
        String tipoFalso = inferirTipoDe(falso, tiposConocidos);
        // emitir la asignacion del valor falso
        cuartetas.add(new Cuarteta(":=", falso, "_", temp, tipoFalso, "_", "_"));
        // emitir la etiqueta final
        cuartetas.add(new Cuarteta("label", lFin, "_", "_", "_", "_", "_"));
        // registrar el temporal con el tipo comun de las ramas
        if (tipoVerdadero.equals(tipoFalso)) {
            tiposConocidos.put(temp, tipoVerdadero);
        }
        return temp;
    }

    @Override
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
                String tempNulo = temporales.nuevoTemporal();
                cuartetas.add(new Cuarteta("=", "null", "_", tempNulo, "_", "_", "_"));
                return tempNulo;
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
