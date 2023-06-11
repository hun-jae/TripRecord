<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col>
        <div class="title_mypage">마이페이지</div>
      </b-col>
      <!-- <b-col>
        <b-alert variant="secondary" show><h3>내정보</h3></b-alert>
      </b-col> -->
    </b-row>
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <!-- <b-jumbotron> -->
        <!-- <template #header>My Page</template> -->

        <!-- <template #lead> 내 정보 확인페이지입니다. </template> -->

        <!-- <hr class="my-4" /> -->

        <b-container class="mt-4 b_container_design">
          <b-row class="row_design">
            <b-col cols="2"></b-col>
            <b-col cols="4" align-self="end">아이디</b-col
            ><b-col cols="4" align-self="start">{{ userInfo.uid }}</b-col>
            <b-col cols="2"></b-col>
          </b-row>
          <b-row class="row_design">
            <b-col cols="2"></b-col>
            <b-col cols="4" align-self="end">이름</b-col
            ><b-col cols="4" align-self="start">{{ userInfo.name }}</b-col>
            <b-col cols="2"></b-col>
          </b-row>
          <b-row class="row_design">
            <b-col cols="2"></b-col>
            <b-col cols="4" align-self="end">이메일</b-col
            ><b-col cols="4" align-self="start">{{ userInfo.email }}</b-col>
            <b-col cols="2"></b-col>
          </b-row>
        </b-container>
        <!-- <hr class="my-4" /> -->

        <!-- </b-jumbotron> -->
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "UserMyPage",
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
};
</script>

<style>
.title_mypage {
  font-size: 30px;
  font-weight: bold;
  padding-bottom: 3%;
  border-bottom: 1px solid #b9b9b9;
  margin-bottom: 3%;
  padding-top: 4%;
  width: 60%;
  margin: 0 auto;
}

.edit_button {
  background-color: rgb(0, 123, 255);
  border: none;
  color: white;
}

.edit_button:hover {
  background-color: rgb(22, 84, 151);
  color: white;
}

.delete_button {
  background-color: rgb(241, 61, 61);
  border: none;
  color: white;
}

.delete_button:hover {
  background-color: rgb(189, 27, 27);
  border: none;
  color: white;
}

.row_design * {
  font-size: 20px;
  margin-bottom: 1%;
}

.b_container_design {
  padding-bottom: 3%;
  margin-bottom: 5%;
  border-bottom: 1px solid #b9b9b9;
  width: 90%;
}
</style>
