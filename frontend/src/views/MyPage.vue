<template>
  <div class="Page-Wrapper">
    <Header :isLogo="false" :isBack="false" title="마이페이지"></Header>
      <div class="mp-box">
        <div class="mp-box2" style="margin-left:15px;">
          <img :src="getthumbnail()" alt="profileImage" class="pimg-box">
          <div style="margin:20px;">
            <h3>{{this.userInfo.nickname}}</h3>
            <button btype="medium" class="mp-button" @click="logout">로그아웃</button>
          </div>
        </div>
        <div class="mp-button2"></div>
        <div class="mp-button2" @click="updateUserInfo">
          회원 정보 수정
        </div>
        <div class="mp-button2" @click="mydoggingList">
          나의 도깅 모아보기
        </div>
        <div class="mp-button2" @click="myPetsitterList">
          내 게시글
        </div>
        <div class="mp-button2" @click="check">
          회원 탈퇴
        </div>
      </div>
      <Modal v-if="showModal" @close="showModal=false" @ok="deleteUser">
      <h3 slot="header">
        정말 탈퇴하시겠습니까?
      </h3>
      <div slot="body"></div>
    </Modal>
    <Footer :isActive="isActive"></Footer>
  </div>
</template>

<script>
import Footer from '../components/molecules/Footer.vue'
import Header from '../components/molecules/Header.vue'
import Modal from '../components/molecules/Modal.vue'
import {mapState} from 'vuex';
export default {
  name:'MyPage',
  components:{
    Footer,
    Header,
    Modal
  },
  data(){
    return{
      isActive:5,
      userid : "",
      BASE_URL : 'https://spetrum.ddns.net/resources/',
      showModal : false,
    }
  },
  computed:{
    ...mapState(['userInfo']),
  },
  methods: {
    getthumbnail(){
      if(this.userInfo.thumbnail){
        var fullurl = this.BASE_URL + this.userInfo.thumbnail
        return fullurl
      }
      else return require("@/assets/img_logo.jpg")
    },
    logout(){
      //로그아웃 -> 모달창 띄우기 어떻게 하는지 알아 보기
      this.$store.dispatch('logout');
    },
    updateUserInfo(){
      this.$router.push("/update/user")
    },
    mydoggingList(){
      this.$router.push("/mydogging")
    },
    myPetsitterList(){
      this.$router.push("/mypetsitter")
    },
    check(){
      this.showModal = true;
    },
    deleteUser(){
      this.$store.dispatch('deleteUser',this.userInfo.userid);
      localStorage.removeItem('userid')
      localStorage.removeItem('token')
      this.$router.push("/signin")
    }
  },
  created(){
    this.userid = localStorage.getItem('userid');
    this.$store.dispatch('requestUser',this.userid);
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
  .mp-box {
    width:100%;
    padding:10px 3px;
    display: flex;
    flex-direction: column;
  }
  .mp-box2{
    display:flex;
    align-items: center;
    justify-content: left;
  }
  .mp-button{
    background-color: rgb(136, 136, 136);
    color: white;
    border-radius: 5px;
    height: 30px;
    width : 70px;
    margin-top: 7px;
  }
  
  .mp-button2{
    border-bottom: 1px ridge rgb(172, 172, 172);
    padding: 10px;
  }
</style>

