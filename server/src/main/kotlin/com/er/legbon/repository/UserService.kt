package com.er.legbon.repository

import com.er.legbon.db.UserTable
import com.er.legbon.model.UserDto
import org.jetbrains.exposed.v1.core.ResultRow
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.deleteWhere
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.suspendTransaction
import org.jetbrains.exposed.v1.jdbc.update

class UserService {
    suspend fun createUser(user: UserDto): Int = dbQuery {
        UserTable.insert {
            it[discord_id] = user.discordId
            it[nick] = user.nick
            it[admin] = user.admin
            it[active] = user.active
        }[UserTable.id]
    }

    suspend fun readUser(id: Int): UserDto? = dbQuery {
        UserTable.selectAll().where { UserTable.id eq id }
            .map { mapToUserDto(it) }
            .singleOrNull()
    }

    suspend fun updateUser(id: Int, user: UserDto): Boolean = dbQuery {
        UserTable.update({ UserTable.id eq id }) {
            it[discord_id] = user.discordId
            it[nick] = user.nick
            it[admin] = user.admin
            it[active] = user.active
        } > 0
    }

    suspend fun deleteUser(id: Int): Boolean = dbQuery {
        UserTable.deleteWhere { UserTable.id eq id } > 0
    }

    private fun mapToUserDto(it: ResultRow) = UserDto(
        id = it[UserTable.id],
        discordId = it[UserTable.discord_id],
        nick = it[UserTable.nick],
        admin = it[UserTable.admin],
        active = it[UserTable.active]
    )
}

suspend fun <T> dbQuery(block: suspend () -> T): T =
    suspendTransaction { block() }