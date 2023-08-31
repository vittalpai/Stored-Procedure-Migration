# Stored-Procedure-Demo
Spring Boot Java application that demonstrates how PL/SQL stored procedures are implemented client-side using Oracle or MongoDB. This application uses polymorphic implementation to connect to MongoDB or Oracle database and exposed through common RESTful APIs. In this sample, we intend to illustrate how a fairly-complex relational stored procedure can be easily converted into a MongoDB aggregation framework using the readily available framework operators and constructs. The application packages the DB implementations and libraries based on the maven and spring boot profiles.

Here is a sample Stored Procedure implementation in Relational Database.
```
CREATE or replace PROCEDURE TOPCUSTOMERS (
  ZIPCODE IN  VARCHAR2,
  OCCUPATION IN  VARCHAR2,
  RES    OUT SYS_REFCURSOR )
AS
 stmt VARCHAR2(2000);
BEGIN
  stmt := 'SELECT C.ID, C.NAME, C.PHONENUMBER, LPAD(SUBSTR (T.CARDNUMBER, 13, 4), 16, ''X'') AS MASKEDCARDNUMBER FROM CUSTOMER C INNER JOIN CUSTOMERTRANSACTIONSUMMARY T ON C.ID = T.CustomerID';
              
   IF ZIPCODE IS NOT NULL AND OCCUPATION IS NOT NULL THEN 
       stmt := stmt || ' WHERE ' || 'C.ZipCode=' || CHR (39) || ZIPCODE || CHR (39) || ' AND C.Occupation=' || CHR (39) || OCCUPATION || CHR (39) ;
  END IF;
  stmt := stmt || ' ORDER BY T.BALANCE DESC FETCH FIRST 10 ROWS ONLY';  

  OPEN RES FOR stmt;
  
  EXCEPTION
        WHEN OTHERS THEN
            dbms_output.put_line(SQLERRM);

END TOPCUSTOMERS;
```
The above procedure uses the LPAD function to take a string, which is a credit card number, and from which we extract a substring (SUBSTR) of the last 4 characters. All the other characters in the string are then “padded” by a single character string of ‘X’.

Here is the equivalent implementation in MongoDB, we can achieve the same masked output using aggregation framework along with a similar set of operators.

```
var pipeline = [
    {
        '$match': {
            'Occupation': 'Business', 
            'ZipCode': '12031'
        }
    },
    {
        '$sort': {
            'Balance': -1
        }
    },
    {
        '$project': {
            'Name': 1, 
            'PhoneNumber': 1, 
            'CardNumber': { '$concat': [ '************', { '$substr': [ '$CardNumber', 12, 4 ] } ] }
        }
    }, 
    {
        '$limit': 10
    }
]
```

## Prerequisites
* Java 1.8
* Maven 3.x
* Oracle/MongoDB Server

## Usage

* Clone the repository 
* Import the Schema & Stored procedure(`schema/oracle.sql`) into Oracle instance.
* Import the JSON documents(`schema/mongodb.json`) into a MongoDB instance.
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
    http://localhost:8080/user/search?occupation=Engineer&zipcode=10291
    ```

    ![REST CALL](/images/db-call.png)