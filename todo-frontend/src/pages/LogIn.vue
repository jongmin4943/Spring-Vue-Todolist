<template>
  <el-row>
    <el-col :span="8">
      <div class="blank"></div>
    </el-col>

    <el-col :span="8">
      <div class="grid-content">
        <el-form :model="form" ref="form" label-width="120px">
          <el-form-item label="아이디" prop="username">
            <el-input v-model="form.username"></el-input>
          </el-form-item>
          <el-form-item label="비밀번호" prop="password">
            <el-input v-model="form.password" type="password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="userLogIn">로그인</el-button>
            <el-button @click="goSignUp">회원가입</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-col>

    <el-col :span="8">
      <div class="blank"></div>
    </el-col>
  </el-row>
</template>
<script>
import { mapActions } from "vuex";
export default {
  data() {
    return {
      form: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    ...mapActions(["logIn"]),
    goSignUp() {
      this.$router.push({ name: "SignUp", params: { signup: "signup" } });
    },
    userLogIn() {
      this.logIn(this.form);
    },
  },
};
</script>
<style>
.el-row {
  margin-top: 15vh;
}
.el-col {
  border-radius: 4px;
}
.grid-content {
  border-radius: 4px;
  min-height: 36px;
  background-color: #a2a2a263;
  padding: 10px;
}
.blank {
  min-height: 20px;
}
</style>
