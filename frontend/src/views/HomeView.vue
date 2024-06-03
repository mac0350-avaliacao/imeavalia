<script setup></script>

<template>
  <main>
    <div>
      <div class="disciplineSelect">
        <v-select
          v-model="selectedDisciplines"
          :items="disciplines"
          hint="Selecione todas as matérias que você irá avaliar"
          label="Selecione as disciplinas"
          multiple
          persistent-hint
        ></v-select>
      </div>
      <div class="disciplineReview">
        <div v-for="type in reviewTypes" :key="type">
          <p>Avaliação do {{ type.name }}: {{ type.value }}</p>
          <v-radio-group v-model="type.value">
            <v-radio :color="reviewColors[0]" label="Muito ruim" value="muito ruim"></v-radio>
            <v-radio :color="reviewColors[1]" label="Ruim" value="ruim"></v-radio>
            <v-radio label="Neutro" value="neutro"></v-radio>
            <v-radio :color="reviewColors[2]" label="Bom" value="bom"></v-radio>
            <v-radio :color="reviewColors[3]" label="Muito bom" value="muito bom"></v-radio>
          </v-radio-group>
        </div>
      </div>
      <div>
        <p>
          Quantas horas por semana você dedica ao estudo dessa disciplina? <br />
        </p>
        <v-radio-group v-model="disciplineHours">
          <v-radio label="1h ou menos" value="1 horas ou menos"></v-radio>
          <v-radio label="2h" value="2 horas"></v-radio>
          <v-radio label="3 horas" value="3 horas"></v-radio>
          <v-radio label="4 horas ou mais" value="4 horas ou mais"></v-radio>
        </v-radio-group>
      </div>
      <div>
        <p>
          Quanto a sua presença nas aulas e realização de atividades propostas, você diria que
          <br />
        </p>
        <v-radio-group v-model="disciplinePresence">
          <v-radio label="muito ruim" value="muito ruim"></v-radio>
          <v-radio label="ruim" value="ruim"></v-radio>
          <v-radio label="boa" value="boa"></v-radio>
          <v-radio label="muito boa" value="muito boa"></v-radio>
        </v-radio-group>
      </div>
      <div>
        <v-text-field v-model="comentariosGerais" label="Comentários gerais"></v-text-field>
      </div>
      <div>
        <v-text-field v-model="comentariosAvaliacao" label="Comentários consulta"></v-text-field>
      </div>
    </div>
  </main>
</template>
##- Quanto a sua presença nas aulas e realização de atividades propostas, você diria que (respostas:
Muito ruim, ruim, boa, Muito boa)
<script>
export default {
  data: () => ({
    disciplineHours: '',
    disciplinePresence: '',
    selectedDisciplines: [],
    reviewColors: ['red', '#DC143C', 'green', 'blue'],
    disciplines: [
      'MAC0101 	Integração na Universidade e na Profissão',
      'MAC0105 	Fundamentos de Matemática para a Computação',
      'MAC0110 	Introdução à Computação',
      'MAC0329 	Álgebra Booleana e Aplicações no Projeto de Arquitetura de Computadores',
      'MAT2453 	Cálculo Diferencial e Integral I',
      'MAT0112 	Vetores e Geometria'
    ],
    reviewTypes: [
      { name: 'Material Didático', value: '' },
      { name: 'Didática do professor', value: '' },
      { name: 'Método avaliativo', value: '' },
      { name: 'Monitoria', value: '' }
    ],
    comentariosGerais: '',
    comentariosAvaliacao: ''
  })
}
</script>

<style></style>
