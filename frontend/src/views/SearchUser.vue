<template>
  <div class="Page-Wrapper">
    <Header :isLogo="false" :isBack="true" :isSearch="false" title="유저 검색"></Header>
  </div>
</template>

<script>
import Header from '../components/molecules/Header.vue'

export default {
  name:'SearchUser',
  components:{
    Header,
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