import Vue from 'vue'
import Router from 'vue-router'
import Todo from '../pages/Todo.vue'
Vue.use(Router)
export default new Router({
    mode: 'history',
    routes: [
        {
            path: "/",
            name: "Todo",
            component: Todo,
        },
    ]
})