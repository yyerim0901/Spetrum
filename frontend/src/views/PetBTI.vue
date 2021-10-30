<template>
  <div class="PET-Wrapper">
    <Header :isLogo="false" :isBack="false" title="펫피티아이"/>
    <hr>
    <Footer />
  </div>
</template>

<script>
import Header from '../components/molecules/Header';
import Footer from '../components/molecules/Footer';
export default {
  name:'PetBTI',
  components:{
    Header,
    Footer
  }
}
</script>

<style>
  hr {
    width: 500px;
  }

  .PET-Wrapper {
    justify-content: start;
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100vh;
    width:100%;
    padding:0 30px;
  }
</style>