package com.er.legbon.features.users

import com.er.legbon.auth.TokenManager
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.mindrot.jbcrypt.BCrypt

fun Application.userRoutes() {

    val userDao = UserDao()
    val tokenManager = TokenManager(environment.config)


    routing {
        route("/users") {
            post("/register") {
                val registerRequest = call.receive<RegisterRequest>()
                val hashedPassword = BCrypt.hashpw(registerRequest.password, BCrypt.gensalt())
                val user = userDao.createUser(registerRequest.username, hashedPassword)
                if (user != null) {
                    call.respond(user)
                } else {
                    call.respond("Error creating user")
                }
            }

            post("/login") {
                val loginRequest = call.receive<LoginRequest>()
                val user = userDao.findUserByUsername(loginRequest.username)
                if (user != null && BCrypt.checkpw(loginRequest.password, user.password)) {
                    val token = tokenManager.generateToken(user.username)
                    call.respond(token)
                } else {
                    call.respond("Invalid credentials")
                }
            }
        }
    }
}