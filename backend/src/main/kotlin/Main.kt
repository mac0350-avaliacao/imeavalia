package br.com.imeavalia

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.serialization.gson.gson
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import models.*
import routes.avaliacoesRoutes
import routes.disciplinasRoutes
import routes.professoresRoutes
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SchemaUtils

object Planetas: Table(){
    val nome = varchar("nome", 30)
}

fun main() {
    embeddedServer(Netty, port = 8081, module = Application::module).start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    install(CORS) {
        anyHost()
        allowHeader(HttpHeaders.ContentType)
    }

    Database.connect(
        "jdbc:postgresql://localhost:5432/postgres?useUnicode=true&characterEncoding=utf-8",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "123456"
    )

    /*

    val avaliacoes = transaction {
        AvaliacaoEntity.all().map { it.toAvaliacao() }
    }
    println(avaliacoes)
    */
    routing {
        disciplinasRoutes()
        professoresRoutes()
        avaliacoesRoutes()
    }
}

fun itDB() {
    transaction {
        SchemaUtils.create(Disciplinas)

        Disciplinas.insert {
            it[sigla] = "MAT1514"
            it[nome] = "A Matemática na Educação Básica"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0329"
            it[nome] = "Álgebra Booleana e Aplicações no Projeto de Arquitetura de Computadores"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0120"
            it[nome] = "Álgebra I para Licenciatura"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0213"
            it[nome] = "Álgebra II"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0231"
            it[nome] = "Álgebra II para Licenciatura"
        }
        Disciplinas.insert {
            it[sigla] = "MAT3211"
            it[nome] = "Álgebra Linear"
        }
        Disciplinas.insert {
            it[sigla] = "MAT3457"
            it[nome] = "Álgebra Linear I"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0122"
            it[nome] = "Álgebra Linear I"
        }
        Disciplinas.insert {
            it[sigla] = "MAT3458"
            it[nome] = "Álgebra Linear II"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0222"
            it[nome] = "Álgebra Linear II"
        }
        Disciplinas.insert {
            it[sigla] = "MAT2116"
            it[nome] = "Álgebra Linear para Química"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0450"
            it[nome] = "Algoritmos de Aproximação"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0430"
            it[nome] = "Algoritmos e Complexidade de Computação"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0121"
            it[nome] = "Algoritmos e Estruturas de Dados I"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0323"
            it[nome] = "Algoritmos e Estruturas de Dados II"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0328"
            it[nome] = "Algoritmos em Grafos"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0570"
            it[nome] = "Amostrador de Gibbs e Aplicacões"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0524"
            it[nome] = "Análise Bayesiana de Dados"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0338"
            it[nome] = "Análise de Algoritmos"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0352"
            it[nome] = "Análise de Dados Categorizados"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0560"
            it[nome] = "Análise de Dados Categorizados"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0399"
            it[nome] = "Análise de Dados e Simulação"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0328"
            it[nome] = "Análise de Regressão"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0412"
            it[nome] = "Análise de Textos Didáticos"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0447"
            it[nome] = "Análise e Reconhecimento de Formas: Teoria e Prática"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0314"
            it[nome] = "Análise Estatística"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0111"
            it[nome] = "Análise Exploratória de Dados"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0334"
            it[nome] = "Análise Funcional"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0353"
            it[nome] = "Análise Multivariada de Dados"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0330"
            it[nome] = "Análise Multivariada de Dados"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0206"
            it[nome] = "Análise Real"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0264"
            it[nome] = "Anéis e Corpos"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0501"
            it[nome] = "Anéis e Módulos"
        }
        Disciplinas.insert {
            it[sigla] = "MAP2210"
            it[nome] = "Aplicações de Álgebra Linear"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0326"
            it[nome] = "Aplicações de Processos Estocásticos"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0501"
            it[nome] = "Aprendizagem Estatística"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0344"
            it[nome] = "Arquitetura de Computadores"
        }
        Disciplinas.insert {
            it[sigla] = "MAP1021"
            it[nome] = "Atendimento de Dúvidas de Ensino Básico I"
        }
        Disciplinas.insert {
            it[sigla] = "MAP1022"
            it[nome] = "Atendimento de Dúvidas do Ensino Básico II"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0213"
            it[nome] = "Atividade Curricular em Comunidade"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0214"
            it[nome] = "Atividade Curricular em Cultura e Extensão"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0215"
            it[nome] = "Atividade Curricular em Pesquisa"
        }
        Disciplinas.insert {
            it[sigla] = "4502400"
            it[nome] = "Atividades Teórico-Práticas de Aprofundamento"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0414"
            it[nome] = "Autômatos, Computabilidade e Complexidade"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0465"
            it[nome] = "Biologia Computacional"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0375"
            it[nome] = "Biologia de Sistemas"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0512"
            it[nome] = "Biometria"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0141"
            it[nome] = "Cálculo"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0418"
            it[nome] = "Cálculo das Variações"
        }
        Disciplinas.insert {
            it[sigla] = "MAP0313"
            it[nome] = "Cálculo de Diferenças Finitas"
        }
        Disciplinas.insert {
            it[sigla] = "MAP0217"
            it[nome] = "Cálculo Diferencial"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0111"
            it[nome] = "Cálculo Diferencial e Integral I"
        }
        Disciplinas.insert {
            it[sigla] = "MAT3110"
            it[nome] = "Cálculo Diferencial e Integral I"
        }
        Disciplinas.insert {
            it[sigla] = "MAT2453"
            it[nome] = "Cálculo Diferencial e Integral I"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0146"
            it[nome] = "Cálculo Diferencial e Integral I para Economia"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0121"
            it[nome] = "Cálculo Diferencial e Integral II"
        }
        Disciplinas.insert {
            it[sigla] = "MAT2454"
            it[nome] = "Cálculo Diferencial e Integral II"
        }
        Disciplinas.insert {
            it[sigla] = "MAT3210"
            it[nome] = "Cálculo Diferencial e Integral II"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0147"
            it[nome] = "Cálculo Diferencial e Integral II para Economia"
        }
        Disciplinas.insert {
            it[sigla] = "MAT2464"
            it[nome] = "Cálculo Diferencial e Integral II para Estatística"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0205"
            it[nome] = "Cálculo Diferencial e Integral III"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0216"
            it[nome] = "Cálculo Diferencial e Integral III"
        }
        Disciplinas.insert {
            it[sigla] = "MAT3120"
            it[nome] = "Cálculo Diferencial e Integral III"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0211"
            it[nome] = "Cálculo Diferencial e Integral III"
        }
        Disciplinas.insert {
            it[sigla] = "MAT2455"
            it[nome] = "Cálculo Diferencial e Integral III"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0221"
            it[nome] = "Cálculo Diferencial e Integral IV"
        }
        Disciplinas.insert {
            it[sigla] = "MAT3220"
            it[nome] = "Cálculo Diferencial e Integral IV"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0220"
            it[nome] = "Cálculo Diferencial e Integral IV"
        }
        Disciplinas.insert {
            it[sigla] = "MAT2456"
            it[nome] = "Cálculo Diferencial e Integral IV"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0311"
            it[nome] = "Cálculo Diferencial e Integral V"
        }
        Disciplinas.insert {
            it[sigla] = "MAT2110"
            it[nome] = "Cálculo I para Química"
        }
        Disciplinas.insert {
            it[sigla] = "MAT2127"
            it[nome] = "Cálculo II para Química"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0208"
            it[nome] = "Cálculo III"
        }
        Disciplinas.insert {
            it[sigla] = "MAT2219"
            it[nome] = "Cálculo III para Química"
        }
        Disciplinas.insert {
            it[sigla] = "MAP0334"
            it[nome] = "Cálculo Integral"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0321"
            it[nome] = "Cálculo Integral"
        }
        Disciplinas.insert {
            it[sigla] = "MAP2122"
            it[nome] = "Cálculo Numérico Aplicado à Atuária"
        }
        Disciplinas.insert {
            it[sigla] = "MAP0214"
            it[nome] = "Cálculo Numérico com Aplicações em Física"
        }
        Disciplinas.insert {
            it[sigla] = "MAP0151"
            it[nome] = "Cálculo Numérico e Aplicações"
        }
        Disciplinas.insert {
            it[sigla] = "MAP0125"
            it[nome] = "Cálculo Numérico para Geociências"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0143"
            it[nome] = "Cálculo para Ciências Biológicas"
        }
        Disciplinas.insert {
            it[sigla] = "MAT1351"
            it[nome] = "Cálculo para Funções de Uma Variável Real I"
        }
        Disciplinas.insert {
            it[sigla] = "MAT1352"
            it[nome] = "Cálculo para Funções de Uma Variável Real II"
        }
        Disciplinas.insert {
            it[sigla] = "MAT2351"
            it[nome] = "Cálculo para Funções de Várias Variáveis I"
        }
        Disciplinas.insert {
            it[sigla] = "MAT2352"
            it[nome] = "Cálculo para Funções de Várias Variáveis II"
        }
        Disciplinas.insert {
            it[sigla] = "MAP0215"
            it[nome] = "Cálculo Vetorial e Aplicações"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0102"
            it[nome] = "Caminhos no Bacharelado em Ciência da Computação"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0459"
            it[nome] = "Ciência e Engenharia de Dados"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0694"
            it[nome] = "Combinatória I"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0695"
            it[nome] = "Combinatória II"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0463"
            it[nome] = "Computação Móvel"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0337"
            it[nome] = "Computação Musical"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0326"
            it[nome] = "Computação, Cibernética e Sistemas Cognitivos."
        }
        Disciplinas.insert {
            it[sigla] = "MAC0316"
            it[nome] = "Conceitos Fundamentais de Linguagens de Programação"
        }
        Disciplinas.insert {
            it[sigla] = "MAP0100"
            it[nome] = "Conceitos Introdutórios de Matemática"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0469"
            it[nome] = "Construção de Software como Serviço em Computação em Nuvem"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0532"
            it[nome] = "Controle Estatístico de Qualidade"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0336"
            it[nome] = "Criptografia para Segurança de Dados"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0510"
            it[nome] = "Demografia"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0327"
            it[nome] = "Desafios de Programação"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0345"
            it[nome] = "Desafios de Programação Avançados"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0470"
            it[nome] = "Desenvolvimento de Software Livre"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0471"
            it[nome] = "Desenvolvimento para Web"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0458"
            it[nome] = "Direito e Software"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0523"
            it[nome] = "Elementos da Teoria das Decisões"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0331"
            it[nome] = "Elementos da Teoria dos Conjuntos"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0467"
            it[nome] = "Empreendedorismo Digital"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0332"
            it[nome] = "Engenharia de Software"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0457"
            it[nome] = "Engenharia de Software Empírica"
        }
        Disciplinas.insert {
            it[sigla] = "MAP0413"
            it[nome] = "Equações de Derivadas Parciais"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0226"
            it[nome] = "Equações Diferenciais I"
        }
        Disciplinas.insert {
            it[sigla] = "MAP0316"
            it[nome] = "Equações Diferenciais II"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0130"
            it[nome] = "Equações Diferenciais Ordinarias e Aplicações"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0413"
            it[nome] = "Estatística Aplicada I"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0423"
            it[nome] = "Estatística Aplicada II"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0331"
            it[nome] = "Estatística Computacional"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0519"
            it[nome] = "Estatística de Redes Sociais"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0217"
            it[nome] = "Estatística Descritiva"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0418"
            it[nome] = "Estatística Documentária"
        }
        Disciplinas.insert {
            it[sigla] = "MAE1514"
            it[nome] = "Estatística no Ensino Básico"
        }
        Disciplinas.insert {
            it[sigla] = "MAE1511"
            it[nome] = "Estatística para Licenciatura I"
        }
        Disciplinas.insert {
            it[sigla] = "MAE1512"
            it[nome] = "Estatística para Licenciatura II"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0385"
            it[nome] = "Estruturas de Dados Avançadas"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0488"
            it[nome] = "Ética na Era da Informação"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0225"
            it[nome] = "Funções Analíticas"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0236"
            it[nome] = "Funções Diferenciáveis e Séries"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0546"
            it[nome] = "Fundamentos da Internet das Coisas"
        }
        Disciplinas.insert {
            it[sigla] = "MAP2220"
            it[nome] = "Fundamentos de Análise Numérica"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0105"
            it[nome] = "Fundamentos de Matemática para a Computação"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0540"
            it[nome] = "Genética de Populacões"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0105"
            it[nome] = "Geometria Analítica"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0331"
            it[nome] = "Geometria Computacional"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0354"
            it[nome] = "Geometria Diferencial"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0326"
            it[nome] = "Geometria Diferencial I"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0336"
            it[nome] = "Geometria Diferencial II"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0230"
            it[nome] = "Geometria e Desenho Geométrico I"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0240"
            it[nome] = "Geometria e Desenho Geométrico II"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0310"
            it[nome] = "Geometria III"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0421"
            it[nome] = "Geometria Não Euclidiana"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0419"
            it[nome] = "Geometria Projetiva e Desenho"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0265"
            it[nome] = "Grupos"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0340"
            it[nome] = "História da Álgebra"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0341"
            it[nome] = "História da Matemática I"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0430"
            it[nome] = "História da Matemática II"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0485"
            it[nome] = "Implicações Sociais da Computação"
        }
        Disciplinas.insert {
            it[sigla] = "MAP2433"
            it[nome] = "Indução Estatística, Ontologia e Metafísica"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0302"
            it[nome] = "Inferência Bayesiana"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0042"
            it[nome] = "Inferência Causal"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0311"
            it[nome] = "Inferência Estatística"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0527"
            it[nome] = "Inferência Estatística Comparada"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0301"
            it[nome] = "Inferência Estatística Frequentista"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0339"
            it[nome] = "Informação, Comunicação e a Sociedade do Conhecimento"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0101"
            it[nome] = "Integração na Universidade e na Profissão"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0425"
            it[nome] = "Inteligência Artificial"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0511"
            it[nome] = "Introdução à Álgebra Comutativa"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0134"
            it[nome] = "Introdução a Álgebra Linear"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0315"
            it[nome] = "Introdução à Análise"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0320"
            it[nome] = "Introdução à Análise Complexa"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0261"
            it[nome] = "Introdução à Análise de Dados"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0354"
            it[nome] = "Introdução a Análise de Sobrevivência"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0514"
            it[nome] = "Introducão a Análise de Sobrevivência"
        }
        Disciplinas.insert {
            it[sigla] = "MAP0216"
            it[nome] = "Introdução à Análise Real"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0530"
            it[nome] = "Introdução a Análise Sequêncial"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0580"
            it[nome] = "Introdução à aprendizagem estatística"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0110"
            it[nome] = "Introdução à Computação"
        }
        Disciplinas.insert {
            it[sigla] = "MAC2166"
            it[nome] = "Introdução à Computação"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0420"
            it[nome] = "Introdução a Computação Gráfica"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0115"
            it[nome] = "Introdução à Computação para Ciências Exatas e Tecnologia"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0113"
            it[nome] = "Introdução à Computação para Ciências Humanas"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0516"
            it[nome] = "Introdução à Confiabilidade"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0225"
            it[nome] = "Introdução à Inferência Estatística"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0349"
            it[nome] = "Introdução à Lógica"
        }
        Disciplinas.insert {
            it[sigla] = "MAP2112"
            it[nome] = "Introdução à Lógica de Programação e Modelagem Computacional"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0239"
            it[nome] = "Introdução à Lógica e Verificação de Programas"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0119"
            it[nome] = "Introdução à Probabilidade e à Estatística"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0219"
            it[nome] = "Introdução à Probabilidade e a Estatística I"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0121"
            it[nome] = "Introdução a Probabilidade e a Estatística I"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0229"
            it[nome] = "Introdução à Probabilidade e a Estatística II"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0212"
            it[nome] = "Introdução à Probabilidade e à Estatística II"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0119"
            it[nome] = "Introdução à Programação de Computadores"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0318"
            it[nome] = "Introdução à Programação de Robôs Móveis"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0552"
            it[nome] = "Introdução à Teoria da Informação"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0370"
            it[nome] = "Introdução à Teoria das Categorias"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0320"
            it[nome] = "Introdução à Teoria dos Grafos"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0515"
            it[nome] = "Introdução à Teoria dos Jogos"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0223"
            it[nome] = "Introdução a Teoria dos Números"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0431"
            it[nome] = "Introdução à Topologia Algébrica"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0432"
            it[nome] = "Introdução à Topologia Diferencial"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0460"
            it[nome] = "Introdução ao aprendizado de máquina"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0350"
            it[nome] = "Introdução ao Desenvolvimento de Sistemas de Software"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0317"
            it[nome] = "Introdução ao Processamento de Sinais Digitais"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0148"
            it[nome] = "Introdução ao trabalho Científico"
        }
        Disciplinas.insert {
            it[sigla] = "MAT0350"
            it[nome] = "Introdução aos Fundamentos de Matemática"
        }
        Disciplinas.insert {
            it[sigla] = "MAE0312"
            it[nome] = "Introdução aos Processos Estocásticos"
        }
        Disciplinas.insert {
            it[sigla] = "MAC0313"
            it[nome] = "Introdução aos Sistemas de Bancos de Dados para Estatística"
        }
        Disciplinas.insert {
            it[sigla] = "MAP2223"
            it[nome] = "Introdução às Equações Diferenciais Ordinárias e Aplicações"
        }
        Disciplinas.insert {
            it[sigla] = "MAP0431"
            it[nome] = "Introdução Matemática à Mecânica dos Fluidos"
        }
        val disciplinas = Disciplinas.selectAll().map {
            it[Disciplinas.nome]
        }
        println(disciplinas)
    }

    transaction {
        SchemaUtils.create(Professores)
        Professores.insert {
            it[nome] = "Albert Meads Fisher"
        }
        Professores.insert {
            it[nome] = "Alexandre Grichkov"
        }
        Professores.insert {
            it[nome] = "Alexandre Lymberopoulos"
        }
        Professores.insert {
            it[nome] = "Ana Catarina Pontone Hellmeister"
        }
        Professores.insert {
            it[nome] = "Ana Paula Jahn"
        }
        Professores.insert {
            it[nome] = "Antonio Carlos Brolezzi"
        }
        Professores.insert {
            it[nome] = "Antonio de Padua Franco Filho"
        }
        Professores.insert {
            it[nome] = "Antonio Luiz Pereira"
        }
        Professores.insert {
            it[nome] = "Artur Hideyuki Tomita"
        }
        Professores.insert {
            it[nome] = "Augusto Reynol Filho"
        }
        Professores.insert {
            it[nome] = "Barbara Corominas Valério"
        }
        Professores.insert {
            it[nome] = "Christina Brech"
        }
        Professores.insert {
            it[nome] = "Claudia Cueva Candido"
        }
        Professores.insert {
            it[nome] = "Claudio Gorodski"
        }
        Professores.insert {
            it[nome] = "Cristian Andres Ortiz Gonzalez"
        }
        Professores.insert {
            it[nome] = "Cristina Cerri"
        }
        Professores.insert {
            it[nome] = "Daciberg Lima Gonçalves"
        }
        Professores.insert {
            it[nome] = "Daniel Victor Tausk"
        }
        Professores.insert {
            it[nome] = "Daniela Mariz Silva Vieira"
        }
        Professores.insert {
            it[nome] = "David Pires Dias"
        }
        Professores.insert {
            it[nome] = "Deborah Martins Raphael"
        }
        Professores.insert {
            it[nome] = "Edson de Faria"
        }
        Professores.insert {
            it[nome] = "Edson Vargas"
        }
        Professores.insert {
            it[nome] = "Eduardo do Nascimento Marcos"
        }
        Professores.insert {
            it[nome] = "Eduardo Rosinato Longa"
        }
        Professores.insert {
            it[nome] = "Elizabeth Ferreira Santos"
        }
        Professores.insert {
            it[nome] = "Eloi Medina Galego"
        }
        Professores.insert {
            it[nome] = "Felipe Yukihide Yasumura"
        }
        Professores.insert {
            it[nome] = "Flavio Ulhoa Coelho"
        }
        Professores.insert {
            it[nome] = "Francisco Cesar Polcino Milies"
        }
        Professores.insert {
            it[nome] = "Francisco Miraglia Neto"
        }
        Professores.insert {
            it[nome] = "Francisco Rui Tavares de Almeida"
        }
        Professores.insert {
            it[nome] = "Gaetano Siciliano"
        }
        Professores.insert {
            it[nome] = "Glaucio Terra"
        }
        Professores.insert {
            it[nome] = "Henrique Guzzo Junior"
        }
        Professores.insert {
            it[nome] = "Hugo Luiz Mariano"
        }
        Professores.insert {
            it[nome] = "Humberto Daniel Carrión Villarroel"
        }
        Professores.insert {
            it[nome] = "Iole de Freitas Druck"
        }
        Professores.insert {
            it[nome] = "Ivan Chestakov"
        }
        Professores.insert {
            it[nome] = "Ivan Struchiner"
        }
        Professores.insert {
            it[nome] = "Jaime Angulo Pava"
        }
        Professores.insert {
            it[nome] = "Jairo Zacarias Goncalves"
        }
        Professores.insert {
            it[nome] = "Javier Sanchez Serdà"
        }
        Professores.insert {
            it[nome] = "João Fernando da Cunha Nariyoshi"
        }
        Professores.insert {
            it[nome] = "João Henrique Santos de Andrade"
        }
        Professores.insert {
            it[nome] = "Jorge Tadashi Hiratuka"
        }
        Professores.insert {
            it[nome] = "Jose Antonio Verderesi"
        }
        Professores.insert {
            it[nome] = "Jose Carlos Diniz Fernandes"
        }
        Professores.insert {
            it[nome] = "José Luis Vilca Rodríguez"
        }
        Professores.insert {
            it[nome] = "Juan Carlos Gutierrez Fernandez"
        }
        Professores.insert {
            it[nome] = "Júlio César Augusto do Valle"
        }
        Professores.insert {
            it[nome] = "Kostiantyn Iusenko"
        }
        Professores.insert {
            it[nome] = "Leila Maria Vasconcellos Figueiredo"
        }
        Professores.insert {
            it[nome] = "Leonardo Pellegrini Rodrigues"
        }
        Professores.insert {
            it[nome] = "Lucas Colucci Cavalcante de Souza"
        }
        Professores.insert {
            it[nome] = "Lucia Renato Junqueira"
        }
        Professores.insert {
            it[nome] = "Lucia Satie Ikemoto Murakami"
        }
        Professores.insert {
            it[nome] = "Lucilia Daruiz Borsari"
        }
        Professores.insert {
            it[nome] = "Marcone Corrêa Pereira"
        }
        Professores.insert {
            it[nome] = "Marcos Martins Alexandrino da Silva"
        }
        Professores.insert {
            it[nome] = "Maria Elisa Esteves Lopes Galvao"
        }
        Professores.insert {
            it[nome] = "Maria Izabel Ramalho Martins"
        }
        Professores.insert {
            it[nome] = "Martha Patrícia Dussan Angulo"
        }
        Professores.insert {
            it[nome] = "Mary Lilian Lourenço"
        }
        Professores.insert {
            it[nome] = "Mikhailo Dokuchaev"
        }
        Professores.insert {
            it[nome] = "Nataliia Goloshchapova"
        }
        Professores.insert {
            it[nome] = "Odilon Otavio Luciano"
        }
        Professores.insert {
            it[nome] = "Ofelia Teresa Alas"
        }
        Professores.insert {
            it[nome] = "Orlando Stanley Juriaans"
        }
        Professores.insert {
            it[nome] = "Oscar Joao Abdounur"
        }
        Professores.insert {
            it[nome] = "Oswaldo Rio Branco de Oliveira"
        }
        Professores.insert {
            it[nome] = "Paolo Piccione"
        }
        Professores.insert {
            it[nome] = "Pavlos Bahia Konstadinidis"
        }
        Professores.insert {
            it[nome] = "Pedro Luiz Fagundes"
        }
        Professores.insert {
            it[nome] = "Pierluigi Benevieri"
        }
        Professores.insert {
            it[nome] = "Raul Antonio Ferraz"
        }
        Professores.insert {
            it[nome] = "Renato Ferreira de Velloso Vianna"
        }
        Professores.insert {
            it[nome] = "Ricardo Bianconi"
        }
        Professores.insert {
            it[nome] = "Ricardo dos Santos Freire Junior"
        }
        Professores.insert {
            it[nome] = "Rogerio Augusto dos Santos Fajardo"
        }
        Professores.insert {
            it[nome] = "Sergio Alves"
        }
        Professores.insert {
            it[nome] = "Severino Toscano do Rego Melo"
        }
        Professores.insert {
            it[nome] = "Valentin Raphael Henri Ferenczi"
        }
        Professores.insert {
            it[nome] = "Vera Helena Giusti de Souza"
        }
        Professores.insert {
            it[nome] = "Vinícius Morelli Cortes"
        }
        Professores.insert {
            it[nome] = "Vitor de Oliveira Ferreira"
        }
        Professores.insert {
            it[nome] = "Vyacheslav Futorny"
        }
        Professores.insert {
            it[nome] = "Wilson Albeiro Cuellar Carrera"
        }
        Professores.insert {
            it[nome] = "Zara Issa Abud"
        }
        Professores.insert {
            it[nome] = "Ana Catarina Pontone Hellmeister"
        }
        Professores.insert {
            it[nome] = "Antonio Luiz Pereira"
        }
        Professores.insert {
            it[nome] = "Claudia Cueva Candido"
        }
        Professores.insert {
            it[nome] = "Cristina Cerri"
        }
        Professores.insert {
            it[nome] = "Daciberg Lima Gonçalves"
        }
        Professores.insert {
            it[nome] = "Francisco Cesar Polcino Milies"
        }
        Professores.insert {
            it[nome] = "Francisco Miraglia Neto"
        }
        Professores.insert {
            it[nome] = "Francisco Rui Tavares de Almeida"
        }
        Professores.insert {
            it[nome] = "Iole de Freitas Druck"
        }
        Professores.insert {
            it[nome] = "Ivan Chestakov"
        }
        Professores.insert {
            it[nome] = "Jairo Zacarias Goncalves"
        }
        Professores.insert {
            it[nome] = "Jose Antonio Verderesi"
        }
        Professores.insert {
            it[nome] = "Lucilia Daruiz Borsari"
        }
        Professores.insert {
            it[nome] = "Maria Elisa Esteves Lopes Galvao"
        }
        Professores.insert {
            it[nome] = "Maria Izabel Ramalho Martins"
        }
        Professores.insert {
            it[nome] = "Ofelia Teresa Alas"
        }
        Professores.insert {
            it[nome] = "Oswaldo Rio Branco de Oliveira"
        }
        Professores.insert {
            it[nome] = "Sergio Alves"
        }
        Professores.insert {
            it[nome] = "Severino Toscano do Rego Melo"
        }
        Professores.insert {
            it[nome] = "Vera Helena Giusti de Souza"
        }
        Professores.insert {
            it[nome] = "Artur Hideyuki Tomita"
        }
        Professores.insert {
            it[nome] = "Claudio Gorodski"
        }
        Professores.insert {
            it[nome] = "Cristian Andres Ortiz Gonzalez"
        }
        Professores.insert {
            it[nome] = "Edson de Faria"
        }
        Professores.insert {
            it[nome] = "Edson Vargas"
        }
        Professores.insert {
            it[nome] = "Eduardo do Nascimento Marcos"
        }
        Professores.insert {
            it[nome] = "Flavio Ulhoa Coelho"
        }
        Professores.insert {
            it[nome] = "Jaime Angulo Pava"
        }
        Professores.insert {
            it[nome] = "Marcone Corrêa Pereira"
        }
        Professores.insert {
            it[nome] = "Mikhailo Dokuchaev"
        }
        Professores.insert {
            it[nome] = "Paolo Piccione"
        }
        Professores.insert {
            it[nome] = "Ricardo Bianconi"
        }
        Professores.insert {
            it[nome] = "Valentin Raphael Henri Ferenczi"
        }
        Professores.insert {
            it[nome] = "Vyacheslav Futorny"
        }
        Professores.insert {
            it[nome] = "Albert Meads Fisher"
        }
        Professores.insert {
            it[nome] = "Alexandre Grichkov"
        }
        Professores.insert {
            it[nome] = "Antonio Carlos Brolezzi"
        }
        Professores.insert {
            it[nome] = "Christina Brech"
        }
        Professores.insert {
            it[nome] = "Daniel Victor Tausk"
        }
        Professores.insert {
            it[nome] = "David Pires Dias"
        }
        Professores.insert {
            it[nome] = "Eloi Medina Galego"
        }
        Professores.insert {
            it[nome] = "Gaetano Siciliano"
        }
        Professores.insert {
            it[nome] = "Henrique Guzzo Junior"
        }
        Professores.insert {
            it[nome] = "Hugo Luiz Mariano"
        }
        Professores.insert {
            it[nome] = "Juan Carlos Gutierrez Fernandez"
        }
        Professores.insert {
            it[nome] = "Kostiantyn Iusenko"
        }
        Professores.insert {
            it[nome] = "Lucia Renato Junqueira"
        }
        Professores.insert {
            it[nome] = "Lucia Satie Ikemoto Murakami"
        }
        Professores.insert {
            it[nome] = "Marcos Martins Alexandrino da Silva"
        }
        Professores.insert {
            it[nome] = "Martha Patrícia Dussan Angulo"
        }
        Professores.insert {
            it[nome] = "Mary Lilian Lourenço"
        }
        Professores.insert {
            it[nome] = "Nataliia Goloshchapova"
        }
        Professores.insert {
            it[nome] = "Orlando Stanley Juriaans"
        }
        Professores.insert {
            it[nome] = "Oscar Joao Abdounur"
        }
        Professores.insert {
            it[nome] = "Pierluigi Benevieri"
        }
        Professores.insert {
            it[nome] = "Rogerio Augusto dos Santos Fajardo"
        }
        Professores.insert {
            it[nome] = "Vitor de Oliveira Ferreira"
        }
        Professores.insert {
            it[nome] = "Alexandre Lymberopoulos"
        }
        Professores.insert {
            it[nome] = "Ana Paula Jahn"
        }
        Professores.insert {
            it[nome] = "Antonio de Padua Franco Filho"
        }
        Professores.insert {
            it[nome] = "Augusto Reynol Filho"
        }
        Professores.insert {
            it[nome] = "Barbara Corominas Valério"
        }
        Professores.insert {
            it[nome] = "Daniela Mariz Silva Vieira"
        }
        Professores.insert {
            it[nome] = "Deborah Martins Raphael"
        }
        Professores.insert {
            it[nome] = "Eduardo Rosinato Longa"
        }
        Professores.insert {
            it[nome] = "Elizabeth Ferreira Santos"
        }
        Professores.insert {
            it[nome] = "Felipe Yukihide Yasumura"
        }
        Professores.insert {
            it[nome] = "Glaucio Terra"
        }
        Professores.insert {
            it[nome] = "Humberto Daniel Carrión Villarroel"
        }
        Professores.insert {
            it[nome] = "Ivan Struchiner"
        }
        Professores.insert {
            it[nome] = "Javier Sanchez Serdà"
        }
        Professores.insert {
            it[nome] = "João Fernando da Cunha Nariyoshi"
        }
        Professores.insert {
            it[nome] = "João Henrique Santos de Andrade"
        }
        Professores.insert {
            it[nome] = "Jorge Tadashi Hiratuka"
        }
        Professores.insert {
            it[nome] = "Jose Carlos Diniz Fernandes"
        }
        Professores.insert {
            it[nome] = "José Luis Vilca Rodríguez"
        }
        Professores.insert {
            it[nome] = "Júlio César Augusto do Valle"
        }
        Professores.insert {
            it[nome] = "Leila Maria Vasconcellos Figueiredo"
        }
        Professores.insert {
            it[nome] = "Leonardo Pellegrini Rodrigues"
        }
        Professores.insert {
            it[nome] = "Lucas Colucci Cavalcante de Souza"
        }
        Professores.insert {
            it[nome] = "Odilon Otavio Luciano"
        }
        Professores.insert {
            it[nome] = "Pavlos Bahia Konstadinidis"
        }
        Professores.insert {
            it[nome] = "Pedro Luiz Fagundes"
        }
        Professores.insert {
            it[nome] = "Raul Antonio Ferraz"
        }
        Professores.insert {
            it[nome] = "Renato Ferreira de Velloso Vianna"
        }
        Professores.insert {
            it[nome] = "Ricardo dos Santos Freire Junior"
        }
        Professores.insert {
            it[nome] = "Vinícius Morelli Cortes"
        }
        Professores.insert {
            it[nome] = "Wilson Albeiro Cuellar Carrera"
        }
        Professores.insert {
            it[nome] = "Zara Issa Abud"
        }
    }

}