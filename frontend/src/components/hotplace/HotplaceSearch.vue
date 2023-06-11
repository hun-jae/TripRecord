<template>
  <div>
    <b-input-group class="mt-3">
      <b-form-input
        v-model="keyword"
        placeholder="핫플레이스 장소나 키워드를 검색해보세요."
        size="lg"
        @keyup.enter="onSubmit"
      ></b-form-input>
      <b-input-group-append>
        <b-button variant="outline-primary" @click="onSubmit"
          ><b-icon-search></b-icon-search
        ></b-button>
      </b-input-group-append>
    </b-input-group>
  </div>
</template>

<script>
export default {
  name: "HotplaceSearch",
  data() {
    return {
      keyword: "",
    };
  },
  methods: {
    onSubmit() {
      // if (this.keyword == "" || this.keyword.length == 0) {
      //   alert("검색어를 입력해주세요.");
      // } else {
      this.$emit("search", this.keyword);
      // }
    },
  },
};
</script>
