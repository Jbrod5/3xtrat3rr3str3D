package org.jrg.util;

import java.util.UUID;

public final class MiniUUID {

    private MiniUUID() {
    }

    /**
     * Generar un identificador unico.
     */
    public static String generate() {
        return UUID.randomUUID().toString();
    }
}
