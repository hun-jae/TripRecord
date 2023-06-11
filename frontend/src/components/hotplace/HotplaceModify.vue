<template>
  <b-container class="bv-example-row mt-3">
    <hotplace-input-paragraph-vue
      type="modify"
      :bid="this.$route.params.bid"
      :contentId="this.$route.params.contentId"
      :hotplaceTitle="this.$route.params.hotplaceTitle"
    ></hotplace-input-paragraph-vue>
  </b-container>
</template>

<script>
import HotplaceInputParagraphVue from "./item/HotplaceInputParagraph.vue";
export default {
  name: "HotplaceModify",
  components: {
    HotplaceInputParagraphVue,
  },
};
</script>

<style></style>
