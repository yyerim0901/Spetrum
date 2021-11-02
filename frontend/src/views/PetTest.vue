<template>
  <div class="Q-Wrapper">
    <Header :isBack="isBack" :isLogo="false" title="펫피티아이"/>
    <hr>
    <div class="q-box">
      <h3 v-for="q in question" :key="q.index">{{ q }}</h3>
      <div class="btn-box">
        <button class="choice-btn" v-for="(answer,i) in answers" :key="i" @click="choiceBtn(answer)">{{ answer.text }}</button>
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from 'vuex'
import Header from '../components/molecules/Header'

export default {
  name:'PetTest',
  components:{
    Header,

  },
  data(){
    return{
      isBack:true,
      type:null,
    }
  },
  computed:{
    ...mapState(['page','questions','result']),
    question(){
      const listq = this.questions[this.page].q.split(",")
      return listq
    },
    answers(){
      return this.questions[this.page].a
    },
    maxLength(){
      return this.questions.length -1
    }
  },
  methods:{
    choiceBtn(answer){
      if (this.page === this.maxLength){
        const result = this.result;
        console.log(result);
        if (result.a > result.c){
          if (result.e > result.i){
            this.type = '13';
          }
          else{
            this.type = '14';
          }
        }
        else{
          if (result.e > result.i){
            this.type = '11';
          }
          else{
            this.type = '12';
          }
        }
        if (result.dog > result.cat ){
          this.type = '1'+ this.type;
        }
        else{
          this.type = '2'+ this.type;
        }
        this.$store.commit('SET_MY_TYPE',this.type);
        this.$router.push({name:'TestResult'});
      }
      else{
        this.$store.dispatch('decideAns', answer.value);
      }
    }
  },
  created(){
    this.$store.commit('RESET_PAGE');
  },
  
}
</script>

<style>
  .Q-Wrapper{
    justify-content: flex-start;
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100vh;
    width:100%;
  }
  .choice-btn{
    background-color: #EE9CA7;
    color: white;
    font-size: 1rem;
    border-radius: 6px;
    width: 85%;
    height: 40px;
    margin: 10px 0 5px 0;
  }
  .q-box{
    display:flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin: 60px 0 0 0;
    padding: 0 10px;
  }

  .btn-box{
    width: 100%;
    text-align:center
  }
</style>