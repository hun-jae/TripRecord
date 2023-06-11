<template>
  <div>
    <div v-if="issidebar === 'false'">
      <b-row class="mb-1">
        <b-col style="text-align: left">
          <b-form class="mx-auto" @submit="onSubmit" @reset="onReset" style="width: 70%">
            <b-form-group id="title-group" label="제목:" label-for="title">
              <b-form-input
                id="title"
                v-model="thistitle"
                type="text"
                required
                placeholder="제목 입력..."
              ></b-form-input>
            </b-form-group>

            <b-form-group id="content-group" label="내용:" label-for="content">
              <b-form-textarea
                id="content"
                v-model="thiscontent"
                placeholder="내용 입력..."
                rows="10"
                max-rows="15"
              ></b-form-textarea>
            </b-form-group>
            <div class="form-row float-right">
              <v-btn type="submit" variant="light" class="m-1" v-if="this.type === 'register'"
                >글 작성</v-btn
              >
              <v-btn type="submit" variant="light" class="m-1" v-else>글 수정</v-btn>
              <v-btn type="reset" color="rgba(0, 123, 255,0.2)" class="m-1">작성 취소</v-btn>
            </div>
          </b-form>
        </b-col>
      </b-row>
    </div>

    <!-- 사이드바 -->
    <div v-else>
      <v-card elevation-24>
        <v-card-title>{{ thistitle }}</v-card-title>
        <v-card-text style="min-height: 30vh" class="text-left">
          {{ thiscontent == null || thiscontent == "" ? "작성된 글이 없습니다." : thiscontent }}
        </v-card-text>
      </v-card>
    </div>
  </div>
</template>

<script>
import { writeArticle, modifyArticle, getArticle } from "@/api/tripway";
import { registAttraction } from "@/api/trip";
import { mapState, mapActions } from "vuex";
const tripwayStore = "tripwayStore";
const memberStore = "memberStore";
//import { writeArticle, modifyArticle, getArticle } from "@/api/tripway";
export default {
  name: "TripwayInputTrip",
  components: {},
  data() {
    return {
      thistitle: null,
      thiscontent: null,

      isuid: false,
      visible: true,
    };
  },
  props: {
    issidebar: String,
    type: { type: String },
    bid: Number,
  },
  computed: {
    ...mapState(tripwayStore, ["title", "content", "attractions", "statedays", "firstCall"]),
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    //수정시 data를 api호출을 통해 가져옴

    if (this.type === "modify" && this.firstCall === true) {
      console.log("paragraph here");
      let param = this.bid;

      getArticle(
        param,
        ({ data }) => {
          this.titleSet(data.title);
          this.contentSet(data.content);
          this.thistitle = this.title;
          this.thiscontent = this.content;

          // this.thisuid = data.article.uid;
          // this.thistitle = data.article.title;
          // this.thiscontent = data.article.content;
        },
        (error) => {
          console.log(error);
        }
      );

      //얘는 state에 저장돼야함.
      this.isuid = true;
    }
    //글쓰기 혹은 컴포넌트 이동시 데이터를 state에서 가져옴
    else {
      console.log("para reloard");

      this.thistitle = this.title;
      this.thiscontent = this.content;
    }
  },
  watch: {
    thistitle() {
      this.titleSet(this.thistitle);
    },
    thiscontent() {
      this.contentSet(this.thiscontent);
    },
    thisnowday() {
      this.statedaysSet(this.thisnowday);
    },
  },
  methods: {
    ...mapActions(tripwayStore, [
      "titleSet",
      "contentSet",
      "attractionSet",
      "attractionsReset",
      "attractionsInit",
      "statedaysSet",
      "firstCallSet",
      "selectedPlaceSet",
      "selectedDaySet",
      "deleteAttractions",
    ]),

    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      !this.thistitle && ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err &&
        !this.thiscontent &&
        ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

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
      console.log("para this.attractions", this.attractions);
      let param = {
        uid: this.userInfo.uid,
        title: this.thistitle,
        content: this.thiscontent,
        day: this.statedays,
        imgSrc: this.attractions[0].first_image,
      };
      const res = await writeArticle(
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
      console.log(res);
    },
    modifyArticle() {
      let param = {
        bid: this.bid,
        uid: this.userInfo.uid,
        title: this.thistitle,
        content: this.thiscontent,
        day: this.statedays,
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
      if (this.attractions) {
        this.attractions.forEach((attraction) => {
          console.log("attraction", attraction);
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
  },
};
</script>

<style></style>
