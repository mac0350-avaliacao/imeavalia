<template>
  <div class="main">
    <img alt="IME logo" class="logo" src="@/assets/ime.png" width="200" height="200" />
    <RouterView />
  </div>
</template>

<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<style>

.main {
  display: flex;
  align-items: center;
  flex-direction: column;
}

</style>
