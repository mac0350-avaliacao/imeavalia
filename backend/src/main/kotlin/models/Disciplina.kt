package models

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID


object Disciplinas : IntIdTable("disciplinas") {
    val name = varchar("name", 100)
    val sigla = varchar("sigla", 10).uniqueIndex()
}

class DisciplinaEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<DisciplinaEntity>(Disciplinas)

    var name by Disciplinas.name
    var sigla by Disciplinas.sigla
}

data class Disciplina(
    val id: Int,
    val name: String,
    val sigla: String
)

fun DisciplinaEntity.toDisciplina() = Disciplina(
    id.value,
    name,
    sigla
)