import todoService from "../../api/service/todoService";
const todoStore = {
  state: {
    //data역할
    todoList: [{ tno: -1 }],
    pageInfo: { page: 1, keyword: "" },
    todoOnEdit: {
      tno: -1,
      title: "",
    },
    loading: false,
  },
  getters: {
    //computed역할
    getTodoList(state) {
      return state.todoList;
    },
    getPageInfo(state) {
      return state.pageInfo;
    },
    getTodoOnEdit(state) {
      return state.todoOnEdit;
    },
    getTodoInProgress(state) {
      return state.todoList.filter((todo) => !todo.done);
    },
    getCompletedTodo(state) {
      return state.todoList.filter((todo) => todo.done);
    },
    getLoading(state) {
      return state.loading;
    },
  },
  mutations: {
    // state변화, 동기 처리
    refreshTodos(state, payload) {
      state = Object.assign(state, payload); // shallow copy
      state.loading = false;
    },
    startEdit(state, payload) {
      state.todoOnEdit = state.todoList.find((todo) => {
        return todo.tno == payload;
      });
    },
    cancelEdit(state) {
      state.todoOnEdit = { tno: -1, title: "" };
    },
    initPageInfo(state, paylaod) {
      const page = paylaod.page || 1;
      const keyword = paylaod.keyword ?? "";
      state.pageInfo = { page, keyword };
    },
  },
  actions: {
    // mutation 로직처리, 비동기 처리
    async getTodos({ state, commit }, payload) {
      state.pageInfo = { ...state.pageInfo, ...payload };
      state.loading = true;
      const result = await todoService.fetchTodoList(state.pageInfo);
      commit("refreshTodos", result.data);
    },
    async addTodo({ state, commit }, payload) {
      state.loading = true;
      await todoService.insertTodo({ title: payload });
      state.pageInfo = { ...state.pageInfo, page: 1, keyword: "" };
      const result = await todoService.fetchTodoList(state.pageInfo);
      commit("refreshTodos", result.data);
    },
    async doneTodo({ state, commit }, payload) {
      state.loading = true;
      await todoService.doneTodo(payload);
      const result = await todoService.fetchTodoList(state.pageInfo);
      commit("refreshTodos", result.data);
    },
    async deleteTodos({ state, commit }, payload) {
      state.loading = true;
      await todoService.removeTodo(payload);
      const result = await todoService.fetchTodoList(state.pageInfo);
      commit("refreshTodos", result.data);
    },
    async editTodo({ state, commit }, payload) {
      state.loading = true;
      await todoService.updateTodo(payload);
      const result = await todoService.fetchTodoList(state.pageInfo);
      commit("refreshTodos", result.data);
      commit("cancelEdit");
    },
    async applyDragToTheSame({ state, commit }, payload) {
      state.loading = true;
      const { movingTodo, targetTodo } = payload;
      await todoService.changePostion(movingTodo, targetTodo.position);
      const result = await todoService.fetchTodoList(state.pageInfo);
      commit("refreshTodos", result.data);
      commit("cancelEdit");
    },
    async applyDragToTheOther({ state, commit }, payload) {
      state.loading = true;
      const { movingTodo, targetTodo } = payload;
      await todoService.changePostionAndDone(movingTodo, targetTodo.position);
      const result = await todoService.fetchTodoList(state.pageInfo);
      commit("refreshTodos", result.data);
      commit("cancelEdit");
    },
  },
};
export default todoStore;
