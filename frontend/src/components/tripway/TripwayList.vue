<template>
  <b-container class="bv-example-row mt-3 text-left" style="width: 60%">
    <tripway-search class="mb-5" @search="searchkeyword"></tripway-search>
    <b-row class="mb-1">
      <b-col class="text-right">
        <v-btn
          variant="outline-primary"
          x-large
          @click="moveWrite()"
          color="rgba(0, 123, 255,0.2)"
          >글쓰기</v-btn
        >
      </b-col>
    </b-row>
    <v-container fluid class="text-left">
      <v-row dense>
        <v-col v-for="article in articles" :key="article.bid" :cols="12" md="4">
          <v-card max-width="400" class="mb-5" @click="viewArticle(article)">
            <v-img
              class="align-end text-white"
              :src="article.imgSrc !== '' ? article.imgSrc : none_img"
              height="200px"
              cover
            ></v-img>
            <v-card-title>
              <template>
                <router-link
                  :to="{ name: 'tripwayview', params: { bid: article.bid } }"
                  class="text-truncate body-2"
                  style="font-weight: 800; color: black"
                >
                  <div style="font-weight: 900; font-size: 1.2em; color: black">
                    {{ article.title }}
                  </div>
                </router-link>
              </template>
            </v-card-title>
            <v-card-subtitle>
              <span class="me-1">작성자 : {{ article.uid }}</span>
            </v-card-subtitle>
            <v-row>
              <v-col>
                <v-card-text class="body-2 text-truncate text-left">
                  {{ article.content }}
                </v-card-text>
              </v-col>
            </v-row>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";
import { listArticle, searchTripway } from "@/api/tripway";
import TripwaySearch from "./TripwaySearch.vue";

const tripwayStore = "tripwayStore";

export default {
  name: "TripwayList",
  data() {
    return {
      from: null,
      articles: [],
      fields: [
        { key: "bid", label: "글번호", tdClass: "tdClass" },
        { key: "title", label: "제목", tdClass: "tdSubject" },
        { key: "uid", label: "작성자", tdClass: "tdClass" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
        { key: "numHeart", label: "조회수", tdClass: "tdClass" },
      ],
      none_img: "https://picsum.photos/400/400/?image=20",
    };
  },
  components: {
    TripwaySearch,
  },
  created() {
    // let from = this.$route.params.from;
    // this.from = from;
    let param = {
      pgno: 1,
      keyword: "",
      search_code: "",
    };
    listArticle(
      param,
      ({ data }) => {
        console.log(data);
        this.articles = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  computed: {
    ...mapState(tripwayStore, ["uid", "title", "content"]),
  },
  methods: {
    ...mapActions(tripwayStore, [
      "uidSet",
      "titleSet",
      "contentSet",
      "attractionsReset",
      "statedaysSet",
      "firstCallSet",
      "selectedPlaceSet",
      "selectedDaySet",
    ]),

    moveWrite() {
      this.uidSet(null);
      this.titleSet(null);
      this.contentSet(null);
      this.attractionsReset();
      this.statedaysSet(1);
      this.firstCallSet(true);
      this.selectedPlaceSet(null);
      this.selectedDaySet(0);

      this.$router.push({ name: "tripwaywrite" });
    },
    viewArticle(article) {
      this.uidSet(null);
      this.titleSet(null);
      this.contentSet(null);
      this.attractionsReset();
      this.statedaysSet(1);
      this.firstCallSet(true);
      this.selectedPlaceSet(null);
      this.selectedDaySet(0);

      this.$router.push({
        name: "tripwayview",
        params: { bid: article.bid },
      });
    },
    searchkeyword(keyword) {
      searchTripway(
        keyword,
        ({ data }) => {
          console.log(data);
          this.articles = data;
        },
        (error) => {
          console.log(error);
        }
      );
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
