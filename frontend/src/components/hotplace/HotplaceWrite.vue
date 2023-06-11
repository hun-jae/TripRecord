<template>
  <b-container class="bv-example-row mt-3">
    <hotplace-input-paragraph
      type="register"
      :contentId="this.$route.params.contentId"
      :hotplaceTitle="this.$route.params.hotplaceTitle"
    />
  </b-container>
</template>

<script>
import HotplaceInputParagraph from "./item/HotplaceInputParagraph.vue";
export default {
  name: "HotplaceWrite",
  components: { HotplaceInputParagraph },
  data() {
    return {
      dialog: false,
    };
  },
};
</script>

<style></style>
