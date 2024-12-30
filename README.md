# Restaurante App 🥗

Bienvenido a **RestauranteApp**, una aplicación para gestionar restaurantes, review y menus.

## Características principales

### Gestión de Restaurantes
- Crear, editar y eliminar información de restaurantes.
- Consultar la lista de restaurantes disponibles.

### Gestión de Menús
- Asociar un menú a un restaurante.
- Añadir, editar y eliminar platos en un menú.

### Reviews de Restaurantes
- Crear reviews generales para un restaurante (calificación y comentario).
- Listar las reviews asociadas a un restaurante.
- Calcular la calificación promedio de un restaurante basada en las reviews.

### Reviews de Platos
- Crear reviews para platos específicos (calificación y comentario).
- Listar las reviews de un plato.
- Calcular la calificación promedio de un plato.

---

## Tecnologías y patrones utilizados

### Tecnologías
- **Java 17**: Lenguaje principal del proyecto.  Uso de `Map`, `Set`, `LinkedList`

### Patrones de diseño
- **Singleton**: Gestión de una única instancia del repositorio central de datos.
- **Factory**: Creación de reviews dinámicas (restaurantes y platos).
- **Observable**: Notificaciones automáticas cuando se agregan reviews o cambian calificaciones promedio.
- **Command**: Sustitución de estructuras condicionales complejas.

---

## Estructura del proyecto

```
src/
├── controller/          # Controladores de la aplicación.
├── service/             # Lógica de negocio.
├── repository/          # Acceso a datos (repositorios).
├── model/               # Modelos de negocio (clases principales).
├── factory/             # Implementación del patrón Factory.
├── observer/            # Implementación del patrón Observable.
├── main/                # Punto de entrada de la aplicación.
├── utils/               # 

```

---

## Cómo ejecutar

1. **Clona el repositorio:**
   ```bash
   git clone https://github.com/usuario/RestauranteApp.git
   ```

2. **Abre el proyecto en tu IDE favorito:**
    - IntelliJ IDEA, Eclipse, NetBeans, etc.

3. **Compila el proyecto:**
   Asegúrate de que tu IDE esté configurado para compilar proyectos Java.

4. **Ejecuta el archivo `Main.java`:**
   Encuentra el archivo en la carpeta `main/` y ejecútalo.

