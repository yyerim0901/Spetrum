<template>
  <div class="MyPetsitter-Wrapper">
    <Header :isLogo="false" :isBack="true" title="나의 게시글"></Header>
    <hr>
    <div style="justify-content:left;">
      <div class="my-p-box">
        <img class="img-box" src="../assets/img_logo.jpg" alt="사진 안 뜸">
        <div class="in-my-p-box">
          <div class="my-p-text">
            <h4>여기는 제모오오오ㅗㅇ옥</h4>
            <p style="height:4px;"></p>
            <p style="font-size:x-small;">작성 일자 : 2021-11-11</p>
          </div>
          <StyledButton btype="small" style="background-color:white; color:gray; margin:0px;" @click="detailof(1)">자세히보기</StyledButton>
          <span style="float: right;">
            <StyledButton btype="xsmall" style="background-color:white; color:gray; margin:0px;" @click="updatemypetsitter(1)">수정</StyledButton>
            <span style="color:gray; font-size:small;">|</span>
            <StyledButton btype="xsmall" style="color:#EE9CA7; margin:0px;" @click="checkdelete(1)">삭제</StyledButton>
          </span>
        </div>
      </div>
    </div>
    <div style="align-items:center;">
      <div class="my-p-box" v-for="mypetsitter in mypetsitters" :key="mypetsitter.id">
        <img class="img-box" :src="fullURL(mypetsitter.picture)" alt="사진 안 뜸">
        <div class="in-my-p-box">
          <div class="my-p-text">
            <h4>{{mypetsitter.title}}</h4>
            <p style="font-size:x-small;">작성 일자 : {{mypetsitter.created.substr(0,10)}}</p>
          </div>
          <StyledButton btype="xsmall" style="background-color:white; color:gray; margin:0px;" @click="detailof(mypetsitter.id)">자세히보기</StyledButton>
          <span style="color:gray; font-size:small;">|</span>
          <StyledButton btype="xsmall" style="background-color:white; color:gray; margin:0px;" @click="updatemypetsitter(mypetsitter.id)">수정</StyledButton>
          <span style="color:gray; font-size:small;">|</span>
          <StyledButton btype="xsmall" style="color:#EE9CA7; margin:0px;" @click="checkdelete(mypetsitter.id)">삭제</StyledButton>
        </div>
      </div>
    </div>
    <Modal v-if="showModal" @close="showModal=false" @ok="deletemypetsitter">
      <h3 slot="header">
        게시글을 정말 삭제하시겠습니까?
      </h3>
      <div slot="body"></div>
    </Modal>
    <hr class="fott">
    <Footer :isActive="isActive"></Footer>
  </div>
</template>

<script>
import Footer from '../components/molecules/Footer.vue'
import Header from '../components/molecules/Header.vue'
import StyledButton from '../components/atoms/StyledButton'
import Modal from '../components/molecules/Modal.vue'
export default {
  name:'MyPetsitter',
  components:{
    Footer,
    Header,
    Modal,
    StyledButton
  },
  data(){
    return{
      isActive:5,
      mypetsitters:[],
      BASE_URL : 'https://spetrum.io/resources/',
      userid:"",
      showModal:false,
      deletepboardId:"",
    }
  },
  methods: {
    fullURL(url){
            const full = this.BASE_URL + url;
            return full;
        },
    detailof (pboardId){
      //상세보기 해야하나 말아야하나..
      console.log(pboardId)
    },
    updatemypetsitter(pboardId){
      //update페이지 즉 상세 입력 페이지로 이동한 후에 dispatch로 실행
      console.log(pboardId)
    },
    checkdelete(pboardId){
      this.showModal = true;
      this.deletepboardId = pboardId;
    },
    deletemypetsitter(){
      this.$store.dispatch('deletePetsitter',this.deletepboardId)
    }
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
    align-items: center;
    flex-direction: column;
    height: 100vh;
    width:100%;
  }
  .my-p-box{
    display: flex;
    /* margin-left:12%; */
  }
  .in-my-p-box{
    margin: 20px;
  }
  .my-p-text{
    margin-left: 7px;
    margin-top: 12px;
  }
</style>