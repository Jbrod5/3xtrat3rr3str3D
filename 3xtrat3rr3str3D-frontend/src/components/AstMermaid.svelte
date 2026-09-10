<script>
  import { onMount, tick } from 'svelte';
  import mermaid from 'mermaid';

  export let codigoMermaid; // código original del backend

  // Referencias al DOM
  let contenedorDiagrama;
  let contenedorWrapper;
  let textareaEditor;       // referencia al <textarea>

  // Estado
  let codigoEditable = '';
  let modoEdicion = false;
  let vistaActual = 'diagrama';   // 'diagrama' | 'codigo'
  let mostrarCodigo = false;      // para bind:checked

  let estaRenderizando = false;
  let idUnico = 'mermaid-' + Math.random().toString(36).substring(2, 11);
  let svgCache = null;

  // Zoom y arrastre
  let nivelZoom = 1;
  const ZOOM_MIN = 0.1;
  const ZOOM_MAX = 5;
  let offsetX = 0;
  let offsetY = 0;
  let isDragging = false;
  let startX = 0, startY = 0;
  let dragStartOffsetX = 0, dragStartOffsetY = 0;

  // ============================================================
  //  INICIALIZACIÓN
  // ============================================================
  onMount(() => {
    mermaid.initialize({
      startOnLoad: false,
      theme: 'default',
      maxEdges: 5000,
      maxTextSize: 10000000,
      flowchart: {
        useMaxWidth: false,
        htmlLabels: true,
        curve: 'basis',
        rankSpacing: 250,
        nodeSpacing: 60,
        padding: 20,
      },
    });

    codigoEditable = codigoMermaid || '';
    if (codigoEditable) {
      renderizar(codigoEditable);
    }

    mostrarCodigo = (vistaActual === 'codigo');

    tick().then(() => {
      if (vistaActual === 'diagrama' && contenedorDiagrama) {
        renderizar(codigoEditable);
      }
    });
  });

  // ============================================================
  //  REACTIVIDAD: sincronizar con prop externo
  // ============================================================
  let ultimoProp = '';
  $: {
    if (codigoMermaid !== undefined && codigoMermaid !== null) {
      if (codigoMermaid !== ultimoProp) {
        ultimoProp = codigoMermaid;
        if (!modoEdicion) {
          codigoEditable = codigoMermaid;
          if (vistaActual === 'diagrama') {
            renderizar(codigoEditable);
          } else {
            // Si estamos en modo código, actualizar el textarea
            if (textareaEditor) {
              textareaEditor.value = codigoEditable;
            }
          }
        }
      }
    }
  }

  // ============================================================
  //  RENDERIZADO MERMAID
  // ============================================================
  async function renderizar(codigo) {
    if (!contenedorDiagrama) return;
    if (estaRenderizando) return;

    if (!codigo || codigo.trim() === '') {
      contenedorDiagrama.innerHTML = '<div class="text-muted small">No hay código Mermaid para mostrar.</div>';
      svgCache = null;
      return;
    }

    try {
      estaRenderizando = true;
      const codigoSaneado = sanearMermaid(codigo);
      const { svg } = await mermaid.render(idUnico, codigoSaneado);
      svgCache = svg;
      contenedorDiagrama.innerHTML = svg;
      offsetX = 0;
      offsetY = 0;
      aplicarTransformacion();
    } catch (err) {
      console.error('Error renderizando Mermaid:', err);
      contenedorDiagrama.innerHTML = `<div class="text-danger small">Error al renderizar AST: ${err.message}</div>`;
      svgCache = null;
    } finally {
      estaRenderizando = false;
    }
  }

  function sanearMermaid(codigo) {
    if (!codigo) return codigo;
    let resultado = codigo.replace(/\\"/g, '');
    resultado = resultado.replace(/\["([^"]*)"\]/g, (match, contenido) => {
      let limpio = contenido
        .replace(/\(/g, ' ')
        .replace(/\)/g, ' ')
        .replace(/\[/g, ' ')
        .replace(/\]/g, ' ')
        .replace(/\{/g, ' ')
        .replace(/\}/g, ' ')
        .replace(/\|/g, ' ')
        .replace(/,/g, ' ');
      limpio = limpio.replace(/\s+/g, ' ');
      return `["${limpio}"]`;
    });
    return resultado;
  }

  // ============================================================
  //  ZOOM Y ARRASTRE
  // ============================================================
  function zoomIn() {
    nivelZoom = Math.min(nivelZoom * 1.2, ZOOM_MAX);
    aplicarTransformacion();
  }
  function zoomOut() {
    nivelZoom = Math.max(nivelZoom * 0.8, ZOOM_MIN);
    aplicarTransformacion();
  }
  function zoomReset() {
    nivelZoom = 1;
    offsetX = 0;
    offsetY = 0;
    aplicarTransformacion();
  }
  function zoomFit() {
    if (contenedorWrapper && contenedorDiagrama) {
      const wrapperRect = contenedorWrapper.getBoundingClientRect();
      const svg = contenedorDiagrama.querySelector('svg');
      if (svg) {
        const svgRect = svg.getBoundingClientRect();
        const scaleX = (wrapperRect.width - 40) / Math.max(svgRect.width, 1);
        const scaleY = (wrapperRect.height - 40) / Math.max(svgRect.height, 1);
        nivelZoom = Math.min(scaleX, scaleY, 1);
        offsetX = 0;
        offsetY = 0;
        aplicarTransformacion();
      }
    }
  }
  function aplicarTransformacion() {
    if (contenedorDiagrama) {
      contenedorDiagrama.style.transform = `translate(${offsetX}px, ${offsetY}px) scale(${nivelZoom})`;
      contenedorDiagrama.style.transformOrigin = 'top left';
    }
  }

  // ============================================================
  //  ARRASTRE
  // ============================================================
  function iniciarArrastre(ev) {
    if (ev.button !== 0) return;
    if (ev.target.closest('button') || ev.target.closest('a')) return;
    isDragging = true;
    startX = ev.clientX;
    startY = ev.clientY;
    dragStartOffsetX = offsetX;
    dragStartOffsetY = offsetY;
    contenedorWrapper.style.cursor = 'grabbing';
    document.body.style.userSelect = 'none';
    ev.preventDefault();
  }
  function moverArrastre(ev) {
    if (!isDragging) return;
    const deltaX = ev.clientX - startX;
    const deltaY = ev.clientY - startY;
    offsetX = dragStartOffsetX + deltaX;
    offsetY = dragStartOffsetY + deltaY;
    aplicarTransformacion();
    ev.preventDefault();
  }
  function finalizarArrastre() {
    if (isDragging) {
      isDragging = false;
      contenedorWrapper.style.cursor = 'grab';
      document.body.style.userSelect = '';
    }
  }
  function manejarWheel(ev) {
    if (ev.ctrlKey || ev.metaKey) {
      ev.preventDefault();
      if (ev.deltaY < 0) zoomIn();
      else zoomOut();
    }
  }

  // ============================================================
  //  FUNCIONES EXPORTADAS
  // ============================================================
  export function copiarMermaid() {
    const texto = codigoEditable || '';
    if (!texto) {
      alert('No hay código Mermaid para copiar.');
      return;
    }
    navigator.clipboard.writeText(texto)
      .then(() => alert('Código Mermaid copiado al portapapeles.'))
      .catch(() => {
        const textarea = document.createElement('textarea');
        textarea.value = texto;
        document.body.appendChild(textarea);
        textarea.select();
        document.execCommand('copy');
        document.body.removeChild(textarea);
        alert('Código Mermaid copiado al portapapeles.');
      });
  }

  // ============================================================
  //  SWITCH DE VISTA
  // ============================================================
  function toggleVista() {
    if (vistaActual === 'diagrama') {
      vistaActual = 'codigo';
      mostrarCodigo = true;
      // Asegurar que el textarea tenga el código actual
      if (textareaEditor) {
        textareaEditor.value = codigoEditable;
      }
    } else {
      vistaActual = 'diagrama';
      mostrarCodigo = false;
      renderizar(codigoEditable);
    }
  }

  // ============================================================
  //  ACCIONES DE EDICIÓN
  // ============================================================
  function aplicarEdicion() {
    // Leer el valor del textarea
    if (textareaEditor) {
      codigoEditable = textareaEditor.value;
      modoEdicion = true;
    }
    vistaActual = 'diagrama';
    mostrarCodigo = false;
    renderizar(codigoEditable);
  }

  function restaurarOriginal() {
    codigoEditable = codigoMermaid || '';
    modoEdicion = false;
    if (textareaEditor) {
      textareaEditor.value = codigoEditable;
    }
    if (vistaActual === 'codigo') {
      // Si estamos en modo código, solo actualizar el textarea
      // pero no renderizar hasta que el usuario quiera
    } else {
      renderizar(codigoEditable);
    }
  }
</script>

<div class="ast-container d-flex flex-column h-100">
  <!-- Barra de herramientas -->
  <div class="toolbar-zoom d-flex gap-2 mb-2 align-items-center flex-shrink-0">
    <!-- Switch -->
    <div class="d-flex align-items-center gap-1">
      <span class="small text-muted">Diagrama</span>
      <div class="form-check form-switch mb-0">
        <input class="form-check-input" type="checkbox" id="vistaSwitch" bind:checked={mostrarCodigo} on:change={toggleVista}>
        <label class="form-check-label small text-muted" for="vistaSwitch">Código</label>
      </div>
    </div>
    <span class="vr"></span>

    <!-- Botones de zoom (solo diagrama) -->
    {#if vistaActual === 'diagrama'}
      <button class="btn btn-sm btn-outline-secondary" on:click={zoomOut} title="Alejar (Ctrl + Scroll)">
        <i class="bi bi-zoom-out"></i>
      </button>
      <span class="badge bg-secondary" style="min-width: 60px; text-align: center;">
        {Math.round(nivelZoom * 100)}%
      </span>
      <button class="btn btn-sm btn-outline-secondary" on:click={zoomIn} title="Acercar (Ctrl + Scroll)">
        <i class="bi bi-zoom-in"></i>
      </button>
      <button class="btn btn-sm btn-outline-secondary" on:click={zoomReset} title="Restablecer zoom y posición">
        <i class="bi bi-arrows-angle-expand"></i>
      </button>
      <button class="btn btn-sm btn-outline-secondary" on:click={zoomFit} title="Ajustar al contenedor">
        <i class="bi bi-bounding-box"></i>
      </button>
      <span class="text-muted small ms-2">(Arrastrar para mover)</span>
    {/if}

    <!-- Botones de edición (solo código) -->
    {#if vistaActual === 'codigo'}
      <button class="btn btn-sm btn-outline-primary" on:click={aplicarEdicion}>
        <i class="bi bi-arrow-repeat"></i> Renderizar
      </button>
      <button class="btn btn-sm btn-outline-secondary" on:click={restaurarOriginal}>
        <i class="bi bi-arrow-counterclockwise"></i> Restaurar
      </button>
      <button class="btn btn-sm btn-outline-secondary" on:click={copiarMermaid}>
        <i class="bi bi-clipboard"></i> Copiar
      </button>
    {/if}
  </div>

  <!-- Contenedor principal -->
  <div class="flex-grow-1 overflow-hidden position-relative">
    {#if vistaActual === 'diagrama'}
      <div
        bind:this={contenedorWrapper}
        class="mermaid-wrapper w-100 h-100 overflow-hidden"
        style="border: 1px solid #e9ecef; border-radius: 4px; background: #fafafa; cursor: grab;"
        on:mousedown={iniciarArrastre}
        on:mousemove={moverArrastre}
        on:mouseup={finalizarArrastre}
        on:mouseleave={finalizarArrastre}
        on:wheel={manejarWheel}
      >
        <div
          bind:this={contenedorDiagrama}
          class="mermaid-contenedor"
          style="transform: translate({offsetX}px, {offsetY}px) scale({nivelZoom}); transform-origin: top left; transition: none;"
        ></div>
      </div>
    {:else}
      <div class="w-100 h-100 d-flex flex-column" style="border: 1px solid #e9ecef; border-radius: 4px; background: #fff; padding: 8px;">
        <label class="small text-muted mb-1">Editar código Mermaid (JSON):</label>
        <textarea
          bind:this={textareaEditor}
          class="form-control font-monospace flex-grow-1"
          style="resize: none; font-size: 13px; line-height: 1.5; border: none; outline: none; background: #f8f9fa;"
          placeholder="Escribe o pega el código Mermaid aquí..."
          bind:value={codigoEditable}
          on:input={() => { modoEdicion = true; }}
        ></textarea>
      </div>
    {/if}
  </div>
</div>

<style>
  .ast-container {
    height: 100%;
    min-height: 0;
    display: flex;
    flex-direction: column;
  }
  .toolbar-zoom {
    padding-bottom: 8px;
    border-bottom: 1px solid #e9ecef;
    flex-shrink: 0;
  }
  .mermaid-wrapper {
    position: relative;
  }
  .mermaid-contenedor {
    display: inline-block;
    min-width: 100%;
    min-height: 100%;
    will-change: transform;
  }
  :global(.mermaid-contenedor svg) {
    max-width: none;
    height: auto;
    display: block;
    pointer-events: none;
  }
  .vr {
    width: 1px;
    background-color: #e9ecef;
    height: 24px;
    margin: 0 4px;
  }
  .form-check-input {
    cursor: pointer;
  }
  .font-monospace {
    font-family: 'Consolas', 'Monaco', 'Courier New', monospace;
  }
</style>