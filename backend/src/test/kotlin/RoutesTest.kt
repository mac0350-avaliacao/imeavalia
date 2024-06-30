import br.com.imeavalia.configureRouting
import config.initDB
import io.github.cdimascio.dotenv.dotenv
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.config.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.testing.*
import junit.framework.TestCase
import models.Avaliacoes
import models.Disciplinas
import models.Oferecimentos
import models.Professores
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.After
import org.junit.Before
import kotlin.test.Test


internal class RoutesTest {
    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
        }
        val response = client.get("/")
        println(response.toString())
        TestCase.assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testGetDisciplinas() = testApplication {
        application {
            configureRouting()
        }
        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
            }
        }
        val response = client.get("/disciplinas")
        TestCase.assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testGetAvaliacoes() = testApplication {
        application {
            configureRouting()
        }
        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
            }
        }
        val response = client.get("/avaliacoes")
        TestCase.assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testGetOferecimentos() = testApplication {
        application {
            configureRouting()
        }
        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
            }
        }
        val response = client.get("/oferecimentos")
        TestCase.assertEquals(HttpStatusCode.OK, response.status)
    }
}