import Vue from "vue";
import VueRouter from "vue-router";
import AppMain from "@/views/AppMain";
import store from "@/store";

Vue.use(VueRouter);

// https://v3.router.vuejs.org/kr/guide/advanced/navigation-guards.html
const onlyAuthUser = async (to, from, next) => {
  const checkUserInfo = store.state["memberStore/checkUserInfo"];
  const checkToken = store.getters["memberStore/checkToken"];
  let token = sessionStorage.getItem("access-token");

  if (checkUserInfo != null && token) {
    await store.dispatch("memberStore/getUserInfo", token);
  }
  if (!checkToken || checkUserInfo === null) {
    alert("로그인이 필요한 페이지입니다.");
    // next({ name: "login" });
    router.push({ name: "login" });
  } else {
    next();
  }
};

const routes = [
  {
    path: "/",
    name: "main",
    component: AppMain,
  },
  {
    path: "/search",
    name: "search",
    component: () => import(/* webpackChunkName: "search" */ "@/views/AppSearch"),
  },
  {
    path: "/hotplace",
    name: "hotplace",
    component: () => import(/* webpackChunkName: "hotplace" */ "@/views/AppHotplace"),
    redirect: "/hotplace/list",
    children: [
      {
        path: "list",
        name: "hotplacelist",
        component: () =>
          import(/* webpackChunkName: "hotplace" */ "@/components/hotplace/HotplaceList"),
      },
      {
        path: "write",
        name: "hotplacewrite",
        beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "hotplace" */ "@/components/hotplace/HotplaceWrite"),
      },
      {
        path: "view/:bid",
        name: "hotplaceview",
        beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "hotplace" */ "@/components/hotplace/HotplaceView"),
      },
      {
        path: "modify",
        name: "hotplacemodify",
        beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "hotplace" */ "@/components/hotplace/HotplaceModify"),
      },
      {
        path: "delete/:bid",
        name: "hotplacedelete",
        beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "hotplace" */ "@/components/hotplace/HotplaceDelete"),
      },
    ],
  },
  {
    path: "/user",
    name: "user",
    component: () => import(/* webpackChunkName: "user" */ "@/views/AppUser"),
    children: [
      {
        path: "join",
        name: "join",
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserRegister"),
      },
      {
        path: "login",
        name: "login",
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserLogin"),
      },
      {
        path: "mypage",
        name: "mypage",
        beforeEnter: onlyAuthUser,
        component: () => import(/* webpackChunkName: "user" */ "@/components/user/UserMyPage"),
      },
    ],
  },
  {
    path: "/tripway",
    name: "tripway",
    component: () => import(/* webpackChunkName: "tripway" */ "@/views/AppTripway"),
    redirect: "/tripway/list",
    children: [
      {
        path: "list",
        name: "tripwaylist",
        component: () =>
          import(/* webpackChunkName: "tripway" */ "@/components/tripway/TripwayList"),
      },
      {
        path: "write",
        name: "tripwaywrite",
        //beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "tripway" */ "@/components/tripway/TripwayWrite"),
      },
      {
        path: "view/:bid",
        name: "tripwayview",
        beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "tripway" */ "@/components/tripway/TripwayView"),
      },
      {
        path: "modify",
        name: "tripwaymodify",
        beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "tripway" */ "@/components/tripway/TripwayModify"),
      },
      {
        path: "delete/:bid",
        name: "tripwaydelete",
        beforeEnter: onlyAuthUser,
        component: () =>
          import(/* webpackChunkName: "tripway" */ "@/components/tripway/TripwayDelete"),
      },
    ],
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
