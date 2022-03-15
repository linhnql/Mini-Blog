FROM maven:3.5-jdk-8-alpine AS BUILD
#LABEL Diep Dang
WORKDIR /home/app
COPY miniblog-api /home/app/miniblog-api
COPY miniblog-client /home/app/miniblog-client
COPY miniblog-service /home/app/miniblog-service
COPY yaml /home/app/yaml
COPY pom.xml /home/app/
RUN mvn clean package -DskipTests

FROM openjdk:8-jdk-alpine
COPY --from=BUILD /home/app/miniblog-service/target/miniblog-service-0.0.1.jar miniblog-service-0.0.1.jar
ENTRYPOINT ["java","-jar","miniblog-service-0.0.1.jar"]