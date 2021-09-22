const state = {
  menus: [
    { title: "학교", icon: "mdi-view-dashboard", to: "/lazy/school" },
    { title: "반", icon: "mdi-image", to: "/lazy/helloworld" },
    { title: "학생", icon: "mdi-image", to: "/lazy/home" },
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
