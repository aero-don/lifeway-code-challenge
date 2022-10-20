# Lifeway Code Challenge - Word Count Application

## Build and Run Requirements
Java 17 or higher is required because the application uses the Java "record" type.

## Repository
Clone repository: `git clone https://github.com/aero-don/lifeway-code-challenge.git`

## Branches
There are two branches in the repository to demonstrate two different implementations.

### Master Branch
The master branch contains a state-based implementation using a layered design approach.

Checkout master branch: `git checkout master`

### Event Driven Branch
The event driven branch contains an event driven implementation using a layered design approach.  This branch also demonstrates the use of a reactive HTTP endpoint, allowing the client to decide if the HTTP response should be delivered synchronously or asynchronously.

Checkout event driven branch `git checkout event-driven`

## Assumptions
1. The REST endpoint is not required to be secured.
2. The request ids are not required to be persisted across application restarts.
3. A request id may not be null, an empty string or a string with only blank spaces.
4. A message may not be null or an empty string, but may be a string with only black spaces. In the case of a string with only blank spaces the word count is zero and the id will be ignored for any future requests with the same id.
5. The words in the message are separated by one or more blank spaces, and any combination of characters separated by a blank space is considered to be a word.
   1. If other characters (e.g., comma, period, question mark, exclamation mark) can separate two words without a blank space, then the regular expression used to tokenize the string would need to be updated.
   2. If any combination of characters separated by a blank space is not considered a word, then additional filtering would be required to not count those combinations as words.

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

The application REST endpoint accepts an HTTP POST request at the following URL: [http://localhost:8787/words/](http://localhost:8787/words/)

The application includes OpenAPI support and the Swagger UI can be accessed at following URL: [http://localhost:8787/swagger-ui/](http://localhost:8787/swagger-ui/)

### Test

For tests, the application is configured to serve up the REST endpoint on port 8887.

* Linux/Mac: `./gradlew test`
* Windows: `gradlew.bat test`

Execute [clean](#clean) to re-run tests.
 
See [build](#build) for location of test results.

### Documentation

All code includes javadoc documentation.

* Linux/Mac: `./gradlew javadoc`
* Windows: `gradlew.bat javadoc`

javadoc: [build/docs/javadoc/index.html](build/docs/javadoc/index.html)
