<template>
  <el-row>
    <el-col :span="8">
      <div class="blank"></div>
    </el-col>

    <el-col :span="8">
      <div class="grid-content bg-purple-light">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px">
          <el-form-item label="아이디" prop="username">
            <el-input v-model="ruleForm.username"></el-input>
          </el-form-item>
          <el-form-item label="비밀번호" prop="password">
            <el-input v-model="ruleForm.password" type="password"></el-input>
          </el-form-item>
          <el-form-item label="비밀번호 확인" prop="passwordCheck">
            <el-input v-model="ruleForm.passwordCheck" type="password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="userSignUp">회원가입</el-button>
            <el-button @click="goLogin">돌아가기</el-button>
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
import userService from "../api/service/userService";
export default {
  data() {
    const validatePassword = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("비밀번호 확인을 입력해주세요."));
      } else if (value !== this.ruleForm.password) {
        callback(new Error("비밀번호가 일치하지 않습니다."));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        username: "",
        password: "",
        passwordCheck: "",
      },
      rules: {
        username: [{ required: true, message: "아이디를 입력해주세요", trigger: "blur" }],
        password: [{ required: true, message: "비밀번호를 입력해주세요.", trigger: "blur" }],
        passwordCheck: [{ required: true, validator: validatePassword, trigger: "blur" }],
      },
    };
  },
  methods: {
    goLogin() {
      this.$router.push({ path: "/user/login" });
    },
    userSignUp() {
      if (this.ruleForm.username.trim() && this.ruleForm.password && this.ruleForm.passwordCheck) {
        userService.signUp(this.ruleForm);
        this.$message({
          showClose: true,
          message: "회원가입 되었습니다",
          type: "success",
        });
        this.$router.push({ path: "/user/login" });
      } else {
        this.$message({
          showClose: true,
          message: "필수 항목을 입력해주세요.",
          type: "error",
        });
      }
    },
  },
};
</script>
<style></style>
