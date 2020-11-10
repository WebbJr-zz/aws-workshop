package com.mercell.workshop.controller

import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
class RootController {

    @GetMapping("/")
    fun root() = "Hello workshop! Current time is ${LocalDateTime.now()}"
}