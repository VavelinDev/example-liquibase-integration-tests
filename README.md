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
* feature/docker - setting up the Docker Oracle database
* feature/liquibase - configuration of the Liquibase structures
* feature/intest - configuration of the separate module for Integration Tests


### 2. Database initialization
When running the app for the first time, prepare the database to work with.
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
<small>Running the ```./gradlew updateSQL``` command before the above you can generate SQL scripts reflecting 
the changes you've made to your database, without actually applying those changes to the database. It allows you
to check if the configuration of Liquibase is done correctly.</small>

## Sources
[Przemek Malirz's ***VShop*** application on GitHub](https://github.com/pmalirz/vshop)

[Oracle Container Registry - ***Docker Image Documentation***](https://container-registry.oracle.com/ords/f?p=113:4:7132997386216:::4:P4_REPOSITORY,AI_REPOSITORY,AI_REPOSITORY_NAME,P4_REPOSITORY_NAME,P4_EULA_ID,P4_BUSINESS_AREA_ID:803,803,Oracle%20Database%20Express%20Edition,Oracle%20Database%20Express%20Edition,1,0&cs=3O9aDhk0TG62vtwnn1iPt8FwP9tFUTiRoYQQgB2DZFKLEoDK408DPdLm-YsbNEMVvjsmYi9_GzDvKbj2ZJKccmg)