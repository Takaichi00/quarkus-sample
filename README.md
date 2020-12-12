# Overview
Implementing sample API following [Quarkus getting started](https://quarkus.io/get-started/).

# How to use
- Start MySQL DB with docker
```
cd quarkus-sample/local-environment
docker-compose up -d
```

- Create table
```
cd quarkus-sample
mvn flyway:migrate
```
 
- Run application dev mode
```
mvn compile quarkus:dev
```

- Create the native image
    - Setting your terminal by [Official Document](https://quarkus.io/guides/building-native-image)
    - run this command
 ```
mvn verify -Pnative
```

- Create the native image for Linux (https://quarkus.io/guides/building-native-image#container-runtime)
```
mvn clean package -Pnative -Dnative-image.docker-build=true
``` 

- Create docker image with native image
   
```
mvn package -Pnative -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true
```

※ If OOM Error occurred, you should set "Limit the resource available to Docker Engine"  
![docker-setting](https://miro.medium.com/max/4800/1*mXAD66LClH0Bry5vVNekzA.png)

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
QUARKUS_PROFILE=it-local bash -c 'mvn test-compile failsafe:integration-test'
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
