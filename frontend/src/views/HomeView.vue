<script setup></script>

<template>
  <main class="home">
    <v-form >
      <div class="disciplineSelect">
        <v-select
          v-model="selectedDisciplines"
          :items="disciplines"
          :rules="SelectRule"
          item-title="nome"
          item-value="id"
          hint="Selecione todas as matérias que você irá avaliar"
          label="Selecione a disciplina"
          persistent-hint
        >
          ></v-select
        >
      </div>
      <div class="professorSelect">
        <v-select
          v-model="selectedProfessors"
          :rules="SelectRule"
          :items="professors"
          item-title="nome"
          item-value="id"
          label="Selecione o(a) professor(a)"
          persistent-hint
        ></v-select>
      </div>
      <div class="disciplineReview">
        <div v-for="type in reviewTypes" :key="type">
          <p>Avaliação do {{ type.name }}</p>
          <v-radio-group v-model="type.value">
            <v-radio
              :color="reviewColors['veryBad']"
              label="Muito ruim"
              value="MuitoRuim"
            ></v-radio>
            <v-radio :color="reviewColors['bad']" label="Ruim" value="Ruim"></v-radio>
            <v-radio label="Neutro" value="Neutro"></v-radio>
            <v-radio :color="reviewColors['good']" label="Bom" value="Bom"></v-radio>
            <v-radio :color="reviewColors['veryGood']" label="Muito bom" value="MuitoBom"></v-radio>
          </v-radio-group>
        </div>
      </div>
      <div>
        <p>Quantas horas por semana você dedica ao estudo dessa disciplina? <br /></p>
        <v-radio-group v-model="disciplineHours">
          <v-radio label="1h ou menos" value="UmaHoraOuMenos"></v-radio>
          <v-radio label="2h" value="DuasHoras"></v-radio>
          <v-radio label="3 horas" value="TresHoras"></v-radio>
          <v-radio label="4 horas ou mais" value="QuatroHorasOuMais"></v-radio>
        </v-radio-group>
      </div>
      <div>
        <p>
          Quanto a sua presença nas aulas e realização de atividades propostas, você diria que
          <br />
        </p>
        <v-radio-group v-model="disciplinePresence">
          <v-radio :color="reviewColors['veryBad']" label="muito ruim" value="MuitoRuim"></v-radio>
          <v-radio :color="reviewColors['bad']" label="ruim" value="Ruim"></v-radio>
          <v-radio :color="reviewColors['good']" label="boa" value="Bom"></v-radio>
          <v-radio :color="reviewColors['veryGood']" label="muito boa" value="MuitoBom"></v-radio>
        </v-radio-group>
      </div>
      <div>
        <p>Caso queira, escreva comentários gerais sobre a matéria</p>
        <v-textarea v-model="comentariosGerais" label="Comentários gerais"></v-textarea>
      </div>
      <div>
        <p>Caso queira, escreva comentários sobre a consulta</p>
        <v-textarea v-model="comentariosAvaliacao" label="Comentários consulta"></v-textarea>
      </div>
    </v-form>
    <v-btn @click="submitForm">Enviar</v-btn>
  </main>
</template>

<script>
import api from '../api'

export default {
  data: () => ({
    disciplineHours: '',
    disciplinePresence: '',
    anoSemestre: '',
    selectedDisciplines: null,
    selectedProfessors: null,
    //reviewColors: ['red', '#DC143C', 'green', 'blue'],
    reviewColors: {
      veryBad: 'red',
      bad: '#DC143C',
      good: 'green',
      veryGood: 'blue'
    },
    professors: [],
    disciplines: [],
    reviewTypes: [
      { name: 'Material Didático', value: '' },
      { name: 'Didática do professor', value: '' },
      { name: 'Método avaliativo', value: '' },
      { name: 'Monitoria', value: '' }
    ],
    comentariosGerais: '',
    comentariosAvaliacao: '',
    SelectRule: [
      (value) => {
        if (value) return true
        return 'Campo não pode ser vazio'
      }
    ]
  }),
  async created() {
    try {
      const response = await api.getDisciplinas()
      this.disciplines = response.data
    } catch (error) {
      console.error('Error fetching disciplines:', error)
    }

    try {
      const response = await api.getProfessores()
      this.professors = response.data
    } catch (error) {
      console.error('Error fetching professors:', error)
    }
  },
  mounted() {
    this.setYearSemester()
  },
  methods: {
    setYearSemester() {
      const current_date = new Date()

      const current_year = current_date.getFullYear()
      const current_month = current_date.getMonth()

      var semester = current_month <= 5 ? 1 : 2

      this.anoSemestre = `${current_year}${semester}`
    },

    async submitForm() {
      const formData = {
        disciplinaId: this.selectedDisciplines,
        professorId: this.selectedProfessors,
        anoSemestre: this.anoSemestre,
        materialDidatico: this.reviewTypes.find((type) => type.name === 'Material Didático').value,
        didaticaProfessor: this.reviewTypes.find((type) => type.name === 'Didática do professor')
          .value,
        metodoAvaliativo: this.reviewTypes.find((type) => type.name === 'Método avaliativo').value,
        monitoria: this.reviewTypes.find((type) => type.name === 'Monitoria').value,
        horasSemanais: this.disciplineHours,
        presencaAtividades: this.disciplinePresence,
        comentariosGerais: this.comentariosGerais,
        comentariosAvaliacao: this.comentariosAvaliacao
      }

      console.log(formData)

      try {
        const response = await api.postAvaliacao(formData)
        console.log('Form submitted successfully:', response.data)
      } catch (error) {

        console.error('Error submitting form:', error)
        console.log(formData)
      }
    }
  }
}
</script>

<style>
.disciplineReview {
  display: flex;
  flex-wrap: wrap;
}

.disciplineReview > * {
  padding: 1em;
}

.home {
  display: flex;
  justify-content: center;
  flex-direction: column;
  background-color: #e3f3f3;
  border-radius: 1em;
  padding: 2em;
}
</style>
