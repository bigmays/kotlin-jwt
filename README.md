A simple jwt project created with kotlin, spring boot 2 and docker

Login, sign up and a test endpoint to test the jwt created

Spring security is not used

## How to run
go to the project's root:
    
    docker-compose up
    
or simply
    
    ./gradlew bootRun

## Structure

- UserController: standard rest controller where you can login and sign-up
- JwtService / UserService: contains logic for jwt and user management
- WebConfig: configure which endpoint should not be under jwt security
- AuthFilter: jwt authorization filter, check is the request can access that resource
- Test: tests with mockito, junit5, spring test