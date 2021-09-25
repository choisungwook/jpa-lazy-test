import axios from "axios";

let url = "";
if (process.env.VUE_APP_ROUTER_PREFIX === undefined) {
  url = process.env.VUE_APP_ENDPOINT;
} else {
  url = process.env.VUE_APP_ROUTER_PREFIX;
}

const instance = axios.create({
  baseURL: url,
});

export default instance;
