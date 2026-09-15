<script>
  import { onDestroy, onMount } from 'svelte';
  import { ideStore } from '../lib/stores/ideStore';
  import { analizarCodigo, traducirCodigo } from '../lib/services/analizadorService.js';

  import MonacoEditor from './MonacoEditor.svelte';
  import ExploradorArchivos from './ExploradorArchivos.svelte';
  import Terminal from './PanelInferior.svelte';
  import PilaAnalisis from './PilaAnalisis.svelte';
  import Separador from './Separador.svelte';

  const URL_BASE = 'http://localhost:7070';

  let estado;
  let archivoActivo;
  let refEditor;
  let traduciendo = false;

  let compilandoProyecto = false;
  let resultadoProyecto = null;

  const desuscribir = ideStore.subscribe((s) => {
    estado = s;
    archivoActivo = s.archivos.find(a => a.id === s.archivoActivoId);
  });

  onMount(() => {});
  onDestroy(() => { if (desuscribir) desuscribir(); });

  function normalizar(p) { return (p || '').replace(/\\/g, '/'); }
  function sinSlashFinal(p) { return p.replace(/\/+$/, ''); }

  // ================= Abrir carpeta (proyecto) =================
  // 1. Pide la ruta absoluta
  // 2. Backend la lista y devuelve [{archivo, ruta, contenido}]
  // 3. Frontend guarda rutaBaseProyecto y cada archivo con su ruta absoluta
  async function abrirProyectoCarpeta() {
    const base = prompt(
      'Ruta absoluta de la carpeta raíz del proyecto:',
    );
    if (!base) return;

    const baseNorm = sinSlashFinal(normalizar(base.trim()));

    try {
      const res = await fetch(`${URL_BASE}/api/archivos/proyecto`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ ruta: baseNorm })
      });

      if (!res.ok) throw new Error(`HTTP ${res.status}`);

      const data = await res.json();
      if (!data.exito) throw new Error(data.mensaje || 'error al cargar proyecto');

      ideStore.setRutaBaseProyecto(baseNorm);

      if (!data.archivos || data.archivos.length === 0) {
        alert('La carpeta está vacía o no se encontraron archivos.');
        return;
      }

      for (const item of data.archivos) {
        // item = { archivo, ruta, contenido }
        const id = ideStore.crearArchivo(item.archivo, normalizar(item.ruta));
        ideStore.actualizarContenido(id, item.contenido || '');
      }

      // Activar el primer archivo
      if (data.archivos.length > 0) {
        // el store ya activó el último creado, está bien
      }

      console.log(`Proyecto cargado: ${data.archivos.length} archivos desde ${baseNorm}`);
    } catch (err) {
      alert('Error al cargar proyecto: ' + err.message);
    }
  }

  // ================= Abrir archivo individual =================
  async function abrirArchivoLocal() {
    const ruta = prompt(
      'Ruta absoluta del archivo:'
    );
    if (!ruta) return;

    const rutaNorm = normalizar(ruta.trim());

    try {
      const res = await fetch(`${URL_BASE}/api/archivos/leer`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ ruta: rutaNorm })
      });

      if (!res.ok) throw new Error(`HTTP ${res.status}`);
      const data = await res.json();
      if (!data.exito) throw new Error(data.mensaje || 'error al leer archivo');

      const nombre = rutaNorm.split('/').pop();
      const id = ideStore.crearArchivo(nombre, rutaNorm);
      setTimeout(() => {
        ideStore.actualizarContenido(id, data.contenido || '');
        ideStore.activarArchivo(id);
      }, 30);
    } catch (err) {
      alert('Error al abrir archivo: ' + err.message);
    }
  }

  // ================= Compilar / Traducir =================
  // detectar el lenguaje segun la extension del archivo activo
function detectarLenguajeArchivo(nombreArchivo) {
  if (!nombreArchivo) {
    return 'piglatin';
  }
  const partes = nombreArchivo.split('.');
  if (partes.length < 2) {
    return 'piglatin';
  }
  const ext = partes[partes.length - 1].toLowerCase();
  if (ext === 'z' || ext === 'zet') {
    return 'zetariano';
  }
  if (ext === 'y') {
    return 'y';
  }
  return 'piglatin';
}

async function enviarCompilacion() {
  if (!archivoActivo) return;
  const lenguaje = detectarLenguajeArchivo(archivoActivo.nombre);
  try {
    const resultado = await analizarCodigo(archivoActivo.contenido, lenguaje, archivoActivo.ruta);
    ideStore.actualizarResultado(archivoActivo.id, resultado);
    ideStore.cambiarPestanaInferior(resultado.exito ? 'resultados' : 'errores');
    if (!estado.panelInferiorAbierto) ideStore.alternarPanelInferior();
  } catch (err) {
    ideStore.actualizarResultado(archivoActivo.id, {
      exito: false, arbolSintactico: null, astMermaid: null, codigoPigLatin: null,
      simbolos: [], tipos: [], pasosPila: [],
      errores: [{ mensaje: 'Fallo de conexion con el servidor: ' + err.message }]
    });
    ideStore.cambiarPestanaInferior('errores');
    if (!estado.panelInferiorAbierto) ideStore.alternarPanelInferior();
  }
}

async function enviarTraduccion() {
  if (!archivoActivo) return;
  const lenguaje = detectarLenguajeArchivo(archivoActivo.nombre);
  try {
    const resultado = await traducirCodigo(archivoActivo.contenido, lenguaje, archivoActivo.ruta);
    ideStore.actualizarResultado(archivoActivo.id, resultado);
    ideStore.cambiarPestanaInferior('resultados');
    if (!estado.panelInferiorAbierto) ideStore.alternarPanelInferior();
  } catch (err) {
    ideStore.actualizarResultado(archivoActivo.id, {
      exito: false, arbolSintactico: null, astMermaid: null, codigoPigLatin: null,
      simbolos: [], tipos: [], pasosPila: [],
      errores: [{ mensaje: 'Fallo de conexion con el servidor: ' + err.message }]
    });
    ideStore.cambiarPestanaInferior('errores');
    if (!estado.panelInferiorAbierto) ideStore.alternarPanelInferior();
  }
}

  // compilar todos los archivos .pig del proyecto y agregar resultados
  async function compilarProyecto() {
    if (!estado.rutaBaseProyecto) {
      alert('Primero abre una carpeta de proyecto para poder compilar.');
      return;
    }
    if (compilandoProyecto) return;
    compilandoProyecto = true;
    // recolectar los archivos piglatin del proyecto
    const archivosPig = estado.archivos.filter(a => a.nombre.endsWith('.pig'));
    if (archivosPig.length === 0) {
      alert('No hay archivos .pig en el proyecto.');
      compilandoProyecto = false;
      return;
    }
    // acumular errores de todos los archivos
    const erroresTotales = [];
    let exitoTotal = true;
    for (let i = 0; i < archivosPig.length; i++) {
      const archivo = archivosPig[i];
      const lenguaje = 'piglatin';
      try {
        const resultado = await analizarCodigo(archivo.contenido, lenguaje, archivo.ruta);
        if (!resultado.exito) {
          exitoTotal = false;
          for (let j = 0; j < resultado.errores.length; j++) {
            const err = resultado.errores[j];
            erroresTotales.push({
              archivo: archivo.nombre,
              linea: err.linea,
              columna: err.columna,
              tipo: err.tipo,
              mensaje: err.mensaje
            });
          }
        }
      } catch (err) {
        exitoTotal = false;
        erroresTotales.push({
          archivo: archivo.nombre,
          linea: 0,
          columna: 0,
          tipo: 'CONEXION',
          mensaje: err.message
        });
      }
    }
    // guardar el resultado agregado en el archivo activo
    if (archivoActivo) {
      ideStore.actualizarResultado(archivoActivo.id, {
        exito: exitoTotal,
        errores: erroresTotales,
        arbolSintactico: null,
        astMermaid: null,
        codigoPigLatin: null,
        simbolos: [],
        tipos: [],
        pasosPila: []
      });
    }
    ideStore.cambiarPestanaInferior(erroresTotales.length > 0 ? 'errores' : 'resultados');
    if (!estado.panelInferiorAbierto) ideStore.alternarPanelInferior();
    compilandoProyecto = false;
    alert('Proyecto compilado. Archivos: ' + archivosPig.length + ' Errores: ' + erroresTotales.length);
  }

  // ================= .pig =================
  function descargarPig() {
    if (!archivoActivo?.resultado?.codigoPigLatin) {
      alert('No hay traduccion PigLatin disponible. Traduce primero.');
      return;
    }
    const blob = new Blob([archivoActivo.resultado.codigoPigLatin], { type: 'text/plain' });
    const url = URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
//    a.download = archivoActivo.nombre.replace('.lat', '.pig');
    // usar extension .z para zetariano
    a.download = archivoActivo.nombre.replace('.z', '.pig');
    a.click();
    URL.revokeObjectURL(url);
  }

  function abrirArchivoPig(contenido) {
    const nombreBase = archivoActivo.nombre.replace(/\.[^.]+$/, '');
    const nombrePig = nombreBase + '.pig';

    const rutaLat = normalizar(archivoActivo.ruta || '');
    const idx = rutaLat.lastIndexOf('/');
    const rutaPig = idx >= 0
      ? rutaLat.substring(0, idx) + '/' + nombrePig
      : nombrePig;

    const id = ideStore.crearArchivo(nombrePig, rutaPig);
    setTimeout(() => {
      ideStore.actualizarContenido(id, contenido);
      ideStore.activarArchivo(id);
    }, 50);
  }

  async function verPig() {
    if (!archivoActivo) { alert('No hay archivo activo.'); return; }
    if (archivoActivo.resultado?.codigoPigLatin) {
      abrirArchivoPig(archivoActivo.resultado.codigoPigLatin);
      return;
    }
    if (traduciendo) return;
    try {
      traduciendo = true;
      const resultado = await traducirCodigo(archivoActivo.contenido);
      ideStore.actualizarResultado(archivoActivo.id, resultado);
      if (resultado.codigoPigLatin) abrirArchivoPig(resultado.codigoPigLatin);
      else alert('La traducción no generó código PigLatin.');
    } catch (err) {
      alert('Error al traducir: ' + err.message);
    } finally {
      traduciendo = false;
    }
  }

  // ================= Guardar =================
  // Envía {archivo, ruta, contenido}. Nunca pregunta ruta.
  async function guardarArchivo() {
    if (!archivoActivo) return;

    let contenidoActual = archivoActivo.contenido;
    if (refEditor && typeof refEditor.obtenerValor === 'function') {
      contenidoActual = refEditor.obtenerValor();
      ideStore.actualizarContenido(archivoActivo.id, contenidoActual);
    }

    const ruta = normalizar(archivoActivo.ruta || archivoActivo.nombre);

    try {
      const res = await fetch(`${URL_BASE}/api/archivos/guardar`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          archivo: archivoActivo.nombre,
          ruta,
          contenido: contenidoActual
        })
      });

      if (!res.ok) {
        const err = await res.json().catch(() => ({}));
        throw new Error(err.mensaje || `HTTP ${res.status}`);
      }

      console.log('Guardado:', ruta);
      alert('Guardado en:\n' + ruta);
    } catch (err) {
      alert('Error al guardar: ' + err.message);
    }
  }

  // Hooks expuestos al explorador para abrir carpeta/archivo
  function abrirProyectoDesdeExplorador() { abrirProyectoCarpeta(); }
  function abrirArchivoDesdeExplorador() { abrirArchivoLocal(); }
</script>

<div class="ide d-flex flex-column vh-100 overflow-hidden bg-white text-dark">
  <nav class="navbar navbar-expand navbar-light bg-white px-3 border-bottom" style="height: 48px; flex-shrink: 0; border-color: #e9ecef !important;">
    <span class="navbar-brand mb-0 h1 text-dark">Codex-Latinus</span>
    <div class="ms-auto d-flex gap-2 align-items-center">
      <button class="btn btn-primary btn-sm" on:click={enviarCompilacion}>
        <i class="bi bi-play-fill"></i> Compilar
      </button>
      <button class="btn btn-success btn-sm" on:click={compilarProyecto} disabled={compilandoProyecto}>
        <i class="bi bi-collection"></i> Compilar Proyecto
      </button>
      <button class="btn btn-outline-secondary btn-sm" on:click={enviarTraduccion}>
        <i class="bi bi-translate"></i> Traducir
      </button>
      <div class="vr mx-1" style="height: 24px;"></div>
      <button class="btn btn-outline-secondary btn-sm" on:click={abrirArchivoLocal}>
        <i class="bi bi-folder-open"></i> Abrir
      </button>
      <button class="btn btn-outline-secondary btn-sm" on:click={guardarArchivo}>
        <i class="bi bi-save"></i> Guardar
      </button>
      <button class="btn btn-outline-secondary btn-sm" on:click={verPig}>
        <i class="bi bi-eye"></i> Ver .pig
      </button>
      <button class="btn btn-outline-secondary btn-sm" on:click={descargarPig}>
        <i class="bi bi-download"></i> .pig
      </button>
    </div>
  </nav>

  <div class="d-flex flex-grow-1 overflow-hidden">
    <ExploradorArchivos
      {estado}
      {archivoActivo}
      on:abrir={abrirArchivoDesdeExplorador}
      on:abrirCarpeta={abrirProyectoDesdeExplorador}
    />

    <Separador direccion="vertical" valor={estado.anchoSidebar} onRedimensionar={(v) => ideStore.fijarAnchoSidebar(v)} />

    <main class="d-flex flex-column flex-grow-1 overflow-hidden bg-white">
      <!-- Pestañas -->
      <div class="d-flex border-bottom bg-light" style="height: 36px; flex-shrink: 0; overflow-x: auto; border-color: #e9ecef !important;">
        {#each estado.archivos.filter(a => a.abierto) as archivo (archivo.id)}
          <div
            class="d-flex align-items-center gap-2 px-3 border-end small cursor-pointer pestana"
            class:bg-white={archivo.id === estado.archivoActivoId}
            class:text-dark={archivo.id === estado.archivoActivoId}
            class:text-secondary={archivo.id !== estado.archivoActivoId}
            class:fw-semibold={archivo.id === estado.archivoActivoId}
            on:click={() => ideStore.activarArchivo(archivo.id)}
            style="border-color: #e9ecef !important;"
          >
            <span>{archivo.nombre.split('/').pop()}</span>
            <button
              class="btn btn-sm btn-link text-secondary p-0 lh-1"
              on:click|stopPropagation={() => ideStore.cerrarPestana(archivo.id)}
            >
              <i class="bi bi-x"></i>
            </button>
          </div>
        {/each}
      </div>

      <div class="flex-grow-1 overflow-hidden">
        <MonacoEditor bind:this={refEditor} />
      </div>

      {#if estado.panelInferiorAbierto}
        <Separador direccion="horizontal" valor={estado.alturaPanelInferior} onRedimensionar={(v) => ideStore.fijarAlturaInferior(v)} />
        <div style="height: {estado.alturaPanelInferior}px; flex-shrink: 0;">
          <Terminal {estado} {archivoActivo} />
        </div>
      {/if}
    </main>

    {#if estado.panelDerechoAbierto}
      <Separador direccion="vertical" valor={estado.anchoPanelDerecho} invertir={true} onRedimensionar={(v) => ideStore.fijarAnchoDerecho(v)} />
      <aside class="bg-white border-start d-flex flex-column overflow-hidden h-100" style="width: {estado.anchoPanelDerecho}px; flex-shrink: 0; border-color: #e9ecef !important;">
        <PilaAnalisis {estado} {archivoActivo} />
      </aside>
    {/if}
  </div>

  <footer class="d-flex align-items-center justify-content-between px-3 bg-light text-secondary border-top" style="height: 24px; font-size: 12px; flex-shrink: 0; border-color: #e9ecef !important;">
    <div>
      {#if archivoActivo}
        Ln {archivoActivo.cursor.linea}, Col {archivoActivo.cursor.columna}
      {:else}
        Sin archivo activo
      {/if}
    </div>
    <div class="d-flex gap-2">
      <button class="btn btn-sm btn-link text-secondary p-0 lh-1" on:click={() => ideStore.alternarPanelInferior()}>
        <i class="bi bi-layout-text-window-reverse"></i>
      </button>
      <button class="btn btn-sm btn-link text-secondary p-0 lh-1" on:click={() => ideStore.alternarPanelDerecho()}>
        <i class="bi bi-layout-sidebar-reverse"></i>
      </button>
    </div>
  </footer>
</div>

<style>
  .ide { font-family: 'Segoe UI', system-ui, sans-serif; }
  .cursor-pointer { cursor: pointer; }
  .pestana:hover { background-color: #f8f9fa; }
</style>