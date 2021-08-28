import Vuex from "vuex";
import Vue from "vue";
import todoStore from "./modules/todoStore";
import userStore from "./modules/userStore";

Vue.use(Vuex);

export const store = new Vuex.Store({
  modules: {
    todoStore,
    userStore,
  },
});
