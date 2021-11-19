<template>
  <div class="Dogging-Wrapper">
      <Header :isLogo="false" :isBack="false" title="도깅"></Header>
      <h3>도깅완료!!</h3>
      <div id="map" style="width:500px;height:400px;"></div>
      <StyledButton btype="medium" bcolor="babypink" @click="moveToDogging">뒤로가개</StyledButton>
      <StyledButton btype="medium" bcolor="babypink" @click="moveToTodayDogging">기록하개</StyledButton>
  </div>
</template>

<script>
import Header from '../components/molecules/Header.vue'
import StyledButton from '../components/atoms/StyledButton'
export default {
    name : "FinishDogging",
    components: {
        Header,
        StyledButton,
    },
    data() {
        return{
            isActive:1,
            lats : this.$route.params.finishLat,
            lngs : this.$route.params.finishLng,
        }
    },
    mounted() { 
      if (window.kakao && window.kakao.maps) {
        this.initMap()
      } else {
        const script = document.createElement('script'); 
        /* global kakao */ 
        script.onload = () => kakao.maps.load(this.initMap); 
        script.src = '//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=8b07795e21cc36039de160da0cd01ffd&libraries=services';
        document.head.appendChild(script); 
      }
    }, 
    methods: {
        initMap() {
            var mapContainer = document.getElementById('map'); 
            
            const forlats = this.$route.params.finishLat;
            const forlngs = this.$route.params.finishLng;
            const latlen = forlats.length;
            let centerlat = 0.0;
            let centerlng = 0.0;

            var linePath = [];
            for (var i = 0; i < latlen; i ++) {
                var forline = new kakao.maps.LatLng(forlats[i], forlngs[i])
                centerlat += forlats[i]
                centerlng += forlngs[i]
                linePath.push(forline)
            }
            centerlat /= latlen
            centerlng /= latlen
            var mapOption = {
                center: new kakao.maps.LatLng(centerlat, centerlng), //지도에 찍힐 위치
                // 33.450701, 126.570667
                level: 5 //지도 확대 수준(기본이 3)
            }; 
            var map = new kakao.maps.Map(mapContainer, mapOption);
            var polyline = new kakao.maps.Polyline({
                path: linePath, // 선을 구성하는 좌표배열 입니다
                strokeWeight: 6, // 선의 두께 입니다
                strokeColor: '#F7797D', // 선의 색깔입니다
                strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
                strokeStyle: 'solid' // 선의 스타일입니다
            });
            polyline.setMap(map);  
            
        },
        moveToTodayDogging() {
            this.$router.push({name:"TodayDogging"})
        },
        moveToDogging() {
            this.$router.push({name:"Dogging"})
        }
        

    },
}
</script>

<style>

</style>