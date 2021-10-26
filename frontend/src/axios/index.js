import axios from "axios";

const baseURL = "/api";

export function request(method, url, data) {
  return axios({
    method,
    url: baseURL + url,
    data,
    headers: getHeader(),
  })
    .then((result) => result.data)
    .catch((error) => error);
}

function getHeader() {
  const token = localStorage.getItem("token");
  if (!token) return {};
  return {
    "X-ACCESS-TOKEN": token,
  };
}
