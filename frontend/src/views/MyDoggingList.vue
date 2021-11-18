<template>
  <div class="MyDogging-Wrapper">
    <Header :isLogo="false" :isBack="false" title="나의 도깅"></Header>
    <div class="p-box-petsitter">
      <div class="my-p-box">
        <img class="img-box" src="../assets/img_logo.jpg" alt="사진 안 뜸">
        <div class="in-my-p-box">
          <div class="my-p-text" @click="detailOfDogging(mydogging.id)">
            <h3>대전 유성구</h3>
            <p style="font-size:small;"> 총 거리 : 6.8km</p>
            <p style="font-size:small;">도깅 시간 : 5시간</p>
            <p style="font-size:small;">도깅 날짜 : 2012-11-16</p>
          </div>
        </div>
      </div>
    </div>
    <div class="p-box-petsitter">
      <div class="my-p-box" v-for="mydogging in mydoggings" :key="mydogging.id">
        <img class="img-box" :src="fullURL(mydogging.customPicturePath)" alt="사진 안 뜸">
        <div class="in-my-p-box">
          <div class="my-p-text" @click="detailOfDogging(mydogging.id)">
            <h3>{{mydogging.location}}</h3>
            <p style="font-size:small;"> 총 거리 : {{mydogging.distance}}km</p>
            <p style="font-size:small;">도깅 시간 : {{mydogging.time}}</p>
            <p style="font-size:small;">도깅 날짜 : {{mydogging.created.substr(0,10)}}</p>
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
    }
  },
  created(){
    this.userid = localStorage.getItem('userid')
    console.log(this.userid) 
    this.$store.dispatch('bringMyDogging')
    .then(res => {
      console.log(res.data.data)
      this.mydoggings = res.data.data
    })
  },
  methods: {
    detailOfDogging(id){
      console.log("go detail of my dogging")
      this.$router.push({name:'MyDoggingDetail',params:{'doggingid':id}});
    },
    getDogging() {
      axios({
        url: 'https://spetrum.io:8080/api/dogging/',
        method: "GET",
        headers: {
          'Authorization':localStorage.getItem('token'),
        },
      }).then(res => {
        this.mydoggings = res.data
      })
    }
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