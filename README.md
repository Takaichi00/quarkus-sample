# Overview
Implementing sample API following [Quarkus getting started](https://quarkus.io/get-started/).

# How to use
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

- Testing an existing native executable
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
