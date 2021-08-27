import Vue from "vue";
import Router from "vue-router";
Vue.use(Router);

const Todo = () => import(/* webpackChunkName: "Todo" */ "../pages/Todo.vue");
const User = () => import(/* webpackChunkName: "User" */ "../pages/User.vue");
const LogIn = () => import(/* webpackChunkName: "LogIn" */ "../pages/LogIn.vue");
const SignUp = () => import(/* webpackChunkName: "SignUp" */ "../pages/SignUp.vue");

const isUserLoggedIn = true;

export const router = new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "Todo",
      beforeEnter: (to, from, next) => {
        isUserLoggedIn ? next() : next("/user/login");
      },
      component: Todo,
    },
    {
      path: "/user",
      name: "User",
      beforeEnter: (to, from, next) => {
        !isUserLoggedIn ? next() : next("/");
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
