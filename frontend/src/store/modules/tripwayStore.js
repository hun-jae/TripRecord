const tripwayStore = {
  namespaced: true,
  state: {
    uid: null,
    title: null,
    content: null,
    attractions: [], //day, content_id, bid, idx, title(어트랙션), first_image?도 수정해야함
    statedays: 1,
    firstCall: true,
    selectedPlace: null,
    selectedDay: 0,
  },
  getters: {},
  mutations: {
    SET_UID: (state, uid) => {
      state.uid = uid;
    },
    SET_TITLE: (state, title) => {
      state.title = title;
    },
    SET_CONTENT: (state, content) => {
      state.content = content;
    },
    SET_ATTRACTIONS: (state, attractions) => {
      state.attractions.push(attractions);
    },
    RESET_ATTRACTIONS: (state) => {
      state.attractions = [];
    },
    INIT_ATTRACTIONS: (state, attractions) => {
      state.attractions = attractions;
    },
    SET_STATEDAYS: (state, statedays) => {
      state.statedays = statedays;
    },
    SET_FIRSTCALL: (state, firstCall) => {
      state.firstCall = firstCall;
    },
    SET_SELECTED_PLACE: (state, selectedPlace) => {
      state.selectedPlace = selectedPlace;
    },
    SET_SELECTED_DAY: (state, selectedDay) => {
      state.selectedDay = selectedDay;
    },
  },
  actions: {
    uidSet({ commit }, uid) {
      commit("SET_UID", uid);
    },
    titleSet({ commit }, title) {
      commit("SET_TITLE", title);
    },
    contentSet({ commit }, content) {
      commit("SET_CONTENT", content);
    },
    attractionsSet({ commit }, attraction) {
      commit("SET_ATTRACTIONS", attraction);
    },
    attractionsReset({ commit }) {
      commit("RESET_ATTRACTIONS");
    },
    attractionsInit({ commit }, attractions) {
      commit("INIT_ATTRACTIONS", attractions);
    },
    statedaysSet({ commit }, statedays) {
      commit("SET_STATEDAYS", statedays);
    },
    firstCallSet({ commit }, firstCall) {
      commit("SET_FIRSTCALL", firstCall);
    },
    selectedPlaceSet({ commit }, selectedPlace) {
      commit("SET_SELECTED_PLACE", selectedPlace);
    },
    selectedDaySet({ commit }, selectedDay) {
      commit("SET_SELECTED_DAY", selectedDay);
    },
  },
};

export default tripwayStore;
