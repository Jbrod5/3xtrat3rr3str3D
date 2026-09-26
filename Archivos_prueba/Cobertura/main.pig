import Estudiante.z
import notas.y

VARIABILES>
esto opcion : numerus -1;
esto lectura : numerus 0;
esto cant : numerus 0;
esto nota : decimalis 0;
esto inicial : littera 'A';
esto encendido : bool verum;
esto titulo : textum "Cobertura total";
esto e1 : novus Estudiante("Ana", 101);
esto e2 : novus Estudiante();
series grupo[3] : Estudiante;
series m2[2] : numerus;
series m3[2][3] : numerus {{3, 2, 1}, {4, 5, 6}};
esto mat : Materia {"Mate", 5};
esto rep : Reporte {"Final", 3, 85.5, mat};

MAIOR>
>> titulo;
>> inicial;
grupo[0] = e1;
grupo[1] = e2;
grupo[0].setNota(0, 90);
grupo[0].setNota(1, 80);
grupo[0].setNota(2, 70);
grupo[0].setNota(3, 60);
m2[0] = 7;
m2[1] = m2[0] + 1;
>> rep.titulo + " " + rep.total;
nota = grupo[0].promedio();
>> nota;
dum (opcion != 6) {
    >> "1.Notas 2.Clasificar 3.Matrices 4.Y 5.Nulo 6.Salir";
    opcion <<
    si (opcion == 1) {
        >> grupo[0].toString();
        >> grupo[0].promedio();
        si (grupo[0].aprobar()) {
            >> "Aprobada";
        } aliter {
            >> "Reprobada";
        } finis;
        >> grupo[0].clasificar();
        >> grupo[0].contarAltas();
        >> grupo[0].buscar(70);
        >> grupo[0].getNota(0);
        >> grupo[0].factorial(5);
        grupo[0].ajustar(2.5);
    } aliter (opcion == 2) {
        >> "Carnet:";
        lectura <<
        e2.nombre = "Beto";
        e2.carnet = lectura;
        >> e2.toString();
        per (esto i : numerus 0; i < 10; i++) {
            si (i == 3) {
                perge;
            } finis;
            si (i == 7) {
                interrumpe;
            } finis;
            >> i;
        } finis;
    } aliter (opcion == 3) {
        cant = m3[1];
        >> cant;
        >> m2[1];
        facere {
            cant = cant + 1;
        } dum (cant < 2);
        >> cant;
    } aliter (opcion == 4) {
        >> promedio3(10, 20, 30);
        >> clasificarY(85);
        >> contarPares(10);
        >> bajarHasta(3);
        >> subirDesde(0);
        >> elegirNota(25);
        >> usarMatriz();
        >> factorialY(5);
        >> eco();
    } aliter (opcion == 5) {
        si (e1.nuloEsNulo() && encendido) {
            >> "Nulo bien";
        } aliter {
            >> "Nulo mal";
        } finis;
    } aliter (opcion == 6) {
        >> "Fin cobertura";
    } finis;
} finis;

FINIS;
