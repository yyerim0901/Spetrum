<template>
  <div class="Moment-Wrapper">
    <Header :isBack="true" title="MY MOMENT" :isLogo="false"></Header>
    <div style="width:100%; display:flex; flex-direction:column; overflow-y:scroll;">
      <div style="display:flex; margin:10px 0 2px 25px; align-items:center;">
        <img style="margin-left:10px;" :src="getthumbnail()" alt="" class="pimg-box-small">
        <p style="padding:0 5px; font-size:16px; font-weight:600;">{{this.writernickname}}</p>
        <div style="justify-content:right;" :class="[isWriter ? 'edit-icon' : '']">
          <i class="fas fa-edit" style="margin:0 0 0 10px;" @click="moveEdit"></i>
          <i class="fas fa-trash" style="margin:0 0 0 10px;" @click="deletecheck"></i>
        </div>
      </div>
      <img :src="fullURL(this.files)" alt="" class="pre-img">
      <div style="margin:5px 40px; display:flex; flex-direction:column;">
        <div style="height:60px; text-align:start; border:solid; 
        border-width:1px 0; border-color:#E5EAEF; padding:8px; font-size:15px; 
        font-weight:bold;" v-html="this.content"></div>
        <div style="margin : 3px 1px" v-for="c in commentList" :key="c.id">
          <p class="comment">
            <span style=" border-radius:5px; padding:2px; font-weight:700;">{{ c.user.nickname }}</span>
            &nbsp;&nbsp;&nbsp;<span>{{ c.content }}</span>
            </p>
        </div>
        <div style="margin-bottom:50px; text-align:center;">
          <i class="far fa-comment-dots" style="margin:0 5px 0 0; font-size:1rem"></i>
          <CommentInput :value="comment" v-model="comment" />
          <StyledButton btype="realsmall" bcolor="babypink" @click="sendComment()">작성</StyledButton>
        </div>
        <Modal v-if="showModal" @close="showModal=false" @ok="handleDelete">
          <h3 slot="header">
            게시글을 정말 삭제하시겠냥?
          </h3>
          <div slot="body"></div>
        </Modal>
        <!--원래 footer가 없나?-->
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from 'vuex'
import Header from '../components/molecules/Header.vue'
import CommentInput from '../components/atoms/CommentInput'
import StyledButton from '../components/atoms/StyledButton'
import Modal from '../components/molecules/Modal.vue'

export default {
  name:'MDetail',
  components:{
    Header,
    CommentInput,
    StyledButton,
    Modal
  },
  data(){
    return{
      files:[],
      content:undefined,
      likes:undefined,
      BASE_URL : 'https://localhost:8080/resources/',
      comment:undefined,
      boardid:undefined,
      commentList:null,
      writerid:null,
      writernickname:null,
      isWriter:false,
      showModal:false,
      profile:null,
    }
  },
  created(){
    this.boardid = this.$route.params.boardid;
    this.$store.dispatch('detailSBoard',this.$route.params.boardid)
    .then(res=>{
      this.files = res.data.data.filelist;
      this.content = res.data.data.content;
      this.likes = res.data.data.likes;
      this.writerid = res.data.data.userid;
      if (localStorage.getItem('userid') === this.writerid ){
        this.isWriter = false
      }else{
        this.isWriter = true
      }
      this.$store.dispatch('requestSBoardUser',this.writerid)
      .then(res=>{
        this.writernickname = res.data.user.nickname;
        this.profile = res.data.user.thumbnail;
      })
      .catch(err=>{
        console.log(err);
      })
    })
    this.$store.dispatch('bringSBoardComments',this.boardid)
    .then(res=>{
      this.commentList = res.data.data;
    })
  },
  computed:{
    ...mapState(['userInfo']),
  },
  methods:{
    fullURL(url){
      if (url.length !== 0){
        var full = this.BASE_URL + url[0].save_file;
      } else{
        full = require('@/assets/noimage.png')
      }
      return full;
    },
    moveEdit(){
      this.$router.push({name:'EditMoment', params:{'boardid':this.boardid}})
    },
    getthumbnail(){
      if (this.profile !== null) {
        var fullurl = this.BASE_URL + this.profile;
        return fullurl
      }else{
        return require("@/assets/img_logo.jpg")
      }
    },
    sendComment(){
      const formdata = new FormData();
      formdata.append('sboardid',this.boardid);
      formdata.append('content',this.comment);
      this.$store.dispatch('requestSComment',formdata)
      .then(res=>{
        console.log(res);
        console.clear();
        this.$store.dispatch('bringSBoardComments',this.boardid)
        .then(res=>{
          console.log(res);
          console.clear();
          this.commentList = res.data.data;
          this.comment = '';
        })
      })
    },
    deletecheck(){
      this.showModal = true;
    },
    handleDelete(){
        this.$store.dispatch('handleMomentDelete',this.boardid)
        this.$router.push("/mymoment")
    },
    updateSBoard() {
      this.$router.push({name:'EditMoment'})
    },
  },
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