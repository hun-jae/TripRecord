<template>
  <v-container fluid class="text-left">
    <h2 style="margin-top: 5%; font-weight: 700">전체 {{ this.placeCnt }}건</h2>
    <hr />
    <v-row dense>
      <v-col v-for="place in listPlaces" :key="place.bid" :cols="12" md="4">
        <search-list-item-vue
          :place="place"
          class="fill-height"
          type="hotplace"
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
const searchStore = "searchStore";

export default {
  name: "SearchHotplace",
  data() {
    return {
      places: [],
      listPlaces: [],
      placeCnt: 0,
      listStartCnt: 0,
      listMoreCnt: 12,
    };
  },
  components: { SearchListItemVue },
  computed: {
    ...mapState(searchStore, ["keyword", "activeTab", "allTripwayData"]),
  },
  mounted() {},
  created() {
    this.places = this.allTripwayData;
    this.placeCnt = this.allTripwayData.length;
    this.getMoreItem();
  },
  methods: {
    getMoreItem() {
      this.listPlaces = this.listPlaces.concat(
        this.places.slice(this.listStartCnt, this.listStartCnt + this.listMoreCnt)
      );
      this.listStartCnt += this.listMoreCnt;
    },
    onSubmit() {
      if (this.input == "" || this.input.length == 0) {
        alert("검색어를 입력해주세요.");
      } else {
        this.keywordSet(this.input);
        this.input = "";
        if (this.$route.path != "/search") {
          this.$router.push({ name: "search" });
        }
      }
    },
  },
  watch: {
    keyword() {
      this.places = this.allTripwayData;
      this.placeCnt = this.allTripwayData.length;
      this.listPlaces = [];
      (this.listStartCnt = 0), this.getMoreItem();
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
s
