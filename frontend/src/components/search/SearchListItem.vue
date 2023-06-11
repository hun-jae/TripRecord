<template>
  <div>
    <!-- 전체 -->
    <v-card
      max-width="400"
      class="mb-5"
      v-if="type === 'place'"
      @click="setPlace"
    >
      <v-img
        v-if="place.imgSrc != null && place.imgSrc != ``"
        class="align-end text-white"
        :src="place.imgSrc"
        height="200px"
        cover
      ></v-img>
      <v-img
        v-else
        class="align-end text-white"
        src="@/assets/img/춘식.png"
        height="200px"
        cover
      ></v-img>
      <v-card-title height="50%" style="font-weight: 900" class="text-truncate">
        {{ place.title }}
      </v-card-title>
      <v-card-subtitle
        style="font-weight: 700; font-size: 1.1em"
        class="text-truncate"
      >
        <span class="me-1">{{ place.addr }} </span>
      </v-card-subtitle>
    </v-card>

    <!-- 여행경로 -->
    <v-card
      max-width="400"
      class="mb-5"
      v-if="type === 'tripway'"
      @click="viewTripway(place)"
    >
      <v-img
        v-if="place.imgSrc != null && place.imgSrc != ``"
        class="align-end text-white"
        :src="place.imgSrc"
        height="200px"
        cover
      ></v-img>
      <v-img
        v-else
        class="align-end text-white"
        src="@/assets/img/춘식.png"
        height="200px"
        cover
      ></v-img>
      <v-card-title>
        <template>
          <router-link
            :to="{ name: 'hotplaceview', params: { bid: place.bid } }"
            class="text-truncate body-2"
          >
            <div style="font-weight: 900; font-size: 1.3em; color: black">
              {{ place.title }}
            </div>
          </router-link>
        </template>
      </v-card-title>
      <v-card-subtitle class="text-truncate">
        <span class="me-1">작성자 : {{ place.uid }}</span>
      </v-card-subtitle>
      <v-row>
        <v-col>
          <v-card-text
            class="text-truncate text-left"
            style="font-weight: 700; font-size: 1.1em"
          >
            {{ place.content }}
          </v-card-text>
        </v-col>
      </v-row>
    </v-card>

    <!-- 핫플레이스 -->
    <v-card
      max-width="400"
      class="mb-5"
      v-if="type === 'hotplace'"
      @click="viewArticle(place)"
    >
      <v-img
        v-if="place.imgSrc != null && place.imgSrc != ``"
        class="align-end text-white"
        :src="place.imgSrc"
        height="200px"
        cover
      ></v-img>
      <v-img
        v-else
        class="align-end text-white"
        src="@/assets/img/춘식.png"
        height="200px"
        cover
      ></v-img>
      <v-card-title>
        <template>
          <router-link
            :to="{ name: 'hotplaceview', params: { bid: place.bid } }"
            class="text-truncate body-2"
          >
            <div style="font-weight: 900; font-size: 1.3em; color: black">
              {{ place.title }}
            </div>
          </router-link>
        </template>
      </v-card-title>
      <v-card-subtitle class="text-truncate">
        <span class="me-1">작성자 : {{ place.uid }}</span>
      </v-card-subtitle>
      <v-row>
        <v-col>
          <v-card-text
            class="text-truncate text-left"
            style="font-weight: 700; font-size: 1.1em"
          >
            {{ place.content }}
          </v-card-text>
        </v-col>
      </v-row>
    </v-card>
  </div>
</template>

<script>
import { mapActions } from "vuex";

const searchStore = "searchStore";
export default {
  name: "SearchListItem",
  data() {
    return {};
  },
  props: {
    place: [],
    type: String,
    from: String,
  },
  component: {},
  created() {},
  computed: {},
  methods: {
    ...mapActions(searchStore, ["nowPlaceSet"]),
    viewArticle(place) {
      this.$router.push({
        name: "hotplaceview",
        params: { bid: place.bid },
      });
    },
    viewTripway(place) {
      console.log(`${typeof place.bid}`);
      this.$router.push({
        name: "tripwayview",
        params: { bid: place.bid },
      });
    },
    setPlace() {
      this.nowPlaceSet(this.place);
      this.$emit("input", {
        isShow: true,
        placeInfo: this.place,
      });
    },
  },
};
</script>
