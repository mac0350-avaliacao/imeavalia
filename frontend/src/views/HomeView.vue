<script setup></script>

<template>
  <main>
    <div>
      <div class="disciplineSelect">
        <v-select
          v-model="selectedDisciplines"
          :items="disciplines"
          item-title="nome"
          item-value="id"
          hint="Selecione todas as matérias que você irá avaliar"
          label="Selecione a disciplina"
          persistent-hint
        ></v-select>
      </div>
      <div class="professorSelect">
        <v-select
            v-model="selectedProfessors"
            :items="professors"
            item-title="nome"
            item-value="id"
            label="Selecione o(a) professor(a)"
            persistent-hint
        ></v-select>
      </div>
      <div>
        <v-text-field
          v-model="anoSemestre"
          label="Ano e Semestre (ex: 2024-2)"
        ><!--  to do: v-mask="'####-#'" -->
        </v-text-field>
      </div>
      <div class="disciplineReview">
        <div v-for="type in reviewTypes" :key="type">
          <p>Avaliação do {{ type.name }}: {{ type.value }}</p>
          <v-radio-group v-model="type.value">
            <v-radio :color="reviewColors[0]" label="Muito ruim" value="MuitoRuim"></v-radio>
            <v-radio :color="reviewColors[1]" label="Ruim" value="Ruim"></v-radio>
            <v-radio label="Neutro" value="neutro"></v-radio>
            <v-radio :color="reviewColors[2]" label="Bom" value="Bom"></v-radio>
            <v-radio :color="reviewColors[3]" label="Muito bom" value="MuitoBom"></v-radio>
          </v-radio-group>
        </div>
      </div>
      <div>
        <p>
          Quantas horas por semana você dedica ao estudo dessa disciplina? <br />
        </p>
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
          <v-radio label="muito ruim" value="MuitoRuim"></v-radio>
          <v-radio label="ruim" value="Ruim"></v-radio>
          <v-radio label="boa" value="Bom"></v-radio>
          <v-radio label="muito boa" value="MuitoBom"></v-radio>
        </v-radio-group>
      </div>
      <div>
        <v-text-field v-model="comentariosGerais" label="Comentários gerais"></v-text-field>
      </div>
      <div>
        <v-text-field v-model="comentariosAvaliacao" label="Comentários consulta"></v-text-field>
      </div>
      <div>
        <v-btn @click="submitForm">Enviar</v-btn>
      </div>
    </div>
  </main>
</template>
##- Quanto a sua presença nas aulas e realização de atividades propostas, você diria que (respostas:
Muito ruim, ruim, boa, Muito boa)
<script>

import api from '../api';

export default {
  data: () => ({
    disciplineHours: '',
    disciplinePresence: '',
    anoSemestre: '',
    selectedDisciplines: null,
    selectedProfessors: null,
    reviewColors: ['red', '#DC143C', 'green', 'blue'],
    professors: [],
    disciplines: [],
    reviewTypes: [
      { name: 'Material Didático', value: '' },
      { name: 'Didática do professor', value: '' },
      { name: 'Método avaliativo', value: '' },
      { name: 'Monitoria', value: '' }
    ],
    comentariosGerais: '',
    comentariosAvaliacao: ''
  }),
  async created() {
    try {
      const response = await api.getDisciplinas();
      this.disciplines = response.data;
    } catch (error) {
      console.error('Error fetching disciplines:', error);
    }

    try {
      const response = await api.getProfessores();
      this.professors = response.data;
    } catch (error) {
      console.error('Error fetching professors:', error);
    }
  },
  methods: {
    async submitForm() {
      const formData = {
        disciplinaId: this.selectedDisciplines,
        professorId: this.selectedProfessors,
        anoSemestre: this.anoSemestre,
        materialDidatico: this.reviewTypes.find(type => type.name === 'Material Didático').value,
        didaticaProfessor: this.reviewTypes.find(type => type.name === 'Didática do professor').value,
        metodoAvaliativo: this.reviewTypes.find(type => type.name === 'Método avaliativo').value,
        monitoria: this.reviewTypes.find(type => type.name === 'Monitoria').value,
        horasSemanais: this.disciplineHours,
        presencaAtividades: this.disciplinePresence,
        comentariosGerais: this.comentariosGerais,
        comentariosAvaliacao: this.comentariosAvaliacao
      };

      console.log(formData)

      const pl = {
        "disciplinaId": 12,
        "anoSemestre": 20212,
        "materialDidatico": "Bom",
        "didaticaProfessor": "MuitoBom",
        "metodoAvaliativo": "Neutro",
        "monitoria": "Ruim",
        "comentariosGerais": "da aaaaaa aaaaaaaaaa aaaaaa asdasdasd",
        "presencaAtividades": "Bom",
        "horasSemanais": "DuasHoras"
      }

      try {
        const response = await api.postAvaliacao(formData);
        console.log('Form submitted successfully:', response.data);
      } catch (error) {
        console.error('Error submitting form:', error);
      }
    }
  }
}
</script>

<style></style>
