plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "docker-playground"

include("application-dockerfile")
include("application-jib")
include("database-backup")
