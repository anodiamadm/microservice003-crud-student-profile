FROM openjdk:11
EXPOSE 8446
ADD target/microservice003-crud-student-profile.jar microservice003-crud-student-profile.jar
ENTRYPOINT ["java","-jar","/microservice003-crud-student-profile.jar"]