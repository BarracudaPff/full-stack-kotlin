package ru.otus.kotlin

import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.html.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.html.*
import ru.otus.kotlin.services.CalcService

fun HTML.index() {
    head {
        title("Hello from Ktor!")
    }
    body {
        div {
            +"Hello from Ktor"
        }
        div {
            id = "root"
        }
        script(src = "/static/full-stack-kotlin.js") {}
    }
}

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        install(ContentNegotiation) {
            jackson {}
        }

        val calcService = CalcService()

        routing {
            get("/") {
                call.respondHtml(HttpStatusCode.OK, HTML::index)
            }
            route("/api/calc") {
                post {
                    val req = call.receive<CalculatorData>()
                    calcService.addCalc(req)
                    call.respondText("OK")
                }
                get {
                    val samples = calcService.getAll()
                    call.respond(HttpStatusCode.OK, samples)
                }
            }
            static("/static") {
                resources()
            }
        }
    }.start(wait = true)
}
