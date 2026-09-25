%funciones
definir unir(entero n, flotante f, caracter c, booleano b) -> cadena:
    cadena r = "n=" + n
    r = r + " f=" + f
    r = r + c + b
    retornar r
definir principal() -> entero:
    cadena s = "hola" + " " + "mundo"
    cadena t = unir(7, 2.5, 'x', verdadero)
    imprimir(s)
    imprimir(t)
    retornar 0
