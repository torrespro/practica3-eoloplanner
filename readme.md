# Práctica 3. Protocolos y reactividad

Aplicación distribuida para la generación de plantas eólicas.

## Install

```bash
git clone git@github.com:torrespro/practica3-eoloplanner.git
cd practica3-eoloplanner
npm i
```

## Run services

### MongoDB

```bash
npm run mongodb
```

### MySQL

```bash
npm run mysql
```

<!-- Andrés, mira a ver cuál es el órden correcto de correr los servicios -->

### toposervice

#### Install 

```bash
cd toposervice
mvn install
```

#### Run

```bash
mvn spring-boot:run
```

### weatherservice

```bash
npm run server
npm run weatherservice
```


