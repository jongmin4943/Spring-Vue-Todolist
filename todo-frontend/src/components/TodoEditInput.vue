<template>
  <div class="edit_input">
    <textarea v-model="todoEditInput" placeholder="할 일 ..." @keyup.enter="updateTodo" class="input" maxlength="250" />
    <el-button icon="el-icon-check" circle @click="updateTodo" />
    <el-button icon="el-icon-circle-close" @click="cancelUpdate" />
  </div>
</template>
<script>
import { mapGetters, mapMutations } from "vuex";
import { mapActions } from "vuex";
export default {
  data() {
    return {
      todoEditInput: "",
    };
  },
  computed: {
    ...mapGetters(["getTodoOnEdit"]),
  },
  methods: {
    ...mapActions(["editTodo"]),
    ...mapMutations(["cancelEdit"]),
    updateTodo() {
      if (this.todoEditInput.trim()) {
        this.editTodo({ title: this.todoEditInput, tno: this.getTodoOnEdit.tno });
      } else {
        this.$message({
          showClose: true,
          message: "할 일을 입력 해주세요.",
          type: "error",
        });
      }
    },
    cancelUpdate() {
      this.cancelEdit();
    },
  },
  created() {
    this.todoEditInput = this.getTodoOnEdit.title;
  },
};
</script>
<style>
.input {
  width: 50vw;
  height: 5vh;
  resize: none;
}
.btn {
  margin: 5px;
}
.edit_input {
  display: inline-flex;
}
</style>
