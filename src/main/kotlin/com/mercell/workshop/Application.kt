package com.mercell.workshop

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@SpringBootApplication
@RestController
class Application {

    @GetMapping("/")
    fun root() = "Hello workshop! Current time is ${LocalDateTime.now()}"
}

fun main() {
    runApplication<Application>()
}