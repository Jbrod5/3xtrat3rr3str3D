import { writable } from 'svelte/store';

function generarId() {
  return Math.random().toString(36).substring(2, 11);
}

function crearEstadoInicial() {
  const archivoInicial = {
    id: generarId(),
    nombre: 'principal.lat',
    ruta: 'principal.lat',      // ruta absoluta o relativa; se sobreescribe al abrir
    contenido: '',
    resultado: null,
    cursor: { linea: 1, columna: 1 },
    abierto: true
  };
  return {
    rutaBaseProyecto: null,
    archivos: [archivoInicial],
    archivoActivoId: archivoInicial.id,
    panelInferiorAbierto: true,
    panelDerechoAbierto: false,
    pestanaInferiorActiva: 'resultados',
    pestanaDerechaActiva: 'pila',
    alturaPanelInferior: 200,
    anchoPanelDerecho: 320,
    anchoSidebar: 220
  };
}

function crearIdeStore() {
  const { subscribe, set, update } = writable(crearEstadoInicial());

  return {
    subscribe,

    setRutaBaseProyecto: (ruta) => update(s => ({ ...s, rutaBaseProyecto: ruta })),

    activarArchivo: (id) => update(s => ({
      ...s,
      archivoActivoId: id,
      archivos: s.archivos.map(a => a.id === id ? { ...a, abierto: true } : a)
    })),

    actualizarContenido: (id, contenido) => update(s => ({
      ...s,
      archivos: s.archivos.map(a => a.id === id ? { ...a, contenido } : a)
    })),

    actualizarResultado: (id, resultado) => update(s => ({
      ...s,
      archivos: s.archivos.map(a => a.id === id ? { ...a, resultado } : a)
    })),

    actualizarCursor: (id, linea, columna) => update(s => ({
      ...s,
      archivos: s.archivos.map(a => a.id === id ? { ...a, cursor: { linea, columna } } : a)
    })),

    // crearArchivo(nombre, ruta) -> ruta puede ser absoluta o relativa
    crearArchivo: (nombre, ruta) => {
      const id = generarId();
      const rutaFinal = (ruta || nombre).replace(/\\/g, '/');
      let idExistente = null;

      update(s => {
        const existente = s.archivos.find(a => a.ruta === rutaFinal);
        if (existente) {
          idExistente = existente.id;
          return {
            ...s,
            archivoActivoId: existente.id,
            archivos: s.archivos.map(a =>
              a.id === existente.id ? { ...a, abierto: true } : a
            )
          };
        }
        const nuevo = {
          id,
          nombre,
          ruta: rutaFinal,
          contenido: '',
          resultado: null,
          cursor: { linea: 1, columna: 1 },
          abierto: true
        };
        return { ...s, archivos: [...s.archivos, nuevo], archivoActivoId: id };
      });

      return idExistente || id;
    },

    eliminarArchivo: (id) => update(s => {
      const filtrados = s.archivos.filter(a => a.id !== id);
      if (filtrados.length === 0) {
        const nuevo = {
          id: generarId(),
          nombre: 'sin_titulo.lat',
          ruta: 'sin_titulo.lat',
          contenido: '',
          resultado: null,
          cursor: { linea: 1, columna: 1 },
          abierto: true
        };
        return { ...s, archivos: [nuevo], archivoActivoId: nuevo.id };
      }
      const nuevoActivo = s.archivoActivoId === id ? filtrados[0].id : s.archivoActivoId;
      return { ...s, archivos: filtrados, archivoActivoId: nuevoActivo };
    }),

    cerrarPestana: (id) => update(s => {
      const nuevosArchivos = s.archivos.map(a =>
        a.id === id ? { ...a, abierto: false } : a
      );
      let nuevoActivoId = s.archivoActivoId;
      if (s.archivoActivoId === id) {
        const abiertos = nuevosArchivos.filter(a => a.abierto);
        nuevoActivoId = abiertos.length > 0 ? abiertos[abiertos.length - 1].id : null;
      }
      return { ...s, archivos: nuevosArchivos, archivoActivoId: nuevoActivoId };
    }),

    alternarPanelInferior: () => update(s => ({ ...s, panelInferiorAbierto: !s.panelInferiorAbierto })),
    alternarPanelDerecho: () => update(s => ({ ...s, panelDerechoAbierto: !s.panelDerechoAbierto })),
    fijarAlturaInferior: (altura) => update(s => ({ ...s, alturaPanelInferior: Math.max(100, altura) })),
    fijarAnchoDerecho: (ancho) => update(s => ({ ...s, anchoPanelDerecho: Math.max(180, ancho) })),
    fijarAnchoSidebar: (ancho) => update(s => ({ ...s, anchoSidebar: Math.max(150, ancho) })),
    cambiarPestanaInferior: (pestana) => update(s => ({ ...s, pestanaInferiorActiva: pestana })),
    cambiarPestanaDerecha: (pestana) => update(s => ({ ...s, pestanaDerechaActiva: pestana }))
  };
}

export const ideStore = crearIdeStore();