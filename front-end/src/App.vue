<script setup>
// IMPORT LIBS
import { onMounted, ref } from 'vue';
import axios from 'axios';

// IMPORT COMPONENTS
import PizzaIndex from './components/PizzaIndex.vue';
import PizzaShow from './components/PizzaShow.vue';
import PizzaForm from './components/PizzaForm.vue';

// DATA
const pizzas = ref(null);
const pizzaActive = ref(null); 
const creatingPizza = ref(false);

// FUNCTIONS
const updatePizzas = async () => {
  const data = await axios.get("http://localhost:8080/api/v1.0/pizzas");
  pizzas.value = data.data;
};

const openPizza = (id) => {
  pizzas.value.forEach((pizza) => {
    if (pizza.id === id) {
      pizzaActive.value = pizza;
    }
  });
};

const closePizza = (update) => {
  pizzaActive.value = null;
  if (update) updatePizzas();
};

const pizzaCreated = () => {
  creatingPizza.value = false;
  updatePizzas();
};

const deletePizza = () => {
  updatePizzas();
};

// HOOKS
onMounted(updatePizzas);
</script>


<template>
  <div class="container-fluid">
    <div class="row">
      <div class="col-12" >
        <button class="btn btn-success mb-4" v-if="!creatingPizza && pizzaActive == null" @click="creatingPizza = true">
            Crea una nuova pizza
        </button>
        <pizza-form v-if="creatingPizza" @back="creatingPizza = false" @created="pizzaCreated" />
        <div v-else>
          <pizza-index v-if="pizzaActive == null" :pizzas="pizzas" @open-pizza="openPizza" @delete-pizza="deletePizza" />
          <pizza-show  v-else :pizza="pizzaActive" @close-pizza="closePizza" />
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@use './styles/generals.scss' as *;
</style>

