# JWT

This Project is about just JSON Web Token (JWT) with Spring Security and Spring Boot.

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

There are 4 Rest API present in this project.
1. Signup User
2. Login user
3. Get User Details.
4. Reset Password
demonstrate the different levels of access to the endpoints in the API and the different authorization exceptions: