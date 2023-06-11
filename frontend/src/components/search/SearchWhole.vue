<template>
  <v-container fluid class="text-left">
    <!-- <search-place-modal
      :value="dialog"
      @input="dialog = $event"
      :place="this.nowPlace"
    ></search-place-modal> -->

    <h2 style="margin-top: 5%; font-weight: 700">여행장소(전체 {{ this.placeCnt }}건)</h2>
    <hr />
    <v-row dense>
      <v-col v-for="place in places" :key="place.contentId" :cols="12" md="3">
        <search-list-item-vue
          :place="place"
          class="fill-height"
          type="place"
          from="whole"
          @input="showDialog"
        ></search-list-item-vue>
      </v-col>
    </v-row>
    <v-row class="text-center" align="center" justify="center">
      <v-col><v-btn @click="changeTab(1)">더보기</v-btn> </v-col>
    </v-row>

    <h2 style="margin-top: 10%; font-weight: 700">여행일정(전체 {{ this.tripwayCnt }}건)</h2>
    <hr />
    <v-row dense>
      <v-col v-for="place in tripways" :key="place.bid" :cols="12" md="3">
        <search-list-item-vue
          :place="place"
          class="fill-height"
          type="tripway"
        ></search-list-item-vue>
      </v-col>
    </v-row>
    <v-row class="text-center" align="center" justify="center">
      <v-col><v-btn @click="changeTab(2)">더보기</v-btn> </v-col>
    </v-row>

    <h2 style="margin-top: 10%; font-weight: 700">핫플레이스(전체 {{ this.hotplaceCnt }}건)</h2>
    <hr />
    <v-row dense>
      <v-col v-for="place in hotplaces" :key="place.bid" :cols="12" md="3">
        <search-list-item-vue
          :place="place"
          class="fill-height"
          type="hotplace"
        ></search-list-item-vue>
      </v-col>
    </v-row>
    <v-row class="text-center" align="center" justify="center">
      <v-col><v-btn @click="changeTab(3)">더보기</v-btn> </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import SearchListItemVue from "./SearchListItem.vue";
//import SearchPlaceModal from "./SearchPlaceModal.vue";
const searchStore = "searchStore";

export default {
  name: "SearchWhole",
  data() {
    return {
      places: [],
      placeCnt: 0,
      hotplaces: [],
      hotplaceCnt: 0,
      dialog: false,
      tripways: [],
      tripwayCnt: 0,
    };
  },
  components: { SearchListItemVue },
  computed: {
    ...mapState(searchStore, [
      "keyword",
      "activeTab",
      "allPlaceData",
      "allHotplaceData",
      "nowPlace",
      "allTripwayData",
    ]),
  },
  mounted() {},
  created() {
    this.places = this.allPlaceData.slice(0, 4);
    this.placeCnt = this.allPlaceData.length;
    this.hotplaces = this.allHotplaceData.slice(0, 4);
    this.hotplaceCnt = this.allHotplaceData.length;
    this.tripways = this.allTripwayData.slice(0, 4);
    this.tripwayCnt = this.allTripwayData.length;
  },
  methods: {
    ...mapActions(searchStore, ["activeTabSet"]),
    changeTab(tab) {
      this.activeTabSet(tab);
    },
    showDialog(value) {
      this.$emit("showDialog", value);
    },
  },
  watch: {
    keyword() {
      this.places = this.allPlaceData.slice(0, 4);
      this.placeCnt = this.allPlaceData.length;
      this.hotplaces = this.allHotplaceData.slice(0, 4);
      this.hotplaceCnt = this.allHotplaceData.length;
      this.tripways = this.allTripwayData.slice(0, 4);
      this.tripwayCnt = this.allTripwayData.length;
    },
    activeTab() {
      this.dialog = false;
    },
  },
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
