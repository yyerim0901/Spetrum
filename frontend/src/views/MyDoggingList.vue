<template>
  <div class="MyDogging-Wrapper">
    <Header :isLogo="false" :isBack="false" title="나의 도깅"></Header>
    <hr>
    <hr class="fott">
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
      isActive:5
    }
  }
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