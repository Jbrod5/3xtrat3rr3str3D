<script>
  import { onMount, onDestroy } from 'svelte';
  import { ideStore } from '../lib/stores/ideStore.js';
  import { convertirAColorBackend, convertirTokensADecoraciones } from '../lib/utils/coloreadoUtil.js';
  import { obtenerColoresPorLenguaje } from '../lib/services/analizadorService.js';
  
  let contenedor;
  let editor;
  let monacoRef;
  let desuscribir;
  let idArchivoActual = null;
  let idsDecoraciones = [];
  let timeoutDebounce = null;
  let lenguajeActual = 'piglatin';
  
function detectarLenguaje(nombreArchivo) {
  if (!nombreArchivo) return 'piglatin';
  const ext = nombreArchivo.split('.').pop().toLowerCase();
  if (ext === 'z' || ext === 'zet') {
    return 'zetariano';
  }
  if (ext === 'y') {
    return 'y';
  }
  return 'piglatin';
}
  
function obtenerNombreLenguajeMonaco(lenguaje) {
  if (lenguaje === 'zetariano') {
    return 'zetariano';
  }
  if (lenguaje === 'y') {
    return 'y';
  }
  return 'piglatin';
}
  
  onMount(async () => {
    const monaco = await import('monaco-editor');
    monacoRef = monaco;
    
    // Registrar lenguaje PigLatin (lat)
    monaco.languages.register({ id: 'piglatin' });
    monaco.languages.setMonarchTokensProvider('piglatin', {
      keywords: [
        'VARIABILES','MAIOR','FINIS',
        'esto','series',
        'numerus','textum','decimalis','littera','bool',
        'verum','falsus',
        'si','aliter','dum','facere','per','interrumpe','perge',
        'novus','import','structura'
      ],
      operators: [
        '==','!=','<=','>=','&&','||','++','--','<<','>>',
        '=','+','-','*','/','<','>'
      ],
      symbols: /[=><!~?:&|+\-*\/\^%]+/,
      tokenizer: {
        root: [
          [/[a-zA-Z_]\w*/, { cases: { '@keywords': 'keyword', '@default': 'identifier' }}],
          [/[0-9]+\.[0-9]+/, 'number.float'],
          [/[0-9]+/, 'number'],
          [/".*?"/, 'string'],
          [/'.*?'/, 'string'],
          [/[{}()\[\]]/, '@brackets'],
          [/[;,.]/, 'delimiter'],
          [/@symbols/, { cases: { '@operators': 'operator', '@default': '' }}],
          [/#\#.*$/, 'comment'],
        ]
      }
    });
    
    // Registrar lenguaje Zetariano
    monaco.languages.register({ id: 'zetariano' });
    monaco.languages.setMonarchTokensProvider('zetariano', {
      keywords: [
        'public','class','void','new','null',
        'int','double','char','boolean','String',
        'true','false',
        'if','else','switch','case','default',
        'for','while','do','break','continue','return'
      ],
      operators: [
        '==','!=','<=','>=','&&','||','++','--',
        '+=','-=','*=','=','+','-','*','/','%','<','>','!','?'
      ],
      symbols: /[=><!~?:&|+\-*\/\^%]+/,
      tokenizer: {
        root: [
          [/[a-zA-Z_]\w*/, { cases: { '@keywords': 'keyword', '@default': 'identifier' }}],
          [/[0-9]+\.[0-9]+/, 'number.float'],
          [/[0-9]+/, 'number'],
          [/".*?"/, 'string'],
          [/'.*?'/, 'string'],
          [/[{}()\[\]]/, '@brackets'],
          [/[;,.]/, 'delimiter'],
          [/@symbols/, { cases: { '@operators': 'operator', '@default': '' }}],
          [/\/\/.*$/, 'comment'],
          [/\/\*/, 'comment', '@comment'],
        ],
        comment: [
          [/[^*]+/, 'comment'],
          [/\*\//, 'comment', '@pop'],
          [/./, 'comment']
        ]
      }
    });

    // Registrar lenguaje Y
    monaco.languages.register({ id: 'y' });
    monaco.languages.setMonarchTokensProvider('y', {
      keywords: [
        'estructura','definir','retornar',
        'entero','cadena','flotante','caracter','booleano',
        'verdadero','falso',
        'si','entonces','sino','contrario',
        'elegir','caso','siempre',
        'romper','continuar',
        'para','mientras','hacer'
      ],
      operators: [
        '==','!=','>=','<=','>','<',
        '&&','||','!',
        '->','++','--',
        '=','+','-','*','/'
      ],
      symbols: /[=><!~?:&|+\-*\/\^%]+/,
      tokenizer: {
        root: [
          [/[a-zA-Z_]\w*/, { cases: { '@keywords': 'keyword', '@default': 'identifier' }}],
          [/[0-9]+\.[0-9]+/, 'number.float'],
          [/[0-9]+/, 'number'],
          [/".*?"/, 'string'],
          [/'.*?'/, 'string'],
          [/[{}()\[\]]/, '@brackets'],
          [/[;,.:]/, 'delimiter'],
          [/@symbols/, { cases: { '@operators': 'operator', '@default': '' }}],
          [/\/\/.*$/, 'comment'],
        ]
      }
    });
    
    editor = monaco.editor.create(contenedor, {
      value: '',
      language: 'piglatin',
      theme: 'vs',
      automaticLayout: true,
      minimap: { enabled: false },
      fontSize: 14,
      scrollBeyondLastLine: false,
      lineNumbers: 'on',
      roundedSelection: false,
      padding: { top: 16 },
      renderLineHighlight: 'all',
      lineNumbersMinChars: 3
    });
    
    editor.onDidChangeModelContent(() => {
      if (idArchivoActual) {
        const valor = editor.getValue();
        ideStore.actualizarContenido(idArchivoActual, valor);
        reiniciarDebounceColoreado(valor);
      }
    });
    
    editor.onDidChangeCursorPosition((ev) => {
      if (idArchivoActual) {
        ideStore.actualizarCursor(idArchivoActual, ev.position.lineNumber, ev.position.column);
      }
    });
    
    desuscribir = ideStore.subscribe((estado) => {
      const activo = estado.archivos.find(a => a.id === estado.archivoActivoId);
      if (!activo) return;
      
      if (idArchivoActual !== activo.id) {
        idArchivoActual = activo.id;
        editor.setValue(activo.contenido);
        limpiarColores();
        
        // detectar lenguaje por extension del archivo
        const nuevoLenguaje = detectarLenguaje(activo.nombre);
        if (nuevoLenguaje !== lenguajeActual) {
          lenguajeActual = nuevoLenguaje;
          const modelo = editor.getModel();
          if (modelo) {
            monaco.editor.setModelLanguage(modelo, obtenerNombreLenguajeMonaco(lenguajeActual));
          }
        }
        // aplicar coloreado del archivo que se acaba de activar
        aplicarColoreadoDesdeBackend(activo.contenido);
      }
    });
  });
  
  onDestroy(() => {
    if (desuscribir) desuscribir();
    if (editor) editor.dispose();
    if (timeoutDebounce) clearTimeout(timeoutDebounce);
  });
  
  function reiniciarDebounceColoreado(codigo) {
    if (timeoutDebounce) clearTimeout(timeoutDebounce);
    timeoutDebounce = setTimeout(() => {
      aplicarColoreadoDesdeBackend(codigo);
    }, 500);
  }
  
  function limpiarColores() {
    if (editor) {
      idsDecoraciones = editor.deltaDecorations(idsDecoraciones, []);
    }
  }
  
  export async function aplicarColoreadoDesdeBackend(codigo) {
    if (!editor || !monacoRef) return;
    
    try {
      const respuesta = await obtenerColoresPorLenguaje(codigo || editor.getValue(), lenguajeActual);
      
      if (respuesta.colores && Array.isArray(respuesta.colores)) {
        const modelo = editor.getModel();
        const decoraciones = convertirTokensADecoraciones(respuesta.colores, modelo, monacoRef);
        idsDecoraciones = editor.deltaDecorations(idsDecoraciones, decoraciones);
      } else {
        limpiarColores();
      }
    } catch {
      limpiarColores();
    }
  }
  
  export function obtenerValor() {
    return editor ? editor.getValue() : '';
  }
  
  export function establecerValor(texto) {
    if (editor) {
      editor.setValue(texto);
    }
  }
</script>

<div bind:this={contenedor} class="monaco-contenedor"></div>

<style>
  .monaco-contenedor {
    width: 100%;
    height: 100%;
    overflow: hidden;
  }
  
  :global(.lc-1) { color: #d32f2f !important; }
  :global(.lc-2) { color: #c2185b !important; }
  :global(.lc-3) { color: #7b1fa2 !important; }
  :global(.lc-4) { color: #303f9f !important; }
  :global(.lc-5) { color: #1976d2 !important; }
  :global(.lc-6) { color: #0097a7 !important; }
  :global(.lc-7) { color: #388e3c !important; }
  :global(.lc-8) { color: #f57c00 !important; }
  :global(.lc-9) { color: #5d4037 !important; }
  :global(.lc-10) { color: #455a64 !important; }
</style>