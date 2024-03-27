package dev.vrudas.docker.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DockerApplication

fun main(args: Array<String>) {
    runApplication<DockerApplication>(*args)
}
