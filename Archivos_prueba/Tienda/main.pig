import Producto.z
import caja.y

VARIABILES>
esto opcion : numerus -1;
esto lectura : numerus 0;
esto cant : numerus 0;
esto total : decimalis 0;
esto linea : decimalis 0;
esto p1 : novus Producto("Manzana", 2.5, 10);
esto p2 : novus Producto("Pan", 1.25, 20);
esto p3 : novus Producto("Leche", 3.0, 5);
series catalogo[3] : Producto;

MAIOR>
>> "=== Tienda La Esquina ===";
catalogo[0] = p1;
catalogo[1] = p2;
catalogo[2] = p3;
dum (opcion != 4) {
    >> "1. Vender  2. Reponer  3. Ver todo  4. Salir";
    opcion <<
    si (opcion == 1) {
        >> "Numero de producto (0-2):";
        lectura <<
        >> "Cantidad:";
        cant <<
        si (catalogo[lectura].cantidad >= cant) {
            catalogo[lectura].vender(cant);
            linea = totalLinea(catalogo[lectura].precio, cant);
            >> "Subtotal: " + linea;
            linea = linea - linea * descuento(cant);
            >> "Total con descuento: " + linea;
            total = total + linea;
        } aliter {
            >> "Sin stock suficiente";
        } finis;
    } aliter (opcion == 2) {
        >> "Numero de producto (0-2):";
        lectura <<
        >> "Cantidad:";
        cant <<
        catalogo[lectura].reponer(cant);
        >> "Listo, ahora hay: " + catalogo[lectura].cantidad;
    } aliter (opcion == 3) {
        per (esto i : numerus 0; i < 3; i++) {
            >> catalogo[i].toString() + " vale " + catalogo[i].valorTotal();
        } finis;
    } aliter (opcion == 4) {
        >> "Gracias por su compra";
    } finis;
    >> "Caja: " + total;
} finis;

FINIS;
