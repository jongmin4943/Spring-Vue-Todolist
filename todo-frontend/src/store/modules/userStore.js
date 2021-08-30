import userService from "../../api/service/userService.js";
import { router } from "../../router";
let refreshToken = null;
const refreshTime = 1000 * 60 * 15;
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
      localStorage.setItem("userData", JSON.stringify(payload));
      localStorage.setItem("lastActiveTime", JSON.stringify(new Date()));
      state.isUserLoggedIn = true;
      state.userData.username = payload.username;
      router.history.current.name !== "Todo" && router.push({ name: "Todo" });
    },
    userLogOut(state) {
      state.userData = { username: "" };
      state.isUserLoggedIn = false;
      localStorage.clear();
      refreshToken && clearInterval(refreshToken);
      router.history.current.name !== "LogIn" && router.push({ path: "/user/login" });
    },
  },
  actions: {
    async logIn({ commit, dispatch }, payload) {
      const result = await userService.logIn(payload);
      refreshToken = setInterval(() => dispatch("silenceRefresh"), refreshTime);
      commit("userLogIn", result.data);
    },
    async silenceRefresh({ commit }) {
      const userData = JSON.parse(localStorage.getItem("userData"));
      const result = await userService.refreshToken(userData);
      commit("userLogIn", result.data);
    },
    startRefresh({ dispatch }) {
      refreshToken = setInterval(() => dispatch("silenceRefresh"), refreshTime);
    },
  },
};
export default userStore;
