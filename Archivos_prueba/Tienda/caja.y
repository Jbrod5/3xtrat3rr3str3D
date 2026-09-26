%estructuras
estructura Linea:
    cadena nombre
    entero cantidad
    flotante subtotal

%funciones
definir descuento(entero cantidad) -> flotante:
    si (cantidad >= 10) entonces
        retornar 0.15
    contrario
        retornar 0.0

definir totalLinea(flotante precio, entero cantidad) -> flotante:
    retornar precio * cantidad
