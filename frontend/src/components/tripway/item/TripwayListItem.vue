<template>
  <b-tr>
    <b-td>{{ bid }}</b-td>
    <b-th class="text-left">
      <router-link :to="{ name: 'tripwayview', params: { bid: bid } }">{{
        title
      }}</router-link>
    </b-th>
    <b-td>{{ uid }}</b-td>
    <!--<b-td>{{ regtime | dateFormat }}</b-td>-->
  </b-tr>
</template>

<script>
//import moment from "moment";

export default {
  name: "TripwayListItem",
  props: {
    bid: Number,
    uid: String,
    title: String,
    numHeart: Number,
    //regtime: String,
  },
  filters: {
    // dateFormat(regtime) {
    //   return moment(new Date(regtime)).format("YY.MM.DD");
    // },
  },
};
</script>

<style></style>
