%estructuras

estructura Punto:
    entero x
    entero y

estructura Persona:
    cadena nombre
    entero edad

%funciones

definir distanciaCuadrada(entero x1, entero y1, entero x2, entero y2) -> entero:
    entero dx = x2 - x1
    entero dy = y2 - y1
    retornar dx * dx + dy * dy