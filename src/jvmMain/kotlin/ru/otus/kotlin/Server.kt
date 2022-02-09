package ru.otus.kotlin

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.respondHtml
import io.ktor.http.HttpStatusCode
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.http.content.resources
import io.ktor.http.content.static
import io.ktor.jackson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*
import org.jetbrains.exposed.sql.SchemaUtils
import ru.otus.kotlin.database.CalcSamples
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
        val calcService = CalcService()

        install(ContentNegotiation) {
            jackson {}
        }

        database {
            SchemaUtils.create(CalcSamples)
        }

        routing {
            get("/") {
                call.respondHtml(HttpStatusCode.OK, HTML::index)
            }
            route("/api/calc") {
                get {
                    val samples = calcService.getAllCalcs()
                    call.respond(HttpStatusCode.OK, samples)
                }
                post {
                    val req = call.receive<CalculatorSample>()
                    calcService.addCalc(req)
                    call.respondText("OK")
                }
            }
            static("/static") {
                resources()
            }
        }
    }.start(wait = true)
}
