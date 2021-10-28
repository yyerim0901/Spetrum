<template>
  <div class="bigbox">
    <Header :isLogo="isLogo" :isBack="isBack" :title="title"/>
    <div class="Signup-Wrap">
      <img src="../assets/img_logo.jpg" alt="dog" class="dog-img">
      <div class="form-container">
        <StyledLabel for="userid">아이디</StyledLabel>
        <StyledInput  :value="userId" v-model="userId" @change="validIdCheck"></StyledInput>
        <div>
          <ErrorMessage >{{this.iderror}}</ErrorMessage>
          <p>아이디 중복확인</p>
        </div>
        <StyledLabel for="password">비밀번호</StyledLabel>
        <StyledInput  type="password" :value="password" v-model="password" @change="validPassCheck"></StyledInput>
        <ErrorMessage >{{this.passerror}}</ErrorMessage>
        <StyledLabel for="pwconfirm">비밀번호확인</StyledLabel>
        <StyledInput  type="password" :value="pwconfirm" v-model="pwconfirm" @change="validIdCheck"></StyledInput>
        <ErrorMessage >{{this.iderror}}</ErrorMessage>
        <StyledLabel for="nickname">닉네임</StyledLabel>
        <StyledInput  :value="nickname" v-model="nickname" @change="validIdCheck"></StyledInput>
        <div>
          <ErrorMessage >{{this.iderror}}</ErrorMessage>
          <p>닉네임 중복확인</p>
        </div>
      </div>
    </div>
    <FooterButton>회원가입</FooterButton>
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
    }
  },
}
</script>

<style>
  .bigbox{
    width:100%;
    height: 100vh;
  }
  .Signup-Wrap{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    justify-content: start;
    width:100%;
    padding:20px 30px 0 30px;
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

  p{
    color:#636E72;
    font-size:0.625rem;
    text-align: right;
  }

</style>