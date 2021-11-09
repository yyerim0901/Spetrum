<template>
  <div class="PET-Wrapper">
    <Header :isback="false" :isLogo="false" title="결과보기"/>
    <hr>
    <div>
      <img :src="myanimal.url" alt="" class="animal-img">
      <h2>{{ myanimal.center }}</h2>

    </div>
    <hr class="fott">
    <Footer :isActive="2"/>
  </div>
</template>

<script>
import axios from '@/axios/index'
import {mapState} from 'vuex'
import Footer from '../components/molecules/Footer.vue'
import Header from '../components/molecules/Header.vue'
export default {
  name:'TestResult',
  components:{
    Footer,
    Header
  },
  data(){
    return {
      myanimal:null,
      explain:undefined,
      recommend: undefined,
    }
  },
  computed:{
    ...mapState(['userInfo']),

  },
  created(){
    if (!this.userInfo.type){
      this.$router.push({name:'PetBTI'})
    }
    else{
      axios({
        url:`/petbti?keyword=${Number(this.userInfo.type)}`,
        method:'get',
      })
      .then(res=>{
        console.log(res);
        this.myanimal = res.data;
      })
      .catch(err=>{
        console.log(err);
      })
    }
  }
}
</script>

<style>
  .animal-img{
    width:300px;
    height: 300px;
  }
</style>