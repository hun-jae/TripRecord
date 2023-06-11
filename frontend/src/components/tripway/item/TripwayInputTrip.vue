<template>
  <div>
    <!-- 사이드바에 아닐 때-->
    <search-place-modal
      :value="dialog"
      @input="dialog = $event"
      :place="this.place"
    ></search-place-modal>
    <div v-if="issidebar === 'false'" style="">
      <!--카카오맵-->
      <kakao-map-multimarker></kakao-map-multimarker>
      <!-- 오른쪽 사이드바 호출 -->
      <tripway-right-sidebar :day="thisSelectedDay" :idx="idx"></tripway-right-sidebar>

      <div style="width: 80%; margin: 1% 1%; margin: 0 auto">
        <!-- 일정관리 전체 card 시작-->
        <div
          class="daycontainer"
          style="height: 50vh; white-space: nowrap; overflow-x: auto; overflow-y: hidden"
        >
          <!-- 날짜 추가하는 부분 시작-->

          <v-card
            class="daycard"
            v-for="d in thisdays"
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
              v-if="d === thisnowday && d < 8"
              @click="newday(d)"
              v-b-toggle.sidebar-right
              style="margin-bottom: 20px"
              >Day{{ d }} +</v-btn
            >
            <v-btn
              variant="tonal"
              color="rgba(0, 123, 255,0.2)"
              block
              v-else-if="d < 8"
              @click="selectDay(d)"
              v-b-toggle.sidebar-right
              style="margin-bottom: 20px"
              >Day{{ d }} 수정</v-btn
            >

            <!-- 계획 일정 추가 시작 -->
            <v-row>
              <template v-for="(addedattraction, index) in thisAddedAttractions">
                <v-col
                  v-if="addedattraction.day === d"
                  :key="addedattraction.content_id"
                  :cols="12"
                  md="9"
                  class="mx-auto text-center"
                  style="cursor: pointer"
                  @click="clickPlace(addedattraction)"
                >
                  <v-card class="overflow-hidden mx-auto text-center" style="max-width: 100%">
                    <v-icon
                      class="text-right"
                      size="large"
                      variant="text"
                      style="
                        position: absolute;
                        top: 0%;
                        left: 90%;
                        margin-right: 2%;
                        cursor: pointer;
                      "
                      @click.native="deleteItem(index)"
                      >mdi-close</v-icon
                    >
                    <div class="d-flex flex-no-wrap justify-space-between">
                      <v-img
                        :src="
                          addedattraction.first_image !== ''
                            ? addedattraction.first_image
                            : none_img
                        "
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
                          >{{ addedattraction.title }}</v-card-text
                        >
                      </div>
                    </div>
                  </v-card>
                </v-col>
              </template>
              <!-- card for 문 끝 -->
            </v-row>

            <!-- 계획 일정 추가 끝 -->
          </v-card>
          <!-- 날짜 추가하는 부분 끝-->
        </div>
        <!-- 일정관리 전체 card 끝-->

        <!-- <div
        v-for="addedattraction in thisAddedAttractions"
        :key="addedattraction.content_id"
        style="float: left"
      >
        <h5 style="float: right" v-if="addedattraction.day === thisSelectedDay">
          {{ addedattraction.content_id }}
        </h5>
      </div> -->
        <!-- footer-->
        <v-row class="mb-1">
          <v-col class="text-right" style="text-align: left">
            <div>
              <div class="form-row float-right">
                <b-button
                  style="clear: both"
                  @click="onSubmit"
                  type="submit"
                  variant="light"
                  class="m-1"
                  v-if="this.type === 'register'"
                  >글 작성</b-button
                >
                <b-button
                  style="clear: both"
                  @click="onSubmit"
                  type="submit"
                  variant="light"
                  class="m-1"
                  v-else
                  >글 수정</b-button
                >
                <v-btn @click="onReset" type="reset" class="m-1" color="rgba(0, 123, 255,0.2)"
                  >작성 취소</v-btn
                >
              </div>
            </div>
          </v-col>
        </v-row>
      </div>
    </div>

    <!-- 사이드바 -->
    <div v-else>
      <div class="accordion" role="tablist">
        <b-card v-if="1 < thisnowday" no-body class="mb-1">
          <b-card-header header-tag="header" class="p-1" role="tab">
            <v-btn block v-b-toggle.accordion-1 color="rgba(0, 123, 255,0.2)">Day 1</v-btn>
          </b-card-header>
          <b-collapse id="accordion-1" visible accordion="my-accordion" role="tabpanel">
            <div v-for="addedattraction in thisAddedAttractions" :key="addedattraction.content_id">
              <b-card-body v-if="addedattraction.day === 1">
                <b-card-text>{{ addedattraction.title }}</b-card-text>
              </b-card-body>
            </div>
          </b-collapse>
        </b-card>

        <b-card v-if="2 < thisnowday" no-body class="mb-1">
          <b-card-header header-tag="header" class="p-1" role="tab">
            <v-btn block v-b-toggle.accordion-2 color="rgba(0, 123, 255,0.2)">Day 2</v-btn>
          </b-card-header>
          <b-collapse id="accordion-2" visible accordion="my-accordion" role="tabpanel">
            <div v-for="addedattraction in thisAddedAttractions" :key="addedattraction.content_id">
              <b-card-body v-if="addedattraction.day === 2">
                <b-card-text>{{ addedattraction.title }}</b-card-text>
              </b-card-body>
            </div>
          </b-collapse>
        </b-card>

        <b-card v-if="3 < thisnowday" no-body class="mb-1">
          <b-card-header header-tag="header" class="p-1" role="tab">
            <v-btn block v-b-toggle.accordion-3 color="rgba(0, 123, 255,0.2)">Day 3</v-btn>
          </b-card-header>
          <b-collapse id="accordion-3" visible accordion="my-accordion" role="tabpanel">
            <div v-for="addedattraction in thisAddedAttractions" :key="addedattraction.content_id">
              <b-card-body v-if="addedattraction.day === 3">
                <b-card-text>{{ addedattraction.title }}</b-card-text>
              </b-card-body>
            </div>
          </b-collapse>
        </b-card>

        <b-card v-if="4 < thisnowday" no-body class="mb-1">
          <b-card-header header-tag="header" class="p-1" role="tab">
            <v-btn block v-b-toggle.accordion-4 color="rgba(0, 123, 255,0.2)">Day 4</v-btn>
          </b-card-header>
          <b-collapse id="accordion-4" visible accordion="my-accordion" role="tabpanel">
            <div v-for="addedattraction in thisAddedAttractions" :key="addedattraction.content_id">
              <b-card-body v-if="addedattraction.day === 4">
                <b-card-text>{{ addedattraction.title }}</b-card-text>
              </b-card-body>
            </div>
          </b-collapse>
        </b-card>

        <b-card v-if="5 < thisnowday" no-body class="mb-1">
          <b-card-header header-tag="header" class="p-1" role="tab">
            <v-btn block v-b-toggle.accordion-5 color="rgba(0, 123, 255,0.2)">Day 5</v-btn>
          </b-card-header>
          <b-collapse id="accordion-5" visible accordion="my-accordion" role="tabpanel">
            <div v-for="addedattraction in thisAddedAttractions" :key="addedattraction.content_id">
              <b-card-body v-if="addedattraction.day === 5">
                <b-card-text>{{ addedattraction.title }}</b-card-text>
              </b-card-body>
            </div>
          </b-collapse>
        </b-card>

        <b-card v-if="6 < thisnowday" no-body class="mb-1">
          <b-card-header header-tag="header" class="p-1" role="tab">
            <v-btn block v-b-toggle.accordion-6 color="rgba(0, 123, 255,0.2)">Day 6</v-btn>
          </b-card-header>
          <b-collapse id="accordion-6" visible accordion="my-accordion" role="tabpanel">
            <div v-for="addedattraction in thisAddedAttractions" :key="addedattraction.content_id">
              <b-card-body v-if="addedattraction.day === 6">
                <b-card-text>{{ addedattraction.title }}</b-card-text>
              </b-card-body>
            </div>
          </b-collapse>
        </b-card>

        <b-card v-if="7 < thisnowday" no-body class="mb-1">
          <b-card-header header-tag="header" class="p-1" role="tab">
            <v-btn block v-b-toggle.accordion-7 color="rgba(0, 123, 255,0.2)">Day 7</v-btn>
          </b-card-header>
          <b-collapse id="accordion-7" visible accordion="my-accordion" role="tabpanel">
            <div v-for="addedattraction in thisAddedAttractions" :key="addedattraction.content_id">
              <b-card-body v-if="addedattraction.day === 7">
                <b-card-text>{{ addedattraction.title }}</b-card-text>
              </b-card-body>
            </div>
          </b-collapse>
        </b-card>
      </div>
    </div>
  </div>
</template>

<script>
import { writeArticle, modifyArticle, getArticle } from "@/api/tripway";
import {
  getArticleAttractions,
  registAttraction,
  deleteAttractions,
  getAttraction,
} from "@/api/trip";
import TripwayRightSidebar from "./TripwayRightSidebar.vue";
import { mapState, mapActions } from "vuex";
import KakaoMapMultimarker from "../../KakaoMapMultimarker.vue";
import "@mdi/font/css/materialdesignicons.css";
import SearchPlaceModal from "@/components/search/SearchPlaceModal.vue";
const searchStore = "searchStore";

const tripwayStore = "tripwayStore";
const memberStore = "memberStore";

//import { writeArticle, modifyArticle, getArticle } from "@/api/tripway";
export default {
  name: "TripwayInputTrip",
  components: {
    TripwayRightSidebar,
    KakaoMapMultimarker,
    SearchPlaceModal,
  },
  data() {
    return {
      thistitle: null,
      thiscontent: null,
      thisAddedAttractions: [
        {}, //day1
      ],
      thisdays: [],

      isuid: false,
      thisSelectedDay: 0,
      sidebarDay: 1,
      idx: 0,
      none_img: "https://picsum.photos/400/400/?image=20",
      dialog: false,
      place: null,
    };
  },
  props: {
    issidebar: String,
    type: { type: String },
    bid: String,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
    ...mapState(tripwayStore, ["title", "content", "attractions", "statedays", "firstCall"]),
  },
  async created() {
    //수정시 data를 api호출을 통해 가져옴
    if (this.type === "modify" && this.firstCall === true) {
      let param = this.bid;

      await getArticle(
        param,
        ({ data }) => {
          this.titleSet(data.title);
          this.contentSet(data.content);
          this.statedaysSet(data.day);
          this.thistitle = this.title;
          this.thiscontent = this.content;
          this.thisnowday = this.statedays;

          //얘는 state에 저장돼야함.
          this.isuid = true;
          this.firstCallSet(false);
          this.thisdays = [];
          for (let i = 1; i <= this.thisnowday; i++) this.thisdays.push(i);
          // this.thisuid = data.article.uid;
          // this.thistitle = data.article.title;
          // this.thiscontent = data.article.content;
        },
        (error) => {
          console.log(error);
        }
      );
      //이부분 추가해줘야함 api/trip.js 에 메소드 추가
      //api 에서 가져오는 부분
      //search/{bid} 로 attractioninfo 및 attrforboard에서 가져옴
      await getArticleAttractions(this.bid, ({ data }) => {
        this.thisAddedAttractions = [];

        data.forEach((attraction) => {
          this.thisAddedAttractions.push({
            day: attraction.day,
            content_id: attraction.contentId,
            idx: attraction.idx,
            title: attraction.title,
            first_image: attraction.imgSrc,
          });
        });
        this.attractionsInit(this.thisAddedAttractions);
      });
    }
    //글쓰기 혹은 컴포넌트 이동시 데이터를 state에서 가져옴
    else {
      this.thistitle = this.title;
      this.thiscontent = this.content;
      this.thisAddedAttractions = this.attractions;
      this.thisnowday = this.statedays;
      this.thisdays = [];
      for (let i = 1; i <= this.thisnowday; i++) this.thisdays.push(i);
    }
  },
  watch: {
    thistitle() {
      this.titleSet(this.thistitle);
    },
    thiscontent() {
      this.contentSet(this.thiscontent);
    },
    attractions() {
      this.thisAddedAttractions = this.attractions;
    },
    thisnowday() {
      this.statedaysSet(this.thisnowday);
    },
    thisSelectedDay() {
      this.selectedDaySet(this.thisSelectedDay);
    },
  },
  methods: {
    ...mapActions(tripwayStore, [
      "titleSet",
      "contentSet",
      "attractionsSet",
      "attractionsReset",
      "attractionsInit",
      "statedaysSet",
      "firstCallSet",
      "selectedPlaceSet",
      "selectedDaySet",
    ]),
    ...mapActions(searchStore, ["nowPlaceSet"]),

    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.thistitle && ((msg = "제목을 입력해주세요"), (err = false));
      err && !this.thiscontent && ((msg = "내용을 입력해주세요"), (err = false));

      if (!err) alert(msg);
      else this.type === "register" ? this.registArticle() : this.modifyArticle();
    },
    onReset(event) {
      event.preventDefault();

      this.thistitle = null;
      this.thiscontent = null;
      this.thisAddedAttractions = [];
      this.thisnowday = 1;
      this.titleSet(null);
      this.contentSet(null);
      this.attractionsReset();
      this.statedaysSet(1);
      this.firstCallSet(true);
      this.selectedDaySet(0);
      this.selectedPlaceSet(null);
      this.moveList();
    },
    resetAll() {
      this.thistitle = null;
      this.thiscontent = null;
      this.thisAddedAttractions = [];
      this.thisnowday = 1;
      this.titleSet(null);
      this.contentSet(null);
      this.attractionsReset();
      this.statedaysSet(1);
      this.firstCallSet(true);
      this.selectedDaySet(0);

      this.selectedPlaceSet(null);
    },

    async registArticle() {
      let param = {
        uid: this.userInfo.uid,
        title: this.thistitle,
        content: this.thiscontent,
        day: this.thisnowday,
        imgSrc: this.attractions[0].first_image,
      };
      await writeArticle(
        param,
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (Number.isInteger(data)) {
            msg = this.registAttractions(data);
          }
          alert(msg);
          this.resetAll();
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    modifyArticle() {
      let param = {
        bid: this.bid,
        uid: this.userInfo.uid,
        title: this.thistitle,
        content: this.thiscontent,
        day: this.thisnowday,
        imgSrc: this.attractions[0].first_image,
      };
      modifyArticle(
        param,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = this.registAttractions(this.bid);
          }
          alert(msg);
          this.resetAll();
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },

    registAttractions(bid) {
      let msg = "등록이 완료되었습니다.";
      if (this.thisAddedAttractions) {
        //여기서 전체 딜리트를 시켜준 뒤, wawtch를 통해 새로운 함수를 실행시키자
        deleteAttractions(
          bid,
          ({ data }) => {
            if (data === "success") {
              console.log(data);
            }
          },
          (error) => {
            console.log(error);
          }
        );

        this.thisAddedAttractions.forEach((attraction) => {
          let param = {
            content_id: attraction.content_id,
            day: attraction.day,
            bid: bid,
            idx: attraction.idx,
            title: attraction.title,
            first_image: attraction.first_image,
          };
          registAttraction(param, ({ data }) => {
            if (data !== "success") {
              msg = "등록 처리시 문제가 발생했습니다.";
            }
          });
        });
      }
      return msg;
    },
    moveList() {
      this.$router.push({ name: "tripwaylist" });
    },
    newday(day) {
      if (this.thisnowday > 7) {
        alert("일정은 7일까지만 추가할 수 있습니다.");
        return;
      }
      this.thisnowday += 1;
      this.thisdays.push(this.thisnowday);
      this.statedaysSet(this.thisnowday);
      this.selectDay(day);
    },
    selectDay(day) {
      this.thisSelectedDay = day;
    },
    // deleteAttr(day, content_id, idx) {
    //   this.thisAddedAttractions
    // },
    deleteItem(index) {
      this.thisAddedAttractions.splice(index, 1);
      this.attractionsInit(this.thisAddedAttractions);
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
