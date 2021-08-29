import Vue from "vue";
import Router from "vue-router";
import { store } from "../store";
Vue.use(Router);

const Todo = () => import(/* webpackChunkName: "Todo" */ "../pages/Todo.vue");
const User = () => import(/* webpackChunkName: "User" */ "../pages/User.vue");
const LogIn = () => import(/* webpackChunkName: "LogIn" */ "../pages/LogIn.vue");
const SignUp = () => import(/* webpackChunkName: "SignUp" */ "../pages/SignUp.vue");

export const router = new Router({
  mode: "history",
  routes: [
    {
      path: "/todo",
      name: "Todo",
      beforeEnter: (to, from, next) => {
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
        !isUserLoggedIn ? next() : next("/todo");
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
      path: "/*",
      redirect: { name: "Todo" },
    },
  ],
});
