<template>
  <div>
    <div class="px-3 py-2">
      <div>
        <transition name="slide-fade" mode="out-in">
          <tripway-input-paragraph
            class="parasidebar"
            :type="type"
            :bid="bid"
            issidebar="true"
            v-if="tab === 'Trip'"
          />
          <tripway-input-trip
            class="tripsidebar"
            :type="type"
            :bid="bid"
            issidebar="true"
            v-if="tab === 'Paragraph'"
          />
        </transition>
      </div>
    </div>
  </div>
  <!--</b-sidebar>-->
</template>

<script>
import TripwayInputParagraph from "@/components/tripway/item/TripwayInputParagraph";
import TripwayInputTrip from "@/components/tripway/item/TripwayInputTrip";
export default {
  name: "TripwaySidebar",
  components: {
    TripwayInputParagraph,
    TripwayInputTrip,
  },
  data() {
    return {};
  },
  props: {
    tab: String,
    bid: Number,
    type: String,
  },
};
</script>
<style>
.parasidebar {
  position: absolute;
  top: 200px;
  left: 10vw;
  /* background-color: rgb(188, 190, 185); */
  height: 300px;
  width: 250px;
}
.tripsidebar {
  position: absolute;
  top: 200px;
  left: 10vw;
  /* background-color: rgb(188, 190, 185); */
  height: 300px;
  width: 220px;
}
</style>
