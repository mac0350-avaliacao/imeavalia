package models

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow


object Oferecimentos : IntIdTable("oferecimentos") {
    val disciplinaId = reference("disciplina_id", Disciplinas)
    val professorId = reference("professor_id", Professores)
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
    disciplinaId = this[Oferecimentos.disciplinaId].value,
    professorId = this[Oferecimentos.professorId].value,
    anoSemestre = this[Oferecimentos.anoSemestre]
)

data class OferecimentoDetalhes(
    val disciplina_sigla: String,
    val professor_nome: String,
    val disciplina_nome: String,
    val disciplina_id: Int,
    val professor_id: Int,
    val id: Int
)
