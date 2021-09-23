<template>
  <v-container fluid>
    <v-btn class="blue white" elevation="2" outlined x-large @click="elb_healthcheck">
      elb healthcheck
    </v-btn>
    <v-btn class="blue white" elevation="2" outlined x-large @click="healthcheck">
      normal healthcheck
    </v-btn>
    <h1>This is Home.</h1>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name: "Home",

  methods: {
    elb_healthcheck() {
      axios
        .get(
          process.env.VUE_APP_ENDPOINT + "/health",
          {},
          {
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json; charset = utf-8",
          }
        )
        .then((response) => {
          console.log("endpoint -> " + process.env.VUE_APP_ENDPOINT);
          console.log(response.data);
          console.log("ping success");
          console.log("\n");
        })
        .catch((error) => {
          console.log("endpoint -> " + process.env.VUE_APP_ENDPOINT);
          console.log("ping failed");
          console.log(error);
        });
    },
    healthcheck() {
      axios
        .get(
          // process.env.VUE_APP_ENDPOINT + "/health",
          "https://jpaschool.choicloudlab.com/health",
          {},
          {
            "Access-Control-Allow-Origin": "*",
            "Content-Type": "application/json; charset = utf-8",
          }
        )
        .then((response) => {
          console.log("endpoint -> " + process.env.VUE_APP_ENDPOINT);
          console.log(response.data);
          console.log("ping success");
          console.log("\n");
        })
        .catch((error) => {
          console.log("endpoint -> " + process.env.VUE_APP_ENDPOINT);
          console.log("ping failed");
          console.log(error);
          console.log(error.request);
          console.log(error.message);
          console.log(error.config);
        });
    },
  }
};
</script>
