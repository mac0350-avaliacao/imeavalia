package models

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow


object Oferecimentos : IntIdTable("oferecimentos") {
    val disciplinaId = integer("disciplina_id")
    val professorId = integer("professor_id")
    val anoSemestre = varchar("ano_semestre", 5)
}

data class Oferecimento(
    val id: Int,
    val disciplinaId: Int,
    val professorId: Int,
    val anoSemestre: String
)

fun ResultRow.toOferecimento() = Oferecimento(
    id = this[Oferecimentos.id].value,
    disciplinaId = this[Oferecimentos.disciplinaId],
    professorId = this[Oferecimentos.professorId],
    anoSemestre = this[Oferecimentos.anoSemestre]
)