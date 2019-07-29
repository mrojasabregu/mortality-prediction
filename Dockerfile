FROM openjdk:8-jdk-alpine

RUN mkdir -p /opt/app

ENV PROJECT_HOME /opt/app

COPY ./target/mortality-prediction-0.0.1-SNAPSHOT.jar $PROJECT_HOME/app.jar

WORKDIR $PROJECT_HOME

CMD ["java","-jar","./app.jar"]