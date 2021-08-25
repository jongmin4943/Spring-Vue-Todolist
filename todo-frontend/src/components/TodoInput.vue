<template>
  <div>
    <input 
      type="text"
      v-model="todoInput"
      placeholder="할 일 ..."
      @keyup.enter="insertTodo"
      class="input"
    />
    <button @click="insertTodo" class="btn">등록</button>
  </div>
</template>
<script>
import {mapActions} from 'vuex'
export default {
  data(){
    return {
      todoInput:''
    }
  },
  computed: {
    currPage: function() {
      return this.$route.query.page || 1;
    },
    keyword: function() {
      return this.$route.query.keyword ?? '';
    }
  },
  methods: {
    ...mapActions(
      ['addTodo']
    ),
    insertTodo() {
      if(this.todoInput.trim()) {
        this.addTodo(this.todoInput);
        if(this.currPage != 1 || this.keyword != '') {
          this.$router.push({name: 'Todo', query: {page:1,keyword:''}})
        }
        this.todoInput = ''
      } else {
        alert("내용을 입력 해주세요")
      }
    }
  },
}
</script>
<style>
.input{
  width: 50vw;
  height: 5vh;
}
.btn{
  height: 5vh;
  margin: 5px;
}
</style>