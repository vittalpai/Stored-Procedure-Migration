# Stored-Procedure-Demo
Spring Boot Java application that demonstrates how PL/SQL stored procedures are implemented client-side using Oracle or MongoDB. This application uses polymorphic implementation to connect to MongoDB or Oracle database and exposed through common RESTful APIs. In this sample, we intend to illustrate how a fairly-complex relational stored procedure can be easily converted into a MongoDB aggregation framework using the readily available framework operators and constructs. The application packages the DB implementations and libraries based on the maven and spring boot profiles.

Here is a sample Stored Procedure implementation in Relational Database.
```
create or replace PROCEDURE CARDMASK 
(
  FIRSTNAME IN  VARCHAR2,
  LASTNAME  IN  VARCHAR2,
  RES    OUT SYS_REFCURSOR
) AS 
    stmt VARCHAR2(200);
BEGIN
    stmt := 'SELECT ID,FIRSTNAME, LASTNAME, AGE, PHONE, LPAD(SUBSTR (CARDNUMBER, 13, 4), 16, ''X'') AS CARDNUMBER FROM USERS';
    IF FirstName IS NOT NULL AND LastName IS NOT NULL THEN 
       stmt := stmt || ' WHERE ' || 'FirstName=' || CHR (39) || FirstName || CHR (39) || ' AND LastName=' || CHR (39) || LastName || CHR (39) ;
    END IF; 
    
    OPEN RES FOR stmt;
    
    EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line(SQLERRM);

END CARDMASK;
```
The above procedure uses the LPAD function to take a string, which is a credit card number, and from which we extract a substring (SUBSTR) of the last 4 characters. All the other characters in the string are then “padded” by a single character string of ‘X’.

Here is the equivalent implementation in MongoDB, we can achieve the same masked output using aggregation framework along with a similar set of operators.

```
var pipeline = [
    {
        "$match" : {
            "FirstName": "Vittal",
            "LastName": "Pai"
        }
    },
    {
        "$project" : {
            "_id" : 1,
            "firstName" : 1,
            "lastName" : 1,
            "age" : 1,
            "phone" : 1,
            "cardNumber" : {
                "$concat": [ 'XXXXXXXXXXXX', {
                    "$substrCP" : ["$cardNumber", 12, 4]
                }]
            }
            
        }
    }
]
```

## Prerequisites
* Java 1.8
* Maven 3.x
* Oracle/MongoDB Server

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
* Build & run the application with following command in your terminal. You can connect to MongoDB/Oracle database backend by setting spring boot profile and maven profile value to  `mongodb`/`oracle` while running application. 
    ```
    mvn clean package
    mvn spring-boot:run -Dspring-boot.run.profiles=mongodb -P mongodb
    ```

* Invoke the following GET endpoints using REST Clients like POSTMAN, etc to fetch the data.
    ```
    http://localhost:8080/user/all
    http://localhost:8080/user/search?lastName=Pai&firstName=Vittal
    ```

    ![REST CALL](/images/db-call.png)