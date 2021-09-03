<template>
  <Container :group-name="groupName" :get-child-payload="getChildPayload" @drop="onDrop(ListType, $event)">
    <Draggable v-for="todo in todoList" :key="todo.order">
      <el-card shadow="hover" class="todo_card cursor">
        <div>
          {{ todo.title }}
          <el-popconfirm
            confirm-button-text="삭제"
            cancel-button-text="취소"
            icon="el-icon-info"
            icon-color="red"
            title="정말 삭제하시겠습니까?"
            @confirm="removeTodo(todo)"
          >
            <i class="el-icon-delete cursor" slot="reference" />
          </el-popconfirm>
        </div>

        <div style="padding: 14px;">
          <span style="font-size: 10px">{{ getDateType }}</span>
          <div>
            <time class="time">{{
              getFormatDate(new Date(ListType === "todoInProgress" ? todo.createdDate : todo.updatedDate))
            }}</time>
          </div>
        </div>
      </el-card>
    </Draggable>
  </Container>
</template>
<script>
import { Container, Draggable } from "vue-dndrop";
import { mapActions, mapMutations } from "vuex";
import { mixins } from "../mixins";
export default {
  name: "TodoDragContainer",
  components: { Container, Draggable },
  props: {
    groupName: String,
    getChildPayload: Function,
    todoList: Array,
    todoInProgress: Array,
    completedTodo: Array,
    ListType: String,
  },
  computed: {
    getDateType() {
      if (this.ListType === "todoInProgress") return "작성 날";
      return "수정 날";
    },
  },
  methods: {
    ...mapActions(["deleteTodos", "getTodos", "applyDragToTheSame", "applyDragToTheOther"]),
    ...mapMutations(["startEdit"]),

    removeTodo(tno) {
      this.deleteTodos([tno]);
    },

    onDrop(collection, dropResult) {
      // console.log(dropResult);
      const movingTodo = dropResult.payload;
      const targetTodo = this[collection][dropResult.addedIndex];
      if (movingTodo && targetTodo) {
        if (targetTodo.done === movingTodo.done) {
          // console.log("같은 곳 이동.");
          this.applyDragToTheSame({ movingTodo, targetTodo });
        } else {
          // console.log("다른 곳 이동.");
          this.applyDragToTheOther({ movingTodo, targetTodo });
        }
      } else if (movingTodo && dropResult.addedIndex != null) {
        // console.log("다른 곳 마지막으로 이동.");
        this.applyDragToTheOther({ movingTodo, targetTodo: { position: dropResult.addedIndex + 1 } });
      }
    },
  },
  mixins: [mixins],
};
</script>
<style></style>
