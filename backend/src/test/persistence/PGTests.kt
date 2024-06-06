package persistence

import org.junit.jupiter.api.Test
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table

import org.postgresql.ds.PGSimpleDataSource


class PGTests {
    @Test fun test() {
        val datasource = PGSimpleDataSource().apply {
            user = "postgres"
            password = "123456"
            databaseName = "banco-de-avaliacao"
        }
    }
    //Database.exposed(datasource)
    //SchemaUtils.createMissingTablesAndColumns(Items)
}

object Items: Table() {
    val id: Column<String> = varchar("id", 100)
}