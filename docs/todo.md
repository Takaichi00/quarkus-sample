# TODO List

## Main
- [x] Configuration Profiles
    - https://quarkus.io/guides/config#configuration-profiles
- [x] Quarkus version up
    - [x] 1.9.2.Final
- [x] Error Handling
    - [x] Error Testing
- [x] MicroProfile Client (https://download.eclipse.org/microprofile/microprofile-rest-client-1.2.1/microprofile-rest-client-1.2.1.html)
    - [x] Request Google Books API
    - [x] Test with wiremock
    - [x] Client Settings
    - [x] [Fault-Tolerance](https://quarkus.io/guides/microprofile-fault-tolerance)
    - [x] Exception Handling and Test
- [x] [Logging](https://quarkus.io/guides/logging)
    - [x] Dumping Request/Response Json
    - [x] ErrorCode has Message
- [x] Flyway
- [x] Native Image Test
- [ ] Containerizing
    - [x] native image for Linux
    - [ ] create application docker image 
        - https://quarkus.io/guides/building-native-image#container-runtime
        - https://medium.com/@kenta.kosugi/quarkus-%E3%81%A7%E4%BD%9C%E3%82%8B%E7%88%86%E9%80%9F-java-micro-service-2-4ba8596319e8
- [ ] Integration Test
- [ ] Tuning JVM Option 
- [ ] Feature Flag
- [ ] Javadoc
- [ ] Swagger (Open API)
- [ ] JFR
- [ ] Performance Test
- [ ] gRPC (https://www.google.com/amp/s/rheb.hatenablog.com/entry/quarkus_grpc%3Famp%3D1)
- [ ] Micrometer + Prometeus + Grafana (https://yuya-hirooka.hatenablog.com/entry/2020/11/04/232853)
- [ ] Reactive (https://github.com/yoshioterada/Reactive-Java-CosmosDB-On-Quarkus)

## Sub
- [x] Fix `BookRepositoryImplTest` and `GoogleBooksApiClientImplTest`
- [ ] Assert Location Header `BookmarkControllerTest.test_registerBookmark()`
- [ ] Validation when eegister bookmark
