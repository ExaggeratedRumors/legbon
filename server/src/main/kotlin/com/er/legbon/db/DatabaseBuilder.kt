package com.er.legbon.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

object DatabaseBuilder {
    fun build() {
        val config = HikariConfig().apply {
            val dbUser: String = System.getenv("DB_USER")
            val dbPassword: String = System.getenv("DB_PASSWORD")
            val dbName: String = System.getenv("DB_NAME")
            val dbPort: String = System.getenv("DB_PORT")

            jdbcUrl = "jdbc:postgresql://localhost:$dbPort/$dbName"
            username = dbUser
            password = dbPassword
            driverClassName = "org.postgresql.Driver"

            maximumPoolSize = 3
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }
        val dataSource = HikariDataSource(config)
        Database.connect(dataSource)

        transaction {
            SchemaUtils.create(UserTable)
        }
    }
}
