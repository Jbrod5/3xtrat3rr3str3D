VARIABILES>
esto suma : numerus 0;

MAIOR>
per (esto i : numerus 0; i < 3; i++) {
    per (esto j : numerus 0; j < 3; j++) {
        si (i == j) {
            suma = suma + 1;
        } aliter {
            si (i < j) {
                suma = suma + 10;
            } aliter {
                suma = suma + 100;
            } finis;
        } finis;
    } finis;
} finis;
>> suma;
FINIS;