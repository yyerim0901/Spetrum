<template>
  <div class="Page-Wrapper">
    <Header :isLogo="false" :isBack="true" :isSearch="false" title="유저 검색"></Header>
    <div class="se-box">
      <input type="text" class="search-bar" placeholder="유저아이디를 입력해주세요" v-model="keyword" @input="handleSearch($event)">
      <div class="user-box" v-for="user in userlist" :key="user.id">
        <img :src="getthumbnail(user.thumbnail)" alt="profilImg" class="psimg-box" @click="goUserMoment(user.userId)">
        <div class="nametag" @click="goUserMoment(user.userId)">
          <h4>{{user.nickname}}</h4>
          <h5 style="color:#B2BEC3">@{{user.userId}}</h5>
        </div>
      </div>
      <!-- <div class="user-box">
        <img src="../assets/img_logo.jpg" alt="profilImg" class="psimg-box">
        <div class="nametag" @click="goUserMoment(test12345)">
          <h4>테스토</h4>
          <h5 style="color:#B2BEC3" >@test1234</h5>
        </div>
      </div> -->
    </div>
  </div>
</template>

<script>
import Header from '../components/molecules/Header.vue'
export default {
  name:'SearchUser',
  components:{
    Header,
  },
  data(){
    return{
      keyword:'',
      userlist :[],
      BASE_URL : 'https://spetrum.ddns.net:8000/resources/',
    }
  },
  methods:{
    handleSearch(e){
      this.$store.dispatch('requestUserSearch',e.target.value)
      .then(res=>{
        this.userlist = res.data.userlist;
      })
    },
    getthumbnail(url){
      if (url) {
        var fullurl = this.BASE_URL + url
        return fullurl
      }
      else{
        return require("@/assets/img_logo.jpg")
      }
    },
    goUserMoment(userid){
      this.$router.push({name:'YourMoment',params:{'userid':userid}});
    }
  },
  
}
</script>

<style>
.Page-Wrapper {
    justify-content: flex-start;
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100vh;
    width:100%;
  }
.search-bar{
  width: 400px;
  height: 45px;
  background-color: #F6F8FA;
  border:none;
  border-radius: 6px;
  margin: 10px 0;
  padding-left:10px;
  outline: none;
}

.user-box{
  display: flex;
  justify-content: flex-start;
  margin-left: 50px;
  margin:5px 0 5px 50px ;
  height: 80px;
}

.nametag{
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-left:15px;
  align-items: flex-start;
}

.se-box{
  width: 100%;
  height: 100vh;
  text-align: center;
  overflow-y:scroll;
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

  .psimg-box{
    width:80px;
    height: 80px;
    border-radius: 70%;
    border:solid #E5EAEF 1px;
  }


</style>