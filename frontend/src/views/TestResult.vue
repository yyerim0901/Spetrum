<template>
  <div class="PET-Wrapper">
    <Header :isback="false" :isLogo="false" title="결과보기"/>
    <hr>
    <div class="result-box">
      <img :src="myanimal.url" alt="" class="animal-img">
      <h3 style="margin:5px 0 0 0; color:#EE9CA7;">{{ recommend}}</h3>
      <h4>{{ explain}}</h4>
      <div class="ex-box">
        <h3>보호현황 안내</h3>
        <span>특징 : {{myanimal.field}}</span>
        <span>보호소 : {{myanimal.center}}</span>
        <span>종 : {{myanimal.kind}}</span>
        <span>공고# : {{myanimal.noticenumber}}</span>

      </div>
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
      recommend:null,
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
        if (this.userInfo.type === '111' || this.userInfo.type === '211') {
          this.recommend = '온순하고 사람을 잘따르는 친구!';
          this.explain = '차분하고 낯가리는 성격을 가진 당신! 먼저다가와주는 친구를 추천합니다'
        }
        else if (this.userInfo.type === '112' || this.userInfo.type === '212') {
          this.recommend = '온순하지만 겁이 많은 친구';
          this.explain = '적극적인 당신에게 관심과 도움이 필요한 친구를 추천드려요~'
        }
        else if (this.userInfo.type === '113' || this.userInfo.type === '213') {
          this.recommend = '활동적이고 애교쟁이 친구!';
          this.explain = '에너자이지만 낯을 가리는 당신! 애교쟁이 친구를 추천드려요~'
        }
        else if (this.userInfo.type === '114' || this.userInfo.type === '214') {
          this.recommend = '활동적이지만 사람은 무서운 친구!';
          this.explain = '활동적이며 적극적인 당신! 관심과 도움이 필요한 친구를 추천드려요~'
        }
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

  .result-box{
    margin: 10px 0 0 0;
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .ex-box{
    margin: 10px 0 0 0;
    border:solid 1px;
    border-color:#F7797D;
    width:400px;
    display:flex;
    flex-direction: column;
    align-items: center;
    height: 180px;
  }
</style>