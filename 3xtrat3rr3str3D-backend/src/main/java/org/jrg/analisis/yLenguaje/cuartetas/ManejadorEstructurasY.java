package org.jrg.analisis.yLenguaje.cuartetas;

import java.util.List;

import org.jrg.model.ast.yLenguaje.TipoDato;
import org.jrg.model.ast.yLenguaje.atributo_struct.AtributoArray;
import org.jrg.model.ast.yLenguaje.atributo_struct.AtributoSimple;
import org.jrg.model.ast.yLenguaje.base.NodoASTY;
import org.jrg.model.ast.yLenguaje.definicion_struct.DefEstructura;
import org.jrg.model.cuarteta.Cuarteta;

// generar cuartetas de estructuras en el lenguaje Y
public class ManejadorEstructurasY {

    private final ContextoCuartetasY ctx;
    private final GeneradorCuartetasY generador;

    // crear la manejadora con contexto y generador
    public ManejadorEstructurasY(ContextoCuartetasY ctx, GeneradorCuartetasY generador) {
        this.ctx = ctx;
        this.generador = generador;
    }

    // agregar la definicion del struct a la lista de cuartetas
    public String visitarDefEstructura(DefEstructura nodo) {
        // construir la lista de campos con sus tipos
        String campos = construirCamposStruct(nodo.getAtributos());

        // agregar la definicion del struct al inicio del programa a la lista de cuartetas
        ctx.getCuartetas().add(new Cuarteta("struct_def", nodo.getNombre(), campos, "_", "_", "_", "_"));

        return null;
    }

    // construir el string de campos separados por coma con formato nombre:tipo
    public String construirCamposStruct(List<NodoASTY> atributos) {

        // devolver guion bajo si no hay atributos
        if (atributos == null || atributos.isEmpty()) {
            return "_";
        }

        // acumular cada campo con su tipo
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < atributos.size(); i++) {
            NodoASTY atributo = atributos.get(i);

            // omitir atributos nulos
            if (atributo == null) {
                continue;
            }

            String campo = "";

            // extraer nombre y tipo segun la clase del atributo
            if (atributo instanceof AtributoSimple) {

                AtributoSimple simple = (AtributoSimple) atributo;
                campo = simple.getNombre() + ":" + extraerNombreTipo(simple.getTipo());

            } else if (atributo instanceof AtributoArray) {

                AtributoArray arreglo = (AtributoArray) atributo;
                campo = arreglo.getNombre() + ":" + extraerNombreTipo(arreglo.getTipo()) + "[]";

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
    public String extraerNombreTipo(NodoASTY tipoNodo) {

        // devolver guion bajo si el nodo es nulo
        if (tipoNodo == null) {
            return "_";
        }

        // extraer el nombre cuando es TipoDato
        if (tipoNodo instanceof TipoDato) {
            String nombre = ((TipoDato) tipoNodo).getNombre();

            // usar guion bajo si el nombre es nulo
            if (nombre == null) {
                return "_";
            }

            return nombre;

        }

        return "_";
    }

    // visitar el atributo simple sin generar cuartetas
    public String visitarAtributoSimple(AtributoSimple nodo) {
        // no genera cuarteta por si solo
        return null;
    }

    // visitar el atributo arreglo sin generar cuartetas
    public String visitarAtributoArray(AtributoArray nodo) {
        // no genera cuarteta por si solo
        return null;
    }
}
