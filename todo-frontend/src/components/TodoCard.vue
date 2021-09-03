<template>
  <div style="display: flex; justify-content: stretch; margin-right: 50px">
    <div class="container_wrapper">
      <h1>할 일</h1>
      <TodoDragContainer
        groupName="1"
        :getChildPayload="getTodoInProgressPayload"
        :completedTodo="completedTodo"
        :todoInProgress="todoInProgress"
        :todoList="todoInProgress"
        ListType="todoInProgress"
      />
    </div>
    <div class="container_wrapper">
      <h1>완료</h1>
      <TodoDragContainer
        groupName="1"
        :getChildPayload="getCompletedTodoPayload"
        :completedTodo="completedTodo"
        :todoInProgress="todoInProgress"
        :todoList="completedTodo"
        ListType="completedTodo"
      />
    </div>
  </div>
</template>

<script>
import TodoDragContainer from "./TodoDragContainer.vue";
import { mapActions, mapGetters } from "vuex";
export default {
  name: "TodoCard",

  components: { TodoDragContainer },

  computed: {
    ...mapGetters({
      todoInProgress: "getTodoInProgress",
      completedTodo: "getCompletedTodo",
      todoOnEdit: "getTodoOnEdit",
    }),
  },

  created() {
    this.getTodos();
  },

  methods: {
    ...mapActions(["getTodos"]),

    getTodoInProgressPayload(index) {
      return this.todoInProgress[index];
    },

    getCompletedTodoPayload(index) {
      return this.completedTodo[index];
    },
  },
};
</script>
<style>
.todo_card {
  margin: 10px 5vw;
  width: 20vw;
}
.container_wrapper {
  margin-left: 50px;
  background-color: #f3f3f3;
  min-height: 30px;
  min-width: 30px;
}
.el-card__body {
  word-break: break-all;
  width: auto;
  background-color: #64b1ff85;
}
.smooth-dnd-container {
  min-height: 100vh;
  min-width: 25vw;
}
.time {
  font-size: 10px;
  color: #999;
}
.cursor {
  cursor: pointer;
}
</style>
