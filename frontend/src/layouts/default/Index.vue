<template>
  <v-app>
    <v-app-bar app primary dark>
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title>Springboot Lazy project</v-toolbar-title>
    </v-app-bar>

    <v-navigation-drawer app v-model="drawer">
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title class="text-h6"> 메뉴 </v-list-item-title>
          <!-- <v-list-item-subtitle> subtext </v-list-item-subtitle> -->
        </v-list-item-content>
      </v-list-item>

      <v-divider></v-divider>

      <v-list dense nav>
        <v-list-item v-for="menu in menus" :key="menu.title" link :to="menu.to">
          <v-list-item-icon>
            <v-icon>{{ menu.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ menu.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-navigation-drawer>

    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
import { mapState } from "vuex";

export default {
  name: "DefaultLayout",

  data: () => ({}),

  computed: {
    ...mapState("menu", ["menus"]),
    drawer: {
      get() {
        return this.$store.getters["menu/getDrawer"];
      },
      set(value) {
        return this.$store.dispatch("menu/toggleDrawer", value);
      },
    },
  },
};
</script>
