export const mixins = {
  computed: {
    currPage: function() {
      return this.$route.query.page || 1;
    },
    keyword: function() {
      return this.$route.query.keyword ?? "";
    },
  },
  methods: {
    getFormatDate(date) {
      let year = date.getFullYear();
      let month = 1 + date.getMonth();
      month = this.addZero(month);
      let day = date.getDate();
      day = this.addZero(day);
      let hours = date.getHours();
      hours = this.addZero(hours);
      let minutes = date.getMinutes();
      minutes = this.addZero(minutes);
      let seconds = date.getSeconds();
      seconds = this.addZero(seconds);
      return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    },
    addZero(num) {
      return num >= 10 ? num : "0" + num;
    },
  },
};
