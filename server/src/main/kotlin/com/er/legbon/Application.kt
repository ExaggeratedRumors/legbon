package com.er.legbon

import com.er.legbon.db.DatabaseFactory
import com.er.legbon.features.users.userRoutes
import com.er.legbon.plugins.configureLogging
import com.er.legbon.plugins.configureSecurity
import com.er.legbon.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    DatabaseFactory.init()
    configureSerialization()
    configureLogging()
    configureSecurity()
    userRoutes()
    routing {
        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }
    }
}