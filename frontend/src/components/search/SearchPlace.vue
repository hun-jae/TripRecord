<template>
  <v-container fluid class="text-left">
    <!-- <search-place-modal2
      :value="dialog2"
      @input="dialog2 = $event"
      :place="this.nowPlace"
    ></search-place-modal2> -->
    <h2 style="margin-top: 5%; font-weight: 700">전체 {{ this.placeCnt }}건</h2>
    <hr />
    <v-row dense>
      <v-col v-for="place in listPlaces" :key="place.contentId" :cols="12" md="4">
        <search-list-item-vue
          :place="place"
          class="fill-height"
          type="place"
          from="detail"
          @input="showDialog"
        ></search-list-item-vue>
      </v-col>
    </v-row>
    <v-row
      class="text-center"
      align="center"
      justify="center"
      v-show="places.length > listPlaces.length"
    >
      <v-col><v-btn @click="getMoreItem">더보기</v-btn> </v-col>
    </v-row>
  </v-container>
</template>

<script>
import { mapState } from "vuex";
import SearchListItemVue from "./SearchListItem.vue";
//import SearchPlaceModal2 from "./SearchPlaceModal2.vue";

const searchStore = "searchStore";

export default {
  name: "SearchPlace",
  data() {
    return {
      places: [],
      listPlaces: [],
      placeCnt: 0,
      listStartCnt: 0,
      listMoreCnt: 12,
      dialog2: false,
    };
  },
  components: { SearchListItemVue },
  computed: {
    ...mapState(searchStore, ["keyword", "activeTab", "allPlaceData", "nowPlace"]),
  },
  mounted() {},
  created() {
    this.places = this.allPlaceData;
    this.placeCnt = this.places.length;
    this.getMoreItem();
  },
  methods: {
    getMoreItem() {
      this.listPlaces = this.listPlaces.concat(
        this.places.slice(this.listStartCnt, this.listStartCnt + this.listMoreCnt)
      );
      this.listStartCnt += this.listMoreCnt;
    },
    showDialog(value) {
      this.$emit("showDialog", value);
    },
  },
  watch: {
    keyword() {
      this.places = this.allPlaceData;
      this.placeCnt = this.allPlaceData.length;
      this.listPlaces = [];
      (this.listStartCnt = 0), this.getMoreItem();
    },
    activeTab() {
      this.dialog2 = false;
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
