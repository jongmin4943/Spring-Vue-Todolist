<template>
  <el-row>
    <el-col :span="8">
      <div class="blank"></div>
    </el-col>

    <el-col :span="8">
      <div class="grid-content">
        <el-form :model="form" ref="form" label-width="120px">
          <el-form-item label="아이디" prop="id">
            <el-input v-model="form.id"></el-input>
          </el-form-item>
          <el-form-item label="비밀번호" prop="password">
            <el-input v-model="form.id"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary">로그인</el-button>
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
export default {
  data() {
    return {
      form: {
        id: "",
        password: "",
      },
    };
  },
  methods: {
    goSignUp() {
      this.$router.push({ name: "SignUp" });
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
