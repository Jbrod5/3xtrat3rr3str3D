##
    Importaciones de los otros lenguajes
##
import Funciones.y
import Contador.z

##
    Variables globales del programa
##
VARIABILES>
esto total : numerus 0;
esto suma : numerus 0;
esto i : numerus 0;
esto mensaje : textum "Resultado";
esto contador : novus Contador(0);
series numeros[5] : numerus {10, 20, 30, 40, 50};

MAIOR>
saludo();
>> "Probando arreglos, objetos y funciones importadas";

## Ciclo con iterador: recorre el arreglo y suma sus elementos ##
per (esto j : numerus 0; j < 5; j++) {
    suma = suma + numeros[j];
} finis;

>> mensaje >> suma;

## Llamada a funcion importada del .y, con el resultado del arreglo ##
total = sumar(suma, 100);
>> "Total tras sumar 100 a la suma del arreglo: " >> total;

## Ciclo dum usando un objeto Zetariano ##
dum (i < 3) {
    contador.incrementar();
    i = i + 1;
} finis;

>> "Valor final del contador (deberia ser 3): " >> contador.obtenerValor();

## Condicional con aliter, usando el resultado del arreglo ##
si (suma > 100) {
    >> "La suma del arreglo es mayor a 100";
} aliter {
    >> "La suma del arreglo es menor o igual a 100";
} finis;

## Modificar un elemento del arreglo y volver a imprimirlo ##
numeros[0] = numeros[0] * 2;
>> "Primer elemento del arreglo tras multiplicarlo por 2: " >> numeros[0];

FINIS;
