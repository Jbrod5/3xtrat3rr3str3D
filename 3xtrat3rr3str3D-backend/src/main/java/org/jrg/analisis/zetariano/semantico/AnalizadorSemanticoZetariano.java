package org.jrg.analisis.zetariano.semantico;

import java.util.ArrayList;
import java.util.List;

import org.jrg.analisis.comun.AmbitoSemantico;
import org.jrg.model.base.FlujoControl;
import org.jrg.model.semantico.CategoriaSimbolo;
import org.jrg.model.semantico.Simbolo;
import org.jrg.model.semantico.Tipo;
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
import org.jrg.service.error.RecolectorErrores;
import org.jrg.model.error.TipoError;

// definir el analizador semantico para el lenguaje Zetariano
public class AnalizadorSemanticoZetariano implements ZetarianoAstVisitor<Object> {

    // almacenar el recolector de errores
    private final RecolectorErrores recolectorErrores;

    // ambitosss
    private AmbitoSemantico ambitoGlobal;
    private AmbitoSemantico ambitoActual;
    private AmbitoSemantico ambitoClase;


    private Tipo tipoClaseActual; // tipo de clase actual en analisis

    // contadores para validar break continue y return
    private int nivelCiclos;
    private int nivelSwitch;

    // estado de la funcion actual
    private boolean enMetodoConRetorno;
    private Tipo tipoRetornoActual;
    private boolean enMetodoConstructor;

    // indicador de mmm alcanzabilidad?? xd dentro de un bloque
    private boolean alcanzable;

    /**
     * Crear el analizador semantico para el lenguaje Zetariano.
     */
    public AnalizadorSemanticoZetariano(RecolectorErrores recolectorErrores) {

        // verificar si el recolector es nulo
        if (recolectorErrores == null) {
            this.recolectorErrores = new RecolectorErrores();
        } else {
            this.recolectorErrores = recolectorErrores;
        }

        // inicializar los contadores y banderas
        this.nivelCiclos = 0;
        this.nivelSwitch = 0;
        this.enMetodoConRetorno = false;
        this.tipoRetornoActual = null;
        this.enMetodoConstructor = false;
        this.alcanzable = true;
    }

    /**
     * Obtener el ambito global tras el analisis.
     */
    public AmbitoSemantico obtenerAmbitoGlobal() {
        return ambitoGlobal;
    }

    /**
     * Obtener el ambito de la clase analizada.
     */
    public AmbitoSemantico obtenerAmbitoClase() {
        return ambitoClase;
    }

    /**
     * Obtener el tipo de la clase analizada.
     */
    public Tipo obtenerTipoClase() {
        return tipoClaseActual;
    }

    // ==================== MANEJO DE ERRORES ====================

    // agregar un error semantico tomando linea y columna del nodo
    private void agregarError(NodoASTZetariano nodo, String descripcion) {
        int linea = 0;
        int columna = 0;

        // verificar si el nodo no es nulo
        if (nodo != null) {
            linea = nodo.getLinea();
            columna = nodo.getColumna();
        }

        // delegar el registro del errorrrr
        recolectorErrores.agregar(TipoError.SEMANTICO, linea, columna, descripcion);
    }

    // agregar un error semantico con linea y columna explicitas
    private void agregarError(int linea, int columna, String descripcion) {
        recolectorErrores.agregar(TipoError.SEMANTICO, linea, columna, descripcion);
    }

    // ==================== MANEJO DE AMBITOS ====================

    // entrar a un nuevo ambito hijo
    private void entrarAmbito(String nombre) {

        // crear el nuevo ambito con el padre actual
        AmbitoSemantico nuevo = new AmbitoSemantico(nombre, ambitoActual);

        // registrar el ambito en el padre si existe
        if (ambitoActual != null) {
            ambitoActual.obtenerAmbito().agregarAmbito(nuevo.obtenerAmbito());
        }

        // actualizar el ambito actual
        ambitoActual = nuevo;
    }

    // salir del ambito actual
    private void salirAmbito() {

        // verificar que exista un padre antes de salir
        if (ambitoActual != null && ambitoActual.obtenerPadre() != null) {
            ambitoActual = ambitoActual.obtenerPadre();
        }

    }

    // ==================== TIPOS PRIMITIVOS ====================

    // registrar los tipos primitivos del lenguaje
    private void registrarTiposPrimitivos() {

        // registrar los tipos int, double, char, boolean string y null :D

        Tipo tipoInt = new Tipo("int", true);
        ambitoGlobal.declararTipo(tipoInt);

        Tipo tipoDouble = new Tipo("double", true);
        ambitoGlobal.declararTipo(tipoDouble);

        Tipo tipoChar = new Tipo("char", true);
        ambitoGlobal.declararTipo(tipoChar);

        Tipo tipoBoolean = new Tipo("boolean", true);
        ambitoGlobal.declararTipo(tipoBoolean);

        Tipo tipoString = new Tipo("String", true);
        ambitoGlobal.declararTipo(tipoString);

        Tipo tipoNull = new Tipo("null", true);
        ambitoGlobal.declararTipo(tipoNull);
    }

    // ==================== FUNCIONES BUILT-IN ====================

    // registrar las funciones especiales del lenguaje en el ambito global
    private void registrarFuncionesBuiltIn() {

        // println con multiples sobrecargas para tipos primitivos comunes
        registrarMetodoBuiltIn("println", new ArrayList<Tipo>(), null);
        registrarMetodoBuiltIn("println", construirListaTipos("int"), null);
        registrarMetodoBuiltIn("println", construirListaTipos("double"), null);
        registrarMetodoBuiltIn("println", construirListaTipos("char"), null);
        registrarMetodoBuiltIn("println", construirListaTipos("boolean"), null);
        registrarMetodoBuiltIn("println", construirListaTipos("String"), null);

        // print con las mismas sobrecargas
        registrarMetodoBuiltIn("print", new ArrayList<Tipo>(), null);
        registrarMetodoBuiltIn("print", construirListaTipos("int"), null);
        registrarMetodoBuiltIn("print", construirListaTipos("double"), null);
        registrarMetodoBuiltIn("print", construirListaTipos("char"), null);
        registrarMetodoBuiltIn("print", construirListaTipos("boolean"), null);
        registrarMetodoBuiltIn("print", construirListaTipos("String"), null);

        // readln sin argumentos devuelve String
        Tipo tipoString = ambitoGlobal.buscarTipo("String");
        registrarMetodoBuiltIn("readln", new ArrayList<Tipo>(), tipoString);
    }

    // declarar un metodo built-in en el ambito global
    private void registrarMetodoBuiltIn(String nombre, List<Tipo> tiposParams, Tipo tipoRetorno) {

        // construir el simbolo del metodo
        Simbolo metodo = new Simbolo(nombre, CategoriaSimbolo.METODO, tipoRetorno);
        metodo.setFila(0);
        metodo.setColumna(0);
        metodo.setTiposParametros(tiposParams);
        metodo.setAmbito(ambitoGlobal.obtenerAmbito());

        // declarar el metodo en el ambito global
        ambitoGlobal.declararMetodo(metodo);
    }

    // construir una lista de tipos con un unico elemento por nombre
    private List<Tipo> construirListaTipos(String nombreTipo) {

        // crear la lista de salida
        List<Tipo> lista = new ArrayList<>();

        // resolver el tipo por nombre
        Tipo tipo = ambitoGlobal.buscarTipo(nombreTipo);

        // agregar el tipo si existe
        if (tipo != null) {
            lista.add(tipo);
        }

        return lista;
    }

    // ==================== BUSQUEDA DE TIPOS ====================

    // buscar un tipo por nombre en el ambito actual y en el global
    private Tipo obtenerTipo(String nombre) {

        // verificar si el nombre es nulo
        if (nombre == null) {
            return null;
        }

        // buscar en el ambito actual
        Tipo tipo = ambitoActual.buscarTipo(nombre);
        if (tipo != null) {
            return tipo;
        }

        // buscar en el ambito global
        return ambitoGlobal.buscarTipo(nombre);
    }

    // sobrecarga para nodos tipo_dato que vienen como NodoASTZetariano
    private Tipo obtenerTipo(NodoASTZetariano tipoNodo) {

        // verificar si el nodo es nulo
        if (tipoNodo == null) {
            return null;
        }

        // verificar si el nodo es TipoDato
        if (tipoNodo instanceof TipoDato) {
            return obtenerTipo((TipoDato) tipoNodo);
        }

        return null;
    }

    // resolver el tipo de un nodo TipoDato
    private Tipo obtenerTipo(TipoDato tipoDato) {

        // verificar si el nodo es nulo
        if (tipoDato == null) {
            return null;
        }

        // resolver el tipo por su nombre
        return obtenerTipo(tipoDato.getTipo());
    }

    // extraer el nombre del tipo para mensajes de error
    private String extraerNombreTipo(NodoASTZetariano tipoNodo) {

        // verificar si el nodo es nulo
        if (tipoNodo == null) {
            return "desconocido";
        }

        // devolver el nombre si es TipoDato
        if (tipoNodo instanceof TipoDato) {
            return ((TipoDato) tipoNodo).getTipo();
        }

        return "desconocido";
    }

    // ==================== DECLARACION DE VARIABLES ====================

    // declarar variable buscando en TODOS los ambitos padres
    // si ya existe en cualquier padre sera un error :D
    private boolean declararVariable(String nombre, Tipo tipo, NodoASTZetariano nodo) {

        // verificar si el nombre es valido
        if (nombre == null || nombre.isEmpty()) {
            agregarError(nodo, "declaracion sin identificador");

            return false;
        }

        // buscar en todos los ambitos padres incluido el actual
        Simbolo existente = ambitoActual.buscarSimbolo(nombre);
        if (existente != null) {
            agregarError(nodo, "la variable '" + nombre + "' ya esta declarada en un ambito padre");

            return false;
        }

        // construir el simbolo de la variable
        Simbolo simbolo = new Simbolo(nombre, CategoriaSimbolo.VARIABLE, tipo);

        simbolo.setFila(nodo == null ? 0 : nodo.getLinea());
        simbolo.setColumna(nodo == null ? 0 : nodo.getColumna());
        simbolo.setAmbito(ambitoActual.obtenerAmbito());

        // declarar el simbolo en el ambito actual
        if (!ambitoActual.declararSimbolo(simbolo, true)) {
            agregarError(nodo, "la variable '" + nombre + "' ya esta declarada");

            return false;
        }

        return true;
    }

    // declarar parametro buscando en todos los ambitos padres
    private boolean declararParametro(String nombre, Tipo tipo, NodoASTZetariano nodo) {

        // verificar si el nombre es valido
        if (nombre == null || nombre.isEmpty()) {
            agregarError(nodo, "parametro sin identificador");

            return false;
        }

        // buscar en todos los ambitos padres incluido el actual
        Simbolo existente = ambitoActual.buscarSimbolo(nombre);
        if (existente != null) {
            agregarError(nodo, "el parametro '" + nombre + "' ya esta declarado en un ambito padre");

            return false;
        }

        // construir el simbolo del parametro
        Simbolo simbolo = new Simbolo(nombre, CategoriaSimbolo.PARAMETRO, tipo);

        simbolo.setFila(nodo == null ? 0 : nodo.getLinea());
        simbolo.setColumna(nodo == null ? 0 : nodo.getColumna());
        simbolo.setAmbito(ambitoActual.obtenerAmbito());

        // declarar el simbolo en el ambito actual
        if (!ambitoActual.declararSimbolo(simbolo, true)) {
            agregarError(nodo, "el parametro '" + nombre + "' ya esta declarado");

            return false;
        }

        return true;
    }

    // ==================== PROGRAMA ====================

    @Override
    public Object visitarPrograma(Programa programa) {
        // crear el ambito global
        ambitoGlobal = new AmbitoSemantico("global", null);
        ambitoActual = ambitoGlobal;

        // registrar los tipos primitivos del lenguaje
        registrarTiposPrimitivos();

        // registrar las funciones especiales del lenguaje
        // println, print y readln son parte del sistema y no requieren declaracion :D
        registrarFuncionesBuiltIn();

        // analizar la clase
        if (programa.getDefinicionClase() != null) {
            programa.getDefinicionClase().accept(this);
        } else {
            agregarError(programa, "el programa debe contener una clase");
        }

        return null;
    }

    // ==================== CLASE ====================

    @Override
    public Object visitarDefClase(DefClase clase) {

        // obtener el nombre de la clase
        String nombreClase = clase.getNombre();

        // verificar que el tipo no exista previamente
        if (ambitoGlobal.buscarTipo(nombreClase) != null) {
            agregarError(clase, "el tipo '" + nombreClase + "' ya esta definido");

            return null;
        }

        // crear el tipo de la clase y registrarlo
        Tipo tipoClase = new Tipo(nombreClase, false);
        ambitoGlobal.declararTipo(tipoClase);
        tipoClaseActual = tipoClase;

        // crear un ambito para la clase como hijo del global
        entrarAmbito("clase:" + nombreClase);
        ambitoClase = ambitoActual;

        // primera pasada: registrar atributos, constructores y metodos
        int contadorConstructores = 0;
        for (NodoASTZetariano miembro : clase.getMiembros()) {

            if (miembro instanceof MiembroAtributo) {
                registrarAtributo(((MiembroAtributo) miembro).getAtributo());

            } else if (miembro instanceof MiembroConstructor) {
                NodoASTZetariano constructorNodo = ((MiembroConstructor) miembro).getConstructor();

                if (constructorNodo instanceof DefConstructor) {
                    registrarConstructor((DefConstructor) constructorNodo);
                    contadorConstructores++;
                }

            } else if (miembro instanceof MiembroMetodo) {
                registrarMetodo(((MiembroMetodo) miembro).getMetodo());
            }

        }

        // registrar un constructor por defecto cuando la clase no declara ninguno
        // igual que en Java, permite new MiClase() sin argumentos :D
        if (contadorConstructores == 0) {
            List<Tipo> tiposVacios = new ArrayList<>();

            Simbolo constructorPorDefecto = new Simbolo(nombreClase, CategoriaSimbolo.CONSTRUCTOR, null);

            constructorPorDefecto.setFila(clase.getLinea());
            constructorPorDefecto.setColumna(clase.getColumna());
            constructorPorDefecto.setTiposParametros(tiposVacios);
            constructorPorDefecto.setAmbito(ambitoClase.obtenerAmbito());

            ambitoClase.declararConstructor(constructorPorDefecto);
        }

        // segunda pasada: analizar los cuerpos de constructores y metodos
        for (NodoASTZetariano miembro : clase.getMiembros()) {

            if (miembro instanceof MiembroConstructor) {
                miembro.accept(this);
            } else if (miembro instanceof MiembroMetodo) {
                miembro.accept(this);
            }
        }

        // salir del ambito de la clase
        salirAmbito();
        ambitoClase = null;

        return null;
    }

    // ==================== ATRIBUTOS ====================

    // registrar un atributo de la clase como campo del tipo
    private void registrarAtributo(NodoASTZetariano atributo) {

        if (atributo instanceof AtributoSimple) {

            // procesar atributo simple
            AtributoSimple simple = (AtributoSimple) atributo;
            Tipo tipo = obtenerTipo(simple.getTipo());

            // reportar error si el tipo no existe
            if (tipo == null) {
                agregarError(simple, "tipo de atributo no definido: '" + extraerNombreTipo(simple.getTipo()) + "'");

                return;
            }

            registrarCampo(simple.getIdentificador(), tipo, simple);

        } else if (atributo instanceof AtributoArray) {

            // procesar atributo array
            AtributoArray array = (AtributoArray) atributo;
            Tipo tipoBase = obtenerTipo(array.getTipo());

            // reportar error si el tipo no existe
            if (tipoBase == null) {
                agregarError(array, "tipo de atributo no definido: '" + extraerNombreTipo(array.getTipo()) + "'");

                return;
            }

            // construir el tipo array con sus dimensiones
            Tipo tipoArray = crearTipoArray(tipoBase, array.getDimensiones());
            registrarCampo(array.getIdentificador(), tipoArray, array);
        }
    }

    // registrar un campo en el ambito de la clase
    private void registrarCampo(String nombre, Tipo tipo, NodoASTZetariano nodo) {

        // buscar si el campo ya existe en la clase
        Simbolo existente = ambitoClase.buscarSimboloLocal(nombre);
        if (existente != null) {
            agregarError(nodo, "el campo '" + nombre + "' ya esta declarado");
            return;
        }

        // construir el simbolo del campo
        Simbolo campo = new Simbolo(nombre, CategoriaSimbolo.CAMPO_ESTRUCTURA, tipo);

        campo.setFila(nodo == null ? 0 : nodo.getLinea());
        campo.setColumna(nodo == null ? 0 : nodo.getColumna());
        campo.setAmbito(ambitoClase.obtenerAmbito());

        // declarar el campo en el ambito de la clase
        ambitoClase.declararSimbolo(campo, true);

        // agregar el campo al tipo de la clase para consultas posteriores
        tipoClaseActual.agregarCampo(campo);
    }

    @Override
    public Object visitarMiembroAtributo(MiembroAtributo miembro) {
        // los atributos ya fueron registrados en la primera pasada
        return null;
    }

    @Override
    public Object visitarAtributoSimple(AtributoSimple atributo) {
        return null;
    }

    @Override
    public Object visitarAtributoArray(AtributoArray atributo) {
        return null;
    }

    // ==================== CONSTRUCTORES ====================

    // registrar un constructor en el ambito de la clase
    private void registrarConstructor(DefConstructor constructor) {

        // obtener el nombre del constructor
        String nombre = constructor.getNombre();

        // el constructor debe llamarse igual que la clase
        if (!nombre.equals(tipoClaseActual.getNombre())) {
            agregarError(constructor, "el constructor debe tener el mismo nombre que la clase");

            return;
        }

        // extraer los tipos de los parametros
        List<Tipo> tiposParametros = new ArrayList<>();

        if (constructor.getParametros() != null) {

            Parametros params = (Parametros) constructor.getParametros();

            for (NodoASTZetariano p : params.getParametros()) {
                Tipo tipoParam = extraerTipoParametro(p);
                tiposParametros.add(tipoParam);
            }

        }

        // verificar si ya existe un constructor con la misma firma
        if (existeConstructorConFirma(nombre, tiposParametros)) {
            agregarError(constructor, "ya existe un constructor con la misma firma");

            return;
        }

        // construir el simbolo del constructor
        Simbolo simboloConstructor = new Simbolo(nombre, CategoriaSimbolo.CONSTRUCTOR, null);

        simboloConstructor.setFila(constructor.getLinea());
        simboloConstructor.setColumna(constructor.getColumna());
        simboloConstructor.setTiposParametros(tiposParametros);
        simboloConstructor.setAmbito(ambitoClase.obtenerAmbito());

        // declarar el constructor en el ambito de la clase
        ambitoClase.declararConstructor(simboloConstructor);
    }

    @Override
    public Object visitarMiembroConstructor(MiembroConstructor miembro) {

        // obtener el nodo interno del constructor
        NodoASTZetariano constructorNodo = miembro.getConstructor();
        if (!(constructorNodo instanceof DefConstructor)) {
            return null;
        }

        DefConstructor constructor = (DefConstructor) constructorNodo;

        // entrar a un ambito nuevo para el constructor
        entrarAmbito("constructor:" + constructor.getNombre());
        enMetodoConstructor = true;
        enMetodoConRetorno = false;
        tipoRetornoActual = null;
        alcanzable = true;

        // registrar los parametros
        if (constructor.getParametros() != null) {
            registrarParametros((Parametros) constructor.getParametros());
        }

        // analizar las instrucciones del cuerpo
        analizarInstrucciones(constructor.getInstrucciones());

        // no es necesario que un constructor retorne
        enMetodoConstructor = false;
        salirAmbito();

        return null;
    }

    @Override
    public Object visitarDefConstructor(DefConstructor constructor) {
        return null;
    }

    // ==================== METODOS ====================

    // registrar un metodo en el ambito de la clase
    private void registrarMetodo(NodoASTZetariano metodo) {
        String nombre = null;
        Tipo tipoRetorno = null;

        if (metodo instanceof MetodoSinRetorno) {

            // procesar metodo sin retorno
            MetodoSinRetorno m = (MetodoSinRetorno) metodo;
            nombre = m.getNombre();
            tipoRetorno = null;

        } else if (metodo instanceof MetodoConRetorno) {
            // procesar metodo con retorno
            MetodoConRetorno m = (MetodoConRetorno) metodo;
            nombre = m.getNombre();
            tipoRetorno = obtenerTipo(m.getTipo());

            // reportar error si el tipo no existe
            if (tipoRetorno == null) {
                agregarError(metodo, "tipo de retorno no definido: '" + extraerNombreTipo(m.getTipo()) + "'");

                return;
            }

        } else {
            return;
        }

        // extraer los tipos de los parametros
        List<Tipo> tiposParametros = new ArrayList<>();
        NodoASTZetariano paramsNodo = null;

        if (metodo instanceof MetodoSinRetorno) {
            paramsNodo = ((MetodoSinRetorno) metodo).getParametros();
        } else if (metodo instanceof MetodoConRetorno) {
            paramsNodo = ((MetodoConRetorno) metodo).getParametros();
        }

        if (paramsNodo != null) {
            Parametros params = (Parametros) paramsNodo;

            for (NodoASTZetariano p : params.getParametros()) {
                Tipo tipoParam = extraerTipoParametro(p);
                tiposParametros.add(tipoParam);
            }
        }

        // verificar si ya existe un metodo con la misma firma
        // se permite sobrecarga siempre que los tipos de parametros difieran :D
        if (existeMetodoConFirma(nombre, tiposParametros)) {
            agregarError(metodo, "ya existe un metodo '" + nombre + "' con la misma firma");

            return;
        }

        // construir el simbolo del metodo
        Simbolo simboloMetodo = new Simbolo(nombre, CategoriaSimbolo.METODO, tipoRetorno);

        simboloMetodo.setFila(metodo.getLinea());
        simboloMetodo.setColumna(metodo.getColumna());
        simboloMetodo.setTiposParametros(tiposParametros);
        simboloMetodo.setAmbito(ambitoClase.obtenerAmbito());

        // declarar el metodo en el ambito de la clase
        ambitoClase.declararMetodo(simboloMetodo);
    }

    // verificar si existe un metodo con la firma exacta
    private boolean existeMetodoConFirma(String nombre, List<Tipo> tiposParametros) {

        // obtener los metodos locales con ese nombre
        List<Simbolo> existentes = ambitoClase.obtenerMetodosLocal(nombre);

        // comparar cada firma existente
        for (Simbolo s : existentes) {
            if (mismaFirma(s.getTiposParametros(), tiposParametros)) {
                return true;
            }
        }

        return false;
    }

    // verificar si existe un constructor con la firma exacta
    private boolean existeConstructorConFirma(String nombre, List<Tipo> tiposParametros) {

        // obtener los constructores locales con ese nombre
        List<Simbolo> existentes = ambitoClase.obtenerConstructoresLocal(nombre);

        // comparar cada firma existente
        for (Simbolo s : existentes) {
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

    // extraer el tipo de un parametro simple o array
    private Tipo extraerTipoParametro(NodoASTZetariano parametro) {

        if (parametro instanceof ParamSimple) {
            // procesar parametro simple
            ParamSimple p = (ParamSimple) parametro;
            return obtenerTipo(p.getTipo());

        } else if (parametro instanceof ParamArray) {

            // procesar parametro array
            ParamArray p = (ParamArray) parametro;
            Tipo base = obtenerTipo(p.getTipo());

            if (base == null) {
                return null;
            }

            // construir el tipo array con sus dimensiones
            return crearTipoArray(base, p.getDimensiones());
        }

        return null;
    }

    @Override
    public Object visitarMiembroMetodo(MiembroMetodo miembro) {

        // obtener el nodo interno del metodo
        NodoASTZetariano metodo = miembro.getMetodo();
        String nombre = null;
        Tipo tipoRetorno = null;
        NodoASTZetariano paramsNodo = null;
        List<NodoASTZetariano> instrucciones = null;

        if (metodo instanceof MetodoSinRetorno) {
            // procesar metodo sin retorno
            MetodoSinRetorno m = (MetodoSinRetorno) metodo;
            nombre = m.getNombre();
            tipoRetorno = null;
            paramsNodo = m.getParametros();
            instrucciones = m.getInstrucciones();

        } else if (metodo instanceof MetodoConRetorno) {
            // procesar metodo con retorno
            MetodoConRetorno m = (MetodoConRetorno) metodo;
            nombre = m.getNombre();
            tipoRetorno = obtenerTipo(m.getTipo());
            paramsNodo = m.getParametros();
            instrucciones = m.getInstrucciones();

        } else {
            return null;
        }

        // entrar a un ambito nuevo para el metodo
        entrarAmbito("metodo:" + nombre);
        alcanzable = true;

        // configurar el estado segun el tipo de retorno
        if (tipoRetorno != null) {
            enMetodoConRetorno = true;
            tipoRetornoActual = tipoRetorno;
        } else {
            enMetodoConRetorno = false;
            tipoRetornoActual = null;
        }

        enMetodoConstructor = false;

        // registrar parametros
        if (paramsNodo != null) {
            registrarParametros((Parametros) paramsNodo);
        }

        // analizar el cuerpo
        FlujoControl flujo = analizarInstrucciones(instrucciones);

        // verificar que todos los caminos retornen si el metodo retorna valor
        if (enMetodoConRetorno && flujo.puedeContinuar()) {
            agregarError(metodo, "el metodo '" + nombre + "' debe retornar un valor en todos los caminos");
        }

        // restaurar el estado anterior
        enMetodoConRetorno = false;
        tipoRetornoActual = null;
        salirAmbito();

        return null;
    }

    @Override
    public Object visitarMetodoSinRetorno(MetodoSinRetorno metodo) {
        return null;
    }

    @Override
    public Object visitarMetodoConRetorno(MetodoConRetorno metodo) {
        return null;
    }

    // ==================== PARAMETROS ====================

    // registrar los parametros de un metodo o constructor
    private void registrarParametros(Parametros parametros) {

        // verificar si la lista es nula
        if (parametros == null) {
            return;
        }

        // recorrer cada parametro
        for (NodoASTZetariano p : parametros.getParametros()) {

            if (p instanceof ParamSimple) {
                // procesar parametro simple
                ParamSimple simple = (ParamSimple) p;
                Tipo tipo = obtenerTipo(simple.getTipo());

                // reportar error si el tipo no existe
                if (tipo == null) {
                    agregarError(simple, "tipo de parametro no definido: '" + extraerNombreTipo(simple.getTipo()) + "'");
                    continue;
                }

                declararParametro(simple.getIdentificador(), tipo, simple);

            } else if (p instanceof ParamArray) {
                // procesar parametro array
                ParamArray array = (ParamArray) p;
                Tipo base = obtenerTipo(array.getTipo());

                // reportar error si el tipo no existe
                if (base == null) {
                    agregarError(array, "tipo de parametro no definido: '" + extraerNombreTipo(array.getTipo()) + "'");
                    continue;
                }

                // construir el tipo array con sus dimensiones
                Tipo tipoArray = crearTipoArray(base, array.getDimensiones());
                declararParametro(array.getIdentificador(), tipoArray, array);
            }

        }
    }

    @Override
    public Object visitarParametros(Parametros parametros) {
        return null;
    }

    @Override
    public Object visitarParamSimple(ParamSimple parametro) {
        return null;
    }

    @Override
    public Object visitarParamArray(ParamArray parametro) {
        return null;
    }

    // ==================== TIPOS ====================

    @Override
    public Object visitarTipoDato(TipoDato tipo) {
        return null;
    }

    // construir un tipo array con la dimension indicada
    private Tipo crearTipoArray(Tipo base, int dimensiones) {

        // verificar si el tipo base es nulo
        if (base == null) {
            return null;
        }

        // construir el tipo array con el base y las dimensiones
        Tipo tipoArray = new Tipo(base.getNombre(), base.esPrimitivo(), dimensiones, base, new ArrayList<>(), null);

        return tipoArray;
    }

    // ==================== ANALISIS DE INSTRUCCIONES ====================

    // analizar una lista de instrucciones combinando sus flujos
    private FlujoControl analizarInstrucciones(List<NodoASTZetariano> instrucciones) {
        FlujoControl flujo = new FlujoControl();

        // verificar si la lista es nula
        if (instrucciones == null) {
            return flujo;
        }

        boolean primeraInalcanzable = false;

        // recorrer cada instruccion
        for (NodoASTZetariano inst : instrucciones) {

            // reportar codigo inalcanzable solo una vez
            if (!flujo.puedeContinuar()) {
                if (!primeraInalcanzable) {
                    agregarError(inst, "codigo inalcanzable");
                    primeraInalcanzable = true;
                }
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

    // ==================== INSTRUCCIONES ====================

    @Override
    public Object visitarStmtDeclaracion(StmtDeclaracion stmt) {
        // visitar la declaracion si existe
        if (stmt.getDeclaracion() != null) {
            stmt.getDeclaracion().accept(this);
        }

        return new FlujoControl();
    }

    @Override
    public Object visitarStmtAsignacion(StmtAsignacion stmt) {
        // visitar la asignacion si existe
        if (stmt.getAsignacion() != null) {
            stmt.getAsignacion().accept(this);
        }

        return new FlujoControl();
    }

    @Override
    public Object visitarStmtCondicional(StmtCondicional stmt) {
        // visitar el condicional si existe
        if (stmt.getCondicional() != null) {
            return stmt.getCondicional().accept(this);
        }

        return new FlujoControl();
    }

    @Override
    public Object visitarStmtSeleccion(StmtSeleccion stmt) {
        // visitar la seleccion si existe
        if (stmt.getSeleccion() != null) {
            return stmt.getSeleccion().accept(this);
        }

        return new FlujoControl();
    }

    @Override
    public Object visitarStmtCiclo(StmtCiclo stmt) {
        // visitar el ciclo si existe
        if (stmt.getCiclo() != null) {
            return stmt.getCiclo().accept(this);
        }

        return new FlujoControl();
    }

    @Override
    public Object visitarStmtReturn(StmtReturn stmt) {
        // validar que estemos dentro de un metodo
        if (enMetodoConstructor) {
            agregarError(stmt, "no se puede usar 'return' dentro de un constructor");

            return new FlujoControl();
        }

        // verificar si hay expresion de retorno
        if (stmt.getExpresion() != null) {

            // analizar el tipo de la expresion
            Tipo tipoExp = (Tipo) stmt.getExpresion().accept(this);

            // reportar error si el metodo no retorna
            if (!enMetodoConRetorno) {
                agregarError(stmt, "no se puede retornar un valor desde un metodo void");
            } else if (tipoExp != null && tipoRetornoActual != null) {

                // verificar la compatibilidad del tipo
                if (!esTipoCompatible(tipoExp, tipoRetornoActual)) {
                    agregarError(stmt, "tipo de retorno incompatible, se esperaba '" + tipoRetornoActual.getNombre() + "' pero se obtuvo '" + tipoExp.getNombre() + "'");
                }

            }

        } else {
            // retorno sin valor
            if (enMetodoConRetorno) {
                agregarError(stmt, "el metodo debe retornar un valor");
            }
        }

        // marcar flujo como no continuable
        FlujoControl flujo = new FlujoControl();
        flujo.marcarRetorno();

        return flujo;
    }

    @Override
    public Object visitarStmtBreak(StmtBreak stmt) {

        // verificar si esta dentro de un ciclo o switch
        if (nivelCiclos == 0 && nivelSwitch == 0) {
            agregarError(stmt, "'break' solo puede usarse dentro de un ciclo o switch");
        }

        // marcar flujo como terminado
        FlujoControl flujo = new FlujoControl();
        flujo.marcarRetorno();

        return flujo;
    }

    @Override
    public Object visitarStmtContinue(StmtContinue stmt) {

        // verificar si esta dentro de un ciclo
        if (nivelCiclos == 0) {
            agregarError(stmt, "'continue' solo puede usarse dentro de un ciclo");
        }

        // marcar flujo como terminado
        FlujoControl flujo = new FlujoControl();
        flujo.marcarRetorno();

        return flujo;
    }

    @Override
    public Object visitarStmtExpresion(StmtExpresion stmt) {
        // visitar la expresion si existe
        if (stmt.getExpresion() != null) {
            stmt.getExpresion().accept(this);
        }

        return new FlujoControl();
    }

    @Override
    public Object visitarBloque(Bloque bloque) {
        // analizar las instrucciones del bloque
        return analizarInstrucciones(bloque.getInstrucciones());
    }

    // ==================== DECLARACIONES DE VARIABLES ====================

    @Override
    public Object visitarDeclConTipo(DeclConTipo decl) {

        // obtener el nodo del tipo
        NodoASTZetariano tipoNodo = decl.getTipo();
        Tipo tipo = obtenerTipo(tipoNodo);

        // reportar error si el tipo no existe
        if (tipo == null) {
            agregarError(decl, "tipo '" + extraerNombreTipo(tipoNodo) + "' no definido");

            return null;
        }

        // si tiene dimensiones es un array
        if (decl.getDimensiones() > 0) {
            tipo = crearTipoArray(tipo, decl.getDimensiones());
        }

        // declarar la variable
        if (!declararVariable(decl.getIdentificador(), tipo, decl)) {
            return null;
        }

        // analizar el valor inicial si existe
        if (decl.getValor() != null) {
            Tipo tipoExp = (Tipo) decl.getValor().accept(this);

            // verificar la compatibilidad del tipo :'c
            if (tipoExp != null && !esTipoCompatible(tipoExp, tipo)) {
                agregarError(decl, "tipo incompatible en inicializacion, se esperaba '" + tipo.getNombre() + "' pero se obtuvo '" + tipoExp.getNombre() + "'");
            }

        }

        return null;
    }

    @Override
    public Object visitarDeclConListaLiteral(DeclConListaLiteral decl) {

        // obtener el nodo del tipo
        NodoASTZetariano tipoNodo = decl.getTipo();
        Tipo tipoBase = obtenerTipo(tipoNodo);

        // reportar error si el tipo no existe
        if (tipoBase == null) {
            agregarError(decl, "tipo '" + extraerNombreTipo(tipoNodo) + "' no definido");

            return null;
        }

        // construir el tipo array con sus dimensiones
        Tipo tipoArray = crearTipoArray(tipoBase, decl.getDimensiones());

        // declarar la variable
        if (!declararVariable(decl.getIdentificador(), tipoArray, decl)) {

            return null;
        }

        // analizar la lista de valores
        if (decl.getListaExpresiones() != null) {
            ListaExpresiones lista = (ListaExpresiones) decl.getListaExpresiones();

            // validar cada valor de la lista
            for (NodoASTZetariano expr : lista.getExpresiones()) {
                Tipo tipoValor = (Tipo) expr.accept(this);

                // verificar la compatibilidad del tipo
                if (tipoValor != null && !esTipoCompatible(tipoValor, tipoBase)) {
                    agregarError(expr, "valor incompatible con el tipo del array, se esperaba '" + tipoBase.getNombre() + "' pero se obtuvo '" + tipoValor.getNombre() + "'");
                }
            }
        }

        return null;
    }

    // ==================== ASIGNACIONES ====================

    @Override
    public Object visitarAsignacionSimple(AsignacionSimple asignacion) {
        // visitar la variable destino
        Tipo tipoVar = (Tipo) asignacion.getVariable().accept(this);

        // reportar error si la variable no esta declarada
        if (tipoVar == null) {
            agregarError(asignacion, "variable no declarada o no accesible");

            return null;
        }

        // visitar el valor de la asignacion
        Tipo tipoExp = (Tipo) asignacion.getExpresion().accept(this);

        // verificar la compatibilidad del tipo
        if (tipoExp != null && !esTipoCompatible(tipoExp, tipoVar)) {
            agregarError(asignacion, "tipo incompatible en asignacion, se esperaba '" + tipoVar.getNombre() + "' pero se obtuvo '" + tipoExp.getNombre() + "'");
        }

        return null;
    }

    @Override
    public Object visitarAsignacionCompuesta(AsignacionCompuesta asignacion) {

        // visitar la variable destino
        Tipo tipoVar = (Tipo) asignacion.getVariable().accept(this);

        // reportar error si la variable no esta declarada
        if (tipoVar == null) {
            agregarError(asignacion, "variable no declarada o no accesible");

            return null;
        }

        // para += -= *= la variable debe ser numerica
        if (!esTipoNumerico(tipoVar)) {
            agregarError(asignacion, "operador compuesto solo valido sobre tipos numericos");
        }

        // visitar el valor de la asignacion
        Tipo tipoExp = (Tipo) asignacion.getExpresion().accept(this);

        // verificar si la expresion es numerica
        if (tipoExp != null && !esTipoNumerico(tipoExp)) {
            agregarError(asignacion, "expresion debe ser numerica");
        }

        return null;
    }

    // ==================== VARIABLES ASIGNABLES ====================

    @Override
    public Object visitarVarSimple(VarSimple var) {

        // buscar el simbolo en el ambito actual
        Simbolo simbolo = ambitoActual.buscarSimbolo(var.getIdentificador());

        if (simbolo == null) {

            // buscar en el ambito de la clase por si es un atributo
            if (ambitoClase != null) {
                simbolo = ambitoClase.buscarSimbolo(var.getIdentificador());
            }

        }

        // reportar error si el simbolo no existe
        if (simbolo == null) {
            agregarError(var, "variable '" + var.getIdentificador() + "' no declarada");

            return null;
        }

        return simbolo.getTipo();
    }

    @Override
    public Object visitarVarArray(VarArray var) {

        // visitar la variable base
        Tipo tipoBase = (Tipo) var.getVariable().accept(this);

        // verificar si el tipo es nulo
        if (tipoBase == null) {
            return null;
        }

        // verificar si la variable no es un array
        if (tipoBase.getDimension() <= 0) {
            agregarError(var, "se esperaba un array");

            return null;
        }

        // validar el indice
        Tipo tipoIndice = (Tipo) var.getIndice().accept(this);

        // verificar si el indice es entero
        if (tipoIndice != null && !esTipoEntero(tipoIndice)) {
            agregarError(var, "el indice debe ser un entero");
        }

        return tipoBase.getTipoBase();
    }

    @Override
    public Object visitarVarMiembro(VarMiembro var) {

        // visitar la variable base
        Tipo tipoObjeto = (Tipo) var.getVariable().accept(this);

        // verificar si el tipo es nulo
        if (tipoObjeto == null) {
            return null;
        }

        // buscar el campo en el tipo
        Simbolo campo = tipoObjeto.buscarCampo(var.getMiembro());

        // reportar error si el campo no existe
        if (campo == null) {
            agregarError(var, "el campo '" + var.getMiembro() + "' no existe en el tipo '" + tipoObjeto.getNombre() + "'");

            return null;
        }

        return campo.getTipo();
    }

    // ==================== CONDICIONALES ====================

    @Override
    public Object visitarStatementIf(StatementIf stmt) {
        // validar condicion principal
        if (stmt.getCondicion() != null) {
            Tipo tipoCond = (Tipo) stmt.getCondicion().accept(this);

            // verificar si la condicion es booleana
            if (tipoCond != null && !esTipoBooleano(tipoCond)) {
                agregarError(stmt.getCondicion(), "la condicion debe ser booleana");
            }
        }

        // analizar el bloque principal
        entrarAmbito("bloque-if");
        FlujoControl flujoPrincipal = (FlujoControl) stmt.getBloque().accept(this);
        salirAmbito();

        // analizar los else if
        List<FlujoControl> flujosRamas = new ArrayList<>();
        flujosRamas.add(flujoPrincipal);

        // recorrer cada rama sino si
        for (int i = 0; i < stmt.getCondicionesSinoSi().size(); i++) {
            NodoASTZetariano cond = stmt.getCondicionesSinoSi().get(i);
            NodoASTZetariano bloque = stmt.getBloquesSinoSi().get(i);

            // validar la condicion de la rama
            Tipo tipoCond = (Tipo) cond.accept(this);
            if (tipoCond != null && !esTipoBooleano(tipoCond)) {
                agregarError(cond, "la condicion debe ser booleana");
            }

            // analizar el bloque de la rama
            entrarAmbito("bloque-else-if");

            FlujoControl f = (FlujoControl) bloque.accept(this);
            salirAmbito();
            flujosRamas.add(f);
        }

        // analizar el else final
        if (stmt.getBloqueSino() != null) {
            entrarAmbito("bloque-else");

            FlujoControl flujoElse = (FlujoControl) stmt.getBloqueSino().accept(this);
            salirAmbito();
            flujosRamas.add(flujoElse);
        }

        // combinar los flujos
        FlujoControl resultado = new FlujoControl();
        if (stmt.getBloqueSino() == null) {
            // sin else el if siempre puede continuar
            resultado.marcarContinuacion();

        } else {

            // con else puede continuar si alguna rama continua
            boolean algunaContinua = false;
            for (FlujoControl f : flujosRamas) {
                if (f.puedeContinuar()) {
                    algunaContinua = true;
                    break;
                }
            }

            // marcar el flujo segun el resultado
            if (algunaContinua) {
                resultado.marcarContinuacion();
            } else {
                resultado.marcarRetorno();
            }
        }

        return resultado;
    }

    // ==================== SWITCH ====================

    @Override
    public Object visitarStatementSwitch(StatementSwitch stmt) {
        // validar la expresion del switch
        Tipo tipoExp = (Tipo) stmt.getExpresion().accept(this);

        // verificar que el tipo sea valido para switch
        if (tipoExp != null && !esTipoEntero(tipoExp) && !esTipoCaracter(tipoExp) && !esTipoTexto(tipoExp)) {
            agregarError(stmt, "tipo no valido para switch");
        }

        nivelSwitch++;
        boolean todosRetornan = true;

        // analizar cada caso
        for (NodoASTZetariano caso : stmt.getCasos()) {
            entrarAmbito("caso-switch");
            FlujoControl f = (FlujoControl) caso.accept(this);
            salirAmbito();

            // verificar si el caso puede continuar
            if (f.puedeContinuar()) {
                todosRetornan = false;
            }
        }

        // analizar el default si existe
        if (stmt.getCasoDefecto() != null) {
            entrarAmbito("caso-default");
            FlujoControl f = (FlujoControl) stmt.getCasoDefecto().accept(this);
            salirAmbito();

            // verificar si el default puede continuar
            if (f.puedeContinuar()) {
                todosRetornan = false;
            }

        } else {
            // sin default siempre puede continuar
            todosRetornan = false;
        }

        nivelSwitch--;

        // devolver el flujo correspondiente
        FlujoControl resultado = new FlujoControl();
        if (todosRetornan) {
            resultado.marcarRetorno();
        } else {
            resultado.marcarContinuacion();
        }
        return resultado;
    }

    @Override
    public Object visitarCasoSwitch(CasoSwitch caso) {
        // analizar las instrucciones del caso
        return analizarInstrucciones(caso.getInstrucciones());
    }

    @Override
    public Object visitarCasoDefault(CasoDefault caso) {
        // analizar las instrucciones del caso por defecto
        return analizarInstrucciones(caso.getInstrucciones());
    }

    // ==================== CICLOS ====================

    @Override
    public Object visitarCicloFor(CicloFor ciclo) {
        // entrar al ciclo y al ambito
        nivelCiclos++;
        entrarAmbito("bloque-for");

        // visitar la inicializacion si existe
        if (ciclo.getInicializacion() != null) {
            ciclo.getInicializacion().accept(this);
        }

        // validar la condicion del ciclo
        if (ciclo.getCondicion() != null) {
            Tipo tipoCond = (Tipo) ciclo.getCondicion().accept(this);

            if (tipoCond != null && !esTipoBooleano(tipoCond)) {
                agregarError(ciclo.getCondicion(), "la condicion debe ser booleana");
            }
        }

        // visitar el paso si existe
        if (ciclo.getPaso() != null) {
            ciclo.getPaso().accept(this);
        }

        // visitar el cuerpo del ciclo
        if (ciclo.getBloque() != null) {
            ciclo.getBloque().accept(this);
        }

        // salir del ambito y del ciclo
        salirAmbito();
        nivelCiclos--;

        // despues de un for siempre se puede continuar
        return new FlujoControl();
    }

    @Override
    public Object visitarCicloWhile(CicloWhile ciclo) {
        // entrar al ciclo y al ambito
        nivelCiclos++;
        entrarAmbito("bloque-while");

        // validar la condicion del ciclo
        if (ciclo.getCondicion() != null) {
            Tipo tipoCond = (Tipo) ciclo.getCondicion().accept(this);

            if (tipoCond != null && !esTipoBooleano(tipoCond)) {
                agregarError(ciclo.getCondicion(), "la condicion debe ser booleana");
            }
        }

        // visitar el cuerpo del ciclo
        if (ciclo.getBloque() != null) {
            ciclo.getBloque().accept(this);
        }

        // salir del ambito y del ciclo
        salirAmbito();
        nivelCiclos--;

        return new FlujoControl();
    }

    @Override
    public Object visitarCicloDoWhile(CicloDoWhile ciclo) {
        // entrar al ciclo y al ambito
        nivelCiclos++;
        entrarAmbito("bloque-do-while");

        // visitar el cuerpo del ciclo
        if (ciclo.getBloque() != null) {
            ciclo.getBloque().accept(this);
        }

        // validar la condicion del ciclo
        if (ciclo.getCondicion() != null) {
            Tipo tipoCond = (Tipo) ciclo.getCondicion().accept(this);

            if (tipoCond != null && !esTipoBooleano(tipoCond)) {
                agregarError(ciclo.getCondicion(), "la condicion debe ser booleana");
            }
        }

        // salir del ambito y del ciclo
        salirAmbito();
        nivelCiclos--;

        return new FlujoControl();
    }

    // ==================== INIT Y PASO DEL FOR ====================

    @Override
    public Object visitarInitForDecl(InitForDecl init) {
        // resolver el tipo de la declaracion
        Tipo tipo = obtenerTipo(init.getTipo());

        // reportar error si el tipo no existe
        if (tipo == null) {
            agregarError(init, "tipo no definido: '" + extraerNombreTipo(init.getTipo()) + "'");
            return null;
        }

        // declarar la variable
        if (!declararVariable(init.getIdentificador(), tipo, init)) {
            return null;
        }

        // visitar la expresion de inicializacion si existe
        if (init.getExpresion() != null) {
            Tipo tipoExp = (Tipo) init.getExpresion().accept(this);

            // verificar la compatibilidad del tipo
            if (tipoExp != null && !esTipoCompatible(tipoExp, tipo)) {
                agregarError(init, "tipo incompatible en inicializacion");
            }
        }
        return null;
    }

    @Override
    public Object visitarInitForAsig(InitForAsig init) {
        // visitar la variable destino
        Tipo tipoVar = (Tipo) init.getVariable().accept(this);

        // verificar si la variable existe
        if (tipoVar == null) {
            return null;
        }

        // visitar la expresion de inicializacion si existe
        if (init.getExpresion() != null) {
            Tipo tipoExp = (Tipo) init.getExpresion().accept(this);

            // verificar la compatibilidad del tipo
            if (tipoExp != null && !esTipoCompatible(tipoExp, tipoVar)) {
                agregarError(init, "tipo incompatible en inicializacion");
            }
        }

        return null;
    }

    @Override
    public Object visitarPasoForExpr(PasoForExpr paso) {

        // visitar la expresion del paso si existe
        if (paso.getExpresion() != null) {
            paso.getExpresion().accept(this);
        }

        return null;
    }

    @Override
    public Object visitarPasoForAsig(PasoForAsig paso) {
        // visitar la variable destino
        Tipo tipoVar = (Tipo) paso.getVariable().accept(this);

        // verificar si la variable existe
        if (tipoVar == null) {
            return null;
        }

        // visitar la expresion del paso si existe
        if (paso.getExpresion() != null) {
            Tipo tipoExp = (Tipo) paso.getExpresion().accept(this);

            // verificar la compatibilidad del tipo
            if (tipoExp != null && !esTipoCompatible(tipoExp, tipoVar)) {
                agregarError(paso, "tipo incompatible en paso");
            }
        }

        return null;
    }

    // ==================== EXPRESIONES ====================

    @Override
    public Object visitarExprParentesis(ExprParentesis expr) {

        // verificar si la expresion es nula
        if (expr.getExpresion() == null) {
            return null;
        }

        // visitar la expresion interna
        return expr.getExpresion().accept(this);
    }

    @Override
    public Object visitarExprInstanciaObjeto(ExprInstanciaObjeto expr) {
        // resolver el tipo de la clase a instanciar
        Tipo tipo = obtenerTipo(expr.getNombreClase());

        // reportar error si el tipo no existe
        if (tipo == null) {
            agregarError(expr, "tipo '" + expr.getNombreClase() + "' no definido");
            return null;
        }

        // validar los argumentos contra los constructores
        List<Tipo> tiposArgs = new ArrayList<>();
        if (expr.getArgumentos() != null) {
            ListaExpresiones lista = (ListaExpresiones) expr.getArgumentos();
            for (NodoASTZetariano arg : lista.getExpresiones()) {
                Tipo t = (Tipo) arg.accept(this);
                tiposArgs.add(t);
            }
        }

        // buscar un constructor compatible
        boolean encontrado = false;
        if (ambitoClase != null) {
            List<Simbolo> constructores = ambitoClase.buscarConstructores(tipo.getNombre());
            for (Simbolo c : constructores) {
                if (firmaCompatible(c.getTiposParametros(), tiposArgs)) {
                    encontrado = true;
                    break;
                }
            }
        }

        // reportar error si no hay constructor compatible
        if (!encontrado) {
            agregarError(expr, "no existe un constructor compatible para '" + expr.getNombreClase() + "'");
        }

        return tipo;
    }

    @Override
    public Object visitarExprInstanciaArreglo(ExprInstanciaArreglo expr) {
        // resolver el tipo base del arreglo
        Tipo tipoBase = obtenerTipo(expr.getTipo());

        // reportar error si el tipo no existe
        if (tipoBase == null) {
            agregarError(expr, "tipo no definido: '" + extraerNombreTipo(expr.getTipo()) + "'");
            return null;
        }

        // validar que las dimensiones sean enteros
        for (NodoASTZetariano dim : expr.getDimensiones()) {
            Tipo tipoDim = (Tipo) dim.accept(this);

            // verificar si la dimension es entera
            if (tipoDim != null && !esTipoEntero(tipoDim)) {
                agregarError(dim, "el tamano del arreglo debe ser un entero");
            }
        }

        // construir el tipo array con sus dimensiones
        Tipo tipoArray = crearTipoArray(tipoBase, expr.getDimensiones().size());
        return tipoArray;
    }

    @Override
    public Object visitarExprLlamadaFuncion(ExprLlamadaFuncion expr) {
        // analizar argumentos primero para tener sus tipos
        List<Tipo> tiposArgs = new ArrayList<>();

        if (expr.getArgumentos() != null) {
            ListaExpresiones lista = (ListaExpresiones) expr.getArgumentos();

            for (NodoASTZetariano arg : lista.getExpresiones()) {
                Tipo t = (Tipo) arg.accept(this);
                tiposArgs.add(t);
            }

        }

        // buscar el metodo por nombre en la clase
        List<Simbolo> candidatos = new ArrayList<>();
        if (ambitoClase != null) {
            candidatos.addAll(ambitoClase.buscarMetodos(expr.getNombre()));
        }

        // reportar error si no hay candidatos
        if (candidatos.isEmpty()) {
            agregarError(expr, "metodo '" + expr.getNombre() + "' no definido");
            return null;
        }

        // buscar el candidato cuya firma sea compatible
        for (Simbolo c : candidatos) {
            if (firmaCompatible(c.getTiposParametros(), tiposArgs)) {
                return c.getTipo();
            }
        }

        // reportar error si ningun candidato coincide
        agregarError(expr, "no existe un metodo '" + expr.getNombre() + "' con argumentos compatibles");
        return null;
    }

    @Override
    public Object visitarExprLlamadaMetodo(ExprLlamadaMetodo expr) {
        // visitar el objeto del metodo
        Tipo tipoObjeto = (Tipo) expr.getObjeto().accept(this);

        // verificar si el tipo es nulo
        if (tipoObjeto == null) {
            return null;
        }

        // buscar metodos del objeto en la clase
        List<Simbolo> metodos = new ArrayList<>();
        if (ambitoClase != null) {
            metodos = ambitoClase.buscarMetodos(expr.getNombre());
        }

        // reportar error si no hay metodos
        if (metodos.isEmpty()) {
            agregarError(expr, "metodo '" + expr.getNombre() + "' no existe");
            return null;
        }

        // analizar argumentos
        List<Tipo> tiposArgs = new ArrayList<>();
        if (expr.getArgumentos() != null) {
            ListaExpresiones lista = (ListaExpresiones) expr.getArgumentos();
            for (NodoASTZetariano arg : lista.getExpresiones()) {
                Tipo t = (Tipo) arg.accept(this);
                tiposArgs.add(t);
            }
        }

        // buscar el candidato compatible
        for (Simbolo c : metodos) {
            if (firmaCompatible(c.getTiposParametros(), tiposArgs)) {
                return c.getTipo();
            }
        }

        // reportar error si ningun candidato coincide
        agregarError(expr, "no existe un metodo '" + expr.getNombre() + "' con argumentos compatibles");
        return null;
    }

    @Override
    public Object visitarExprAccesoArray(ExprAccesoArray expr) {
        // visitar el arreglo del acceso
        Tipo tipoArray = (Tipo) expr.getObjeto().accept(this);

        // verificar si el tipo es nulo
        if (tipoArray == null) {
            return null;
        }

        // verificar si el tipo no es un arreglo
        if (tipoArray.getDimension() <= 0) {
            agregarError(expr, "se esperaba un arreglo");
            return null;
        }

        // validar el indice
        Tipo tipoIndice = (Tipo) expr.getIndice().accept(this);

        // verificar si el indice es entero
        if (tipoIndice != null && !esTipoEntero(tipoIndice)) {
            agregarError(expr, "el indice debe ser un entero");
        }

        return tipoArray.getTipoBase();
    }

    @Override
    public Object visitarExprAccesoMiembro(ExprAccesoMiembro expr) {
        // visitar el objeto del acceso
        Tipo tipoObjeto = (Tipo) expr.getObjeto().accept(this);

        // verificar si el tipo es nulo
        if (tipoObjeto == null) {
            return null;
        }

        // buscar el campo en el tipo
        Simbolo campo = tipoObjeto.buscarCampo(expr.getMiembro());

        // reportar error si el campo no existe
        if (campo == null) {
            agregarError(expr, "el campo '" + expr.getMiembro() + "' no existe en '" + tipoObjeto.getNombre() + "'");
            return null;
        }

        return campo.getTipo();
    }

    @Override
    public Object visitarExprPostIncremento(ExprPostIncremento expr) {
        // visitar la variable del incremento
        Tipo tipoVar = (Tipo) expr.getVariable().accept(this);

        // verificar si la variable es numerica
        if (tipoVar != null && !esTipoNumerico(tipoVar)) {
            agregarError(expr, "incremento solo permitido sobre tipos numericos");
        }

        return tipoVar;
    }

    @Override
    public Object visitarExprPostDecremento(ExprPostDecremento expr) {
        // visitar la variable del decremento
        Tipo tipoVar = (Tipo) expr.getVariable().accept(this);

        // verificar si la variable es numerica
        if (tipoVar != null && !esTipoNumerico(tipoVar)) {
            agregarError(expr, "decremento solo permitido sobre tipos numericos");
        }

        return tipoVar;
    }

    @Override
    public Object visitarExprNegativa(ExprNegativa expr) {
        // visitar la expresion interna
        Tipo tipoExp = (Tipo) expr.getExpresion().accept(this);

        // verificar si la expresion es numerica
        if (tipoExp != null && !esTipoNumerico(tipoExp)) {
            agregarError(expr, "negacion aritmetica solo permitida sobre numeros");
        }

        return tipoExp;
    }

    @Override
    public Object visitarExprNegada(ExprNegada expr) {
        // visitar la expresion interna
        Tipo tipoExp = (Tipo) expr.getExpresion().accept(this);

        // verificar si la expresion es booleana
        if (tipoExp != null && !esTipoBooleano(tipoExp)) {
            agregarError(expr, "negacion logica solo permitida sobre booleanos");
        }

        return ambitoGlobal.buscarTipo("boolean");
    }

    @Override
    public Object visitarExprMultiplicacionDivisionModulo(ExprMultiplicacionDivisionModulo expr) {
        // visitar ambos operandos
        Tipo tIzq = (Tipo) expr.getOperandoIzquierdo().accept(this);
        Tipo tDer = (Tipo) expr.getOperandoDerecho().accept(this);

        // verificar si el operando izquierdo es numerico
        if (tIzq != null && !esTipoNumerico(tIzq)) {
            agregarError(expr.getOperandoIzquierdo(), "operando izquierdo debe ser numerico");
        }

        // verificar si el operando derecho es numerico
        if (tDer != null && !esTipoNumerico(tDer)) {
            agregarError(expr.getOperandoDerecho(), "operando derecho debe ser numerico");
        }

        return tipoPromovido(tIzq, tDer);
    }

    @Override
    public Object visitarExprSumaResta(ExprSumaResta expr) {
        // visitar ambos operandos
        Tipo tIzq = (Tipo) expr.getOperandoIzquierdo().accept(this);
        Tipo tDer = (Tipo) expr.getOperandoDerecho().accept(this);

        // devolver nulo si algun operando no tiene tipo
        if (tIzq == null || tDer == null) {
            return null;
        }

        String op = expr.getOperador();

        // si es concatenacion de String
        if ("+".equals(op) && (esTipoTexto(tIzq) || esTipoTexto(tDer))) {
            return ambitoGlobal.buscarTipo("String");
        }

        // verificar si ambos operandos son numericos
        if (!esTipoNumerico(tIzq) || !esTipoNumerico(tDer)) {
            agregarError(expr, "operacion aritmetica solo permitida entre numericos");
            return null;
        }

        return tipoPromovido(tIzq, tDer);
    }

    @Override
    public Object visitarExprRelacional(ExprRelacional expr) {
        // visitar ambos operandos
        Tipo tIzq = (Tipo) expr.getOperandoIzquierdo().accept(this);
        Tipo tDer = (Tipo) expr.getOperandoDerecho().accept(this);

        // verificar la compatibilidad de los tipos
        if (tIzq != null && tDer != null) {
            boolean numericos = esTipoNumerico(tIzq) && esTipoNumerico(tDer);
            boolean textos = esTipoTexto(tIzq) && esTipoTexto(tDer);
            boolean bools = esTipoBooleano(tIzq) && esTipoBooleano(tDer);

            // reportar si los tipos son incompatibles
            if (!numericos && !textos && !bools) {
                agregarError(expr, "tipos incompatibles para comparacion");
            }
        }

        return ambitoGlobal.buscarTipo("boolean");
    }

    @Override
    public Object visitarExprAnd(ExprAnd expr) {
        // visitar ambos operandos
        Tipo tIzq = (Tipo) expr.getOperandoIzquierdo().accept(this);
        Tipo tDer = (Tipo) expr.getOperandoDerecho().accept(this);

        // verificar si el operando izquierdo es booleano
        if (tIzq != null && !esTipoBooleano(tIzq)) {
            agregarError(expr.getOperandoIzquierdo(), "operando izquierdo debe ser booleano");
        }
        // verificar si el operando derecho es booleano
        if (tDer != null && !esTipoBooleano(tDer)) {
            agregarError(expr.getOperandoDerecho(), "operando derecho debe ser booleano");
        }

        return ambitoGlobal.buscarTipo("boolean");
    }

    @Override
    public Object visitarExprOr(ExprOr expr) {
        // visitar ambos operandos
        Tipo tIzq = (Tipo) expr.getOperandoIzquierdo().accept(this);
        Tipo tDer = (Tipo) expr.getOperandoDerecho().accept(this);

        // verificar si el operando izquierdo es booleano
        if (tIzq != null && !esTipoBooleano(tIzq)) {
            agregarError(expr.getOperandoIzquierdo(), "operando izquierdo debe ser booleano");
        }
        // verificar si el operando derecho es booleano
        if (tDer != null && !esTipoBooleano(tDer)) {
            agregarError(expr.getOperandoDerecho(), "operando derecho debe ser booleano");
        }

        return ambitoGlobal.buscarTipo("boolean");
    }

    @Override
    public Object visitarExprTernario(ExprTernario expr) {
        // validar la condicion del ternario
        Tipo tipoCond = (Tipo) expr.getCondicion().accept(this);
        if (tipoCond != null && !esTipoBooleano(tipoCond)) {
            agregarError(expr.getCondicion(), "la condicion del ternario debe ser booleana");
        }

        // visitar ambas ramas del ternario
        Tipo tipoVerdadero = (Tipo) expr.getValorVerdadero().accept(this);
        Tipo tipoFalso = (Tipo) expr.getValorFalso().accept(this);

        // ambos valores deben ser compatibles
        if (tipoVerdadero != null && tipoFalso != null) {
            if (!esTipoCompatible(tipoVerdadero, tipoFalso) && !esTipoCompatible(tipoFalso, tipoVerdadero)) {
                agregarError(expr, "tipos incompatibles en el ternario");
            }
        }

        return tipoPromovido(tipoVerdadero, tipoFalso);
    }

    @Override
    public Object visitarExprPrimitivo(ExprPrimitivo expr) {

        // visitar el valor interno si existe
        if (expr.getValor() != null) {
            return expr.getValor().accept(this);
        }

        return null;
    }

    @Override
    public Object visitarValorPrimitivo(ValorPrimitivo valor) {
        // verificar si el tipo es nulo
        if (valor.getTipoDato() == null) {
            return null;
        }

        // mapear cada tipo primitivo a su tipo del lenguaje
        switch (valor.getTipoDato()) {
            case ENTERO:
                return ambitoGlobal.buscarTipo("int");
            case DECIMAL:
                return ambitoGlobal.buscarTipo("double");
            case CADENA:
                return ambitoGlobal.buscarTipo("String");
            case CARACTER:
                return ambitoGlobal.buscarTipo("char");
            case BOOLEANO:
                return ambitoGlobal.buscarTipo("boolean");
            case NULO:
                return ambitoGlobal.buscarTipo("null");
            case IDENTIFICADOR:

                // es una referencia a variable
                Simbolo s = ambitoActual.buscarSimbolo(valor.getValor());

                // buscar en el ambito de la clase si no se encontro
                if (s == null && ambitoClase != null) {
                    s = ambitoClase.buscarSimbolo(valor.getValor());
                }

                // devolver el tipo si el simbolo existe
                if (s != null) {
                    return s.getTipo();
                }

                // reportar error si la variable no existe
                agregarError(valor, "variable '" + valor.getValor() + "' no declarada");
                return null;

            default:
                return null;
        }
    }

    @Override
    public Object visitarListaExpresiones(ListaExpresiones lista) {
        // recorrer cada expresion de la lista
        for (NodoASTZetariano expr : lista.getExpresiones()) {
            expr.accept(this);
        }

        return null;
    }

    // ==================== AUXILIARES DE TIPOS ====================

    // verificar si un tipo es numerico
    private boolean esTipoNumerico(Tipo tipo) {
        // verificar si el tipo es nulo
        if (tipo == null) {
            return false;
        }

        // aceptar int double y char
        String nombre = tipo.getNombre();
        return "int".equals(nombre) || "double".equals(nombre) || "char".equals(nombre);
    }

    // verificar si un tipo es entero
    private boolean esTipoEntero(Tipo tipo) {
        return tipo != null && "int".equals(tipo.getNombre());
    }

    // verificar si un tipo es caracter
    private boolean esTipoCaracter(Tipo tipo) {
        return tipo != null && "char".equals(tipo.getNombre());
    }

    // verificar si un tipo es booleano
    private boolean esTipoBooleano(Tipo tipo) {
        return tipo != null && "boolean".equals(tipo.getNombre());
    }

    // verificar si un tipo es texto
    private boolean esTipoTexto(Tipo tipo) {
        return tipo != null && "String".equals(tipo.getNombre());
    }

    // calcular el tipo promovido entre dos tipos
    private Tipo tipoPromovido(Tipo a, Tipo b) {

        // devolver b si a es nulo
        if (a == null) {
            return b;
        }

        // devolver a si b es nulo
        if (b == null) {
            return a;
        }

        // promover a double si alguno lo es
        if ("double".equals(a.getNombre()) || "double".equals(b.getNombre())) {
            return ambitoGlobal.buscarTipo("double");
        }

        // conservar int si ambos son int
        if ("int".equals(a.getNombre()) && "int".equals(b.getNombre())) {
            return ambitoGlobal.buscarTipo("int");
        }
        return a;
    }

    // verificar si un tipo origen es compatible con un destino
    private boolean esTipoCompatible(Tipo origen, Tipo destino) {

        // aceptar si alguno es nulo
        if (origen == null || destino == null) {
            return true;
        }

        // aceptar si los nombres coinciden
        if (origen.getNombre().equals(destino.getNombre())) {
            return true;
        }

        // int a double permitido
        if ("int".equals(origen.getNombre()) && "double".equals(destino.getNombre())) {
            return true;
        }

        // char a int permitido
        if ("char".equals(origen.getNombre()) && "int".equals(destino.getNombre())) {
            return true;
        }

        return false;
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
            if (!esTipoCompatible(argumentos.get(i), parametros.get(i))) {
                return false;
            }
        }

        return true;
    }
}