<template>
  <div class="Wrapper">
    <img src="@/assets/logo.png" alt="logo" class="logo-img">
    <img src="../assets/img_logo.jpg" alt="dog" class="dog-img">
    <div class="FormContainer">
      <StyledLabel for="userid">아이디</StyledLabel>
      젠킨스 
      <StyledInput  :value="userId" v-model="userId" @change="validIdCheck"></StyledInput>
      <ErrorMessage :message="iderror">{{this.iderror}}</ErrorMessage>
      <StyledLabel for="password">비밀번호</StyledLabel>
      <StyledInput type="password" :value="password" v-model="password" @change="validPassCheck"></StyledInput>
      <ErrorMessage :message="pderror">{{this.pderror}}</ErrorMessage>
      <div class="isUser">
        <p>아직 회원이 아니신가요?</p>
        <button class="userbtn" @click="moveSignup">회원가입</button>
      </div>
      <StyledButton class="btn" bcolor="babypink" btype="large" @click="handleSignIn">로그인</StyledButton>
    </div>
  </div>
</template>

<script>
import StyledButton from '../components/atoms/StyledButton'
import StyledInput from '../components/atoms/StyledInput'
import StyledLabel from '../components/atoms/StyledLabel'
import ErrorMessage from '../components/atoms/ErrorMessage'
export default {
  name: 'SignIn',
  components :{
    StyledButton,
    StyledLabel,
    StyledInput,
    ErrorMessage,
  },
  data(){
      return {
        userId:null,
        password:null,
        iderror:null,
        pderror:null,
        idValid:false,
        pdValid:false,
      }
    },
  methods:{
    handleSignIn(){
      if (this.idValid && this.pdValid) {
        const formData = new FormData();
        formData.append("userId",this.userId);
        formData.append("password",this.password);
        this.$store.dispatch('requestSignIn',formData);
      }  
    },
    validIdCheck(e){
      if (e.target.value.length < 2){
        this.iderror = "아이디를 입력해주세요";
      }
      else{
        this.iderror = '';
        this.idValid = true;
      }
    },
    validPassCheck(e){
      const rule = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&^])[A-Za-z\d$@$!%*#?&^]{3,16}$/ ;
      if (!rule.test(e.target.value)){
        this.pderror = '비밀번호는 영문, 숫자, 특수문자가 조합되어야 합니다.';
      }
      else {
        this.pderror = '';
        this.pdValid = true;
      }
    },
    moveSignup(){
      this.$router.push({name:'SignUp'});
    }
  },

}
</script>

<style>
  .Wrapper{
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    margin: 150px 0 0 0;
    align-items: center;
    height: 100vh;
    width:100%;
    padding:0 30px;
  }

  .dog-img{
    width: 200px;
    padding: 2px 0;
    margin: 0 10px 0 0;
  }

  .logo-img{
    width: 150px;
    margin:0 0 10px 10px;
  }

  .FormContainer{
    width: 100%;
    margin: 20px 0;
  }

  .btn{
    margin:10px 0 0 0;
  }

  .isUser {
    display: flex;
    text-align: start;
    padding: 3px 0 0 0;

  }

  .userbtn{
    font-size:0.700rem;
    padding: 0 0 0 290px;
  }
  p {
    font-size:0.625rem;
  }
</style>
