package routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.transactions.transaction
import models.*
import org.jetbrains.exposed.sql.Op

fun Route.avaliacoesRoutes() {
    route("/avaliacoes") {
        get {
            val avaliacoes = transaction {
                AvaliacaoEntity.all().map {
                    it.toAvaliacao()
                }
            }
            call.respond(avaliacoes)
        }

        post {
            val avaliacao = call.receive<Avaliacao>()

            val oferecimentos = transaction {
                OferecimentoEntity.all().map {
                    it.toOferecimento()
                }
            }

            val oferecimento = oferecimentos.find {
                it.disciplinaId == avaliacao.disciplinaId &&
                it.anoSemestre == avaliacao.anoSemestre &&
                it.professorId == avaliacao.professorId
            }

            /*
            if (oferecimento != null) {
                call.respond(oferecimento)
            } else {
                call.respond(oferecimentos)
            }
             */

            if (oferecimento != null) {
                val newAvaliacao = transaction {
                    AvaliacaoEntity.new {
                        oferecimentoId = oferecimento.id
                        disciplinaId = avaliacao.disciplinaId
                        professorId = avaliacao.professorId
                        anoSemestre = avaliacao.anoSemestre
                        oferecimentoId = avaliacao.oferecimentoId
                        materialDidatico = avaliacao.materialDidatico
                        didaticaProfessor = avaliacao.didaticaProfessor
                        metodoAvaliativo = avaliacao.metodoAvaliativo
                        monitoria = avaliacao.monitoria
                        comentariosGerais = avaliacao.comentariosGerais
                        comentariosAvaliacao = avaliacao.comentariosAvaliacao
                        presencaAtividades = avaliacao.presencaAtividades
                        horasSemanais = avaliacao.horasSemanais
                    }
                }
                call.respond(mapOf("action" to "ADD_TO_PRIOR_OFFERING",  "message" to newAvaliacao.toAvaliacao()))
            } else {
                val newOferecimento = transaction {
                    OferecimentoEntity.new {
                        disciplinaId = avaliacao.disciplinaId
                        professorId = avaliacao.professorId
                        anoSemestre = avaliacao.anoSemestre
                    }
                }
                val newAvaliacao = transaction {
                    AvaliacaoEntity.new {
                        disciplinaId = avaliacao.disciplinaId
                        professorId = avaliacao.professorId
                        anoSemestre = avaliacao.anoSemestre
                        oferecimentoId = newOferecimento.toOferecimento().id
                        materialDidatico = avaliacao.materialDidatico
                        didaticaProfessor = avaliacao.didaticaProfessor
                        metodoAvaliativo = avaliacao.metodoAvaliativo
                        monitoria = avaliacao.monitoria
                        comentariosGerais = avaliacao.comentariosGerais
                        comentariosAvaliacao = avaliacao.comentariosAvaliacao
                        presencaAtividades = avaliacao.presencaAtividades
                        horasSemanais = avaliacao.horasSemanais
                    }
                }
                call.respond(mapOf("action" to "ADD_TO_NEW_OFFERING", "message" to newAvaliacao.toAvaliacao()))
            }
        }
    }
}
