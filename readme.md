# Práctica 3. Protocolos y reactividad

## Technologies used

- Java 17
- Spring Boot 2.6.1
- MySQL 5.7/8.0.22
- MongoDB 5.0.5

## Requirements

- Java 17
- Maven 3.5 or higher
- Docker
- Node

## Documentation

- [Requirements](./requirements.md)

## Build

## Install

```bash
node install.js
```

### Install via npm global (alternative)

```bash
git clone git@github.com:torrespro/practica3-eoloplanner.git
cd practica3-eoloplanner
npm i
```

```bash
cd toposervice
mvn clean install
```

## Run services

```bash
node exec_aux_services.js
node exec.js
```
## Run Services via npm (alternative)
### MongoDB

```bash
npm run mongodb
```

### MySQL

```bash
npm run mysql
```

### toposervice

```bash
mvn spring-boot:run
```

### weatherservice

```bash
npm run server
npm run weatherservice
```

## How to test

Cities available: 

"Avilés", "Toledo", "Madrid", "Barcelona", "Jaca", "Andorra", "Valencia", "Sevilla", "Zaragoza", "Málaga", "Murcia", "Palma", "Bilbao", "Alicante", "Córdoba", "Valladolid", "Vigo", "Gijón", "Vitoria"
