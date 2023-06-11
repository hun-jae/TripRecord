const hotplaceStore = {
  namespaced: true,
  state: {
    hotplace: null,
  },
  getters: {},
  mutations: {
    SET_HOT_PLACE: (state, hotplace) => {
      state.hotplace = hotplace;
    },
  },
  actions: {
    hotplaceSet({ commit }, hotplace) {
      commit("SET_HOT_PLACE", hotplace);
    },
  },
};

export default hotplaceStore;
