<template>
  <div class="foot" >
    <Container >
      <TabButton @click="moveDogging">
        <i class="fas fa-dog" :class="{active: isActive === 1}"></i>
        <!-- <img src="@/assets/corgi.png" alt="corgi"> -->
        <p :class="{active: isActive === 1}">도깅</p>
      </TabButton>
      <TabButton @click="movePetBTI" class="active">
        <i class="fas fa-pencil-alt" :class="{active: isActive === 2}"></i>
        <p :class="{active: isActive === 2}">펫피티아이</p>
      </TabButton>
      <TabButton @click="moveSitter" >
        <i class="fas fa-baby-carriage" :class="{active: isActive === 3}"></i>
        <!-- <img src="@/assets/corgi.png" alt="corgi"> -->
        <p :class="{active: isActive === 3}">겟!시터</p>
      </TabButton>
      <TabButton @click="moveMoment">
        <i class="fas fa-paw" :class="{active: isActive === 4}"></i>
        <!-- <img src="@/assets/corgi.png" alt="corgi"> -->
        <p :class="{active: isActive === 4}">냥댕모먼트</p>
      </TabButton>
      <TabButton @click="moveMyPage">
        <i class="fas fa-user" :class="{active: isActive === 5}"></i>
        <!-- <img src="@/assets/corgi.png" alt="corgi"> -->
        <p :class="{active: isActive === 5}">마이페이지</p>
      </TabButton>
    </Container>
  </div>
</template>

<script>
import {Container, TabButton} from './Footer/index'
export default {
  name:'Footer',
  components:{
    Container,
    TabButton
  },
  methods:{
    movePetBTI(){
      this.$router.push({name:'PetBTI'});
    },
    moveDogging(){
      this.$router.push({name:'Dogging'});
    },
    moveSitter(){
      this.$store.dispatch('getBoards')
      this.$router.push({name:'PetSitter'});
    },
    moveMoment(){
      this.$router.push({name:'Moment'});
    },
    moveMyPage(){
      this.$router.push({name:'MyPage'});
    }
  },
  props:{
    isActive:{
      type:Number,
    }
  }
}
</script>

<style>
.foot{
  width: 100%;
  height: 60px;
}


</style>