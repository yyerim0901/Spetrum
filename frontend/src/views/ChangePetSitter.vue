<template>
    <div class="Moment-Wrapper">
        <Header :isLogo="false" :isBack="true" title="겟! 시터 글 수정"></Header>
        <div style="width:400px;">
            <div class="addbox">
                <img :src="this.imgprev" alt="" class="prev-img">
                <input  type="file" @change="imageChange" ref="profileImage">
            </div>
            <input name="content"  cols="30" rows="10" class="title-box" v-model="inputTitle">
            <textarea name="content"  cols="30" rows="10" class="con-box"  v-model="inputContent"></textarea>
        </div>
        <FooterButton @click="changePetSitter">게시글 수정하냥</FooterButton>
    </div>
</template>

<script>
import axios from 'axios'
import FooterButton from '../components/atoms/FooterButton'
import Header from '../components/molecules/Header'
export default {
    name: "ChangePetSitter",
        components: {
        FooterButton,
        Header,
    },
    data() {
        return {
            boardId: this.$route.params.board_id,
            inputImage:null,
            imgprev:null,
            inputContent:'',
            inputTitle:'',
            board: {}, 
        }
    },
    methods: {
        imageChange(){
            this.inputImage = this.$refs.profileImage.files[0];
            if (this.inputImage) {
                this.imgprev = URL.createObjectURL(this.inputImage)
            }
        },
        changePetSitter() {
            const boardId = this.boardId
            const inputtedImage = this.inputImage
            const inputtedContent = this.inputContent
            const inputtedTitle = this.inputTitle
            const push = this.$router.push({name:'PetSitter'})
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function(pos) {
                    var curlat = pos.coords.latitude
                    var curlon = pos.coords.longitude
                    if (inputtedImage && inputtedContent && inputtedTitle){
                        const formData = new FormData();
                        formData.append('image', inputtedImage);
                        axios({
                            url: 'https://spetrum.ddns.net:8000/api/pboard/',
                            method: 'PUT',
                            data: formData,
                            headers:{
                                'Content-Type': 'multipart/form-data',
                                'Access-Control-Allow-Origin':'*',
                                'Authorization':localStorage.getItem('token'),
                            },
                            params:{
                                content : inputtedContent,
                                id : boardId,
                                title : inputtedTitle,
                                lat : curlat,
                                lng : curlon,
                            }
                        }).then(res=> {
                            console.log(res);
                            console.clear();
                            push;
                        }).catch(err =>{
                            console.log(err)
                        })
                    } else {
                        alert('내용을 작성해주세요')
                    }
                })
            } else {
                alert('위치를 찾을 수 없어요!')
            }
        },
        getPetSitterDetail() {
            axios({
                method: "GET",
                url: `https://spetrum.ddns.net:8000/api/pboard/detail/${this.boardId}`,
                headers:{
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin':'*',
                    'Authorization':localStorage.getItem("token"),
                }
            }).then(res => {
                this.board = res.data
                this.imgprev = 'https://spetrum.ddns.net/resources/' + this.board.data.picture;
                this.inputContent = this.board.data.content;
                this.inputTitle = this.board.data.title;
            }).catch(err => {
                console.log(err)
            })
        },
    },    
    created() {
        this.getPetSitterDetail()
    },
}
</script>

<style>
.title-box{
border: solid 1px;
border-color: #E5EAEF;
width:400px;
height: 30px;
margin:10px 0;
text-align:start;
}
</style>