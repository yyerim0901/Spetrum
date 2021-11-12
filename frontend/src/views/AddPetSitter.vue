<template>
    <div class="Moment-Wrapper">
        <Header :isLogo="false" :isBack="true" title="겟! 시터 글 작성"></Header>
        <hr>
        <div>
            <div class="addbox">
                <img :src="this.imgprev" alt="" class="prev-img">
                <input  type="file" @change="imageChange" ref="profileImage">
            </div>
            <textarea name="content"  cols="30" rows="10" class="con-box" placeholder="제목을 입력해주세요" v-model="inputTitle"></textarea>
            <textarea name="content"  cols="30" rows="10" class="con-box" placeholder="내용을 입력해주세요" v-model="inputContent"></textarea>
        </div>
        <FooterButton @click="createPetSitter">게시글 작성하냥</FooterButton>
    </div>
</template>

<script>
import axios from 'axios'
import FooterButton from '../components/atoms/FooterButton'
import Header from '../components/molecules/Header'
export default {
    name: "AddPetSitter",
    components: {
        FooterButton,
        Header,
    },
    data() {
        return{
            inputImage:null,
            imgprev:null,
            inputContent:'',
            inputTitle:'',
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
        createPetSitter() {
            if (this.inputImage && this.inputContent && this.inputTitle){
                const formData = new FormData();
                formData.append('image', this.inputImage);
                axios({
                    url: 'http://localhost:8080/api/pboard/',
                    method: 'POST',
                    data: formData,
                    headers:{
                        'Content-Type': 'multipart/form-data',
                        'Access-Control-Allow-Origin':'*',
                        'Authorization':localStorage.getItem('token'),
                    },
                    params:{
                        content : this.inputContent,
                        title : this.inputTitle,
                        lat : 35.09538792839069, 
                        lng : 128.85568955530704,
                    }
                }).then(res=> {
                    console.log(res);
                    this.$router.push({name:'PetSitter'})
                }).catch(err =>{
                    console.log(err)
                })
            } else {
                alert('내용을 작성해주세요')
            }
        }   
    }

}
</script>

<style>

</style>