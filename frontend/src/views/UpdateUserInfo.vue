<template>
  <div class="updateUser-Wrapper">
    <Header :isLogo="false" :isBack="false" title="회원 정보 수정"></Header>
    <hr>
    <div class="Signup-Wrap">
      <img :src="getthumbnail()" alt="dog" class="pimg-box">
      <div class="form-container">
        <StyledLabel for="userid">아이디</StyledLabel>
        <StyledInput v-model="this.userInfo.userid" disabled></StyledInput>
        <StyledLabel for="password">비밀번호</StyledLabel>
        <StyledInput  type="password" :value="password" v-model="password" @change="validPassCheck"></StyledInput>
        <ErrorMessage :message="pderror" >{{this.pderror}}</ErrorMessage>
        <StyledLabel for="pwconfirm">비밀번호확인</StyledLabel>
        <StyledInput  type="password" :value="pwconfirm" v-model="pwconfirm" @change="validPassConCheck"></StyledInput>
        <ErrorMessage :message="pdconerror" >{{this.pdconerror}}</ErrorMessage>
        <StyledLabel for="nickname">닉네임</StyledLabel>
        <StyledInput v-model="this.userInfo.nickname" @change="validNickCheck"></StyledInput>
        <ErrorMessage :message="nickerror" >{{this.nickerror}}</ErrorMessage>
        <StyledLabel for="image">프로필 사진 수정</StyledLabel>
        <div class="img-box">
          <input  type="file" @change="imageChange" ref="profileImage">
          <img :src="this.userInfo.thumbnail" alt="" class="prev">
        </div>
      </div>
      <div class="i-box">
        <button class="follow-button" style="background-color:#EE9CA7;" @click="updateUserInfo()">수정</button>
        <button class="unfollow-button" @click="goMyPage()">취소</button>
      </div>
    </div>
    <hr class="fott">
    <Footer :isActive="isActive"></Footer>
  </div>
</template>

<script>
// import FooterButton from '../components/atoms/FooterButton'
import Footer from '../components/molecules/Footer.vue'
import Header from '../components/molecules/Header.vue'
import StyledInput from '../components/atoms/StyledInput'
import StyledLabel from '../components/atoms/StyledLabel'
import {mapState} from 'vuex';
export default {
  name:'Dogging',
  components:{
    Footer,
    Header,
    StyledInput,
    StyledLabel,
    // FooterButton
  },
  data(){
    return{
      isActive:5,
      password:null,
      pwconfirm:null,
      nickname:null,
      pderror:null,
      pdconerror:null,
      nickerror:null,
      pdValid:false,
      pdconValid:false,
      nickValid:false,
      nickcheck:false,
      profileImg:null,
      imgprev:null,
    }
  },
  computed:{
    ...mapState(['userInfo']),
  },
  methods: {
    getthumbnail(){
      if (this.userInfo.thumbnail) {
        return this.userInfo.thumbnail
      }else{
        return require("@/assets/img_logo.jpg")
      }
    },
    validPassCheck(e){
      const rule = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&^])[A-Za-z\d$@$!%*#?&^]{3,16}$/ ;
      if (!rule.test(e.target.value)){
        this.pderror = '비밀번호는 영문, 숫자, 특수문자가 포함되어야 합니다.';
      }
      else {
        this.pderror = null;
        this.pdValid = true;
      }
    },
    validPassConCheck(e){
      if (this.password !== e.target.value) {
        this.pdconerror = '비밀번호가 일치하지 않습니다!';
      } else {
        this.pdconValid = true;
        this.pdconerror = null;
      }
    },
    validNickCheck(e){
      if (e.target.value.length < 2) {
        this.nickerror = '닉네임을 입력해주세요!';
      } else {
        this.nickValid = true;
        this.nickerror = null;
      }
    },
    nickCheck(){
      const response = this.$store.dispatch('nickCheck',this.nickname);
      if (response.data.statusCode == '200'){
        this.nickcheck = true;
      }
      else {
        alert(response.data.message);
      }
    },
    imageChange(){
      this.profileImg = this.$refs.profileImage.files;
      if (this.profileImg) {
        this.imgprev = URL.createObjectURL(this.profileImg[0]);
        }
    },
    updateUserInfo(){
      if(this.profileImg == null) this.profileImg = this.userInfo.thumbnail;

      if (this.nickValid & this.pdValid & this.pdconValid && this.nickcheck ) {
        const formData = new FormData();
        formData.append("userid",this.userInfo.userid)
        formData.append("nickname",this.nickname);
        formData.append("password",this.password);
        formData.append("image",this.profileImg);
        console.log(this.profileImg,'프로필이미지');
        this.$store.dispatch('requestUpdateUserInfo',formData);
      }
      else {
        alert('입력하신 정보를 다시 한 번 확인해주세요!');
      }
    },
    goMyPage(){
      this.$router.push("/mypage")
    }
  },
  created(){
    this.userid = localStorage.getItem('userid');
    this.$store.dispatch('requestUser',this.userid);
  }
}
</script>

<style>
  .updateUser-Wrapper {
    justify-content: flex-start;
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100vh;
    width:100%;
  }
</style>