<template>
    <div class="search">
      <input 
        type="text"
        v-model="searchInput"
        placeholder="검색어"
        @keyup.enter="searchTodo"
        class="search_input"
      />
      <el-button icon="el-icon-search" circle @click="searchTodo"/>
    </div>
</template>
<script>
import { mapActions } from 'vuex';
export default {
  data() {
    return {
      searchInput: '',
    }
  },
  methods: {
    ...mapActions(['getTodos']),
    searchTodo () {
      if(this.searchInput) {
        this.$router.push({name: 'Todo', query: {page:1,keyword:this.searchInput}});
        this.getTodos({page:1,keyword:this.searchInput})
      } else {
        this.$message({
          showClose: true,
          message: "검색어 를 입력해주세요",
          type: 'error'
        });
      }
    }
  },
}
</script>
<style>
  .search_input{
    margin: 10px;
  }
  .search {
    margin-top: 10px;
  }
</style>