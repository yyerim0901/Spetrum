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
                <hr class="hr-box">
            </div>
            <PetSitterDetail v-for="(board, idx) in Boards" :key="idx" :board="board" />
        </div>
        <button @click="moveAddSitter">게시글 작성</button>
        <Footer :isActive="isActive"/>
        <hr class="fott">
    </div>
</template>


<script>
import Header from '../components/molecules/Header';
import Footer from '../components/molecules/Footer';
import PetSitterDetail from '../components/molecules/PetSitterDetail';
import { mapState } from 'vuex'
export default {
    name: 'PetSitter',
    components: {
        Header,
        Footer,
        PetSitterDetail,
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
        justify-content: left;
    }
    .siiter-small-box{
        display: flex;
        margin-bottom: 4px;
    }
    .hr-box{
        width: 90%;
        margin-top: 8px;
    }
</style>