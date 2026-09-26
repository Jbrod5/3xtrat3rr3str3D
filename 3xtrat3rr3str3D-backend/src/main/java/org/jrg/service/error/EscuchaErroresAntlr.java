package org.jrg.service.error;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.jrg.model.error.TipoError;

/**
 * Adaptar los errores generados por ANTLR hacia el recolector del proyecto.
 */
public class EscuchaErroresAntlr extends BaseErrorListener {

    private final RecolectorErrores recolector;
    private final TipoError tipoError;

    /**
     * Crear una escucha que dirige los errores hacia el recolector indicado.
     */
    public EscuchaErroresAntlr(RecolectorErrores recolector, TipoError tipoError) {
        this.recolector = recolector;
        this.tipoError = tipoError;
    }

    @Override
    public void syntaxError(
            Recognizer<?, ?> recognizer,
            Object offendingSymbol,
            int line,
            int charPositionInLine,
            String msg,
            RecognitionException e) {
        // poner la columna en base 1 y pasarla al recolector
        this.recolector.agregar(this.tipoError, line, charPositionInLine + 1, msg);
    }
}