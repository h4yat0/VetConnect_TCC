import axios from "axios";
const BASE_URL = "http://localhost:9191/";

const api = axios.create({
  baseURL: BASE_URL,
});

export const axiosPrivate = axios.create({
  baseURL: BASE_URL,
  headers:{'Content-Type': 'application/json'},
  withCredentials: true
});

export default api;
