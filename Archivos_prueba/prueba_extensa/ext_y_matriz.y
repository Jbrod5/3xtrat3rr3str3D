%funciones
definir usar() -> entero:
    entero m[3][2] = {{1, 2}, {3, 4}, {5, 6}}
    entero n[2][2]
    n[0] = m[1]
    retornar n[0]
definir principal() -> entero:
    entero r = usar()
    imprimir(r)
    retornar r
