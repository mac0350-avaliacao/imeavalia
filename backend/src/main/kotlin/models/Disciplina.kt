package models

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID


object Disciplina : IntIdTable("disciplinas") {
    val name = varchar("name", 100)
    val sigla = varchar("sigla", 10).uniqueIndex()
}

class DisciplinaEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<DisciplinaEntity>(Disciplina)

    var name by Disciplina.name
    var sigla by Disciplina.sigla
}