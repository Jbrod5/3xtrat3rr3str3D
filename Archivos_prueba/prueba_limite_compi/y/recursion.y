%funciones

definir factorial(entero n) -> entero:
    si (n == 0) entonces
        retornar 1
    contrario
        retornar n * factorial(n - 1)

definir fibonacci(entero n) -> entero:
    si (n < 2) entonces
        retornar n
    contrario
        retornar fibonacci(n - 1) + fibonacci(n - 2)