import Vue from "vue";
import Router from "vue-router";
import { store } from "../store";
Vue.use(Router);

const Todo = () => import(/* webpackChunkName: "Todo" */ "../pages/Todo.vue");
const User = () => import(/* webpackChunkName: "User" */ "../pages/User.vue");
const LogIn = () => import(/* webpackChunkName: "LogIn" */ "../pages/LogIn.vue");
const SignUp = () => import(/* webpackChunkName: "SignUp" */ "../pages/SignUp.vue");
const TodoCard = () => import(/* webpackChunkName: "TodoCard" */ "../components/TodoCard");

export const router = new Router({
  mode: "history",
  routes: [
    {
      path: "/todo",
      name: "Todo",
      beforeEnter: (to, from, next) => {
        store.commit("initPageInfo", to.query);
        const isUserLoggedIn = store.getters.isUserLoggedIn;
        isUserLoggedIn ? next() : next("/user/login");
      },
      component: Todo,
    },
    {
      path: "/user",
      name: "User",
      beforeEnter: (to, from, next) => {
        const isUserLoggedIn = store.getters.isUserLoggedIn;
        const pageInfo = store.getters.getPageInfo;
        !isUserLoggedIn ? next() : next(`/todo?page=${pageInfo.page}&keyword=${pageInfo.keyword}`);
      },
      component: User,
      children: [
        {
          path: ":login",
          name: "LogIn",
          component: LogIn,
        },
        {
          path: ":signup",
          name: "SignUp",
          component: SignUp,
        },
      ],
    },
    {
      path: "/todocard",
      name: "TodoCard",
      beforeEnter: (to, from, next) => {
        const isUserLoggedIn = store.getters.isUserLoggedIn;
        isUserLoggedIn ? next() : next("/user/login");
      },
      component: TodoCard,
    },
    {
      path: "/*",
      redirect: { name: "Todo" },
    },
  ],
});
