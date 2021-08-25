<template>
  <div>
    <div>
      <table class="table">
        <thead>
          <tr>
            <th class="no">번호</th>
            <th class="title">할 일</th>
            <th class="date">작성일</th>
            <th class="check">체크박스</th>
            <th class="option">옵션</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="todo in todoList" :key="todo.tno" :class="[todo.done? doneClass:'']">
            <td>{{todo.tno}}</td>
            <td>{{todo.title}}</td>
            <td>{{todo.createdDate.substr(0,10)}} {{todo.createdDate.substr(11,8)}}</td>
            <td><input type="checkbox" v-model="checkedTodos" :value="todo.tno"></td>
            <td>
              <input type="button" value="수정">
              <input type="button" :value="[todo.done? '취소':'완료']" @click="completeTodo(todo.tno)">
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="deleteBtn">
      <button @click="removeTodos">선택 삭제</button>
    </div>
  </div>
</template>
<script>
import {mapGetters} from 'vuex';
import {mapActions} from 'vuex'
export default {
  data() { 
    return {
      doneClass: 'done',
      checkedTodos: [],
    }
  },
  computed: {
    ...mapGetters({
      todoList: 'getTodoList',
    }),
    currPage: function() {
      return this.$route.query.page || 1;
    },
    keyword: function() {
      return this.$route.query.keyword ?? '';
    }
  },
  created() {
    this.getTodos({page:this.currPage,keyword:this.keyword});
  },
  methods: {
    ...mapActions(['doneTodo','deleteTodos','getTodos']),
    completeTodo(tno) {
      this.doneTodo(tno)
    },
    removeTodos() {
      if(this.checkedTodos.length > 0) {
        this.deleteTodos(this.checkedTodos);
        this.checkedTodos = [];
      }
    }
  },
  watch: {
    $route(to) {
      this.getTodos({
        page:to.query.page,
        keyword:to.query.keyword ?? ''
      });
    }
  },
}
</script>
<style>
.table{
  width: 100%;
  border-collapse: collapse;
}
td { 
  vertical-align: middle;
  word-break: break-all;
}
.done {
  text-decoration: line-through;
  background-color: #dddddd;
}
.no{
  width: 5vw;
}
.title{
  width: 55vw;
}
.date{
  width: 20vw;
}
.check{
  width: 7vw;
}
.option{
  width: 10vw;
}
.deleteBtn{
  display: flex;
  justify-content: flex-end;
}
</style>