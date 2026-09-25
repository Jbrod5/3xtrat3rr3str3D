%funciones

definir clasificar(entero i, entero j) -> entero:
    si (i == j) entonces
        retornar 1
    contrario
        si (i < j) entonces
            retornar 10
        contrario
            retornar 100

definir sumarTodo() -> entero:
    entero suma = 0
    para (entero i = 0; i < 3; i++):
        para (entero j = 0; j < 3; j++):
            suma = suma + clasificar(i, j)
    retornar suma