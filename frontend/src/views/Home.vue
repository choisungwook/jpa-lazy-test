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
        .get("/api/health")
        .then((response) => {
          console.log(response);
          console.log(response.data);
          console.log("ping success");
          console.log("\n");
        })
        .catch((error) => {
          console.log("ping failed");
          console.log(error);
        });
    },
  }
};
</script>
