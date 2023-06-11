import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";
// module import
import tripwayStore from "@/store/modules/tripwayStore";
import memberStore from "@/store/modules/memberStore";
import todoStore from "@/store/modules/todoStore";
import searchStore from "@/store/modules/searchStore";
import hotplaceStore from "@/store/modules/hotplaceStore";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    tripwayStore,
    memberStore,
    todoStore,
    searchStore,
    hotplaceStore,
  },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});
