# Caching con Google Cloud

### Introduccion
El caching es una tecnica que nos permite almacenar datos en un espacio de memoria el cual iremos a consultar antes de conectarnos a una API externa, con el objetivo de agilizar la obtencion del resultado esperado.

Podriamos pensar en una base de datos que nos permita crear tablas que consistan unicamente en dos columnas, una para las claves y otra para los valores, en donde:
- la clave es la url (o parte de ella) de la api a la cual queremos consultar y
- el valor es el resultado de consultar a esa misma url

### Implementacion
Bajando esto a nuestro dominio, las claves seran las url de las APIs de los climas, por ejemplo, la url que nos devuelve la temperatura en Buenos Aires para dentro de 5 dias. El valor sera la temperatura propiamente dicha.
Si quisieramos consultar nuevamente este dato, no tendremos que conectarnos a la API del clima, sino que tendremos el dato cacheado en la plataforma de Google.

Por suerte, GCP nos provee el servicio de Google Cloud Memorystore, un servicio de almacenamiento de datos en memoria preparado para Redis, siendo Redis un motor de bases de datos indicado para esta tarea debido a que está optimizado para el almacenamiento de tablas de hash (clave/valor)