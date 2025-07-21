# Microservicio de trazabilidad (food-court-tracking-service)

## Descripción General

Microservicio encargado de gestionar y almacenar los registros de trazabilidad de los pedidos generados en la plazoleta de comidas.
Este proyecto forma parte de un sistema de gestión de pedidos para una plazoleta de comidas.
Este microservicio utiliza **MongoDB** como base de datos no relacional, optimizada para almacenar los logs de trazabilidad de forma eficiente.

## Arquitectura

Este microservicio está diseñado siguiendo los principios de la Arquitectura Hexagonal (Puertos y Adaptadores). Esto asegura una clara separación de la lógica de negocio del dominio de los detalles de infraestructura (bases de datos, frameworks web, etc.), haciendo el código más modular, testable y adaptable a cambios futuros.

### Componentes Principales

* **Núcleo (Dominio y Servicios de Aplicación):** Contiene la lógica de negocio fundamental del servicio de usuarios, como la validación de usuarios, reglas de registro y autenticación.
* **Puertos:** Interfaces que definen cómo el núcleo interactúa con el mundo exterior (APIs de entrada y repositorios de salida).
* **Adaptadores de Entrada (Driving Adapters):** Implementaciones de las APIs REST que exponen las funcionalidades del servicio.
* **Adaptadores de Salida (Driven Adapters):** Implementaciones que conectan el núcleo con la base de datos y otras infraestructuras (ej., hashing de contraseñas).

## Tecnologías Utilizadas

* **Lenguaje de Programación:** Java
* **Framework:** Spring Boot
* **Base de Datos:** MongoDB
* **Gestión de Dependencias:** Gradle
* **Seguridad:** Spring Security (para autenticación/autorización), JWT (para tokens).

## Cómo Empezar

### Prerequisitos

* Java Amazon Corretto 17
* Gradle
* Una instancia de base de datos MongoDB en ejecución.
