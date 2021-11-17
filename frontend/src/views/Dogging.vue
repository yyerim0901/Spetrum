<template>
  <div class="Dogging-Wrapper">
    <Header :isLogo="false" :isBack="false" title="도깅"></Header>
    <div v-if="doggingflag">
      <div>
        <p>도깅 시간</p>
        <h2>{{ formattedElapsedTime }}</h2>
      </div>
      <div>
        <p>도깅 거리</p>
        <h2>{{ curDistance }}km</h2>
      </div>
    </div>
    <div id="map" style="width:450px;height:400px;"></div>
    <div v-if="!doggingflag">
      <h2>--.-- km</h2>
      <StyledButton btype="medium" bcolor="babypink" @click="doggingAdjust" >도깅시작</StyledButton>
    </div>
    <div v-if="doggingflag">
      <!-- <StyledButton v-if="!liveDoggingflag" btype="medium" bcolor="babypink" @click="liveDogging" >정지하개</StyledButton>
      <StyledButton v-if="liveDoggingflag" btype="medium" bcolor="babypink" @click="liveDogging" >다시하개</StyledButton> -->
      <StyledButton btype="medium" bcolor="babypink" @click="doggingAdjust" >완료하개</StyledButton>
    </div>
    <Footer :isActive="isActive"></Footer>
  </div>
</template>

<script>
import Footer from '../components/molecules/Footer.vue'
import Header from '../components/molecules/Header.vue'
import StyledButton from '../components/atoms/StyledButton'
import axios from 'axios'
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
      liveDoggingflag: false,
      watchId:'',
      latlist : [],
      lnglist : [],
      totalDistance : 0,
      totalTime : 0,
      curTime : 0,
      curMin : 0,
      curSec : 0,
      curDistance : 0,
      liveWatchId : '',
      location : '',
      elapsedTime: 0,
      timer: undefined,
      timecheck: '',
      initMapflag: false,
    }
  },
    mounted() { 
      if (window.kakao && window.kakao.maps) {
        this.initMap()
      } else {
        const script = document.createElement('script'); 
        /* global kakao */ 
        script.onload = () =>{
          kakao.maps.load(this.initMap); 
          console.log('이거 계속 로드됨???')
        } 
        script.src = '//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=8b07795e21cc36039de160da0cd01ffd&libraries=services';
        document.head.appendChild(script); 
      }
    }, 
    methods : {
      doggingAdjust() {
        if (navigator.geolocation) {
          var lat = 0,
              lng = 0,
              that = this,
              locationFlag = false;
          if (this.doggingflag === false) {
            // 도깅을 시작할 때
            this.timer = setInterval(() => {
              this.elapsedTime += 1000;
            }, 1000);
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
                  that.curDistance = parseFloat(Math.round(that.totalDistance * 100) / 100).toFixed(2)
                } 
                // 시간 측정 부분
                if (that.curTime !== 0) {
                  var nextTime = pos.timestamp
                  console.log(that.totalTime, '추가 전 총 시간')
                  // unix시간 나누기 1000하면 초단위로 끊을 수 있음
                  that.totalTime += parseInt((nextTime - that.curTime) / 1000)
                  console.log(that.totalTime, '추가 후 총 시간')
                  that.curTime = nextTime
                  that.curSec = that.totalTime % 60
                  that.curMin = parseInt((that.totalTime - that.curSec) / 60)
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

                //주소반환하기
                if (locationFlag === false) {
                  var geocoder = new kakao.maps.services.Geocoder();

                  var cord = new kakao.maps.LatLng(lat, lng);
                  var callback = function(result, status) {
                      if (status === kakao.maps.services.Status.OK) {
                        that.location = result[0].address.address_name;
                      }
                  };
                  geocoder.coord2Address(cord.getLng(), cord.getLat(), callback);
                  locationFlag = true
                }
              }
            })
          } else {
            // 도깅을 멈추고 완료할 때

            // 타이머종료
            clearInterval(this.timer);
            const date = new Date(null);
            date.setSeconds(this.elapsedTime / 1000);
            const utc = date.toUTCString();
            navigator.geolocation.clearWatch(this.watchId)
            //kakaomap의 watchposition도 종료
            navigator.geolocation.clearWatch(this.liveWatchId)
            // components의 데이터 초기화해야함
            this.doggingflag = false
            console.log(this.totalDistance, this.totalTime, utc.substr(utc.indexOf(":") - 2, 8), 'axios보낼 총 거리와 시간 주소@@', typeof(utc.substr(utc.indexOf(":") - 2, 8)), '시간의 타입!!!')
            console.log(this.latlist, this.lnglist, 'axios보낼 array@@@')
            const formData = new FormData();
            formData.append('distance', this.totalDistance);
            formData.append('lats', this.latlist);
            formData.append('lngs', this.lnglist);
            formData.append('time', utc.substr(utc.indexOf(":") - 2, 8));
            formData.append('location', this.location);
            
            axios({
              url: 'https://spetrum.io:8080/api/dogging/',
              method: 'POST',
              headers: {
                "Authorization": localStorage.getItem("token")
              },
              data: formData,
            }).then(res => {
              console.log(res)
              let finishedLat = this.latlist;
              let finishedLng = this.lnglist
              this.totalDistance = 0
              this.totalTime = 0
              this.latlist = []
              this.lnglist = []
              this.curDistance = 0
              this.elapsedTime = 0
              this.location = ''
              this.$router.push({name:'FinishDogging', params: {finishLat: finishedLat, finishLng : finishedLng}})
            }).catch(err => {
              console.log(err)
            })
          }
        } else {
          alert('위치를 찾을 수 없어요')
        }
      },
      initMap() {
        if (navigator.geolocation) {
          let container = document.getElementById('map');
          let map = new kakao.maps.Map(container, { 
            center : new kakao.maps.LatLng(36.355064085731115, 127.29836175557786),
            level: 3
            });
          var imageSrc = require('@/assets/corgi.png'), // 마커이미지의 주소입니다    
              imageSize = new kakao.maps.Size(30, 30), // 마커이미지의 크기입니다
              imageOption = {offset: new kakao.maps.Point(27, 69)}; // 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
          var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);
          var marker = new kakao.maps.Marker({ position: map.getCenter(), image: markerImage }); 
          marker.setMap(map); 
          
          this.liveWatchId = navigator.geolocation.watchPosition(function(pos) {
            console.log('실행이 되고 있어 watchposition??')
            var lat = pos.coords.latitude,
                lon = pos.coords.longitude;
            
            const currentPosition = new kakao.maps.LatLng(lat, lon);
            map.setCenter(currentPosition)
            marker.setPosition(currentPosition);
          })
        } else {
          alert('위치를 받을 수 없습니다')
        }
        // var marker = new kakao.maps.Marker({ position: map.getCenter() }); marker.setMap(map); 
      },
      // 중단하고 재시작 하는 방법
    },
  computed: {
    formattedElapsedTime() {
      const date = new Date(null);
      date.setSeconds(this.elapsedTime / 1000);
      const utc = date.toUTCString();
      return utc.substr(utc.indexOf(":") - 2, 8);
    }
  }
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