<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="light">
      <b-navbar-brand href="#">
        <router-link to="/" class="link" style="font-size: 1.5em; font-weight: 900">
          <img
            src="@/assets/img/춘식.png"
            class="d-inline-block align-middle"
            width="50px"
            alt="TripRecord"
          />TripRecord
        </router-link>
      </b-navbar-brand>

      <b-navbar-toggle target="nav-collapse">
        <template #default="{ expanded }">
          <b-icon v-if="expanded" icon="chevron-bar-up" variant="dark"></b-icon>
          <b-icon v-else icon="chevron-bar-down" variant="dark"></b-icon>
        </template>
      </b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#" class="text-truncate">
            <router-link
              :to="{ name: 'main' }"
              class="mx-3 link"
              style="font-size: 1.1em; font-weight: 900"
            >
              MAIN
            </router-link>
            <router-link
              :to="{ name: 'hotplace' }"
              class="mx-3 link"
              style="font-size: 1.1em; font-weight: 900"
            >
              HOTPLACE
            </router-link>
            <router-link
              :to="{ name: 'tripway', params: { from: tripway } }"
              class="mx-3 link"
              style="font-size: 1.1em; font-weight: 900"
            >
              TRIPWAY
            </router-link>
          </b-nav-item>
        </b-navbar-nav>

        <b-navbar-nav>
          <the-search-bar navbar="true"></the-search-bar>
        </b-navbar-nav>

        <!-- after login -->
        <b-navbar-nav class="ml-auto" v-if="userInfo">
          <b-nav-item-dropdown right>
            <template #button-content>
              <b-avatar :badge="userInfo.uid.charAt(0).toUpperCase()"></b-avatar>
              <span id="userName" class="mr-auto textColor"
                >{{ userInfo.name }}({{ userInfo.uid }})</span
              >
            </template>
            <b-dropdown-item href="#" @click.prevent="onClickLogout">
              <b-icon icon="person-circle" variant="dark"></b-icon>
              로그아웃
            </b-dropdown-item>
            <b-dropdown-item href="#">
              <router-link :to="{ name: 'mypage' }" style="color: black">
                <b-icon icon="key" variant="dark"></b-icon>마이페이지
              </router-link>
            </b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
        <!-- before login -->
        <b-navbar-nav class="ml-auto" v-else>
          <b-nav-item-dropdown right>
            <template #button-content>
              <b-icon icon="people" variant="dark" font-scale="2"></b-icon>
            </template>
            <b-dropdown-item href="#">
              <router-link :to="{ name: 'join' }" style="color: black">
                <b-icon icon="person-circle" variant="dark"></b-icon> 회원가입
              </router-link>
            </b-dropdown-item>
            <b-dropdown-item href="#">
              <router-link :to="{ name: 'login' }" style="color: black">
                <b-icon icon="key" variant="dark"></b-icon>로그인
              </router-link>
            </b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions } from "vuex";
import TheSearchBar from "./TheSearchBar.vue";

const memberStore = "memberStore";

export default {
  name: "TheHeaderNavbar",
  data() {
    return {
      tripway: "여행경로",
      hotplace: "핫플레이스",
    };
  },
  components: {
    TheSearchBar,
  },
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    ...mapActions(memberStore, ["userLogout"]),
    // ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      console.log(this.userInfo.uid);

      this.userLogout(this.userInfo.uid);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "main" });
    },
  },
};
</script>

<style scoped>
#logo {
  width: 120px;
}

.nav-link .link {
  color: black;
}

.textColor {
  color: black;
  text-decoration: none;
}
</style>
