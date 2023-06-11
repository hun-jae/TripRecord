<template>
  <div class="about">
    <div>
      
      <b-carousel
        id="carousel-1"
        v-model="slide"
        :interval="3000"
        controls
        indicators
        background="#ababab"
        img-width="500px"
        img-height="280px"
        style="text-shadow: 1px 1px 2px #333"

      >
        <!-- Slides with img slot -->
        <!-- Note the classes .d-block and .img-fluid to prevent browser default image alignment -->

        <b-carousel-slide>
          <template #img>
            <img width="100%" height="400" src="@/assets/img/기차.jpg" alt="image slot" />
          </template>
        </b-carousel-slide>
        <b-carousel-slide>
          <template #img>
            <img width="100%" height="400" src="@/assets/img/바다.jpg" alt="image slot" />
          </template>
        </b-carousel-slide>
        <b-carousel-slide>
          <template #img>
            <img width="100%" height="400" src="@/assets/img/산.jpg" alt="image slot" />
          </template>
        </b-carousel-slide>
        <b-carousel-slide>
          <template #img>
            <img width="100%" height="400" src="@/assets/img/해변.jpg" alt="image slot" />
          </template>
        </b-carousel-slide>
        
      </b-carousel>
      
    </div>
  </div>
</template>
<script>
import Vue from "vue";
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);

export default {
  name: "TheMainSlider",
  data() {
    return {
      slide: 0,
      sliding: null,
    };
  },
};
</script>
<style></style>
