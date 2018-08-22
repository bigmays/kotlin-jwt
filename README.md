A simple jwt project created with kotlin and spring boot 2. 

Login, sign up and a test endpoint to test the jwt created

Spring security is not used

### Structure

- UserController: standard rest controller where you can login and sign-up
- JwtService / UserService: contains logic for jwt and user management
- WebConfig: configure which endpoint should not be under jwt security
- AuthFilter: jwt authorization filter, check is the request can access that resource
- Test: tests with mockito, junit5, spring test