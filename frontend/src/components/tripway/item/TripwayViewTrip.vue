<template>
  <!-- 사이드바에 아닐 때-->
  <div class="text-center" style="margin 0 auto">
    <search-place-modal
      :value="dialog"
      @input="dialog = $event"
      :place="this.place"
    ></search-place-modal>

    <!--카카오맵-->
    <kakao-map-multimarker></kakao-map-multimarker>

    <div style="width: 80%; margin: 1% 1%; margin: 0 auto">
      <!-- 일정관리 전체 card 시작-->
      <div
        class="daycontainer text-left"
        style="height: 50vh; white-space: nowrap; overflow-x: auto; overflow-y: hidden"
      >
        <v-card
          class="daycard"
          v-for="d in this.days"
          :key="d"
          style="
            margin-top: 10px;
            height: 45vh;
            width: 15vw;
            display: inline-block;
            overflow-y: auto;
            overflow-x: hidden;
            margin-right: 10px;
            margin-bottom: 20px;
          "
        >
          <v-btn
            variant="tonal"
            color="rgba(0, 123, 255,0.2)"
            block
            v-if="d < 8"
            @click="selectDay(d)"
            style="color: black; margin-bottom: 20px"
            >Day{{ d }}</v-btn
          >

          <!-- 계획 일정 추가 시작 -->
          <v-row>
            <template v-for="attraction in attractions">
              <!-- card for 문 시작 -->
              <v-col
                v-if="attraction.day === d"
                :key="attraction.content_id"
                :cols="12"
                md="9"
                class="mx-auto text-center"
                style="cursor: pointer"
                @click="clickPlace(attraction)"
              >
                <div class="d-flex flex-no-wrap justify-space-between">
                  <v-img
                    :src="attraction.first_image !== '' ? attraction.first_image : none_img"
                    alt="Image"
                    cover
                    class="align-end text-white"
                    style="max-width: 50%"
                    height="80px"
                  ></v-img>
                  <div class="text-truncate">
                    <v-card-text
                      class="body-2 text-truncate text-left mt-3"
                      style="font-weight: 700"
                      >{{ attraction.title }}</v-card-text
                    >
                  </div>
                </div>
              </v-col>
              <!-- card for 문 끝 -->
            </template>
          </v-row>

          <!-- 계획 일정 추가 끝 -->
        </v-card>
        <!-- 날짜 추가하는 부분 끝-->
      </div>
      <!-- 일정관리 전체 card 끝-->
    </div>
  </div>
</template>

<script>
import { getArticle } from "@/api/tripway";
import { getArticleAttractions, getAttraction } from "@/api/trip";
import KakaoMapMultimarker from "../../KakaoMapMultimarker.vue";
import { mapActions } from "vuex";
const searchStore = "searchStore";

import SearchPlaceModal from "@/components/search/SearchPlaceModal.vue";

const tripwayStore = "tripwayStore";

//import { writeArticle, modifyArticle, getArticle } from "@/api/tripway";
export default {
  name: "TripwayViewTrip",
  components: {
    KakaoMapMultimarker,
    SearchPlaceModal,
  },
  data() {
    return {
      title: null,
      content: null,
      attractions: [],
      isuid: false,
      days: [1],
      none_img: "https://picsum.photos/400/400/?image=20",
      dialog: false,
      place: null,
    };
  },
  props: {
    bid: Number,
  },
  computed: {},
  async created() {
    //수정시 data를 api호출을 통해 가져옴
    let param = this.bid;

    await getArticle(
      param,
      ({ data }) => {
        this.title = data.title;
        this.content = data.content;
        this.nowday = data.day;

        //얘는 state에 저장돼야함.
        this.isuid = true;
        this.days = [];
        for (let i = 1; i <= this.nowday - 1; i++) this.days.push(i);
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
      this.attractionsInit(this.attractions);
    });
  },
  watch: {},
  methods: {
    ...mapActions(searchStore, ["nowPlaceSet"]),

    ...mapActions(tripwayStore, ["attractionsInit", "selectedDaySet"]),
    moveList() {
      this.$router.go(-1);
    },

    selectDay(day) {
      this.SelectedDay = day;

      this.selectedDaySet(day);
    },

    clickPlace(attraction) {
      getAttraction(
        attraction.content_id,
        ({ data }) => {
          this.dialog = !this.dialog;
          this.place = data;
          this.nowPlaceSet(this.place);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style>
.daycard::-webkit-scrollbar {
  width: 10px;
}
.daycard::-webkit-scrollbar-thumb {
  background-color: rgba(41, 41, 41, 0.3);
  border-radius: 10px;
  background-clip: padding-box;
  border: 1px solid transparent;
}
.daycard::-webkit-scrollbar-track {
  background-color: rgba(145, 145, 145, 0.1);
  border-radius: 10px;
  box-shadow: inset 0px 0px 5px white;
}
.daycontainer::-webkit-scrollbar {
  width: 10px;
}
.daycontainer::-webkit-scrollbar-thumb {
  background-color: rgba(41, 41, 41, 0.3);
  border-radius: 10px;
  background-clip: padding-box;
  border: 1px solid transparent;
}
.daycontainer::-webkit-scrollbar-track {
  background-color: rgba(145, 145, 145, 0.1);
  border-radius: 10px;
  box-shadow: inset 0px 0px 5px white;
}
</style>
