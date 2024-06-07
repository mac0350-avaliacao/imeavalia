package models

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID


object Oferecimentos : IntIdTable("oferecimentos") {
    val disciplinaId = integer("disciplina_id")
    val professorId = integer("professor_id")
    val anoSemestre = varchar("ano_semestre", 5)
}

class OferecimentoEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<OferecimentoEntity>(Oferecimentos)

    var disciplinaId by Oferecimentos.disciplinaId
    var professorId by Oferecimentos.professorId
    var anoSemestre by Oferecimentos.anoSemestre
}

data class Oferecimento(
    val id: Int,
    val disciplinaId: Int,
    val professorId: Int,
    val anoSemestre: String
)

fun OferecimentoEntity.toOferecimento() = Oferecimento(
    id.value,
    disciplinaId,
    professorId,
    anoSemestre
)