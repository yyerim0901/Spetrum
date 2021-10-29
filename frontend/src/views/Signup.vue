<template>
  <div class="bigbox">
    <Header :isLogo="isLogo" :isBack="isBack" :title="title"/>
    <div class="Signup-Wrap">
      <img src="../assets/img_logo.jpg" alt="dog" class="dog-img">
      <div class="form-container">
        <StyledLabel for="userid">아이디</StyledLabel>
        <StyledInput  :value="userId" v-model="userId" @change="validIdCheck"></StyledInput>
        <div>
          <ErrorMessage :message="iderror" >{{this.iderror}}</ErrorMessage>
          <p>아이디 중복확인</p>
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
        <div>
          <ErrorMessage >{{this.nickerror}}</ErrorMessage>
          <p>닉네임 중복확인</p>
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
    }
  },
  methods:{
    validIdCheck(e){
      console.log('변화');
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
      if (this.nickValid & this.pdValid & this.pdconValid & this.idValid) {
        const data = {
          nickname:this.nickname,
          password: this.password,
          userId: this.userId,
        }
        this.$store.dispatch('requestSignup',data);
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
    justify-content: start;
    height: 100vh;
    width:100%;
    padding:20px 30px 0 30px;
  }

  .dog-img{
    width: 140px;
    padding: 2px 0;
    margin: 50px 10px 0 0;
  }

  .form-container{
    width:100%;
    margin: 10px 0;
  }

  p{
    color:#636E72;
    font-size:0.625rem;
    text-align: right;
  }

</style>