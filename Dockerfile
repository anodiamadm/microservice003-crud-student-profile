FROM openjdk:11
EXPOSE 8446
ADD target/CRUDStudentProfile.jar CRUDStudentProfile.jar
ENTRYPOINT ["java","-jar","/CRUDStudentProfile.jar"]