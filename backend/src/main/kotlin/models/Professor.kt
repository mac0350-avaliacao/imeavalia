package models

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow

object Professores : IntIdTable("professores") {
    val nome = varchar("nome", 200)
}

data class Professor(
    val id: Int,
    val nome: String
)

fun ResultRow.toProfessor() = Professor(
    id = this[Professores.id].value,
    nome = this[Professores.nome],
)