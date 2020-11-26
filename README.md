# Overview
Implementing sample API following [Quarkus getting started](https://quarkus.io/get-started/).

# How to use
- start MySQL DB with docker
```
cd quarkus-sample/local-environment
docker-compose up -d
```

- create table
```
cd quarkus-sample
mvn flyway:migrate
```
 
- run application dev mode
```
mvn compile quarkus:dev
```

- create native image
    - Setting your terminal by [Official Document](https://quarkus.io/guides/building-native-image)
    - run this command
 ```
mvn verify -Pnative
```

## Testing
- Unit Test
```
mvn test
```

### Testing native image

- Start MySQL docker container
```
cd quarkus-sample/local-environment
docker-compose up -d
```

- Create table
```
cd ../
mvn flyway:clean flyway:migrate
```

- Native Image Compile
```
mvn verify -Pnative
```

- Execute Integration Test to executable native image
```
mvn test-compile failsafe:integration-test
```

## Profile
- If you want to use custom profiles, please set system property or the QUARKUS_PROFILE environment variable.
    - [see official document](https://quarkus.io/guides/config#configuration-profiles)

- e.g. 
```
export QUARKUS_PROFILE=custom
```

# Sample Request
## Get All Bookmarks
```
curl localhost:8080/v1/bookmarks
```

## Search book info by ISBN (using GoogleBooks API)
```
curl localhost:8080/v1/books/9784865942248
curl localhost:8080/v2/books/9784865942248
```

## Register Book as Bookmarks
```
curl -X POST localhost:8080/v1/bookmarks/9784865942248
```
