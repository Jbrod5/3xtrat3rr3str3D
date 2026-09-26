<script>
  import { ideStore } from '../lib/stores/ideStore.js';

  export let estado;
  export let archivoActivo;

  // ========== Funciones helper para la tabla (originales) ==========
  function formatearTipo(tipo) {
    if (!tipo) return '--';
    if (tipo.dimension > 0) {
      const base = tipo.tipoBase ? tipo.tipoBase.nombre : tipo.nombre;
      return `${base}[${tipo.dimension}]`;
    }
    return tipo.nombre;
  }

  function esPrimitivoTexto(val) {
    return val ? 'Sí' : 'No';
  }

  function badgeCategoria(categoria) {
    switch (categoria) {
      case 'VARIABLE': return 'bg-primary';
      case 'FUNCION': return 'bg-success';
      case 'PARAMETRO': return 'bg-warning text-dark';
      case 'CAMPO_ESTRUCTURA': return 'bg-info text-dark';
      default: return 'bg-secondary';
    }
  }

  function formatearValorConstante(constante) {
  if (!constante) return '--';
  // Obtener el valor mediante el metodo getValor() que expone Object
  const valor = constante.valor;
  if (valor === null || valor === undefined) return '--';
  // Si es texto, mostrarlo entre comillas para distinguir
  if (typeof valor === 'string') return `"${valor}"`;
  // Si es booleano, mostrar 'verum' o 'falsus'
  if (typeof valor === 'boolean') return valor ? 'verum' : 'falsus';
  // Numeros o caracteres se muestran directamente
  return String(valor);
}

function mostrarTamano(simbolo) {
  // Si tiene tamaño (array), mostrarlo, si no '--'
  if (simbolo.tamano !== null && simbolo.tamano !== undefined) {
    return simbolo.tamano;
  }
  // Opcional: si el tipo tiene dimension > 0 pero no tamaño, mostrar '?'
  if (simbolo.tipo && simbolo.tipo.dimension > 0) {
    return '?';
  }
  return '--';
}
</script>

<div class="d-flex flex-column h-100 bg-white" style="border-color: #e9ecef !important;">
  <div class="d-flex border-bottom" style="border-color: #e9ecef !important;">
    <button 
      class="btn btn-sm rounded-0 flex-fill"
      class:btn-light={estado.pestanaDerechaActiva !== 'cuartetas'}
      class:btn-white={estado.pestanaDerechaActiva === 'cuartetas'}
      class:active-pestaña={estado.pestanaDerechaActiva === 'cuartetas'}
      on:click={() => ideStore.cambiarPestanaDerecha('cuartetas')}
    >
      <i class="bi bi-list-ol"></i> Cuartetas
    </button>
    <button 
      class="btn btn-sm rounded-0 flex-fill"
      class:btn-light={estado.pestanaDerechaActiva !== 'c'}
      class:btn-white={estado.pestanaDerechaActiva === 'c'}
      class:active-pestaña={estado.pestanaDerechaActiva === 'c'}
      on:click={() => ideStore.cambiarPestanaDerecha('c')}
    >
      <i class="bi bi-filetype-c"></i> C
    </button>
    <button 
      class="btn btn-sm rounded-0 flex-fill"
      class:btn-light={estado.pestanaDerechaActiva !== 'tabla'}
      class:btn-white={estado.pestanaDerechaActiva === 'tabla'}
      class:active-pestaña={estado.pestanaDerechaActiva === 'tabla'}
      on:click={() => ideStore.cambiarPestanaDerecha('tabla')}
    >
      <i class="bi bi-table"></i> Tabla
    </button>
  </div>

  <div class="flex-grow-1 overflow-auto p-3 small">
{#if estado.pestanaDerechaActiva === 'cuartetas'}
      {#if archivoActivo?.resultado?.cuartetas && archivoActivo.resultado.cuartetas.length > 0}
        <div class="d-flex justify-content-between align-items-center mb-2">
          <h6 class="fw-bold mb-0 text-dark">
            <i class="bi bi-list-ol me-1"></i> Cuartetas
          </h6>
          <span class="badge bg-secondary">{archivoActivo.resultado.cuartetas.length} registros</span>
        </div>
        <pre class="bg-light p-2 rounded border text-dark mb-0" style="border-color: #e9ecef !important; font-size: 12px; white-space: pre-wrap;">{#each archivoActivo.resultado.cuartetas as c, i}{i + 1}: ({c.operador}, {c.arg1}, {c.arg2}, {c.resultado})
{/each}</pre>
      {:else}
        <div class="text-center text-muted py-5">
          <i class="bi bi-list-ol fs-1"></i>
          <p class="mt-2">No hay cuartetas disponibles.</p>
          <p class="small">Compila un archivo Pig Latin para generarlas.</p>
        </div>
      {/if}

    <!-- ================= PESTAÑA C ================= -->
    {:else if estado.pestanaDerechaActiva === 'c'}
      {#if archivoActivo?.resultado?.codigoC && archivoActivo.resultado.codigoC.length > 0}
        <div class="d-flex justify-content-between align-items-center mb-2">
          <h6 class="fw-bold mb-0 text-dark">
            <i class="bi bi-filetype-c me-1"></i> Codigo C generado
          </h6>
          <button 
            class="btn btn-sm btn-outline-secondary"
            on:click={() => {
              const blob = new Blob([archivoActivo.resultado.codigoC], { type: 'text/plain' });
              const url = URL.createObjectURL(blob);
              const a = document.createElement('a');
              a.href = url;
              a.download = 'programa.c';
              a.click();
              URL.revokeObjectURL(url);
            }}
          >
            <i class="bi bi-download"></i> Descargar
          </button>
        </div>
        {#if archivoActivo?.resultado?.resultadoGcc}
          {#if !archivoActivo.resultado.resultadoGcc.compilo && !(archivoActivo.resultado.codigoC || '').includes('int main')}
            <div class="alert alert-info py-1 px-2 small mb-2" style="font-size: 11px;">
              <i class="bi bi-info-circle"></i> Este archivo no genera <code>main</code>: los <code>.y</code> y <code>.z</code> son librerias. Compila el <code>.pig</code> que los importa para obtener el binario completo.
            </div>
          {/if}
          <div class="alert {archivoActivo.resultado.resultadoGcc.compilo ? 'alert-success' : 'alert-danger'} py-1 px-2 small mb-2" style="font-size: 12px;">
            <div class="d-flex justify-content-between align-items-center">
              <span>
                {#if archivoActivo.resultado.resultadoGcc.compilo}
                  <i class="bi bi-check-circle"></i> Compilacion exitosa
                {:else}
                  <i class="bi bi-x-circle"></i> Errores de compilacion
                {/if}
              </span>
            </div>
            {#if archivoActivo.resultado.resultadoGcc.rutaBinario}
              <div class="mt-1 text-secondary">
                Binario generado en:
                <code>{archivoActivo.resultado.resultadoGcc.rutaBinario}</code>
                <button
                  class="btn btn-sm btn-link p-0 ms-1"
                  on:click={() => navigator.clipboard.writeText(archivoActivo.resultado.resultadoGcc.rutaBinario)}
                  title="Copiar ruta"
                >
                  <i class="bi bi-clipboard"></i>
                </button>
              </div>
            {/if}
            <details class="mt-1">
              <summary class="text-muted" style="cursor: pointer; font-size: 11px;">Ver salida de gcc</summary>
              <div class="text-muted font-monospace mt-1" style="font-size: 10px;">{archivoActivo.resultado.resultadoGcc.comando}</div>
              <pre class="mb-0 mt-1" style="font-size: 11px; white-space: pre-wrap; max-height: 150px; overflow-y: auto;">{archivoActivo.resultado.resultadoGcc.salida}</pre>
            </details>
          </div>
        {/if}
        <pre class="bg-light p-2 rounded border text-dark mb-0" style="border-color: #e9ecef !important; font-size: 12px; white-space: pre-wrap;">{archivoActivo.resultado.codigoC}</pre>
      {:else}
        <div class="text-center text-muted py-5">
          <i class="bi bi-filetype-c fs-1"></i>
          <p class="mt-2">No hay codigo C generado.</p>
          <p class="small">Compila un archivo para generarlo.</p>
        </div>
      {/if}

    <!-- ================= PESTAÑA TABLA ================= -->
    {:else if estado.pestanaDerechaActiva === 'tabla'}
      {#if archivoActivo?.resultado?.simbolos && archivoActivo.resultado.simbolos.length > 0}
        <!-- TABLA DE SIMBOLOS -->
        <div class="mb-4">
          <div class="d-flex justify-content-between align-items-center mb-2">
            <h6 class="fw-bold mb-0 text-dark">
              <i class="bi bi-grid-3x3-gap me-1"></i> Tabla de Simbolos
            </h6>
            <span class="badge bg-secondary">{archivoActivo.resultado.simbolos.length} registros</span>
          </div>
          <div class="table-responsive">
            <table class="table table-sm table-bordered table-hover align-middle mb-0">
              <thead class="table-light">
                <tr>
                  <th class="text-nowrap">#</th>
                  <th class="text-nowrap">UUID</th>
                  <th class="text-nowrap">Nombre</th>
                  <th class="text-nowrap">Categoria</th>
                  <th class="text-nowrap">Tipo</th>

                  <th class="text-nowrap">Valor</th>
                  <th class="text-nowrap">Tamaño</th>

                  <th class="text-nowrap">Params</th>

                  <th class="text-nowrap">Ambito</th>
                  <th class="text-nowrap">Posicion relativa al ambito</th>
                  
                  <th class="text-nowrap">Fila</th>
                  <th class="text-nowrap">Columna</th>
                </tr>
              </thead>
              <tbody>
                {#each archivoActivo.resultado.simbolos as simbolo, i}
                  <tr>
                    <td class="text-muted text-center">{i + 1}</td>
                    <td class="text-muted text-center">{simbolo.id}</td>
                    <td class="fw-semibold font-monospace text-primary">{simbolo.nombre}</td>
                    <td>
                      <span class="badge {badgeCategoria(simbolo.categoria)}">{simbolo.categoria}</span>
                    </td>
                    <td class="font-monospace">{formatearTipo(simbolo.tipo)}</td>
                    
                    <td>{simbolo.valor ? formatearValorConstante(simbolo.valor) : '--'}</td>
                    <td>{simbolo.tamano != null ? simbolo.tamano : '--'}</td>
                    
                    <td class="text-center">
                      {#if simbolo.categoria === 'FUNCION'}
                        {simbolo.numParametros}
                      {:else}
                        <span class="text-muted">--</span>
                      {/if}
                    </td>
                    <td class="text-center font-monospace text-secondary">{simbolo.nombreAmbito}</td>
                    <td class="text-center font-monospace text-secondary">{simbolo.posicionRelativa}</td>
                    <td class="text-center font-monospace text-secondary">{simbolo.fila}</td>
                    <td class="text-center font-monospace text-secondary">{simbolo.columna}</td>
                  </tr>
                {/each}
              </tbody>
            </table>
          </div>
        </div>

        <!-- TABLA DE TIPOS -->
        {#if archivoActivo.resultado.tipos && archivoActivo.resultado.tipos.length > 0}
          <div class="mb-3">
            <div class="d-flex justify-content-between align-items-center mb-2">
              <h6 class="fw-bold mb-0 text-dark">
                <i class="bi bi-layers me-1"></i> Tabla de Tipos
              </h6>
              <span class="badge bg-secondary">{archivoActivo.resultado.tipos.length} registros</span>
            </div>
            <div class="table-responsive">
              <table class="table table-sm table-bordered table-hover align-middle mb-0">
                <thead class="table-light">
                  <tr>
                    <th class="text-nowrap">#</th>
                    <th class="text-nowrap">Nombre</th>
                    <th class="text-nowrap">Primitivo</th>
                    <!--<th class="text-nowrap">Dimension</th>-->
                    <th class="text-nowrap">Campos</th>
                    <th class="text-nowrap">Ambito</th>
                  </tr>
                </thead>
                <tbody>
                  {#each archivoActivo.resultado.tipos as tipo, i}
                    <tr>
                      <td class="text-muted text-center">{i + 1}</td>
                      <td class="fw-semibold font-monospace text-primary">{tipo.nombre}</td>
                      <td class="text-center">
                        {#if tipo.esPrimitivo}
                          <span class="badge bg-success">Sí</span>
                        {:else}
                          <span class="badge bg-secondary">No</span>
                        {/if}
                      </td>
                      <!-- <td class="text-center font-monospace">{tipo.dimension}</td> -->
                      <td>
                        {#if tipo.campos && tipo.campos.length > 0}
                          <div class="d-flex flex-wrap gap-1">
                            {#each tipo.campos as campo}
                              <span class="badge bg-info text-dark" title="ID: {campo.id}">
                                {campo.nombre}: {formatearTipo(campo.tipo)}
                              </span>
                            {/each}
                          </div>
                        {:else}
                          <span class="text-muted">--</span>
                        {/if}
                      </td>
                      <td>{tipo.nombreAmbito}</td>
                    </tr>
                  {/each}
                </tbody>
              </table>
            </div>
          </div>
        {/if}
      {:else}
        <div class="text-center text-muted py-5">
          <i class="bi bi-table fs-1"></i>
          <p class="mt-2">Tabla de simbolos vacia.</p>
          <p class="small">Compilar el codigo para generar la tabla.</p>
        </div>
      {/if}
    {/if}
  </div>
</div>

<style>
  .active-pestaña {
    border-bottom: 2px solid #0d6efd !important;
    font-weight: 600;
  }
  
  pre {
    white-space: pre-wrap;
    word-break: break-all;
    font-size: 12px;
  }
  
  .table {
    font-size: 12px;
  }
  
  .table th {
    font-weight: 600;
    background-color: #f8f9fa;
  }
  
  .font-monospace {
    font-family: 'Consolas', 'Monaco', 'Courier New', monospace;
  }
</style>