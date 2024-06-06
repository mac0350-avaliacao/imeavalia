<template>
  <div id="app">
    <h1>Disciplinas</h1>
    <ul>
      <li v-for="disciplina in disciplinas" :key="disciplina.id">
        {{ disciplina.name }} ({{ disciplina.sigla }})
      </li>
    </ul>
  </div>
</template>

<script>
import api from './api';

export default {
  name: 'App',
  data() {
    return {
      disciplinas: []
    };
  },
  created() {
    this.fetchDisciplinas();
  },
  methods: {
    async fetchDisciplinas() {
      try {
        const response = await api.getDisciplinas();
        this.disciplinas = response.data;
      } catch (error) {
        console.error('Error fetching disciplinas:', error);
      }
    }
  }
};
</script>

<style>
/* Add any styles you need here */
</style>
