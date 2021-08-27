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
import {mapActions, mapGetters} from 'vuex';
import {getPageKeywordQuery} from '../mixins/getPageKeywordQuery'
export default {
  computed: {
    ...mapGetters(['getPageInfo']),
  },
  methods: {
    ...mapActions(['getTodos']),
    movePage (page) {
      if(this.currPage != page) {
        this.$router.push({name: 'Todo', query: {page:page,keyword:this.keyword}});
        this.getTodos({page:page})
      }
    },
  },
  mixins: [getPageKeywordQuery]
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