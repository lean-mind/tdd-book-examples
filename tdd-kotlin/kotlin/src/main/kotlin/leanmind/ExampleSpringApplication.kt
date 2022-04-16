package leanmind

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExampleSpringApplication

fun main(args: Array<String>) {
    runApplication<ExampleSpringApplication>(*args)
}
