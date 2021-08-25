<template>
  <div>
    <div>
      <span class="pages cursor" v-if="prev" v-on:click="movePage(start-1)">◀</span>
      <span 
      v-bind:class="[currPage != n ? 'cursor':'current', {pages:true}]"
      v-for="n in pageList" 
      :key="n"
      v-on:click="movePage(n)"
      >[{{n}}]</span>
      <span class="pages cursor" v-if="next" v-on:click="movePage(end+1)">▶</span>
    </div>
    <div class="search">
      <input 
        type="text"
        v-model="searchInput"
        placeholder="검색어"
        v-on:keyup.enter="searchTodo"
        class="search_input"
      />
      <button v-on:click="searchTodo">검색</button>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      searchInput: '',
    }
  },
  props: {
    pageInfo: Object,
  },
  computed: {
    pageList: function() {
      return this.pageInfo.pageList;
    },
    prev: function() {
      return this.pageInfo.prev;
    },
    next: function() {
      return this.pageInfo.next;
    },
    start: function() {
      return this.pageInfo.start;
    },
    end: function() {
      return this.pageInfo.end;
    },
    currPage: function() {
      return this.$route.query.page;
    },
    keyword: function() {
      return this.$route.query.keyword;
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