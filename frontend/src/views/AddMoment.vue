<template>
  <div class="Moment-Wrapper">
    <Header :isLogo="false" :isBack="true" title="ADD MOMENT"></Header>
    <hr>
    <div>
      <div class="addbox">
        <img :src="this.imgprev" alt="" class="prev-img">
        <input  type="file" @change="imageChange" ref="profileImage">
      </div>
      <textarea name="content"  cols="30" rows="10" class="con-box" placeholder="내용을 입력해주세요" v-model="contents"></textarea>
    </div>
    <FooterButton @click="handleWrite">게시글 작성하냥</FooterButton>
  </div>
</template>

<script>
import FooterButton from '../components/atoms/FooterButton'
import Header from '../components/molecules/Header'
import axios from 'axios';
export default {
  name:'AddMoment',
  components:{
    FooterButton,
    Header
  },
  data(){
    return{
      addimage:null,
      imgprev:null,
      contents:null,
    }
  },
  methods:{
    imageChange(){
      this.addimage = this.$refs.profileImage.files[0];
      if (this.addimage) {
        this.imgprev = URL.createObjectURL(this.addimage);
        }
    },
    handleWrite(){
      if (this.addimage && this.contents){
        const formData = new FormData();
        formData.append('content',this.contents);
        formData.append('snsfiles',this.addimage);
        axios({
          url:'http:/localhost:8080/api/sns/',
          method:'post',
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
    }
  }
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