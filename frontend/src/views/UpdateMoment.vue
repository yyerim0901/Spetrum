<template>
  <div class="Moment-Wrapper">
    <Header :isLogo="false" :isBack="true" title="UPDATE MOMENT"></Header>
    <hr>
    <div>
      <div class="addbox">
        <img :src="this.imgprev" alt="" class="prev-img">
      </div>
      <textarea name="content"  cols="30" rows="10" class="con-box" placeholder="내용을 입력해주세요" v-model="contents"></textarea>
    </div>
    <FooterButton @click="updateWrite">게시글 수정하냥</FooterButton>
  </div>
</template>

<script>
import FooterButton from '../components/atoms/FooterButton'
import Header from '../components/molecules/Header'
import axios from 'axios';
export default {
  name:'MUpdate',
  components:{
    FooterButton,
    Header
  },
  data(){
    return{
      BASE_URL : 'https://spetrum.io/resources/',
      addimage:null,
      imgprev:null,
      contents:null,
      boardid:null,
    }
  },
  created(){
    this.boardid = this.$route.params.boardid;
    this.$store.dispatch('detailSBoard',this.$route.params.boardid)
    .then(res=>{
      this.imgprev = this.fullURL(res.data.data.filelist[0].save_file);
      this.contents = res.data.data.content;
    })
  },
  methods:{
    updateWrite(){
      if (this.contents){
        const formData = new FormData();
        formData.append('content',this.contents);
        axios({
          url:'https://spetrum.io:8080/api/sns/' + this.boardid,
          method:'put',
          data:formData,
          headers:{
            'Content-Type': 'multipart/form-data',
            'Access-Control-Allow-Origin':'*',
            'Authorization':localStorage.getItem('token'),
          }
        })
          .then(res=>{
            console.log(res);
            this.$router.push({name:'Moment'})
          })
      }else{
        alert('내용을 작성해주세요');
      }
    },
    fullURL(url){
      const full = this.BASE_URL + url;
      return full;
    },
  },
}
</script>

<style>
  .addbox{
    display:flex;
    flex-direction: column;
  }
  .prev-img{
    width: 400px;
    height:400px;
    margin: 20px 0;
  }

  .con-box{
    border: solid 1px;
    border-color: #E5EAEF;
    width:400px;
    height: 100px;
    margin:10px 0;
    text-align:start;

  }
</style>