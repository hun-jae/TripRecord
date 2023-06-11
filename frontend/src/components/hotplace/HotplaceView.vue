<template>
  <b-container class="bv-example-row mt-3 text-left">
    <v-card
      class="mx-auto"
      width="60%"
      max-width="80%"
      min-height="80vh"
      style="margin-bottom: 10vh"
    >
      <v-card-title>
        <h3 class="font-weight-bold">[{{ this.attraction.title }}]</h3>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <h3 class="font-weight-light">
          {{ article.title }}
        </h3>
      </v-card-title>

      <v-card-subtitle
        class="d-flex flex-row mb-6 bg-surface-variant text-indigo-lighten-5"
      >
        <span class="me-2 mt-2"
          ><div>by {{ article.uid }}</div></span
        >
      </v-card-subtitle>
      <v-row>
        <v-divider class="mx-4"></v-divider>
      </v-row>

      <v-row class="mx-1" style="margin-top: 20px; margin-bottom: 20px">
        <v-card style="width: 100%">
          <v-tabs v-model="tab" grow>
            <v-tab v-for="item in items" :key="item.name">
              {{ item.name }}
            </v-tab>
          </v-tabs>
          <v-tabs-items v-model="tab">
            <v-tab-item key="지도">
              <v-card-text>
                <kakao-map-vue :place="this.attraction"></kakao-map-vue>
              </v-card-text>
            </v-tab-item>
            <v-tab-item key="정보">
              <v-card-text
                class="text-center"
                style="width: 95%; margin: 0 auto"
              >
                <v-row>
                  <v-img
                    class="bg-white"
                    height="20vh"
                    :aspect-ratio="1"
                    :src="this.attraction.imgSrc"
                    cover
                  ></v-img>
                </v-row>
                <v-row style="height: 5vh" class="text-left">
                  <span class="me-2 mt-2" style="width: 20%; font-weight: 900"
                    ><div>주소</div></span
                  >
                  <div class="me-2 mt-2 ml-3">
                    {{ this.attraction.addr }}
                  </div>
                </v-row>
                <v-row style="height: 5vh" class="text-left"
                  ><span class="me-2" style="width: 20%; font-weight: 900"
                    ><div>전화번호</div></span
                  >
                  <div class="me-2 ml-3">
                    {{
                      this.attraction.tel != null ? this.attraction.tel : "X"
                    }}
                  </div></v-row
                >
              </v-card-text>
            </v-tab-item>
          </v-tabs-items>
        </v-card>
      </v-row>
      <v-row>
        <v-col>
          <v-card-text
            class="body-2 mx-2"
            style="min-height: 20vh; font-size: 1.2em"
          >
            {{ article.content }}
          </v-card-text>
        </v-col>
      </v-row>
      <v-row class="mt-5 mx-auto text-right" max-width="80%">
        <v-col class="text-right">
          <v-btn
            size="lg"
            color="rgba(0, 123, 255,0.2)"
            class="m-1"
            @click="moveList"
            >목록</v-btn
          >
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
    <comment-view :bid="bid" type="hotplace" />
  </b-container>
</template>

<script>
import { getArticle, deleteArticle } from "@/api/hotplace";
import { getAttraction } from "@/api/trip";
import KakaoMapVue from "../KakaoMap.vue";
import { mapState } from "vuex";
import CommentView from "../board_comment/CommentView.vue";
const memberStore = "memberStore";

export default {
  name: "HotplaceView",
  data() {
    return {
      bid: -1,
      article: {},
      attraction: {},
      tab: null,
      items: [{ name: "지도" }, { name: "정보" }],
    };
  },
  components: {
    KakaoMapVue,
    CommentView,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  async created() {
    let param = this.$route.params.bid;
    await getArticle(
      param,
      ({ data }) => {
        this.article = data;
        this.bid = data.bid;
        getAttraction(
          this.article.contentId,
          ({ data }) => {
            this.attraction = data;
          },
          (error) => {
            console.log(error);
          }
        );
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    moveModifyArticle() {
      this.$router.replace({
        name: "hotplacemodify",
        params: {
          bid: this.article.bid,
          contentId: this.article.contentId,
          hotplaceTitle: this.article.title,
        },
      });
    },
    deleteArticle() {
      if (confirm("정말로 삭제하시겠습니까?")) {
        // this.$router.replace({
        //   name: "hotplacedelete",
        //   params: { bid: this.article.bid },
        // });
        let param = this.article.bid;
        deleteArticle(
          param,
          ({ data }) => {
            let msg = "삭제 처리시 문제가 발생했습니다.";
            if (data === "success") {
              msg = "삭제가 완료되었습니다.";
            }
            alert(msg);
            // 현재 route를 /list로 변경.
            this.$router.push({ name: "hotplacelist" });
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
    moveList() {
      // this.$router.push({ name: "hotplacelist" });
      this.$router.go(-1);
    },
  },
};
</script>

<style></style>
