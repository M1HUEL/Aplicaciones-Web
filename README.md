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

## Entidades

| Entidad | Tabla | Descripción | Relaciones |
|---|---|---|---|
| `Usuario` | `usuarios` | Cuenta con nombre, correo único, contraseña y rol. | Tiene un `Carrito`, direcciones y órdenes. |
| `Direccion` | `direcciones` | Dirección de envío, con bandera `predeterminada`. | Pertenece a `Usuario`; se usa en `Envio`. |
| `Autor` | `autores` | Autor con nombre, apellido y nacionalidad. | Muchos a muchos con `Libro`. |
| `Categoria` | `categorias` | Categoría del catálogo. | Uno a muchos con `Libro`. |
| `Editorial` | `editoriales` | Editorial del libro. | Uno a muchos con `Libro`. |
| `Libro` | `libros` | Obra con ISBN, sinopsis, portada y páginas. | `Categoria`, `Editorial`, autores y `FormatoLibro`. |
| `FormatoLibro` | `formatos_libro` | Presentación del libro con su precio. | Pertenece a `Libro`; se referencia desde items y stock. |
| `ItemStock` | `items_stock` | Existencias y stock mínimo por formato. | Uno a uno con `FormatoLibro`. |
| `AjusteInventario` | `ajustes_inventario` | Movimiento de inventario con cantidad y motivo. | Pertenece a `ItemStock`. |
| `Carrito` | `carritos` | Carrito activo de un usuario. | Uno a uno con `Usuario`; contiene `ItemCarrito`. |
| `ItemCarrito` | `items_carrito` | Línea del carrito con cantidad y precio unitario. | `Carrito` y `FormatoLibro`. |
| `Orden` | `ordenes` | Orden de compra con número único y estado. | Pertenece a `Usuario`; contiene `ItemOrden`. |
| `ItemOrden` | `items_orden` | Línea de la orden con el precio histórico. | `Orden` y `FormatoLibro`. |
| `Descarga` | `descargas` | Enlace de descarga de un libro digital comprado. | Uno a uno con `ItemOrden`. |
| `Pago` | `pagos` | Pago con método, monto y estado. | Uno a uno con `Orden`. |
| `Envio` | `envios` | Datos de envío con paquetería y número de guía. | `Orden` y `Direccion`. |

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
