<template>
  <b-container class="bv-example-row mt-3" style="max-width: 70%">
    <tripway-input-item type="register" />
  </b-container>
</template>

<script>
import TripwayInputItem from "@/components/tripway/item/TripwayInputItem";

export default {
  name: "TripwayWrite",
  components: {
    TripwayInputItem,
  },
};
</script>

<style></style>
