package routes
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.transactions.transaction
import models.*

fun Route.professoresRoutes() {
    route("/professores") {
        get {
            val professor = transaction {
                ProfessorEntity.all().map {
                    it.toProfessor()
                }
            }
            call.respond(professor)
        }
    }
}