package org.jrg.analisis.pigLatin.semantico;

import java.util.ArrayList;
import java.util.List;

import org.jrg.model.base.TipoPrimitivo;
import org.jrg.model.ast.pigLatin.Bloque;
import org.jrg.model.ast.pigLatin.ElementoImprimir;
import org.jrg.model.ast.pigLatin.ListaAtributosInstancia;
import org.jrg.model.ast.pigLatin.ListaExpresiones;
import org.jrg.model.ast.pigLatin.Programa;
import org.jrg.model.ast.pigLatin.RutaImportacion;
import org.jrg.model.ast.pigLatin.SeccionGlobalVariables;
import org.jrg.model.ast.pigLatin.SeccionImportaciones;
import org.jrg.model.ast.pigLatin.SeccionMaior;
import org.jrg.model.ast.pigLatin.TipoDato;
import org.jrg.model.ast.pigLatin.ValorPrimitivo;
import org.jrg.model.ast.pigLatin.asignacion.AsignacionGeneral;
import org.jrg.model.ast.pigLatin.atributo_instancia.CampoConNombre;
import org.jrg.model.ast.pigLatin.atributo_instancia.CampoPosicional;
import org.jrg.model.ast.pigLatin.base.LatinusAstVisitor;
import org.jrg.model.ast.pigLatin.base.NodoAST;
import org.jrg.model.ast.pigLatin.ciclo.CicloDum;
import org.jrg.model.ast.pigLatin.ciclo.CicloFacere;
import org.jrg.model.ast.pigLatin.ciclo.CicloPer;
import org.jrg.model.ast.pigLatin.condicional.StatementSi;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclArrayConDatos;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclArrayEstructura;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclArraySinDatos;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclBooleanaImplicita;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclConTipoYValor;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclEstructuraConValores;
import org.jrg.model.ast.pigLatin.declaracion_variable.DeclObjetoNovus;
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
import org.jrg.model.ast.pigLatin.variable_asignable.ValorAsignableArray;
import org.jrg.model.ast.pigLatin.variable_asignable.ValorAsignableMiembroEstructura;
import org.jrg.model.ast.pigLatin.variable_asignable.ValorAsignableSimple;
import org.jrg.model.cuarteta.Cuarteta;
import org.jrg.model.resultado.ResultadoAnalisis;
import org.jrg.model.semantico.CategoriaSimbolo;
import org.jrg.model.semantico.Simbolo;
import org.jrg.model.semantico.Tipo;
import org.jrg.service.compiler.GestorImports;
import org.jrg.service.error.RecolectorErrores;
import org.jrg.model.base.FlujoControl;

// definir el analizador semantico para el lenguaje Pig Latin
public class AnalizadorSemanticoPigLatin implements LatinusAstVisitor<Object> {



    // almacenar el contexto semantico del analisis
    private final ContextoSemanticoPigLatin contexto;
    // recolector de errores para pasarlo al gestor de imports
    private final RecolectorErrores recolectorErrores;
    // ruta base del proyecto para resolver imports
    private String rutaBase;
    // gestor de imports creado bajo demanda
    private GestorImports gestorImports;
    // cuartetas importadas desde otros lenguajes
    private final List<Cuarteta> cuartetasImportadas;





    /**
     * Crear el analizador semantico para el lenguaje Pig Latin.
     */
    public AnalizadorSemanticoPigLatin(RecolectorErrores recolectorErrores) {

        // guardar el recolector original o crear uno nuevo si viene nulo :D
        if (recolectorErrores == null) {
            this.recolectorErrores = new RecolectorErrores();
        } else {
            this.recolectorErrores = recolectorErrores;
        }
        this.contexto = new ContextoSemanticoPigLatin(this.recolectorErrores);
        this.cuartetasImportadas = new ArrayList<>();
    }

    /**
     * Analizar el programa Pig Latin de forma semantica.
     */
    public void analizar(Programa programa) {
        analizar(programa, null);
    }

    /**
     * Analizar el programa Pig Latin con contexto de proyecto para imports.
     */
    public void analizar(Programa programa, String rutaBase) {
        this.rutaBase = rutaBase;
        this.contexto.iniciar();

        // crear el gestor de imports solo si hay ruta base
        if (rutaBase != null && !rutaBase.isEmpty()) {
            this.gestorImports = new GestorImports(rutaBase, this.recolectorErrores);
        } else {
            this.gestorImports = null;
        }

        if (programa != null) {
            programa.accept(this);
        }

        // copiar las cuartetas acumuladas por el gestor :D
        if (this.gestorImports != null) {
            this.cuartetasImportadas.addAll(this.gestorImports.getCuartetasAcumuladas());
        }
    }

    /**
     * Obtener las cuartetas importadas desde otros lenguajes.
     */
    public List<Cuarteta> getCuartetasImportadas() {
        // devolver las cuartetas importadas
        return this.cuartetasImportadas;
    }

    /**
     * Obtener el contexto semantico usado durante el analisis.
     */
    public ContextoSemanticoPigLatin obtenerContexto() {
        // devolver el contexto semantico
        return this.contexto;
    }

    /**
     * Obtener todos los simbolos registrados durante el analisis.
     */
    public List<Simbolo> obtenerSimbolos() {
        return this.contexto.obtenerSimbolos();
    }

    /**
     * Obtener todos los tipos registrados durante el analisis.
     */
    public List<Tipo> obtenerTipos() {
        return this.contexto.obtenerTipos();
    }

    // ==================== AUXILIARES DE TIPOS ====================

    // resolver el tipo a partir de un nodo TipoDato
    private Tipo resolverTipoTipoDato(NodoAST tipoNodo) {

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

        // obtener el nombre del tipo
        String nombre = tipoDato.getTipo();

        // verificar si el nombre es nulo o vacio
        if (nombre == null || nombre.isEmpty()) {
            return null;
        }

        // buscar el tipo primitivo en el contexto
        Tipo primitivo = this.contexto.tipoPrimitivo(nombre);
        if (primitivo != null) {
            return primitivo;
        }

        // buscar el tipo no primitivo existente en el contexto
        Tipo existente = this.contexto.tipoNoPrimitivoExistente(nombre);
        if (existente != null) {
            return existente;
        }

        // registrar el tipo no primitivo importado
        return this.contexto.registrarTipoNoPrimitivoImportado(nombre, tipoDato);
    }

    // resolver el tipo a partir de un nombre
    private Tipo resolverTipoPorNombre(String nombre, NodoAST nodo) {

        // verificar si el nombre es nulo o vacio
        if (nombre == null || nombre.isEmpty()) {
            return null;
        }

        // buscar el tipo primitivo en el contexto :D
        Tipo primitivo = this.contexto.tipoPrimitivo(nombre);
        if (primitivo != null) {
            return primitivo;
        }

        // buscar el tipo no primitivo existente en el contexto
        Tipo existente = this.contexto.tipoNoPrimitivoExistente(nombre);
        if (existente != null) {
            return existente;
        }

        // registrar el tipo no primitivo importado
        return this.contexto.registrarTipoNoPrimitivoImportado(nombre, nodo);
    }

    // extraer el tipo de un resultado de expresion
    private Tipo extraerTipoDeExpresion(Object resultado) {

        // verificar si el resultado es un Tipo
        if (resultado instanceof Tipo) {
            return (Tipo) resultado;
        }

        return null;

    }

    // extraer el valor entero de un nodo si es una constante literal
    private Integer extraerEnteroConstante(NodoAST nodo) {

        // verificar si el nodo es nulo
        if (nodo == null) {
            return null;
        }

        // caso literal directo: 5
        if (nodo instanceof ExprPrimitivo) {
            ExprPrimitivo expr = (ExprPrimitivo) nodo;

            // verificar si el valor es primitivo
            if (expr.getValor() instanceof ValorPrimitivo) {
                ValorPrimitivo valor = (ValorPrimitivo) expr.getValor();

                // verificar si el tipo es entero
                if (valor.getTipoDato() == TipoPrimitivo.ENTERO) {
                    try {

                        // convertir el valor a entero
                        return Integer.parseInt(valor.getValor());

                    } catch (NumberFormatException e) {
                        return null;
                    }
                }
            }

            return null;

        }

        // caso referencia a constante conocida: tam
        if (nodo instanceof ValorAsignableSimple) {
            String nombre = ((ValorAsignableSimple) nodo).getIdentificador();

            // buscar el simbolo en el ambito actual
            Simbolo simbolo = this.contexto.ambitoActual().buscarSimbolo(nombre);

            // verificar si el simbolo tiene valor
            if (simbolo != null && simbolo.getValor() != null) {
                Object valor = simbolo.getValor().getValor();

                // verificar si el valor es entero
                if (valor instanceof Integer) {
                    return (Integer) valor;
                }

            }
            return null;

        }

        return null;
    }

    // ==================== DECLARACIONES SEGURAS ====================

    // declarar variable buscando en TODOS los ambitos padres :D
    // si existe en cualquier padre se considera error de redeclaracion
    // asi no se permite lo del i declarado multiples veces en for anidados :D
    private boolean declararVariableSeguro(NodoAST nodo, String nombre, Tipo tipo, CategoriaSimbolo categoria, Integer tamano) {

        // verificar si el nombre es nulo o vacio
        if (nombre == null || nombre.isEmpty()) {
            this.contexto.agregarError(nodo, "declaracion sin identificador");
            return false;
        }

        // buscar el simbolo en el ambito actual
        Simbolo existente = this.contexto.ambitoActual().buscarSimbolo(nombre);

        // verificar si ya existe
        if (existente != null) {
            this.contexto.agregarError(nodo, "la variable '" + nombre + "' ya esta declarada en un ambito padre");
            return false;
        }

        // declarar la variable en el contexto
        return this.contexto.declarar(nodo, nombre, tipo, categoria, tamano);
    }

    // ==================== ALCANZABILIDAD :D ====================

    // recorrer una lista de instrucciones combinando sus flujos
    // reportar codigo inalcanzable una sola vez por bloque :3
    private FlujoControl analizarInstrucciones(List<NodoAST> instrucciones) {

        FlujoControl flujo = new FlujoControl();

        // verificar si la lista es nula
        if (instrucciones == null) {
            return flujo;
        }

        boolean primeraInalcanzable = false;

        // recorrer cada instruccion
        for (NodoAST inst : instrucciones) {

            // verificar si el flujo puede continuar
            if (!flujo.puedeContinuar()) {

                // reportar codigo inalcanzable solo una vez
                if (!primeraInalcanzable) {
                    this.contexto.agregarError(inst, "codigo inalcanzable");
                    primeraInalcanzable = true;
                }

                continue;
            }

            // verificar si la instruccion es nula
            if (inst == null) {
                continue;
            }

            // entrar en la instruccion

            Object resultado = inst.accept(this);

            // actualizar el flujo si el resultado es FlujoControl
            if (resultado instanceof FlujoControl) {
                flujo = (FlujoControl) resultado;
            }

        }

        return flujo;
    }

    // visitar un bloque hijo aislado y devolver su flujo
    private FlujoControl visitarBloqueAislado(NodoAST nodo) {

        // verificar si el nodo es nulo
        if (nodo == null) {
            return new FlujoControl();
        }

        // entrar en el nodo
        Object resultado = nodo.accept(this);

        // devolver el flujo si el resultado es FlujoControl
        if (resultado instanceof FlujoControl) {
            return (FlujoControl) resultado;
        }

        return new FlujoControl();
    }

    // validar que una condicion sea booleana
    private void validarCondicionBooleana(NodoAST cond) {

        // verificar si la condicion es nula
        if (cond == null) {
            return;
        }

        // entrar en la condicion
        Object tipoObj = cond.accept(this);

        // verificar si el resultado es un Tipo
        if (tipoObj instanceof Tipo) {

            Tipo tipoCond = (Tipo) tipoObj;

            // verificar si el tipo no es booleano
            if (!this.contexto.esBooleano(tipoCond)) {
                this.contexto.agregarError(cond, "la condicion debe ser booleana");
            }

        }

    }

    // ==================== PROGRAMA ====================

    @Override
    public Object visitPrograma(Programa programa) {

        // visitar la seccion de importaciones si existe :D
        if (programa.getSeccionImportaciones() != null) {
            programa.getSeccionImportaciones().accept(this);
        }

        // visitar la seccion de variables globales si existe :D
        if (programa.getSeccionGlobalVariables() != null) {
            programa.getSeccionGlobalVariables().accept(this);
        }

        // visitar la seccion maior si existe :D
        if (programa.getSeccionMaior() != null) {
            programa.getSeccionMaior().accept(this);
        }

        return new FlujoControl();
    }

    @Override
    public Object visitSeccionImportaciones(SeccionImportaciones seccion) {

        // verificar si hay importaciones
        if (seccion.getImportaciones() != null) {

            // recorrer cada importacion
            for (NodoAST imp : seccion.getImportaciones()) {

                // verificar si la importacion es nula
                if (imp != null) {

                    // entrar en la importacion
                    imp.accept(this);
                }
            }
        }
        return new FlujoControl();
    }

    @Override
    public Object visitRutaImportacion(RutaImportacion ruta) {
        // si no hay gestor de imports no se puede procesar
        if (this.gestorImports == null) {
            return new FlujoControl();
        }

        // procesar el import y obtener el resultado del archivo importado
        ResultadoAnalisis resultado = this.gestorImports.procesarImport(ruta.getIdentificadores(), ruta.getLinea(), ruta.getColumna());

        if (resultado == null) {
            return new FlujoControl();
        }

        // propagar los errores del archivo importado al recolector actual
        for (int i = 0; i < resultado.getErrores().size(); i++) {
            org.jrg.model.error.ErrorCompilacion e = resultado.getErrores().get(i);
            this.recolectorErrores.agregar(e.getTipo(), e.getLinea(), e.getColumna(), e.getDescripcion());
        }

        // registrar los simbolos crudos importados en el contexto
        for (int i = 0; i < resultado.getSimbolosCrudos().size(); i++) {
            this.contexto.registrarSimboloImportado(resultado.getSimbolosCrudos().get(i));
        }

        // registrar los tipos crudos importados en el contexto
        for (int i = 0; i < resultado.getTiposCrudos().size(); i++) {
            this.contexto.registrarTipoImportado(resultado.getTiposCrudos().get(i));
        }

        return new FlujoControl();
    }

    @Override
    public Object visitSeccionGlobalVariables(SeccionGlobalVariables seccion) {

        // verificar si hay declaraciones
        if (seccion.getDeclaraciones() != null) {

            // recorrer cada declaracion, ver que no sea nula y entrar en la declaracion
            for (NodoAST decl : seccion.getDeclaraciones()) {
                if (decl != null) {
                    decl.accept(this);
                }
            }
        }

        return new FlujoControl();
    }

    @Override
    public Object visitSeccionMaior(SeccionMaior seccion) {
        // analizar las instrucciones de la seccion maior
        return analizarInstrucciones(seccion.getInstrucciones());
    }

    // ==================== TIPOS ====================

    @Override
    public Object visitTipoDato(TipoDato tipo) {
        return null;
    }

    // ==================== VALORES PRIMITIVOS ====================

    @Override
    public Object visitValorPrimitivo(ValorPrimitivo valor) {
        // obtener el tipo primitivo del valor
        TipoPrimitivo tp = valor.getTipoDato();

        // verificar si el tipo es nulo
        if (tp == null) {
            return null;
        }

        // seleccionar el tipo segun el valor
        switch (tp) {
            case ENTERO:
                return this.contexto.tipoPrimitivo("numerus");
            case DECIMAL:
                return this.contexto.tipoPrimitivo("decimalis");
            case CADENA:
                return this.contexto.tipoPrimitivo("textum");
            case CARACTER:
                return this.contexto.tipoPrimitivo("littera");
            case BOOLEANO:
                return this.contexto.tipoPrimitivo("bool");
            case IDENTIFICADOR:

                // resolver el simbolo del identificador
                Simbolo simbolo = this.contexto.resolver(valor.getValor(), valor);

                // devolver el tipo del simbolo si existe
                if (simbolo != null) {
                    return simbolo.getTipo();
                }
                return null;

            default:
                return null;
        }
    }

    // ==================== LISTAS ====================

    @Override
    public Object visitListaExpresiones(ListaExpresiones lista) {
        // verificar si hay expresiones
        if (lista.getExpresiones() != null) {

            // recorrer cada expresion y visitarla si no es nula :D
            for (NodoAST expr : lista.getExpresiones()) {

                if (expr != null) {
                    expr.accept(this);
                }
            }
        }

        return null;
    }

    @Override
    public Object visitListaAtributosInstancia(ListaAtributosInstancia lista) {
        // verificar si hay atributos
        if (lista.getAtributos() != null) {

            // recorrer cada atributo y visitarlos si no son nulos
            for (NodoAST attr : lista.getAtributos()) {

                if (attr != null) {
                    attr.accept(this);
                }

            }
        }
        return null;
    }

    @Override
    public Object visitCampoConNombre(CampoConNombre campo) {

        // visitar el valor si existe
        if (campo.getValor() != null) {
            campo.getValor().accept(this);
        }

        return null;
    }

    @Override
    public Object visitCampoPosicional(CampoPosicional campo) {

        // visitar el valor si existe
        if (campo.getValor() != null) {
            campo.getValor().accept(this);
        }

        return null;
    }

    // ==================== BLOQUES E IMPRESION ====================

    @Override
    public Object visitBloque(Bloque bloque) {

        // analizar las instrucciones del bloque
        return analizarInstrucciones(bloque.getInstrucciones());

    }

    @Override
    public Object visitElementoImprimir(ElementoImprimir elemento) {

        // visitar la expresion si existe
        if (elemento.getExpresion() != null) {
            elemento.getExpresion().accept(this);
        }

        return null;

    }

    // ==================== VARIABLES ASIGNABLES ====================

    @Override
    public Object visitValorAsignableSimple(ValorAsignableSimple valor) {

        // resolver el simbolo del identificador
        Simbolo simbolo = this.contexto.resolver(valor.getIdentificador(), valor);

        // devolver el tipo del simbolo si existe
        if (simbolo != null) {
            return simbolo.getTipo();
        }

        return null;
    }

    @Override
    public Object visitValorAsignableArray(ValorAsignableArray valor) {
        Tipo tipoBase = null;

        // visitar la base del arreglo si existe (o sea, el nombre xd)
        if (valor.getBase() != null) {
            Object resultado = valor.getBase().accept(this);
            tipoBase = extraerTipoDeExpresion(resultado);
        }

        // verificar si el tipo base es nulo
        if (tipoBase == null) {
            return null;
        }

        // verificar si el tipo base no es un arreglo
        if (tipoBase.getDimension() <= 0) {
            this.contexto.agregarError(valor, "se esperaba un arreglo");
            return null;
        }

        // visitar el indice si existe
        if (valor.getIndice() != null) {
            Object tipoIndiceObj = valor.getIndice().accept(this);
            Tipo tipoIndice = extraerTipoDeExpresion(tipoIndiceObj);

            // verificar si el indice no es entero
            if (tipoIndice != null && !this.contexto.esEntero(tipoIndice)) {
                this.contexto.agregarError(valor.getIndice(), "el indice debe ser entero");
            }

        }

        // devolver el tipo del elemento
        return this.contexto.tipoElemento(tipoBase);
    }

    @Override
    public Object visitValorAsignableMiembroEstructura(ValorAsignableMiembroEstructura valor) {
        Tipo tipoBase = null;

        // visitar el nombre del objeto o del struct si existe (o sea, en persona.nombre, visitar persona xd)
        if (valor.getBase() != null) {
            Object resultado = valor.getBase().accept(this);
            tipoBase = extraerTipoDeExpresion(resultado);
        }

        // verificar si el tipo base es nulo
        if (tipoBase == null) {
            return null;
        }

        // buscar el campo en el contexto
        Simbolo campo = this.contexto.campo(tipoBase, valor.getMiembro(), valor);

        // devolver el tipo del campo si existe
        if (campo != null) {
            return campo.getTipo();
        }

        return null;
    }

    // ==================== EXPRESIONES ====================

    @Override
    public Object visitExprParentesis(ExprParentesis expr) {

        // verificar si la expresion es nula
        if (expr.getExpresion() == null) {
            return null;
        }

        // entrar en la expresion
        return expr.getExpresion().accept(this);
    }

    @Override
    public Object visitExprPrimitivo(ExprPrimitivo expr) {

        // verificar si el valor es nulo
        if (expr.getValor() == null) {
            return null;
        }

        // entrar en el valor
        return expr.getValor().accept(this);
    }

    @Override
    public Object visitExprInstanciaObjeto(ExprInstanciaObjeto expr) {
        // obtener el nombre del tipo
        String nombreTipo = expr.getTipo();

        // resolver el tipo por nombre
        Tipo tipo = resolverTipoPorNombre(nombreTipo, expr);

        // visitar los argumentos si existen
        if (expr.getArgumentos() != null) {
            expr.getArgumentos().accept(this);
        }

        return tipo;
    }

    @Override
    public Object visitExprLlamadaFuncion(ExprLlamadaFuncion expr) {
        // analizar los argumentos primero
        List<Tipo> tiposArgs = new ArrayList<>();
        // los argumentos vienen envueltos en un ListaExpresiones
        List<NodoAST> argumentos = new ArrayList<>();

        if (expr.getArgumentos() instanceof ListaExpresiones) {
            argumentos = ((ListaExpresiones) expr.getArgumentos()).getExpresiones();
        }

        for (int i = 0; i < argumentos.size(); i++) {
            Object tipoObj = argumentos.get(i).accept(this);
            tiposArgs.add(extraerTipoDeExpresion(tipoObj));
        }

        // resolver la funcion en el ambito
        Simbolo funcion = this.contexto.ambitoActual().buscarSimbolo(expr.getNombre());
        if (funcion == null) {
            this.contexto.agregarError(expr, "funcion '" + expr.getNombre() + "' no definida");
            return null;
        }

        // validar la cantidad de argumentos
        if (funcion.getNumParametros() != tiposArgs.size()) {
            this.contexto.agregarError(expr, "numero de argumentos incorrecto, se esperaban "
                    + funcion.getNumParametros() + " pero se dieron " + tiposArgs.size());
        } else {

            // validar la compatibilidad de cada argumento
            for (int i = 0; i < tiposArgs.size(); i++) {
                Tipo esperado = funcion.getTiposParametros().get(i);
                Tipo real = tiposArgs.get(i);

                if (esperado != null && real != null && !this.contexto.esCompatible(esperado, real)) {
                    this.contexto.agregarError(argumentos.get(i), "tipo de argumento incompatible, se esperaba '" + esperado.getNombre() + "' pero se obtuvo '" + real.getNombre() + "'");
                }
            }
        }
        return funcion.getTipo();
    }

    @Override
    public Object visitExprLlamadaMetodo(ExprLlamadaMetodo expr) {
        // visitar el objeto si existe
        if (expr.getObjeto() != null) {
            expr.getObjeto().accept(this);
        }

        // visitar los argumentos si existen
        if (expr.getArgumentos() != null) {
            expr.getArgumentos().accept(this);
        }

        return null;
    }

    @Override
    public Object visitExprAccesoPosicionArray(ExprAccesoPosicionArray expr) {
        Tipo tipoArray = null;

        // visitar el arreglo si existe
        if (expr.getArray() != null) {
            Object resultado = expr.getArray().accept(this);
            tipoArray = extraerTipoDeExpresion(resultado);
        }

        // verificar si el tipo del arreglo es nulo
        if (tipoArray == null) {
            return null;
        }

        // verificar si el tipo no es un arreglo
        if (tipoArray.getDimension() <= 0) {
            this.contexto.agregarError(expr, "se esperaba un arreglo");
            return null;
        }

        // visitar el indice si existe
        if (expr.getIndice() != null) {
            Object tipoIndiceObj = expr.getIndice().accept(this);
            Tipo tipoIndice = extraerTipoDeExpresion(tipoIndiceObj);

            // verificar si el indice no es entero
            if (tipoIndice != null && !this.contexto.esEntero(tipoIndice)) {
                this.contexto.agregarError(expr.getIndice(), "el indice debe ser entero");
            }

        }

        // devolver el tipo del elemento
        return this.contexto.tipoElemento(tipoArray);
    }

    @Override
    public Object visitExprAccesoMiembroEstructura(ExprAccesoMiembroEstructura expr) {
        Tipo tipoObjeto = null;

        // visitar el objeto si existe
        if (expr.getObjeto() != null) {
            Object resultado = expr.getObjeto().accept(this);
            tipoObjeto = extraerTipoDeExpresion(resultado);
        }

        // verificar si el tipo del objeto es nulo
        if (tipoObjeto == null) {
            return null;
        }

        // buscar el campo en el contexto
        Simbolo campo = this.contexto.campo(tipoObjeto, expr.getMiembro(), expr);

        // devolver el tipo del campo si existe
        if (campo != null) {
            return campo.getTipo();
        }

        return null;
    }

    @Override
    public Object visitExprPostIncremento(ExprPostIncremento expr) {
        Tipo tipoVar = null;

        // visitar la variable si existe
        if (expr.getVariable() != null) {
            Object resultado = expr.getVariable().accept(this);
            tipoVar = extraerTipoDeExpresion(resultado);
        }

        // verificar si la variable no es numerica
        if (tipoVar != null && !this.contexto.esNumerico(tipoVar)) {
            this.contexto.agregarError(expr, "incremento solo permitido sobre tipos numericos");
        }

        return tipoVar;
    }

    @Override
    public Object visitExprPostDecremento(ExprPostDecremento expr) {
        Tipo tipoVar = null;

        // visitar la variable si existe
        if (expr.getVariable() != null) {
            Object resultado = expr.getVariable().accept(this);
            tipoVar = extraerTipoDeExpresion(resultado);
        }

        // verificar si la variable no es numerica
        if (tipoVar != null && !this.contexto.esNumerico(tipoVar)) {
            this.contexto.agregarError(expr, "decremento solo permitido sobre tipos numericos");
        }

        return tipoVar;
    }

    @Override
    public Object visitExprPreIncremento(ExprPreIncremento expr) {
        Tipo tipoVar = null;

        // visitar la variable si existe
        if (expr.getVariable() != null) {
            Object resultado = expr.getVariable().accept(this);
            tipoVar = extraerTipoDeExpresion(resultado);
        }

        // verificar si la variable no es numerica
        if (tipoVar != null && !this.contexto.esNumerico(tipoVar)) {
            this.contexto.agregarError(expr, "incremento solo permitido sobre tipos numericos");
        }

        return tipoVar;
    }

    @Override
    public Object visitExprPreDecremento(ExprPreDecremento expr) {
        Tipo tipoVar = null;

        // visitar la variable si existe
        if (expr.getVariable() != null) {
            Object resultado = expr.getVariable().accept(this);
            tipoVar = extraerTipoDeExpresion(resultado);
        }

        // verificar si la variable no es numerica
        if (tipoVar != null && !this.contexto.esNumerico(tipoVar)) {
            this.contexto.agregarError(expr, "decremento solo permitido sobre tipos numericos");
        }

        return tipoVar;
    }

    @Override
    public Object visitExprListaLiteral(ExprListaLiteral expr) {
        Tipo tipoPrevio = null;

        // verificar si hay elementos
        if (expr.getElementos() != null) {

            // recorrer cada elemento
            for (NodoAST elem : expr.getElementos()) {

                // verificar si el elemento es nulo
                if (elem == null) {
                    continue;
                }

                // entrar en el elemento
                Object resultado = elem.accept(this);
                Tipo tipoActual = extraerTipoDeExpresion(resultado);

                // verificar si el tipo actual es nulo
                if (tipoActual == null) {
                    continue;
                }

                // asignar el primer tipo
                if (tipoPrevio == null) {
                    tipoPrevio = tipoActual;

                } else if (!this.contexto.esCompatible(tipoPrevio, tipoActual)) {
                    // reportar elementos que no son del mismo tipo
                    this.contexto.agregarError(elem, "elementos que no son del mismo tipo en lista literal");
                }
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
    public Object visitExprNegativa(ExprNegativa expr) {
        Tipo tipoExp = null;

        // visitar la expresion si existe
        if (expr.getExpresion() != null) {
            Object resultado = expr.getExpresion().accept(this);
            tipoExp = extraerTipoDeExpresion(resultado);
        }

        // verificar si la expresion no es numerica
        if (tipoExp != null && !this.contexto.esNumerico(tipoExp)) {
            this.contexto.agregarError(expr, "negacion aritmetica solo permitida sobre numeros");
        }

        return tipoExp;
    }

    @Override
    public Object visitExprNegada(ExprNegada expr) {
        Tipo tipoExp = null;

        // visitar la expresion si existe
        if (expr.getExpresion() != null) {
            Object resultado = expr.getExpresion().accept(this);
            tipoExp = extraerTipoDeExpresion(resultado);
        }

        // verificar si la expresion no es booleana
        if (tipoExp != null && !this.contexto.esBooleano(tipoExp)) {
            this.contexto.agregarError(expr, "negacion logica solo permitida sobre booleanos");
        }
        return this.contexto.tipoPrimitivo("bool");
    }

    @Override
    public Object visitExprMultiplicacionDivision(ExprMultiplicacionDivision expr) {
        Tipo tIzq = null;
        Tipo tDer = null;

        // visitar el operando izquierdo si existe
        if (expr.getOperandoIzquierdo() != null) {
            Object r = expr.getOperandoIzquierdo().accept(this);
            tIzq = extraerTipoDeExpresion(r);
        }

        // visitar el operando derecho si existe
        if (expr.getOperandoDerecho() != null) {
            Object r = expr.getOperandoDerecho().accept(this);
            tDer = extraerTipoDeExpresion(r);
        }

        // verificar si el operando izquierdo no es numerico
        if (tIzq != null && !this.contexto.esNumerico(tIzq)) {
            this.contexto.agregarError(expr.getOperandoIzquierdo(), "operando izquierdo debe ser numerico");
        }

        // verificar si el operando derecho no es numerico
        if (tDer != null && !this.contexto.esNumerico(tDer)) {
            this.contexto.agregarError(expr.getOperandoDerecho(), "operando derecho debe ser numerico");
        }

        // devolver el tipo de mayor jerarquia numerica (double sobre int :D)
        return this.contexto.tipoMayorJerarquia(tIzq, tDer);
    }

    @Override
    public Object visitExprSumaResta(ExprSumaResta expr) {
        Tipo tIzq = null;
        Tipo tDer = null;

        // visitar el operando izquierdo si existe
        if (expr.getOperandoIzquierdo() != null) {
            Object r = expr.getOperandoIzquierdo().accept(this);
            tIzq = extraerTipoDeExpresion(r);
        }

        // visitar el operando derecho si existe
        if (expr.getOperandoDerecho() != null) {
            Object r = expr.getOperandoDerecho().accept(this);
            tDer = extraerTipoDeExpresion(r);
        }

        // verificar si algun operando es nulo
        if (tIzq == null || tDer == null) {
            return this.contexto.tipoMayorJerarquia(tIzq, tDer);
        }

        String op = expr.getOperador();
        // concatenacion de texto
        if ("+".equals(op) && (this.contexto.esTexto(tIzq) || this.contexto.esTexto(tDer))) {
            return this.contexto.tipoPrimitivo("textum");
        }

        // verificar si ambos operandos son numericos
        if (!this.contexto.esNumerico(tIzq) || !this.contexto.esNumerico(tDer)) {
            this.contexto.agregarError(expr, "operacion aritmetica solo permitida entre numericos");
            return null;
        }

        // devolver el tipo de mayor jerarquia
        return this.contexto.tipoMayorJerarquia(tIzq, tDer);
    }

    @Override
    public Object visitExprRelacional(ExprRelacional expr) {
        Tipo tIzq = null;
        Tipo tDer = null;

        // visitar el operando izquierdo si existe
        if (expr.getOperandoIzquierdo() != null) {
            Object r = expr.getOperandoIzquierdo().accept(this);
            tIzq = extraerTipoDeExpresion(r);
        }

        // visitar el operando derecho si existe
        if (expr.getOperandoDerecho() != null) {
            Object r = expr.getOperandoDerecho().accept(this);
            tDer = extraerTipoDeExpresion(r);
        }

        // verificar si ambos tipos existen
        if (tIzq != null && tDer != null) {
            boolean num = this.contexto.esNumerico(tIzq) && this.contexto.esNumerico(tDer);
            boolean txt = this.contexto.esTexto(tIzq) && this.contexto.esTexto(tDer);
            boolean bool = this.contexto.esBooleano(tIzq) && this.contexto.esBooleano(tDer);

            // verificar si los tipos son incompatibles
            if (!num && !txt && !bool) {
                this.contexto.agregarError(expr, "tipos incompatibles para comparacion");
            }

        }

        return this.contexto.tipoPrimitivo("bool");
    }

    @Override
    public Object visitExprAnd(ExprAnd expr) {
        Tipo tIzq = null;
        Tipo tDer = null;

        // visitar el operando izquierdo si existe
        if (expr.getOperandoIzquierdo() != null) {
            Object r = expr.getOperandoIzquierdo().accept(this);
            tIzq = extraerTipoDeExpresion(r);
        }

        // visitar el operando derecho si existe
        if (expr.getOperandoDerecho() != null) {
            Object r = expr.getOperandoDerecho().accept(this);
            tDer = extraerTipoDeExpresion(r);
        }

        // verificar si el operando izquierdo no es booleano
        if (tIzq != null && !this.contexto.esBooleano(tIzq)) {
            this.contexto.agregarError(expr.getOperandoIzquierdo(), "operando izquierdo debe ser booleano");
        }

        // verificar si el operando derecho no es booleano
        if (tDer != null && !this.contexto.esBooleano(tDer)) {
            this.contexto.agregarError(expr.getOperandoDerecho(), "operando derecho debe ser booleano");
        }

        return this.contexto.tipoPrimitivo("bool");
    }

    @Override
    public Object visitExprOr(ExprOr expr) {
        Tipo tIzq = null;
        Tipo tDer = null;

        // visitar el operando izquierdo si existe
        if (expr.getOperandoIzquierdo() != null) {
            Object r = expr.getOperandoIzquierdo().accept(this);
            tIzq = extraerTipoDeExpresion(r);
        }

        // visitar el operando derecho si existe
        if (expr.getOperandoDerecho() != null) {
            Object r = expr.getOperandoDerecho().accept(this);
            tDer = extraerTipoDeExpresion(r);
        }

        // verificar si el operando izquierdo no es booleano
        if (tIzq != null && !this.contexto.esBooleano(tIzq)) {
            this.contexto.agregarError(expr.getOperandoIzquierdo(), "operando izquierdo debe ser booleano");
        }

        // verificar si el operando derecho no es booleano
        if (tDer != null && !this.contexto.esBooleano(tDer)) {
            this.contexto.agregarError(expr.getOperandoDerecho(), "operando derecho debe ser booleano");
        }

        return this.contexto.tipoPrimitivo("bool");
    }

    // ==================== DECLARACIONES ====================

    @Override
    public Object visitDeclConTipoYValor(DeclConTipoYValor decl) {

        // resolver el tipo de la declaracion
        Tipo tipo = resolverTipoTipoDato(decl.getTipo());

        // verificar si el tipo es nulo
        if (tipo == null) {
            this.contexto.agregarError(decl, "tipo no definido");
            return null;
        }

        // declarar la variable de forma segura
        if (!declararVariableSeguro(decl, decl.getIdentificador(), tipo, CategoriaSimbolo.VARIABLE, null)) {
            return null;
        }

        // visitar el valor si existe
        if (decl.getValor() != null) {
            Object resultado = decl.getValor().accept(this);
            Tipo tipoExp = extraerTipoDeExpresion(resultado);

            // verificar si el tipo es asignable
            if (tipoExp != null && !this.contexto.esAsignable(tipo, tipoExp)) {
                this.contexto.agregarError(decl.getValor(), "tipo incompatible en inicializacion, se esperaba '" + tipo.getNombre() + "' pero se obtuvo '" + tipoExp.getNombre() + "'");
            }

        }
        return null;
    }

    @Override
    public Object visitDeclBooleanaImplicita(DeclBooleanaImplicita decl) {

        // obtener el tipo booleano
        Tipo tipoBool = this.contexto.tipoPrimitivo("bool");

        // declarar la variable de forma segura
        declararVariableSeguro(decl, decl.getIdentificador(), tipoBool, CategoriaSimbolo.VARIABLE, null);

        return null;
    }

    @Override
    public Object visitDeclArraySinDatos(DeclArraySinDatos decl) {
        // resolver el tipo base
        Tipo tipoBase = resolverTipoTipoDato(decl.getTipo());

        // verificar si el tipo base es nulo
        if (tipoBase == null) {
            this.contexto.agregarError(decl, "tipo no definido");
            return null;
        }

        // crear el tipo arreglo
        Tipo tipoArray = this.contexto.tipoArray(tipoBase);
        Integer tamano = null;

        // visitar el tamano si existe
        if (decl.getTamano() != null) {
            decl.getTamano().accept(this);
            tamano = extraerEnteroConstante(decl.getTamano());

            // verificar si el tamano no es constante
            if (tamano == null) {
                this.contexto.agregarError(decl.getTamano(), "el tamano del arreglo debe ser una constante entera");

            } else if (tamano <= 0) {

                this.contexto.agregarError(decl.getTamano(), "el tamano del arreglo debe ser positivo");

            }
        }

        // declarar la variable de forma segura
        declararVariableSeguro(decl, decl.getIdentificador(), tipoArray, CategoriaSimbolo.ARREGLO, tamano);

        return null;
    }

    @Override
    public Object visitDeclArrayConDatos(DeclArrayConDatos decl) {

        // resolver el tipo base
        Tipo tipoBase = resolverTipoTipoDato(decl.getTipo());

        // verificar si el tipo base es nulo
        if (tipoBase == null) {
            this.contexto.agregarError(decl, "tipo no definido");

            return null;
        }

        // crear el tipo arreglo
        Tipo tipoArray = this.contexto.tipoArray(tipoBase);
        Integer tamano = null;

        // visitar el tamano si existe
        if (decl.getTamano() != null) {
            decl.getTamano().accept(this);
            tamano = extraerEnteroConstante(decl.getTamano());

            // verificar si el tamano no es constante
            if (tamano == null) {

                this.contexto.agregarError(decl.getTamano(), "el tamano del arreglo debe ser una constante entera");

            } else if (tamano <= 0) {

                this.contexto.agregarError(decl.getTamano(), "el tamano del arreglo debe ser positivo");

            }
        }

        // declarar la variable de forma segura
        declararVariableSeguro(decl, decl.getIdentificador(), tipoArray, CategoriaSimbolo.ARREGLO, tamano);

        // verificar si hay valores
        if (decl.getValores() != null) {

            // verificar la cantidad de valores
            if (tamano != null && decl.getValores().size() != tamano) {
                this.contexto.agregarError(decl, "la cantidad de valores (" + decl.getValores().size() + ") no coincide con el tamano declarado (" + tamano + ")");
            }

            // recorrer cada valor
            for (NodoAST expr : decl.getValores()) {


                // verificar si el valor es nulo
                if (expr == null) {
                    continue;
                }

                // entrar en el valor
                Object resultado = expr.accept(this);
                Tipo tipoValor = extraerTipoDeExpresion(resultado);

                // verificar si el tipo es compatible
                if (tipoValor != null && !this.contexto.esCompatible(tipoBase, tipoValor)) {
                    this.contexto.agregarError(expr, "valor incompatible con el tipo del arreglo, se esperaba '" + tipoBase.getNombre() + "' pero se obtuvo '" + tipoValor.getNombre() + "'");
                }

            }
        }

        return null;
    }

    @Override
    public Object visitDeclArrayEstructura(DeclArrayEstructura decl) {

        // resolver el tipo base por nombre
        Tipo tipoBase = resolverTipoPorNombre(decl.getTipo(), decl);

        // verificar si el tipo base es nulo
        if (tipoBase == null) {
            this.contexto.agregarError(decl, "tipo no definido");
            return null;
        }

        // crear el tipo arreglo
        Tipo tipoArray = this.contexto.tipoArray(tipoBase);
        Integer tamano = null;

        // visitar el tamano si existe
        if (decl.getTamano() != null) {
            decl.getTamano().accept(this);
            tamano = extraerEnteroConstante(decl.getTamano());

            // verificar si el tamano no es constante
            if (tamano == null) {
                this.contexto.agregarError(decl.getTamano(), "el tamano del arreglo debe ser una constante entera");
            } else if (tamano <= 0) {
                this.contexto.agregarError(decl.getTamano(), "el tamano del arreglo debe ser positivo");
            }

        }

        // declarar la variable de forma segura
        declararVariableSeguro(decl, decl.getIdentificador(), tipoArray, CategoriaSimbolo.ARREGLO, tamano);

        return null;
    }

    @Override
    public Object visitDeclEstructuraConValores(DeclEstructuraConValores decl) {
        // resolver el tipo por nombre
        Tipo tipo = resolverTipoPorNombre(decl.getTipo(), decl);

        // verificar si el tipo es nulo
        if (tipo == null) {
            this.contexto.agregarError(decl, "tipo no definido");
            return null;
        }

        // declarar la variable de forma segura
        declararVariableSeguro(decl, decl.getIdentificador(), tipo, CategoriaSimbolo.VARIABLE, null);

        // validar los atributos si existen
        if (decl.getAtributos() != null) {
            validarAtributosInstancia(tipo, decl.getAtributos());
        }

        return null;
    }

    @Override
    public Object visitDeclObjetoNovus(DeclObjetoNovus decl) {
        // resolver el tipo por nombre
        Tipo tipo = resolverTipoPorNombre(decl.getTipo(), decl);

        // verificar si el tipo es nulo
        if (tipo == null) {
            this.contexto.agregarError(decl, "tipo no definido");
            return null;
        }

        // declarar la variable de forma segura
        declararVariableSeguro(decl, decl.getIdentificador(), tipo, CategoriaSimbolo.OBJETO, null);

        // visitar los argumentos si existen
        if (decl.getArgumentos() != null) {
            decl.getArgumentos().accept(this);
        }

        return null;
    }

    // validar los atributos de una instancia
    private void validarAtributosInstancia(Tipo tipo, NodoAST atributosNodo) {

        // verificar si el nodo es una lista de atributos
        if (!(atributosNodo instanceof ListaAtributosInstancia)) {
            return;
        }

        // convertir a lista de atributos
        ListaAtributosInstancia lista = (ListaAtributosInstancia) atributosNodo;

        int posicion = 0;

        // recorrer cada atributo
        for (NodoAST attr : lista.getAtributos()) {

            // verificar si el atributo es nulo
            if (attr == null) {
                continue;
            }

            // verificar si es campo con nombre
            if (attr instanceof CampoConNombre) {
                CampoConNombre campo = (CampoConNombre) attr;

                // buscar la definicion del campo
                Simbolo definicion = this.contexto.campo(tipo, campo.getNombre(), campo);

                // verificar si la definicion es nula
                if (definicion == null) {

                    // TODO: el esquema del struct no se conoce cuando el tipo viene de un import .y
                    // mientras no se lea el archivo importado, campo() devuelve null sin reportar error :D
                    // el esquema importado ya se marca como conocido en registrarTipoImportado
                    // campo() ya reporta miembro no declarado cuando el esquema es conocido
                    // omitir el valor porque no hay definicion contra que validar
                    continue;

                }


                // visitar el valor si existe
                if (campo.getValor() != null) {
                    Object resultado = campo.getValor().accept(this);
                    Tipo tipoValor = extraerTipoDeExpresion(resultado);

                    // verificar si el tipo es compatible
                    if (tipoValor != null && !this.contexto.esCompatible(definicion.getTipo(), tipoValor)) {
                        this.contexto.agregarError(campo.getValor(), "tipo incompatible para el campo '" + campo.getNombre() + "', se esperaba '" + definicion.getTipo().getNombre() + "' pero se obtuvo '" + tipoValor.getNombre() + "'");
                    }

                }

            } else if (attr instanceof CampoPosicional) {

                CampoPosicional campo = (CampoPosicional) attr;
                Simbolo definicion = null;

                // verificar si el esquema es conocido
                if (this.contexto.tieneEsquemaConocido(tipo)) {

                    // verificar si la posicion esta dentro del rango

                    if (posicion < tipo.getCampos().size()) {
                        definicion = tipo.getCampos().get(posicion);

                    } else {
                        this.contexto.agregarError(campo, "demasiados valores posicionales para el tipo '" + tipo.getNombre() + "'");
                    }

                }
                // visitar el valor si existe
                if (campo.getValor() != null) {
                    Object resultado = campo.getValor().accept(this);
                    Tipo tipoValor = extraerTipoDeExpresion(resultado);

                    // verificar si hay definicion y tipo
                    if (definicion != null && tipoValor != null) {

                        // verificar si el tipo es compatible
                        if (!this.contexto.esCompatible(definicion.getTipo(), tipoValor)) {
                            this.contexto.agregarError(campo.getValor(), "tipo incompatible para el campo posicional " + posicion + " ('" + definicion.getNombre() + "'), se esperaba '" + definicion.getTipo().getNombre() + "' pero se obtuvo '" + tipoValor.getNombre() + "'");
                        }

                    }
                }

                posicion++;

            }
        }
    }

    // ==================== ASIGNACIONES ====================

    @Override
    public Object visitAsignacionGeneral(AsignacionGeneral asignacion) {
        Tipo tipoVar = null;

        // visitar la variable si existe
        if (asignacion.getVariable() != null) {
            Object resultado = asignacion.getVariable().accept(this);
            tipoVar = extraerTipoDeExpresion(resultado);
        }

        // verificar si el tipo de la variable es nulo
        if (tipoVar == null) {
            this.contexto.agregarError(asignacion, "variable no declarada o no accesible");
            return null;
        }

        // visitar el valor si existe
        if (asignacion.getValor() != null) {
            Object resultado = asignacion.getValor().accept(this);
            Tipo tipoExp = extraerTipoDeExpresion(resultado);

            // verificar si el tipo es asignable
            if (tipoExp != null && !this.contexto.esAsignable(tipoVar, tipoExp)) {
                this.contexto.agregarError(asignacion.getValor(), "tipo incompatible en asignacion, se esperaba '" + tipoVar.getNombre() + "' pero se obtuvo '" + tipoExp.getNombre() + "'");
            }

        }

        return null;
    }

    // ==================== INSTRUCCIONES DE FLUJO ====================

    @Override
    public Object visitStmtAsignacion(StmtAsignacion stmt) {
        // visitar la asignacion si existe
        if (stmt.getAsignacion() != null) {
            stmt.getAsignacion().accept(this);
        }

        return new FlujoControl();
    }

    @Override
    public Object visitStmtCondicional(StmtCondicional stmt) {
        // visitar el condicional si existe
        if (stmt.getCondicional() != null) {
            Object resultado = stmt.getCondicional().accept(this);

            // devolver el flujo si el resultado es FlujoControl
            if (resultado instanceof FlujoControl) {
                return resultado;
            }
        }

        return new FlujoControl();
    }

    @Override
    public Object visitStmtCiclo(StmtCiclo stmt) {

        // visitar el ciclo si existe
        if (stmt.getCiclo() != null) {
            stmt.getCiclo().accept(this);
        }

        return new FlujoControl();
    }

    @Override
    public Object visitStmtLectura(StmtLectura stmt) {
        // visitar la lectura si existe
        if (stmt.getLectura() != null) {
            stmt.getLectura().accept(this);
        }

        return new FlujoControl();
    }

    @Override
    public Object visitStmtImpresion(StmtImpresion stmt) {
        // visitar la impresion si existe
        if (stmt.getImpresion() != null) {
            stmt.getImpresion().accept(this);
        }

        return new FlujoControl();
    }

    @Override
    public Object visitStmtInterrumpe(StmtInterrumpe stmt) {
        // verificar si esta dentro de un ciclo
        if (!this.contexto.estaDentroDeCiclo()) {
            this.contexto.agregarError(stmt, "'interrumpe' solo puede usarse dentro de un ciclo");
        }

        // interrumpe termina el flujo del bloque actual
        FlujoControl flujo = new FlujoControl();
        flujo.marcarRetorno();

        return flujo;
    }

    @Override
    public Object visitStmtPerge(StmtPerge stmt) {

        // verificar si esta dentro de un ciclo
        if (!this.contexto.estaDentroDeCiclo()) {
            this.contexto.agregarError(stmt, "'perge' solo puede usarse dentro de un ciclo");
        }

        // perge termina el flujo del bloque actual
        FlujoControl flujo = new FlujoControl();
        flujo.marcarRetorno();

        return flujo;
    }

    @Override
    public Object visitStmtExpresion(StmtExpresion stmt) {

        // visitar la expresion si existe
        if (stmt.getExpresion() != null) {
            stmt.getExpresion().accept(this);
        }

        return new FlujoControl();
    }

    // ==================== CONDICIONALES ====================

    @Override
    public Object visitStatementSi(StatementSi stmt) {
        // validar la condicion principal
        validarCondicionBooleana(stmt.getCondicion());

        // validar las condiciones de los else if
        if (stmt.getCondicionesAliter() != null) {
            for (NodoAST cond : stmt.getCondicionesAliter()) {
                validarCondicionBooleana(cond);
            }
        }

        // visitar cada rama y acumular flujos
        this.contexto.entrarAmbito("bloque-si");
        FlujoControl flujoPrincipal = visitarBloqueAislado(stmt.getBloque());
        this.contexto.salirAmbito();

        boolean todasTerminan = !flujoPrincipal.puedeContinuar();

        // visitar los bloques else if
        if (stmt.getBloquesAliter() != null) {

            int i = 0;

            for (NodoAST bloque : stmt.getBloquesAliter()) {

                this.contexto.entrarAmbito("bloque-aliter-" + i);
                FlujoControl flujoRama = visitarBloqueAislado(bloque);
                this.contexto.salirAmbito();

                // verificar si la rama puede continuar
                if (flujoRama.puedeContinuar()) {
                    todasTerminan = false;
                }

                i++;
            }
        }

        boolean hayElse = false;
        // visitar el bloque else final si existe
        if (stmt.getBloqueAliter() != null) {

            hayElse = true;

            this.contexto.entrarAmbito("bloque-aliter-final");

            FlujoControl flujoElse = visitarBloqueAislado(stmt.getBloqueAliter());

            this.contexto.salirAmbito();

            // verificar si el else puede continuar
            if (flujoElse.puedeContinuar()) {
                todasTerminan = false;
            }

        } else {

            // sin else siempre hay una ruta que no termina
            todasTerminan = false;

        }

        // devolver el flujo correspondiente
        FlujoControl resultado = new FlujoControl();
        if (hayElse && todasTerminan) {
            resultado.marcarRetorno();

        } else {
            resultado.marcarContinuacion();

        }

        return resultado;
    }

    // ==================== CICLOS ====================

    @Override
    public Object visitCicloDum(CicloDum ciclo) {

        // validar la condicion
        validarCondicionBooleana(ciclo.getCondicion());

        this.contexto.entrarCiclo();
        this.contexto.entrarAmbito("bloque-dum");

        // visitar el bloque si existe
        if (ciclo.getBloque() != null) {
            ciclo.getBloque().accept(this);
        }

        this.contexto.salirAmbito();
        this.contexto.salirCiclo();

        // despues de un ciclo siempre se puede continuar
        return new FlujoControl();
    }

    @Override
    public Object visitCicloFacere(CicloFacere ciclo) {

        this.contexto.entrarCiclo();
        this.contexto.entrarAmbito("bloque-facere");

        // visitar el bloque si existe
        if (ciclo.getBloque() != null) {
            ciclo.getBloque().accept(this);
        }

        this.contexto.salirAmbito();
        this.contexto.salirCiclo();

        // validar la condicion
        validarCondicionBooleana(ciclo.getCondicion());

        // despues de un ciclo siempre se puede continuar
        return new FlujoControl();
    }

    @Override
    public Object visitCicloPer(CicloPer ciclo) {

        this.contexto.entrarCiclo();
        this.contexto.entrarAmbito("bloque-per");

        // inicializacion
        if (ciclo.getInicializacion() != null) {
            ciclo.getInicializacion().accept(this);
        }

        // condicion
        validarCondicionBooleana(ciclo.getCondicion());

        // paso
        if (ciclo.getPaso() != null) {
            ciclo.getPaso().accept(this);
        }

        // cuerpo
        if (ciclo.getBloque() != null) {
            ciclo.getBloque().accept(this);
        }

        this.contexto.salirAmbito();
        this.contexto.salirCiclo();

        // despues de un ciclo siempre se puede continuar
        return new FlujoControl();
    }

    // ==================== INIT Y PASO DEL FOR ====================

    @Override
    public Object visitInitPerDecl(InitPerDecl init) {
        // resolver el tipo
        Tipo tipo = resolverTipoTipoDato(init.getTipo());

        // verificar si el tipo es nulo
        if (tipo == null) {
            this.contexto.agregarError(init, "tipo no definido");
            return null;
        }

        // declarar la variable de forma segura
        if (!declararVariableSeguro(init, init.getIdentificador(), tipo, CategoriaSimbolo.VARIABLE, null)) {
            return null;
        }

        // visitar el valor si existe
        if (init.getValor() != null) {
            Object resultado = init.getValor().accept(this);
            Tipo tipoExp = extraerTipoDeExpresion(resultado);

            // verificar si el tipo es asignable
            if (tipoExp != null && !this.contexto.esAsignable(tipo, tipoExp)) {
                this.contexto.agregarError(init.getValor(), "tipo incompatible en inicializacion");
            }

        }

        return null;
    }

    @Override
    public Object visitInitPerAsig(InitPerAsig init) {
        Tipo tipoVar = null;

        // visitar la variable si existe
        if (init.getVariable() != null) {
            Object resultado = init.getVariable().accept(this);
            tipoVar = extraerTipoDeExpresion(resultado);
        }

        // verificar si el tipo es nulo
        if (tipoVar == null) {
            return null;
        }

        // visitar el valor si existe
        if (init.getValor() != null) {
            Object resultado = init.getValor().accept(this);
            Tipo tipoExp = extraerTipoDeExpresion(resultado);

            // verificar si el tipo es asignable
            if (tipoExp != null && !this.contexto.esAsignable(tipoVar, tipoExp)) {
                this.contexto.agregarError(init.getValor(), "tipo incompatible en inicializacion");
            }

        }
        return null;
    }

    @Override
    public Object visitPasoPerExpr(PasoPerExpr paso) {

        // visitar la expresion si existe
        if (paso.getExpresion() != null) {
            paso.getExpresion().accept(this);
        }

        return null;
    }

    @Override
    public Object visitPasoPerAsig(PasoPerAsig paso) {

        Tipo tipoVar = null;

        // visitar la variable si existe
        if (paso.getVariable() != null) {
            Object resultado = paso.getVariable().accept(this);
            tipoVar = extraerTipoDeExpresion(resultado);
        }

        // verificar si el tipo es nulo
        if (tipoVar == null) {
            return null;
        }

        // visitar el valor si existe
        if (paso.getValor() != null) {
            Object resultado = paso.getValor().accept(this);
            Tipo tipoExp = extraerTipoDeExpresion(resultado);

            // verificar si el tipo es asignable
            if (tipoExp != null && !this.contexto.esAsignable(tipoVar, tipoExp)) {
                this.contexto.agregarError(paso.getValor(), "tipo incompatible en paso");
            }

        }

        return null;
    }

    // ==================== LECTURA ====================

    @Override
    public Object visitLecturaConsolaSimple(LecturaConsolaSimple lectura) {
        return null;
    }

    @Override
    public Object visitLecturaConsolaAVariable(LecturaConsolaAVariable lectura) {

        // visitar la variable si existe
        if (lectura.getVariable() != null) {
            Object resultado = lectura.getVariable().accept(this);

            Tipo tipoVar = extraerTipoDeExpresion(resultado);

            // verificar si la variable no esta declarada
            if (tipoVar == null) {
                this.contexto.agregarError(lectura, "variable no declarada");
            }

        }
        return null;
    }

    // ==================== IMPRESION ====================

    @Override
    public Object visitImpresionEncadenada(ImpresionEncadenada impresion) {

        // verificar si hay elementos
        if (impresion.getElementos() != null) {

            // recorrer cada elemento
            for (NodoAST elem : impresion.getElementos()) {

                // entrar en el elemento si no es nulo :D
                if (elem != null) {
                    elem.accept(this);
                }

            }
        }

        return null;
    }
}