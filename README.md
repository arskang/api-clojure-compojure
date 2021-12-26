# Reto CRUD básico con clojure

#### Librerías y otros

- [Leiningen](https://leiningen.org/)
- [compojure-api](https://github.com/metosin/compojure-api)

#### Crear un nuevo proyecto

`lein new compojure-api aplicacion-nombre`

#### Iniciar el servidor localmente

`lein ring server`

#### Packaging and running as standalone jar

```
lein do clean, ring uberjar
java -jar target/server.jar
```

#### Packaging as war

`lein ring uberwar`
