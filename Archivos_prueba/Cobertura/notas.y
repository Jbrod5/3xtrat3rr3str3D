%estructuras
estructura Materia:
    cadena nombre
    entero creditos

estructura Reporte:
    cadena titulo
    entero total
    flotante promedio
    Materia materia

%funciones
definir promedio3(entero a, entero b, entero c) -> flotante:
    entero suma = a + b + c
    retornar suma / 3

definir clasificarY(entero nota) -> cadena:
    si (nota >= 90) entonces
        retornar "A"
    sino (nota >= 70) entonces
        retornar "B"
    sino (nota >= 61) entonces
        retornar "C"
    contrario
        retornar "F"

definir contarPares(entero n) -> entero:
    entero total = 0
    para (entero i = 0; i < n; i++):
        si (i == 3) entonces
            continuar
        si (i == 8) entonces
            romper
        total = total + i
    retornar total

definir bajarHasta(entero n) -> entero:
    entero i = n
    mientras (i > 0) hacer
        i--
    retornar i

definir subirDesde(entero n) -> entero:
    entero i = n
    hacer:
        i++
    mientras (i < 3)
    retornar i

definir elegirNota(entero total) -> entero:
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
    retornar total

definir usarMatriz() -> entero:
    entero m[3][2] = {{1, 2}, {3, 4}, {5, 6}}
    entero n[2][2]
    n[0] = m[1]
    retornar n[0]

definir factorialY(entero n) -> entero:
    si (n <= 1) entonces
        retornar 1
    contrario
        retornar n * factorialY(n - 1)

definir eco() -> cadena:
    cadena linea = leer()
    imprimir(linea)
    retornar linea
