import axios from "axios";

let url = "";

console.log("process.env.VUE_APP_ENDPOINT -> " + process.env.VUE_APP_ENDPOINT);
console.log("process.env.ROUTER_PREFIX -> " + process.env.ROUTER_PREFIX);
if (process.env.ROUTER_PREFIX === undefined) {
  url = process.env.VUE_APP_ENDPOINT;
} else {
  console.log("router_prefix is undefined");
  url = process.env.ROUTER_PREFIX;
}

console.log("url -> " + url);

const instance = axios.create({
  baseURL: url,
});

export default instance;
