<h1>API RESTful de Tickets</h1>


<h4> NOTA: Antes de correr en local</h4>
* Debes tener instalado spring boot con Maven en la computadora
* Se debe tener instalado el JDK de Java, de la versión 8 en adelante


<h2>Pasos a seguir</h2>

<ul>
  <li>1. Clona el proyecto <a href="https://github.com/julianzp/tickets-api-rest.git" target="_blank"></a>.</li>
  <li>2. ve a la carpeta docker-setup y sigue las instrucciones del Dockerfile. Ahí está la BD con la tabla 'tickets' y los registros en el archivo db_dump.sql</li>
</ul>

<h2>Corre el Proyecto</h2>

* Puedes abrir un terminal en la carpeta principal y correr el comando:  </p>

```bash
mvn spring-boot:run
```

* O si tienes algún framework con spring para correr el proyecto, desde ahí también es una opción</p>

La aplicación va correr en: <http://localhost:8080>

* Nota: Si al abrir el proyecto aparece algún error, recarga el maven project (reload maven project)

### Exploremos el API

### GET tickets or ticket

| MÉTODO | URL | DESCRIPCIÓN | REQUEST BODY VÁLIDO |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/tickets?page={pagina}&size={tamaño} | Usar la paginación si se quiere. El API valida si no hay registros (Hay algunos de prueba en la tabla) | |
| GET    | /api/ticket/{ticket_id} | Proveer el id del ticket a consultar. El API devuelve error 500 si no encuentra el registro | |

### POST ticket

| MÉTODO | URL | DESCRIPCIÓN | REQUEST BODY VÁLIDO |
| ------ | --- | ----------- | ------------------------- |
| POST    | /api/postTicket | Agrega ticket en la tabla. El API valida si el estatus se digitó exclusivamente como: abierto/cerrado | [JSON](#ticketcreate)|

### PUT ticket

| MÉTODO | URL | DESCRIPCIÓN | REQUEST BODY VÁLIDO |
| ------ | --- | ----------- | ------------------------- |
| PUT    | /api/updateTicket/{ticket_id} | Actualiza un ticket en específico. El API valida si el estatus se digitó exclusivamente como: abierto/cerrado | [JSON](#ticketupdate)|

### DELETE ticket

| MÉTODO | URL | DESCRIPCIÓN | REQUEST BODY VÁLIDO |
| ------ | --- | ----------- | ------------------------- |
| DELETE    | /api/deleteTicket/{ticket_id} | Elimina algún registro en específico. Si el id no existe devuelve status 500 | |

##### <a id="ticketcreate">Create ticket -> /api/postTicket</a>
```json
   {
        "usuario": "Wilson Anibal",
        "fechaCreacion": "2023-06-13",
        "fechaActualizacion": "2023-06-06",
        "estatus": "cerrado"
    }
```

##### <a id="ticketupdate">Create ticket -> /api/updateTicket/{ticket_id}</a>
```json
      {
        "usuario": "James David",
        "fechaCreacion": "2023-07-14",
        "fechaActualizacion": "2023-07-23",
        "estatus": "abierto"
    }
```

<h4>Contacto</h4>

Si ocurre alguna particularidad o dificultad para correr el proyecto, contactar a: zapataparrajulian@gmail.com