plugins {
    id("java")
    id("org.liquibase.gradle") version "2.2.0"
    id("application")
}

group = "dev.vavelin.example"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    liquibaseRuntime("org.liquibase:liquibase-core:4.23.0")
    liquibaseRuntime("com.oracle.database.jdbc:ojdbc11:23.2.0.0")
    liquibaseRuntime("info.picocli:picocli:4.7.4")
}

liquibase {
    activities.register("defaultRunList") {
        this.arguments = mapOf(
                "classpath" to "${buildDir}/resources/main",
                "changelogFile" to "changelog.yml",
                "url" to "jdbc:oracle:thin:@//localhost:1521/XEPDB1",
                "username" to "DEMOUSER",
                "password" to "demopass",
                "liquibaseSchemaName" to "DEMOUSER",
                "defaultSchemaName" to "DEMOUSER",
                "databaseChangelogTableName" to "DEMO_CHANGE_LOG",
                "databaseChangelogLockTableName" to "DEMO_CHANGE_LOCK"
        )
    }

    runList = "defaultRunList"
}