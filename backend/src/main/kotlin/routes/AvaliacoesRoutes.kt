package routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.transactions.transaction
import models.*

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
            val newAvaliacao = transaction {
                AvaliacaoEntity.new {
                    disciplinaId = avaliacao.disciplinaId
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
        }
    }
}
