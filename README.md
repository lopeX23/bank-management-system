# Banking Management System

Este proyecto es una aplicación de gestión de usuarios de un banco. Permite crear cuentas, depositar, extraer dinero y verificar saldo. Está estructurado en una arquitectura de 3 capas: presentación, lógica de negocio y acceso a datos.

## Tecnologías Usadas
- **Lenguaje**: Java
- **IDE**: IntelliJ IDEA

## Requisitos Previos
- Java Development Kit (JDK) 8 o superior
- IntelliJ IDEA o cualquier otro IDE de Java
- Git

## Estructura del Proyecto

```plaintext
├── src
│   └── main
│       ├── java
│       │   ├── edu
│       │   │   └── umss
│       │   │       └── bank
│       │   │           ├── application
│       │   │           │   ├── Account.java
│       │   │           │   ├── AccountService.java
│       │   │           │   └── AccountServiceImpl.java
│       │   │           ├── data
│       │   │           │   ├── AccountDao.java
│       │   │           │   └── AccountDaoImpl.java
│       │   │           └── presentation
│       │   │           │   └── BankUI.java
│                       └── Main.java
