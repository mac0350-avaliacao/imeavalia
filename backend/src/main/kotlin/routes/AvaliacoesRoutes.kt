package routes

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

            val oferecimento = oferecimentos.find { it.disciplinaId == avaliacao.disciplinaId && it.anoSemestre == avaliacao.anoSemestre }

            if (oferecimento != null) {
                val newAvaliacao = transaction {
                    AvaliacaoEntity.new {
                        disciplinaId = avaliacao.disciplinaId
                        anoSemestre = avaliacao.anoSemestre
                        oferecimentoId = avaliacao.oferecimentoId
                        materialDidatico = avaliacao.materialDidatico
                        didaticaProfessor = avaliacao.didaticaProfessor
                        metodoAvaliativo = avaliacao.metodoAvaliativo
                        monitoria = avaliacao.monitoria
                        comentariosGerais = avaliacao.comentariosGerais
                        presencaAtividades = avaliacao.presencaAtividades
                        horasSemanais = avaliacao.horasSemanais
                    }
                }
                call.respond(newAvaliacao.toAvaliacao())
            } else {
                val newOferecimento = transaction {
                    OferecimentoEntity.new {
                        disciplinaId
                        professorId
                        anoSemestre
                    }
                }
                call.respond(newOferecimento.toOferecimento())
            }
        }
    }
}
