<template>
  <div class="header">
    <h1>{{ this.$store.getters.getUserData.username }} 의 Todo - List</h1>
    <el-button class="logout_btn" type="info" plain @click="userLogOut">로그아웃</el-button>
  </div>
</template>
<script>
import { mapMutations } from "vuex";
export default {
  methods: {
    ...mapMutations(["userLogOut"]),
  },
};
</script>
<style>
.header {
  display: inline-flex;
}
.logout_btn {
  margin: 25px;
  justify-content: end;
}
</style>
