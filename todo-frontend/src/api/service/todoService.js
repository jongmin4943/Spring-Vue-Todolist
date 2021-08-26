import {instance} from "..";

const todoService = () => {

  const fetchTodoList = async (pageData) => {
    const page = pageData.page && pageData.page > 0? pageData.page : 1;
    const keyword = pageData.keyword ?? '';
    return await instance({
      url: `/api/todo/?page=${page}&keyword=${keyword}`,
      method: 'get'
    });
  }

  const fecthTodo = async (tno) => {
    return await instance({
      url: `/api/todo/${tno}`,
      method: 'get'
    });
  }

  const insertTodo = async (data) => {
    return await instance({
      url: "/api/todo/",
      method: 'post',
      data: data
    })
  }
  const updateTodo = async (data) => {
    return await instance({
      url: "/api/todo/",
      method: 'put',
      data: data
    })
  }
  const doneTodo = async (tno) => {
    return await instance({
      url: `/api/todo/${tno}`,
      method: 'put'
    })
  }
  const removeTodo = async (tno=[]) => {
    return await instance({
      url: "/api/todo/",
      method: 'delete',
      data: {tnos:tno},
    })
  }

  
  return {fetchTodoList,fecthTodo,insertTodo,updateTodo,doneTodo,removeTodo,}

}

export default todoService();