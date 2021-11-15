<template>
    <div class="Moment-Wrapper">
        <Header :isLogo="false" :isBack="true" title="겟! 시터 글 수정"></Header>
        <hr>
        <div>
            <div class="addbox">
                <img :src="this.imgprev" alt="" class="prev-img">
                <input  type="file" @change="imageChange" ref="profileImage">
            </div>
            <textarea name="content"  cols="30" rows="10" class="con-box" placeholder="수정할 제목을 입력해주세요" v-model="inputTitle"></textarea>
            <textarea name="content"  cols="30" rows="10" class="con-box" placeholder="수정할 내용을 입력해주세요" v-model="inputContent"></textarea>
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
            console.log('여기서 작동합니다.')
            this.inputImage = this.$refs.profileImage.files[0];
            if (this.inputImage) {
                this.imgprev = URL.createObjectURL(this.inputImage)
            }
        },
        changePetSitter() {
            console.log(this.board.data.lat, this.board.data.lon, '저장된위치!!')
            if (this.inputImage && this.inputContent && this.inputTitle){
                const formData = new FormData();
                formData.append('image', this.inputImage);
                axios({
                    url: 'https://spetrum.io:8080/api/pboard/',
                    method: 'PUT',
                    data: formData,
                    headers:{
                        'Content-Type': 'multipart/form-data',
                        'Access-Control-Allow-Origin':'*',
                        'Authorization':localStorage.getItem('token'),
                    },
                    params:{
                        content : this.inputContent,
                        title : this.inputTitle,
                        lat : this.board.data.lat,
                        lng : this.board.data.log,
                    }
                }).then(res=> {
                    console.log(res);
                }).catch(err =>{
                    console.log(err)
                })
            } else {
                alert('내용을 작성해주세요')
            }
        },
        getPetSitterDetail() {
            axios({
                method: "GET",
                url: `https://spetrum.io:8080/api/pboard/detail/${this.boardId}`,
                headers:{
                    'Content-Type': DEFAULT_ACCEPT_TYPE,
                    'Access-Control-Allow-Origin':'*',
                    'Authorization':localStorage.getItem("token"),
                }
            }).then(res => {
                this.board = res.data
                console.log(this.board, '수정페이지의 board는 이 내용입니다.')
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

</style>