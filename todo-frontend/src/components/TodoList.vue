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
          <tr v-for="todo in todoList" :key="todo.tno" v-bind:class="[todo.done? doneClass:'']">
            <td>{{todo.tno}}</td>
            <td>{{todo.title}}</td>
            <td>{{todo.createdDate.substr(0,10)}} {{todo.createdDate.substr(11,8)}}</td>
            <td><input type="checkbox" v-model="checkedTodos" v-bind:value="todo.tno"></td>
            <td>
              <input type="button" value="수정" v-on:click="editTodoEmit(todo.tno)">
              <input type="button" v-bind:value="[todo.done? '취소':'완료']" v-on:click="doneTodoEmit(todo.tno)">
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="deleteBtn">
      <button @click="deleteTodosEmit">선택 삭제</button>
    </div>
  </div>
</template>
<script>

export default {
  data() { 
    return {
      doneClass: 'done',
      checkedTodos: [],
    }
  },
  props: {
    todoList: Array,
    doneTodo: Function,
    deleteTodos: Function,
  },
  methods: {
    doneTodoEmit(tno) {
      this.$emit("doneTodo",tno)
    },
    deleteTodosEmit() {
      if(this.checkedTodos.length>0) {
        this.$emit("deleteTodos",this.checkedTodos);
        this.checkedTodos=[];
      }
    },
    editTodoEmit(tno) {
      this.$emit("editTodo",tno);
    },
  }
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