<template>
  <div>
    <TodoHeader/>
    <TodoInput/>
    <TodoList
      :todoList="todoList"
    />
    <TodoFooter/>
  </div>
</template>
<script>
import TodoInput from '../components/TodoInput.vue';
import TodoHeader from '../components/TodoHeader.vue';
import TodoFooter from '../components/TodoFooter.vue';
import TodoList from '../components/TodoList.vue';
import todoService from '../api/service/todoService';

export default {
   components: { TodoInput,TodoHeader,TodoList,TodoFooter },
   data(){
     return {
       todoList: [],
     };
   },
   async created() {
      const result = await todoService.fetchTodoList();
      this.todoList = result.data.todoList;
   }
}
</script>
<style>
  
</style>