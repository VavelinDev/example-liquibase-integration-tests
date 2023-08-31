plugins {
    java
    id("idea")
    id("groovy")
    id("org.springframework.boot") version "3.1.2"
    id("io.spring.dependency-management") version "1.1.2"
}

group = "dev.vavelin.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
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
    implementation("com.oracle.database.jdbc:ojdbc11:23.2.0.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-jdbc")
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
}


tasks.withType<Test> {
    useJUnitPlatform()
}
