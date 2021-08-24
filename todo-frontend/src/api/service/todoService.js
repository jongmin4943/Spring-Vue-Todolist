import instance from "..";

const todoService = () => {

  const fetchTodoList = async () => {
    return await instance({
      url: "/api/todo/",
      method: 'get'
    });
  }

  
  return {fetchTodoList}

}

export default todoService();