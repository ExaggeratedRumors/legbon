package com.er.legbon

import com.er.legbon.db.DatabaseFactory
import com.er.legbon.plugins.configureLogging
import com.er.legbon.plugins.configureSecurity
import com.er.legbon.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {
    DatabaseFactory.init()
    configureSerialization()
    configureLogging()
    configureSecurity()
    //userRoutes()
    routing {
        get("/") {
            call.respondText("Ktor: ${Greeting().greet()}")
        }
    }
}