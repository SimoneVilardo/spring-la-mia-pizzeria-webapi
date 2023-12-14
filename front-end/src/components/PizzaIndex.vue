<script setup>
// IMPORT LIBS
import { defineProps, ref, computed } from 'vue';  
import axios from 'axios';

// DATA
const nomeFilter = ref('');

// EMITS
const emits = defineEmits(["openPizza", "deletePizza"]);

// FUNCTIONS
const deletePizza = async (id) => {
    const data = await axios.delete(
        `http://localhost:8080/api/v1.0/pizzas/${id}`
    );
    console.log("data", data);

    emits("deletePizza");
}

// COMPUTED PROPERTY
const filteredPizzas = computed(() => {
    const searchTerm = nomeFilter.value.toLowerCase().trim();
    return searchTerm ? props.pizzas.filter(pizza => pizza.nome.toLowerCase().includes(searchTerm)) : props.pizzas;
});

// PROPS
const props = defineProps({
    pizzas: {
        type: Array,
        required: true
    }
});
</script>
<template>
    <h1 class="text-center">LE MIE PIZZE</h1>
    <form class="index-filter-form py-5">
        <input type="text" placeholder="Filtra le pizze per nome" class="form-control d-inline-block" v-model="nomeFilter">
    </form>
    <table class="table table-dark table-hover index-table">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Nome</th>
                <th scope="col">Strumenti</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="pizza in filteredPizzas" :key="pizza.id">
                <th scope="row" v-text="pizza.id"></th>
                <td v-text="pizza.nome"></td>
                <td>
                    <button class="btn btn-info" @click="$emit('openPizza', pizza.id)">
                        <i class="fas fa-eye"></i>
                    </button>
                    <button class="btn btn-danger mx-2" @click="deletePizza(pizza.id)">
                        <i class="fas fa-trash"></i>
                    </button>
                </td>
            </tr>
        </tbody>
    </table>
</template>

<style lang="scss" scoped>
@use '../styles/generals.scss' as *;

.index-table {
    width: 50vw !important;
}
</style>