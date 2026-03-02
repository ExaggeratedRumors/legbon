package com.er.legbon.db

import org.jetbrains.exposed.v1.core.Table
import org.jetbrains.exposed.v1.datetime.datetime

object UserTable: Table("users") {
    val id = integer("id").autoIncrement()
    val nick = varchar("nick", 50)
    val verifiedAt = datetime("verified_at").nullable()

    override val primaryKey = PrimaryKey(id)
}