<template>
  <div style="width: 100%">
    <div v-if="issidebar === 'false'" id="map" class="map"></div>
    <div v-else-if="isModal === 'true'" id="map2" class="map2"></div>
    <div v-else-if="place != null" id="map3" class="map3"></div>
    <div v-else id="map1" class="map1"></div>
  </div>
</template>
<script>
import { mapState } from "vuex";
const hotplaceStore = "hotplaceStore";
const tripwayStore = "tripwayStore";
const searchStore = "searchStore";
export default {
  name: "KakaoMap",
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
  props: {
    issidebar: String,
    isModal: String,
    place: null,
  },
  computed: {
    ...mapState(searchStore, ["nowPlace"]),
    ...mapState(hotplaceStore, ["hotplace"]),
    ...mapState(tripwayStore, ["selectedPlace"]),
  },
  watch: {
    hotplace(hotplace) {
      if (hotplace != null && window.kakao && window.kakao.maps) {
        this.makeMarker(hotplace);
      }
    },
    nowPlace(now) {
      console.log("장소바껴용><");
      this.makeMarker(now);
    },
    selectedPlace() {
      if (this.selectedPlace != null && window.kakao && window.kakao.maps) {
        this.makeMarkerSidebar(this.selectedPlace);
      }
    },
  },

  methods: {
    initMap() {
      if (this.issidebar === "false" || this.isModal === "true" || this.place != null) {
        if (this.place != null) {
          var container = document.getElementById("map3");
          var options = {
            center: new kakao.maps.LatLng(this.place.latitude, this.place.longitude),
            level: 3,
          };
          var map = new kakao.maps.Map(container, options);
          var markerPosition = new kakao.maps.LatLng(this.place.latitude, this.place.longitude);
          // 커스텀 오버레이를 생성합니다
          // 마커를 생성합니다
          var marker = new kakao.maps.Marker({
            position: markerPosition,
          });
          marker.setMap(map);
        } else {
          if (this.issidebar === "false") {
            var container2 = document.getElementById("map");
          } else if (this.isModal === "true") {
            container2 = document.getElementById("map2");
          }
          var options2 = {
            center: new kakao.maps.LatLng(33.450701, 126.570667),
            level: 3,
          };
          var map2 = new kakao.maps.Map(container2, options2);
          var marker2 = new kakao.maps.Marker({
            position: map2.getCenter(),
          });
          marker2.setMap(map2);
        }
      }
      //sidebar
      else {
        var mapContainer1 = document.getElementById("map1"),
          mapOption1 = {
            center: new kakao.maps.LatLng(33.450701, 126.570667),
            level: 3,
          };
        var map1 = new kakao.maps.Map(mapContainer1, mapOption1);
        var markerPosition1 = new kakao.maps.LatLng(33.450701, 126.570667);
        var marker1 = new kakao.maps.Marker({
          position: markerPosition1,
        });
        marker1.setMap(map1);
      }
    },
    makeScript() {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=469f46b71da77be6f7ecd3bf628f083a";
      document.head.appendChild(script);
    },
    makeMarker(place) {
      var mapContainer = document.getElementById("map2"),
        mapOption = {
          center: new kakao.maps.LatLng(place.latitude, place.longitude),
          level: 3,
        };
      var map = new kakao.maps.Map(mapContainer, mapOption);

      var markerPosition = new kakao.maps.LatLng(place.latitude, place.longitude);
      // 커스텀 오버레이를 생성합니다
      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        position: markerPosition,
      });
      marker.setMap(map);
    },
    makeMarkerSidebar(place) {
      var mapContainer = document.getElementById("map1"),
        mapOption = {
          center: new kakao.maps.LatLng(place.latitude, place.longitude),
          level: 3,
        };
      var map = new kakao.maps.Map(mapContainer, mapOption);

      var markerPosition = new kakao.maps.LatLng(place.latitude, place.longitude);
      // 커스텀 오버레이를 생성합니다
      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        //map: map,
        position: markerPosition,
      });
      marker.setMap(map);
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
.map1 {
  width: 250px;
  height: 200px;
}
.map2 {
  width: 90%;
  margin: 0 auto;
  height: 30vh;
}
.map3 {
  width: 95%;
  height: 30vh;
  margin: 0 auto;
}
</style>
