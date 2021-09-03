<template>
  <div id="app" v-loading="this.$store.getters.getLoading" element-loading-text="Loading..." element-loading-spinner="el-icon-loading" element-loading-background="rgba(0, 0, 0, 0.8)">
    <router-view />
    <router-link :to="{ name: 'TodoCard' }">Card</router-link>
  </div>
</template>

<script>
import { mapActions, mapMutations } from "vuex";
export default {
  name: "App",
  methods: {
    ...mapActions(["silenceRefresh", "startRefresh"]),
    ...mapMutations(["userLogOut", "initPageInfo"]),
    checkLocalStorage() {
      const lastActiveTime = JSON.parse(localStorage.getItem("lastActiveTime") || null);
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
.el-loading-mask {
  background-color: rgba(0, 0, 0, 0.5) !important;
  width: 100vw;
}
</style>
