<template class="container">
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit">
        <v-card
          class="mt-2 mb-5 p-3 text-center"
          variant="tonal"
          style="width: 100%; margin: 0 auto"
        >
          <div class="selected">{{ this.hotplaceTitle }}</div></v-card
        >
        <b-form-group
          id="title-group"
          label="제목:"
          label-for="title"
          description="제목을 입력하세요."
        >
          <b-form-input
            id="title"
            v-model="article.title"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용:" label-for="content">
          <b-form-textarea
            id="content"
            v-model="article.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>

        <v-col class="text-center">
          <v-btn
            type="submit"
            size="lg"
            color="blue-darken-1"
            class="m-1"
            v-if="this.type === 'register'"
            >글작성</v-btn
          >
          <v-btn type="submit" variant="" class="m-1" v-else>글수정</v-btn>
        </v-col>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { mapState } from "vuex";
import { writeArticle, modifyArticle, getArticle } from "@/api/hotplace";
const memberStore = "memberStore";
export default {
  name: "HotplaceInputParagraph",
  data() {
    return {
      article: {
        bid: 0,
        title: "",
        content: "",
      },
    };
  },
  props: {
    type: { type: String },
    bid: Number,
    contentId: Number,
    hotplaceTitle: String,
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  created() {
    if (this.type === "modify") {
      let param = this.bid;
      getArticle(
        param,
        ({ data }) => {
          this.article = data;
        },
        (error) => {
          console.log(error);
        }
      );
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      err &&
        !this.article.title &&
        ((msg = "제목 입력해주세요"), (err = false), this.$refs.title.focus());
      err &&
        !this.article.content &&
        ((msg = "내용 입력해주세요"), (err = false), this.$refs.content.focus());

      if (!err) alert(msg);
      else this.type === "register" ? this.registArticle() : this.modifyArticle();
    },

    registArticle() {
      let param = {
        uid: this.userInfo.uid,
        title: this.article.title,
        content: this.article.content,
        contentId: this.contentId,
      };
      writeArticle(
        param,
        ({ data }) => {
          let msg = "등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
          }
          alert(msg);
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    modifyArticle() {
      let param = {
        bid: this.article.bid,
        uid: this.article.uid,
        title: this.article.title,
        content: this.article.content,
        contentId: this.contentId,
      };
      modifyArticle(
        param,
        ({ data }) => {
          let msg = "수정 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "수정이 완료되었습니다.";
          }
          alert(msg);
          // 현재 route를 /list로 변경.
          this.moveList();
        },
        (error) => {
          console.log(error);
        }
      );
    },
    moveList() {
      this.$router.push({ name: "hotplacelist" });
    },
  },
};
</script>

<style>
.container {
  width: 80%;
}
.selected {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(231, 27, 139, 0.3) 30%);
  font-weight: 800;
  font-size: 1.3rem;
  letter-spacing: 0.2rem;
}
</style>
