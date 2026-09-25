package org.jrg.analisis.zetariano.cuartetas;

import java.util.List;

import org.jrg.model.ast.zetariano.Parametros;
import org.jrg.model.ast.zetariano.TipoDato;
import org.jrg.model.ast.zetariano.atributo_clase.AtributoArray;
import org.jrg.model.ast.zetariano.atributo_clase.AtributoSimple;
import org.jrg.model.ast.zetariano.base.NodoASTZetariano;
import org.jrg.model.ast.zetariano.constructor.DefConstructor;
import org.jrg.model.ast.zetariano.definicion_clase.DefClase;
import org.jrg.model.ast.zetariano.metodo.MetodoConRetorno;
import org.jrg.model.ast.zetariano.metodo.MetodoSinRetorno;
import org.jrg.model.ast.zetariano.miembro_clase.MiembroAtributo;
import org.jrg.model.ast.zetariano.miembro_clase.MiembroConstructor;
import org.jrg.model.ast.zetariano.miembro_clase.MiembroMetodo;
import org.jrg.model.ast.zetariano.parametro.ParamArray;
import org.jrg.model.ast.zetariano.parametro.ParamSimple;
import org.jrg.model.cuarteta.Cuarteta;

// generar cuartetas de clases constructores y metodos en Zetariano
public class ManejadorClasesZetariano {

    // estado compartido de la generacion
    private final ContextoCuartetasZetariano ctx;
    // generador duenio para el descenso recursivo
    private final GeneradorCuartetasZetariano generador;

    // crear la manejadora con contexto y generador
    public ManejadorClasesZetariano(ContextoCuartetasZetariano ctx, GeneradorCuartetasZetariano generador) {
        // asignar las dependencias recibidas
        this.ctx = ctx;
        this.generador = generador;
    }

    // definir la clase como struct y visitar sus miembros
    public String visitarDefClase(DefClase nodo) {
        // guardar el nombre de la clase actual
        ctx.setNombreClaseActual(nodo.getNombre());

        // construir la lista de campos con sus tipos
        String campos = construirCamposClase(nodo.getMiembros());

        // agregar la definicion de la clase como struct a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("struct_def", nodo.getNombre(), campos, "_", "_", "_", "_"));

        // recorrer cada miembro de la clase
        if (nodo.getMiembros() != null) {

            for (NodoASTZetariano miembro : nodo.getMiembros()) {

                // visitar el miembro actual si existe
                if (miembro != null) {
                    miembro.accept(generador);
                }
            }

        }
        // limpiar el nombre de la clase actual
        ctx.setNombreClaseActual(null);

        return null;
    }

    // construir el string de campos separados por coma con formato nombre:tipo
    public String construirCamposClase(List<NodoASTZetariano> miembros) {
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
    public String extraerNombreTipoClase(NodoASTZetariano tipoNodo) {

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

    // omitir atributos porque solo ocupan memoria
    public String visitarMiembroAtributo(MiembroAtributo nodo) {
        // no agregar cuartetas a la lista porque los atributos solo ocupan memoria
        return null;
    }

    // visitar el constructor interno del miembro
    public String visitarMiembroConstructor(MiembroConstructor nodo) {
        // visitar el constructor interno si existe
        if (nodo.getConstructor() != null) {
            nodo.getConstructor().accept(generador);
        }

        return null;
    }

    // visitar el metodo interno del miembro
    public String visitarMiembroMetodo(MiembroMetodo nodo) {
        // visitar el metodo interno si existe
        if (nodo.getMetodo() != null) {
            nodo.getMetodo().accept(generador);
        }

        return null;
    }

    // visitar el atributo simple sin generar cuartetas
    public String visitarAtributoSimple(AtributoSimple nodo) {
        // TODO
        return null;
    }

    // visitar el atributo arreglo sin generar cuartetas
    public String visitarAtributoArray(AtributoArray nodo) {
        // TODO
        return null;
    }

    // agregar los marcadores de inicio y fin del constructor
    public String visitarDefConstructor(DefConstructor nodo) {
        // construir el string de tipos de parametros
        String tiposParams = extraerTiposParametrosZet(nodo.getParametros());

        // construir el nombre completo incluyendo el nombre del constructor
        String nombreFuncion = nodo.getNombre() + "_" + nodo.getNombre();

        // agregar marcador de inicio a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("func_begin", nombreFuncion, tiposParams, "void", tiposParams, "_", "void"));

        // recorrer las instrucciones del cuerpo
        if (nodo.getInstrucciones() != null) {

            for (NodoASTZetariano instruccion : nodo.getInstrucciones()) {
                if (instruccion != null) {
                    instruccion.accept(generador);
                }
            }

        }

        // agregar marcador de fin a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("func_end", nombreFuncion, "_", "_", "_", "_", "_"));

        return null;
    }

    // agregar los marcadores de inicio y fin del metodo sin retorno
    public String visitarMetodoSinRetorno(MetodoSinRetorno nodo) {
        // construir el string de tipos de parametros
        String tiposParams = extraerTiposParametrosZet(nodo.getParametros());

        // construir el prefijo con el nombre de la clase actual
        String prefijo = "Clase";

        if (ctx.getNombreClaseActual() != null) {
            prefijo = ctx.getNombreClaseActual();
        }

        // construir el nombre completo
        String nombreFuncion = prefijo + "_" + nodo.getNombre();

        // agregar marcador de inicio a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("func_begin", nombreFuncion, tiposParams, "void", tiposParams, "_", "void"));

        // recorrer las instrucciones del cuerpo
        if (nodo.getInstrucciones() != null) {

            for (NodoASTZetariano instruccion : nodo.getInstrucciones()) {
                if (instruccion != null) {
                    instruccion.accept(generador);
                }
            }

        }

        // agregar marcador de fin a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("func_end", nombreFuncion, "_", "_", "_", "_", "_"));

        return null;
    }

    // agregar los marcadores de inicio y fin del metodo con retorno
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
        if (ctx.getNombreClaseActual() != null) {
            prefijo = ctx.getNombreClaseActual();
        }

        // construir el nombre completo
        String nombreFuncion = prefijo + "_" + nodo.getNombre();

        // agregar marcador de inicio a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("func_begin", nombreFuncion, tiposParams, tipoRetorno, tiposParams, "_", tipoRetorno));

        // recorrer las instrucciones del cuerpo
        if (nodo.getInstrucciones() != null) {
            for (NodoASTZetariano instruccion : nodo.getInstrucciones()) {

                if (instruccion != null) {
                    instruccion.accept(generador);
                }

            }
        }

        // agregar marcador de fin a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("func_end", nombreFuncion, "_", "_", "_", "_", "_"));

        return null;
    }

    // construir el string de params con formato nombre tipo separados por coma
    public String extraerTiposParametrosZet(NodoASTZetariano parametrosNodo) {
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

    // visitar el parametro simple sin generar cuartetas
    public String visitarParamSimple(ParamSimple nodo) {
        // TODO
        return null;
    }

    // visitar el parametro arreglo sin generar cuartetas
    public String visitarParamArray(ParamArray nodo) {
        // TODO
        return null;
    }
}
