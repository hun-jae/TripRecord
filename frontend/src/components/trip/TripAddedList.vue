<template>
  <div>
    <ul id="example-1">
      <li>
        {{ this.attraction.title }}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "TripSearchList",
  props: {
    attraction: {},
  },
};
</script>
