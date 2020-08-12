package com.todo.exmaple

import com.fasterxml.jackson.databind.SerializationFeature
import com.todo.exmaple.factory.DatabaseFactory
import com.todo.exmaple.model.Todos
import com.todo.exmaple.service.TodoService
import com.todo.exmaple.web.todos
import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.jackson.jackson
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.routing

fun main(args: Array<String>): Unit = io.ktor.server.tomcat.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    install(ContentNegotiation) {
        jackson {
            configure(SerializationFeature.INDENT_OUTPUT, true)
        }
    }

    DatabaseFactory.init()

    val todoService = TodoService()
    install(Routing) {
        todos(todoService)
    }
}

