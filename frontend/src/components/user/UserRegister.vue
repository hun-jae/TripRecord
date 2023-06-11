<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col class="mt-5">
        <h3 style="font-weight: bolder">회원가입</h3>
      </b-col>
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-card class="text-center mt-3 mx-auto" style="max-width: 40rem" align="left">
          <b-form class="text-left">
            <b-alert show variant="danger" v-if="isJoinError">{{ joinErrorType }}</b-alert>
            <b-form-group label="아이디:" label-for="userid">
              <b-form-input
                id="userid"
                v-model="user.uid"
                required
                placeholder="아이디 입력...."
                @keyup.enter="join"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="비밀번호:" label-for="userpwd">
              <b-form-input
                type="password"
                id="userpwd"
                v-model="user.pw"
                required
                placeholder="비밀번호 입력...."
                @keyup.enter="join"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이름:" label-for="username">
              <b-form-input
                id="userpwd"
                v-model="user.name"
                required
                placeholder="이름 입력...."
                @keyup.enter="join"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="이메일:" label-for="useremail">
              <b-form-input
                type="email"
                id="useremail"
                v-model="user.email"
                required
                placeholder="이메일 입력...."
                @keyup.enter="join"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="나이:" label-for="userage">
              <b-form-input
                type="number"
                id="userage"
                v-model="user.age"
                required
                placeholder="나이 입력...."
                @keyup.enter="join"
              ></b-form-input>
            </b-form-group>
            <b-button type="button" variant="primary" class="m-1 text-white" @click="join"
              >회원가입</b-button
            >
            <b-button type="button" variant="light" class="m-1" @click="movePage">로그인</b-button>
          </b-form>
        </b-card>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserRegister",
  data() {
    return {
      user: {
        uid: null,
        pw: null,
        name: null,
        email: null,
        age: null,
      },
    };
  },
  computed: {
    ...mapState(memberStore, ["isJoin", "isJoinError", "joinErrorType"]),
  },
  methods: {
    ...mapActions(memberStore, ["userJoin", "setJoinError", "setJoinErrorReset"]),
    async join() {
      if (!this.user.uid || !this.user.pw || !this.user.email || !this.user.age) {
        this.setJoinError("모든 정보를 입력해주세요.");
      } else {
        const validateEmail = /^[A-Za-z0-9_\\.\\-]+@[A-Za-z0-9\\-]+\.[A-Za-z0-9\\-]+/;
        if (!validateEmail.test(this.user.email) || !this.user.email) {
          this.setJoinError("이메일 형식이 잘못되었습니다.");
        } else {
          await this.userJoin(this.user);
          if (this.isJoin) {
            alert("회원가입에 성공했습니다!");
            this.$router.push({ name: "main" });
          }
        }
      }
    },
    movePage() {
      this.$router.push({ name: "login" });
    },
  },
  created() {
    this.setJoinErrorReset();
  },
};
</script>

<style></style>
