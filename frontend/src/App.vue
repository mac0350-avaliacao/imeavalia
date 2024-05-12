<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <img alt="IME logo" class="logo" src="@/assets/ime.png" width="200" height="200" />

  <nav>
    <RouterLink to="/">Home</RouterLink>
    <RouterLink to="/about">About</RouterLink>
  </nav>

  <RouterView />
</template>
