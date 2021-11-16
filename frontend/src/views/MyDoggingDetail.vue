<template>
    <div class="MyDogging-Wrapper">
        <Header :isLogo="false" :isBack="true" title="나의 도깅"></Header>
        <div class="p-box">
            <div>여기는 map 자리</div>
            <div></div>
        </div>
        <Footer :isActive="isActive"></Footer>
    </div>
</template>

<script>
import Footer from '../components/molecules/Footer.vue'
import Header from '../components/molecules/Header.vue'

export default {
  name:'Dogging',
  components:{
    Footer,
    Header
  },
  data(){
    return{
      isActive:5,
      doggingId:"",
    }
  },
  created(){
    this.doggingId = this.$route.params.doggingId;
  },
  methods: {
    
  },
}
</script>