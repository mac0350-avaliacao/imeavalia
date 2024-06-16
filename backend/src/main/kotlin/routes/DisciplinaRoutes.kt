package routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import models.*

fun Route.disciplinasRoutes() {
    route("/disciplinas") {
        get {
            val disciplinas = transaction {
                Disciplinas.selectAll().map {
                    it.toDisciplina()
                }
            }
            call.respond(disciplinas)
        }
    }
}
