# Ecommerce API

## Endpoints

| Método | Ruta | Acceso | Descripción |
|---|---|---|---|
| POST | `/api/auth/iniciar-sesion` | Público | Inicia sesión con `usuario` y `contrasena`. |
| GET | `/api/auth/perfil` | Cliente | Devuelve el usuario y roles del token actual. |
| POST | `/api/auth/registro` | Público | Crea una cuenta con nombre, correo único y contraseña. |
| POST | `/api/auth/login` | Público | Inicia sesión con `correo` y `contrasena`. Devuelve token. |
| POST | `/api/auth/logout` | Cliente | Cierra la sesión. |
| GET | `/api/auth/me` | Cliente | Devuelve el usuario autenticado. |
| GET | `/api/libros` | Público | Listado de libros con filtros `q`, `categoria`, `autor` y `orden`. |
| GET | `/api/libros/destacados` | Público | Libros destacados para el inicio. |
| GET | `/api/libros/{id}` | Público | Detalle del libro y disponibilidad. |
| GET | `/api/categorias` | Público | Listado de categorías. |
| GET | `/api/autores` | Público | Listado de autores. |
| GET | `/api/carrito` | Cliente | Items, subtotal, envío y total del carrito. |
| POST | `/api/carrito/items` | Cliente | Agrega un libro. Si ya existe, suma la cantidad. |
| PATCH | `/api/carrito/items/{libroId}` | Cliente | Cambia la cantidad de un item. |
| DELETE | `/api/carrito/items/{libroId}` | Cliente | Elimina un item del carrito. |
| POST | `/api/ordenes` | Cliente | Crea la orden desde el carrito. |
| GET | `/api/ordenes/mias` | Cliente | Historial de órdenes del usuario autenticado. |
| GET | `/api/ordenes/{id}` | Cliente (dueño) | Detalle de la orden. |
| POST | `/api/ordenes/{id}/cancelar` | Cliente (dueño) | Cancela la orden y repone el stock. |

## Excepciones personalizadas

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
