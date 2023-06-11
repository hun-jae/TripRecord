<template>
  <div>
    <trip-search issidebar="false"></trip-search>
    <kakao-map></kakao-map>
    <b-row class="mb-1">
      <b-col style="text-align: left">
        <b-button type="submit" variant="primary" class="m-1" v-if="this.type === 'register'"
          >글작성</b-button
        >
        <b-button type="submit" variant="primary" class="m-1" v-else>글수정</b-button>
      </b-col>
    </b-row>
  </div>
</template>

<script>
import KakaoMap from "../../KakaoMap.vue";
import TripSearch from "../../TripSearch.vue";
//import { writeArticle, modifyArticle, getArticle } from "@/api/hotplace";

export default {
  name: "HotplaceInputTrip",
  components: {
    KakaoMap,
    TripSearch,
  },
  data() {
    return {
      article: {
        bid: 0,
        uid: "",
        title: "",
        content: "",
      },
      isuid: false,
    };
  },
  props: {
    type: { type: String },
  },
};
</script>

<style></style>
