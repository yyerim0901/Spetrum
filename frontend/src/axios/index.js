import axios from "axios";

// const baseURL = "/api";

const DEFAULT_ACCEPT_TYPE = 'application/json'
export default axios.create({
  // baseURL:"http://k5b101.p.ssafy.io:8080/api",
  baseURL:"http://localhost:8080/api",
  headers:{'Content-Type': DEFAULT_ACCEPT_TYPE,
            'Access-Control-Allow-Origin':'*',
            'X-ACCESS-TOKEN':getHeader(),
          }
});


// export function request(method, url, data) {
//   return axios({
//     method,
//     url: baseURL + url,
//     data,
//     headers: getHeader(),
//   })
//     .then((result) => result.data)
//     .catch((error) => error);
// }

function getHeader() {
  const token = localStorage.getItem("token");
  if (!token) return {};
  return {
    "X-ACCESS-TOKEN": token,
  };
}
