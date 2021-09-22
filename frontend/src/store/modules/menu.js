const state = {
  menus: [
    { title: "home", icon: "mdi-view-dashboard", to: "/vue-practice1/home" },
    { title: "helloworld", icon: "mdi-image", to: "/vue-practice1/helloworld" },
  ],
  drawer: false,
};

const getters = {
  getDrawer: (state) => state.drawer,
};

const mutations = {
  setDrawer: (state, data) => {
    state.drawer = data;
  },
};

const actions = {
  toggleDrawer({ commit }, value) {
    commit("setDrawer", value);
  },
};

export default {
  namespaced: true,
  state,
  getters,
  mutations,
  actions,
};
