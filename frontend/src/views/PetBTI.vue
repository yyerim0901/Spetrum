<template>
  <div class="PET-Wrapper">
    <Header :isLogo="false" :isBack="false" title="펫피티아이"/>
    <hr>
    <div class="if-box">
      <div class="t-box">
        <h1 style="color:#F7797D">PetBTI</h1><h1>란?</h1>
      </div>
      <span>나는 어떤 반려동물과 잘 맞을까?<br>지금바로 간단한 테스트를 통해<br>나와 잘맞는 도움이 필요한 친구들을 만나보세요!</span>
      <span>총 7문항, 소요 예상 시간:5분</span>
      <img src="@/assets/test.jpg" alt="" class="testdoggy">
      <StyledButton btype="medium" bcolor="babypink" @click="startTest" >테스트시작</StyledButton>
    </div>
    <div class="gohelp" @click="goHelp">
      <img src="@/assets/help.jpg" alt="" class="help">
      <h3 style="text-align:center;">친구들 더 만나러가기!<br><p>동물보호시스템으로 이동</p></h3>
    </div>
    <Footer :isActive="isActive"/>
  </div>
</template>

<script>
import Header from '../components/molecules/Header';
import Footer from '../components/molecules/Footer';
import StyledButton from '../components/atoms/StyledButton'
export default {
  name:'PetBTI',
  components:{
    Header,
    Footer,
    StyledButton,
  },
  data(){
    return {
      isActive: 2,
    }
  },
  methods:{
    goHelp(){
      window.open("https://www.animal.go.kr/front/awtis/protection/protectionList.do?menuNo=1000000060")
    },
    startTest(){
      this.$router.push({name:'PetTest'})
    }
  }
}
</script>

<style>
  hr {
    width: 500px;
  }

  .PET-Wrapper {
    justify-content: flex-start;
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100vh;
    width:100%;
  }

  .fott{
    position: fixed;
    bottom: 65px;
    width: 500px;
  }

  .if-box{
    width: 380px;
    height: 450px;
    margin:10px 0;
    display:flex;
    flex-direction: column;
    align-items: center;
    border:solid 3px #EE9CA7;
  }

  .t-box{
    display:flex;
    color:#636E72;
  }
  span{
    text-align: center;
    margin:3px 0 0 0;
  }

  .testdoggy{
    width:300px;
    margin:10px 0;
  }

  .help{
    width: 100px;
    margin: 0 20px 0 0;
  }

  .gohelp{
    display: flex;
    align-items: center;
    justify-content: center;
  }
</style>