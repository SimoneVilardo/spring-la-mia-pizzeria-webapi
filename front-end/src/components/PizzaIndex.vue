<script setup>
import { defineProps } from 'vue';  

const props = defineProps({
    pizzas: {
        type: Array,
        required: true
    }
});
</script>
<template>
    <table class="table table-dark table-hover index-table">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Nome</th>
                <th scope="col">Strumenti</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="pizza in pizzas" :key="pizza.id">
                <th scope="row" v-text="pizza.id"></th>
                <td v-text="pizza.nome"></td>
                <td>
                    <button class="btn btn-info">
                        <i class="fas fa-eye"></i>
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