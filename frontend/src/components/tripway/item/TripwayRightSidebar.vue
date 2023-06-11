<template>
  <div>
    <b-sidebar id="sidebar-right" title="" right shadow backdrop>
      <div class="px-3 py-2">
        <trip-search :day="day" issidebar="true"></trip-search>
      </div>
    </b-sidebar>
  </div>
</template>
<script>
import TripSearch from "../../TripSearch.vue";

export default {
  name: "TripwawyRightSidebar",
  components: {
    TripSearch,
  },
  date() {
    return {};
  },
  props: {
    day: Number,
  },
  computed: {},
  created() {},
};
</script>
