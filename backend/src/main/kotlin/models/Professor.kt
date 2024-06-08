package models

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID


object Professores : IntIdTable("professores") {
    val nome = varchar("nome", 50)
}

class ProfessorEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ProfessorEntity>(Professores)

    var nome by Professores.nome
}

data class Professor(
    val id: Int,
    val nome: String
)

fun ProfessorEntity.toProfessor() = Professor(
    id.value,
    nome
)