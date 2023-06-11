<template>
  <div>
    <v-parallax src="@/assets/img/airplane.png" height="300">
      <div class="d-flex flex-column fill-height justify-center align-center mt-5 text-white">
        <h1 class="text-h2 mt-5 title mx-auto" style="font-weight: 900; font-size: 3em">
          '{{ this.keyword }}'에 대한 검색결과
        </h1>
      </div>
    </v-parallax>
    <search-list-vue></search-list-vue>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import SearchListVue from "../components/search/SearchList.vue";
const searchStore = "searchStore";
export default {
  name: "AppSearch",
  components: { SearchListVue },
  computed: {
    ...mapState(searchStore, ["keyword"]),
  },
  methods: {
    ...mapActions(searchStore, ["keywordSet"]),
  },
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(72, 190, 233, 0.3) 30%);
}
.title {
  position: relative;
  color: #fff;
  font-weight: 700;
  font-size: 4.4rem;
  line-height: 1.2;
  display: block;
  margin-top: 500;
}

.parallex {
  object-fit: cover;
}
</style>
