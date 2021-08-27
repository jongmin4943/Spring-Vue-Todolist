import Vue from "vue";
import Router from "vue-router";
Vue.use(Router);

const isUserLoggedIn = true;

export const router = new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      name: "Todo",
      beforeEnter: (to, from, next) => {
        if (isUserLoggedIn) {
          next();
        } else {
          next("/user/login");
        }
      },
      component: () => import("../pages/Todo.vue"),
    },
    {
      path: "/user",
      name: "User",
      beforeEnter: (to, from, next) => {
        if (!isUserLoggedIn) {
          next();
        } else {
          next("/user/login");
        }
      },
      component: () => import("../pages/User.vue"),
      children: [
        {
          path: ":login",
          name: "LogIn",

          component: () => import("../pages/LogIn.vue"),
        },
        {
          path: ":signup",
          name: "SignUp",
          component: () => import("../pages/SignUp.vue"),
        },
      ],
    },
    {
      path: "/*",
      redirect: { name: "Todo" },
    },
  ],
});
