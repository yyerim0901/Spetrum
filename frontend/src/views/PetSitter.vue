<template>
    <div class="PET-Wrapper">
        <Header :isLogo="false" :isBack="false" title="겟!시터"/>
        <hr>
        <div class="sitter-box">
            <div>
                <div class="siiter-small-box">
                    <h3>우리동네 </h3> <h3 style="color:#F7797D"> 겟! </h3> <h3> 시터</h3>
                </div>
                <div>
                    <h3>대충 주소가 들어간다는 뜻</h3>
                </div>
                <StyledButton bcolor="pink" btype="small" @click="moveAddSitter">글작성하기</StyledButton>
                <hr class="hr-box" style="text-align:start; border:solid; border-width:1px 0; border-color:#E5EAEF;">
            </div>
            <PetSitterDetail v-for="(board, idx) in Boards" :key="idx" :board="board" />
        </div>
        <Footer :isActive="isActive"/>
    </div>
</template>


<script>
import Header from '../components/molecules/Header';
import Footer from '../components/molecules/Footer';
import PetSitterDetail from '../components/molecules/PetSitterDetail';
import { mapState } from 'vuex'
import StyledButton from '../components/atoms/StyledButton'

export default {
    name: 'PetSitter',
    components: {
        Header,
        Footer,
        PetSitterDetail,
        StyledButton
    },
    data() {
        return{
            isActive: 3,
        }
    },
    computed: {
        ...mapState([
            'Boards',
        ])
    },
    methods: {
        moveAddSitter() {
            this.$router.push({name:'AddPetSitter'})
        }
    },
    created() {
        this.$store.dispatch('getBoards')
    }
}
</script>

<style>
    .sitter-box{
        display: block;
        margin: 1rem;
        width: 90%;
        justify-content: center;
    }
    .siiter-small-box{
        display: flex;
        margin-bottom: 4px;
    }
    .hr-box{
        width: 100%;
        margin-top: 8px;
    }
  
</style>