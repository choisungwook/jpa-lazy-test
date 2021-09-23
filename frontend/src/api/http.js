import axios from "axios";

const instance = axios.create({
  baseURL: process.env.VUE_APP_ENDPOINT,
  rejectUnauthorized: false,
});

export default instance;
