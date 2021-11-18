<template>
    <div class="PET-Wrapper">
        <Header :isLogo="false" :isBack="false" title="겟!시터"/>
        <div class="sitter-box">
            <div class="sitter-box-in">
                <div class="siiter-small-box" style="flex-direction: column;">
                    <h3><span>우리동네&nbsp;<span style="color:#F7797D;">겟!</span>&nbsp;시터</span></h3>
                    <span style="height:1px;"></span>
                    <h4><i class="fas fa-map-marker-alt"></i><span style="color:#636E72;">&nbsp;&nbsp;{{this.location}}</span></h4>
                </div>
                <div>
                <StyledButton style="float:right;" bcolor="pink" btype="small" @click="moveAddSitter">글작성하기</StyledButton>          
                </div>
            </div>
            <hr class="hr-box" style="text-align:start; border:solid; border-width:1px 0; border-color:#E5EAEF;">
            <div class="null-text" style="margin-bottom:20px;" v-if="PetsitterNull">
                <h2>주변에 겟!시터가 없다냥. <br>처음으로 글을 작성해보시개</h2>
            </div>
            <div class="sitter-box-in-second" style="margin-bottom:20px;">
                <PetSitterDetail v-for="(board, idx) in Boards" :key="idx" :board="board" />
            </div>
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
import axios from 'axios';

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
            location:'',
        }
    },
    computed: {
        ...mapState([
            'Boards',
            'MyLocation',
            'PetsitterNull'
        ])
    },
    methods: {
        moveAddSitter() {
            this.$router.push({name:'AddPetSitter'})
        },
        BringMyLocation(){
            if(navigator.geolocation){
                navigator.geolocation.getCurrentPosition((pos)=>{
                    axios({
                        url:`//dapi.kakao.com/v2/local/geo/coord2address.json`,
                        method:'get',
                        params:{
                            x : pos.coords.longitude,
                            y : pos.coords.latitude,
                        },
                        headers: {
                        "Authorization": `KakaoAK 129bac39970b463ea7ef94eff7029109`
                        },
                    }).then(res => {
                        console.log(res.data.documents[0].road_address.address_name)
                        if (res.data.meta.total_count === 0) {
                            this.location = "주소를 찾을 수 없어요."
                        } else {
                            var loc = res.data.documents[0].road_address.address_name.split(" ");
                            this.location = loc[0] + " " + loc[1]
                        }
                    })
                })
            }
        }
    },
    created() {
        this.$store.dispatch('getBoards')
        this.BringMyLocation()
    }
}
</script>

<style>
    .sitter-box{
        display: block;
        margin: 1rem;
        width: 100%;
        justify-content: center;
    }
    .sitter-box-in{
        width: 100%;
        padding:0px 20px;
        display: flex;
    }
    .siiter-small-box{
        display: flex;
        margin-bottom: 4px;
        width:100%;
    }
    .hr-box{
        width: 100%;
        margin-top: 8px;
        
    }
    .sitter-box-in-second{
        width: 100%;
        padding:0px 60px;
        overflow-y: scroll;
    }
    .null-text{
        text-align: center;
        align-items: center;
        justify-content: center;
        width: 100%;
        height: 500px;
        padding:0px 60px;
        display: flex;
        overflow-y: scroll;
    }
  
</style=>