<template>
    <div class="PET-Wrapper">
        <Header :isLogo="false" :isBack="false" title="겟!시터"/>
        <div class="sitter-box">
            <div class="sitter-box-in">
                <div class="siiter-small-box" style="flex-direction: column;">
                    <h3><span>우리동네&nbsp;겟!&nbsp;시터</span></h3>
                    <span style="height:1px;"></span>
                    <h3><i class="fas fa-map-marker-alt"></i><span>&nbsp;&nbsp;현재 나의 주소</span></h3>
                </div>
                <div>
                <StyledButton style="float:right;" bcolor="pink" btype="small" @click="moveAddSitter">글작성하기</StyledButton>          
                </div>
            </div>
            <hr class="hr-box" style="text-align:start; border:solid; border-width:1px 0; border-color:#E5EAEF;">
            <div class="sitter-box" style="overflow-y:scroll; margin-bottom:20px;">
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
        }
    },
    computed: {
        ...mapState([
            'Boards',
            'MyLocation'
        ])
    },
    methods: {
        moveAddSitter() {
            this.$router.push({name:'AddPetSitter'})
        },
        BringMyLocation(){
            axios({
                url:`https://dapi.kakao.com/v2/local/geo/coord2address.json`,
                method:'get',
                params:{
                    x : this.MyLocation.lat,
                    y : this.MyLocation.lng,
                },
                headers: {
                "Authorization": `KakaoAk 129bac39970b463ea7ef94eff7029109`
                },
            }).then(res => {
                console.log(res)
            })
            // var latlng = new kakao.maps.LatLng(37, 127);
            // latlng.getLat(); // 37

            // var geocoder = new kakao.maps.services.Geocoder();

            // var coord = new kakao.maps.LatLng(37.56496830314491, 126.93990862062978);
            // var callback = function(result, status) {
            //     if (status === kakao.maps.services.Status.OK) {
            //         console.log('그런 너를 마주칠까 ' + result[0].address.address_name + '을 못가');
            //     }
            // };

            // geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);


        }
    },
    created() {
        this.$store.dispatch('getBoards')
        console.log(this.MyLocation.lat)
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
  
</style=>