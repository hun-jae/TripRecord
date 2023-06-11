<template>
  <b-container class="bv-example-row mt-3">
    <search-place-modal
      :value="dialog"
      @input="dialog = $event"
      :place="this.place"
    ></search-place-modal>

    <v-card>
      <v-tabs v-model="tab" grow>
        <v-tab v-for="item in items" :key="item.name" style="font-size: 1.3em; font-weight: 800">
          {{ item.name }}
        </v-tab>
      </v-tabs>
      <v-tabs-items v-model="tab">
        <v-tab-item key="전체">
          <v-card-text><search-whole @showDialog="show"></search-whole></v-card-text>
        </v-tab-item>
        <v-tab-item key="여행장소">
          <v-card-text><search-place @showDialog="show"></search-place></v-card-text>
        </v-tab-item>
        <v-tab-item key="여행일정">
          <v-card-text><search-tripway></search-tripway></v-card-text>
        </v-tab-item>
        <v-tab-item key="핫플레이스">
          <v-card-text><search-hotplace></search-hotplace></v-card-text>
        </v-tab-item>
      </v-tabs-items>
    </v-card>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import SearchWhole from "./SearchWhole.vue";
import SearchPlace from "./SearchPlace.vue";
import SearchHotplace from "./SearchHotplace.vue";
import SearchTripway from "./SearchTripway.vue";

import SearchPlaceModal from "./SearchPlaceModal.vue";

const searchStore = "searchStore";

export default {
  name: "SearchList",
  data() {
    return {
      tab: null,
      items: [{ name: "전체" }, { name: "여행장소" }, { name: "여행일정" }, { name: "핫플레이스" }],
      dialog: false,
      place: [],
    };
  },
  components: { SearchWhole, SearchPlace, SearchHotplace, SearchPlaceModal, SearchTripway },
  computed: {
    ...mapState(searchStore, ["keyword", "allPlaceData", "activeTab"]),
  },
  methods: {
    ...mapActions(searchStore, ["activeTabSet"]),
    show({ isShow, placeInfo }) {
      this.dialog = isShow;
      this.place = placeInfo;
    },
  },
  watch: {
    keyword() {
      this.tab = 0;
    },
    tab(newTab) {
      this.activeTabSet(newTab);
    },
    activeTab(newTab) {
      this.tab = newTab;
    },
  },
  created() {},
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
