# My Hello World from microservices
## Repository from microservices that includes three projects
- Microprofile
- ricas-backend
- ricas-microservices

# Usage
## Config
Navigate until ricas-microfiles root and run:
- mvn spring-boot:run
  - The spring boot server i'll be listen on port 9090
  
Navigate until ricas-backend
- mvn wildfly:run
  - The server i'll be listen on port 8080

## Running:
Type the follow URL on browser
http://localhost:9090/api/greeting?=HELLO

## Output
Hello Spring Boot application from cluster backend at host: 192.168.68.2
