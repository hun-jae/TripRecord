<template>
  <ul class="list">
    <li class="underline-list">
      {{ this.attraction.title }}
      <button @click="onClick" size="sm" variant="outline-primary" style="float: right">
        <v-icon class="ml-3" size="medium">mdi-check-circle</v-icon>
      </button>
    </li>
  </ul>
</template>

<script>
import { mapActions } from "vuex";
const hotplaceStore = "hotplaceStore";
export default {
  name: "HotplaceSearchListItem",
  data() {
    return {};
  },
  props: {
    attraction: {},
  },
  methods: {
    ...mapActions(hotplaceStore, ["hotplaceSet"]),

    onClick() {
      this.hotplaceSet(this.attraction);
    },
  },
};
</script>
<style>
.ul.list {
  list-style: none;
  margin: 0px;
  padding: 0px;
  width: 90%;
}
.ul.list li {
  padding: 5px 0px 5px 5px;
  margin-bottom: 5px;
  border-bottom: 1px solid #efefef;
  font-size: 12px;
}

ul.list li:last-child {
  border-bottom: 0px;
}

ul.mylist li:before {
  content: ">";
  display: inline-block;
  vertical-align: middle;
  padding: 0px 5px 6px 0px;
}
</style>
