<template>
  <div>
    <input 
      type="text"
      v-model="todoInput"
      placeholder="할 일 ..."
      v-on:keyup.enter="addTodoEmit"
      class="input"
    />
    <button v-on:click="addTodoEmit" class="btn">등록</button>
  </div>
</template>
<script>
export default {
  data(){
    return {
      todoInput:''
    }
  },
  props:{
    addTodo:Function,
  },
  methods: {
    addTodoEmit() {
      if(this.todoInput.trim()) {
        this.$emit("addTodo",this.todoInput);
        this.todoInput = ''
      } else {
        alert("내용을 입력 해주세요")
      }
    }
  },
}
</script>
<style>
.input{
  width: 50vw;
  height: 5vh;
}
.btn{
  height: 5vh;
  margin: 5px;
}
</style>