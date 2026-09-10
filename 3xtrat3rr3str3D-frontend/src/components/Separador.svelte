<script>
  export let direccion = 'vertical';
  export let valor;
  export let invertir = false;
  export let onRedimensionar;
  
  let valorInicial;
  let inicioX;
  let inicioY;
  
  function iniciarArrastre(ev) {
    valorInicial = valor;
    inicioX = ev.clientX;
    inicioY = ev.clientY;
    
    function mover(evMov) {
      let nuevoValor;
      if (direccion === 'vertical') {
        const delta = evMov.clientX - inicioX;
        nuevoValor = invertir ? valorInicial - delta : valorInicial + delta;
      } else {
        const delta = inicioY - evMov.clientY;
        nuevoValor = valorInicial + delta;
      }
      onRedimensionar(nuevoValor);
    }
    
    function soltar() {
      window.removeEventListener('mousemove', mover);
      window.removeEventListener('mouseup', soltar);
    }
    
    window.addEventListener('mousemove', mover);
    window.addEventListener('mouseup', soltar);
  }
</script>

{#if direccion === 'vertical'}
  <div class="separador-vertical" on:mousedown={iniciarArrastre}></div>
{:else}
  <div class="separador-horizontal" on:mousedown={iniciarArrastre}></div>
{/if}

<style>
  .separador-vertical {
    width: 4px;
    cursor: col-resize;
    background-color: #f1f3f5;
    flex-shrink: 0;
    transition: background-color 0.2s;
  }
  
  .separador-vertical:hover {
    background-color: #adb5bd;
  }
  
  .separador-horizontal {
    height: 4px;
    cursor: row-resize;
    background-color: #f1f3f5;
    flex-shrink: 0;
    transition: background-color 0.2s;
  }
  
  .separador-horizontal:hover {
    background-color: #adb5bd;
  }
</style>