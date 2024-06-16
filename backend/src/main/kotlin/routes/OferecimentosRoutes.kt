package routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import models.*

fun Route.oferecimentosRoutes() {
    route("/oferecimentos") {
        get {
            val oferecimentos = transaction {
                Oferecimentos.selectAll().map {
                    it.toOferecimento()
                }
            }
            call.respond(oferecimentos)
        }
    }
}