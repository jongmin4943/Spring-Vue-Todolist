<template>
  <div class="todo">
    <TodoHeader />
    <TodoInput />
    <TodoList />
    <TodoFooter />
    <TodoSearch />
  </div>
</template>
<script>
import TodoInput from "../components/TodoInput.vue";
import TodoHeader from "../components/TodoHeader.vue";
import TodoFooter from "../components/TodoFooter.vue";
import TodoList from "../components/TodoList.vue";
import TodoSearch from "../components/TodoSearch.vue";

export default {
  components: { TodoInput, TodoHeader, TodoList, TodoFooter, TodoSearch },
};
</script>
<style>
.todo {
  margin: 10vh 10vw;
}
</style>
