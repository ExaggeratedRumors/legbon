package com.er.legbon.db

import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.slf4j.LoggerFactory
import kotlin.test.Test
import kotlin.test.assertNotNull

class DatabaseConnectionTest {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Test
    fun `test connection to postgresql`() {
        try {
            DatabaseBuilder.build()

            transaction {
                val url = connection.metadata { this.url }
                assertNotNull(url)
                logger.info("Successfully connected to: {}", url)

                exec("SELECT 1") { rs ->
                    if (rs.next()) {
                        logger.info("Database query 'SELECT 1' successful.")
                    }
                }
            }
        } catch (e: Exception) {
            logger.error("Failed to connect to the database: {}", e.message)
            throw e
        }
    }
}