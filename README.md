# Mortality prediction Rest API
This is a PoC rest API to predict the mortality date of people using Spring Boot, MongoDB and machine learning, running in docker containers defined by docker-compose yml file.
The prediction of cause of death is based in the dataset provided by Wold Health Organization.


## Requirements
- Java 8
- Maven 3.3.9
- Docker 1.13.1
- Docker-compose 1.21.0

## Build and Run
Build Docker image
 
```bash
docker build .
```
 
Launch an Application and a Mongo container

```bash
docker-compose up --build
```

## Docker
The docker file defines a container based on Java with the jar created by maven package

## Docker-compose
The Docker-compose file describes our multi-container application, the application consists in 3 containers.
1.  Running the springboot api linked container with MongoDB.
2.  MongoDB
3.  Machine learning to predict dates of mortality
 

 

## Examples

### GET
```http://ec2-54-165-236-240.compute-1.amazonaws.com/api/people?page=0&size=5&orderBy=age&direction=DESC```

### POST

```
http://ec2-54-165-236-240.compute-1.amazonaws.com/api/person

{
	"firstName": "b",
	"lastName":"b",
	"age":20,
	"birthDate": "2014-07-29"
}
```
## Swagger


http://ec2-54-165-236-240.compute-1.amazonaws.com/swagger-ui.html


## Dataset
Health statistics and information systems

https://www.who.int/healthinfo/statistics/mortality_rawdata/en/
http://apps.who.int/healthinfo/statistics/mortality/causeofdeath_query/ext1_params.php


# Next Steps
- [x] Springboot rest api
- [x] Integration with mongo
- [x] Docker & docker-compose
- [x] Mortality prediction base on dataset of Wold Health Organization
- [ ] UT Tests
- [ ] Smoke Tests
- [ ] Integration Tests
- [ ] Mortality Prediction using machine learning
- [ ] Authentication
- [ ] Implement KMS

## Author
Matias Rojas

ar.rojas.matias@gmail.com