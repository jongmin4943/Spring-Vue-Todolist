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
  },
  mutations: {
    // state변화, 동기 처리
    refreshTodos(state, payload) {
      state = Object.assign(state, payload); // shallow copy
    },
    startEdit(state, payload) {
      state.todoOnEdit = state.todoList.find((todo) => {
        return todo.tno == payload;
      });
    },
    cancelEdit(state) {
      state.todoOnEdit = { tno: -1, title: "" };
    },
  },
  actions: {
    // mutation 로직처리, 비동기 처리
    async getTodos({ state, commit }, payload) {
      state.pageInfo = { ...state.pageInfo, ...payload };
      const result = await todoService.fetchTodoList(state.pageInfo);
      console.log(result.data);
      commit("refreshTodos", result.data);
    },
    async addTodo({ state, commit }, payload) {
      await todoService.insertTodo({ title: payload });
      state.pageInfo = { ...state.pageInfo, page: 1, keyword: "" };
      const result = await todoService.fetchTodoList(state.pageInfo);
      commit("refreshTodos", result.data);
    },
    async doneTodo({ state, commit }, payload) {
      await todoService.doneTodo(payload);
      const result = await todoService.fetchTodoList(state.pageInfo);
      commit("refreshTodos", result.data);
    },
    async deleteTodos({ state, commit }, payload) {
      await todoService.removeTodo(payload);
      const result = await todoService.fetchTodoList(state.pageInfo);
      commit("refreshTodos", result.data);
    },
    async editTodo({ state, commit }, payload) {
      await todoService.updateTodo(payload);
      const result = await todoService.fetchTodoList(state.pageInfo);
      commit("refreshTodos", result.data);
      commit("cancelEdit");
    },
  },
};
export default todoStore;
