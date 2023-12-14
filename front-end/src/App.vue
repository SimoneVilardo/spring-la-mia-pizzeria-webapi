<script setup>
import { onMounted, ref } from 'vue';
import axios from 'axios';

import PizzaIndex from './components/PizzaIndex.vue';

const pizzas = ref(null);

const updatePizzas = async () => {
  const data = await axios.get("http://localhost:8080/api/v1.0/pizzas");
  pizzas.value = data.data;
};

onMounted(updatePizzas);
</script>


<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col-12">
        <pizza-index :pizzas="pizzas" />
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@use './styles/generals.scss' as *;
</style>

