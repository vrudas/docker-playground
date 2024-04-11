import com.google.cloud.tools.jib.api.buildplan.ImageFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"

    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"

    id("com.google.cloud.tools.jib") version "3.4.2"
}

group = "org.geekhub"
version = "0.0.1-SNAPSHOT"

jib {
    from {
        image = "eclipse-temurin:21-jdk-alpine"
    }
    to {
        image = "docker.io/vrudas/jib-application"
        tags = setOf("jib-latest")
        auth {
            username = providers.gradleProperty("docker.username")
                .orElse(providers.environmentVariable("DOCKER_USERNAME"))
                .get()

            password = providers.gradleProperty("docker.password")
                .orElse(providers.environmentVariable("DOCKER_PASSWORD"))
                .get()
        }
    }
    container {
        mainClass = "dev.vrudas.jib.application.JibApplicationKt"
        jvmFlags = listOf("-Xms256m", "-Xmx512m")
        args = listOf("arg1", "arg2")
        environment = mapOf(
            "SPRING_DOCKER_COMPOSE_ENABLED" to "false",
            "SPRING_MAIN_LAZY_INITIALIZATION" to "false",
            "SPRING_PROFILES_ACTIVE" to "prod"
        )
        ports = listOf("8080", "8090")
        format = ImageFormat.OCI
        appRoot = "/app"
    }
    dockerClient {
        executable = "/usr/local/bin/docker"
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.testcontainers:postgresql")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
