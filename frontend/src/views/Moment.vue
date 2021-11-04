<template>
  <div class="Moment-Wrapper">
    <Header :isLogo="false" :isBack="false" title="냥댕모먼트"></Header>
    <hr>
    <div class="p-box">
      <div class="i-box">
        <img :src="getthumbnail()" alt="profilImg" class="pimg-box">
        <button bcolor="babypink" btype="medium" class="f-button"><h3>게시글</h3><span>32</span></button>
        <button bcolor="babypink" btype="medium" class="f-button"><h3>팔로워</h3><span>32</span></button>
        <button bcolor="babypink" btype="medium" class="f-button"><h3>팔로우</h3><span>32</span></button>
      </div>
      <div class="l-box">
        <div class="m-box">
          <h3 style="#636E72">{{this.userInfo.nickname}}</h3>
          <h5 style="color:#B2BEC3">@{{this.userInfo.userid}}</h5>
          <!-- <p>{{this.userInfo.introduce}}</p> -->
          <span class="intro">방가방가</span>
        </div>
      </div>

    </div>
    <hr class="fott">
    <Footer :isActive="isActive"></Footer>
  </div>
</template>

<script>
import Footer from '../components/molecules/Footer.vue'
import Header from '../components/molecules/Header.vue'
import axios from '@/axios/index'
import {mapState} from 'vuex';
export default {
  name:'Moment',
  components:{
    Footer,
    Header,

  },
  data(){
    return{
      isActive:4,
      userid: '',
      isfollowed:true,
      notme:true,
      mywrites:null,
    }
  },
  computed:{
    ...mapState(['userInfo']),
  },
  methods:{
    getthumbnail(){
      if (this.userInfo.thumbnail) {
        return this.userInfo.thumbnail
      }else{
        return require("@/assets/img_logo.jpg")
      }
    }
  },
  created(){
    this.userid = localStorage.getItem('userid');
    this.$store.dispatch('requestUser',this.userid);
    axios({
      url:'/sns/',
      method:'get',
      params:{
        page:1,
      }
    })
      .then(res=>{
        this.mywrites = res.data.filelist;
        console.log(res);
      })
  }

}
</script>

<style>
  .Moment-Wrapper {
    justify-content: flex-start;
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100vh;
    width:100%;
  }
  .pimg-box{
    width:100px;
    height: 100px;
    border-radius: 70%;
    border:solid #E5EAEF 1px;
  }

  .l-box{
    display:flex;
    align-items: center;
  }

  .p-box{
    width:100%;
    padding:10px 3px;
    display: flex;
    flex-direction: column;
  }

  .i-box{
    display:flex;
    align-items: center;
    justify-content: center;
  }

  .f-button{
    background-color: #EE9CA7;
    color:white;
    height: 65px;
    width:100px;
    border: solid 0.5px;
  }

  .m-box{
    display:flex;
    flex-direction: column;
    margin:10px 50px;
    width: 50%;
  }

  .intro{
    text-align: start;
    font-size: 0.8rem;
  }

  .follow-button{
    background-color:#FBD786; 
    color:white; 
    height:33px;
    width:100px;
    border-radius:6px;
    font-size:1rem;
  }

  .unfollow-button{
    background-color:#FBD786; 
    color:white; 
    height:33px;
    width:100px;
    border-radius:6px;
    font-size:1rem;
  }

</style>