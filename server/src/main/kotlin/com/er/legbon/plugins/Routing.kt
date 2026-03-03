package com.er.legbon.plugins

import com.er.legbon.Greeting
import com.er.legbon.repository.UserService
import com.er.legbon.routes.userRoutes
import io.ktor.server.application.Application
import io.ktor.server.routing.*
import io.ktor.server.response.*

fun Application.configureRouting() {
    val userService = UserService()

    routing {
        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }
        
        userRoutes(userService)
    }
}