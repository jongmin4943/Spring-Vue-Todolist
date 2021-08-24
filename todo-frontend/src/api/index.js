import axios from "axios";

const instance = axios.create({
  // baseURL: "http://localhost:8080/api",
  timeout: 10000,
  params: {},
});

instance.interceptors.request.use(
  (config) => {
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
    console.log(error.response);
    return Promise.reject(error);
  }
);

export default instance;