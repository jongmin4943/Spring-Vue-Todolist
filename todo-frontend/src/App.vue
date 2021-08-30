<template>
  <div id="app">
    <router-view />
  </div>
</template>

<script>
import { mapActions, mapMutations } from "vuex";
export default {
  name: "App",
  methods: {
    ...mapActions(["silenceRefresh", "startRefresh"]),
    ...mapMutations(["userLogOut"]),
    checkLocalStorage() {
      lastActiveTime;
      const lastActiveTime = JSON.parse(localStorage.getItem("lastActiveTime"));
      if (lastActiveTime) {
        const minute = Math.abs(new Date() - new Date(lastActiveTime)) / (1000 * 60);
        if (minute > 15) {
          this.userLogOut();
        } else {
          const userData = JSON.parse(localStorage.getItem("userData"));
          if (userData) {
            this.silenceRefresh();
            this.startRefresh();
          }
        }
      } else {
        this.userLogOut();
      }
    },
  },
  created() {
    this.checkLocalStorage();
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
