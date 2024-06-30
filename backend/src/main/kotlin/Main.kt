package br.com.imeavalia

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.serialization.gson.gson
import io.ktor.server.routing.*
import io.github.cdimascio.dotenv.dotenv

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import models.*
import routes.*
import config.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SchemaUtils
import java.io.File
import java.io.FileNotFoundException
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.server.response.*


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

    configureRouting()
}

fun Application.configureRouting() {
    val dotenv = dotenv()
    Database.connect(url = dotenv["DB_URL"], driver = "org.postgresql.Driver", user = dotenv["DB_USER"], password = dotenv["DB_PASSWORD"])

    initDB()

    routing {
        disciplinasRoutes()
        professoresRoutes()
        avaliacoesRoutes()
        oferecimentosRoutes()
        route("/") {
            get {
                call.respond("Hello world")
            }
        }
    }
}
