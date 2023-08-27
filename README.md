# example-liquibase-intest
Example - Integration tests with Liquibase (plus Spring Boot)

This demo project demonstrates how to effectively manage database schema changes using ***Liquibase*** library.
Taking it a step further, the automation of integration tests in a Spring Boot application is showcased using
***Gradle*** as build automation tool. The demo database is built using ***Oracle Database XE***.

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

### 2. Database initialization
When running the example application for the first time, prepare the oracle database to work with.
Run the SQL statements included in 'database/src/main/resources/init/users.sql', use a user with the DBA role.

### 3. Liquibase changes
Run the following command to update changes in your database structures:
```shell
./gradlew update
```
SQL files for step '0' in 'database/src/main/resources/changes' intentionally left blank.
On this basis add next revisions of the database with db operations defined in the sql files in this directory.
Each revision should be noted in the 'database/src/main/resources/changelog.yml' file.

💡
<small>The command ```./gradlew updateSQL``` serves as a dry run variant of the update command. Instead of directly
implementing the SQL instructions on the database, it outputs these instructions to the console. This feature proves
useful when there's a desire to manually review or execute the scripts, such as through an SQL console.</small>


## Sources
[Przemek Malirz's ***VShop*** application on GitHub](https://github.com/pmalirz/vshop)

[Oracle Container Registry - ***Docker Image Documentation***](https://container-registry.oracle.com/ords/f?p=113:4:7132997386216:::4:P4_REPOSITORY,AI_REPOSITORY,AI_REPOSITORY_NAME,P4_REPOSITORY_NAME,P4_EULA_ID,P4_BUSINESS_AREA_ID:803,803,Oracle%20Database%20Express%20Edition,Oracle%20Database%20Express%20Edition,1,0&cs=3O9aDhk0TG62vtwnn1iPt8FwP9tFUTiRoYQQgB2DZFKLEoDK408DPdLm-YsbNEMVvjsmYi9_GzDvKbj2ZJKccmg)

[Getting Started with Liquibase and Gradle](https://contribute.liquibase.com/extensions-integrations/directory/integration-docs/gradle/)