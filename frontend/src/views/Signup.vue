<template>
  <div class="bigbox">
    <Header :isLogo="isLogo" :isBack="isBack" :title="title"/>
    <div class="Signup-Wrap">
      <img src="../assets/img_logo.jpg" alt="dog" class="dog-img">
      <div class="form-container">
        <StyledLabel for="userid">아이디</StyledLabel>
        <StyledInput  :value="userId" v-model="userId" @change="validIdCheck"></StyledInput>
        <div class="msg-box">
          <ErrorMessage :message="iderror" >{{this.iderror}}</ErrorMessage>
          <button class="double-check" @click="idCheck"><p>아이디 중복확인</p></button>
        </div>
        <StyledLabel for="password">비밀번호</StyledLabel>
        <StyledInput  type="password" :value="password" v-model="password" @change="validPassCheck"></StyledInput>
        <ErrorMessage :message="pderror" >{{this.pderror}}</ErrorMessage>
        <StyledLabel for="pwconfirm">비밀번호확인</StyledLabel>
        <StyledInput  type="password" :value="pwconfirm" v-model="pwconfirm" @change="validPassConCheck"></StyledInput>
        <ErrorMessage :message="pdconerror" >{{this.pdconerror}}</ErrorMessage>
        <StyledLabel for="nickname">닉네임</StyledLabel>
        <StyledInput  :value="nickname" v-model="nickname" @change="validNickCheck"></StyledInput>
        <ErrorMessage :message="nickerror" >{{this.nickerror}}</ErrorMessage>
        <div class="msg-box">
          <ErrorMessage >{{this.nickerror}}</ErrorMessage>
          <button class="double-check" @click="nickCheck"><p>닉네임 중복확인</p></button>
        </div>
        <StyledLabel for="image">프로필 사진 업로드</StyledLabel>
        <div class="img-box">
          <input  type="file" @change="imageChange" ref="profileImage">
          <img :src="this.imgprev" alt="" class="prev">
        </div>
      </div>
    </div>
    <FooterButton @click="handleSignup">회원가입</FooterButton>
  </div>
</template>

<script>
import Header from '../components/molecules/Header'
import StyledInput from '../components/atoms/StyledInput'
import StyledLabel from '../components/atoms/StyledLabel'
import ErrorMessage from '../components/atoms/ErrorMessage'
import FooterButton from '../components/atoms/FooterButton'
import axios from 'axios'
export default {
  name:'Signup',
  components:{
    Header,
    StyledInput,
    StyledLabel,
    ErrorMessage,
    FooterButton,
  },
  data(){
    return{
      isLogo:true,
      isBack:true,
      title:null,
      userId:null,
      password:null,
      pwconfirm:null,
      nickname:null,
      iderror:null,
      pderror:null,
      pdconerror:null,
      nickerror:null,
      idValid:false,
      pdValid:false,
      pdconValid:false,
      nickValid:false,
      idcheck:false,
      nickcheck:false,
      profileImg:null,
      imgprev:null,
    }
  },
  methods:{
    validIdCheck(e){
      if (e.target.value.length < 2){
        this.iderror = "아이디를 입력해주세요";
      }
      else{
        this.iderror = null;
        this.idValid = true;
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
    handleSignup(){
      if (this.nickValid & this.pdValid & this.pdconValid & this.idValid && this.idcheck && this.nickcheck ) {
        const formData = new FormData();
        formData.append("nickname",this.nickname);
        formData.append("password",this.password);
        formData.append("userId",this.userId);
        if(this.profileImg) {formData.append("thumbnail",this.profileImg);}
        // this.$store.dispatch('requestSignup',formData);
        axios({
          url:'https://spetrum.io:8080/api/users/regist',
          method:'post',
          data:formData,
          headers:{
            'Content-Type': 'multipart/form-data',
            'Access-Control-Allow-Origin':'*',
          }
        })
          .then(res=>{
            console.log(res);
            console.clear();
            this.$router.push({name:'SignIn'})
          })
      }
      else {
        alert('아이디, 닉네임 중복검사를 해주세요!');
      }
    },
    nickCheck(){
      this.$store.dispatch('nickCheck',this.nickname)
      .then(res=>{
        if (res.data.statusCode === 200) {
          this.nickcheck = true
          alert('사용가능한 닉네임입니다')
        }else{
          alert('이미 사용중인 닉네임입니다')
        }
      })
    },
    idCheck(){
      this.$store.dispatch('idCheck',this.userId)
      .then(res=>{
        if (res.data.statusCode === 200) {
          this.idcheck = true
          alert('사용가능한 아이디입니다')
        }else{
          alert('이미 사용중인 아이디입니다')
        }
      })
    },
    imageChange(){
      this.profileImg = this.$refs.profileImage.files[0];
      if (this.profileImg) {
        this.imgprev = URL.createObjectURL(this.profileImg);
        }
    }

  }
}
</script>

<style>
  .bigbox{
    width:100%;
  }
  .Signup-Wrap{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    justify-content: flex-start;
    height: 100vh;
    width:100%;
    padding:10px 30px 0 30px;
  }

  .dog-img{
    width: 140px;
    padding: 2px 0;
    margin: 10px 10px 0 0;
  }

  .form-container{
    width:100%;
    margin: 10px 0;
  }

  .double-check{
    color:#636E72;
    font-size:0.625rem;
    margin:0 0 0 auto;
  }

  .msg-box{
    display: flex;
  }

  .prev {
    width: 100px;
  }

  .img-box{
    display: flex;
    flex-direction: column;
  }

  input[type=file]::file-selector-button {
    background-color: #EE9CA7;
    color: white;
    border-radius: 6px;
    border : none;
    font-size: 0.8rem;
    width: 100px;
    padding: 5px;
  }

</style>