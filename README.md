# poe-stash-searcher

Export and explore your POE stashes

## Building

run tests:
```
./mvnw clean test
```

package the application:
```
./mvnw clean package
```

run the application:
```
./mvnw clean compile exec:java -Dexec.mainClass="com.martin.poestashsearcher.<A_Main>"
```
default to StashExportMain

## POE API

POE public API requires an Oauth application key, but GGG is curently unable to process new application demands so this application uses, for now, the legagy API and relies on an access token from the official website.

The account and sessionId must be defined in the `application.properties`.