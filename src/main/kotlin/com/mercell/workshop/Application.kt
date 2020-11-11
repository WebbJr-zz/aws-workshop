package com.mercell.workshop

import com.mercell.workshop.facts.WorkshopProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
@ConfigurationPropertiesScan(basePackages = ["com.mercell"])
class Application(val workshopProperties: WorkshopProperties) {

    @GetMapping("/")
    fun root() = "Hello workshop! Here's a fact: ${workshopProperties.fact}"
}

fun main() {
    runApplication<Application>()
}