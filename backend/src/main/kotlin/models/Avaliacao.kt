package models

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable


object Avaliacao : IntIdTable("avaliacoes") {
    val disciplina_id = integer("disciplina")
    val materialDidatico = enumerationByName("materialDidatico", 9, Nota::class)
    val didaticaProfessor = enumerationByName("didaticaProfessor", 9, Nota::class)
    val metodoAvaliativo = enumerationByName("metodoAvaliativo", 9, Nota::class)
    val monitoria = enumerationByName("monitoria", 9, Nota::class)

    val comentariosGerais = varchar("comentariosGerais", 999)

    val presencaAtividades = enumerationByName("presencaAtividades", 9, Nota::class)
    val horasSemanais = enumerationByName("horasSemanais", 9, HorasSemanaisEnum::class)
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
    companion object : IntEntityClass<AvaliacaoEntity>(Avaliacao)

    var disciplina_id by Avaliacao.disciplina_id
    var materialDidatico by Avaliacao.materialDidatico
    var didaticaProfessor by Avaliacao.didaticaProfessor
    var metodoAvaliativo by Avaliacao.metodoAvaliativo
    var monitoria by Avaliacao.monitoria
    var comentariosGerais by Avaliacao.comentariosGerais
    var presencaAtividades by Avaliacao.presencaAtividades
    var horasSemanais by Avaliacao.horasSemanais
}