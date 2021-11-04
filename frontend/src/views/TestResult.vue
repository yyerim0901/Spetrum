<template>
  <div class="PET-Wrapper">
    <Header :isback="false" :isLogo="false" title="결과보기"/>
    {{this.type}}
    <Footer :isActive="4"/>
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
    }
  },
  computed:{
    ...mapState(['userInfo'])
  },
  created(){
    axios({
      url:`/petbti?keyword=${Number(this.userInfo.type)}`,
      method:'get',
    })
    .then(res=>{
      console.log(res);
    })
    .catch(err=>{
      console.log(err);
    })
  }
}
</script>

<style>

</style>