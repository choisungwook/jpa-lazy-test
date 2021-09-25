import axios from "axios";

// let url = "";

// console.log("process.env.VUE_APP_ENDPOINT -> " + process.env.VUE_APP_ENDPOINT);
// console.log(
//   "process.env.VUE_APP_ROUTER_PREFIX -> " + process.env.VUE_APP_ROUTER_PREFIX
// );
// if (process.env.VUE_APP_ROUTER_PREFIX === undefined) {
//   console.log("VUE_APP_ROUTER_PREFIX is undefined");
//   url = process.env.VUE_APP_ENDPOINT;
// } else {
//   console.log("VUE_APP_ROUTER_PREFIX is !!!");
//   url = process.env.VUE_APP_ROUTER_PREFIX;
// }

// console.log("url -> " + url);

const instance = axios.create({
  baseURL: process.env.VUE_APP_ENDPOINT,
});

export default instance;
