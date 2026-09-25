%funciones
definir esParY(entero n) -> booleano:
    si (n == 0) entonces
        retornar verdadero
    retornar esImparY(n - 1)
definir esImparY(entero n) -> booleano:
    si (n == 0) entonces
        retornar falso
    retornar esParY(n - 1)
definir principal() -> entero:
    booleano r = esParY(4)
    imprimir(r)
    retornar 0
