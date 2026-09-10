/* Utilitario para procesar la respuesta de coloreado del backend
   y convertirla en decoraciones compatibles con Monaco Editor.
   
   Formato esperado del backend (POST /colores):
   {
     "colores": [
       { "lineaInicio": 1, "colInicio": 1, "lineaFin": 1, "colFin": 5, "tipo": "keyword" },
       { "lineaInicio": 1, "colInicio": 7, "lineaFin": 1, "colFin": 14, "tipo": "identifier" }
     ]
   }
*/

const mapaTipoAClase = {
  keyword: 'lc-1',
  type: 'lc-2',
  identifier: 'lc-3',
  number: 'lc-4',
  string: 'lc-5',
  character: 'lc-6',
  comment: 'lc-7',
  operator: 'lc-8',
  bracket: 'lc-9',
  delimiter: 'lc-10',
  boolean: 'lc-1',
  function: 'lc-8'
};

export function mapearTipoAClase(tipo) {
  return mapaTipoAClase[tipo] || 'lc-3';
}

export function convertirAColorBackend(coloresBackend, monaco) {
  if (!coloresBackend || !Array.isArray(coloresBackend)) {
    return [];
  }

  return coloresBackend.map((item) => {
    const clase = mapearTipoAClase(item.tipo);
    return {
      range: new monaco.Range(
        item.lineaInicio,
        item.colInicio,
        item.lineaFin,
        item.colFin
      ),
      options: { inlineClassName: clase }
    };
  });
}