import axios from "axios";

let url = "";
if (process.env.ROUTER_PREFIX === undefined) {
  url = process.env.VUE_APP_ENDPOINT;
} else {
  url = process.env.VUE_APP_ENDPOINT + "/" + process.env.ROUTER_PREFIX;
}

console.log("url -> " + url);

const instance = axios.create({
  baseURL: url,
});

export default instance;
