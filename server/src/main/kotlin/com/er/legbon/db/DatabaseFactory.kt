package com.er.legbon.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.v1.jdbc.Database

object DatabaseFactory {
    fun init() {
        val config = HikariConfig().apply {
            val dbUser = System.getenv("DB_USER")
            val dbPassword = System.getenv("DB_PASSWORD")
            val dbName = System.getenv("DB_NAME")
            val dbPort = System.getenv("DB_PORT")

            jdbcUrl = "jdbc:postgresql://localhost:$dbPort/$dbName"
            username = dbUser
            password = dbPassword
            driverClassName = "org.postgresql.Driver"

            maximumPoolSize = 3
        }
        val dataSource = HikariDataSource(config)
        Database.connect(dataSource)
    }
}
