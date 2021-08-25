<template>
  <div>
    <div>
      <span class="pages cursor" v-if="getPageInfo.prev" @click="movePage(getPageInfo.start-1)">◀</span>
      <span 
      :class="[currPage != n ? 'cursor':'current', {pages:true}]"
      v-for="n in getPageInfo.pageList" 
      :key="n"
      @click="movePage(n)"
      >[{{n}}]</span>
      <span class="pages cursor" v-if="getPageInfo.next" @click="movePage(getPageInfo.end+1)">▶</span>
    </div>
    <div class="search">
      <input 
        type="text"
        v-model="searchInput"
        placeholder="검색어"
        @keyup.enter="searchTodo"
        class="search_input"
      />
      <button @click="searchTodo">검색</button>
    </div>
  </div>
</template>
<script>
import {mapGetters} from 'vuex';
export default {
  data() {
    return {
      searchInput: '',
    }
  },
  computed: {
    ...mapGetters({
      getPageInfo : 'getPageInfo'
    }),
    currPage: function() {
      return this.$route.query.page || 1;
    },
    keyword: function() {
      return this.$route.query.keyword ?? '';
    }
  },
  methods: {
    movePage (page) {
      if(this.currPage != page) {
        this.$router.push({name: 'Todo', query: {page:page,keyword:this.keyword}})
      }
    },
    searchTodo () {
      if(this.searchInput) {
        this.$router.push({name: 'Todo', query: {page:1,keyword:this.searchInput}})
      } else {
        alert("검색어 를 입력해주세요")
      }
    }
  },
}
</script>
<style>
  .pages{
    padding: 5px;
  }
  .cursor{
    cursor: pointer;
  }
  .current {
    background-color: bisque;
    border-radius: 40%;
  }
  .search_input{
    margin: 10px;
  }
  .search {
    margin-top: 10px;
  }

</style>