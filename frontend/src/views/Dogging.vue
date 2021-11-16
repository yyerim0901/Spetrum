<template>
  <div class="Dogging-Wrapper">
    <Header :isLogo="false" :isBack="false" title="도깅"></Header>
    <hr>
    <div id="map" style="width:450px;height:400px;"></div>
    <Footer :isActive="isActive"></Footer>
  </div>
</template>

<script>
import Footer from '../components/molecules/Footer.vue'
import Header from '../components/molecules/Header.vue'
export default {
  name:'Dogging',
  components:{
    Footer,
    Header,
  },
  data(){
    return{
      isActive:1
    }
  },
    mounted() { 
      if (window.kakao && window.kakao.maps) {
        this.initMap()
      } else {
        const script = document.createElement('script'); 
        /* global kakao */ 
        script.onload = () => kakao.maps.load(this.initMap); 
        script.src = '//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=8b07795e21cc36039de160da0cd01ffd';
        document.head.appendChild(script); 
      }
    }, 
    methods : { 
      initMap() {
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function(pos) {
            var lat = pos.coords.latitude,
                lon = pos.coords.longitude;
            var container = document.getElementById('map');
            var options = {
              center: new kakao.maps.LatLng(lat, lon),
              level: 3
            };
            var map = new kakao.maps.Map(container, options);
            var imageSrc = require('@/assets/corgi.png'), // 마커이미지의 주소입니다    
                imageSize = new kakao.maps.Size(30, 30), // 마커이미지의 크기입니다
                imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
            var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
            var marker = new kakao.maps.Marker({ position: map.getCenter(), image: markerImage }); 
            marker.setMap(map); 
          })
        } else {
          alert('위치를 받을 수 없습니다')
        }

        // var marker = new kakao.maps.Marker({ position: map.getCenter() }); marker.setMap(map); 
      },
    },

}
</script>

<style>
  .Dogging-Wrapper {
    justify-content: flex-start;
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100vh;
    width:100%;

  }
</style>