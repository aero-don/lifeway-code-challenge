# Lifeway Code Challenge - Word Count Application

## Build and Run Requirements
Java 17 or higher is required because the application uses the Java "record" type.

## Assumptions
1. The REST endpoint is not required to be secured.
2. The request ids are not required to be persisted across application restarts.
3. A request id may not be null, an empty string or a string with only blanks.
4. A message may not be null or and empty, but may be a string with only blacks. In the case of a string with only blanks the word count is zero and the id will be ignored for any future requests with the same id.

## Clean, Build, Run, Test and Document

The project uses the Gradle build tool and the Micronaut framework.

All commands are executed from the root directory of the project.

### Clean

* Linux/Mac: `./gradlew clean`
* Windows: `gradlew.bat clean`

### Build

The first build will take a little time to download all the dependencies.

The build target depends on the test target; therefore, all tests are executed as part of the build process.

* Linux/Mac: `./gradlew build`
* Windows: `gradlew.bat build`

Test Results: [build/reports/tests/test/index.html](build/reports/tests/test/index.html)

### Run

The application is configured to serve up the REST endpoint on port 8787.

* Linux/Mac: `./gradlew run`
* Windows: `gradlew.bat run`

The application includes OpenAPI support and the Swagger UI can be accessed with the following URL: [http://localhost:8787/swagger-ui/](http://localhost:8787/swagger-ui/)

### Test

May need to execute [clean](#clean) to re-run tests.

* Linux/Mac: `./gradlew test`
* Windows: `gradlew.bat test`

### Documentation

All code includes javadoc documentation.

* Linux/Mac: `./gradlew javadoc`
* Windows: `gradlew.bat javadoc`

javadoc: [build/docs/javadoc/index.html](build/docs/javadoc/index.html)
