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
    mvn package -Pnative
    ```