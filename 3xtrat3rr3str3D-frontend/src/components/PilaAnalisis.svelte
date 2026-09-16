<script>
  import { ideStore } from '../lib/stores/ideStore.js';
  import AstMermaid from './AstMermaid.svelte';

  export let estado;
  export let archivoActivo;

  let refAst;
  let codigoMermaid = null;

  // Actualizar solo cuando el codigo Mermaid cambie realmente
  $: {
    const nuevoCodigo = archivoActivo?.resultado?.astMermaid || null;
    if (nuevoCodigo !== codigoMermaid) {
      codigoMermaid = nuevoCodigo;
    }
  }

  function copiarMermaidDesdeAst() {
    if (refAst && typeof refAst.copiarMermaid === 'function') {
      refAst.copiarMermaid();
    }
  }

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

  // ========== Lógica para la pila (NUEVA) ==========
  let pasos = [];
  let indiceActual = 0;
  let pasoActual = null;

  // Actualizar pasos cuando cambie el archivo o su resultado
  $: {
    if (archivoActivo?.resultado?.pasosPila) {
      pasos = archivoActivo.resultado.pasosPila;
      indiceActual = 0;
    } else {
      pasos = [];
      indiceActual = 0;
    }
  }

  // Actualizar el paso actual cuando cambie el índice o los pasos
  $: {
    if (pasos.length > 0 && indiceActual >= 0 && indiceActual < pasos.length) {
      pasoActual = pasos[indiceActual];
    } else {
      pasoActual = null;
    }
  }

  function irAlPrimero() {
    if (pasos.length > 0) indiceActual = 0;
  }

  function irAlAnterior() {
    if (indiceActual > 0) indiceActual--;
  }

  function irAlSiguiente() {
    if (indiceActual < pasos.length - 1) indiceActual++;
  }

  function irAlUltimo() {
    if (pasos.length > 0) indiceActual = pasos.length - 1;
  }

  // Función para mostrar la pila (cima arriba)
  function pilaInvertida(pila) {
    if (!pila) return [];
    return [...pila].reverse();
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
      class:btn-light={estado.pestanaDerechaActiva !== 'pila'}
      class:btn-white={estado.pestanaDerechaActiva === 'pila'}
      class:active-pestaña={estado.pestanaDerechaActiva === 'pila'}
      on:click={() => ideStore.cambiarPestanaDerecha('pila')}
    >
      <i class="bi bi-stack"></i> Pila
    </button>
    <button 
      class="btn btn-sm rounded-0 flex-fill"
      class:btn-light={estado.pestanaDerechaActiva !== 'ast'}
      class:btn-white={estado.pestanaDerechaActiva === 'ast'}
      class:active-pestaña={estado.pestanaDerechaActiva === 'ast'}
      on:click={() => ideStore.cambiarPestanaDerecha('ast')}
    >
      <i class="bi bi-diagram-3"></i> AST
    </button>
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
      class:btn-light={estado.pestanaDerechaActiva !== 'tabla'}
      class:btn-white={estado.pestanaDerechaActiva === 'tabla'}
      class:active-pestaña={estado.pestanaDerechaActiva === 'tabla'}
      on:click={() => ideStore.cambiarPestanaDerecha('tabla')}
    >
      <i class="bi bi-table"></i> Tabla
    </button>
  </div>

  <div class="flex-grow-1 overflow-auto p-3 small">
    <!-- ================= PESTAÑA PILA ================= -->
{#if estado.pestanaDerechaActiva === 'pila'}
  {#if pasos.length === 0}
    <div class="text-center text-muted py-5">
      <i class="bi bi-stack fs-1"></i>
      <p class="mt-2">Pila de análisis vacía.</p>
      <p class="small">Compila un archivo para generar los pasos.</p>
    </div>
  {:else}
    <!-- Controles de navegación -->
    <div class="d-flex justify-content-between align-items-center mb-2 flex-shrink-0">
      <div class="d-flex gap-2">
        <button class="btn btn-sm btn-outline-secondary" on:click={irAlPrimero} disabled={indiceActual === 0}>
          <i class="bi bi-chevron-bar-left"></i>
        </button>
        <button class="btn btn-sm btn-outline-secondary" on:click={irAlAnterior} disabled={indiceActual === 0}>
          <i class="bi bi-chevron-left"></i> Anterior
        </button>
      </div>
      <span class="badge bg-secondary">
        Paso {indiceActual + 1} de {pasos.length}
      </span>
      <div class="d-flex gap-2">
        <button class="btn btn-sm btn-outline-secondary" on:click={irAlSiguiente} disabled={indiceActual === pasos.length - 1}>
          Siguiente <i class="bi bi-chevron-right"></i>
        </button>
        <button class="btn btn-sm btn-outline-secondary" on:click={irAlUltimo} disabled={indiceActual === pasos.length - 1}>
          <i class="bi bi-chevron-bar-right"></i>
        </button>
      </div>
    </div>

    <!-- Detalle del paso actual -->
    {#if pasoActual}
      <div class="mb-2 p-2 border rounded bg-light flex-shrink-0" style="border-color: #e9ecef !important;">
        <div class="d-flex flex-wrap gap-3">
          <div>
            <span class="fw-bold">Operación:</span>
            <span class="badge {pasoActual.operacion === 'shift' ? 'bg-primary' : 'bg-success'}">
              {pasoActual.operacion.toUpperCase()}
            </span>
          </div>
          <div>
            <span class="fw-bold">Símbolo:</span>
            <code class="bg-white px-1 rounded">{pasoActual.simbolo}</code>
          </div>
          <div>
            <span class="fw-bold">Línea:</span>
            <span>{pasoActual.linea}</span>
          </div>
          <div>
            <span class="fw-bold">Columna:</span>
            <span>{pasoActual.columna}</span>
          </div>
        </div>
      </div>

      <!-- Visualización de la pila ocupando el espacio restante -->
      <div class="flex-grow-1 d-flex flex-column" style="min-height: 0;">
        <div class="fw-bold mb-1">Estado de la pila (cima arriba):</div>
        <div class="border rounded p-2 bg-white flex-grow-1 overflow-auto" style="border-color: #e9ecef !important;">
          {#if pasoActual.pila && pasoActual.pila.length > 0}
            <ul class="list-unstyled mb-0">
              {#each pilaInvertida(pasoActual.pila) as item}
                <li class="border-bottom py-1 px-2 font-monospace" style="border-color: #f1f3f5 !important;">
                  {item}
                </li>
              {/each}
            </ul>
          {:else}
            <span class="text-muted">(pila vacía)</span>
          {/if}
        </div>
      </div>
    {/if}
  {/if}

<!-- ================= PESTAÑA AST ================= -->
{:else if estado.pestanaDerechaActiva === 'ast'}
  {#if codigoMermaid}
    <AstMermaid bind:this={refAst} codigoMermaid={codigoMermaid} />
  {:else if archivoActivo?.resultado?.arbolSintactico}
    <div class="mb-2 text-muted small">No se recibió diagrama Mermaid. Mostrando representación textual:</div>
    <pre class="bg-light p-2 rounded border text-dark" style="border-color: #e9ecef !important;">{archivoActivo.resultado.arbolSintactico}</pre>
  {:else}
    <div class="text-center text-muted py-5">
      <i class="bi bi-diagram-3 fs-1"></i>
      <p class="mt-2">No hay AST disponible.</p>
      <p class="small">Compilar el código para generar el árbol.</p>
    </div>
  {/if}

    <!-- ================= PESTAÑA CUARTETAS ================= -->
    {:else if estado.pestanaDerechaActiva === 'cuartetas'}
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