FROM openjdk:17-alpine
 
 COPY target/createticket-0.0.1-SNAPSHOT.jar ticket.jar
 
ENTRYPOINT [ "java","-jar","/ticket.jar"]
 
 
 
 
 