package models

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

object Disciplinas : IntIdTable("disciplinas") {
    val nome = varchar("nome", 100)
    val sigla = varchar("sigla", 10).uniqueIndex()
}

data class Disciplina(
    val id: Int,
    val nome: String,
    val sigla: String
)

fun ResultRow.toDisciplina() = Disciplina(
    id = this[Disciplinas.id].value,
    nome = this[Disciplinas.nome],
    sigla = this[Disciplinas.sigla]
)