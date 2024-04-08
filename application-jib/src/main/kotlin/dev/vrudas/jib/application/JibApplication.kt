package dev.vrudas.jib.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JibApplication

fun main(args: Array<String>) {
    runApplication<JibApplication>(*args)
}
