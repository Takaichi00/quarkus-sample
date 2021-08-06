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
#  Quarkus currently supports 21.0. (GraalVM Version) 
 ```

- Create the native image for Linux (https://quarkus.io/guides/building-native-image#container-runtime)
```
mvn clean package -Pnative -Dnative-image.docker-build=true
```

- Create docker image with native image & run
  
```
mvn package -Pnative -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true
```

※ If OOM Error occurred, you should set "Limit the resource available to Docker Engine"  
![docker-setting](https://miro.medium.com/max/4800/1*mXAD66LClH0Bry5vVNekzA.png)

```
docker build -f src/main/docker/Dockerfile.native -t quarkus-sample .
docker run -i --rm -p 8080:8080 quarkus-sample
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

## Get All Isbn of Bookmarks
```
curl localhost:8080/v1/bookmarks/isbn
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

## HealthCheck
```
curl localhost:8080/q/health
curl localhost:8080/q/health/live
curl localhost:8080/q/health/ready
```

# SonarQube
## set up SonarQube
- clone https://github.com/Takaichi00/sonarqube-docker
- execute `docker-compose up -d` and run sonarqube
- browse: http://localhost:9000

## install java plugin to SonarQube
- browse: http://localhost:9000/admin/marketplace
    - id/pass = admin/admin
- install plugins "Java Code Quality and Security" and "JaCoCo"
- push「Restart Server」

## run test
- when execute `mvn clean test` command, jacoco outputs report in `target/`
    - pom.xml → `jacoco-maven-plugin`

## analytics with sonarqube
- sonarqube settings file is sonar-project.properties
- exclude lombok → lombok.config `lombok.addLombokGeneratedAnnotation = true`
```
mvn sonar:sonar
```

## run Gatling test
```
mvn gatling:test
```

## Confirm
- http://localhost:9000/dashboard?id=com.takaichi00%3Aquarkus-sample

# Adjusting Quarkus 2.0.0

* Quarkus 2.0.0 Released → https://github.com/quarkusio/quarkus/releases/tag/2.0.0.Final
