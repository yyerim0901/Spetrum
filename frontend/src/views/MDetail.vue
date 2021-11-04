<template>
  <div class="Moment-Wrapper">
    <Header :isBack="true" title="MY MOMENT" :isLogo="false"></Header>
    <hr>
    <div style="width:100%; padding:10px 10px; display:flex; flex-direction:column">
      <div style="display:flex; margin:2px 0 2px 35px; align-items:center; justify-content:flex-start;">
        <img :src="getthumbnail()" alt="" class="pimg-box-small">
        <h3 style="padding:0 5px;">{{this.userInfo.nickname}}</h3>
        <i class="fas fa-trash" style="margin:0 0 0 270px;"></i>
        <i class="fas fa-edit" style="margin:0 0 0 10px;"></i>
      </div>
      <img :src="fullURL(this.files)" alt="" class="pre-img">
      <div style="margin:5px 40px; display:flex; flex-direction:column;">
        <i class="fas fa-paw"> {{this.likes}}개</i>
        <span style="text-align:start; border:solid; border-width:1px 0; border-color:#E5EAEF;">{{this.content}}</span>
        <!-- <p v-for></p> -->
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
      BASE_URL : 'http://k5b101.p.ssafy.io/resources/',
      created:undefined,
      comment:undefined,
      boardid:undefined,
    }
  },
  created(){
    this.boardid = this.$route.params.boardid;
    this.$store.dispatch('detailSBoard',this.$route.params.boardid)
    .then(res=>{
      this.files = res.data.data.filelist[0].save_file;
      this.content = res.data.data.content;
      this.likes = res.data.data.likes;
    })
    this.$store.dispatch('bringSBoardComments',this.$route.params.boardid)
    .then(res=>{
      console.log(res);
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
      const data = {
        sboardid:this.boardid,
        content:this.comment,
      }
      console.log(this.comment);
      this.$store.dispatch('requestSComment',data);
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
</style>