import axios from "axios";

const instance = axios.create({
  baseURL: process.env.VUE_APP_ROUTER_PREFIX,
});

export default instance;
