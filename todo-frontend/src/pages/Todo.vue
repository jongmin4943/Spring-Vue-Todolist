<template>
  <div class = "todo">
    <TodoHeader/>
    <TodoInput
      @addTodo="addTodo"
      :editing="editing"
    />
    <TodoList
      :todoList="todoList"
      @doneTodo="doneTodo"
      @deleteTodos="deleteTodos"
      @editTodo="editTodo"
    />
    <TodoFooter
      :pageInfo="pageInfo"
    />
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
      pageInfo: {},
      editing: -1,
    };
  },
  methods: {
    async getTodos(pageData = {page:1}) {
      const result = await todoService.fetchTodoList(pageData);
      console.log(result)
      this.todoList = result.data.todoList;
      this.pageInfo = result.data.pageInfo; 
    },
    async addTodo(todoInput) {
      await todoService.insertTodo({
        title: todoInput
      })
      this.getTodos();
    },
    async doneTodo(tno) {
      await todoService.doneTodo(tno)
      this.getTodos({page:this.currPage,keyword:this.keyword});
    },
    async deleteTodos(tnos=[]) {
      await todoService.removeTodo(tnos)
      this.getTodos({page:this.currPage,keyword:this.keyword});
    },
    async editTodo(tno) {
      this.editing = tno;
    }
  },
  created() {
    this.getTodos({
      page:this.$route.query.page || 1,
      keyword:this.$route.query.keyword ?? ''
    })
  },
  watch: {
    $route(to) {
      this.getTodos({
        page:to.query.page,
        keyword:to.query.keyword ?? ''
      });
    }
  },
  computed:{
    currPage: function() {
      return this.$route.query.page || 1;
    },
    keyword: function() {
      return this.$route.query.keyword ?? '';
    }
  }
    
}
</script>
<style>
  .todo{
    margin: 10vh 10vw
  }
</style>