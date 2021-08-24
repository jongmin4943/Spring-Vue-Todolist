<template>
  <div id="app">
    <Todo/>
  </div>
</template>

<script>
import Todo from './pages/Todo.vue';

export default {
  components: { Todo },
  name: 'App',
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
