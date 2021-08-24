<template>
  <div>
    <input 
      type="text"
      v-model="newTodo"
      placeholder="할 일 ..."
      v-on:keyup.enter="addTodo"
    />
    <button v-on:click="addTodo">입력</button>
  </div>
</template>
<script>
export default {
  data() {
    return {
      newTodo: "",
    };
  },
  methods: {
    addTodo() {
      if(this.newTodo.trim()) {
        console.log(this.newTodo)
        this.newTodo = "";
      } else {
        console.log("내용이 없다.")
      }
    }
  },
}
</script>
<style>
  
</style>