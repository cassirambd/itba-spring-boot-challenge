# API RESTful para gestión de productos alimenticios.

API RESTful desarrollada con Spring Boot para gestionar productos. Permite crear, consultar, eliminar y notificar fechas
de expiración de cada producto.
Proyecto realizado como challenge para ITBA.

---

## Setup

### Requisitos

* Java 21.0.7 (https://www.oracle.com/java/technologies/downloads/#java21)
* Maven 3.9+ (https://maven.apache.org/download.cgi)
* Docker Desktop (https://www.docker.com/products/docker-desktop/)

---

## ¿Cómo levantar y compilar el proyecto?

### Localmente

```bash
   git clone https://github.com/cassirambd/itba-spring-boot-challenge.git
   cd itba-spring-boot-challenge
   cd challenge
```

### Vía Docker

---

## Endpoints

### Obtener todos los productos

```
curl --location 'http://localhost:8080/api/v1/product/list'
```

### Obtener producto según id

```
curl --location 'http://localhost:8080/api/v1/product/2'
```

### Crear nuevo producto

```
curl --location 'http://localhost:8080/api/v1/product' \
--header 'Content-Type: application/json' \
--data '{
  "productName": "Tallarín",
  "productSuitable": false,
  "productBrand": "Lucchetti",
  "productExpirationDate": "2026-02-24"
}'
```

### Actualizar producto

```
curl --location --request PUT 'http://localhost:8080/api/v1/product' \
--header 'Content-Type: application/json' \
--data '{
  "productId": "5",
  "productName": "Mostachol",
  "productSuitable": true,
  "productBrand": "Lucchetti",
  "productExpirationDate": "2026-02-24"
}'
```

### Eliminar producto

```
curl --location --request DELETE 'http://localhost:8080/api/v1/product/5'
```