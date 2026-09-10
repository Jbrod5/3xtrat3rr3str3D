<script>
  import { ideStore } from '../lib/stores/ideStore.js';
  import { createEventDispatcher } from 'svelte';
  import ElementoArbol from './ElementoArbol.svelte';

  const dispatch = createEventDispatcher();

  export let estado;
  export let archivoActivo;

  function normalizar(p) { return (p || '').replace(/\\/g, '/'); }
  function sinSlashFinal(p) { return p.replace(/\/+$/, ''); }

  function crearNuevoArchivo() {
    const nombre = prompt('Nombre del archivo:', 'nuevo.lat');
    if (!nombre || !nombre.trim()) return;

    const base = estado.rutaBaseProyecto;
    if (!base) {
      alert('Primero abre una carpeta de proyecto para tener una ruta base.');
      return;
    }

    const rutaRelativa = prompt(
      'Ruta relativa dentro del proyecto (puede incluir carpetas):',
      nombre.trim()
    );
    if (!rutaRelativa) return;

    const rutaAbs = sinSlashFinal(normalizar(base)) + '/' +
                    normalizar(rutaRelativa).replace(/^\/+/, '');

    const id = ideStore.crearArchivo(nombre.trim(), rutaAbs);
    ideStore.activarArchivo(id);
  }

  function confirmarEliminar(id, nombre) {
    if (confirm('Eliminar archivo "' + nombre + '"?')) {
      ideStore.eliminarArchivo(id);
    }
  }

  function abrirArchivoLocal() { dispatch('abrir'); }
  function abrirProyectoCarpeta() { dispatch('abrirCarpeta'); }

  // ================= Árbol =================
  // Usa rutaBaseProyecto para calcular la parte relativa de cada archivo.
  function calcularRelativa(rutaAbs, base) {
    const r = normalizar(rutaAbs);
    if (!base) return r;
    const b = sinSlashFinal(normalizar(base));
    if (r.startsWith(b + '/')) return r.substring(b.length + 1);
    if (r === b) return '';
    return r; // fuera de la base: se muestra tal cual
  }

  function construirArbol(archivos, base) {
    const raiz = { nombre: '', carpetas: {}, archivos: [] };
    if (!archivos) return raiz;

    for (const arch of archivos) {
      const rel = calcularRelativa(arch.ruta || arch.nombre, base);
      const partes = rel.split('/').filter(p => p !== '');

      let actual = raiz;
      for (let i = 0; i < partes.length - 1; i++) {
        const parte = partes[i];
        if (!actual.carpetas[parte]) {
          actual.carpetas[parte] = { nombre: parte, carpetas: {}, archivos: [] };
        }
        actual = actual.carpetas[parte];
      }
      actual.archivos.push(arch);
    }
    return raiz;
  }

  $: arbolArchivos = construirArbol(estado.archivos, estado.rutaBaseProyecto);

  function activarArchivo(id) { ideStore.activarArchivo(id); }
</script>

<aside class="bg-white border-end d-flex flex-column overflow-hidden" style="width: {estado.anchoSidebar}px; flex-shrink: 0; border-color: #e9ecef !important;">
  <div class="p-2 border-bottom small text-uppercase text-muted fw-bold d-flex justify-content-between align-items-center" style="border-color: #e9ecef !important;">
    <span>Explorador</span>
    <div class="d-flex gap-1">
      <button class="btn btn-sm btn-link text-secondary p-0" on:click={abrirProyectoCarpeta} title="Abrir carpeta o proyecto">
        <i class="bi bi-folder2-open"></i>
      </button>
      <button class="btn btn-sm btn-link text-secondary p-0" on:click={abrirArchivoLocal} title="Abrir archivo individual">
        <i class="bi bi-folder-open"></i>
      </button>
    </div>
  </div>

  {#if estado.rutaBaseProyecto}
    <div class="px-2 py-1 border-bottom small text-muted text-truncate" title={estado.rutaBaseProyecto} style="border-color: #e9ecef !important;">
      <i class="bi bi-hdd-network"></i> {estado.rutaBaseProyecto}
    </div>
  {/if}

  <div class="flex-grow-1 overflow-auto p-2">
    <ElementoArbol
      {estado}
      carpeta={arbolArchivos}
      {activarArchivo}
      eliminarArchivo={confirmarEliminar}
    />

    <button class="btn btn-sm btn-outline-secondary w-100 mt-2" on:click={crearNuevoArchivo}>
      <i class="bi bi-plus-lg"></i> Nuevo Archivo
    </button>
  </div>
</aside>