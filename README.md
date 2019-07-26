# My Hello World from microservices

This is an example of microservices using java. 

We have an archicheture distributed in some services, where exists a service layer ricas-api-gatewayan that invoke others two microservice, such spring boot and microprofile service. Thats services, call backend api as you can see in image below:

![Quick Start architecture for ricardohsmello/microservices](https://imagizer.imageshack.com/v2/752x452q90/r/923/4wtnFL.png)

## This microservice project includes four projects
- ricas-microprofile
- ricas-backend
- ricas-microservices
- ricas-api-gateway

## Technology
- Spring boot
- Wildfly
- Apache Camel

# Usage
## Config
Navigate until ricas-microservices root and run:
- mvn spring-boot:run
  - The spring boot server i'll be listen on port 9090
  
Make sure that the ricas-microservices is running and navigate until ricas-backend and run: 
- mvn wildfly:run
  - The server i'll be listen on port 8080
  
 Connecting microprofile to backend
 - Navigate until backend folder and run
   - mvn clean wildfly:run
 - Navigate until ricas-microfile project and run:
   - mvn thorntail:run "-Dswarm.network.socket-binding-groups.standard-sockets.port-offset=100"
   #### Note: the above command changes the default port of wildfly to not collide with backend service 


## Running
Type the follow URL on browser
http://localhost:9090/api/greeting

## Output
Hello Spring Boot application from cluster backend at host: 192.168.68.2
