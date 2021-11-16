<template>
  <div class="MyDogging-Wrapper">
    <Header :isLogo="false" :isBack="false" title="나의 도깅"></Header>
    <div class="p-box" @scroll="handleInfiniteScroll">

    </div>
    <hr class="fott">
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
      page : 1,
      mydoggings:[],
      userid:"",
    }
  },
  created(){
    this.userid = localStorage.getItem('userid')
    console.log(this.userid)
    this.$store.dispatch('bringMyDogging',this.page)
    .then(res => {
      console.log(res.data.data)
      this.mydoggings = res.data.data
    })
  },
  methods: {
    handleInfiniteScroll(e) {
      const { scrollTop, clientHeight, scrollHeight } = e.target;
      if (parseInt(scrollTop) + parseInt(clientHeight) + 1 !== parseInt(scrollHeight))
        return;
      if (this.mydoggings && this.mydoggings.length % 10 === 0) {
        //게시물이 1페이지 전채 개수가 넘으면
        console.log(this.mywrites.length,'길이');
        this.page +=  1;
        console.log(this.page);
        this.$store.dispatch('bringMyDogging',this.page)
        .then(res=>{
          console.log(res);
          this.mydoggings.push(...res.data.data);
          console.log(this.mydoggings,'게시물');
        })
      }
    }
  },
}
</script>

<style>
  .MyDogging-Wrapper {
    justify-content: flex-start;
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100vh;
    width:100%;

  }
</style>