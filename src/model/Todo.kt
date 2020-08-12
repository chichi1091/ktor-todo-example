package com.todo.exmaple.model

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Todos: Table() {
    val id: Column<Int> = integer("id").autoIncrement().primaryKey()
    val task: Column<String> = varchar("task", 4000)
}

data class Todo (
    val id: Int,
    val task: String
)