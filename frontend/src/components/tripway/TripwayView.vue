<template>
  <b-container class="bv-example-row mt-3 text-left">
    <v-card
      class="mx-auto"
      width="60%"
      max-width="60%"
      min-height="80vh"
      style="margin-bottom: 10vh"
    >
      <v-card-title
        style="display: flex; margin-top: 20px; margin-bottom: 20px; justify-content: space-between"
      >
        <v-card-text
          class="text-center"
          @click="changeTab('Trip')"
          style="font-weight: 800; width: 40%; min-width: fit-content; cursor: pointer"
        >
          여행 경로
        </v-card-text>
        <v-card-text
          class="text-center"
          @click="changeTab('Paragraph')"
          style="font-weight: 800; width: 40%; min-width: fit-content; cursor: pointer"
        >
          글 보기
        </v-card-text>
      </v-card-title>
      <div class="text-center" style="margin 0 auto">
        <transition name="slide-fade" mode="out-in" class="text-center" style="margin 0 auto">
          <tripway-view-paragraph :bid="article.bid" v-if="tab === 'Paragraph'" />
          <tripway-view-trip :bid="bid" v-if="tab === 'Trip'" />
        </transition>
      </div>

      <!-- view 버튼 -->
      <v-row class="mt-5 mx-auto text-right" max-width="70%">
        <v-col class="text-right">
          <v-btn size="lg" color="rgba(0, 123, 255,0.2)" class="m-1" @click="moveList">목록</v-btn>
          <v-btn
            variant="blue-darken-1"
            class="m-1"
            @click="moveModifyArticle"
            v-if="this.userInfo.uid === article.uid"
            >수정하기</v-btn
          >
          <v-btn
            variant="blue-darken-1"
            class="m-1"
            @click="deleteArticle"
            v-if="this.userInfo.uid === article.uid"
            >삭제하기</v-btn
          >
        </v-col>
      </v-row>
    </v-card>
    <b-container class="bv-example-row mt-3 text-left">
      <comment-view :bid="bid" type="tripway" />
    </b-container>
  </b-container>
</template>

<script>
// import moment from "moment";
import { getArticle } from "@/api/tripway";
import { mapState, mapActions } from "vuex";

import TripwayViewParagraph from "./item/TripwayViewParagraph.vue";
import TripwayViewTrip from "./item/TripwayViewTrip.vue";
import CommentView from "../board_comment/CommentView.vue";
const memberStore = "memberStore";
const tripwayStore = "tripwayStore";

export default {
  name: "TripwayView",
  data() {
    return {
      article: {},
      tab: "Trip",
      bid: 0,
    };
  },
  components: {
    TripwayViewParagraph,
    TripwayViewTrip,
    CommentView,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(tripwayStore, ["uid", "title", "content"]),

    message() {
      if (this.article.content) return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    this.bid = this.$route.params.bid;
    let param = this.$route.params.bid;
    console.log(this.bid);
    getArticle(
      param,
      ({ data }) => {
        this.article = data;
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    ...mapActions(tripwayStore, [
      "uidSet",
      "titleSet",
      "contentSet",
      "attractionsReset",
      "statedaysSet",
      "firstCallSet",
    ]),
    moveModifyArticle() {
      this.uidSet(null);
      this.titleSet(null);
      this.contentSet(null);
      this.attractionsReset();
      this.statedaysSet(0);
      this.firstCallSet(true);
      this.$router.replace({
        name: "tripwaymodify",
        params: { bid: this.article.bid },
      });
      //   this.$router.push({ path: `/tripway/modify/${this.article.bid}` });
    },
    deleteArticle() {
      console.log(this.article.bid);
      if (confirm("정말로 삭제?")) {
        this.$router.replace({
          name: "tripwaydelete",
          params: { bid: this.article.bid },
        });
      }
    },
    moveList() {
      this.$router.go(-1);
      [2];
    },
    changeTab(tab) {
      this.tab = tab;
    },
  },
};
</script>

<style></style>
