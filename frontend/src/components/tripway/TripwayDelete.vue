<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글목록</h3></b-alert>
      </b-col>
    </b-row>
    <b-row>
      <b-col><b-alert show variant="danger">삭제처리중...</b-alert></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { deleteArticle } from "@/api/tripway";
import { deleteAttractions } from "@/api/trip";
export default {
  name: "TripwayDelete",
  data() {
    return {};
  },
  watch: {},
  created() {
    let param = this.$route.params.bid;
    deleteAttractions(
      param,
      ({ data }) => {
        this.msg = "삭제 처리시 문제가 발생했습니다.";
        if (data === "success") {
          this.deleteArticle(param);
        }
        this.$router.push({ name: "tripwaylist" });
        // 현재 route를 /list로 변경.
      },
      (error) => {
        console.log(error);
      }
    );
  },
  methods: {
    deleteArticle(bid) {
      deleteArticle(
        bid,
        ({ data }) => {
          console.log(data);
          if (data === "success") {
            this.msg = "삭제 처리 완료";
          }
          alert(this.msg);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style></style>
