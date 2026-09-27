import Caja.z
import Fabrica.z

VARIABILES>
esto caja : novus Caja(7);
esto vacia : Caja null;
esto fabrica : novus Fabrica();
esto nula : Caja null;
esto real : Caja null;

MAIOR>
si (vacia == null) {
    >> "1 vacia es nula";
} aliter {
    >> "1 vacia NO es nula";
} finis;

si (caja == null) {
    >> "2 caja es nula";
} aliter {
    >> "2 caja NO es nula";
} finis;

caja = null;

si (caja == null) {
    >> "3 caja es nula";
} aliter {
    >> "3 caja NO es nula";
} finis;

nula = fabrica.crearNula();

si (nula == null) {
    >> "4 nula es nula";
} aliter {
    >> "4 nula NO es nula";
} finis;

real = fabrica.crearReal();

si (real == null) {
    >> "5 real es nula";
} aliter {
    >> "5 real NO es nula";
} finis;

si (real != null) {
    >> "6 real distinto de nulo";
} aliter {
    >> "6 real igual a nulo";
} finis;

FINIS;
