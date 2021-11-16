<template>
  <div class="Dogging-Wrapper">
    <Header :isLogo="false" :isBack="false" title="도깅"></Header>
    <hr>
    <div v-if="doggingflag">
      <div>
        <p>도깅 시간</p>
        <h2>00.00.00</h2>
      </div>
      <div>
        <p>도깅 거리</p>
        <h2>0.0 km</h2>
      </div>
    </div>
    <div id="map" style="width:450px;height:400px;"></div>
    <hr class="fott">
    <div v-if="!doggingflag">
      <h2>--.-- km</h2>
      <StyledButton btype="medium" bcolor="babypink" @click="doggingAdjust" >도깅시작</StyledButton>
    </div>
    <div v-if="doggingflag">
      <StyledButton btype="medium" bcolor="babypink" >정지하개</StyledButton> |
      <StyledButton btype="medium" bcolor="babypink" @click="doggingAdjust" >완료하개</StyledButton>
    </div>
    <Footer :isActive="isActive"></Footer>
  </div>
</template>

<script>
import Footer from '../components/molecules/Footer.vue'
import Header from '../components/molecules/Header.vue'
import StyledButton from '../components/atoms/StyledButton'
export default {
  name:'Dogging',
  components:{
    Footer,
    Header,
    StyledButton,
  },
  data(){
    return{
      isActive:1,
      doggingflag: false,
      watchId:'',
      latlist : [],
      lnglist : [],
      totalDistance : 0,
      totalTime : 0,
      curTime : 0,
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
      doggingAdjust() {
        if (navigator.geolocation) {
          var lat = 0,
              lng = 0,
              that = this;
          if (this.doggingflag === false) {
            // 도깅을 시작할 때
            this.doggingflag = true
            this.watchId = navigator.geolocation.watchPosition(function(pos) {
              if (lat !== pos.coords.latitude || lng !== pos.coords.longitude) {
                // 좌표로 거리 구하기 내장함수가 없네 ㅎㅎ
                // 공식 자체는 이해못함 무지성 따라하기임
                var getDistanceFromLatLonInKm;

                getDistanceFromLatLonInKm = function(lat1,lng1,lat2,lng2) {
                  var deg2rad;
                  deg2rad = function(deg) {
                      return deg * (Math.PI/180)
                  }

                  var R = 6371; // Radius of the earth in km
                  var dLat = deg2rad(lat2-lat1);  // deg2rad below
                  var dLon = deg2rad(lng2-lng1);
                  var a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(dLon/2) * Math.sin(dLon/2);
                  var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
                  var d = R * c; // Distance in km
                  return d;
                }                
                
                if (lat !== 0 && lng !== 0 && that.totalTime !== 0) {
                  // 초기 값은 0으로 들어가게 되고 그때 작동하면 안됨
                  var distance = getDistanceFromLatLonInKm(lat, lng, pos.coords.latitude, pos.coords.longitude)
                  console.log('여긴들어와??')
                  console.log(distance, '이게 거리 km')
                  console.log(that.totalDistance, '추가 전 총 거리')
                  that.totalDistance += distance
                  console.log(that.totalDistance, '추가 후 총 거리')
                }
                // 시간 측정 부분
                if (that.curTime !== 0) {
                  var nextTime = pos.timestamp
                  console.log(that.totalTime, '추가 전 총 시간')
                  // unix시간 나누기 1000하면 초단위로 끊을 수 있음
                  that.totalTime += parseInt((nextTime - that.curTime) / 1000)
                  console.log(that.totalTime, '추가 후 총 시간')
                  that.curTime = nextTime
                } else {
                  // 0 이면 시간 갱신
                  that.curTime = pos.timestamp
                }
                console.log(pos.coords)
                console.log(pos,'여긴가?')
                lat = pos.coords.latitude
                lng = pos.coords.longitude
                that.latlist.push(lat)
                that.lnglist.push(lng)
              }
            })
          } else {
            navigator.geolocation.clearWatch(this.watchId)
            // 도깅을 멈추고 완료할 때
            // components의 데이터 초기화해야함
            this.doggingflag = false
            console.log(this.latlist, this.lnglist, '여기다가 axios해도 될까요?')
          }
        } else {
          alert('위치를 찾을 수 없어요')
        }
      },
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