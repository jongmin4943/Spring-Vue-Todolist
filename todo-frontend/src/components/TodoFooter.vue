<template>
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
</template>
<script>
import {mapGetters} from 'vuex';
export default {
 
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
    cursor: default;
  }
</style>