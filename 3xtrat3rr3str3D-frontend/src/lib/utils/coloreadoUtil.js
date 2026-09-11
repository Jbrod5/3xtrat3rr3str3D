/* Utilitario para procesar la respuesta de coloreado del backend
   y convertirla en decoraciones compatibles con Monaco Editor.
   
   Formato esperado del backend (POST /colores):
   {
     "colores": [
       { "start": 0, "length": 5, "category": "keyword" },
       { "start": 7, "length": 7, "category": "identifier" }
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

/**
 * Convierte la respuesta del backend (start/length) a decoraciones Monaco (range)
 * @param {Array} coloresBackend - Array de {start, length, category}
 * @param {Object} monaco - Referencia al objeto monaco
 * @returns {Array} Decoraciones para Monaco Editor
 */
export function convertirAColorBackend(coloresBackend, monaco) {
  if (!coloresBackend || !Array.isArray(coloresBackend)) {
    return [];
  }

  return coloresBackend.map((item) => {
    const clase = mapearTipoAClase(item.category);
    // El backend devuelve start/length, necesitamos convertir a Range
    // Para esto necesitamos el modelo del editor para calcular line/column
    // Retornamos un objeto con la info necesaria para que el editor la convierta
    return {
      start: item.start,
      length: item.length,
      category: item.category,
      className: clase
    };
  });
}

/**
 * Convierte posiciones absolutas (start/length) a rangos Monaco
 * @param {Array} tokens - Tokens con start/length/category
 * @param {monaco.editor.ITextModel} modelo - Modelo del editor
 * @param {Object} monaco - Referencia al objeto monaco
 * @returns {Array} Decoraciones Monaco
 */
export function convertirTokensADecoraciones(tokens, modelo, monaco) {
  if (!tokens || !Array.isArray(tokens) || !modelo) {
    return [];
  }

  return tokens.map((item) => {
    const clase = mapearTipoAClase(item.category);
    const startPos = modelo.getPositionAt(item.start);
    const endPos = modelo.getPositionAt(item.start + item.length);
    return {
      range: new monaco.Range(
        startPos.lineNumber,
        startPos.column,
        endPos.lineNumber,
        endPos.column
      ),
      options: { inlineClassName: clase }
    };
  });
}