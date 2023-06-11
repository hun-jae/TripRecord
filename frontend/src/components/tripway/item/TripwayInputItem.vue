<template>
  <div>
    <div>
      <tripway-sidebar v-if="width > 1400 || width === 0" :type="type" :bid="bid" :tab="tab">
      </tripway-sidebar>
    </div>

    <div v-if="width > 1400 || width === 0" style="margin-left: 10%">
      <div style="display: flex; margin-bottom: 20px; justify-content: space-between">
        <v-card-text
          @click="changeTab('Trip')"
          style="font-weight: 800; width: 40%; min-width: fit-content; cursor: pointer"
        >
          여행 경로 선택
        </v-card-text>
        <v-card-text
          @click="changeTab('Paragraph')"
          style="font-weight: 800; width: 40%; min-width: fit-content; cursor: pointer"
        >
          글 작성
        </v-card-text>
      </div>
      <div>
        <transition name="slide-fade" mode="out-in">
          <tripway-input-paragraph
            issidebar="false"
            :type="type"
            :bid="bid"
            v-if="tab === 'Paragraph'"
          />
          <tripway-input-trip issidebar="false" :type="type" :bid="bid" v-if="tab === 'Trip'" />
        </transition>
      </div>
    </div>

    <div v-else>
      <div
        style="
          padding-left: 10%;
          padding-right: 10%;
          display: flex;
          margin-bottom: 20px;
          justify-content: space-between;
        "
      >
        <v-card-text
          @click="changeTab('Trip')"
          style="font-weight: 800; width: 40%; min-width: fit-content; cursor: pointer"
        >
          여행 경로 선택
        </v-card-text>
        <v-card-text
          @click="changeTab('Paragraph')"
          style="font-weight: 800; width: 40%; min-width: fit-content; cursor: pointer"
        >
          글 작성
        </v-card-text>
      </div>
      <div>
        <transition name="slide-fade" mode="out-in">
          <tripway-input-paragraph
            issidebar="false"
            :type="type"
            :bid="bid"
            v-if="tab === 'Paragraph'"
          />
          <tripway-input-trip issidebar="false" :type="type" :bid="bid" v-if="tab === 'Trip'" />
        </transition>
      </div>
    </div>
  </div>
</template>

<script>
import TripwayInputParagraph from "@/components/tripway/item/TripwayInputParagraph";
import TripwayInputTrip from "@/components/tripway/item/TripwayInputTrip";
import TripwaySidebar from "@/components/tripway/item/TripwaySidebar";
export default {
  name: "TripwayInputItem",
  data() {
    return {
      //userInfo삭제하고 mapstate, memberStore부분 다 주석 해제
      //router에서 주석도 해제해야함
      // userInfo: {
      //   uid: "ssafy3",
      //   password: "ssafy4",
      // },
      // article: {
      //   bid: 0,
      //   uid: "",
      //   title: "",
      //   content: "",
      // },
      bid: this.$route.params.bid,
      isuid: false,
      tab: "Trip",
      width: 0,
      height: 0,
    };
  },
  components: {
    TripwayInputParagraph,
    TripwayInputTrip,
    TripwaySidebar,
  },
  props: {
    type: { type: String },
  },
  mounted() {
    // console.log("ready...");
    window.addEventListener("resize", this.handleResize);
  },

  methods: {
    changeTab(tab) {
      this.tab = tab;
    },
    handleResize(event) {
      this.tmp = event;
      this.width = window.innerWidth;
      this.height = window.innerHeight;
    },
  },
};
</script>

<style>
.slide-fade-enter {
  transform: translateX(10px);
  opacity: 0;
}

.slide-fade-enter-active,
.slide-fade-leave-active {
  transition: all 0.25s ease;
}

.slide-fade-leave-to {
  transform: translateX(-10px);
  opacity: 0;
}
</style>
