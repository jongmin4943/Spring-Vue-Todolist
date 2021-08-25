<template>
  <div class = "todo">
    <TodoHeader/>
    <TodoInput/>
    <TodoList/>
    <TodoFooter/>
  </div>
</template>
<script>
import TodoInput from '../components/TodoInput.vue';
import TodoHeader from '../components/TodoHeader.vue';
import TodoFooter from '../components/TodoFooter.vue';
import TodoList from '../components/TodoList.vue';

export default {
  components: { TodoInput,TodoHeader,TodoList,TodoFooter },
}
</script>
<style>
  .todo{
    margin: 10vh 10vw
  }
</style>