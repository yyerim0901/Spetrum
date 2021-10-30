<template>
  <Head >
    <Container :isBack="isBack">
      <button @click="goBack">
        <i class="el-icon-arrow-left"></i>
      </button>
      <LogoTitle :isLogo="isLogo" :title="title">
        <img src="@/assets/logo.png" alt="spetrum">
        <p>{{title}}</p>
      </LogoTitle>
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
    }
  },
  methods:{
    goBack(){
      this.$router.go(-1);
    }
  }
}
</script>

<style>

</style>