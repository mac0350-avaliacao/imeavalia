package models

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID


object Aluno : IntIdTable("alunos") {
    val name = varchar("name", 50)
    val nusp = varchar("nusp", 10)
    val email = varchar("email", 255).uniqueIndex()
}

class AlunoEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<AlunoEntity>(Aluno)

    var name by Aluno.name
    var nusp by Aluno.nusp
    var email by Aluno.email
}