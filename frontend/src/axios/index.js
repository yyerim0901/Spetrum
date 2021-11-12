import axios from "axios"

// const baseURL = "/api";

const DEFAULT_ACCEPT_TYPE = 'application/json'
export default axios.create({
  baseURL:"http://spetrum.io:8080/api",
  // baseURL:"http://localhost:8080/api",
  headers:{'Content-Type': DEFAULT_ACCEPT_TYPE,
            'Access-Control-Allow-Origin':'*',
            'Authorization':localStorage.getItem("token"),
          }
});


// function getHeader() {
//   const token = localStorage.getItem("token");
//   if (!token) return {};
//   return {
//     token,
//   };
// }
