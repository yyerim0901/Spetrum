<template>
  <div class="Wrapper">
    <img src="@/assets/logo.png" alt="logo" class="logo-img">
    <img src="../assets/img_logo.jpg" alt="dog" class="dog-img">
    <div class="FormContainer">
      <StyledLabel for="userid">아이디</StyledLabel>
      <StyledInput  :value="userId" v-model="userId" @change="validIdCheck"></StyledInput>
      <ErrorMessage :message="iderror">{{this.iderror}}</ErrorMessage>
      <StyledLabel for="password">비밀번호</StyledLabel>
      <StyledInput type="password" :value="password" v-model="password" @change="validPassCheck"></StyledInput>
      <ErrorMessage :message="pderror">{{this.pderror}}</ErrorMessage>
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
        const data = {
          'userId':this.userId,
          'password':this.password,
        }
        this.$store.dispatch('requestSignIn',data);
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
    }
  },

}
</script>

<style>
  .Wrapper{
    display: flex;
    flex-direction: column;
    justify-content: start;
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
    margin:40px 0 0 0;
  }
</style>