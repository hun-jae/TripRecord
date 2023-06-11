<template>
  <div>
    <div>
      <tripway-sidebar v-if="width > 1400 || width === 0" :type="type" :bid="bid" :tab="tab">
      </tripway-sidebar>
    </div>

    <div v-if="width > 1400 || width === 0" style="margin-left: 10%">
      <div style="margin-right: 15%; margin-bottom: 20px">
        <button @click="changeTab('Trip')" style="color: gray; padding-right: 300px">
          여행 경로 선택
        </button>

        <button @click="changeTab('Paragraph')" style="color: gray">글 작성</button>
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
      <div style="margin-right: 15%; margin-bottom: 20px">
        <button @click="changeTab('Trip')" style="color: gray; padding-right: 300px">
          여행 경로 선택
        </button>

        <button @click="changeTab('Paragraph')" style="color: gray">글 작성</button>
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
  name: "HotplaceInputItem",
  data() {
    return {
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
