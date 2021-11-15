<template>
  <div class="MyPetsitter-Wrapper">
    <Header :isLogo="false" :isBack="true" title="나의 게시글"></Header>
    <hr>
    <div>
      <div class="my-p-box" v-for="mypetsitter in mypetsitters" :key="mypetsitter.id">
        <img class="img-box" :src="fullURL(mypetsitter.picture)" alt="사진 안 뜸">
        <div class="in-my-p-box">
          <div class="my-p-text">
            <h4>{{mypetsitter.title}}</h4>
            <p style="font-size:x-small;">작성 일자 : {{mypetsitter.created.substr(0,10)}}</p>
          </div>
          <StyledButton btype="xsmall" style="background-color:white; color:gray; margin:0px;">수정</StyledButton>
          <span style="color:gray; font-size:small;">|</span>
          <StyledButton btype="xsmall" style="color:#EE9CA7; margin:0px;">삭제</StyledButton>
        </div>
      </div>
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
  name:'MyPetsitter',
  components:{
    Footer,
    Header,
    StyledButton
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
    .then(res => {
      console.log(res.data.data)
      this.mypetsitters = res.data.data
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
  .my-p-box{
    display: flex;
  }
  .in-my-p-box{
    margin: 20px;
  }
  .my-p-text{
    margin-left: 7px;
    margin-top: 12px;
  }
</style>