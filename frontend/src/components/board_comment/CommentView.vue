<template>
  <div style="margin: auto; width: 100vh">
    <div id="form-commentInfo">
      <div id="comment-count">댓글 {{ comments.length }}</div>
      <div id="comment-input-div">
        <input v-model="input_comment" id="comment-input" placeholder="댓글을 입력해 주세요." />
        <button
          style="float: right; width: 8vh; background-color: #1e85e5"
          id="submit"
          @click="comment_submit"
        >
          등록
        </button>
      </div>
    </div>
    <div id="comments">
      <div class="commentList" v-for="(comment, index) in comments" :key="comment.cid">
        <div class="name">
          {{ comment.uid }}
          <v-btn
            v-if="comment.uid === userInfo.uid"
            @click="deleteComment(index, comment.cid)"
            class="m-1"
            color="rgba(0, 123, 255,0.2)"
            style="color: grey"
            >삭제</v-btn
          >
        </div>
        <div class="inputValue">{{ comment.comment }}</div>
        <hr width />
      </div>
    </div>
  </div>
</template>

<script>
import { listComment, writeComment, deleteComment } from "@/api/comment";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  name: "CommentView",
  components: {},
  data() {
    return {
      input_comment: "",
      comments: [],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  props: {
    bid: Number,
    type: String,
  },
  async created() {
    let param = {
      type: this.type,
      bid: this.bid,
    };
    if (param.bid != -1) {
      await listComment(
        param,
        ({ data }) => {
          this.comments = data;
        },
        (error) => {
          console.log(error);
        }
      );
    }
  },
  watch: {
    async bid() {
      let param = {
        type: this.type,
        bid: this.bid,
      };
      if (param.bid != -1) {
        await listComment(
          param,
          ({ data }) => {
            this.comments = data;
          },
          (error) => {
            console.log(error);
          }
        );
      }
    },
  },
  methods: {
    async deleteComment(index, cid) {
      let param = {
        type: this.type,
        cid: cid,
      };
      await deleteComment(
        param,
        ({ data }) => {
          let msg = "댓글 삭제 처리시 문제가 발생했습니다.";

          if (data === "success") {
            msg = "댓글 삭제 완료";
            this.comments = this.comments.splice(index, 1);
            this.$router.go(0);
          }
          alert(msg);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    async comment_submit() {
      let param = {
        type: this.type,
        comment: {
          uid: this.userInfo.uid,
          bid: this.bid,
          comment: this.input_comment,
        },
      };
      await writeComment(
        param,
        ({ data }) => {
          let msg = "댓글 등록 처리시 문제가 발생했습니다.";
          if (data === "success") {
            msg = "댓글 등록 완료";
            this.comments.push(param.comment);
            this.input_comment = "";
            this.$router.go(0);
          }
          alert(msg);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};
</script>

<style>
#form-commentInfo {
  width: 100%;
}

#comment-count {
  margin-bottom: 10px;
}

#comment-input-div {
  width: 100vh;
  height: 3em;
  border: 0.5px solid gray;
  border-radius: 5px 5px 5px 5px / 5px 5px 5px 5px;
}

#comment-input {
  margin-left: 1vh;
  outline: none;
  width: 89.9vh;
  height: 3em;
}

#submit {
  background-color: rgb(0, 128, 255);
  width: 5.5em;
  height: 3.1em;
  font-size: 15px;
  font-weight: bold;
  color: aliceblue;
}

#voteUp,
#voteDown {
  width: 3.5em;
  height: 1.9em;
  background-color: aqua;
}

#comments {
  margin-top: 10px;
}

.eachComment {
  width: 50%;
  margin: 10px;
  padding: 0.5em;
  border-bottom: 1px solid #c1bcba;
}

.name {
  font-size: 1.5em;
  font-weight: bold;
  margin-bottom: 0.3em;
  display: flex;
  justify-content: space-between;
}

.inputValue {
  font-size: 1em;
  font-style: italic;
  margin-left: 20px;
}
</style>
