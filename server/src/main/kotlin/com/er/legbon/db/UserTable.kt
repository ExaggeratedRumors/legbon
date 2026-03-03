package com.er.legbon.db

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.datetime.CurrentDateTime
import org.jetbrains.exposed.v1.datetime.datetime

object UserTable: Table("users") {
    const val MAX_VARCHAR_LENGTH = 50
    val id = integer("id").autoIncrement()
    val discord_id = integer("discord_id").uniqueIndex()
    val nick = varchar("name", MAX_VARCHAR_LENGTH)
    val verifiedAt = datetime("verified_at").defaultExpression(CurrentDateTime)
    val admin = bool("admin").default(false)
    val active = bool("active").default(true)

    override val primaryKey = PrimaryKey(id)
}