<template>
  <div>
    <div>
      <ul>
        <li>
          <div class="todo_items">
            <div class="no">
              번호
            </div>
            <div class="title">
              할일
            </div>
            <div class="date">
              작성일
            </div>
            <div class="check">
              체크박스
            </div>
            <div class="option">
              옵션
            </div>
          </div>
        </li>
        <li v-for="todo in todoList" :key="todo.tno" :class="todo.done ? 'done' : ''">
          <div class="todo_items" v-if="todoOnEdit.tno != todo.tno">
            <div class="no">
              {{ todo.tno }}
            </div>
            <div class="title">
              {{ todo.title }}
            </div>
            <div class="date">{{ getFormatDate(new Date(todo.createdDate)) }}</div>
            <div class="check">
              <input type="checkbox" v-model="checkedTodos" :value="todo" />
            </div>
            <div class="option">
              <el-button
                :type="todo.done ? 'info' : 'success'"
                icon="el-icon-check"
                circle
                :value="todo.done ? '취소' : '완료'"
                @click="completeTodo(todo.tno)"
              />
              <el-button type="primary" icon="el-icon-edit" circle disabled v-if="todo.done" />
              <el-button type="primary" icon="el-icon-edit" circle @click="startEditing(todo.tno)" v-else />
            </div>
          </div>
          <div v-else>
            <TodoEditInput />
          </div>
        </li>
      </ul>
    </div>
    <div class="deleteBtn">
      <el-button type="default" round @click="showAllTodos" v-if="keyword != ''">전체 목록</el-button>
      <el-popconfirm
        confirm-button-text="삭제"
        cancel-button-text="취소"
        icon="el-icon-info"
        icon-color="red"
        title="정말 삭제하시겠습니까?"
        @confirm="removeTodos()"
      >
        <el-button type="danger" round slot="reference">선택 삭제</el-button>
      </el-popconfirm>
    </div>
  </div>
</template>
<script>
import { mapGetters, mapMutations } from "vuex";
import { mapActions } from "vuex";
import TodoEditInput from "./TodoEditInput.vue";
import { mixins } from "../mixins";

export default {
  components: { TodoEditInput },
  data() {
    return {
      checkedTodos: [],
    };
  },
  computed: {
    ...mapGetters({
      todoList: "getTodoList",
      todoOnEdit: "getTodoOnEdit",
    }),
  },
  created() {
    this.getTodos({ page: this.currPage, keyword: this.keyword });
  },
  methods: {
    ...mapActions(["doneTodo", "deleteTodos", "getTodos"]),
    ...mapMutations(["startEdit"]),
    completeTodo(tno) {
      this.doneTodo(tno);
    },
    removeTodos() {
      if (this.checkedTodos.length > 0) {
        this.deleteTodos(this.checkedTodos);
        this.checkedTodos = [];
      } else {
        this.$message({
          showClose: true,
          message: "선택 된 Todo가 없습니다.",
          type: "error",
        });
      }
    },
    startEditing(tno) {
      this.startEdit(tno);
    },
    showAllTodos() {
      this.$router.push({ name: "Todo", query: { page: 1, keyword: "" } });
      this.getTodos({ page: 1, keyword: "" });
    },
  },
  // watch: {
  //   $route(to) {
  //     this.getTodos({
  //       page:to.query.page,
  //       keyword:to.query.keyword ?? ''
  //     });
  //   }
  // },
  mixins: [mixins],
};
</script>
<style>
.done {
  text-decoration: line-through;
  background-color: #dddddd;
}
.no {
  width: 5vw;
}
.title {
  width: 55vw;
}
.date {
  width: 20vw;
}
.check {
  width: 7vw;
}
.option {
  width: 10vw;
}
.deleteBtn {
  display: flex;
  justify-content: flex-end;
}
ul {
  list-style-type: none;
}
.todo_items {
  display: flex;
}
</style>
