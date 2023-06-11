import jwtDecode from "jwt-decode";
import router from "@/router";
import { login, signup, findById, tokenRegeneration, logout } from "@/api/member";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    joinErrorType: "",
    isJoin: false,
    isJoinError: false,
    userInfo: null,
    isValidToken: false,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
    checkToken: function (state) {
      return state.isValidToken;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_IS_JOIN: (state, isJoin) => {
      state.isJoin = isJoin;
    },
    SET_IS_JOIN_ERROR: (state, isJoinError) => {
      state.isJoinError = isJoinError;
    },
    SET_JOIN_ERROR_TYPE: (state, joinErrorType) => {
      state.joinErrorType = joinErrorType;
    },
    SET_IS_VALID_TOKEN: (state, isValidToken) => {
      state.isValidToken = isValidToken;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
  },
  actions: {
    async userJoin({ commit }, user) {
      await signup(
        user,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_IS_JOIN", true);
            commit("SET_IS_JOIN_ERROR", false);
          } else {
            commit("SET_IS_JOIN", false);
            commit("SET_IS_JOIN_ERROR", true);
            commit("SET_JOIN_ERROR_TYPE", "회원가입 중 예상치 못한 에러가 발생했습니다.");
          }
        },
        (error) => {
          commit("SET_IS_JOIN", false);
          commit("SET_IS_JOIN_ERROR", true);
          commit("SET_JOIN_ERROR_TYPE", "이미 등록된 아이디입니다.");
          console.log("회원가입실패" + error);
        }
      );
    },
    async userConfirm({ commit }, user) {
      await login(
        user,
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            let refreshToken = data["refresh-token"];
            // console.log("login success token created!!!! >> ", accessToken, refreshToken);
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false, "");
            commit("SET_IS_VALID_TOKEN", true);
            sessionStorage.setItem("access-token", accessToken);
            sessionStorage.setItem("refresh-token", refreshToken);
            // sessionStorage.setItem("userInfo", JSON.stringify(data));
            // console.log("사용자정보", data.userInfo);
            commit("SET_USER_INFO", data.userInfo);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true, "회원가입 중 에러가 발생했습니다.");
            commit("SET_IS_VALID_TOKEN", false);
          }
        },
        (error) => {
          commit("SET_IS_LOGIN", false);
          commit("SET_IS_LOGIN_ERROR", true, "이미 등록된 아이디입니다.");
          commit("SET_IS_VALID_TOKEN", false);
          console.log(error);
        }
      );
    },
    async getUserInfo({ commit, dispatch }, token) {
      let decodeToken = jwtDecode(token);
      await findById(
        decodeToken.uid,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_USER_INFO", data.userInfo);
            // console.log("3. getUserInfo data >> ", data);
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        async (error) => {
          console.log(
            "getUserInfo() error code [토큰 만료되어 사용 불가능.] ::: ",
            error.response.status
          );
          commit("SET_IS_VALID_TOKEN", false);
          await dispatch("tokenRegeneration");
        }
      );
    },
    async tokenRegeneration({ commit, state }) {
      console.log("토큰 재발급 >> 기존 토큰 정보 : {}", sessionStorage.getItem("access-token"));
      await tokenRegeneration(
        JSON.stringify(state.userInfo),
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            console.log("재발급 완료 >> 새로운 토큰 : {}", accessToken);
            sessionStorage.setItem("access-token", accessToken);
            commit("SET_IS_VALID_TOKEN", true);
          }
        },
        async (error) => {
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
          if (error.response.status === 401) {
            console.log("갱신 실패");
            // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
            await logout(
              state.userInfo.userid,
              ({ data }) => {
                if (data.message === "success") {
                  console.log("리프레시 토큰 제거 성공");
                } else {
                  console.log("리프레시 토큰 제거 실패");
                }
                alert("RefreshToken 기간 만료!!! 다시 로그인해 주세요.");
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
                commit("SET_IS_VALID_TOKEN", false);
                router.push({ name: "login" });
              },
              (error) => {
                console.log(error);
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
              }
            );
          }
        }
      );
    },
    async userLogout({ commit }, uid) {
      await logout(
        uid,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_IS_LOGIN", false);
            commit("SET_USER_INFO", null);
            commit("SET_IS_VALID_TOKEN", false);
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },

    setJoinError({ commit }, joinErrorType) {
      commit("SET_IS_JOIN_ERROR", true);
      commit("SET_JOIN_ERROR_TYPE", joinErrorType);
    },

    setJoinErrorReset({ commit }) {
      commit("SET_IS_JOIN_ERROR", false);
      commit("SET_JOIN_ERROR_TYPE", "");
    },

    setLoginErrorReset({ commit }) {
      commit("SET_IS_LOGIN", false);
      commit("SET_IS_LOGIN_ERROR", false);
    },
  },
};

export default memberStore;
