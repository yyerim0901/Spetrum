<template>
  <div class="MyDogging-Wrapper">
    <Header :isLogo="false" :isBack="true" title="나의 도깅"></Header>
    <div class="p-box-petsitter">
      <div class="my-p-box" v-for="mydogging in mydoggings" :key="mydogging.id">
        <img class="img-box" :src="fullURL(mydogging.customPicturePath)" alt="사진 안 뜸">
        <div class="in-my-p-box">
          <div class="my-p-text">
            <h3>{{mydogging.location}}</h3>
            <p style="font-size:small;"> 총 거리 : {{mydogging.distance}}km</p>
            <p style="font-size:small;">도깅 시간 : {{mydogging.time}}</p>
            <p style="font-size:small;">도깅 날짜 : {{mydogging.date.substr(0,10)}}</p>
          </div>
        </div>
      </div>
    </div>
    <Footer :isActive="isActive"></Footer>
  </div>
</template>

<script>
import Footer from '../components/molecules/Footer.vue'
import Header from '../components/molecules/Header.vue'
import axios from 'axios'
export default {
  name:'Dogging',
  components:{
    Footer,
    Header
  },
  data(){
    return{
      isActive:5,
      page : 1,
      mydoggings:[],
      userid:"",
      BASE_URL : 'https://spetrum.ddns.net/resources/',
    }
  },
  created(){
    this.userid = localStorage.getItem('userid')
    this.getDogging()
  },
  methods: {
    getDogging() {
      axios({
        url: 'https://spetrum.ddns.net:8000/api/dogging/',
        method: "GET",
        headers: {
          'Authorization':localStorage.getItem('token'),
        },
      }).then(res => {
        this.mydoggings = res.data
      })
    },
    fullURL(url){
      if (url !== null){
        var full = this.BASE_URL + url.slice(58);
      } else{
        full = require('@/assets/noimage.png')
      }
      return full;
    },
  },
  
}
</script>

<style>
  .MyDogging-Wrapper {
    justify-content: flex-start;
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100vh;
    width:100%;

  }
</style>