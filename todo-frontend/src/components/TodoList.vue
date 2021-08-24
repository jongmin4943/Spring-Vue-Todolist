<template>
  <div>
    <h1>TodoList</h1>
    <table>
      <thead>
        <tr>
          <th>번호</th>
          <th>할 일</th>
          <th>생성 한 날</th>
          <th>체크박스</th>
          <th>옵션</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="todo in todoList" :key="todo.tno">
          <td>{{todo.tno}}</td>
          <td>{{todo.title}}</td>
          <td>{{todo.createdDate.substr(0,10)}}</td>
          <td><input type="checkbox"></td>
          <td><input type="button" value="삭제"></td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script>

export default {
  props: {
    todoList: Array,
  }
}
</script>
<style>
  
</style>