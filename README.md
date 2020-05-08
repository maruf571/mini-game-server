## mini game back-end
Implemented code could be a nice example of mini game backend. You have to 
create a java based api without using any third party lib/framework. 
com.sun.net.httpserver.HttpServer  is used as server and Java collection 
framework is used to store the data of the
project.  

## Design Decisions
- All the requests go to the DispatchHandler, DispatchHandler 
uses RequestRouter to route the request to the proper handler.
- BaseRequestHandler is an abstract class for each request handler
- Use ConcurrentHashMap for store data as well as  thread safety.  
- Use Collections.synchronizedSortedSet(TreeSet) for thread safety
 and sorting purpose
- Use Singleton pattern for services 
 
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
$ mvn clean package
$ java -jar target/mini-game-server-1.0-SNAPSHOT.jar
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

## Endpoints
- Following endpoint returns session key and this session key will be valid for 10 minutes.
It's not real authentication process but real session management.  
`GET http://localhost:8000/<user-id>/login`

Example:
```
GET http://localhost:8000/100/login

Response: FMLRGQXDYR
```

- Following endpoints create user score for a particular level. You must pass all the data 
with the request. Score should be pass as a post body. There should not any response
only 200 for the success.

`POST http://localhost:8000/<level>/score?sessionkey=<session-key> <score>`

Response:

```
POST http://localhost:8000/3/score?sessionkey=FMLRGQXDYR

500
```
- Following endpoint will return the max 15 score for a particular
level in descending order. Response should be key-value pair separated 
with comma. where key is user id and value is user score. 

`GET http://localhost:8000/<level>/highscorelist`

Example:
```
GET http://localhost:8000/3/highscorelist

Response: 20=2000,19=1900,18=1800,17=1700,16=1600,15=1500,14=1400,13=1300,12=1200,11=1100,10=1000,9=900,8=800,7=700,6=600
```


 
