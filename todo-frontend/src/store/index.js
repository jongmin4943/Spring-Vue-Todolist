import Vuex from 'vuex'
import Vue from 'vue'
import todoService from '../api/service/todoService'

Vue.use(Vuex)

export const store = new Vuex.Store({

    state:{ //data역할
        todoData: {
            todoList : [{tno:-1}],
            pageInfo: {page:1,keyword:'',end:1,totalCount:0},
        },
        todoOnEdit: {
            tno: -1,
            title: ''
        }
    },
    getters:{ //computed역할
        getTodoList(state) {
            return state.todoData.todoList
        },
        getPageInfo(state) {
            return state.todoData.pageInfo
        },
        getTodoOnEdit(state) {
            return state.todoOnEdit
        }
    },
    mutations:{ // state변화, 동기 처리
        refreshTodos(state,payload) {
            state.todoData = payload;
        },
        startEdit(state,payload) {
            state.todoOnEdit = state.todoData.todoList.find(todo =>{
                return todo.tno == payload;
            })
        },
        cancelEdit(state) {
            state.todoOnEdit = { tno: -1, title: ''}
        }
    },
    actions:{ // mutation 로직처리, 비동기 처리
        async getTodos({state,commit},payload) {
            state.todoData.pageInfo.page = payload.page || 1;
            state.todoData.pageInfo.keyword = payload.keyword ?? '';
            const result = await todoService.fetchTodoList(state.todoData.pageInfo);
            commit('refreshTodos',result.data);
        },
        async addTodo({state,commit},payload) {
            await todoService.insertTodo({title:payload});
            const result = await todoService.fetchTodoList(state.todoData.pageInfo);
            commit('refreshTodos',result.data);
        },
        async doneTodo({state,commit},payload) {
            await todoService.doneTodo(payload);
            const result = await todoService.fetchTodoList(state.todoData.pageInfo);
            commit('refreshTodos',result.data);
        },
        async deleteTodos({state,commit},payload) {
            await todoService.removeTodo(payload);
            const result = await todoService.fetchTodoList(state.todoData.pageInfo);
            commit('refreshTodos',result.data);
        },
        async editTodo({state,commit},payload) {
            await todoService.updateTodo(payload);
            const result = await todoService.fetchTodoList(state.todoData.pageInfo);
            commit('refreshTodos',result.data);
            commit('cancelEdit');
        }
    },
})