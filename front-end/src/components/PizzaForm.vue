<template>
   <form @submit.prevent="submit">
        <label for="nome">Nome</label>
        <input type="text" name="nome" id="nome" v-model="newPizza.nome">
        <br>
        <label for="descrizione">Descrizione</label>
        <input type="text" name="descrizione" id="descrizione" v-model="newPizza.descrizione">
        <br>
        <label for="url_foto">Url Foto</label>
        <input type="text" name="url_foto" id="url_foto" v-model="newPizza.url_foto">
        <br>
        <label for="prezzo">Prezzo</label>
        <input type="number" name="prezzo" id="prezzo" v-model="newPizza.prezzo">
        <br>
        <button type="submit">Invia</button>
        <button type="button"  @click="$emit('back')">
            Back
        </button>
    </form>
</template>

<script setup>
// IMPORT LIBS
import { defineEmits, ref } from 'vue';
import axios from 'axios';

// EMITS
const emits = defineEmits(["back", "created"]);

// DATA
const newPizza = ref({
    nome: " ",
    descrizione: " ",
    url_foto: " ",
    prezzo: 0
});

// FUNCTIONS
const submit = async () => {

    const data = await axios.post("http://localhost:8080/api/v1.0/pizzas", newPizza.value );

    emits("created");
    return;
}
</script>