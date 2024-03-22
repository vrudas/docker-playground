package dev.vrudas.docker.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DockerPlaygroundApplication

fun main(args: Array<String>) {
    runApplication<DockerPlaygroundApplication>(*args)
}
