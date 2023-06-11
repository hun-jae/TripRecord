<template>
  <div>
    <b-container class="bv-example-row mt-3 text-center">
      <v-card class="mx-auto" max-width="100%" min-height="80vh">
        <v-card-title>
          <h3 class="font-weight-light">
            {{ title }}
          </h3>
        </v-card-title>
        <v-card-subtitle
          class="d-flex flex-row mb-6 mt-1 bg-surface-variant text-indigo-lighten-5"
        >
          <span class="me-2 mt-2"
            ><div>by {{ uid }}</div></span
          >
        </v-card-subtitle>
        <v-row>
          <v-divider class="mx-4"></v-divider>
        </v-row>
        <v-row>
          <v-col>
            <v-card-title class="body-2 mx-2">
              {{ content }}
            </v-card-title>
          </v-col>
        </v-row>
      </v-card>
    </b-container>
  </div>
</template>

<script>
import { getArticle } from "@/api/tripway";
import { getArticleAttractions } from "@/api/trip";

//import { writeArticle, modifyArticle, getArticle } from "@/api/tripway";
export default {
  name: "TripwayViewParagraph",
  components: {},
  data() {
    return {
      title: null,
      content: null,
      attractions: [
        {}, //day1
      ],
      uid: null,
      numHeart: 0,
      days: [],

      isuid: false,
      SelectedDay: 0,
      none_img: "https://picsum.photos/400/400/?image=20",
    };
  },
  props: {
    bid: Number,
  },
  computed: {},
  async created() {
    //수정시 data를 api호출을 통해 가져옴
    console.log("trip here");
    let param = this.bid;

    await getArticle(
      param,
      ({ data }) => {
        this.title = data.title;
        this.content = data.content;
        this.nowday = data.day;
        this.uid = data.uid;
        this.numHeart = data.numHeart;
        this.isuid = true;
        this.days = [];
        for (let i = 1; i <= this.nowday; i++) this.days.push(i);
      },
      (error) => {
        console.log(error);
      }
    );
    //이부분 추가해줘야함 api/trip.js 에 메소드 추가
    //api 에서 가져오는 부분
    //search/{bid} 로 attractioninfo 및 attrforboard에서 가져옴
    await getArticleAttractions(this.bid, ({ data }) => {
      this.attractions = [];

      data.forEach((attraction) => {
        this.attractions.push({
          day: attraction.day,
          content_id: attraction.contentId,
          idx: attraction.idx,
          title: attraction.title,
          first_image: attraction.imgSrc,
        });
      });
    });
  },
  watch: {},
  methods: {
    moveList() {
      this.$router.go(-1);
    },
  },
};
</script>

<style></style>
