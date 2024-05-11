package br.com.imeavalia

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SchemaUtils

import models.Aluno
import models.AlunoEntity
import models.Disciplina
import models.DisciplinaEntity
import org.jetbrains.exposed.sql.addLogger
import models.Avaliacao
import models.AvaliacaoEntity
import models.Nota
import models.HorasSemanaisEnum

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {

    // Connect to your database
    Database.connect(
        "jdbc:postgresql://localhost:5432/postgres",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "123456"
    )

    transaction {
        /*
        SchemaUtils.createMissingTablesAndColumns(Aluno)

        val newAluno = AlunoEntity.new {
            name = "Aya Meira"
            nusp = "9353081"
            email = "aya.meira@usp.br"
        }
        SchemaUtils.createMissingTablesAndColumns(Disciplina)

        val disciplina = DisciplinaEntity.new {
            name = "Desenvolvimento de Software"
            sigla = "MAC0250"
        }
        */
        SchemaUtils.createMissingTablesAndColumns(Avaliacao)

        val newAvaliacao = AvaliacaoEntity.new {
            disciplina_id = 1
            materialDidatico = Nota.Bom
            didaticaProfessor = Nota.MuitoBom
            metodoAvaliativo = Nota.Neutro
            monitoria = Nota.Ruim
            comentariosGerais = "Curso bem estruturado, mas com avaliações confusas."
            presencaAtividades = Nota.Bom
            horasSemanais = HorasSemanaisEnum.DuasHoras
        }

    }

    println("Hello, world")

}