<template>
  <div class="Page-Wrapper">
    <Header :isLogo="false" :isBack="false" title="마이페이지"></Header>
      <hr>
      <div>
        <StyledButton btype="medium" bcolor="babypink" @click="handleLogout">로그아웃</StyledButton>
      </div>
      <hr class="fott">
    <Footer :isActive="isActive"></Footer>
  </div>
</template>

<script>
import Footer from '../components/molecules/Footer.vue'
import Header from '../components/molecules/Header.vue'
import StyledButton from '../components/atoms/StyledButton'
export default {
  name:'MyPage',
  components:{
    Footer,
    Header,
    StyledButton
  },
  data(){
    return{
      isActive:5
    }
  },
  methods:{
    handleLogout(){
      this.$store.commit('SET_LOGOUT');
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