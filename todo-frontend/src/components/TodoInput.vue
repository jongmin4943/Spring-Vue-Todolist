<template>
  <div class='add_input'>
    <textarea
      v-model="todoInput"
      placeholder="할 일 ..."
      @keyup.enter="insertTodo"
      class="input"
    />
    <el-button type="primary" icon="el-icon-edit" @click="insertTodo"/>
  </div>
</template>
<script>
import {mapActions} from 'vuex'
import {getPageKeywordQuery} from '../mixins/getPageKeywordQuery'
export default {
  data(){
    return {
      todoInput:''
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
        this.$message({
          showClose: true,
          message: '할 일을 입력 해주세요.',
          type: 'error'
        });
      }
    }
  },
  mixins: [getPageKeywordQuery]
}
</script>
<style>
.input{
  width: 50vw;
  height: 5vh;
  resize: none;
}
.add_input{
  display: inline-flex;
}
</style>