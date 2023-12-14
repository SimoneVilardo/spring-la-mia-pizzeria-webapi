<script setup>
// PROPS
const props = defineProps({
    pizza: {
        type: Object,
        required: true
    }
});

// EMITS
const emits = defineEmits(["closePizza"]);
</script>

<template>
    <div class="show-background p-5">
        <div class="text-center  pb-5">
            <button class="link btn btn-danger" @click="$emit('closePizza')">
                X
            </button>
        </div>
        <img :src="pizza.url_foto" class="rounded">
        <div class="p-3">
            <h5 v-text="pizza.nome" class="card-title my-3"></h5>
            <p v-text="pizza.descrizione" class="card-text my-3"></p>
            <span v-text="pizza.prezzo" class="d-block my-3"></span>
        </div>
    </div>
</template>

<style lang="scss" scoped>
@use '../styles/generals.scss' as *;

.show-background {
    background-color: white;
    color: black;
    border: 1px solid black;
    border-radius: 10px !important;
}


</style>