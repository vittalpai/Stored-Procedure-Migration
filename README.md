# Stored-Procedure-Demo
Spring Boot Java application that demonstrates how PL/SQL stored procedures are implemented client-side using Oracle or MongoDB. This application uses polymorphic implementation to connect to MongoDB or Oracle database and exposed through common RESTful APIs.

## Prerequisite
* Java 1.8
* Maven 3.x

## Usage

* Clone the repository 
* Update the MongoDB/Oracle Server credentials in `src/main/resources/application.properties` file
```
# MongoDB
spring.data.mongodb.uri=mongodb+srv://username:password@xxx.xxxx.mongodb.net
spring.data.mongodb.database=sample

# Oracle
spring.datasource.url=jdbc:oracle:thin:@X.X.X.X:1521:ORCL
spring.datasource.username=username
spring.datasource.password=password
```
* Build & run the application with following command in your terminal. You can connect to MongoDB/Oracle database backend by setting spring boot profile value to  `mongodb`/`oracle` while running application. 
```
mvn clean package
mvn spring-boot:run -Dspring-boot.run.profiles=mongodb
```

* Invoke the following GET endpoints using REST Clients like POSTMAN, etc to fetch the data.
```
http://localhost:8080/user/all
http://localhost:8080/user/search?lastName=Pai&firstName=Vittal
```

![REST CALL](/images/db-call.png)