import Vuex from 'vuex'
import Vue from 'vue'
import todoService from '../api/service/todoService'

Vue.use(Vuex)

export const store = new Vuex.Store({

    state:{ //data역할
        todoData: {
            todoList : [],
            pageInfo: {page:1,keyword:''},
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
            state.todoData = await todoService.fetchTodoList(state.todoData.pageInfo);
        },
    },
    actions:{ // mutation 로직처리, 비동기 처리
        async getTodos(context,payload) {
            console.log("getTodos ", payload)
            context.commit('refreshTodos'); //mutation 동작
        },
        async addTodo(context,payload) {
            console.log("addTodo ", payload)
            const result = await todoService.insertTodo(payload);
            context.commit('refreshTodos',result) 
        },
        async doneTodo(context,payload) {
            console.log("doneTodo ", payload)
            const result = await todoService.doneTodo(payload);
            context.commit('refreshTodos',result);
        },
        async removeTodo(context,payload) {
            console.log("removeTodo ", payload)
            const result = await todoService.removeTodo(payload);
            context.commit('refreshTodos',result);
        },
    },
})