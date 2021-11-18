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
import axios from 'axios'
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
      dogging: {},
    }
  },
  created(){
    this.doggingId = this.$route.params.doggingid;
  },
  methods: {
    getDoggingDetail() {
      axios({
        url: 'https://spetrum.io:8080/api/dogging/detail',
        method: "GET",
        headers: {
          'Authorization':localStorage.getItem('token'),
        },
        params: {
          doggingId : this.doggingId
        }
      }).then(res => {
        this.dogging = res.data
      })
    }
  },
}
</script>