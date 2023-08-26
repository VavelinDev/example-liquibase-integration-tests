# example-liquibase-intest
Example - Integration tests with Liquibase (plus Spring Boot)



## Commit messages convention
Commit messages start with the following identifiers enclosed in brackets:

* [CONFIG] - for overall project configuration
* [DOCK] - for docker configuration
* [DB] - for database (liquibase) changes
* [INTEST] - for integration tests



## Branches names used in this demo project

* main
* config/* - overall project configuration
* feature/docker-oracle - setting up the Docker Oracle database
* feature/liquibase - configuration of the Liquibase structures
* feature/intest - configuration of the separate module for Integration Tests


## Prerequisites

- Docker
- JDK 17, make sure that the JAVA_HOME environment variable is properly configured


## Run the app
### 1. Docker
From the project directory run the following command:
```shell
docker-compose up -d oracle
```
⏳ The Docker image is downloaded during first build thus it  may take a little longer than usual


## Sources
[Przemek Malirz's ***VShop*** application on GitHub](https://github.com/pmalirz/vshop)

[Oracle Container Registry - ***Docker Image Documentation***](https://container-registry.oracle.com/ords/f?p=113:4:7132997386216:::4:P4_REPOSITORY,AI_REPOSITORY,AI_REPOSITORY_NAME,P4_REPOSITORY_NAME,P4_EULA_ID,P4_BUSINESS_AREA_ID:803,803,Oracle%20Database%20Express%20Edition,Oracle%20Database%20Express%20Edition,1,0&cs=3O9aDhk0TG62vtwnn1iPt8FwP9tFUTiRoYQQgB2DZFKLEoDK408DPdLm-YsbNEMVvjsmYi9_GzDvKbj2ZJKccmg)