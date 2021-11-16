<template>
  <div class="Moment-Wrapper">
    <Header :isLogo="false" :isBack="false" title="냥댕모먼트" :isSearch="true"></Header>
    <hr>
    <div class="p-box" @scroll="handleInfiniteScroll">
      <div class="i-box">
        <img :src="getthumbnail()" alt="profilImg" class="pimg-box">
        <button bcolor="babypink" btype="medium" class="f-button"><h3>게시글</h3><span>{{writes.length}}</span></button>
        <button bcolor="babypink" btype="medium" class="f-button"><h3>팔로워</h3><span>{{this.followerList.length}}</span></button>
        <button bcolor="babypink" btype="medium" class="f-button"><h3>팔로우</h3><span>{{this.followList.length}}</span></button>
      </div>
      <div class="l-box">
        <div class="m-box">
          <h3 style="#636E72">{{this.nickname}}</h3>
          <h5 style="color:#B2BEC3">@{{this.userid}}</h5>
          <span class="intro">{{this.introduce}}</span>
        </div>
        <button v-if="followStatus" class="unfollow-button" @click="requestFollow">언팔로우</button>
        <button v-else class="follow-button" @click="requestFollow">팔로우</button>
      </div>
      <div>
        <div class="write-box">
          <div v-for="write in writes" :key="write.id">
            <img :src="fullURL(write)" alt="없다" class="w-img" @click="moveDetail(write.id)">
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

export default {
  name:'YourMoment',
  components:{
    Footer,
    Header,

  },
  data(){
    return{
      isActive:4,
      userid:'',
      nickname:'',
      thumbnail:'',
      introduce:'',
      followerList:[],
      followList:[],
      writes:[],
      page:1,
      BASE_URL : 'https://spetrum.io/resources/'
    }
  },
  methods:{
    getthumbnail(){
      if (this.thumbnail) {
        return this.thumbnail
      }else{
        return require("@/assets/img_logo.jpg")
      }
    },
    handleInfiniteScroll(e) {
      const { scrollTop, clientHeight, scrollHeight } = e.target;
      if (parseInt(scrollTop) + parseInt(clientHeight) + 1 !== parseInt(scrollHeight))
        return;
      if (this.writes && this.writes.length % 10 === 0) {
        //게시물이 1페이지 전채 개수가 넘으면
        this.page +=  1;
        this.$store.dispatch('bringOtherSBoard',{userid:this.userid, page:this.page})
        .then(res=>{
          console.log(res); 
          this.writes.push(...res.data.data);
          console.log(this.writes,'게시물');
        })
      } 
    },
    moveDetail(id){
      console.log(id);
      this.$router.push({name:'MDetail',params:{'boardid':id}});
    },

    fullURL(url){
      if (url.filelist[0]){
        var full = this.BASE_URL + url.filelist[0].save_file;
      } else{
        full = require('@/assets/img_logo.jpg')
      }
      console.log(full);
      return full;
    },
    requestFollow(){
      const formData = new FormData();
      formData.append('to',this.userid);
      formData.append('from',localStorage.getItem('userid'));
      this.$store.dispatch('handleFollow',formData)
      .then(res=>{
        if (res.data.statusCode === 200){
          this.$store.dispatch('requestSBoardUser',this.userid)
          .then(res=>{
            this.followerList = res.data.followerList;
            this.followList = res.data.followList;
          })
        }
      })
    }

  },
  created(){
    const nowUser = this.$route.params.userid;
    if (nowUser == localStorage.getItem('userid')){
      this.$router.push({name:'Moment'});
      console.log('돌아가');
    }
    else{
      this.$store.dispatch('requestSBoardUser',nowUser)
      .then(res=>{
        this.userid = res.data.user.userId;
        this.nickname = res.data.user.nickname;
        this.thumbnail = res.data.user.thumbnail;
        this.introduce = res.data.user.introduce;
        this.followerList = res.data.followerList;
        this.followList = res.data.followList;
      })
      this.$store.dispatch('bringOtherSBoard',{userid:nowUser, page:this.page})
      .then(res=>{
        console.log(res.data.data);
        this.writes = res.data.data;
      })
    }
  },
  computed:{
    followStatus(){
      if (this.followerList.includes(localStorage.getItem('userid'))){
          return true
        } else{
          return false
        }
    }
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
    padding:10px 15px;
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
    background-color:#636E72; 
    color:white; 
    height:33px;
    width:100px;
    border-radius:6px;
    font-size:1rem;
  }

</style>