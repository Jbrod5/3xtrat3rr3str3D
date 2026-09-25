VARIABILES>
esto total : numerus 0;
esto texto : textum "ini";
esto real : decimalis 0.5;
esto letra : littera 'a';
esto flag : bool falsus;

MAIOR>
total = 0;
dum (total < 3) {
    total++;
    si (total == 2) {
        >> "dos";
    } aliter {
        >> "otro";
    } finis;
} finis;
facere {
    total = total + 10;
    si (total > 100) {
        interrumpe;
    } finis;
} dum (total < 5);
per (esto i : numerus 0; i < 4; i = i + 1) {
    si (i == 1) {
        perge;
    } finis;
    total = total + i;
} finis;
>> "total:";
>> total
texto = texto + " fin";
>> texto;
real = real + 1.5;
>> real;
flag = verum;
>> flag;
<<;
total <<;
FINIS;
