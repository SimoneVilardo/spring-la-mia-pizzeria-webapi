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
    <div>
        <ul>
            <h1>Pizze</h1>
            <li 
                class="list-elem"
                v-for="pizza in pizzas" 
                :key="pizza.id"
            >
                {{ pizza.nome }}
            </li>
        </ul>
    </div>
</template>
<style scoped>
    
</style>