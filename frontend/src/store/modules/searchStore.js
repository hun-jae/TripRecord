import { searchList } from "@/api/trip";
import { search } from "@/api/hotplace";
import { searchTripway } from "@/api/tripway";
const searchStore = {
  namespaced: true,
  state: {
    keyword: null,
    activeTab: 0,
    allPlaceData: [],
    allHotplaceData: [],
    nowPlace: [],
    allTripwayData: [],
  },
  getters: {},
  mutations: {
    SET_KEYWORD: (state, keyword) => {
      state.keyword = keyword;
    },
    SET_ALL_PLACE_DATA: (state, data) => {
      state.allPlaceData = data;
    },
    SET_ALL_HOT_PLACE_DATA: (state, data) => {
      state.allHotplaceData = data;
    },
    SET_ACTIVE_TAB: (state, tab) => {
      state.activeTab = tab;
    },
    SET_NOW_PLACE: (state, data) => {
      state.nowPlace = data;
    },
    SET_ALL_TRIPWAY_DATA: (state, data) => {
      state.allTripwayData = data;
    },
  },
  actions: {
    async keywordSet({ commit }, keyword) {
      console.log("키워드변경 " + keyword);
      let param = {
        sido_code: "",
        keyword: keyword,
        content_type_id: "",
      };
      await searchList(
        // 여행장소 업데이트
        param,
        ({ data }) => {
          commit("SET_ALL_PLACE_DATA", data);
          commit("SET_KEYWORD", keyword);
        },
        (error) => {
          console.log(error);
        }
      );
      param = {
        sido_code: "",
        keyword: keyword,
        content_type_id: "",
      };
      // 여행일정 업데이트
      await searchTripway(
        keyword,
        ({ data }) => {
          commit("SET_ALL_TRIPWAY_DATA", data);
        },
        (error) => {
          console.log(error);
        }
      ); // 핫플레이스 업데이트
      await search(
        keyword,
        ({ data }) => {
          commit("SET_ALL_HOT_PLACE_DATA", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    activeTabSet({ commit }, tab) {
      commit("SET_ACTIVE_TAB", tab);
    },
    nowPlaceSet({ commit }, place) {
      commit("SET_NOW_PLACE", place);
    },
  },
};

export default searchStore;
