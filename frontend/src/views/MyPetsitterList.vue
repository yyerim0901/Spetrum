<template>
  <div class="MyPetsitter-Wrapper">
    <Header :isLogo="false" :isBack="true" :isSearch="false" title="나의 게시글"></Header>
    <!-- <div class="p-box-petsitter">
      <div class="my-p-box">
        <img class="img-box" src="../assets/img_logo.jpg" alt="사진 안 뜸">
        <div class="in-my-p-box">
          <div class="my-p-text">
            <h3>여기는 제모오오오ㅗㅇ옥</h3>
            <p style="height:4px;"></p>
            <p style="font-size:small;">작성 일자 : 2021-11-11</p>
          </div>
          <StyledButton btype="small" style="background-color:white; color:gray; margin:0px;" @click="detailof(1)">자세히보기</StyledButton>
        </div>
      </div> 
    </div> -->
    <div class="null-text" v-if="MyPetsitterNull">
        <h2>내가 쓴 겟!시터가 없다냥</h2>
    </div>
    <div class="p-box-petsitter">
      <div class="my-p-box" v-for="mypetsitter in mypetsitterList" :key="mypetsitter.id">
        <img class="img-box" :src="fullURL(mypetsitter.picture)" alt="사진 안 뜸">
        <div class="in-my-p-box">
          <div class="my-p-text">
            <h3>{{mypetsitter.title}}</h3>
            <p style="height:4px;"></p>
            <p style="font-size:small;">작성 일자 : {{mypetsitter.created.substr(0,10)}}</p>
          </div>
          <StyledButton btype="small" style="background-color:white; color:gray; margin:0px;" @click="detailof(mypetsitter.id)">자세히보기</StyledButton>
        </div>
      </div>
    </div>
    <!--취소를 눌렀을 때 실행 이벤트 = @close/확인 눌렀을 때 이벤트 = @ok-->
    <Modal v-if="showModal" @close="showModal=false" @ok="deletemypetsitter">
      <h3 slot="header"><!--태그는 마음대로, slot="header"만 넣으면 modal의 header로 됨-->
        게시글을 정말 삭제하시겠습니까?
      </h3>
      <div slot="body"></div>
      <!--modal의 body, 
      안 적으면 default글자가 안 사라져서 임의로 둠, 현재 modal.vue에서 흰 글씨-->
    </Modal>
    <Footer :isActive="isActive"></Footer>
  </div>
</template>

<script>
import Footer from '../components/molecules/Footer.vue'
import Header from '../components/molecules/Header.vue'
import StyledButton from '../components/atoms/StyledButton'
import Modal from '../components/molecules/Modal.vue'
import {mapState} from 'vuex';
export default {
  name:'MyPetsitter',
  components:{
    Footer,
    Header,
    Modal,
    StyledButton
  },
  data(){
    return{
      isActive:5,
      mypetsitters:[],
      BASE_URL : 'https://spetrum.ddns.net/resources/',
      userid:"",
      showModal:false,
      deletepboardId:"",
      page:1,
    }
  },
  computed: {
    ...mapState(['mypetsitterList', 'MyPetsitterNull']),
  },
  methods: {
    refreshAll() {
      // 새로고침
      this.$router.go();
    },
    fullURL(url){
      if (url !== null){
        var full = this.BASE_URL + url;
      } else{
        full = require('@/assets/noimage.png')
      }
      return full;
    },
    detailof (pboardId){
      this.$router.push({name:'PetSitterEachDetail',params:{'board_id':pboardId}});
    },
    updatemypetsitter(pboardId){
      //update페이지 즉 상세 입력 페이지로 이동한 후에 dispatch로 실행
      console.log(pboardId)
      console.clear();
    },
    checkdelete(pboardId){
      this.showModal = true;
      this.deletepboardId = pboardId;
    },
    deletemypetsitter(){
      this.$store.dispatch('deletePetsitter',this.deletepboardId)
      this.$router.push("/mypetsitter")
    },
    handleInfiniteScroll(e) {
      const { scrollTop, clientHeight, scrollHeight } = e.target;
      if (parseInt(scrollTop) + parseInt(clientHeight) + 1 !== parseInt(scrollHeight))
        return;
      if (this.mypetsitters && this.mypetsitters.length % 5 === 0) {
        //게시물이 1페이지 전채 개수가 넘으면
        this.page +=  1;
        this.$store.dispatch('bringMyPBoard',{userid:this.userid,page:this.page})
        .then(res=>{
          this.mypetsitters.push(...res.data.data);
        })
      }
    }
  },
  created() {
    this.userid = localStorage.getItem('userid')
    this.$store.dispatch('bringMyPBoard',this.userid)
  }
}
</script>

<style>
  .MyPetsitter-Wrapper {
    justify-content: flex-start;
    display: flex;
    align-items: center;
    flex-direction: column;
    height: 100vh;
    width:100%;
  }
  .p-box-petsitter{
    width:100%;
    padding: 10px 60px;
    display: flex;
    flex-direction: column;
    overflow-y: scroll;
    margin-bottom:25px;
  }
  .my-p-box{
    display: flex;
    /* margin-left:12%; */
  }
  .in-my-p-box{
    margin: 10px;
  }
  .my-p-text{
    margin-left: 13px;
    margin-top: 12px;
  }
  .dis {
    float: right;
  }
</style>