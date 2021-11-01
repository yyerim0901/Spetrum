<template>
  <div class="Moment-Wrapper">
    <Header :isLogo="false" :isBack="false" title="냥댕모먼트"></Header>
    <hr>
    <hr class="fott">
    <Footer :isActive="isActive"></Footer>
  </div>
</template>

<script>
import Footer from '../components/molecules/Footer.vue'
import Header from '../components/molecules/Header.vue'
export default {
  name:'Moment',
  components:{
    Footer,
    Header
  },
  data(){
    return{
      isActive:4
    }
  }
}
</script>

<style>
  .Moment-Wrapper {
    justify-content: flex-start;
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100vh;
    width:100%;
  }
</style>