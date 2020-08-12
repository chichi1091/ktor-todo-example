package com.todo.exmaple.service

import com.todo.exmaple.factory.DatabaseFactory.dbQuery
import com.todo.exmaple.model.Todo
import com.todo.exmaple.model.Todos
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class TodoService {
    suspend fun getAllTodos(): List<Todo> = dbQuery {
        Todos.selectAll().map { toTodo(it) }
    }

    suspend fun getTodo(id: Int): Todo? = dbQuery {
        Todos.select {
            (Todos.id eq id)
        }.mapNotNull { toTodo(it) }
            .singleOrNull()
    }

    private fun toTodo(row: ResultRow): Todo =
        Todo(
            id = row[Todos.id],
            task = row[Todos.task]
        )
}