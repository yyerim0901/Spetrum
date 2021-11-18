<template>
  <div class="Moment-Wrapper">
    <Header :isLogo="false" :isBack="false" :isSearch="true" title="냥댕모먼트"></Header>
    <div v-if="userInfo !== undefined" class="p-box"  @scroll="handleInfiniteScroll">
      <div class="i-box">
        <img :src="getthumbnail()" alt="profilImg" class="pimg-box">
        <button bcolor="babypink" btype="medium" class="f-button"><h3>게시글</h3><span>{{this.mywritesLength}}</span></button>
        <button bcolor="babypink" btype="medium" class="f-button"><h3>팔로워</h3><span>{{this.followerLength}}</span></button>
        <button bcolor="babypink" btype="medium" class="f-button"><h3>팔로우</h3><span>{{this.followLength}}</span></button>
      </div>
      <div class="l-box">
        <div class="m-box">
          <h3 style="#636E72">{{this.userInfo.nickname}}</h3>
          <h5 style="color:#B2BEC3">@{{this.userInfo.userid}}</h5>
          <span class="intro">{{this.userInfo.introduce}}</span>
        </div>
        <StyledButton bcolor="pink" btype="small" @click="AddWrite">글작성하기</StyledButton>
      </div>
      <div>
        <div class="write-box">
          <div v-for="mywrite in mywrites" :key="mywrite.id">
            <img :src="fullURL(mywrite)" alt="없다" class="w-img" @click="moveDetail(mywrite.id)">
          </div>
        </div>
      </div>
    </div>

    <Footer :isActive="isActive"></Footer>
  </div>
</template>

<script>
import Footer from '../components/molecules/Footer.vue'
import Header from '../components/molecules/Header.vue'
import StyledButton from '../components/atoms/StyledButton'
// import axios from 'axios'
import {mapState} from 'vuex';

export default {
  name:'Moment',
  components:{
    Footer,
    Header,
    StyledButton

  },
  data(){
    return{
      isActive:4,
      userid: '',
      isfollowed:true,
      mywrites:[],
      BASE_URL : 'https://spetrum.io/resources/',
      page:1
    }
  },
  computed:{
    ...mapState(['userInfo']),
    followerLength(){
      if (this.userInfo.followerList){
        return this.userInfo.followerList.length
      }else{
        return 0
      }
    },
    followLength(){
      if (this.userInfo.followList){
        return this.userInfo.followList.length
      }else{
        return 0
      }
    },
    mywritesLength(){
      if (this.mywrites) {
        return this.mywrites.length
      }else{
        return 0
      }
    }

  },
  methods:{
    getthumbnail(){
      if (this.userInfo.thumbnail) {
        var fullurl = this.BASE_URL + this.userInfo.thumbnail
        console.log(fullurl,'fullurl');
        return fullurl
      }else{
        return require("@/assets/img_logo.jpg")
      }
    },
    fullURL(url){
      if (url.filelist[0]){
        var full = this.BASE_URL + url.filelist[0].save_file;
      } else{
        full = require('@/assets/noimage.png')
      }
      return full;
    },
    AddWrite(){
      this.$router.push({name:'AddMoment'})
    },
    moveDetail(id){
      console.log(id);
      this.$router.push({name:'MDetail',params:{'boardid':id}});
    },
    handleInfiniteScroll(e) {
      const { scrollTop, clientHeight, scrollHeight } = e.target;
      if (parseInt(scrollTop) + parseInt(clientHeight) + 1 !== parseInt(scrollHeight))
        return;
      if (this.mywrites && this.mywrites.length % 10 === 0) {
        //게시물이 1페이지 전채 개수가 넘으면
        this.page +=  1;
        this.$store.dispatch('bringSBoard',this.page)
        .then(res=>{
          this.mywrites.push(...res.data.data);
          console.log(this.mywrites,'게시물');
        })
      } 
    }
  },
  mounted() {
    this.$store.dispatch('requestUser',this.userid);
    this.$store.dispatch('bringSBoard',this.page)
    .then(res=>{
      this.mywrites = res.data.data;
      console.log('여기서에러')
      console.log(res)
    })
    .catch(err=>{
      console.log('나는 게시판 받아오는 에러');
      console.log(err)
    })
  },
  created(){
    // this.$store.dispatch('Store/fetchData');
    this.userid = localStorage.getItem('userid');
    // console.log(localStorage.getItem)
    // console.log(this.userid)
    // axios({
    //     url:'https://spetrum.io:8080/api/sns/',
    //     method:'get',
    //     params:{
    //       page:1,
    //     },
    //     headers: {
    //       "Authorization": localStorage.getItem("token")
    //     },
    //   })
    //   .then(res => {
    //     console.log(res.data.data)
    //     this.mywrites = res.data.data;
    //     this.$store.dispatch ('requestUser',this.userid);
    //   })
    // this.$store.dispatch('bringSBoard',this.page)
    // .then(res=>{
    //   this.mywrites = res.data.data;
    // })
    this.$store.dispatch('requestUser',this.userid);
    console.log(this.userInfo.thumbnail,'요펑가니?')
    this.$store.dispatch('bringSBoard',this.page)
    .then(res=>{
      this.mywrites = res.data.data;
      console.log('여기서에러')
      console.log(res)
    })
    .catch(err=>{
      console.log('나는 게시판 받아오는 에러');
      console.log(err)
    })
  },
}
</script>

<style>
  
  .w-img{
    width:150px;
    height:150px;
    /* border:solid 1px #E5EAEF; */
  }
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
    margin:0 0 20px 0;
  }

  .p-box{
    width:100%;
    padding:10px 3px;
    display: flex;
    flex-direction: column;
    overflow-y: scroll;
    margin-bottom:15px;
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

  .write-box{
    display: grid;
    margin:0 0 0 10px;
    grid-gap:0px;
    grid-template-columns: 150px 150px 150px;
  }

  ::-webkit-scrollbar {
    width: 10px;
    background-color: #f6f8fa;
    border-radius: 10px;
  }
  ::-webkit-scrollbar-thumb {
    background-color: #E5EAEF;
    height:30px;
    border-radius: 10px;
  }

</style>