<template>
  <v-container fluid>
    <v-btn class="blue white" elevation="2" outlined x-large @click="healthcheck">
      healthcheck
    </v-btn>
    <h1>This is Home.</h1>
  </v-container>
</template>

<script>
import axios from "axios";

export default {
  name: "Home",

  methods: {
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
        });
    },
  }
};
</script>
