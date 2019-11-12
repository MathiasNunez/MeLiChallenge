# MeLi Challenge

- Aplicación desarrollada según lo solicitado [aquí](https://drive.google.com/file/d/0B76L3t_S06G1QjlQZlpud1FfdDdwMi0yUjhuSEJ1MDBYTm9n/view)
- La aplicación puede ser descargada e instalada siguiendo este [link](https://rink.hockeyapp.net/apps/96fbf88639de4e9dbba92117611b657b/app_versions/1)

## Compilación de la aplicación

* Este proyecto fue hecho utilizando Gradle 3.4.2, Kotlin 1.3.50 y Android Studio 3.5.2.
* El mismo contiene una variable de entorno `URL_BASE` configurada en el archivo `app/build.gradle`.
* El SITE_ID utlilizado fue `MLA`.

## Arquitectura

Se aplica en la aplicación el patrón **MVP**, Model View Presenter, con el fin de separar la capa de negocio de la capa de  presentación. La UI está implementada en cada xml layout correspondiente y su controlador (Activity o Fragment), el cual implementa una interfaz llamada BaseView para mantenerlo indiferente al Presenter asociado.

Por esta razón, una estructura típica para una pantalla sería:
* XML Layout.
* Activity o Fragment implementando BaseView.
* Un Presenter.

## Mnunez Core
Se utilizó en este aplicación una librería de implementación propia que contiene las clases bases para la fácil implementación del patrón MVP. Además contiene la configuración de la capa de networking utilizando Retrofit, RxJava and Gson.
También brinda un conjunto de extensiones y utlidades que son y pueden ser utilizadas en diferentes aplicaciones.

## Funcionalidades

* Buscador:
  - Se brinda al usuario un campo de búsqueda para realizar consultas sobre un producto.
  - Cada búsqueda realizada es guardada localmente en el dispositvo y presentada al usuario en forma de lista como búsqueda reciente.
  - A medida que se escribe en el campo de búsqueda, de haber búsquedas recientes que coincidan con lo escrito, se filtran los resultados de dichas búsquedas recientes.
  
* Listado de Resultados:
  - La aplicación revela un listado de productos que coinciden con la consulta mencionada en el punto anterior.
  - Se provee de una imagen, el nombre y precio de cada producto.
  - Al tope del listado se indica cuántos resultados fueron encontrados.
  - La aplicación inicialmente revela los primeros 20 y a medida que el usuario navega por la lista, se cargan otros 20 más y así sucesivamente.
  - En la parte baja derecha de la pantalla, se mustra un indicador flotante con la cantidad de resultados que están siendo mostrados.

* Detalle de un producto:
  - Esta pantalla revela información más completa sobre un producto previamente seleccionado en el listado antes mencionado.
  - Al tope, se muestra un slider con las imágenes de este producto y un indicador de cuántas imágenes hay.
  - Se muestra el nombre, precio y pago por cuotas, seguido de más búsquedas de ese vendedor (presentadas de forma similar al listado de producto, las cuales son funcionales y admiten que el usuario navege al detalle de la misma) y finalmente una descripción completa del producto.
