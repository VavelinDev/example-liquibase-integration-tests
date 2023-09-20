# Integration Tests with **Liquibase, Oracle** and **Spring Boot**

This demo project showcases the execution of integration tests using Liquibase, even in the absence of Testcontainers or
Docker. The primary objectives of the project are twofold:

1. Conducting integration tests efficiently by leveraging Liquibase to set up databases, often of a complex nature.
2. Executing tests against pre-installed instances of the Oracle Database, a common scenario in many companies.

Note: Despite the emphasis on point 2, the project includes a docker-compose script to facilitate running the Oracle Instance.

## Prerequisites

- Docker or any Oracle Instance
- JDK 17, make sure that the JAVA_HOME environment variable is properly configured

## Before you run the integration tests (set up)

These are the prerequisites that should be completed once before running the integration tests.

### Oracle Container (Docker)

This method involves starting a preconfigured database instance as a Docker container. From the project directory, run
the following command:

```shell
docker-compose up -d oracle
```

⏳ The Docker image is downloaded during the first build, so it may take a little longer than usual.

If you choose this approach, both the database instance and the required `adminuser` user are created automatically when
the Oracle container starts.

### Local Oracle Instance

If you have your own local Oracle setup, you should create an `adminuser`. To do that, execute the script
[./database/src/main/resources/init/users.sql](./database/src/main/resources/init/users.sql) using a user with the DBA
role. This user is utilized by the integration tests to set up all the necessary database objects on the fly. This step
is automatic when running the *Oracle Container*.

## Run the integration tests

Once you have the Oracle instance configured, you can run the integration tests:

```shell
./gradlew build intest
```

## Sources

[Przemek Malirz's ***VShop*** application on GitHub](https://github.com/pmalirz/vshop)

[Oracle Container Registry - ***Docker Image Documentation***](https://container-registry.oracle.com/ords/f?p=113:4:7132997386216:::4:P4_REPOSITORY,AI_REPOSITORY,AI_REPOSITORY_NAME,P4_REPOSITORY_NAME,P4_EULA_ID,P4_BUSINESS_AREA_ID:803,803,Oracle%20Database%20Express%20Edition,Oracle%20Database%20Express%20Edition,1,0&cs=3O9aDhk0TG62vtwnn1iPt8FwP9tFUTiRoYQQgB2DZFKLEoDK408DPdLm-YsbNEMVvjsmYi9_GzDvKbj2ZJKccmg)

[Getting Started with Liquibase and Gradle](https://contribute.liquibase.com/extensions-integrations/directory/integration-docs/gradle/)

[Gradle - processResources with filesMatching](https://docs.gradle.org/current/dsl/org.gradle.language.jvm.tasks.ProcessResources.html)

[Instead of setting up the gradle `intest` configuration manually you can use Przemek Malirz's ***intest-gradle-plugin***](https://github.com/pmalirz/intest-gradle-plugin)

[Gradle - Working With Files](https://docs.gradle.org/current/userguide/working_with_files.html)

[Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/)