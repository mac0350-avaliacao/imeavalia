package br.com.imeavalia

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.serialization.gson.gson
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import models.*
import routes.avaliacoesRoutes
import routes.disciplinasRoutes
import routes.testesRoutes

fun main() {
    embeddedServer(Netty, port = 8081, module = Application::module).start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    install(CORS) {
        anyHost()
        allowHeader(HttpHeaders.ContentType)
    }

    Database.connect(
        "jdbc:postgresql://localhost:5432/postgres",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "123456"
    )

    routing {
        disciplinasRoutes()
        avaliacoesRoutes()
        testesRoutes()
    }
}
