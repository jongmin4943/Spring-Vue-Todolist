import Vuex from 'vuex'
import Vue from 'vue'
import todoService from '../api/service/todoService'

Vue.use(Vuex)

export const store = new Vuex.Store({

    state:{ //data역할
        todoData: {
            todoList : [],
            pageInfo: {page:1,keyword:'',end:1,totalCount:0},
        }
    },
    getters:{ //computed역할
        getTodoList(state) {
            return state.todoData.todoList
        },
        getPageInfo(state) {
            return state.todoData.pageInfo
        },
    },
    mutations:{ // state변화, 동기 처리
        async refreshTodos(state) {
            const result = await todoService.fetchTodoList(state.todoData.pageInfo);
            state.todoData = result.data;
        },
    },
    actions:{ // mutation 로직처리, 비동기 처리
        async getTodos(context,payload) {
            context.state.todoData.pageInfo.page = payload.page || 1;
            context.state.todoData.pageInfo.keyword = payload.keyword ?? '';
            context.commit('refreshTodos'); //mutation 동작
        },
        async addTodo(context,payload) {
            await todoService.insertTodo({title:payload});
            context.commit('refreshTodos') 
        },
        async doneTodo(context,payload) {
            await todoService.doneTodo(payload);
            context.commit('refreshTodos');
        },
        async deleteTodos(context,payload) {
            await todoService.removeTodo(payload);
            context.commit('refreshTodos');
        },
    },
})