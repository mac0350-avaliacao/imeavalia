package routes

import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import models.*
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.dao.id.EntityID

fun Route.avaliacoesRoutes() {
    route("/avaliacoes") {
        get {
            val avaliacoes = transaction {
                Avaliacoes.selectAll().map {
                    it.toAvaliacao()
                }
            }
            call.respond(avaliacoes)
        }

        post {
            val avaliacao = call.receive<Avaliacao>()

            val oferecimentos = transaction {
                Oferecimentos.selectAll().map {
                    it.toOferecimento()
                }
            }

            val oferecimento = oferecimentos.find {
                it.disciplinaId == avaliacao.disciplinaId &&
                it.anoSemestre == avaliacao.anoSemestre &&
                it.professorId == avaliacao.professorId
            }

            if (oferecimento != null) {
                var newAvaliacaoId: EntityID<Int>? = null
                transaction {
                    val id = Avaliacoes.insertAndGetId {
                        it[oferecimentoId] = oferecimento.id
                        it[disciplinaId] = avaliacao.disciplinaId
                        it[professorId] = avaliacao.professorId
                        it[anoSemestre] = avaliacao.anoSemestre
                        it[oferecimentoId] = avaliacao.oferecimentoId
                        it[materialDidatico] = avaliacao.materialDidatico
                        it[didaticaProfessor] = avaliacao.didaticaProfessor
                        it[metodoAvaliativo] = avaliacao.metodoAvaliativo
                        it[monitoria] = avaliacao.monitoria
                        it[comentariosGerais] = avaliacao.comentariosGerais
                        it[comentariosAvaliacao] = avaliacao.comentariosAvaliacao
                        it[presencaAtividades] = avaliacao.presencaAtividades
                        it[horasSemanais] = avaliacao.horasSemanais
                    }
                    newAvaliacaoId = id
                }
                call.respond(mapOf("action" to "ADD_TO_PRIOR_OFFERING", "newAvaliacaoId" to newAvaliacaoId?.value))

            } else {
                var newOferecimentoId: Int = 0
                var newAvaliacaoId: Int = 0

                transaction {
                    val id = Oferecimentos.insertAndGetId {
                        it[disciplinaId] = avaliacao.disciplinaId
                        it[professorId] = avaliacao.professorId
                        it[anoSemestre] = avaliacao.anoSemestre
                    }
                    newOferecimentoId = id.value
                }

                transaction {
                    val id = Avaliacoes.insertAndGetId {
                        it[disciplinaId] = avaliacao.disciplinaId
                        it[professorId] = avaliacao.professorId
                        it[anoSemestre] = avaliacao.anoSemestre
                        it[oferecimentoId] = newOferecimentoId
                        it[materialDidatico] = avaliacao.materialDidatico
                        it[didaticaProfessor] = avaliacao.didaticaProfessor
                        it[metodoAvaliativo] = avaliacao.metodoAvaliativo
                        it[monitoria] = avaliacao.monitoria
                        it[comentariosGerais] = avaliacao.comentariosGerais
                        it[comentariosAvaliacao] = avaliacao.comentariosAvaliacao
                        it[presencaAtividades] = avaliacao.presencaAtividades
                        it[horasSemanais] = avaliacao.horasSemanais
                    }
                    newAvaliacaoId = id.value
                }
                call.respond(mapOf(
                    "action" to "ADD_TO_NEW_OFFERING",
                    "newOferecimentoId" to newOferecimentoId,
                    "newAvaliacaoId" to newAvaliacaoId
                ))
            }
        }
    }
}
