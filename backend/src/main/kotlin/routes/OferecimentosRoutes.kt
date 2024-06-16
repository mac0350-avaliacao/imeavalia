package routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import models.*
import kotlinx.coroutines.withContext

fun Route.oferecimentosRoutes() {
    route("/oferecimentos") {
        get {
            val results = withContext(Dispatchers.IO) {
                transaction {
                    val query = (Oferecimentos innerJoin Disciplinas innerJoin Professores)
                        .select {
                            Oferecimentos.disciplinaId eq Disciplinas.id and
                                    (Oferecimentos.professorId eq Professores.id)
                        }

                    query.map {
                        OferecimentoDetalhes(
                            id = it[Oferecimentos.id].value,
                            disciplina_sigla = it[Disciplinas.sigla],
                            disciplina_nome = it[Disciplinas.nome],
                            disciplina_id = it[Disciplinas.id].value,
                            professor_nome = it[Professores.nome],
                            professor_id = it[Professores.id].value
                        )
                    }
                }
            }
            call.respond(results)
        }
    }
}