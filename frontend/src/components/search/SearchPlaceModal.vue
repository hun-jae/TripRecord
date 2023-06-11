<template>
  <div id="app" data-app>
    <v-dialog
      v-model="dialog"
      width="40vw"
      height="50vh"
      class="dialog"
      body-bg-variant="white"
      style="background-color: black"
    >
      <v-card>
        <v-img
          v-if="place.imgSrc != null && place.imgSrc != ``"
          class="align-end text-white"
          :src="place.imgSrc"
          height="30vh"
          cover
        ></v-img>
        <v-img
          v-else
          class="align-end text-white"
          src="@/assets/img/춘식.png"
          height="30vh"
          cover
        ></v-img>
        <v-card-title
          height="50%"
          style="font-weight: 900"
          class="text-truncate text-left"
        >
          {{ place.title }}
        </v-card-title>
        <v-card-subtitle
          style="font-weight: 700; font-size: 1.1em"
          class="mt-1 text-truncate text-left"
        >
          <span class="me-1">{{ place.addr }} </span>
        </v-card-subtitle>

        <v-row class="mx-1" style="margin-top: 20px">
          <v-card style="width: 100%; min-height: 10vh">
            <v-tabs v-model="tab" grow>
              <v-tab v-for="item in items" :key="item.name">
                {{ item.name }}
              </v-tab>
            </v-tabs>
            <v-tabs-items v-model="tab">
              <v-tab-item key="지도">
                <v-card-text>
                  <kakao-map isModal="true"></kakao-map>
                </v-card-text>
              </v-tab-item>
              <v-tab-item key="핫플레이스">
                <v-row
                  dense
                  class="mt-2 mb-5"
                  v-if="articles != null && articles.length > 0"
                >
                  <v-col
                    v-for="article in articles"
                    :key="article.bid"
                    :cols="12"
                    md="9"
                    class="mx-auto text-center"
                  >
                    <v-card
                      max-width="100%"
                      class="mb-2"
                      @click="viewArticle(article)"
                    >
                      <div class="d-flex flex-no-wrap justify-space-between">
                        <v-img
                          class="align-end text-white"
                          :src="article.imgSrc"
                          height="200px"
                          cover
                          style="max-width: 300px"
                        ></v-img>
                        <div class="text-truncate">
                          <v-card-title>
                            {{ article.title }}
                          </v-card-title>
                          <v-card-subtitle class="text-left">
                            <span class="me-1">작성자 : {{ article.uid }}</span>
                          </v-card-subtitle>
                          <v-card-text
                            class="body-2 text-truncate text-left"
                            style=""
                          >
                            {{ article.content }}
                          </v-card-text>
                        </div>
                      </div>
                    </v-card>
                  </v-col>
                </v-row>
                <h3 v-else class="text">
                  등록된 핫플레이스 게시물이 없습니다!
                </h3>
              </v-tab-item>
            </v-tabs-items>
          </v-card>
        </v-row>
      </v-card>
    </v-dialog>
  </div>
</template>
<script>
import { mapState, mapActions } from "vuex";
import { listArticle } from "@/api/hotplace";
import KakaoMap from "../KakaoMap.vue";
const searchStore = "searchStore";

export default {
  name: "SearchPlaceModal",
  data() {
    return {
      tab: null,
      articles: null,
      items: [{ name: "지도" }, { name: "핫플레이스" }],
    };
  },
  created() {
    this.nowPlaceSet(this.place);
  },
  components: { KakaoMap },
  props: {
    place: [],
    value: {
      type: Boolean,
      required: false,
    },
  },
  watch: {
    place() {
      let param = {
        pgno: 1,
        keyword: this.place.contentId,
        search_code: "content_id",
      };
      listArticle(
        param,
        ({ data }) => {
          this.articles = data;
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
  computed: {
    dialog: {
      get() {
        return this.value;
      },
      set(value) {
        this.$emit("input", value);
      },
    },
    ...mapState(searchStore, ["keyword"]),
  },
  methods: {
    ...mapActions(searchStore, ["nowPlaceSet"]),

    viewArticle(article) {
      this.$router.push({ name: "hotplaceview", params: { bid: article.bid } });
    },
  },
};
</script>
<style scoped>
.text {
  font-weight: 700;
  font-style: italic;
  margin-top: 50px;
  margin-bottom: 50px;
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}
</style>
