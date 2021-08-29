import { instance } from "..";

const userService = () => {
  const logIn = async (logInData) => {
    return await instance({
      url: `/api/user/login`,
      method: "post",
      data: logInData,
    });
  };

  const signUp = async (signUpData) => {
    return await instance({
      url: `/api/user/register`,
      method: "post",
      data: signUpData,
    });
  };

  const refreshToken = async (userData) => {
    return await instance({
      url: `/api/user/refresh`,
      method: "post",
      data: userData,
    });
  };

  return { logIn, signUp, refreshToken };
};

export default userService();
