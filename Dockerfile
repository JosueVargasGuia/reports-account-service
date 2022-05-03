FROM openjdk:11
EXPOSE  8097
WORKDIR /app
ADD   ./target/*.jar /app/reports-account-service.jar
ENTRYPOINT ["java","-jar","/app/reports-account-service.jar"]