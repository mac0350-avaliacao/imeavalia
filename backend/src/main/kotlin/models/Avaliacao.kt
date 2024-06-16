package models

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow



object Avaliacoes : IntIdTable("avaliacoes") {
    val oferecimentoId = integer("oferecimento_id")
    val disciplinaId = integer("disciplina_id")
    val professorId = integer("professor_id")
    val anoSemestre = varchar("ano_semestre", 5)
    val materialDidatico = enumerationByName("material_didatico", 9, Nota::class)
    val didaticaProfessor = enumerationByName("didatica_professor", 9, Nota::class)
    val metodoAvaliativo = enumerationByName("metodo_avaliativo", 9, Nota::class)
    val monitoria = enumerationByName("monitoria", 9, Nota::class)
    val comentariosGerais = varchar("comentarios_gerais", 999)
    val comentariosAvaliacao = varchar("comentarios_avaliacao", 999)
    val presencaAtividades = enumerationByName("presenca_atividades", 9, Nota::class)
    val horasSemanais = enumerationByName("horas_semanais", 17, HorasSemanaisEnum::class)
}

enum class Nota(val description: String) {
    MuitoRuim("Muito Ruim"),
    Ruim("Ruim"),
    Neutro("Neutro"),
    Bom("Bom"),
    MuitoBom("Muito Bom")
}

enum class HorasSemanaisEnum(val description: String) {
    UmaHoraOuMenos("Uma hora ou menos"),
    DuasHoras("Duas horas"),
    TresHoras("Tres horas"),
    QuatroHorasOuMais("Quatro horas ou mais")
}

data class Avaliacao(
    val id: Int,
    val disciplinaId: Int,
    val professorId: Int,
    val anoSemestre: String,
    val oferecimentoId: Int,
    val materialDidatico: Nota,
    val didaticaProfessor: Nota,
    val metodoAvaliativo: Nota,
    val monitoria: Nota,
    val comentariosGerais: String,
    val comentariosAvaliacao: String,
    val presencaAtividades: Nota,
    val horasSemanais: HorasSemanaisEnum
)

fun ResultRow.toAvaliacao() = Avaliacao(
    id = this[Avaliacoes.id].value,
    disciplinaId = this[Avaliacoes.disciplinaId],
    professorId = this[Avaliacoes.professorId],
    anoSemestre = this[Avaliacoes.anoSemestre],
    oferecimentoId = this[Avaliacoes.oferecimentoId],
    materialDidatico = this[Avaliacoes.materialDidatico],
    didaticaProfessor = this[Avaliacoes.didaticaProfessor],
    metodoAvaliativo = this[Avaliacoes.metodoAvaliativo],
    monitoria = this[Avaliacoes.monitoria],
    comentariosGerais = this[Avaliacoes.comentariosGerais],
    comentariosAvaliacao = this[Avaliacoes.comentariosAvaliacao],
    presencaAtividades = this[Avaliacoes.presencaAtividades],
    horasSemanais = this[Avaliacoes.horasSemanais]
)