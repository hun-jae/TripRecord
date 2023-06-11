<template>
  <v-card class="mt-2">
    <v-row v-if="notselected === 'true'" class="underline-list">
      <v-col cols="10" class="text-truncate" style="max-width: 70%">{{
        this.attraction.title
      }}</v-col>
      <v-col cols="2" @click="onClick" size="sm" variant="outline-primary" style="float: right">
        <v-icon class="ml-3" size="medium" style="cursor: pointer"> mdi-plus-circle</v-icon>
      </v-col>
    </v-row>
  </v-card>
</template>

<script>
import { mapActions, mapState } from "vuex";
const tripwayStore = "tripwayStore";

export default {
  name: "TripSearchList",
  data() {
    return {
      notselected: "true",
    };
  },
  props: {
    attraction: {},
    day: Number,

    //attractions에 추가를 할건데 attraction에다가 day속성을 붙여서 추가하면 될듯?
    //day를 props를 통해서 전달받자
    //day는 순서대로만 지울 수 있다.
  },
  created() {},
  computed: {
    ...mapState(tripwayStore, ["attractions"]),
  },
  methods: {
    ...mapActions(tripwayStore, ["attractionsSet", "selectedPlaceSet"]),

    onClick() {
      let attraction_data = {
        day: this.day,
        content_id: this.attraction.contentId,
        idx: this.getIdx(this.day),
        title: this.attraction.title,
        first_image: this.attraction.imgSrc,
      };
      this.attractionsSet(attraction_data);
      this.notselected = "false";
      this.selectedPlaceSet(this.attraction);
    },
    getIdx(day) {
      var idx = 0;
      this.attractions.forEach((attraction) => {
        if (attraction.day === day) idx = idx + 1;
      });
      return idx;
    },
  },
};
</script>
<style>
.ul.list {
  list-style: none;
  margin: 0px;
  padding: 0px;
  width: 90%;
}
.ul.list li {
  padding: 5px 0px 5px 5px;
  margin-bottom: 5px;
  border-bottom: 1px solid #efefef;
  font-size: 12px;
}

ul.list li:last-child {
  border-bottom: 0px;
}

ul.mylist li:before {
  content: ">";
  display: inline-block;
  vertical-align: middle;
  padding: 0px 5px 6px 0px;
}
</style>
