FROM openjdk:11
EXPOSE 2030
ADD target/PostFreeApp.war PostFreeApp.jar
ENTRYPOINT ["java","-jar","/PostFreeApp.jar"]