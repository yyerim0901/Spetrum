<template>
  <div class="Moment-Wrapper">
    <Header :isBack="true" title="MY MOMENT" :isLogo="false"></Header>
    <hr>
    <div style="width:100%; padding:10px 10px; display:flex; flex-direction:column; overflow:scroll">
      <div style="display:flex; margin:2px 0 2px 35px; align-items:center; justify-content:flex-start;">
        <img :src="getthumbnail()" alt="" class="pimg-box-small">
        <h3 style="padding:0 5px;">{{this.writernickname}}</h3>
        <i class="[isWriter ? fas fa-edit : '',edit-icon ]" style="margin:0 0 0 270px;" ></i>
        <i class="[!isWriter ? fas fa-trash : '',edit-icon ]" style="margin:0 0 0 10px;"></i>
      </div>
      <img :src="fullURL(this.files)" alt="" class="pre-img">
      <div style="margin:5px 40px; display:flex; flex-direction:column;">
        <i class="fas fa-paw"> {{this.likes}}개</i>
        <span style="text-align:start; border:solid; border-width:1px 0; border-color:#E5EAEF;">{{this.content}}</span>
        <div v-for="c in commentList" :key="c.id">
          <p class="comment">{{c.content}} - 작성자: {{c.user.nickname }}</p>
        </div>
        <div>
          <i class="far fa-comment-dots" style="margin:0 5px 0 0; font-size:1rem"></i>
          <CommentInput :value="comment" v-model="comment" />
          <StyledButton btype="realsmall" bcolor="babypink" @click="sendComment()">작성</StyledButton>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from 'vuex'
import Header from '../components/molecules/Header.vue'
import CommentInput from '../components/atoms/CommentInput'
import StyledButton from '../components/atoms/StyledButton'
export default {
  name:'MDetail',
  components:{
    Header,
    CommentInput,
    StyledButton
  },
  data(){
    return{
      files:undefined,
      content:undefined,
      likes:undefined,
      BASE_URL : 'https://spetrum.io/resources/',
      created:undefined,
      comment:undefined,
      boardid:undefined,
      commentList:null,
      writerid:null,
      writernickname:null,
      isWriter:false,
    }
  },
  created(){
    this.boardid = this.$route.params.boardid;
    this.$store.dispatch('detailSBoard',this.$route.params.boardid)
    .then(res=>{
      this.files = res.data.data.filelist[0].save_file;
      this.content = res.data.data.content;
      this.likes = res.data.data.likes;
      this.writerid = res.data.data.userid;
      if (localStorage.getItem('userid') === this.writerid ){
        this.isWriter = true
      }else{
        this.isWriter = false
      }
      this.$store.dispatch('requestSBoardUser',this.writerid)
      .then(res=>{
        this.writernickname = res.data.user.nickname;
      })
      .catch(err=>{
        console.log(err);
      })
    })
    this.$store.dispatch('bringSBoardComments',this.boardid)
    .then(res=>{
      console.log(res);
      this.commentList = res.data.data;
    })
  },
  computed:{
    ...mapState(['userInfo']),
  },
  methods:{
    fullURL(url){
      const full = this.BASE_URL + url;
      return full;
    },
    getthumbnail(){
      if (this.userInfo.thumbnail) {
        return this.userInfo.thumbnail
      }else{
        return require("@/assets/img_logo.jpg")
      }
    },
    sendComment(){
      const formdata = new FormData();
      formdata.append('sboardid',this.boardid);
      formdata.append('content',this.comment);
      this.$store.dispatch('requestSComment',formdata)
        // this.$store.dispatch('bringSBoardComments',this.boardid)
        // .then(res=>{
        //   console.log(res);
        //   this.commentList = res.data.data;
        // })
    }
  }
}
</script>

<style>
  .pre-img{
    width: 400px;
    height: 400px;
    margin:5px auto;
    padding:2px 0;
    border:solid;
    border-width:2px 0; 
    border-color:#E5EAEF;
  }

  .pimg-box-small{
    width:40px;
    height: 40px;
    border-radius: 70%;
    border:solid #E5EAEF 1px;
  }

  .edit-icon{
    display:none;
  }

  .comment{
    font-size:0.9rem;
  }
</style>