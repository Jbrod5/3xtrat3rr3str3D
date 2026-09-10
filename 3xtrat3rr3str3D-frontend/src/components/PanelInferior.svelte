<script>
  import { ideStore } from '../lib/stores/ideStore.js';
  
  export let estado;
  export let archivoActivo;
</script>

<div class="d-flex flex-column h-100 bg-white" style="border-color: #e9ecef !important;">
  <!-- Barra de pestañas: solo Errores y Resultados -->
  <div class="d-flex border-bottom" style="border-color: #e9ecef !important;">
    <button 
      class="btn btn-sm rounded-0 flex-fill"
      class:btn-light={estado.pestanaInferiorActiva !== 'errores'}
      class:btn-white={estado.pestanaInferiorActiva === 'errores'}
      class:active-pestaña={estado.pestanaInferiorActiva === 'errores'}
      on:click={() => ideStore.cambiarPestanaInferior('errores')}
    >
      <i class="bi bi-exclamation-triangle"></i> Errores
    </button>
    <button 
      class="btn btn-sm rounded-0 flex-fill"
      class:btn-light={estado.pestanaInferiorActiva !== 'resultados'}
      class:btn-white={estado.pestanaInferiorActiva === 'resultados'}
      class:active-pestaña={estado.pestanaInferiorActiva === 'resultados'}
      on:click={() => ideStore.cambiarPestanaInferior('resultados')}
    >
      <i class="bi bi-check-circle"></i> Resultados
    </button>
  </div>
  
  <div class="flex-grow-1 overflow-auto p-2 font-monospace small position-relative">
    <!-- Pestaña Errores -->
    {#if estado.pestanaInferiorActiva === 'errores'}
      {#if archivoActivo?.resultado?.errores && archivoActivo.resultado.errores.length > 0}
        <div class="text-danger fw-bold mb-2">Errores detectados ({archivoActivo.resultado.errores.length})</div>
        <ul class="list-unstyled">
          {#each archivoActivo.resultado.errores as error}
            <li class="mb-2 p-2 border-bottom" style="border-color: #f1f3f5 !important;">
              <div class="d-flex gap-2 flex-wrap text-secondary small mb-1">
                {#if error.linea}
                  <span>Ln {error.linea}{#if error.columna}, Col {error.columna}{/if}</span>
                {/if}
                {#if error.tipo}
                  <span>[{error.tipo}]</span>
                {/if}
              </div>
              <div class="text-danger">{error.mensaje || JSON.stringify(error)}</div>
            </li>
          {/each}
        </ul>
      {:else}
        <span class="text-muted fst-italic">No hay errores para mostrar.</span>
      {/if}
      
    <!-- Pestaña Resultados -->
    {:else if estado.pestanaInferiorActiva === 'resultados'}
      {#if archivoActivo?.resultado}
        {#if archivoActivo.resultado.exito}
          <div class="text-success fw-bold mb-2">Compilación exitosa</div>
          {#if archivoActivo.resultado.codigoPigLatin}
            <div class="mb-2">
              <span class="fw-bold small text-muted">Traducción PigLatin:</span>
              <pre class="bg-light p-2 rounded border mt-1 text-dark" style="border-color: #e9ecef !important;">{archivoActivo.resultado.codigoPigLatin}</pre>
            </div>
          {/if}
          {#if archivoActivo.resultado.arbolSintactico}
            <details>
              <summary class="text-primary fw-bold">Árbol sintáctico (texto)</summary>
              <pre class="bg-light p-2 rounded border text-dark mt-2" style="border-color: #e9ecef !important;">{archivoActivo.resultado.arbolSintactico}</pre>
            </details>
          {/if}
        {:else}
          <span class="text-muted fst-italic">La última compilación no fue exitosa. Revisa la pestaña Errores.</span>
        {/if}
      {:else}
        <span class="text-muted fst-italic">Presiona "Compilar" para analizar el archivo activo.</span>
      {/if}
    {/if}
  </div>
</div>

<style>
  .font-monospace {
    font-family: 'Consolas', 'Monaco', 'Courier New', monospace;
  }
  
  .active-pestaña {
    border-bottom: 2px solid #0d6efd !important;
    font-weight: 600;
  }
  
  pre {
    white-space: pre-wrap;
    word-break: break-all;
    font-size: 12px;
  }
</style>