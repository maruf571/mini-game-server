## mini game back-end
This is an assignment project. Implemented code could be a nice 
example of mini game backend. com.sun.net.httpserver.HttpServer  is used
as server and Java collection framework is used to store the data of the
project. 

## Design Decisions
- All the requests go to the DispatchHandler, DispatchHandler 
uses RequestRouter to get the proper handler to handle the request
- BaseRequestHandler is an abstract class for each request handler, 
perform general task
- Use map to store the data. Chose ConcurrentHashMap for thread 
safety.  
- Use Collections.synchronizedSortedSet(TreeSet) for thread safety
 and sorting purpose
 

## Modules/Class Introduction
handler: package related to handle request and response data.
model: package related to data model 
service: package where all the business logic sstay
MiniGameServer: http server create from this file 
Application: Application start from this class


## Development 
- To run locally
```
$ mvn clean compile exec:java
```

- To run the jar
```
$ mvn clean install
$ cd target
$ java -jar mini-game-server-1.0-SNAPSHOT.jar
```
in both cases a server start with the port 8000

## Test
- To run unit test
```
$ mvn clean test 
``` 
- To run unit test + integration test
```
$ mvn clean verify
```

## TODO

- change the readme file extension to txt 
- 