<template>
  <div class="Page-Wrapper">
    <Header :isLogo="false" :isBack="true" :isSearch="false" title="유저 검색"></Header>
    <div>
      <input type="text" class="search-bar" placeholder="유저아이디를 입력해주세요" v-model="keyword" @change="handleSearch">
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
    }
  },
  methods:{
    handleSearch(){
      this.$store.dispatch('requestUserSearch',this.keyword)
      .then(res=>{
        console.log(res);
      })
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
</style>