<template>
  <b-container fluid="sm" class="text-center mx-auto">
    <b-input-group
      v-if="navbar === 'true'"
      style="width: fit-content; margin: 0 auto"
      class="text-truncate"
    >
      <b-form-input
        class="search-input"
        size="sm"
        v-model="input"
        placeholder="검색"
        @keyup.enter="onSubmit"
      ></b-form-input>
      <b-input-group-append>
        <b-button size="sm" variant="outline-primary" @click="onSubmit" class="search-button"
          ><b-icon-search></b-icon-search
        ></b-button>
      </b-input-group-append>
    </b-input-group>
    <b-input-group v-else>
      <b-form-input
        class="search-input"
        size="lg"
        v-model="input"
        placeholder="장소나 키워드를 검색해보세요."
        @keyup.enter="onSubmit"
      ></b-form-input>
      <b-input-group-append>
        <b-button variant="primary" @click="onSubmit" class="search-button"
          ><b-icon-search></b-icon-search
        ></b-button>
      </b-input-group-append>
    </b-input-group>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const searchStore = "searchStore";
export default {
  name: "TheSearchBar",
  data() {
    return {
      input: "",
    };
  },
  props: {
    navbar: String,
  },
  computed: {
    ...mapState(searchStore, ["keyword"]),
  },
  methods: {
    ...mapActions(searchStore, ["keywordSet"]),
    onSubmit() {
      if (this.input == "" || this.input.length == 0) {
        alert("검색어를 입력해주세요.");
      } else {
        this.keywordSet(this.input);
        this.input = "";
        if (this.$route.path != "/search") {
          this.$router.push({ name: "search" });
        }
      }
    },
  },
};
</script>

<style>
.search-input {
  width: 80%;
  padding: 2rem 0 2rem 3.5rem;
  border-radius: 5rem;
  border: 0.1rem solid #666;
  box-sizing: border-box;
  font-size: 4vw;
  box-shadow: 0.1rem 0.1rem 0.8rem #00000026;
}
.search-button {
  border-radius: 5rem;
  border: 0.1rem solid #666;
  box-sizing: border-box;
  box-shadow: 0.1rem 0.1rem 0.8rem #00000026;
}
</style>
