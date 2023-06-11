<template>
  <div style="width: 100%">
    <div id="map4" class="map"></div>
  </div>
</template>
<script>
import { mapState } from "vuex";
import { getAttraction } from "@/api/trip";

const tripwayStore = "tripwayStore";
export default {
  name: "KakaoMapMultimarker",
  mounted() {
    if (window.kakao && window.kakao.maps) {
      setTimeout(() => {
        this.initMap();
      }, 100);
    } else {
      setTimeout(() => {
        this.makeScript();
      }, 100);
    }
  },
  data() {
    return {
      tmp: null,
      markerPosition: null,
      places: [],
    };
  },
  props: {
    issidebar: String,
    isModal: String,
    place: null,
  },
  computed: {
    ...mapState(tripwayStore, ["attractions", "selectedDay"]),
  },
  watch: {
    async attractions() {
      if (this.attractions != [] && window.kakao && window.kakao.maps) {
        await this.getAttractions(this.attractions);
      }
    },
    async selectedDay() {
      if (this.attractions != [] && window.kakao && window.kakao.maps) {
        await this.getAttractions(this.attractions);
      } else if (this.propsattractions != [] && window.kakao && window.kakao.maps) {
        await this.getAttractions(this.propsattractions);
      }
    },
    places() {
      if (this.places.length) {
        this.makeMarker();
      }
    },
  },

  methods: {
    initMap() {
      var mapContainer = document.getElementById("map4"),
        mapOption = {
          center: new kakao.maps.LatLng(33.450701, 126.570667),
          level: 3,
        };
      var map = new kakao.maps.Map(mapContainer, mapOption);
      var markerPosition = new kakao.maps.LatLng(33.450701, 126.570667);
      var marker = new kakao.maps.Marker({
        position: markerPosition,
      });
      marker.setMap(map);
    },
    makeScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=469f46b71da77be6f7ecd3bf628f083a";
      document.head.appendChild(script);
    },
    makeMarker() {
      var mapContainer = document.getElementById("map4"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(this.places[0].Ma, this.places[0].La), // 지도의 중심좌표
          level: 11, // 지도의 확대 레벨
        };
      var map = new kakao.maps.Map(mapContainer, mapOption);

      var linePath = [];
      this.places.forEach((place) => {
        linePath.push(new kakao.maps.LatLng(place.Ma, place.La));
      });
      var polyline = new kakao.maps.Polyline({
        path: linePath, // 선을 구성하는 좌표배열 입니다
        strokeWeight: 5, // 선의 두께 입니다
        strokeColor: "#1E85E5", // 선의 색깔입니다
        strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
        strokeStyle: "solid", // 선의 스타일입니다
      });

      for (const place of this.places) {
        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: map, // 마커를 표시할 지도
          position: place, // 마커를 표시할 위치
        });
        this.tmp = marker;
      }
      polyline.setMap(map);
    },

    async getAttractions(attractions) {
      this.places = [];
      for await (const attraction of attractions) {
        if (attraction.day === this.selectedDay) {
          await getAttraction(
            attraction.content_id,
            ({ data }) => {
              this.markerPosition = new kakao.maps.LatLng(data.latitude, data.longitude);
              this.places.push(this.markerPosition);
            },
            (error) => {
              console.log(error);
            }
          );
        }
      }
      this.fin = true;
    },
  },
};
</script>

<style>
.area-search-form {
  margin-top: 100px;
  width: 80%;
  margin: 0 auto;
}

.search-item {
  width: 30%;
}
.map {
  width: 80%;
  height: 400px;
  margin: 0 auto;
}
</style>
