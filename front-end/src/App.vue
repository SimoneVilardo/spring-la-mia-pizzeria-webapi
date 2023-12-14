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
  <div>
    <pizza-index :pizzas="pizzas"/>
  </div>
</template>
