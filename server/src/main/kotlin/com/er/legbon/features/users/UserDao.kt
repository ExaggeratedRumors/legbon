package com.er.legbon.features.users

import com.er.legbon.db.DatabaseFactory.dbQuery
import com.er.legbon.db.UserTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class UserDao {

    suspend fun createUser(username: String, password: String): User? = dbQuery {
        val insertStatement = UserTable.insert {
            it[UserTable.username] = username
            it[UserTable.password] = password
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::toUser)
    }

    suspend fun findUserByUsername(username: String): User? = dbQuery {
        UserTable.select { UserTable.username eq username }
            .map(::toUser)
            .singleOrNull()
    }

    private fun toUser(row: ResultRow): User = User(
        id = row[UserTable.id],
        username = row[UserTable.username],
        password = row[UserTable.password]
    )
}
