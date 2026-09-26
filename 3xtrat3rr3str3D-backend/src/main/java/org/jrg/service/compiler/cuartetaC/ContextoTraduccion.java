package org.jrg.service.compiler.cuartetaC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jrg.model.resultado.CuartetaResultado;
import org.jrg.service.compiler.cuartetaC.valor.ValorBooleano;
import org.jrg.service.compiler.cuartetaC.valor.ValorC;
import org.jrg.service.compiler.cuartetaC.valor.ValorLiteral;
import org.jrg.service.compiler.cuartetaC.valor.ValorNulo;
import org.jrg.service.compiler.cuartetaC.valor.ValorVariable;

// estado compartido para la traduccion de cuartetas a codigo C
public class ContextoTraduccion {

    public List<String> paramsPendientes;
    public List<String> tiposParamsPendientes;
    public List<String> builtinsUsados;
    public List<String> prototipos;
    public Map<String, String> globales;
    public Set<String> declaradas;
    public String funcionActual;
    public boolean primeraFuncionAgregada;
    public boolean dentroDeFuncion;
    public StringBuilder bufferFuncion;
    public List<CuartetaResultado> crudasFuncion;
    public Set<String> llamadasFuncion;
    // tipos de params del func_begin en proceso
    public String tiposParamsFuncion;
    public String tipoRetornoFuncion;
    public Map<String, String> retornosFuncion;
    // cuartetas previas a funciones para agregar al cuerpo en la siguiente
    public List<CuartetaResultado> preMain;
    public Map<String, String> tiposParamsActuales;
    public Map<String, String> tiposDeclarados;
    public int contadorTempsString;
    public Map<String, Map<String, String>> structs;
    public Map<String, List<String>> ordenCampos;
    public List<String> ordenStructs;
    // struct al que pertenece cada temporal o variable
    public Map<String, String> structDeNombre;
    // nombres de clases de Zetariano detectadas por prefijo Clase_
    public final Set<String> clases;
    public final Set<String> funcionesConocidas;
    // temporales y variables que guardan punteros a heap
    public final Set<String> punteros;
    // temporales que guardan nulo para retornos enteros
    public final Set<String> nulosConocidos;
    public String nombreClaseActual;

    /**
     * Crear un contexto de traduccion con estado vacio.
     */
    public ContextoTraduccion() {
        // inicializar las estructuras internas
        this.paramsPendientes = new ArrayList<>();
        this.tiposParamsPendientes = new ArrayList<>();
        this.builtinsUsados = new ArrayList<>();
        this.prototipos = new ArrayList<>();
        this.globales = new HashMap<>();
        this.declaradas = new HashSet<>();
        this.funcionActual = "";
        this.primeraFuncionAgregada = false;
        this.dentroDeFuncion = false;
        this.bufferFuncion = null;
        this.crudasFuncion = new ArrayList<>();
        this.llamadasFuncion = new HashSet<>();
        this.tiposParamsFuncion = "_";
        this.tipoRetornoFuncion = "_";
        this.retornosFuncion = new HashMap<>();
        this.preMain = new ArrayList<>();
        this.tiposParamsActuales = new HashMap<>();
        this.tiposDeclarados = new HashMap<>();
        this.contadorTempsString = 0;
        this.structs = new HashMap<>();
        this.ordenCampos = new HashMap<>();
        this.ordenStructs = new ArrayList<>();
        this.structDeNombre = new HashMap<>();
        this.clases = new HashSet<>();
        this.funcionesConocidas = new HashSet<>();
        this.punteros = new HashSet<>();
        this.nulosConocidos = new HashSet<>();
        this.nombreClaseActual = "";
    }

    /**
     * Preprocesar las definiciones de structs de la lista.
     */
    public void preprocesarStructs(List<CuartetaResultado> cuartetas) {
        // reiniciar las estructuras de structs
        this.structs = new HashMap<>();
        this.ordenCampos = new HashMap<>();
        this.ordenStructs = new ArrayList<>();
        this.structDeNombre = new HashMap<>();
        // registrar definiciones en una pasada global
        for (int i = 0; i < cuartetas.size(); i++) {
            CuartetaResultado c = cuartetas.get(i);
            // omitir cuartetas nulas
            if (c == null) {
                continue;
            }
            // registrar cada definicion de struct
            if ("struct_def".equals(c.getOperador())) {
                registrarStruct(c.getArg1(), c.getArg2());
            }
        }
    }

    /**
     * Registrar un struct con sus campos en orden.
     */
    public void registrarStruct(String nombre, String camposTexto) {
        // omitir nombres nulos o vacios
        if (nombre == null || nombre.isEmpty() || nombre.equals("_")) {
            return;
        }
        // crear el mapa de campos y su orden
        Map<String, String> campos = new HashMap<>();
        List<String> orden = new ArrayList<>();
        // recorrer los campos separados por coma
        if (camposTexto != null && camposTexto.equals("_") == false && camposTexto.isEmpty() == false) {
            String[] partes = camposTexto.split(",");
            for (int i = 0; i < partes.length; i++) {
                String recorte = partes[i].trim();
                int dosPuntos = recorte.indexOf(':');
                // omitir partes sin formato nombre tipo
                if (dosPuntos <= 0) {
                    continue;
                }
                String campo = recorte.substring(0, dosPuntos).trim();
                String tipo = recorte.substring(dosPuntos + 1).trim();
                // omitir campos o tipos vacios
                if (campo.isEmpty() || tipo.isEmpty()) {
                    continue;
                }
                // guardar el orden solo la primera vez
                if (campos.containsKey(campo) == false) {
                    orden.add(campo);
                }
                campos.put(campo, tipo);
            }
        }
        // guardar el struct y su orden de campos
        structs.put(nombre, campos);
        ordenCampos.put(nombre, orden);
        // guardar el orden de definicion sin duplicados
        if (ordenStructs.contains(nombre) == false) {
            ordenStructs.add(nombre);
        }
    }

    /**
     * Buscar clases de Zetariano por nombre de funcion.
     */
    public void detectarClases(List<CuartetaResultado> cuartetas) {
        // reiniciar los conjuntos de deteccion
        this.clases.clear();
        this.funcionesConocidas.clear();
        // recoger todos los nombres de struct_def
        Set<String> nombresStruct = new HashSet<>();
        for (int i = 0; i < cuartetas.size(); i++) {
            CuartetaResultado c = cuartetas.get(i);
            // omitir cuartetas nulas
            if (c == null) {
                continue;
            }
            if ("struct_def".equals(c.getOperador())) {
                if (c.getArg1() != null && c.getArg1().isEmpty() == false) {
                    nombresStruct.add(c.getArg1());
                }
            }
        }
        // buscar func_begin con prefijo de struct
        for (int i = 0; i < cuartetas.size(); i++) {
            CuartetaResultado c = cuartetas.get(i);
            // omitir cuartetas nulas
            if (c == null) {
                continue;
            }
            if ("func_begin".equals(c.getOperador())) {
                String nombreFunc = c.getArg1();
                // registrar toda funcion conocida
                if (nombreFunc != null && nombreFunc.isEmpty() == false) {
                    funcionesConocidas.add(nombreFunc);
                }
                // sacar el posible inicio de clase
                String posibleClase = prefijoFuncion(nombreFunc);
                // marcar la clase solo si tiene su struct_def
                if (posibleClase != null && nombresStruct.contains(posibleClase)) {
                    clases.add(posibleClase);
                }
            }
        }
    }

    /**
     * Sacar lo de antes del primer guion bajo.
     */
    public String prefijoFuncion(String nombreFuncion) {
        // omitir nombres nulos o vacios
        if (nombreFuncion == null || nombreFuncion.isEmpty()) {
            return null;
        }
        int guion = nombreFuncion.indexOf('_');
        // omitir nombres sin prefijo antes del guion
        if (guion <= 0) {
            return null;
        }
        return nombreFuncion.substring(0, guion);
    }

    /**
     * Extraer la clase desde un nombre con formato Clase_metodo.
     */
    public String claseDeFuncion(String nombreFuncion) {
        String prefijo = prefijoFuncion(nombreFuncion);
        // omitir prefijos nulos
        if (prefijo == null) {
            return null;
        }
        // aceptar solo inicios marcados como clase
        if (clases.contains(prefijo) == false) {
            return null;
        }
        return prefijo;
    }

    /**
     * Mapear un tipo a C resolviendo clases como punteros a heap.
     */
    public String mapearTipoConClases(String tipo) {
        // usar struct con * para clases ya vistas
        if (tipo != null && clases.contains(tipo)) {
            return "struct " + tipo + "*";
        }
        return mapearTipo(tipo);
    }

    /**
     * Mapear un tipo del compilador a un tipo de C.
     */
    public String mapearTipo(String tipo) {
        // usar int cuando el tipo es desconocido
        if (tipo == null || tipo.equals("_") || tipo.isEmpty()) {
            return "int";
        }
        // dejar arreglos con corchetes en el tipo base
        if (tipo.endsWith("[]")) {
            String base = tipo.substring(0, tipo.length() - 2);
            return mapearTipo(base) + "[]";
        }
        // pasar enteros
        if (tipo.equals("entero") || tipo.equals("numerus") || tipo.equals("int")) {
            return "int";
        }
        // pasar flotantes
        if (tipo.equals("flotante") || tipo.equals("decimalis") || tipo.equals("double")) {
            return "double";
        }
        // pasar cadenas
        if (tipo.equals("cadena") || tipo.equals("textum") || tipo.equals("String")) {
            return "char*";
        }
        // pasar caracteres
        if (tipo.equals("caracter") || tipo.equals("littera") || tipo.equals("char")) {
            return "char";
        }
        // pasar booleanos
        if (tipo.equals("booleano") || tipo.equals("bool") || tipo.equals("boolean")) {
            return "bool";
        }
        // conservar void
        if (tipo.equals("void")) {
            return "void";
        }
        // usar puntero a struct para clases aunque vengan sueltas
        if (clases.contains(tipo)) {
            return "struct " + tipo + "*";
        }
        // usar int si el tipo no se conoce
        return "int";
    }

    /**
     * Verificar si un tipo corresponde a texto para concatenar.
     */
    public boolean esTipoTexto(String tipo) {
        // comparar contra los nombres de texto de los tres lenguajes
        if ("cadena".equals(tipo) || "textum".equals(tipo) || "String".equals(tipo)) {
            return true;
        }
        return false;
    }

    /**
     * Verificar si un nombre es un temporal generado.
     */
    public boolean esTemporal(String nombre) {
        // rechazar nulos y nombres cortos
        if (nombre == null || nombre.length() < 2) {
            return false;
        }
        // verificar que empiece con t
        if (nombre.charAt(0) != 't') {
            return false;
        }
        // verificar que el resto sean digitos
        for (int i = 1; i < nombre.length(); i++) {
            if (Character.isDigit(nombre.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verificar si un nombre es una etiqueta generada.
     */
    public boolean esEtiqueta(String nombre) {
        // rechazar nulos y nombres cortos
        if (nombre == null || nombre.length() < 2) {
            return false;
        }
        // verificar que empiece con L mayuscula
        if (nombre.charAt(0) != 'L') {
            return false;
        }
        // verificar que el resto sean digitos
        for (int i = 1; i < nombre.length(); i++) {
            if (Character.isDigit(nombre.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verificar si una cadena es un numero entero.
     */
    public boolean esNumerico(String texto) {
        // rechazar nulos y vacios
        if (texto == null || texto.isEmpty()) {
            return false;
        }
        try {
            Long.parseLong(texto);
            return true;
        } catch (NumberFormatException e) {
            // usar falso cuando el texto no es numero
            return false;
        }
    }

    /**
     * Calcular el tipo C para declarar un destino de asignacion.
     */
    public String tipoDestinoPara(String destino, String arg1, String tipoArg1) {
        // usar puntero a heap cuando el valor viene de un objeto
        if (arg1 != null && punteros.contains(arg1)) {
            String structOrigen = structDeNombre.get(arg1);
            if (structOrigen != null) {
                return "struct " + structOrigen + "*";
            }
        }
        // usar struct cuando el valor viene de un temporal de struct
        if (arg1 != null) {
            String structOrigen = structDeNombre.get(arg1);
            if (structOrigen != null && structs.containsKey(structOrigen)) {
                return "struct " + structOrigen;
            }
        }
        // usar el tipo del campo cuando el destino es campo de clase
        String tipoCampo = tipoCampoClase(destino);
        if (tipoCampo != null) {
            return tipoCampo;
        }
        // usar el tipo del campo cuando el valor es campo de la clase actual
        if (esCampoActual(arg1)) {
            String tipoValorCampo = tipoCampoClase(arg1);
            if (tipoValorCampo != null) {
                return tipoValorCampo;
            }
        }
        // usar el tipo del valor por defecto
        return mapearTipo(tipoArg1);
    }

    /**
     * Calcular el tipo C para declarar una variable global.
     */
    public String tipoGlobalPara(String arg1, String tipoArg1) {
        // usar puntero a heap cuando el valor viene de un objeto
        if (arg1 != null && punteros.contains(arg1)) {
            String structOrigen = structDeNombre.get(arg1);
            if (structOrigen != null) {
                return "struct " + structOrigen + "*";
            }
        }
        // usar struct por valor cuando el temporal es de struct
        if (arg1 != null) {
            String structOrigen = structDeNombre.get(arg1);
            if (structOrigen != null && structs.containsKey(structOrigen)) {
                return "struct " + structOrigen;
            }
        }
        // usar el tipo del valor por defecto
        return mapearTipo(tipoArg1);
    }

    /**
     * Buscar el tipo C de un campo de la clase actual.
     */
    public String tipoCampoClase(String destino) {
        // omitir destinos nulos o vacios
        if (destino == null || destino.isEmpty() || destino.equals("_")) {
            return null;
        }
        // extraer la clase desde el nombre de la funcion actual
        if (funcionActual == null || funcionActual.isEmpty()) {
            return null;
        }
        int guion = funcionActual.indexOf('_');
        // omitir funciones sin prefijo de clase
        if (guion <= 0) {
            return null;
        }
        String clase = funcionActual.substring(0, guion);
        // buscar los campos de la clase
        Map<String, String> campos = structs.get(clase);
        if (campos == null) {
            return null;
        }
        // buscar el tipo fuente del campo
        String tipoFuente = campos.get(destino);
        if (tipoFuente == null) {
            return null;
        }
        return mapearTipo(tipoFuente);
    }

    /**
     * Verificar si un nombre es campo de la clase actual sin sombra local.
     */
    public boolean esCampoActual(String nombre) {
        // omitir nulos vacios y guiones
        if (nombre == null || nombre.isEmpty() || nombre.equals("_")) {
            return false;
        }
        // solo aplica dentro de un metodo de clase
        if (nombreClaseActual == null || nombreClaseActual.isEmpty()) {
            return false;
        }
        // respetar variables locales y params que sombrean el campo
        if (declaradas.contains(nombre)) {
            return false;
        }
        if (tiposParamsActuales.containsKey(nombre)) {
            return false;
        }
        // buscar el nombre entre los campos de la clase
        Map<String, String> campos = structs.get(nombreClaseActual);
        if (campos == null) {
            return false;
        }
        return campos.containsKey(nombre);
    }

    /**
     * Construir el lado izquierdo con this para campos de clase.
     */
    public String ladoIzquierdo(String nombre, String tipo) {
        // usar this sin declarar cuando es campo de la clase actual
        if (esCampoActual(nombre)) {
            return "this->" + nombre;
        }
        return prefijoDeclaracion(nombre, tipo);
    }

    /**
     * Traducir un valor leyendo campos de clase con this.
     */
    public String accesoCampo(String nombre) {
        // usar this cuando es campo de la clase actual
        if (esCampoActual(nombre)) {
            return "this->" + nombre;
        }
        return nombre;
    }

    /**
     * Resolver la clase de un objeto por sus marcas de puntero.
     */
    public String claseDeObjeto(String nombre) {
        // omitir nulos vacios y guiones
        if (nombre == null || nombre.isEmpty() || nombre.equals("_")) {
            return null;
        }
        // resolver this con la clase actual
        if ("this".equals(nombre)) {
            if (nombreClaseActual == null || nombreClaseActual.isEmpty()) {
                return null;
            }
            return nombreClaseActual;
        }
        // buscar el struct del temporal o variable
        String structOrigen = structDeNombre.get(nombre);
        if (structOrigen != null && clases.contains(structOrigen)) {
            // exigir marca de puntero para objetos en heap
            if (punteros.contains(nombre)) {
                return structOrigen;
            }
            // aceptar el tipo declarado como puntero a struct
            String tipo = tiposDeclarados.get(nombre);
            if (tipo != null && tipo.equals("struct " + structOrigen + "*")) {
                return structOrigen;
            }
            return null;
        }
        // buscar el tipo declarado como puntero a struct
        String tipo = tiposDeclarados.get(nombre);
        if (tipo != null && tipo.startsWith("struct ") && tipo.endsWith("*")) {
            String base = tipo.substring(7, tipo.length() - 1).trim();
            if (clases.contains(base)) {
                return base;
            }
        }
        return null;
    }

    /**
     * Verificar si un nombre guarda un puntero a objeto en heap.
     */
    public boolean esPuntero(String nombre) {
        // omitir nulos vacios y guiones
        if (nombre == null || nombre.isEmpty() || nombre.equals("_")) {
            return false;
        }
        // tratar this como puntero dentro de metodos de clase
        if ("this".equals(nombre)) {
            if (nombreClaseActual != null && nombreClaseActual.isEmpty() == false) {
                return true;
            }
            return false;
        }
        // usar la marca directa de puntero
        if (punteros.contains(nombre)) {
            return true;
        }
        // usar la clase resuelta del objeto
        return claseDeObjeto(nombre) != null;
    }

    /**
     * Adivinar el struct duenio de un campo por su nombre.
     */
    public String structPorCampo(String campo) {
        // omitir campos nulos o vacios
        if (campo == null || campo.isEmpty()) {
            return null;
        }
        // buscar primero structs por valor
        for (int i = 0; i < ordenStructs.size(); i++) {
            String nombre = ordenStructs.get(i);
            if (clases.contains(nombre)) {
                continue;
            }
            Map<String, String> campos = structs.get(nombre);
            if (campos != null && campos.containsKey(campo)) {
                return nombre;
            }
        }
        // aceptar clases como respaldo
        for (int i = 0; i < ordenStructs.size(); i++) {
            String nombre = ordenStructs.get(i);
            Map<String, String> campos = structs.get(nombre);
            if (campos != null && campos.containsKey(campo)) {
                return nombre;
            }
        }
        return null;
    }

    /**
     * Resolver el tipo C de un acceso a miembro de objeto.
     */
    public String tipoMiembro(String objeto, String campo, String tipoRespaldo) {
        String clase = claseDeObjeto(objeto);
        if (clase != null && campo != null) {
            Map<String, String> campos = structs.get(clase);
            if (campos != null) {
                String tipoFuente = campos.get(campo);
                if (tipoFuente != null) {
                    return mapearTipoConClases(tipoFuente);
                }
            }
        }
        return mapearTipo(tipoRespaldo);
    }

    /**
     * Resolver el nombre del campo por indice dentro de un struct.
     */
    public String nombreCampoPorIndice(String objeto, String indice) {
        // buscar el struct del objeto
        String structNombre = null;
        if (objeto != null) {
            structNombre = structDeNombre.get(objeto);
        }
        // verificar que el struct tenga orden de campos
        List<String> orden = null;
        if (structNombre != null) {
            orden = ordenCampos.get(structNombre);
        }
        // mapear el indice al nombre cuando es posible
        if (orden != null && indice != null) {
            try {
                int posicion = Integer.parseInt(indice.trim());
                if (posicion >= 0 && posicion < orden.size()) {
                    return orden.get(posicion);
                }
            } catch (NumberFormatException e) {
                // usar el respaldo cuando el indice no es numero
            }
        }
        // usar nombre de respaldo con el indice crudo
        if (indice == null) {
            return "campo0";
        }
        return "campo" + indice.trim();
    }

    /**
     * Construir el prefijo con declaracion solo la primera vez.
     */
    public String prefijoDeclaracion(String nombre, String tipo) {
        // devolver guion bajo si el nombre es nulo o vacio de tipo
        if (nombre == null || nombre.equals("_")) {
            return "_";
        }
        // devolver solo el nombre si ya fue declarado
        if (declaradas.contains(nombre)) {
            return nombre;
        }
        // registrar el nombre con su tipo y devolverlo declarado
        declaradas.add(nombre);
        tiposDeclarados.put(nombre, tipo);
        return tipo + " " + nombre;
    }

    /**
     * Crear el valor concreto segun la forma del texto.
     */
    public ValorC crearValor(String texto) {
        // dejar nulos y vacios como literales
        if (texto == null || texto.isEmpty()) {
            return new ValorLiteral(texto);
        }
        // poner nulo si no se conoce
        if ("_".equals(texto) || "null".equals(texto) || "NULL".equals(texto)) {
            return new ValorNulo(texto);
        }
        // poner booleano para verum, true y demas
        if ("verum".equals(texto) || "verdadero".equals(texto) || "true".equals(texto) || "falsus".equals(texto) || "falso".equals(texto) || "false".equals(texto)) {
            return new ValorBooleano(texto);
        }
        // poner literal si empieza con comilla
        if (texto.startsWith("\"") || texto.startsWith("'")) {
            return new ValorLiteral(texto);
        }
        // poner variable para lo demas
        return new ValorVariable(texto);
    }

    /**
     * Traducir un print segun el tipo del argumento.
     */
    public String traducirPrint(String valor, String tipoArg) {
        String texto = crearValor(valor).obtenerCodigoC(this);
        // elegir %d %f %c o %s segun el tipo
        if ("cadena".equals(tipoArg) || "textum".equals(tipoArg) || "String".equals(tipoArg) || "char*".equals(tipoArg)) {
            return "printf(\"%s\\n\", " + texto + ");";
        }
        if ("flotante".equals(tipoArg) || "decimalis".equals(tipoArg) || "double".equals(tipoArg)) {
            return "printf(\"%f\\n\", " + texto + ");";
        }
        if ("caracter".equals(tipoArg) || "littera".equals(tipoArg) || "char".equals(tipoArg)) {
            return "printf(\"%c\\n\", " + texto + ");";
        }
        return "printf(\"%d\\n\", " + texto + ");";
    }

    /**
     * Preparar un operando para concatenacion convirtiendo numericos a string.
     */
    public String prepararOperandoParaConcat(String valor, String tipo, StringBuilder lineas) {
        // dejar cadenas y desconocidos igual
        if (tipo == null || esTipoTexto(tipo) || "_".equals(tipo)) {
            return valor;
        }
        // elegir el % de sprintf segun el tipo
        String formato = "";
        if ("entero".equals(tipo) || "numerus".equals(tipo) || "int".equals(tipo)) {
            formato = "%d";
        } else if ("flotante".equals(tipo) || "decimalis".equals(tipo) || "double".equals(tipo)) {
            formato = "%f";
        } else if ("caracter".equals(tipo) || "littera".equals(tipo) || "char".equals(tipo)) {
            formato = "%c";
        } else if ("booleano".equals(tipo) || "bool".equals(tipo) || "boolean".equals(tipo)) {
            formato = "%d";
        }
        // dejar el valor igual si no hay que convertir
        if (formato.isEmpty()) {
            return crearValor(valor).obtenerCodigoC(this);
        }
        // armar un nombre nuevo para el temporal de string
        String tempStr = "__str_" + this.contadorTempsString;
        this.contadorTempsString = this.contadorTempsString + 1;
        // meter el malloc y el sprintf al cuerpo
        lineas.append("char* ").append(tempStr).append(" = malloc(32);\n    ");
        lineas.append("sprintf(").append(tempStr).append(", \"").append(formato).append("\", ").append(crearValor(valor).obtenerCodigoC(this)).append(");\n    ");
        // devolver el nombre del temporal de string
        return tempStr;
    }

    /**
     * Unir los params pendientes separados por coma.
     */
    public String unirParams() {
        // unir los args ya traducidos con coma
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < paramsPendientes.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(crearValor(paramsPendientes.get(i)).obtenerCodigoC(this));
        }
        return sb.toString();
    }

    /**
     * Limpiar los params pendientes despues de un call.
     */
    public void limpiarParams() {
        // vaciar ambas listas en orden
        paramsPendientes.clear();
        tiposParamsPendientes.clear();
    }

    /**
     * Elegir la variante del builtin segun el tipo del primer argumento.
     */
    public String varianteBuiltin(String nombre) {
        String sufijo = "int";
        // usar la version sin argumentos si no hay params
        if (paramsPendientes.isEmpty()) {
            sufijo = "";
        } else {
            // adivinar el tipo del primer arg pendiente
            String tipo = inferirTipoParametro(paramsPendientes.get(0));
            // sacar el final int str float char o bool segun el tipo
            if ("char*".equals(tipo)) {
                sufijo = "str";
            } else if ("double".equals(tipo)) {
                sufijo = "float";
            } else if ("char".equals(tipo)) {
                sufijo = "char";
            } else if ("bool".equals(tipo)) {
                sufijo = "bool";
            } else {
                sufijo = "int";
            }
        }
        // armar el nombre con su final
        String variante = nombre;
        if (sufijo.isEmpty() == false) {
            variante = nombre + "_" + sufijo;
        }
        // guardar la variante para el encabezado
        if (builtinsUsados.contains(variante) == false) {
            builtinsUsados.add(variante);
        }
        return variante;
    }

    /**
     * Adivinar el tipo C de un arg de builtin.
     */
    public String inferirTipoParametro(String nombre) {
        // usar entero si el nombre es nulo o vacio de tipo
        if (nombre == null || nombre.isEmpty() || nombre.equals("_")) {
            return "int";
        }
        // buscar en lo ya declarado con temporales
        String declarado = tiposDeclarados.get(nombre);
        if (declarado != null) {
            return declarado;
        }
        // buscar en los params actuales
        String deParam = tiposParamsActuales.get(nombre);
        if (deParam != null) {
            return deParam;
        }
        // buscar en los campos de la clase actual
        if (esCampoActual(nombre)) {
            String tipoCampo = tipoCampoClase(nombre);
            if (tipoCampo != null) {
                return tipoCampo;
            }
        }
        // adivinar por la forma, int si no se sabe
        return mapearTipo(inferirTipoLiteralParametro(nombre));
    }

    /**
     * Adivinar el tipo por como se ve el valor.
     */
    public String inferirTipoLiteralParametro(String valor) {
        // rechazar nulos y vacios
        if (valor == null || valor.isEmpty()) {
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
        if (Character.isDigit(valor.charAt(0))) {
            return "entero";
        }
        // cualquier otra cosa es de tipo desconocido
        return "_";
    }

    /**
     * Generar la definicion C de un builtin usado.
     */
    public String definicionBuiltin(String variante) {
        // definir imprimir sin argumentos
        if ("imprimir".equals(variante)) {
            return "void imprimir(void) { printf(\"\\n\"); }\n";
        }
        // definir println sin argumentos
        if ("println".equals(variante)) {
            return "void println(void) { printf(\"\\n\"); }\n";
        }
        // partir el nombre en base y final
        int guion = variante.lastIndexOf('_');
        // dejar vacio si no hay final
        if (guion < 0) {
            return "";
        }
        String base = variante.substring(0, guion);
        String sufijo = variante.substring(guion + 1);
        String tipo = "int";
        String formato = "%d";
        // pasar el final a tipo y %
        if ("str".equals(sufijo)) {
            tipo = "char*";
            formato = "%s";
        } else if ("float".equals(sufijo)) {
            tipo = "double";
            formato = "%f";
        } else if ("char".equals(sufijo)) {
            tipo = "char";
            formato = "%c";
        } else if ("bool".equals(sufijo)) {
            tipo = "bool";
            formato = "%d";
        }
        return "void " + variante + "(" + tipo + " x) { printf(\"" + formato + "\\n\", x); }\n";
    }

    /**
     * Construir la expresion de dimension para alloc.
     */
    public String expresionDimension(String dimension) {
        // poner 1 si no se sabe la dimension
        if (dimension == null || dimension.equals("_") || dimension.isEmpty()) {
            return "1";
        }
        // usar el numero directo si es numero
        if (esNumerico(dimension)) {
            return dimension;
        }
        // cuidar el tamano con (n > 0 ? n : 1)
        return "(" + dimension + " > 0 ? " + dimension + " : 1)";
    }

    /**
     * Verificar si el string de params trae formato nombre tipo.
     */
    public boolean contieneNombres(String tiposParams) {
        // devolver falso si no hay params
        if (tiposParams == null || tiposParams.isEmpty() || tiposParams.equals("_")) {
            return false;
        }
        // detectar dos puntos como marca del formato nuevo
        return tiposParams.contains(":");
    }

    /**
     * Parsear params con formato nombre tipo separados por coma.
     */
    public void parsearParamsConNombres(String tiposParams, List<String> nombres, List<String> tipos) {
        // omitir strings nulos o vacios
        if (tiposParams == null || tiposParams.isEmpty()) {
            return;
        }
        // partir los params por coma
        String[] partes = tiposParams.split(",");
        for (int i = 0; i < partes.length; i++) {
            String parte = partes[i].trim();
            int dosPuntos = parte.indexOf(':');
            // omitir partes sin formato nombre tipo
            if (dosPuntos <= 0) {
                continue;
            }
            String nombre = parte.substring(0, dosPuntos).trim();
            String tipo = parte.substring(dosPuntos + 1).trim();
            // omitir nombres o tipos vacios
            if (nombre.isEmpty() || tipo.isEmpty()) {
                continue;
            }
            // guardar el par nombre tipo mapeado a C
            nombres.add(nombre);
            // usar puntero a heap para params de tipo clase
            tipos.add(mapearTipoConClases(tipo));
        }
    }

    /**
     * Contar los parametros desde el string de tipos separados por coma.
     */
    public int contarParametros(String tiposParams) {
        // devolver cero si no hay parametros
        if (tiposParams == null || tiposParams.equals("_") || tiposParams.isEmpty()) {
            return 0;
        }
        // dividir los tipos por coma
        String[] partes = tiposParams.split(",");
        return partes.length;
    }

    /**
     * Dividir los tipos de params por coma.
     */
    public String[] dividirTipos(String tiposParams) {
        // devolver arreglo vacio si no hay parametros
        if (tiposParams == null || tiposParams.equals("_") || tiposParams.isEmpty()) {
            return new String[0];
        }
        return tiposParams.split(",");
    }

    /**
     * Construir la firma desde listas de nombres y tipos.
     */
    public String firmaConListas(String nombre, String tipoRetorno, List<String> nombres, List<String> tipos) {
        // unir cada param con su nombre
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nombres.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(tipos.get(i)).append(" ").append(nombres.get(i));
        }
        // dejar int en main aunque diga void
        if ("main".equals(nombre)) {
            // usar void cuando no hay parametros
            if (sb.length() == 0) {
                return "int main(void)";
            }
            return "int main(" + sb.toString() + ")";
        }
        // usar void cuando no hay parametros
        String lista = sb.toString();
        if (lista.isEmpty()) {
            lista = "void";
        }
        // usar puntero a heap cuando el retorno es una clase
        return mapearTipoConClases(tipoRetorno) + " " + nombre + "(" + lista + ")";
    }

    /**
     * Juntar los nombres que parecen params de una cuarteta.
     */
    public void recolectarIdentificadores(CuartetaResultado c, List<String> identificadores) {
        // omitir cuartetas nulas
        if (c == null) {
            return;
        }
        String operador = c.getOperador();
        // ver valores segun el operador
        if ("param".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            return;
        }
        if ("=".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            return;
        }
        if (":=".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            return;
        }
        if ("+".equals(operador) || "-".equals(operador) || "*".equals(operador) || "/".equals(operador) || "%".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            agregarCandidato(c.getArg2(), identificadores);
            return;
        }
        if ("==".equals(operador) || "!=".equals(operador) || "<".equals(operador) || ">".equals(operador) || "<=".equals(operador) || ">=".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            agregarCandidato(c.getArg2(), identificadores);
            return;
        }
        if ("&&".equals(operador) || "||".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            agregarCandidato(c.getArg2(), identificadores);
            return;
        }
        if ("!".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            return;
        }
        if ("if_false".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            return;
        }
        if ("return".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            return;
        }
        if ("print".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            return;
        }
        if (".".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            return;
        }
        if (".,=".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            agregarCandidato(c.getResultado(), identificadores);
            return;
        }
        if ("=[]".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            agregarCandidato(c.getArg2(), identificadores);
            return;
        }
        if ("[]=".equals(operador)) {
            agregarCandidato(c.getArg1(), identificadores);
            agregarCandidato(c.getArg2(), identificadores);
            agregarCandidato(c.getResultado(), identificadores);
            return;
        }
        if ("alloc".equals(operador)) {
            agregarCandidato(c.getArg2(), identificadores);
            return;
        }
        // saltar calls, news, labels, gotos, halts y reads
    }

    /**
     * Agregar un nombre a la lista si es candidato a parametro.
     */
    public void agregarCandidato(String nombre, List<String> identificadores) {
        // omitir nulos vacios y guiones
        if (nombre == null || nombre.isEmpty() || nombre.equals("_")) {
            return;
        }
        // omitir el receptor implicito de metodos
        if (nombre.equals("this")) {
            return;
        }
        // omitir temporales generados
        if (esTemporal(nombre)) {
            return;
        }
        // omitir etiquetas
        if (esEtiqueta(nombre)) {
            return;
        }
        // omitir numeros
        if (nombre.length() > 0 && Character.isDigit(nombre.charAt(0))) {
            return;
        }
        // omitir cadenas y caracteres
        if (nombre.startsWith("\"") || nombre.startsWith("'")) {
            return;
        }
        // omitir booleanos de los tres lenguajes
        if (nombre.equals("verum") || nombre.equals("verdadero") || nombre.equals("true") || nombre.equals("falsus") || nombre.equals("falso") || nombre.equals("false")) {
            return;
        }
        // omitir nulos
        if (nombre.equals("null") || nombre.equals("NULL")) {
            return;
        }
        // omitir funciones llamadas
        if (llamadasFuncion.contains(nombre)) {
            return;
        }
        // omitir builtins conocidos
        if (nombre.equals("imprimir") || nombre.equals("println") || nombre.equals("leer") || nombre.equals("readln") || nombre.equals("print")) {
            return;
        }
        // omitir variables globales
        if (globales.containsKey(nombre)) {
            return;
        }
        // meter sin repetir en orden
        if (identificadores.contains(nombre) == false) {
            identificadores.add(nombre);
        }
    }
}
