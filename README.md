# JWT

This Project is about JSON Web Token (JWT) with Spring Security and Spring Boot.

### Prerequisites

This project is build with with Maven 3 and Java 1.8.

### Maven Dependency

```
<dependency>
    <groupId>io.jsonwebtoken</groupId>
    <artifactId>jjwt</artifactId>
    <version>0.9.0</version>
</dependency>
```
### Running the Tests

Just start the application with the Spring Boot maven plugin (mvn spring-boot:run). The application is running at http://localhost:8070.

There are 4 Rest API present in this project, These API demonstrate the different levels of access to the endpoints in the API and the different authorization exceptions:

1 **Sample Request to Signup User**
```
POST http://localhost:8070/JWT/v1/api/userSignup
-H "Content-Type: application/json"
-d '{
	"fname": "jony",
	"lname": "mittal",
	"email": "user1@gmail.com",
	"password": "user1"
}'

Sample Response
{
    "code": "200",
    "data": {
        "access_token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMUBnbWFpbC5jb20iLCJleHAiOjE1NTYxODI2MzEsImlhdCI6MTU1NTQ5MTQzMX0.xk2m0-fMJy07wGNvUBhAq2lASenMtW3ado37XTjSf0hn5zERE8iO78yyEZE7fBB42p5EatEXjjZ-XFR5Wpf12w",
        "user": {
            "id": 1,
            "fname": "jony",
            "lname": "mittal",
            "email": "user1@gmail.com",
            "password": "$2a$10$a3s662wciBGEmNhoyV9ux.pm6obtpaRK/Cta30Pf1vTd4JhZhrQfa",
            "lastPasswordResetDate": null,
            "role": {
                "id": 2,
                "role": "ROLE_USER"
            }
        }
    },
    "message": "Record created successfully.",
    "status": "OK"
}
```


## Authors

* **Jony Mittal**

[GitHub](https://github.com/jonymittal)

[StackOverflow](https://stackoverflow.com/users/6931456/jony-mittal)


