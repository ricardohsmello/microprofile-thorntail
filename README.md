# My Hello World from microservices

This is an example of microservices using java. The ricas-microservices is a spring-boot application that receive a http request and calls the ricas-backend maven module using rest-template.

## This microservice project includes three projects
- ricas-microprofile
- ricas-backend
- ricas-microservices

## Technology
- Spring boot
- HttpRequest
- Wildfly

# Usage
## Config
Navigate until ricas-microservices root and run:
- mvn spring-boot:run
  - The spring boot server i'll be listen on port 9090
  
Navigate until ricas-backend
- mvn wildfly:run
  - The server i'll be listen on port 8080

## Running
Type the follow URL on browser
http://localhost:9090/api/greeting?=HELLO

## Output
Hello Spring Boot application from cluster backend at host: 192.168.68.2
