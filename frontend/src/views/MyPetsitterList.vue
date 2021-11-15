<template>
  <div class="MyPetsitter-Wrapper">
    <Header :isLogo="false" :isBack="true" title="나의 게시글"></Header>
    <hr>
    <div v-for="mypetsitter in mypetsitters" :key="mypetsitter.id">
      <img class="img-box" :src="fullURL(mypetsitter.picture)" alt="사진 안 뜸">
      <p>{{mypetsitter.title}}</p>
      <p>{{mypetsitter.created}}</p>
      <p>{{mypetsitter.user}}</p>
    </div>
    <hr class="fott">
    <Footer :isActive="isActive"></Footer>
  </div>
</template>

<script>
import Footer from '../components/molecules/Footer.vue'
import Header from '../components/molecules/Header.vue'
export default {
  name:'MyPetsitter',
  components:{
    Footer,
    Header
  },
  data(){
    return{
      isActive:5,
      mypetsitters:[],
      BASE_URL : 'https://spetrum.io/resources/',
      userid:"",
    }
  },
  methods: {
    fullURL(url){
            const full = this.BASE_URL + url;
            return full;
        },
  },
  created() {
    this.userid = localStorage.getItem('userid')
    console.log(this.userid)
    this.$store.dispatch('bringMyPBoard',this.userid)
    .then(res=>{
      console.log(res.data)
      // console.log(res.data.data)
      this.mypetsitters = res.data;
    })
  },
}
</script>

<style>
  .MyPetsitter-Wrapper {
    justify-content: flex-start;
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100vh;
    width:100%;

  }
</style>