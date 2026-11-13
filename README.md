# Ecommerce API

API REST para una tienda de libros electrónicos y físicos.

## Convenciones

- **URL base:** `http://localhost:8080/api`
- **Formato:** JSON (`Content-Type: application/json`)
- **Autenticación:** `Authorization: Bearer <token>`
- **Acceso:**
  - **Público:** no requiere token.
  - **Cliente:** requiere token válido.

> Los endpoints marcados como **nuevos** ya están declarados en los controllers, pero todavía no tienen implementación: responden `501 Not Implemented` hasta que se conecte la lógica de negocio.

---

## 1. Autenticación

| Método | Ruta | Acceso | Estado | Descripción |
|---|---|---|---|---|
| POST | `/auth/iniciar-sesion` | Público | Existente | Inicia sesión con `usuario` y `contrasena`. |
| GET | `/auth/perfil` | Cliente | Existente | Devuelve el usuario y roles del token actual. |
| POST | `/auth/registro` | Público | Nuevo | Crea una cuenta. Valida correo único y contraseña de mínimo 8 caracteres. |
| POST | `/auth/login` | Público | Nuevo | Inicia sesión con `correo` y `contrasena`. Devuelve token. |
| POST | `/auth/logout` | Cliente | Nuevo | Cierra la sesión. |
| GET | `/auth/me` | Cliente | Nuevo | Devuelve el usuario actual para el menú "Cuenta" y el checkout. |

### `POST /auth/registro`

```json
{
  "nombre": "Ana Pérez",
  "correo": "ana@correo.com",
  "contrasena": "secreto123",
  "confirmar": "secreto123"
}
```

Respuesta `201`:

```json
{
  "id": 1,
  "nombre": "Ana Pérez",
  "correo": "ana@correo.com",
  "telefono": null,
  "rol": "CLIENTE"
}
```

### `POST /auth/login`

```json
{
  "correo": "ana@correo.com",
  "contrasena": "secreto123"
}
```

Respuesta `200`:

```json
{
  "token": "eyJhbGciOiJIUzM4NCJ9...",
  "tipo": "Bearer",
  "expiracion": 3600000
}
```

### `POST /auth/logout`

Respuesta `200`:

```json
{
  "mensaje": "Sesión cerrada"
}
```

### `GET /auth/me`

Respuesta `200`:

```json
{
  "id": 1,
  "nombre": "Ana Pérez",
  "correo": "ana@correo.com",
  "telefono": "6441234567",
  "rol": "CLIENTE"
}
```

---

## 2. Catálogo

| Método | Ruta | Acceso | Estado | Descripción |
|---|---|---|---|---|
| GET | `/libros` | Público | Nuevo | Listado con filtros. |
| GET | `/libros/destacados` | Público | Nuevo | Novedades y destacados del inicio. |
| GET | `/libros/{id}` | Público | Nuevo | Detalle del libro y disponibilidad. |
| GET | `/categorias` | Público | Nuevo | Categorías para el home y el filtro. |
| GET | `/autores` | Público | Nuevo | Autores para el filtro. |

### `GET /libros`

Parámetros de consulta:

| Parámetro | Tipo | Descripción |
|---|---|---|
| `q` | string | Busca por título o autor. |
| `categoria` | long | Filtra por ID de categoría. |
| `autor` | long | Filtra por ID de autor. |
| `orden` | string | `relevancia`, `precio_asc`, `precio_desc`, `titulo_asc`. |

Ejemplo: `GET /libros?q=cien&categoria=2&orden=precio_asc`

Respuesta `200`:

```json
[
  {
    "id": 1,
    "titulo": "Cien años de soledad",
    "autores": ["Gabriel García Márquez"],
    "precio": 349.0,
    "portada": "https://...",
    "disponible": true
  }
]
```

### `GET /libros/destacados`

Respuesta `200`: arreglo de `LibroResumenResponse`.

### `GET /libros/{id}`

Respuesta `200`:

```json
{
  "id": 1,
  "titulo": "Cien años de soledad",
  "autores": ["Gabriel García Márquez"],
  "editorial": "Sudamericana",
  "categoria": "Novela",
  "isbn": "978-84-376-0494-7",
  "precio": 349.0,
  "descripcion": "Una saga familiar...",
  "portada": "https://...",
  "disponible": true
}
```

> `disponible` es el booleano público para mostrar "Sin stock". La cantidad exacta queda para el panel de administración.

### `GET /categorias`

Respuesta `200`:

```json
[
  { "id": 1, "nombre": "Novela" },
  { "id": 2, "nombre": "Ciencia ficción" }
]
```

### `GET /autores`

Respuesta `200`:

```json
[
  { "id": 1, "nombre": "Gabriel", "apellido": "García Márquez" }
]
```

---

## 3. Carrito

| Método | Ruta | Acceso | Estado | Descripción |
|---|---|---|---|---|
| GET | `/carrito` | Cliente | Nuevo | Items, subtotal, envío y total. |
| POST | `/carrito/items` | Cliente | Nuevo | Agrega un libro. Si ya existe, suma la cantidad. |
| PATCH | `/carrito/items/{libroId}` | Cliente | Nuevo | Cambia la cantidad de un item. |
| DELETE | `/carrito/items/{libroId}` | Cliente | Nuevo | Elimina un item. |

### `POST /carrito/items`

```json
{
  "libroId": 1,
  "cantidad": 2
}
```

### `PATCH /carrito/items/{libroId}`

```json
{
  "cantidad": 3
}
```

### `GET /carrito` · respuesta

```json
{
  "items": [
    {
      "libroId": 1,
      "titulo": "Cien años de soledad",
      "formato": "PASTA_BLANDA",
      "cantidad": 2,
      "precioUnitario": 349.0,
      "subtotal": 698.0
    }
  ],
  "subtotal": 698.0,
  "envio": 80.0,
  "total": 778.0
}
```

> El carrito también puede vivir en `localStorage`; en ese caso se envían los items directamente en `POST /ordenes`. El backend siempre debe recalcular precios y validar stock.

---

## 4. Órdenes

| Método | Ruta | Acceso | Estado | Descripción |
|---|---|---|---|---|
| POST | `/ordenes` | Cliente | Nuevo | Crea la orden, valida y descuenta stock, guarda precios, vacía el carrito y deja la orden en `PENDIENTE`. |
| GET | `/ordenes/mias` | Cliente | Nuevo | Historial del usuario autenticado. |
| GET | `/ordenes/{id}` | Cliente (dueño) | Nuevo | Detalle de la orden. |
| POST | `/ordenes/{id}/cancelar` | Cliente (dueño) | Nuevo | Cancela solo si está `PENDIENTE` o `PROCESANDO`; si no, `409`. Repone stock. |

### `POST /ordenes`

```json
{
  "nombre": "Ana Pérez",
  "direccion": "Calle Reforma 123",
  "ciudad": "Hermosillo",
  "telefono": "6441234567",
  "items": [
    { "libroId": 1, "cantidad": 2 }
  ]
}
```

> `items` es opcional: úsalo cuando el carrito viva en `localStorage`. Si el carrito está en el backend, se toma del carrito del usuario.

Respuesta `201`:

```json
{
  "id": 10,
  "numeroOrden": "ORD-2026-000010",
  "fecha": "2026-11-13T10:30:00",
  "estado": "PENDIENTE",
  "items": [
    {
      "libroId": 1,
      "titulo": "Cien años de soledad",
      "formato": "PASTA_BLANDA",
      "cantidad": 2,
      "precioUnitario": 349.0,
      "subtotal": 698.0
    }
  ],
  "total": 698.0,
  "entrega": {
    "nombre": "Ana Pérez",
    "direccion": "Calle Reforma 123",
    "ciudad": "Hermosillo",
    "telefono": "6441234567"
  }
}
```

### `GET /ordenes/mias`

Respuesta `200`:

```json
[
  {
    "id": 10,
    "numeroOrden": "ORD-2026-000010",
    "fecha": "2026-11-13T10:30:00",
    "productos": ["Cien años de soledad"],
    "estado": "PENDIENTE",
    "total": 698.0
  }
]
```

### `GET /ordenes/{id}` · `POST /ordenes/{id}/cancelar`

Ambos responden `OrdenDetalleResponse`.

---

## 5. Estados y enums

### `EstadoOrden`

`PENDIENTE`, `PROCESANDO`, `ENVIADO`, `ENTREGADO`, `CANCELADO`

### `EstadoPago`

`PENDIENTE`, `COMPLETADO`, `RECHAZADO`, `REEMBOLSADO`

### `MetodoPago`

`TARJETA_CREDITO`, `TARJETA_DEBITO`, `TRANSFERENCIA`, `PAYPAL`

### `Formato`

`PASTA_DURA`, `PASTA_BLANDA`, `DIGITAL`

### `Rol`

`ADMINISTRADOR`, `CLIENTE`

---

## 6. Errores

### Validación (`400`)

Formato devuelto por `GlobalExceptionHandler`:

```json
{
  "status": 400,
  "mensaje": "Error de validación",
  "errores": {
    "correo": "El correo no tiene un formato válido",
    "contrasena": "La contraseña debe tener al menos 8 caracteres"
  }
}
```

### Excepciones personalizadas

| Excepción | HTTP | Uso previsto |
|---|---|---|
| `ContrasenasNoCoincidenException` | 400 | `contrasena` y `confirmar` no coinciden. |
| `CredencialesInvalidasException` | 401 | Correo o contraseña incorrectos en el login. |
| `AccesoDenegadoException` | 403 | El usuario autenticado no es dueño de la orden. |
| `LibroNoEncontradoException` | 404 | El ID de libro solicitado no existe. |
| `OrdenNoEncontradaException` | 404 | El ID de orden solicitado no existe. |
| `RecursoNoEncontradoException` | 404 | Categoría, autor o item de carrito inexistente. |
| `CarritoVacioException` | 409 | Se intentó crear una orden sin productos. |
| `CorreoDuplicadoException` | 409 | El correo ya está registrado. |
| `OrdenNoCancelableException` | 409 | La orden ya no está en un estado cancelable. |
| `StockInsuficienteException` | 409 | Las existencias no alcanzan para la cantidad solicitada. |
| `EndpointNoImplementadoException` | 501 | Endpoint declarado sin implementación. |

Cada excepción lleva `@ResponseStatus` con su código HTTP, así que `GlobalExceptionHandler` solo se encarga de las validaciones (`MethodArgumentNotValidException`).

---

## 7. Estructura de paquetes

```
com.itson.ecommerce
├── config        Seguridad y JWT
├── controller    Endpoints REST
├── dto           Request/Response
├── entity        Entidades JPA
├── exception     Excepciones personalizadas
├── security      Filtro JWT
└── service       AuthService existente
```

> No se agregaron repositorios ni servicios nuevos: los endpoints nuevos solo definen el contrato.
