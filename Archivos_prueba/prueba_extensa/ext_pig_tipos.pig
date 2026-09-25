VARIABILES>
esto a : numerus 7;
esto b : decimalis 2.0;

MAIOR>
a = a + 3;
a = a - 1;
a = a * 2;
a = a / 2;
b = b + a;
b = a + b;
si (a >= 5 && b < 10.0) {
    >> "rango";
} finis;
si (a == 7 || b != 1.0) {
    >> "igual";
} finis;
a++;
++a;
a--;
--a;
>> a;
>> b;
FINIS;
