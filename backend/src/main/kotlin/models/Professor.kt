package models

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID


object Professor : IntIdTable("professores") {
    val nome = varchar("nome", 50)
}

class ProfessorEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ProfessorEntity>(Professor)

    var nome by Professor.nome
}