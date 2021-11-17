<template>
  <div class="Moment-Wrapper">
    <Header :isLogo="false" :isBack="true" title="ADD MOMENT"></Header>
    <div>
      <div class="addbox">
        <img :src="this.imgprev" alt="" class="prev-img">
      </div>
      <textarea name="content"  cols="30" rows="10" class="con-box" placeholder="내용을 입력해주세요" v-model="content"></textarea>
    </div>
    <FooterButton @click="handleWrite">게시글 작성하냥</FooterButton>
  </div>
</template>

<script>
import FooterButton from '../components/atoms/FooterButton'
import Header from '../components/molecules/Header'

export default {
  name:'EditMoment',
  components:{
    FooterButton,
    Header
  },
  data(){
    return{
      files:[],
      content: undefined,
      BASE_URL : 'http://spetrum.io/resources/',
      addimage:null,
      imgprev:null,
      boardid:null,
    }
  },
  methods:{
    handleWrite(){
      if (this.content){
        const formData = new FormData();
        formData.append('content',this.content);
        formData.append('sboardid',this.boardid);
        this.$store.dispatch('handleMomentEdit',formData)
      }else{
        alert('내용을 작성해주세요');
      }
    },
  },
  created(){
    this.boardid = this.$route.params.boardid;
    this.$store.dispatch('detailSBoard',this.$route.params.boardid)
    .then(res=>{
      this.files = res.data.data.filelist;
      this.content = res.data.data.content;
      if (this.files.length !== 0){
        this.imgprev = this.BASE_URL + this.files[0].save_file;
      } else{
        this.imgprev = require('@/assets/noimage.png')
      }
    })
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