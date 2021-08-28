import { instance } from "..";

const userService = () => {
  const logIn = async (logInData) => {
    return await instance({
      url: `/api/user/login`,
      method: "post",
      data: logInData,
    });
  };

  return { logIn };
};

export default userService();
