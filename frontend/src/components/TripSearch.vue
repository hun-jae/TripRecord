<template>
  <div id="app" data-app>
    <!-- 사이드바 -->
    <b-form-group v-if="issidebar === 'true'" id="fieldset-1">
      <b-form-select
        class="mt-1"
        size="sm"
        v-model="param.sido_code"
        :options="search_area"
      ></b-form-select>

      <b-form-select
        class="mt-1"
        size="sm"
        v-model="param.content_type_id"
        :options="option"
      ></b-form-select>

      <b-form-input
        v-model="param.keyword"
        placeholder="검색어 입력.."
        size="sm"
        class="mt-1"
      ></b-form-input>

      <b-button @click="onSubmit" size="sm" variant="outline-primary" style="margin-top: 10px"
        >검색하기</b-button
      >

      <kakao-map :issidebar="issidebar" class="mt-3 text-center mx-auto"></kakao-map>
      <div v-if="attractions.length" style="margin-top: 30px">
        <div
          v-for="attraction in attractions"
          :key="attraction.contentid"
          style="list-style-type: none"
        >
          <trip-search-list :day="day" :attraction="attraction"></trip-search-list>
        </div>
      </div>
    </b-form-group>

    <!-- 사이드바아님 -->
    <b-container v-else-if="isModal != true" class="bv-example-row mt-3 p-5 text-center">
      <b-row class="mt-4 mb-5 text-center">
        <b-col class="s">
          <b-form-select size="lg" v-model="param.sido_code" :options="search_area"></b-form-select>
        </b-col>

        <b-col class="s">
          <b-form-select
            size="lg"
            v-model="param.content_type_id"
            :options="option"
          ></b-form-select>
        </b-col>

        <b-form-input v-model="param.keyword" placeholder="검색어 입력.."></b-form-input>

        <b-col>
          <b-button @click="onSubmit" size="lg" variant="outline-primary">검색하기</b-button>
        </b-col>
      </b-row>
      <kakao-map :issidebar="issidebar"></kakao-map>
    </b-container>

    <!-- modal -->
    <v-row justify="center" style="min-height: fit-content">
      <v-dialog
        v-if="isModal === true"
        :value="dialog"
        @input="dialog = $event"
        scrollable
        width="50%"
        height="max-content"
        class="dialog"
        body-bg-variant="white"
      >
        <v-card>
          <v-card-title>글을 작성할 핫플레이스를 선택해주세요</v-card-title>
          <v-divider></v-divider>
          <v-row class="text-center" style="width: 90%; margin: 0 auto">
            <v-col cols="10">
              <b-form-group>
                <b-form-select
                  class="mt-1"
                  v-model="param.sido_code"
                  :options="search_area"
                ></b-form-select>
                <b-form-select
                  class="mt-1"
                  v-model="param.content_type_id"
                  :options="option"
                ></b-form-select>
                <b-form-input
                  class="mt-1"
                  v-model="param.keyword"
                  placeholder="검색어 입력.."
                  style="margin-top: 10px"
                ></b-form-input>
              </b-form-group>
            </v-col>
            <v-col cols="2" align="left">
              <v-btn
                @click="onSubmit"
                variant="outline-primary"
                style="margin-top: 10px; height: 80%"
                >검색</v-btn
              >
            </v-col>
          </v-row>
          <kakao-map isModal="true" style="margin: 0 auto; margin-top: 10px"></kakao-map>
          <v-card
            class="mt-2 mb-2 p-2 text-center"
            v-if="hotplace != null"
            variant="tonal"
            style="width: 90%; margin: 0 auto"
          >
            <div>⬇ 선택한 핫플레이스 ⬇</div>
            <div class="selected">{{ hotplace.title }}</div></v-card
          >
          <div
            v-if="attractions.length"
            style="
              max-height: 40%;
              overflow: scroll;
              margin-top: 10px;
              max-width: 90%;
              text-align: center;
            "
            class="scroll"
          >
            <ul
              v-for="attraction in attractions"
              :key="attraction.contentid"
              style="list-style-type: none"
            >
              <hotplace-search-list-item-vue
                @select="selectPlace"
                :attraction="attraction"
              ></hotplace-search-list-item-vue>
            </ul>
          </div>
          <v-divider></v-divider>
          <v-card-actions class="text-right" align="right">
            <v-col class="text-right">
              <v-btn color="blue-darken-1" variant="text" @click="dialog = false"> 취소 </v-btn>
              <v-btn class="ml-3" color="rgba(231, 27, 139, 0.1)" @click="moveWrite">
                글쓰기
              </v-btn>
            </v-col>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </div>
</template>

<script>
import KakaoMap from "@/components/KakaoMap.vue";
import { searchSido, searchList } from "@/api/trip";

import TripSearchList from "./trip/TripSearchList.vue";
import HotplaceSearchListItemVue from "./hotplace/item/HotplaceSearchListItem.vue";

import { mapState, mapActions } from "vuex";
const hotplaceStore = "hotplaceStore";
export default {
  name: "TripSearch",
  components: {
    KakaoMap,
    TripSearchList,
    HotplaceSearchListItemVue,
  },
  props: {
    issidebar: String,
    isModal: {
      type: Boolean,
      required: false,
    },
    value: {
      type: Boolean,
      required: false,
    },
    day: Number,
  },
  data() {
    return {
      param: {
        sido_code: "",
        content_type_id: "",
        keyword: "",
      },
      search_area: [],
      option: [],
      attractions: [],
    };
  },
  created() {
    this.searchSido();
    this.search_area.push({ value: "", text: "검색할 지역" });
    this.option.push({ value: "", text: "관광지 유형" });
    this.option.push({ value: 12, text: "관광지" });
    this.option.push({ value: 14, text: "문화시설" });
    this.option.push({ value: 15, text: "축제공연행사" });
    this.option.push({ value: 25, text: "여행코스" });
    this.option.push({ value: 28, text: "레포츠" });
    this.option.push({ value: 32, text: "숙박" });
    this.option.push({ value: 38, text: "쇼핑" });
    this.option.push({ value: 39, text: "음식점" });
    this.hotplaceSet(this.attraction);
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
    ...mapState(hotplaceStore, ["hotplace"]),
  },
  methods: {
    ...mapActions(hotplaceStore, ["hotplaceSet"]),

    searchSido() {
      searchSido(
        ({ data }) => {
          data.forEach((sido) => {
            this.search_area.push({
              value: sido.sido_code,
              text: sido.sido_name,
            });
          });
        },
        (error) => {
          console.log(error);
        }
      );
    },
    onSubmit(event) {
      console.log("onClick");
      event.preventDefault();
      if (
        this.param.sido_code === "" &&
        this.param.content_type_id === "" &&
        this.param.keyword === ""
      ) {
        alert("검색 조건을 1개 이상 적용해주세요");
      } else {
        searchList(this.param, ({ data }) => {
          this.attractions = [];
          data.forEach((attraction) => {
            this.attractions.push(attraction);
          });
          console.log(this.attractions);
        });
      }
    },
    selectPlace(attraction) {
      this.select_place = attraction;
    },
    moveWrite() {
      if (this.hotplace != null) {
        this.$router.push({
          name: "hotplacewrite",
          params: { contentId: this.hotplace.contentId, hotplaceTitle: this.hotplace.title },
        });
      } else {
        alert("핫플레이스를 선택해주세요!");
      }
    },
  },
  watch: {
    dialog(newvalue, oldvalue) {
      if (!newvalue && oldvalue) {
        this.hotplaceSet(null);
        (this.param.sido_code = ""),
          (this.param.content_type_id = ""),
          (this.param.keyword = ""),
          (this.attractions = []);
      }
    },
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(72, 190, 233, 0.3) 30%);
}

.selected {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(231, 27, 139, 0.3) 30%);
  font-weight: 700;
  font-size: 1.2rem;
  letter-spacing: 0.2rem;
}

.scroll::-webkit-scrollbar {
  display: none;
}
</style>
