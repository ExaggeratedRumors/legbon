package com.er.legbon

import com.er.legbon.db.DatabaseBuilder
import com.er.legbon.plugins.configureLogging
import com.er.legbon.plugins.configureRouting
import com.er.legbon.plugins.configureSecurity
import com.er.legbon.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {
    DatabaseBuilder.build()
    configureSerialization()
    configureLogging()
    configureSecurity()
    configureRouting()
}