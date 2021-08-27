export const getPageKeywordQuery = {
    computed: {
        currPage: function() {
          return this.$route.query.page || 1;
        },
        keyword: function() {
          return this.$route.query.keyword ?? '';
        }
    },
}