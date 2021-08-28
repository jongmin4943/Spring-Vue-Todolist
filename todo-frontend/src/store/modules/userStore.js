import userService from "../../api/service/userService.js";
import { router } from "../../router";

const userStore = {
  state: {
    userData: {
      username: "",
    },
    isUserLoggedIn: false,
  },
  getters: {
    getUserData(state) {
      return state.userData;
    },
    isUserLoggedIn(state) {
      return state.isUserLoggedIn;
    },
  },
  mutations: {
    userLogIn(state, payload) {
      localStorage.setItem("accessToken", JSON.stringify(payload.accessToken));
      state.isUserLoggedIn = true;
      router.push({ name: "Todo" });
    },
    userLogOut(state) {
      state.userData = { username: "" };
      state.isUserLoggedIn = false;
    },
  },
  actions: {
    async logIn({ commit }, payload) {
      console.log("log In Data : ", payload);
      const result = await userService.logIn(payload);
      console.log("User Data : ", result.data);
      commit("userLogIn", result.data);
    },
  },
};
export default userStore;
