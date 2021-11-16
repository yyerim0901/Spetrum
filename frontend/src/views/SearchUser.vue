<template>
  <div class="Page-Wrapper">
    <Header :isLogo="false" :isBack="true" :isSearch="false" title="유저 검색"></Header>
    <div>
      <input type="text" class="search-bar" placeholder="유저아이디를 입력해주세요" v-model="keyword" @input="handleSearch($event)">
      <div class="user-box" v-for="user in userlist" :key="user.id">
        <img :src="getthumbnail(user.thumbnail)" alt="profilImg" class="pimg-box">
        <div class="nametag">
          <h4>{{user.nickname}}</h4>
          <h5 style="color:#B2BEC3">@{{user.userId}}</h5>
        </div>
      </div>
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
      BASE_URL : 'https://spetrum.io/resources/',
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
        var fullurl = 'https://spetrum.io/resources/' + this.userInfo.thumbnail
        return fullurl
      }else{
        return require("@/assets/img_logo.jpg")
      }
    },
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
}

.nametag{
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-left:15px;
}
</style>