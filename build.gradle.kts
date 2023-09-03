import org.apache.commons.lang3.RandomStringUtils
import org.apache.tools.ant.Project
import org.apache.tools.ant.taskdefs.SQLExec

plugins {
    java
    id("idea")
    id("groovy")
    id("org.springframework.boot") version "3.1.2"
    id("io.spring.dependency-management") version "1.1.2"
}

group = "dev.vavelin.example"
version = "0.0.1-SNAPSHOT"

val dbRegion = project.findProperty("db.dbRegion.active") ?: ""
val dbData = mutableMapOf(
        "dbUrl" to (project.findProperty("db.$dbRegion.dbUrl") ?: "jdbc:oracle:thin:@//localhost:1521/XEPDB1"),
        "dbUser" to (project.findProperty("db.$dbRegion.dbUser") ?: "demouser"),
        "dbPass" to (project.findProperty("db.$dbRegion.dbPass") ?: "demopass"),
        "dbAdmUser" to (project.findProperty("db.$dbRegion.dbAdmUser") ?: "adminuser"),
        "dbAdmPass" to (project.findProperty("db.$dbRegion.dbAdmPass") ?: "adminpass"),
        "dbGenRandomUserSuffix" to (project.findProperty("db.$dbRegion.dbGenRandomUserSuffix")?.toString()?.toBoolean() ?: false)
)
if (dbData["dbGenRandomUserSuffix"] as Boolean) {
    dbData["dbUser"] = "${dbData["dbUser"]}${generateRandomSuffix()}"
}
dbData.keys.forEach{
    extra[it] = dbData[it]
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
    }
    dependencies {
        classpath("org.apache.commons:commons-lang3:3.13.0")
    }
}

repositories {
    mavenLocal()
    mavenCentral()
}

configurations {
    create("intestImplementation") {
        extendsFrom(configurations["testImplementation"])
        isCanBeResolved = true
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.oracle.database.jdbc:ojdbc11:23.2.0.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.spockframework:spock-core:2.4-M1-groovy-4.0")
    testImplementation("org.spockframework:spock-spring:2.4-M1-groovy-4.0")
}

sourceSets {
    create("intest") {
        groovy {
            setSrcDirs(listOf("$projectDir/src/intest/groovy"))
        }
        resources {
            setSrcDirs(listOf("$projectDir/src/intest/resources"))
        }
        compileClasspath += sourceSets["main"].output + sourceSets["test"].output + configurations["intestImplementation"]
        runtimeClasspath += sourceSets["main"].output + sourceSets["test"].output + configurations["intestImplementation"]
    }
}
val intest by tasks.creating(Test::class) {
    group = "verification"
    description = "Runs integration tests."

    testClassesDirs = sourceSets["intest"].output.classesDirs
    classpath = sourceSets["intest"].runtimeClasspath

    dependsOn("initDB")
    finalizedBy("dropDB")

    jvmArgs = listOf(
            "-Dspring.datasource.url=${dbData["dbUrl"]}",
            "-Dspring.datasource.username=${dbData["dbUser"]}",
            "-Dspring.datasource.password=${dbData["dbPass"]}",
            "-Dspring.jpa.properties.hibernate.default_schema=${dbData["dbUser"]}"
    )
}

tasks.register("initDB") {
    description = "Initialize database structures used in Integration Tests"

    val liquibaseClean = project(":database").tasks.getByName("clean")
    dependsOn(liquibaseClean)
    doFirst {
        logger.lifecycle("Initializing database structures")
        runScript(
                "${project.projectDir}/src/intest/resources/db/setup-sql/init-db.sql",
                "@database_user@" to (dbData["dbUser"] as String)
                )
    }

    val liquibaseUpdate = project(":database").tasks.getByName("update")
    finalizedBy(liquibaseUpdate)
}

tasks.register("dropDB") {
    description = "Drop all database structures used in Integration Tests"

    doFirst {
        logger.lifecycle("Dropping database structures")
        runScript(
                "${project.projectDir}/src/intest/resources/db/setup-sql/drop-db.sql",
                "@database_user@" to (dbData["dbUser"] as String)
        )
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

fun runScript(scriptPath: String, replacementPair: Pair<String, String>) {
    val sqlExec = SQLExec()
    sqlExec.project = Project()
    sqlExec.project.init()

    val delimiterType = SQLExec.DelimiterType()
    delimiterType.value = SQLExec.DelimiterType.ROW
    sqlExec.setDelimiterType(delimiterType)
    sqlExec.setDelimiter("/")

    sqlExec.setDriver("oracle.jdbc.OracleDriver")
    sqlExec.createClasspath().setPath(project.sourceSets["main"].runtimeClasspath.asPath)
    sqlExec.setUrl(dbData["dbUrl"] as String)
    sqlExec.setUserid(dbData["dbAdmUser"] as String)
    sqlExec.setPassword(dbData["dbAdmPass"] as String)
    sqlExec.setPrint(true)

    val scriptFile = file(scriptPath)
    var script = scriptFile.readText()
    script = script.replace(replacementPair.first, replacementPair.second)
    sqlExec.addText(script)
    sqlExec.execute()
}

fun generateRandomSuffix(): String {
    val allowedChars = "0123456789abcdefghijklmnopqrstuvwxyz"
    val suffixLength = 5
    return RandomStringUtils.random(suffixLength, allowedChars)
}