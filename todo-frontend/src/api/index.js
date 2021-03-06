import axios from "axios";
import { Notification } from "element-ui";
export const instance = axios.create({
  // baseURL: "http://localhost:8080/api",
  timeout: 10000,
  params: {},
});

instance.interceptors.request.use(
  (config) => {
    const userData = JSON.parse(localStorage.getItem("userData"));
    localStorage.setItem("lastActiveTime", JSON.stringify(new Date()));
    config.headers.Authorization = `Bearer ${userData?.accessToken || ""}`;
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);
instance.interceptors.response.use(
  (config) => {
    return config;
  },
  (error) => {
    console.log(error);
    Notification.error(error.response.data.error || "오류");
    return Promise.reject(error);
  }
);
