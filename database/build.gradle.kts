import org.apache.tools.ant.filters.ReplaceTokens

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

val dbUrl = project.rootProject.extra["dbUrl"]
val dbAppUser = project.rootProject.extra["dbAppUser"]
val dbAdmUser = project.rootProject.extra["dbAdmUser"]
val dbAdmPass = project.rootProject.extra["dbAdmPass"]


tasks.processResources {
    filesMatching("**/changes/*.sql") {
        filter<ReplaceTokens>("tokens" to mapOf("dbAppUser" to dbAppUser))
    }
}

tasks.withType<org.liquibase.gradle.LiquibaseTask> {
    dependsOn(tasks.processResources)
}

liquibase {
    activities.register("defaultRunList") {
        this.arguments = mapOf(
                "classpath" to "${buildDir}/resources/main",
                "changelogFile" to "changelog.yml",
                "url" to dbUrl,
                "username" to dbAdmUser,
                "password" to dbAdmPass,
                "liquibaseSchemaName" to dbAppUser,
                "defaultSchemaName" to dbAppUser,
                "databaseChangelogTableName" to "DEMO_CHANGE_LOG",
                "databaseChangelogLockTableName" to "DEMO_CHANGE_LOCK"
        )
    }

    runList = "defaultRunList"
}