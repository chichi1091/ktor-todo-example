package com.todo.exmaple.web

import com.todo.exmaple.model.NewTodo
import com.todo.exmaple.service.TodoService
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*

fun Route.todos(todoService: TodoService) {
    route("todos") {
        get("/") {
            call.respond(todoService.getAllTodos())
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toInt()
                ?: throw IllegalStateException("Must To id")
            val widget = todoService.getTodo(id)
            if (widget == null) call.respond(HttpStatusCode.NotFound)
            else call.respond(widget)
        }

        post("/") {
            val newTodo = call.receive<NewTodo>()
            call.respond(HttpStatusCode.Created, todoService.addTodo(newTodo))
        }

        put("/{id}") {
            val todo = call.receive<NewTodo>()
            val updated = todoService.updateTodo(todo)
            if (updated == null) call.respond(HttpStatusCode.NotFound)
            else call.respond(HttpStatusCode.OK, updated)
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toInt()
                ?: throw IllegalStateException("Must To id");
            val removed = todoService.deleteTodo(id)
            if (removed) call.respond(HttpStatusCode.OK)
            else call.respond(HttpStatusCode.NotFound)
        }
    }
}