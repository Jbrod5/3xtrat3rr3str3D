%estructuras
estructura Punto:
    entero x
    entero y
    cadena nombre
    flotante peso
    entero vals[3]

%funciones
definir factorial(entero n) -> entero:
    si (n <= 1) entonces
        retornar 1
    retornar n * factorial(n - 1)
definir procesar() -> entero:
    entero total = 0
    entero i = 0
    para (entero k = 0; k < 5; k++):
        si (k == 2) entonces
            continuar
        si (k == 4) entonces
            romper
        total = total + k
    mientras (i < 3) hacer
        i++
    elegir (total):
        caso 0:
            total = 1
            romper
        caso 25:
            total = 2
            romper
        siempre:
            total = 3
            romper
    hacer:
        total = total + 10
    mientras (total > 1000)
    retornar total
definir principal() -> entero:
    entero f = factorial(5)
    entero p = procesar()
    Punto q
    q.x = 1
    entero m = 0
    si (f > 100) entonces
        m = 1
    sino (f > 10) entonces
        m = 2
    contrario
        m = 3
    imprimir(f)
    imprimir("fin")
    retornar m
