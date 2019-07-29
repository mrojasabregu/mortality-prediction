# Mortality prediction Rest API
This is a PoC rest API to predict the mortality date of people using Spring Boot, MongoDB and machine learning, running in docker containers defined by docker-compose yml file.


## Requirements
- Java 8
- Maven 3.3.9
- Docker 1.13.1
- Docker-compose 1.21.0

## Build and Run
Build Docker image
 
```docker build .```
 
Launch an Application and a Mongo container

```docker-compose up```

## Docker
The docker file defines a container based on Java with the jar created by maven package

## Docker-compose
The Docker-compose file describes our multi-container application, the application consists in 3 containers.
 - (1)  running the springboot api linked container with MongoDB.
 - (2) MongoDB
 - (3) Machine learning to predict dates of mortality
 

# Next Steps
- UT Tests
- Mortality Prediction using machine learning
- Authentication
- Implement KMS

## Examples

```localhost:8080/api/persons?page=1&size=7&orderBy=age&direction=DESC```


## Author
Matias Rojas

ar.rojas.matias@gmail.com