<template>
  <div style="display: flex; justify-content: stretch; margin-top: 50px; margin-right: 50px">
    <div class="container_wrapper">
      <h1>할 일</h1>
      <Container group-name="1" :get-child-payload="getTodoInProgressPayload" @drop="onDrop('todoInProgress', $event)">
        <Draggable v-for="todo in todoInProgress" :key="todo.order">
          <el-card shadow="hover" class="todo_card">
            {{ todo.title }}
            <i class="el-icon-delete" @click="removeTodo(todo.tno)" />
            <div style="padding: 14px;">
              <span style="font-size: 10px">작성 날</span>
              <div>
                <time class="time">{{ todo.createdDate.substr(0, 10) }} {{ todo.createdDate.substr(11, 8) }}</time>
              </div>
            </div>
          </el-card>
        </Draggable>
      </Container>
    </div>
    <div class="container_wrapper">
      <h1>완료</h1>
      <Container group-name="1" :get-child-payload="getCompletedTodoPayload" @drop="onDrop('completedTodo', $event)">
        <Draggable v-for="todo in completedTodo" :key="todo.order">
          <el-card shadow="hover" class="todo_card">
            {{ todo.title }}
            <i class="el-icon-delete" @click="removeTodo(todo.tno)" />
            <div style="padding: 14px;">
              <span style="font-size: 10px">완료 날</span>
              <div>
                <time class="time">{{ todo.updatedDate.substr(0, 10) }} {{ todo.updatedDate.substr(11, 8) }}</time>
              </div>
            </div>
          </el-card>
        </Draggable>
      </Container>
    </div>
  </div>
</template>

<script>
import { Container, Draggable } from "vue-dndrop";
import { mapActions, mapGetters, mapMutations } from "vuex";

export default {
  name: "TodoCard",

  components: { Container, Draggable },

  computed: {
    ...mapGetters(["getTodoInProgress", "getCompletedTodo", "getTodoOnEdit"]),
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
    ...mapActions(["doneTodo", "deleteTodos", "getTodos"]),
    ...mapMutations(["startEdit"]),

    removeTodo(tno) {
      this.deleteTodos([tno]);
    },

    onDrop(collection, dropResult) {
      console.log(dropResult);
      this[collection] = this.applyDrag(this[collection], dropResult);
    },

    applyDrag(arr, dragResult) {
      const { removedIndex, addedIndex, payload } = dragResult;
      if (removedIndex === null && addedIndex === null) return arr;

      const result = [...arr];
      let itemToAdd = payload;

      if (removedIndex !== null) {
        itemToAdd = result.splice(removedIndex, 1)[0];
      }

      if (addedIndex !== null) {
        result.splice(addedIndex, 0, itemToAdd);
      }
      return result;
    },

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
}
.el-card__body {
  word-break: break-all;
  width: auto;
  background-color: #64b1ff85;
}

.time {
  font-size: 10px;
  color: #999;
}
.el-icon-delete {
  cursor: pointer;
}
</style>
