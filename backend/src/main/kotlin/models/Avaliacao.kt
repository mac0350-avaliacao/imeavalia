package models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable


object Avaliacoes : IntIdTable("avaliacoes") {
    val oferecimentoId = integer("oferecimento_id")
    val disciplinaId = integer("disciplina_id")
    val anoSemestre = varchar("ano_semestre", 5)
    val materialDidatico = enumerationByName("material_didatico", 9, Nota::class)
    val didaticaProfessor = enumerationByName("didatica_professor", 9, Nota::class)
    val metodoAvaliativo = enumerationByName("metodo_avaliativo", 9, Nota::class)
    val monitoria = enumerationByName("monitoria", 9, Nota::class)
    val comentariosGerais = varchar("comentarios_gerais", 999)
    val presencaAtividades = enumerationByName("presenca_atividades", 9, Nota::class)
    val horasSemanais = enumerationByName("horas_semanais", 9, HorasSemanaisEnum::class)
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

class AvaliacaoEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AvaliacaoEntity>(Avaliacoes)

    var disciplinaId by Avaliacoes.disciplinaId
    var anoSemestre by Avaliacoes.anoSemestre
    var oferecimentoId by Avaliacoes.oferecimentoId
    var materialDidatico by Avaliacoes.materialDidatico
    var didaticaProfessor by Avaliacoes.didaticaProfessor
    var metodoAvaliativo by Avaliacoes.metodoAvaliativo
    var monitoria by Avaliacoes.monitoria
    var comentariosGerais by Avaliacoes.comentariosGerais
    var presencaAtividades by Avaliacoes.presencaAtividades
    var horasSemanais by Avaliacoes.horasSemanais
}

data class Avaliacao(
    val disciplinaId: Int,
    val anoSemestre: String,
    val oferecimentoId: Int,
    val materialDidatico: Nota,
    val didaticaProfessor: Nota,
    val metodoAvaliativo: Nota,
    val monitoria: Nota,
    val comentariosGerais: String,
    val presencaAtividades: Nota,
    val horasSemanais: HorasSemanaisEnum
)

fun AvaliacaoEntity.toAvaliacao() = Avaliacao(
    disciplinaId,
    anoSemestre,
    oferecimentoId,
    materialDidatico,
    didaticaProfessor,
    metodoAvaliativo,
    monitoria,
    comentariosGerais,
    presencaAtividades,
    horasSemanais
)