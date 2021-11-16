<template>
  <div class="MyDogging-Wrapper">
    <Header :isLogo="false" :isBack="false" title="나의 도깅"></Header>
    <div class="p-box">
      <div class="my-p-box" v-for="mydogging in mydoggings" :key="mydogging.id">
        <img class="img-box" :src="fullURL(mydogging.customPicturePath)" alt="사진 안 뜸">
        <div class="in-my-p-box">
          <div class="my-p-text" @click="detailOfDogging(mydogging.id)">
            <h4>{{mydogging.location}}</h4>
            <p style="font-size:x-small;"> 총 거리 : {{mydogging.distance}}km</p>
            <p style="font-size:x-small;">도깅 시간 : {{mydogging.time}}</p>
            <p style="font-size:x-small;">도깅 날짜 : {{mydogging.created.substr(0,10)}}</p>
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
    this.$store.dispatch('bringMyDogging',this.page)
    .then(res => {
      console.log(res.data.data)
      this.mydoggings = res.data.data
    })
  },
  methods: {
    detailOfDogging(id){
      this.$router.push({name:'MyDoggingDetail',params:{'doggingid':id}});
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