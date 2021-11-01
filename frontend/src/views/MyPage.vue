<template>
  <div class="Page-Wrapper">
    <Header :isLogo="false" :isBack="false" title="마이페이지"></Header>
      <hr>
      <hr class="fott">
    <Footer :isActive="isActive"></Footer>
  </div>
</template>

<script>
import Footer from '../components/molecules/Footer.vue'
import Header from '../components/molecules/Header.vue'
export default {
  name:'MyPage',
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
  .Page-Wrapper {
    justify-content: flex-start;
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100vh;
    width:100%;
  }
</style>