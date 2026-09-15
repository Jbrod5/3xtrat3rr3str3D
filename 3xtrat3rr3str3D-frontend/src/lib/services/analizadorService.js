const URL_BASE = 'http://localhost:7070';

// detectar el lenguaje segun la extension del archivo
function detectarLenguaje(nombreArchivo) {
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
  return 'piglatin';
}

export async function analizarCodigo(codigo, lenguaje, ruta) {
  let lang = lenguaje;
  if (!lang) {
    lang = 'piglatin';
  }
  let url = `${URL_BASE}/analizar?lenguaje=${lang}`;
  if (ruta) {
    url = url + `&ruta=${encodeURIComponent(ruta)}`;
  }
  const respuesta = await fetch(url, {
    method: 'POST',
    headers: { 'Content-Type': 'text/plain; charset=UTF-8' },
    body: codigo
  });
  if (!respuesta.ok) {
    throw new Error(`Error HTTP: ${respuesta.status}`);
  }
  return await respuesta.json();
}

export async function traducirCodigo(codigo, lenguaje, ruta) {
  let lang = lenguaje;
  if (!lang) {
    lang = 'piglatin';
  }
  let url = `${URL_BASE}/traducir?lenguaje=${lang}`;
  if (ruta) {
    url = url + `&ruta=${encodeURIComponent(ruta)}`;
  }
  const respuesta = await fetch(url, {
    method: 'POST',
    headers: { 'Content-Type': 'text/plain; charset=UTF-8' },
    body: codigo
  });
  if (!respuesta.ok) {
    throw new Error(`Error HTTP: ${respuesta.status}`);
  }
  return await respuesta.json();
}

// conservar las funciones de coloreado tal cual estan
export async function obtenerColores(codigo) {
  const respuesta = await fetch(`${URL_BASE}/colores`, {
    method: 'POST',
    headers: { 'Content-Type': 'text/plain; charset=UTF-8' },
    body: codigo
  });
  if (!respuesta.ok) {
    throw new Error(`Error HTTP: ${respuesta.status}`);
  }
  return await respuesta.json();
}

export async function obtenerColoresPigLatin(codigo) {
  const respuesta = await fetch(`${URL_BASE}/colores?lenguaje=piglatin`, {
    method: 'POST',
    headers: { 'Content-Type': 'text/plain; charset=UTF-8' },
    body: codigo
  });
  if (!respuesta.ok) {
    throw new Error(`Error HTTP: ${respuesta.status}`);
  }
  return await respuesta.json();
}

export async function obtenerColoresZetariano(codigo) {
  const respuesta = await fetch(`${URL_BASE}/colores?lenguaje=zetariano`, {
    method: 'POST',
    headers: { 'Content-Type': 'text/plain; charset=UTF-8' },
    body: codigo
  });
  if (!respuesta.ok) {
    throw new Error(`Error HTTP: ${respuesta.status}`);
  }
  return await respuesta.json();
}

export async function obtenerColoresY(codigo) {
  const respuesta = await fetch(`${URL_BASE}/colores?lenguaje=y`, {
    method: 'POST',
    headers: { 'Content-Type': 'text/plain; charset=UTF-8' },
    body: codigo
  });
  if (!respuesta.ok) {
    throw new Error(`Error HTTP: ${respuesta.status}`);
  }
  return await respuesta.json();
}


export async function obtenerColoresPorLenguaje(codigo, lenguaje) {
  const lang = (lenguaje || 'piglatin').toLowerCase();
  if (lang === 'zetariano' || lang === 'zet') {
    return obtenerColoresZetariano(codigo);
  }
  if (lang === 'y') {
    return obtenerColoresY(codigo);
  }
  return obtenerColoresPigLatin(codigo);
}