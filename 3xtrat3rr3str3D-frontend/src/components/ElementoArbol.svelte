<script>
  import ElementoArbol from './ElementoArbol.svelte';

  export let estado;
  export let carpeta;
  export let activarArchivo;
  export let eliminarArchivo;

  let expandida = true;

  function alternarCarpeta() { expandida = !expandida; }
  function manejarClickArchivo(archivo) { activarArchivo(archivo.id); }

  $: subCarpetas = Object.keys(carpeta.carpetas || {});
  $: esRaiz = carpeta.nombre === '';
</script>

<div class="mb-1">
  {#if !esRaiz}
    <!-- Encabezado de carpeta (clickeable para expandir/contraer) -->
    <div
      class="d-flex align-items-center gap-2 p-1 text-secondary small fw-bold cursor-pointer carpeta-item rounded"
      on:click={alternarCarpeta}
    >
      <i class="bi {expandida ? 'bi-chevron-down' : 'bi-chevron-right'} small"></i>
      <i class="bi {expandida ? 'bi-folder2-open text-warning' : 'bi-folder text-warning'}"></i>
      <span class="text-truncate">{carpeta.nombre}</span>
    </div>
  {/if}

  {#if expandida}
    <div
      class={esRaiz ? '' : 'ps-3 border-start ms-2'}
      style="border-color: #e9ecef !important;"
    >
      <!-- Subcarpetas recursivas -->
      {#each subCarpetas as nombreSubCarpeta (nombreSubCarpeta)}
        {@const subCarpeta = carpeta.carpetas[nombreSubCarpeta]}
        <ElementoArbol {estado} carpeta={subCarpeta} {activarArchivo} {eliminarArchivo} />
      {/each}

      <!-- Archivos en esta carpeta -->
      {#each carpeta.archivos as archivo (archivo.id)}
        <div
          class="d-flex align-items-center gap-2 p-1 rounded cursor-pointer archivo-item"
          class:bg-light={archivo.id === estado.archivoActivoId}
          on:click={() => manejarClickArchivo(archivo)}
          title={archivo.rutaRelativa}
        >
          <i class="bi bi-file-earmark-code text-primary"></i>
          <span class="text-truncate small flex-grow-1 text-dark">
            {archivo.nombre.split('/').pop()}
          </span>
          <button
            class="btn btn-sm btn-link text-secondary p-0 lh-1"
            on:click|stopPropagation={() => eliminarArchivo(archivo.id, archivo.nombre)}
          >
            <i class="bi bi-x"></i>
          </button>
        </div>
      {/each}
    </div>
  {/if}
</div>

<style>
  .cursor-pointer { cursor: pointer; }
  .carpeta-item:hover { background-color: #f8f9fa; }
  .archivo-item:hover { background-color: #f8f9fa; }
</style>