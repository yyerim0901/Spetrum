<template>
  <Head>
    <Container :isBack="isBack" :isSearch="isSearch">
      <button @click="goBack">
        <i class="ii fas fa-chevron-left"></i>
      </button>
      <LogoTitle :isLogo="isLogo" :title="title">
        <img src="@/assets/logo.png" alt="spetrum">
        <p>{{title}}</p>
      </LogoTitle>
      <button @click="goSearch">
        <i class="iii fa fa-search"></i>
      </button>
    </Container>

  </Head>
</template>

<script>
import {Container,Head,LogoTitle} from './Header/index'
export default {
  name:'Header',
  components:{
    Head,
    LogoTitle,
    Container
  },
  props:{
    isBack:{
      type:Boolean,
      default:false,
    },
    isLogo:{
      type:Boolean,
      default:false,
    },
    title:{
      type:String,
      default:'',
    },
    isSearch:{
      type:Boolean,
      default:false,
    }
  },
  methods:{
    goBack(){
      this.$router.go(-1);
    },
    goSearch(){
      this.$router.push("/user/search");
    }
  }
}
</script>

<style>

</style>