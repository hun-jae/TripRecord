<template>
  <div class="jb-box">
    <video muted autoplay loop>
      <source src="@/assets/video/sky.mp4" type="video/mp4" />
    </video>
    <div class="jb-text text-center">
      <h1 class="top-text">세상의 모든 여행</h1>
      <h1 class="underline-hotpink">Trip Record</h1>
      <the-search-bar class="mt-5 mx-5"></the-search-bar>
    </div>
  </div>
</template>

<script>
// import TheMainSlider from "@/components/TheMainSlider.vue";
// import TripSearch from "@/components/TripSearch.vue";
import TheSearchBar from "@/components/TheSearchBar.vue";

export default {
  name: "AppMain",
  components: {
    //TripSearch,
    TheSearchBar,
    // TheMainSlider,
  },
  props: {
    msg: String,
  },
};
</script>

<style scoped>
.top-text {
  margin-top: 10%;
  font-weight: 700;
  font-size: 2.6rem;
}
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(79, 57, 291, 0.3) 30%);
  font-weight: 700;
  font-size: 6.2rem;
  letter-spacing: 0.2rem;
}
body {
  padding: 0px;
  margin: 0px;
}
video {
  width: 100%;
  height: 100vh;
  object-fit: cover;
}
.jb-box::after {
  width: 100vw;
  height: 100%;
  overflow: hidden;
  margin: 0px auto;
  position: relative;
}
.jb-text {
  position: absolute;
  top: 10%;
  width: 100%;
}
.jb-text p {
  margin-top: -24px;
  text-align: center;
  font-size: 48px;
  color: #ffffff;
}
</style>
