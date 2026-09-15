package org.jrg.util;

import java.util.UUID;

public final class MiniUUID {

    private MiniUUID() {
    }

    /**
     * Generar un identificador único de 8 caracteres dividido en dos tramos de 4 separados por "-".
     */
    public static String generate() {
        // Generamos el UUID y eliminamos los guiones originales
        String fullUuid = UUID.randomUUID().toString().replace("-", "");

        // Tomamos los primeros 8 caracteres :D
        String part1 = fullUuid.substring(0, 4);
        String part2 = fullUuid.substring(4, 8);

        // Unimos los dos tramos con el guion :D
        return part1 + "-" + part2;
    }
}
