const URL_BASE = 'http://localhost:7070';

export async function analizarCodigo(codigo) {
  const respuesta = await fetch(`${URL_BASE}/analizar`, {
    method: 'POST',
    headers: { 'Content-Type': 'text/plain; charset=UTF-8' },
    body: codigo
  });
  if (!respuesta.ok) {
    throw new Error(`Error HTTP: ${respuesta.status}`);
  }
  return await respuesta.json();
}

export async function traducirCodigo(codigo) {
  const respuesta = await fetch(`${URL_BASE}/traducir`, {
    method: 'POST',
    headers: { 'Content-Type': 'text/plain; charset=UTF-8' },
    body: codigo
  });
  if (!respuesta.ok) {
    throw new Error(`Error HTTP: ${respuesta.status}`);
  }
  return await respuesta.json();
}

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