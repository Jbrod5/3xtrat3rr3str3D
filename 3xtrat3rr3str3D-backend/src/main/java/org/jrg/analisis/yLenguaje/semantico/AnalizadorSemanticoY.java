package org.jrg.analisis.yLenguaje.semantico;

import java.util.ArrayList;
import java.util.List;

import org.jrg.model.base.FlujoControl;
import org.jrg.model.base.TipoPrimitivo;
import org.jrg.model.semantico.CategoriaSimbolo;
import org.jrg.model.semantico.Simbolo;
import org.jrg.model.semantico.Tipo;
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
import org.jrg.model.ast.yLenguaje.declaracion_variable.DeclMatrizConValores;
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
import org.jrg.service.error.RecolectorErrores;

// definir el analizador semantico para el lenguaje Y
public class AnalizadorSemanticoY implements YAstVisitor<Object> {


    private final ContextoSemanticoY contexto;    // contexto semantico del analisis
    private boolean enFuncionConRetorno;          // indicador de si se esta dentro de una funcion con retorno
    private Tipo tipoRetornoActual;               //  tipo de retorno esperado actual

    /**
     * Crear el analizador semantico para el lenguaje Y.
     */
    public AnalizadorSemanticoY(RecolectorErrores recolectorErrores) {

        // crear el contexto con el recolector
        this.contexto = new ContextoSemanticoY(recolectorErrores);
        this.enFuncionConRetorno = false;
        this.tipoRetornoActual = null;

    }

    /**
     * Analizar un programa Y de forma semantica.
     */
    public void analizar(Programa programa) {

        // iniciar el contexto semantico
        this.contexto.iniciar();

        // verificar si el programa no es nulo
        if (programa != null) {

            // entrar en el programa
            programa.accept(this);
        }
    }

    /**
     * Obtener el contexto semantico del analisis.
     */
    public ContextoSemanticoY obtenerContexto() {
        return this.contexto;
    }

    /**
     * Obtener todos los simbolos registrados.
     */
    public List<Simbolo> obtenerSimbolos() {
        return this.contexto.obtenerSimbolos();
    }

    /**
     * Obtener todos los tipos registrados.
     */
    public List<Tipo> obtenerTipos() {
        return this.contexto.obtenerTipos();
    }




    // ==================== AUXILIARES DE TIPOS ====================

    // sacar el tipo de un nodo TipoDato
    private Tipo resolverTipoDato(NodoASTY tipoNodo) {

        // verificar si el nodo es nulo
        if (tipoNodo == null) {
            return null;
        }

        // verificar si el nodo no es TipoDato
        if (!(tipoNodo instanceof TipoDato)) {
            return null;
        }

        // convertir el nodo a TipoDato
        TipoDato tipoDato = (TipoDato) tipoNodo;

        // resolver el tipo por su nombre
        return this.contexto.tipoPorNombre(tipoDato.getNombre());
    }

    // sacar el tipo de lo que dio la visita
    private Tipo extraerTipoDeExpresion(Object resultado) {

        // verificar si el resultado es un Tipo
        if (resultado instanceof Tipo) {
            return (Tipo) resultado;
        }

        return null;
    }

    // extraer el valor entero de un nodo si es una constante literal
    private Integer extraerEnteroConstante(NodoASTY nodo) {

        // verificar si el nodo es nulo
        if (nodo == null) {
            return null;
        }

        // caso: primitiva con valor primitivo
        if (nodo instanceof ExprPrimitivo) {

            ExprPrimitivo expr = (ExprPrimitivo) nodo;
            NodoASTY valor = expr.getValor();

            // verificar si el valor es primitivo
            if (valor instanceof ValorPrimitivo) {
                ValorPrimitivo vp = (ValorPrimitivo) valor;

                // verificar si el tipo es entero
                if (vp.getTipo() == TipoPrimitivo.ENTERO) {

                    try {
                        // convertir el valor a entero
                        return Integer.parseInt(vp.getValor());

                    } catch (NumberFormatException e) {
                        return null;
                    }

                }
            }
        }
        // caso valor primitivo directo
        if (nodo instanceof ValorPrimitivo) {
            ValorPrimitivo vp = (ValorPrimitivo) nodo;

            // verificar si el tipo es entero
            if (vp.getTipo() == TipoPrimitivo.ENTERO) {

                try {
                    // convertir el valor a entero
                    return Integer.parseInt(vp.getValor());

                } catch (NumberFormatException e) {
                    return null;
                }
            }
        }

        return null;
    }

    // ==================== DECLARACION SEGURA ====================

    // declarar variable o parametro buscando en todos los ambitos padres
    private boolean declararVariableSeguro(NodoASTY nodo, String nombre, Tipo tipo, CategoriaSimbolo categoria, Integer tamano) {

        // verificar si el nombre es valido
        if (nombre == null || nombre.isEmpty()) {
            this.contexto.agregarError(nodo, "declaracion sin identificador");

            return false;
        }

        // buscar el simbolo en el ambito actual
        Simbolo existente = this.contexto.ambitoActual().buscarSimbolo(nombre);

        // reportar el error si ya existe
        if (existente != null) {
            this.contexto.agregarError(nodo, "la variable '" + nombre + "' ya esta declarada en un ambito padre");

            return false;
        }

        // pasar la declaracion al contexto
        return this.contexto.declarar(nodo, nombre, tipo, categoria, tamano);
    }

    // ==================== BUILT-INS ====================

    // meter print y read al ambito global
    private void registrarFuncionesBuiltIn() {

        // imprimir con multiples firmas mediante declararMetodo
        registrarFuncionBuiltIn("imprimir", new ArrayList<Tipo>(), null);
        registrarFuncionBuiltIn("imprimir", construirListaTipos("entero"), null);
        registrarFuncionBuiltIn("imprimir", construirListaTipos("flotante"), null);
        registrarFuncionBuiltIn("imprimir", construirListaTipos("caracter"), null);
        registrarFuncionBuiltIn("imprimir", construirListaTipos("booleano"), null);
        registrarFuncionBuiltIn("imprimir", construirListaTipos("cadena"), null);

        // leer sin argumentos devuelve cadena
        Tipo tipoCadena = this.contexto.tipoPrimitivo("cadena");
        registrarFuncionBuiltIn("leer", new ArrayList<Tipo>(), tipoCadena);

    }

    // declarar una funcion built-in en el ambito global usando declararMetodo
    // declararMetodo deja varias con el mismo nombre
    private void registrarFuncionBuiltIn(String nombre, List<Tipo> tiposParams, Tipo tipoRetorno) {

        // construir el simbolo de la funcion
        Simbolo funcion = new Simbolo(nombre, CategoriaSimbolo.FUNCION, tipoRetorno);
        funcion.setFila(0);
        funcion.setColumna(0);
        funcion.setTiposParametros(tiposParams);
        funcion.setAmbito(this.contexto.ambitoActual().obtenerAmbito());

        // declarar la funcion como metodo en el ambito
        this.contexto.ambitoActual().declararMetodo(funcion);

    }

    // construir una lista con un unico tipo por nombre
    private List<Tipo> construirListaTipos(String nombreTipo) {

        // crear la lista de salida
        List<Tipo> lista = new ArrayList<>();

        // resolver el tipo indicado
        Tipo tipo = this.contexto.tipoPorNombre(nombreTipo);

        // agregar el tipo si existe
        if (tipo != null) {
            lista.add(tipo);
        }

        return lista;
    }

    // ==================== REGISTRO DE ESTRUCTURAS ====================

    // registrar una estructura como tipo en el ambito actual
    private void registrarEstructura(DefEstructura estructura) {

        // obtener el nombre de la estructura
        String nombre = estructura.getNombre();

        // verificar si el tipo ya esta definido
        if (this.contexto.tipoPorNombre(nombre) != null) {
            this.contexto.agregarError(estructura, "el tipo '" + nombre + "' ya esta definido");

            return;
        }

        // crear el tipo no primitivo
        Tipo tipo = new Tipo(nombre, false);

        // usar declararTipo del ambito semantico
        this.contexto.ambitoActual().declararTipo(tipo);

        // registrar cada atributo como campo del tipo
        for (NodoASTY attr : estructura.getAtributos()) {

            // procesar atributo simple
            if (attr instanceof AtributoSimple) {
                AtributoSimple simple = (AtributoSimple) attr;

                // resolver el tipo del campo
                Tipo tipoCampo = resolverTipoDato(simple.getTipo());

                // reportar error si el tipo no existe
                if (tipoCampo == null) {
                    this.contexto.agregarError(simple, "tipo de atributo no definido");

                    continue;
                }

                // construir el simbolo del campo
                Simbolo campo = new Simbolo(simple.getNombre(), CategoriaSimbolo.CAMPO_ESTRUCTURA, tipoCampo);

                campo.setFila(simple.getLinea());
                campo.setColumna(simple.getColumna());

                tipo.agregarCampo(campo);

            } else if (attr instanceof AtributoArray) {

                // procesar atributo array
                AtributoArray array = (AtributoArray) attr;

                // resolver el tipo base del arreglo
                Tipo tipoBase = resolverTipoDato(array.getTipo());

                // reportar error si el tipo no existe
                if (tipoBase == null) {
                    this.contexto.agregarError(array, "tipo de atributo no definido");

                    continue;
                }

                // construir el tipo arreglo
                Tipo tipoArray = this.contexto.tipoArray(tipoBase);

                // construir el simbolo del campo
                Simbolo campo = new Simbolo(array.getNombre(), CategoriaSimbolo.CAMPO_ESTRUCTURA, tipoArray);

                campo.setFila(array.getLinea());
                campo.setColumna(array.getColumna());
                campo.setTamano(array.getTamano());

                tipo.agregarCampo(campo);
            }
        }
    }

    // ==================== REGISTRO DE FUNCIONES ====================

    // registrar una funcion sin retorno en el ambito global
    private void registrarFuncionSinRetorno(DefFuncionSinRetorno funcion) {

        // obtener el nombre de la funcion
        String nombre = funcion.getNombre();

        // extraer los tipos de los parametros
        List<Tipo> tiposParametros = extraerTiposParametros(funcion.getParametros());

        // verificar si ya existe una firma igual
        if (existeFuncionConFirma(nombre, tiposParametros)) {
            this.contexto.agregarError(funcion, "ya existe una funcion '" + nombre + "' con la misma firma");

            return;
        }

        // construir el simbolo de la funcion
        Simbolo simbolo = new Simbolo(nombre, CategoriaSimbolo.FUNCION, null);

        simbolo.setFila(funcion.getLinea());
        simbolo.setColumna(funcion.getColumna());
        simbolo.setTiposParametros(tiposParametros);
        simbolo.setAmbito(this.contexto.ambitoActual().obtenerAmbito());

        // declarar la funcion como metodo en el ambito
        this.contexto.ambitoActual().declararMetodo(simbolo);
    }

    // registrar una funcion con retorno en el ambito global
    private void registrarFuncionConRetorno(DefFuncionConRetorno funcion) {

        // obtener el nombre de la funcion
        String nombre = funcion.getNombre();

        // resolver el tipo de retorno
        Tipo tipoRetorno = resolverTipoDato(funcion.getTipoRetorno());

        // reportar error si el tipo no existe
        if (tipoRetorno == null) {
            this.contexto.agregarError(funcion, "tipo de retorno no definido");

            return;
        }

        // extraer los tipos de los parametros
        List<Tipo> tiposParametros = extraerTiposParametros(funcion.getParametros());

        // verificar si ya existe una firma igualllllll
        if (existeFuncionConFirma(nombre, tiposParametros)) {
            this.contexto.agregarError(funcion, "ya existe una funcion '" + nombre + "' con la misma firma");

            return;
        }

        // construir el simbolo de la funcion
        Simbolo simbolo = new Simbolo(nombre, CategoriaSimbolo.FUNCION, tipoRetorno);

        simbolo.setFila(funcion.getLinea());
        simbolo.setColumna(funcion.getColumna());
        simbolo.setTiposParametros(tiposParametros);
        simbolo.setAmbito(this.contexto.ambitoActual().obtenerAmbito());


        // declarar la funcion como metodo en el ambito
        this.contexto.ambitoActual().declararMetodo(simbolo);
    }

    // verificar si ya existe una funcion con la misma firma exacta en el ambito actual
    private boolean existeFuncionConFirma(String nombre, List<Tipo> tiposParametros) {

        // obtener los metodos locales con ese nombre
        List<Simbolo> existentes = this.contexto.ambitoActual().obtenerMetodosLocal(nombre);

        // comparar cada firma existente
        for (int i = 0; i < existentes.size(); i++) {
            Simbolo s = existentes.get(i);

            // comparar la firma del candidato
            if (mismaFirma(s.getTiposParametros(), tiposParametros)) {
                return true;
            }
        }

        return false;
    }

    // verificar si dos firmas son identicas en tipos y dimensiones
    private boolean mismaFirma(List<Tipo> a, List<Tipo> b) {

        // aceptar si ambas son nulas
        if (a == null && b == null) {
            return true;
        }

        // rechazar si solo una es nula
        if (a == null || b == null) {
            return false;
        }

        // comparar la cantidad de parametros
        if (a.size() != b.size()) {
            return false;
        }

        // comparar cada par de tipos
        for (int i = 0; i < a.size(); i++) {
            Tipo ta = a.get(i);
            Tipo tb = b.get(i);

            // rechazar si alguno es nulo
            if (ta == null || tb == null) {
                return false;
            }

            // comparar el nombre
            if (!ta.getNombre().equals(tb.getNombre())) {
                return false;
            }

            // comparar la dimension
            if (ta.getDimension() != tb.getDimension()) {
                return false;
            }

        }

        return true;
    }

    // verificar si los tipos de los parametros son compatibles con los argumentos
    private boolean firmaCompatible(List<Tipo> parametros, List<Tipo> argumentos) {

        // aceptar si ambas listas son nulas
        if (parametros == null && argumentos == null) {
            return true;
        }

        // aceptar solo si una es nula y la otra esta vacia
        if (parametros == null || argumentos == null) {
            return parametros == null && argumentos.isEmpty();
        }

        // comparar la cantidad de elementos
        if (parametros.size() != argumentos.size()) {
            return false;
        }

        // comparar la compatibilidad de cada par
        for (int i = 0; i < parametros.size(); i++) {

            if (!this.contexto.esCompatible(parametros.get(i), argumentos.get(i))) {
                return false;
            }

        }

        return true;
    }

    // extraer los tipos de los parametros de una lista
    private List<Tipo> extraerTiposParametros(NodoASTY parametrosNodo) {

        // crear la lista de salida
        List<Tipo> tipos = new ArrayList<>();

        // verificar si el nodo es nulo
        if (parametrosNodo == null) {
            return tipos;
        }

        // verificar si el nodo no es Parametros
        if (!(parametrosNodo instanceof Parametros)) {
            return tipos;
        }

        // convertir el nodo a Parametros
        Parametros parametros = (Parametros) parametrosNodo;

        // recorrer cada parametro
        for (NodoASTY p : parametros.getParametros()) {

            if (p instanceof ParamSimple) {

                // procesar parametro simple
                ParamSimple simple = (ParamSimple) p;
                Tipo tipo = resolverTipoDato(simple.getTipo());
                tipos.add(tipo);

            } else if (p instanceof ParamArray) {

                // procesar parametro array
                ParamArray array = (ParamArray) p;
                Tipo base = resolverTipoDato(array.getTipo());

                // agregar el arreglo si el tipo base existe
                if (base != null) {
                    tipos.add(this.contexto.tipoArray(base));
                } else {
                    tipos.add(null);
                }

            } else if (p instanceof ParamEstructura) {

                // procesar parametro estructura
                ParamEstructura estructura = (ParamEstructura) p;
                Tipo tipo = this.contexto.tipoPorNombre(estructura.getTipoEstructura());
                tipos.add(tipo);
            }
        }

        return tipos;
    }

    // ==================== FLUJO DE CONTROL ====================

    // analizar una lista de instrucciones y devolver el flujo acumulado
    private FlujoControl analizarInstrucciones(List<NodoASTY> instrucciones) {

        // crear el flujo inicial
        FlujoControl flujo = new FlujoControl();

        // verificar si la lista es nula
        if (instrucciones == null) {
            return flujo;
        }

        boolean primeraInalcanzable = false;

        // recorrer cada instruccion
        for (NodoASTY inst : instrucciones) {

            // reportar codigo inalcanzable una sola vez
            if (!flujo.puedeContinuar()) {

                if (!primeraInalcanzable) {
                    this.contexto.agregarError(inst, "codigo inalcanzable");
                    primeraInalcanzable = true;
                }

                continue;
            }

            // omitir instrucciones nulas
            if (inst == null) {
                continue;
            }

            // entrar en la instruccion
            Object resultado = inst.accept(this);

            // actualizar el flujo si el resultado lo permite
            if (resultado instanceof FlujoControl) {
                flujo = (FlujoControl) resultado;
            }

        }

        return flujo;
    }

    // visitar un bloque aislado y devolver su flujo
    private FlujoControl visitarBloqueAislado(NodoASTY nodo) {

        // verificar si el nodo es nulo
        if (nodo == null) {
            return new FlujoControl();
        }

        // entrar en el nodo
        Object resultado = nodo.accept(this);

        // devolver el flujo si el resultado lo permite
        if (resultado instanceof FlujoControl) {
            return (FlujoControl) resultado;
        }

        return new FlujoControl();
    }

    // validar que una condicion sea booleana
    private void validarCondicionBooleana(NodoASTY cond) {

        // verificar si la condicion es nula
        if (cond == null) {
            return;
        }

        // entrar en la condicion
        Object tipoObj = cond.accept(this);

        // verificar si el resultado es un Tipo
        if (tipoObj instanceof Tipo) {
            Tipo tipoCond = (Tipo) tipoObj;

            // reportar error si no es booleano
            if (!this.contexto.esBooleano(tipoCond)) {
                this.contexto.agregarError(cond, "la condicion debe ser booleana");
            }

        }
    }

    // ==================== PROGRAMA ====================

    @Override
    public Object visitarPrograma(Programa nodo) {

        // registrar funciones built-in en el ambito global
        registrarFuncionesBuiltIn();

        // primera pasada: registrar structs globales
        if (nodo.getSeccionEstructuras() != null && nodo.getSeccionEstructuras() instanceof SeccionEstructuras) {
            SeccionEstructuras seccion = (SeccionEstructuras) nodo.getSeccionEstructuras();

            // registrar cada estructura declarada
            for (NodoASTY est : seccion.getEstructuras()) {
                if (est instanceof DefEstructura) {
                    registrarEstructura((DefEstructura) est);
                }
            }
        }

        // primera pasada: registrar firmas de funcioness
        if (nodo.getSeccionFunciones() != null && nodo.getSeccionFunciones() instanceof SeccionFunciones) {
            SeccionFunciones seccion = (SeccionFunciones) nodo.getSeccionFunciones();

            // registrar la firma de cada funcion
            for (NodoASTY fun : seccion.getFunciones()) {

                if (fun instanceof DefFuncionSinRetorno) {
                    registrarFuncionSinRetorno((DefFuncionSinRetorno) fun);

                } else if (fun instanceof DefFuncionConRetorno) {
                    registrarFuncionConRetorno((DefFuncionConRetorno) fun);
                }
            }
        }




        // segunda pasada: analizar cuerpos de funciones
        if (nodo.getSeccionFunciones() != null && nodo.getSeccionFunciones() instanceof SeccionFunciones) {
            SeccionFunciones seccion = (SeccionFunciones) nodo.getSeccionFunciones();

            // visitar cada funcion
            for (NodoASTY fun : seccion.getFunciones()) {
                if (fun != null) {
                    fun.accept(this);
                }
            }
        }

        return new FlujoControl();
    }

    @Override
    public Object visitarSeccionEstructuras(SeccionEstructuras nodo) {
        // las estructuras ya fueron procesadas en la primera pasada :D
        return new FlujoControl();
    }

    @Override
    public Object visitarSeccionFunciones(SeccionFunciones nodo) {
        // las funciones ya se vieron en la primera pasada
        return new FlujoControl();
    }

    @Override
    public Object visitarTipoDato(TipoDato nodo) {
        // resolver el tipo por su nombre
        return this.contexto.tipoPorNombre(nodo.getNombre());
    }

    @Override
    public Object visitarParametros(Parametros nodo) {
        return null;
    }

    @Override
    public Object visitarCuerpoFuncion(CuerpoFuncion nodo) {
        // analizar las instrucciones del cuerpo
        return analizarInstrucciones(nodo.getInstrucciones());
    }

    @Override
    public Object visitarBloque(Bloque nodo) {
        // analizar las instrucciones del bloque
        return analizarInstrucciones(nodo.getInstrucciones());
    }

    @Override
    public Object visitarListaExpresiones(ListaExpresiones nodo) {

        // recorrer cada expresion
        for (NodoASTY expr : nodo.getExpresiones()) {
            if (expr != null) {
                expr.accept(this);
            }
        }

        return null;
    }

    @Override
    public Object visitarValorPrimitivo(ValorPrimitivo nodo) {

        // obtener el tipo primitivo
        TipoPrimitivo tp = nodo.getTipo();

        // verificar si el tipo es nulo
        if (tp == null) {
            return null;
        }

        // mapear cada tipo primitivo
        if (tp == TipoPrimitivo.ENTERO) {
            return this.contexto.tipoPrimitivo("entero");
        }
        if (tp == TipoPrimitivo.DECIMAL) {
            return this.contexto.tipoPrimitivo("flotante");
        }
        if (tp == TipoPrimitivo.CADENA) {
            return this.contexto.tipoPrimitivo("cadena");
        }
        if (tp == TipoPrimitivo.CARACTER) {
            return this.contexto.tipoPrimitivo("caracter");
        }
        if (tp == TipoPrimitivo.BOOLEANO) {
            return this.contexto.tipoPrimitivo("booleano");
        }
        if (tp == TipoPrimitivo.IDENTIFICADOR) {

            // resolver el simbolo del identificador
            Simbolo simbolo = this.contexto.resolver(nodo.getValor(), nodo);

            if (simbolo != null) {
                return simbolo.getTipo();
            }

            return null;
        }

        return null;
    }

    // ==================== ESTRUCTURAS ====================

    @Override
    public Object visitarDefEstructura(DefEstructura nodo) {
        // la estructura ya fue registrada en la primera pasada
        return null;
    }

    @Override
    public Object visitarAtributoSimple(AtributoSimple nodo) {
        return null;
    }

    @Override
    public Object visitarAtributoArray(AtributoArray nodo) {
        return null;
    }

    // ==================== FUNCIONES ====================

    @Override
    public Object visitarDefFuncionSinRetorno(DefFuncionSinRetorno nodo) {

        // entrar al ambito de la funcion
        this.contexto.entrarAmbito("funcion:" + nodo.getNombre());
        this.enFuncionConRetorno = false;
        this.tipoRetornoActual = null;

        // registrar los parametros de la funcion
        registrarParametros(nodo.getParametros());

        // analizar las instrucciones del cuerpo
        analizarInstrucciones(obtenerInstruccionesDe(nodo.getCuerpo()));

        // restaurar el estado anterior :D
        this.enFuncionConRetorno = false;
        this.tipoRetornoActual = null;
        this.contexto.salirAmbito();

        return null;
    }

    @Override
    public Object visitarDefFuncionConRetorno(DefFuncionConRetorno nodo) {

        // resolver el tipo de retorno
        Tipo tipoRetorno = resolverTipoDato(nodo.getTipoRetorno());

        // entrar al ambito de la funcion
        this.contexto.entrarAmbito("funcion:" + nodo.getNombre());
        this.enFuncionConRetorno = true;
        this.tipoRetornoActual = tipoRetorno;

        // registrar los parametros de la funcion
        registrarParametros(nodo.getParametros());

        // analizar las instrucciones del cuerpo
        FlujoControl flujo = analizarInstrucciones(obtenerInstruccionesDe(nodo.getCuerpo()));

        // verificar que todos los caminos retornen
        if (flujo.puedeContinuar()) {
            this.contexto.agregarError(nodo, "la funcion '" + nodo.getNombre() + "' debe retornar un valor en todos los caminos");
        }

        // restaurar el estado anterior
        this.enFuncionConRetorno = false;
        this.tipoRetornoActual = null;
        this.contexto.salirAmbito();

        return null;
    }

    // extraer las instrucciones de un nodo CuerpoFuncion
    private List<NodoASTY> obtenerInstruccionesDe(NodoASTY cuerpo) {

        // verificar si el cuerpo es CuerpoFuncion
        if (cuerpo instanceof CuerpoFuncion) {
            return ((CuerpoFuncion) cuerpo).getInstrucciones();
        }

        return new ArrayList<>();
    }

    // registrar los parametros de una funcion en el ambito actual
    private void registrarParametros(NodoASTY parametrosNodo) {

        // verificar si el nodo es Parametros
        if (parametrosNodo == null || !(parametrosNodo instanceof Parametros)) {
            return;
        }

        // convertir el nodo a Parametros
        Parametros parametros = (Parametros) parametrosNodo;

        // recorrer cada parametro
        for (NodoASTY p : parametros.getParametros()) {
            if (p instanceof ParamSimple) {

                // procesar parametro simple
                ParamSimple simple = (ParamSimple) p;
                Tipo tipo = resolverTipoDato(simple.getTipo());

                // reportar error si el tipo no existe
                if (tipo == null) {
                    this.contexto.agregarError(simple, "tipo de parametro no definido");

                    continue;
                }

                declararVariableSeguro(simple, simple.getNombre(), tipo, CategoriaSimbolo.PARAMETRO, null);


            } else if (p instanceof ParamArray) {

                // procesar parametro array
                ParamArray array = (ParamArray) p;
                Tipo base = resolverTipoDato(array.getTipo());

                // reportar error si el tipo base no existe
                if (base == null) {
                    this.contexto.agregarError(array, "tipo de parametro no definido");

                    continue;
                }

                // construir el tipo arreglo
                Tipo tipoArray = this.contexto.tipoArray(base);
                declararVariableSeguro(array, array.getNombre(), tipoArray, CategoriaSimbolo.PARAMETRO, null);

            } else if (p instanceof ParamEstructura) {

                // procesar parametro estructura
                ParamEstructura estructura = (ParamEstructura) p;
                Tipo tipo = this.contexto.tipoPorNombre(estructura.getTipoEstructura());

                // reportar error si el tipo no existe
                if (tipo == null) {
                    this.contexto.agregarError(estructura, "tipo de estructura no definido");

                    continue;
                }

                declararVariableSeguro(estructura, estructura.getNombre(), tipo, CategoriaSimbolo.PARAMETRO, null);
            }
        }
    }

    @Override
    public Object visitarParamSimple(ParamSimple nodo) {
        return null;
    }

    @Override
    public Object visitarParamArray(ParamArray nodo) {
        return null;
    }

    @Override
    public Object visitarParamEstructura(ParamEstructura nodo) {
        return null;
    }

    // ==================== INSTRUCCIONES ====================

    @Override
    public Object visitarStmtDeclaracion(StmtDeclaracion nodo) {

        // visitar la declaracion si existe
        if (nodo.getDeclaracion() != null) {
            nodo.getDeclaracion().accept(this);
        }

        return new FlujoControl();
    }

    @Override
    public Object visitarStmtAsignacion(StmtAsignacion nodo) {

        // visitar la asignacion si existe
        if (nodo.getAsignacion() != null) {
            nodo.getAsignacion().accept(this);
        }

        return new FlujoControl();
    }

    @Override
    public Object visitarStmtEstructuraLocal(StmtEstructuraLocal nodo) {

        // registrar la estructura local si existe
        if (nodo.getEstructura() instanceof DefEstructura) {
            registrarEstructura((DefEstructura) nodo.getEstructura());
        }

        return new FlujoControl();
    }

    @Override
    public Object visitarStmtCondicional(StmtCondicional nodo) {

        // visitar el condicional si existe
        if (nodo.getCondicional() != null) {
            Object resultado = nodo.getCondicional().accept(this);

            // devolver el flujo si el resultado lo permite
            if (resultado instanceof FlujoControl) {
                return resultado;
            }
        }

        return new FlujoControl();
    }

    @Override
    public Object visitarStmtSeleccion(StmtSeleccion nodo) {

        // visitar la seleccion si existe
        if (nodo.getSeleccion() != null) {
            Object resultado = nodo.getSeleccion().accept(this);

            // devolver el flujo si el resultado lo permite
            if (resultado instanceof FlujoControl) {
                return resultado;
            }
        }

        return new FlujoControl();
    }

    @Override
    public Object visitarStmtCiclo(StmtCiclo nodo) {

        // visitar el ciclo si existe
        if (nodo.getCiclo() != null) {
            nodo.getCiclo().accept(this);
        }

        return new FlujoControl();
    }

    @Override
    public Object visitarStmtRetorno(StmtRetorno nodo) {

        // verificar si hay expresion de retorno
        if (nodo.getExpresion() != null) {

            Object tipoObj = nodo.getExpresion().accept(this);
            Tipo tipoExp = extraerTipoDeExpresion(tipoObj);

            // reportar error si la funcion no retorna
            if (!this.enFuncionConRetorno) {
                this.contexto.agregarError(nodo, "no se puede retornar un valor desde una funcion sin retorno");

            } else if (tipoExp != null && this.tipoRetornoActual != null) {

                // verificar la compatibilidad del tipo
                if (!this.contexto.esCompatible(this.tipoRetornoActual, tipoExp)) {
                    this.contexto.agregarError(nodo, "tipo de retorno incompatible, se esperaba '" + this.tipoRetornoActual.getNombre() + "' pero se obtuvo '" + tipoExp.getNombre() + "'");
                }

            }
        } else {

            // reportar error si falta el valor de retorno
            if (this.enFuncionConRetorno) {
                this.contexto.agregarError(nodo, "la funcion debe retornar un valor");
            }

        }

        // marcar el flujo como terminado
        FlujoControl flujo = new FlujoControl();
        flujo.marcarRetorno();

        return flujo;
    }

    @Override
    public Object visitarStmtContinuar(StmtContinuar nodo) {

        // verificar si esta dentro de un ciclo
        if (!this.contexto.estaDentroDeCiclo()) {
            this.contexto.agregarError(nodo, "'continuar' solo puede usarse dentro de un ciclo");
        }

        // marcar el flujo como terminado
        FlujoControl flujo = new FlujoControl();
        flujo.marcarRetorno();

        return flujo;
    }

    @Override
    public Object visitarStmtRomper(StmtRomper nodo) {

        // verificar si esta dentro de un ciclo o una seleccion
        if (!this.contexto.estaDentroDeCiclo() && !this.contexto.estaDentroDeSeleccion()) {
            this.contexto.agregarError(nodo, "'romper' solo puede usarse dentro de un ciclo o una seleccion");
        }

        // marcar el flujo como terminado
        FlujoControl flujo = new FlujoControl();
        flujo.marcarRetorno();

        return flujo;
    }

    @Override
    public Object visitarStmtExpresion(StmtExpresion nodo) {

        // visitar la expresion si existe
        if (nodo.getExpresion() != null) {
            nodo.getExpresion().accept(this);
        }

        return new FlujoControl();
    }

    // ==================== DECLARACIONES DE VARIABLES ====================

    @Override
    public Object visitarDeclConTipoYValor(DeclConTipoYValor nodo) {

        // resolver el tipo de la declaracion
        Tipo tipo = resolverTipoDato(nodo.getTipo());

        // reportar error si el tipo no existe
        if (tipo == null) {
            this.contexto.agregarError(nodo, "tipo no definido");
            return null;
        }

        // declarar la variable de forma segura
        if (!declararVariableSeguro(nodo, nodo.getNombre(), tipo, CategoriaSimbolo.VARIABLE, null)) {
            return null;
        }

        // visitar el valor si existe
        if (nodo.getValor() != null) {
            Object tipoObj = nodo.getValor().accept(this);
            Tipo tipoExp = extraerTipoDeExpresion(tipoObj);

            // verificar la compatibilidad del tipo
            if (tipoExp != null && !this.contexto.esCompatible(tipo, tipoExp)) {
                this.contexto.agregarError(nodo.getValor(), "tipo incompatible en inicializacion, se esperaba '" + tipo.getNombre() + "' pero se obtuvo '" + tipoExp.getNombre() + "'");
            }
        }

        return null;
    }

    @Override
    public Object visitarDeclArraySinValores(DeclArraySinValores nodo) {

        // resolver el tipo base
        Tipo tipoBase = resolverTipoDato(nodo.getTipo());

        // reportar error si el tipo no existe
        if (tipoBase == null) {
            this.contexto.agregarError(nodo, "tipo no definido");

            return null;
        }

        // construir el tipo arreglo
        Tipo tipoArray = this.contexto.tipoArray(tipoBase);
        Integer tamano = null;

        // visitar el tamano si existe
        if (nodo.getTamano() != null) {

            nodo.getTamano().accept(this);
            tamano = extraerEnteroConstante(nodo.getTamano());

            // reportar error si no es constante
            if (tamano == null) {
                this.contexto.agregarError(nodo.getTamano(), "el tamano del arreglo debe ser una constante entera");
            } else if (tamano <= 0) {
                this.contexto.agregarError(nodo.getTamano(), "el tamano del arreglo debe ser positivo");
            }
        }

        // declarar la variable de forma segura
        declararVariableSeguro(nodo, nodo.getNombre(), tipoArray, CategoriaSimbolo.ARREGLO, tamano);

        return null;
    }

    @Override
    public Object visitarDeclArrayConValores(DeclArrayConValores nodo) {

        // resolver el tipo base
        Tipo tipoBase = resolverTipoDato(nodo.getTipo());

        // reportar error si el tipo no existe :'c
        if (tipoBase == null) {
            this.contexto.agregarError(nodo, "tipo no definido");

            return null;
        }

        // construir el tipo arreglo
        Tipo tipoArray = this.contexto.tipoArray(tipoBase);
        Integer tamano = null;

        // visitar el tamano si existe
        if (nodo.getTamano() != null) {
            nodo.getTamano().accept(this);
            tamano = extraerEnteroConstante(nodo.getTamano());

            // reportar error si no es constante
            if (tamano == null) {
                this.contexto.agregarError(nodo.getTamano(), "el tamano del arreglo debe ser una constante entera");
            } else if (tamano <= 0) {
                this.contexto.agregarError(nodo.getTamano(), "el tamano del arreglo debe ser positivo");
            }

        }

        // declarar la variable de forma segura
        declararVariableSeguro(nodo, nodo.getNombre(), tipoArray, CategoriaSimbolo.ARREGLO, tamano);

        // verificar la lista de valores
        if (nodo.getListaValores() != null) {
            Object listaObj = nodo.getListaValores();

            // verificar si la lista es de expresiones
            if (listaObj instanceof ListaExpresiones) {
                ListaExpresiones lista = (ListaExpresiones) listaObj;

                // verificar la cantidad de valores
                if (tamano != null && lista.getExpresiones().size() != tamano) {
                    this.contexto.agregarError(nodo, "la cantidad de valores (" + lista.getExpresiones().size() + ") no coincide con el tamano declarado (" + tamano + ")");
                }

                // validar cada valor
                for (NodoASTY expr : lista.getExpresiones()) {

                    // omitir expresiones nulas
                    if (expr == null) {
                        continue;
                    }

                    Object tipoValorObj = expr.accept(this);
                    Tipo tipoValor = extraerTipoDeExpresion(tipoValorObj);

                    // verificar la compatibilidad del tipo
                    if (tipoValor != null && !this.contexto.esCompatible(tipoBase, tipoValor)) {
                        this.contexto.agregarError(expr, "valor incompatible con el tipo del arreglo");
                    }
                }


            }
        }

        return null;
    }

    @Override
    public Object visitarDeclMatriz(DeclMatriz nodo) {
        // resolver el tipo base
        Tipo tipoBase = resolverTipoDato(nodo.getTipo());

        // reportar error si el tipo no existe
        if (tipoBase == null) {
            this.contexto.agregarError(nodo, "tipo no definido");

            return null;
        }


        // construir el tipo matriz de dos dimensiones
        Tipo tipoMatriz = new Tipo(tipoBase.getNombre(), tipoBase.esPrimitivo(), 2, tipoBase, new ArrayList<>(), null);

        // visitar el tamano de filas si existe
        if (nodo.getTamanoFilas() != null) {
            nodo.getTamanoFilas().accept(this);
        }

        // visitar el tamano de columnas si existe
        if (nodo.getTamanoColumnas() != null) {
            nodo.getTamanoColumnas().accept(this);
        }

        // declarar la variable de forma segura
        declararVariableSeguro(nodo, nodo.getNombre(), tipoMatriz, CategoriaSimbolo.ARREGLO, null);

        return null;
    }

    @Override
    public Object visitarDeclMatrizConValores(DeclMatrizConValores nodo) {
        // resolver el tipo base
        Tipo tipoBase = resolverTipoDato(nodo.getTipo());

        // reportar error si el tipo no existe
        if (tipoBase == null) {
            this.contexto.agregarError(nodo, "tipo no definido");

            return null;
        }

        // construir el tipo matriz de dos dimensiones
        Tipo tipoMatriz = new Tipo(tipoBase.getNombre(), tipoBase.esPrimitivo(), 2, tipoBase, new ArrayList<>(), null);

        // visitar el tamano de filas si existe
        if (nodo.getTamanoFilas() != null) {
            nodo.getTamanoFilas().accept(this);
        }

        // visitar el tamano de columnas si existe
        if (nodo.getTamanoColumnas() != null) {
            nodo.getTamanoColumnas().accept(this);
        }

        // declarar la variable de forma segura
        declararVariableSeguro(nodo, nodo.getNombre(), tipoMatriz, CategoriaSimbolo.ARREGLO, null);

        // validar cada fila con sus valores
        if (nodo.getFilas() != null) {
            for (int i = 0; i < nodo.getFilas().size(); i++) {
                List<NodoASTY> fila = nodo.getFilas().get(i);
                // omitir filas nulas
                if (fila == null) {
                    continue;
                }
                // validar cada valor de la fila
                for (int j = 0; j < fila.size(); j++) {
                    NodoASTY expr = fila.get(j);
                    // omitir expresiones nulas
                    if (expr == null) {
                        continue;
                    }
                    Object tipoValorObj = expr.accept(this);
                    Tipo tipoValor = extraerTipoDeExpresion(tipoValorObj);
                    // verificar la compatibilidad del tipo
                    if (tipoValor != null && !this.contexto.esCompatible(tipoBase, tipoValor)) {
                        this.contexto.agregarError(expr, "valor incompatible con el tipo de la matriz");
                    }
                }
            }
        }

        return null;
    }

    // ==================== ASIGNACION ====================

    @Override
    public Object visitarAsignacion(Asignacion nodo) {

        // visitar la variable destino
        Object tipoVarObj = nodo.getVariable().accept(this);
        Tipo tipoVar = extraerTipoDeExpresion(tipoVarObj);

        // reportar error si la variable no esta declarada
        if (tipoVar == null) {
            this.contexto.agregarError(nodo, "variable no declarada o no accesible");

            return null;
        }

        // visitar el valor de la asignacion
        Object tipoValorObj = nodo.getValor().accept(this);
        Tipo tipoValor = extraerTipoDeExpresion(tipoValorObj);

        // verificar la compatibilidad del tipo
        if (tipoValor != null && !this.contexto.esCompatible(tipoVar, tipoValor)) {
            this.contexto.agregarError(nodo, "tipo incompatible en asignacion, se esperaba '" + tipoVar.getNombre() + "' pero se obtuvo '" + tipoValor.getNombre() + "'");
        }

        return null;
    }

    // ==================== VARIABLES ASIGNABLES ====================

    @Override
    public Object visitarVarSimple(VarSimple nodo) {

        // resolver el simbolo de la variable
        Simbolo simbolo = this.contexto.resolver(nodo.getNombre(), nodo);

        // devolver el tipo si existe
        if (simbolo != null) {
            return simbolo.getTipo();
        }

        return null;
    }

    @Override
    public Object visitarVarArray(VarArray nodo) {

        // visitar la base del acceso
        Object tipoBaseObj = nodo.getBase().accept(this);
        Tipo tipoBase = extraerTipoDeExpresion(tipoBaseObj);

        // verificar si el tipo base es nulo
        if (tipoBase == null) {
            return null;
        }

        // verificar si la base no es un arreglo
        if (tipoBase.getDimension() <= 0) {
            this.contexto.agregarError(nodo, "se esperaba un arreglo");
            return null;
        }

        // visitar el indice del acceso
        Object tipoIndiceObj = nodo.getIndice().accept(this);
        Tipo tipoIndice = extraerTipoDeExpresion(tipoIndiceObj);

        // verificar si el indice es entero
        if (tipoIndice != null && !this.contexto.esEntero(tipoIndice)) {
            this.contexto.agregarError(nodo.getIndice(), "el indice debe ser entero");
        }

        // devolver el tipo elemento
        return this.contexto.tipoElemento(tipoBase);
    }

    @Override
    public Object visitarVarMiembro(VarMiembro nodo) {

        // visitar la base del acceso
        Object tipoBaseObj = nodo.getBase().accept(this);
        Tipo tipoBase = extraerTipoDeExpresion(tipoBaseObj);

        // verificar si el tipo base es nulo
        if (tipoBase == null) {
            return null;
        }

        // buscar el campo en el tipo
        Simbolo campo = tipoBase.buscarCampo(nodo.getMiembro());

        // reportar error si el campo no existe
        if (campo == null) {
            this.contexto.agregarError(nodo, "el campo '" + nodo.getMiembro() + "' no existe en '" + tipoBase.getNombre() + "'");
            return null;
        }

        return campo.getTipo();
    }

    // ==================== CONDICIONAL ====================

    @Override
    public Object visitarStatementSi(StatementSi nodo) {

        // validar la condicion principal
        validarCondicionBooleana(nodo.getCondicionPrincipal());

        // visitar el bloque principal
        this.contexto.entrarAmbito("bloque-si");
        FlujoControl flujoPrincipal = visitarBloqueAislado(nodo.getBloquePrincipal());
        this.contexto.salirAmbito();

        boolean todasTerminan = !flujoPrincipal.puedeContinuar();

        // recorrer cada rama sino si
        for (int i = 0; i < nodo.getCondicionesSino().size(); i++) {
            NodoASTY cond = nodo.getCondicionesSino().get(i);
            NodoASTY bloque = nodo.getBloquesSino().get(i);

            validarCondicionBooleana(cond);

            this.contexto.entrarAmbito("bloque-sino");
            FlujoControl flujoRama = visitarBloqueAislado(bloque);
            this.contexto.salirAmbito();

            // verificar si la rama puede continuar
            if (flujoRama.puedeContinuar()) {
                todasTerminan = false;
            }

        }

        boolean hayContrario = false;

        // visitar el bloque contrario si existe
        if (nodo.getBloqueContrario() != null) {
            hayContrario = true;
            this.contexto.entrarAmbito("bloque-contrario");

            FlujoControl flujoContrario = visitarBloqueAislado(nodo.getBloqueContrario());
            this.contexto.salirAmbito();

            // verificar si el bloque puede continuar
            if (flujoContrario.puedeContinuar()) {
                todasTerminan = false;
            }
        }

        // devolver el flujo correspondiente
        FlujoControl resultado = new FlujoControl();

        if (hayContrario && todasTerminan) {
            resultado.marcarRetorno();
        } else {
            resultado.marcarContinuacion();
        }

        return resultado;
    }

    // ==================== SELECCION ====================

    @Override
    public Object visitarStatementElegir(StatementElegir nodo) {

        // visitar la expresion de seleccion
        Object tipoExpObj = nodo.getExpresion().accept(this);
        Tipo tipoExp = extraerTipoDeExpresion(tipoExpObj);

        // verificar que el tipo sea valido para elegir
        if (tipoExp != null && !this.contexto.esEntero(tipoExp) && !this.contexto.esCaracter(tipoExp) && !this.contexto.esCadena(tipoExp)) {
            this.contexto.agregarError(nodo, "tipo no valido para elegir");
        }

        boolean todosTerminan = true;

        // entrar a la seleccion para aceptar romper en sus casos
        this.contexto.entrarSeleccion();

        // recorrer cada caso de la seleccion
        for (NodoASTY caso : nodo.getCasos()) {
            this.contexto.entrarAmbito("caso-elegir");

            FlujoControl flujoCaso = visitarBloqueAislado(caso);
            this.contexto.salirAmbito();

            // verificar si el caso puede continuar
            // un romper final sale de la seleccion y el flujo sigue despues
            if (flujoCaso.puedeContinuar() || terminaEnRomper(caso)) {
                todosTerminan = false;
            }
        }

        // visitar el caso por defecto si existe
        if (nodo.getCasoDefecto() != null) {
            this.contexto.entrarAmbito("caso-defecto");

            FlujoControl flujoDefecto = visitarBloqueAislado(nodo.getCasoDefecto());
            this.contexto.salirAmbito();

            // verificar si el caso puede continuar
            // un romper final sale de la seleccion y el flujo sigue despues
            if (flujoDefecto.puedeContinuar() || terminaEnRomper(nodo.getCasoDefecto())) {
                todosTerminan = false;
            }

        } else {
            // sin caso por defecto siempre hay una ruta que no termina
            todosTerminan = false;
        }

        // salir de la seleccion antes de devolver el flujo
        this.contexto.salirSeleccion();

        // devolver el flujo correspondiente
        FlujoControl resultado = new FlujoControl();
        if (todosTerminan) {
            resultado.marcarRetorno();
        } else {
            resultado.marcarContinuacion();
        }

        return resultado;
    }

    // verificar si un caso termina con romper y el flujo sigue despues
    private boolean terminaEnRomper(NodoASTY caso) {
        // omitir casos nulos
        if (caso == null) {
            return false;
        }
        // extraer instrucciones segun el tipo de caso
        List<NodoASTY> lista = null;
        if (caso instanceof CasoSeleccion) {
            lista = ((CasoSeleccion) caso).getInstrucciones();
        } else if (caso instanceof CasoDefecto) {
            lista = ((CasoDefecto) caso).getInstrucciones();
        }
        // omitir casos sin instrucciones
        if (lista == null || lista.isEmpty()) {
            return false;
        }
        // revisar la ultima instruccion del caso
        return lista.get(lista.size() - 1) instanceof StmtRomper;
    }

    @Override
    public Object visitarCasoSeleccion(CasoSeleccion nodo) {
        // analizar las instrucciones del caso
        return analizarInstrucciones(nodo.getInstrucciones());
    }

    @Override
    public Object visitarCasoDefecto(CasoDefecto nodo) {
        // analizar las instrucciones del caso por defecto
        return analizarInstrucciones(nodo.getInstrucciones());
    }

    // ==================== CICLOS ====================

    @Override
    public Object visitarCicloPara(CicloPara nodo) {

        // entrar al ciclo y al ambito
        this.contexto.entrarCiclo();
        this.contexto.entrarAmbito("bloque-para");

        // visitar la inicializacion si existe
        if (nodo.getInicializacion() != null) {
            nodo.getInicializacion().accept(this);
        }

        // validar la condicion del ciclo
        validarCondicionBooleana(nodo.getCondicion());

        // visitar el paso si existe
        if (nodo.getPaso() != null) {
            nodo.getPaso().accept(this);
        }

        // visitar el cuerpo del ciclo
        if (nodo.getBloque() != null) {
            nodo.getBloque().accept(this);
        }

        // salir del ambito y del ciclo
        this.contexto.salirAmbito();
        this.contexto.salirCiclo();
        return new FlujoControl();
    }

    @Override
    public Object visitarCicloMientras(CicloMientras nodo) {
        // entrar al ciclo y al ambito
        this.contexto.entrarCiclo();
        this.contexto.entrarAmbito("bloque-mientras");

        // validar la condicion del ciclo
        validarCondicionBooleana(nodo.getCondicion());

        // visitar el cuerpo del ciclo
        if (nodo.getBloque() != null) {
            nodo.getBloque().accept(this);
        }

        // salir del ambito y del ciclo
        this.contexto.salirAmbito();
        this.contexto.salirCiclo();
        return new FlujoControl();
    }

    @Override
    public Object visitarCicloHacer(CicloHacer nodo) {
        // entrar al ciclo y al ambito
        this.contexto.entrarCiclo();
        this.contexto.entrarAmbito("bloque-hacer");

        // visitar el cuerpo del ciclo
        if (nodo.getBloque() != null) {
            nodo.getBloque().accept(this);
        }

        // validar la condicion del ciclo
        validarCondicionBooleana(nodo.getCondicion());

        // salir del ambito y del ciclo
        this.contexto.salirAmbito();
        this.contexto.salirCiclo();

        return new FlujoControl();
    }

    // ==================== INIT Y PASO DEL PARA ====================

    @Override
    public Object visitarInitParaDecl(InitParaDecl nodo) {

        // resolver el tipo de la declaracion
        Tipo tipo = resolverTipoDato(nodo.getTipo());

        // reportar error si el tipo no existe
        if (tipo == null) {
            this.contexto.agregarError(nodo, "tipo no definido");
            return null;
        }

        // declarar la variable de forma segura
        if (!declararVariableSeguro(nodo, nodo.getNombre(), tipo, CategoriaSimbolo.VARIABLE, null)) {
            return null;
        }

        // visitar la expresion de inicializacion si existe
        if (nodo.getExpresion() != null) {
            Object tipoExpObj = nodo.getExpresion().accept(this);
            Tipo tipoExp = extraerTipoDeExpresion(tipoExpObj);

            // verificar la compatibilidad del tipo
            if (tipoExp != null && !this.contexto.esCompatible(tipo, tipoExp)) {
                this.contexto.agregarError(nodo, "tipo incompatible en inicializacion");
            }
        }

        return null;
    }

    @Override
    public Object visitarInitParaAsig(InitParaAsig nodo) {
        // visitar la variable destino
        Object tipoVarObj = nodo.getVariable().accept(this);
        Tipo tipoVar = extraerTipoDeExpresion(tipoVarObj);

        // verificar si la variable existe
        if (tipoVar == null) {
            return null;
        }

        // visitar la expresion de inicializacion si existe
        if (nodo.getExpresion() != null) {
            Object tipoExpObj = nodo.getExpresion().accept(this);
            Tipo tipoExp = extraerTipoDeExpresion(tipoExpObj);

            // verificar la compatibilidad del tipo
            if (tipoExp != null && !this.contexto.esCompatible(tipoVar, tipoExp)) {
                this.contexto.agregarError(nodo, "tipo incompatible en inicializacion");
            }
        }

        return null;
    }

    @Override
    public Object visitarPasoParaExpr(PasoParaExpr nodo) {
        // visitar la expresion del paso si existe
        if (nodo.getExpresion() != null) {
            nodo.getExpresion().accept(this);
        }

        return null;
    }

    @Override
    public Object visitarPasoParaAsig(PasoParaAsig nodo) {
        // visitar la variable destino
        Object tipoVarObj = nodo.getVariable().accept(this);
        Tipo tipoVar = extraerTipoDeExpresion(tipoVarObj);

        // verificar si la variable existe
        if (tipoVar == null) {
            return null;
        }

        // visitar la expresion del paso si existe
        if (nodo.getExpresion() != null) {
            Object tipoExpObj = nodo.getExpresion().accept(this);
            Tipo tipoExp = extraerTipoDeExpresion(tipoExpObj);

            // verificar la compatibilidad del tipo
            if (tipoExp != null && !this.contexto.esCompatible(tipoVar, tipoExp)) {
                this.contexto.agregarError(nodo, "tipo incompatible en paso");
            }

        }

        return null;
    }

    // ==================== EXPRESIONES ====================

    @Override
    public Object visitarExprParentesis(ExprParentesis nodo) {

        // verificar si la expresion es nula
        if (nodo.getExpresion() == null) {
            return null;
        }

        // visitar la expresion interna
        return nodo.getExpresion().accept(this);
    }

    @Override
    public Object visitarExprPrimitivo(ExprPrimitivo nodo) {

        // verificar si el valor es nulo
        if (nodo.getValor() == null) {
            return null;
        }

        // visitar el valor interno
        return nodo.getValor().accept(this);
    }

    @Override
    public Object visitarExprLlamadaFuncion(ExprLlamadaFuncion nodo) {

        // analizar los argumentos primero
        List<Tipo> tiposArgs = new ArrayList<>();

        for (NodoASTY arg : nodo.getArgumentos()) {
            Object tipoObj = arg.accept(this);
            Tipo tipo = extraerTipoDeExpresion(tipoObj);
            tiposArgs.add(tipo);
        }

        // buscar candidatos con ese nombre aqui y arriba
        List<Simbolo> candidatos = this.contexto.ambitoActual().buscarMetodos(nodo.getNombre());

        // reportar error si no hay candidatos
        if (candidatos.isEmpty()) {
            this.contexto.agregarError(nodo, "funcion '" + nodo.getNombre() + "' no definida");

            return null;
        }

        // elegir el primer candidato cuya firma sea compatible
        for (int i = 0; i < candidatos.size(); i++) {

            Simbolo candidato = candidatos.get(i);
            if (firmaCompatible(candidato.getTiposParametros(), tiposArgs)) {
                return candidato.getTipo();
            }

        }

        // reportar error si ningun candidato coincide
        this.contexto.agregarError(nodo, "no existe una funcion '" + nodo.getNombre() + "' con argumentos compatibles");

        return null;
    }

    @Override
    public Object visitarExprAccesoArray(ExprAccesoArray nodo) {

        // visitar el arreglo del acceso
        Object tipoArrayObj = nodo.getObjeto().accept(this);
        Tipo tipoArray = extraerTipoDeExpresion(tipoArrayObj);

        // verificar si el tipo es nulo
        if (tipoArray == null) {
            return null;
        }

        // verificar si el tipo no es un arreglo
        if (tipoArray.getDimension() <= 0) {
            this.contexto.agregarError(nodo, "se esperaba un arreglo");

            return null;
        }

        // visitar el indice del acceso
        Object tipoIndiceObj = nodo.getIndice().accept(this);
        Tipo tipoIndice = extraerTipoDeExpresion(tipoIndiceObj);

        // verificar si el indice es entero
        if (tipoIndice != null && !this.contexto.esEntero(tipoIndice)) {
            this.contexto.agregarError(nodo.getIndice(), "el indice debe ser entero");
        }

        // devolver el tipo elemento
        return this.contexto.tipoElemento(tipoArray);
    }

    @Override
    public Object visitarExprAccesoMiembro(ExprAccesoMiembro nodo) {

        // visitar el objeto del acceso
        Object tipoBaseObj = nodo.getObjeto().accept(this);
        Tipo tipoBase = extraerTipoDeExpresion(tipoBaseObj);

        // verificar si el tipo es nulo
        if (tipoBase == null) {
            return null;
        }

        // buscar el campo en el tipo
        Simbolo campo = tipoBase.buscarCampo(nodo.getMiembro());

        // reportar error si el campo no existe
        if (campo == null) {
            this.contexto.agregarError(nodo, "el campo '" + nodo.getMiembro() + "' no existe en '" + tipoBase.getNombre() + "'");

            return null;
        }

        return campo.getTipo();
    }

    @Override
    public Object visitarExprPostIncremento(ExprPostIncremento nodo) {
        // visitar la variable del incremento
        Object tipoObj = nodo.getVariable().accept(this);
        Tipo tipoVar = extraerTipoDeExpresion(tipoObj);

        // verificar si la variable es numerica
        if (tipoVar != null && !this.contexto.esNumerico(tipoVar)) {
            this.contexto.agregarError(nodo, "incremento solo permitido sobre tipos numericos");
        }

        return tipoVar;
    }

    @Override
    public Object visitarExprPostDecremento(ExprPostDecremento nodo) {

        // visitar la variable del decremento
        Object tipoObj = nodo.getVariable().accept(this);
        Tipo tipoVar = extraerTipoDeExpresion(tipoObj);

        // verificar si la variable es numerica
        if (tipoVar != null && !this.contexto.esNumerico(tipoVar)) {
            this.contexto.agregarError(nodo, "decremento solo permitido sobre tipos numericos");
        }

        return tipoVar;
    }

    @Override
    public Object visitarExprListaLiteral(ExprListaLiteral nodo) {
        Tipo tipoPrevio = null;

        // recorrer cada elemento de la lista
        for (NodoASTY elem : nodo.getElementos()) {

            // omitir elementos nulos
            if (elem == null) {
                continue;
            }

            // visitar el elemento
            Object tipoObj = elem.accept(this);
            Tipo tipoActual = extraerTipoDeExpresion(tipoObj);

            // omitir elementos sin tipo
            if (tipoActual == null) {
                continue;
            }

            // registrar el primer tipo
            if (tipoPrevio == null) {
                tipoPrevio = tipoActual;

            } else if (!this.contexto.esCompatible(tipoPrevio, tipoActual)) {
                // reportar elementos con tipos diferentes
                this.contexto.agregarError(elem, "elementos con tipos diferentes en lista literal");
            }
        }
        // verificar si no hay tipo previo
        if (tipoPrevio == null) {
            return null;
        }

        // devolver el tipo arreglo
        return this.contexto.tipoArray(tipoPrevio);
    }

    @Override
    public Object visitarExprNegativa(ExprNegativa nodo) {
        // visitar la expresion interna
        Object tipoObj = nodo.getExpresion().accept(this);
        Tipo tipoExp = extraerTipoDeExpresion(tipoObj);

        // verificar si el tipo es numerico
        if (tipoExp != null && !this.contexto.esNumerico(tipoExp)) {
            this.contexto.agregarError(nodo, "negacion aritmetica solo permitida sobre numeros");
        }

        return tipoExp;
    }

    @Override
    public Object visitarExprNegada(ExprNegada nodo) {
        // visitar la expresion interna
        Object tipoObj = nodo.getExpresion().accept(this);
        Tipo tipoExp = extraerTipoDeExpresion(tipoObj);

        // verificar si el tipo es booleano
        if (tipoExp != null && !this.contexto.esBooleano(tipoExp)) {
            this.contexto.agregarError(nodo, "negacion logica solo permitida sobre booleanos");
        }

        return this.contexto.tipoPrimitivo("booleano");
    }

    @Override
    public Object visitarExprMultiplicacionDivision(ExprMultiplicacionDivision nodo) {

        // visitar ambos operandos
        Object tipoIzqObj = nodo.getIzquierdo().accept(this);
        Object tipoDerObj = nodo.getDerecho().accept(this);
        Tipo tipoIzq = extraerTipoDeExpresion(tipoIzqObj);
        Tipo tipoDer = extraerTipoDeExpresion(tipoDerObj);

        // verificar si el operando izquierdo es numerico
        if (tipoIzq != null && !this.contexto.esNumerico(tipoIzq)) {
            this.contexto.agregarError(nodo.getIzquierdo(), "operando izquierdo debe ser numerico");
        }

        // verificar si el operando derecho es numerico
        if (tipoDer != null && !this.contexto.esNumerico(tipoDer)) {
            this.contexto.agregarError(nodo.getDerecho(), "operando derecho debe ser numerico");
        }

        // devolver el tipo de mayor jerarquia
        return this.contexto.tipoMayorJerarquia(tipoIzq, tipoDer);
    }

    @Override
    public Object visitarExprSumaResta(ExprSumaResta nodo) {

        // visitar ambos operandos
        Object tipoIzqObj = nodo.getIzquierdo().accept(this);
        Object tipoDerObj = nodo.getDerecho().accept(this);
        Tipo tipoIzq = extraerTipoDeExpresion(tipoIzqObj);
        Tipo tipoDer = extraerTipoDeExpresion(tipoDerObj);

        // devolver el tipo de mayor jerarquia si algun operando es nulo
        if (tipoIzq == null || tipoDer == null) {
            return this.contexto.tipoMayorJerarquia(tipoIzq, tipoDer);
        }

        String operador = nodo.getOperador();

        // concatenacion de cadenas
        if ("+".equals(operador) && (this.contexto.esCadena(tipoIzq) || this.contexto.esCadena(tipoDer))) {
            return this.contexto.tipoPrimitivo("cadena");
        }

        // verificar si ambos operandos son numericos
        if (!this.contexto.esNumerico(tipoIzq) || !this.contexto.esNumerico(tipoDer)) {
            this.contexto.agregarError(nodo, "operacion aritmetica solo permitida entre numericos");
            return null;
        }

        // devolver flotante si hay, si no entero
        return this.contexto.tipoMayorJerarquia(tipoIzq, tipoDer);
    }

    @Override
    public Object visitarExprRelacional(ExprRelacional nodo) {

        // visitar ambos operandos
        Object tipoIzqObj = nodo.getIzquierdo().accept(this);
        Object tipoDerObj = nodo.getDerecho().accept(this);

        Tipo tipoIzq = extraerTipoDeExpresion(tipoIzqObj);
        Tipo tipoDer = extraerTipoDeExpresion(tipoDerObj);

        // verificar la compatibilidad de los tipos
        if (tipoIzq != null && tipoDer != null) {
            boolean num = this.contexto.esNumerico(tipoIzq) && this.contexto.esNumerico(tipoDer);
            boolean txt = this.contexto.esCadena(tipoIzq) && this.contexto.esCadena(tipoDer);
            boolean bool = this.contexto.esBooleano(tipoIzq) && this.contexto.esBooleano(tipoDer);

            // reportar si los tipos son incompatibles
            if (!num && !txt && !bool) {
                this.contexto.agregarError(nodo, "tipos incompatibles para comparacion");
            }
        }

        return this.contexto.tipoPrimitivo("booleano");
    }

    @Override
    public Object visitarExprAnd(ExprAnd nodo) {
        // visitar ambos operandos
        Object tipoIzqObj = nodo.getIzquierdo().accept(this);
        Object tipoDerObj = nodo.getDerecho().accept(this);

        Tipo tipoIzq = extraerTipoDeExpresion(tipoIzqObj);
        Tipo tipoDer = extraerTipoDeExpresion(tipoDerObj);

        // verificar si el operando izquierdo es booleano
        if (tipoIzq != null && !this.contexto.esBooleano(tipoIzq)) {
            this.contexto.agregarError(nodo.getIzquierdo(), "operando izquierdo debe ser booleano");
        }

        // verificar si el operando derecho es booleano
        if (tipoDer != null && !this.contexto.esBooleano(tipoDer)) {
            this.contexto.agregarError(nodo.getDerecho(), "operando derecho debe ser booleano");
        }

        return this.contexto.tipoPrimitivo("booleano");
    }

    @Override
    public Object visitarExprOr(ExprOr nodo) {

        // visitar ambos operandos
        Object tipoIzqObj = nodo.getIzquierdo().accept(this);
        Object tipoDerObj = nodo.getDerecho().accept(this);

        Tipo tipoIzq = extraerTipoDeExpresion(tipoIzqObj);
        Tipo tipoDer = extraerTipoDeExpresion(tipoDerObj);

        // verificar si el operando izquierdo es booleano
        if (tipoIzq != null && !this.contexto.esBooleano(tipoIzq)) {
            this.contexto.agregarError(nodo.getIzquierdo(), "operando izquierdo debe ser booleano");
        }

        // verificar si el operando derecho es booleano
        if (tipoDer != null && !this.contexto.esBooleano(tipoDer)) {
            this.contexto.agregarError(nodo.getDerecho(), "operando derecho debe ser booleano");
        }

        return this.contexto.tipoPrimitivo("booleano");
    }
}