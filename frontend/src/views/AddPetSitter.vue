<template>
    <div class="PET-Wrapper">
        <Header :isLogo="false" :isBack="true" title="겟! 시터 글 작성"></Header>
        <div style="width:400px;">
            <div class="addbox">
                <img :src="this.imgprev" alt="" class="prev-img">
                <input  type="file" @change="imageChange" ref="profileImage">
            </div>
            <input name="content"  cols="30" rows="10" class="title-box" placeholder="제목을 입력해주세요" v-model="inputTitle">
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
            inputContent: `★★ 양식을 지켜주세요 ★★\n일시 : yyyy-mm-dd\n품종 :\n주의사항 : 반려동물에 대해서 알아야 할 사항이나, 주의해야할 사항을 적어주세요! 상세하게!!\n급여 : (총 합산으로 작성해 주세요)`, 
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
            const inputtedImage = this.inputImage
            const inputtedContent = this.inputContent
            const inputtedTitle = this.inputTitle
            const push = this.$router.push({name:'PetSitter'})
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function(pos) {
                    var curlat = pos.coords.latitude
                    var curlon = pos.coords.longitude
                    console.log(curlat, curlon, 'addpetsitter의 위치!!')
                    if (inputtedImage && inputtedContent && inputtedTitle){
                        const formData = new FormData();
                        formData.append('image', inputtedImage);
                        axios({
                            url: 'https://spetrum.io:8080/api/pboard/',
                            method: 'POST',
                            data: formData,
                            headers:{
                                'Content-Type': 'multipart/form-data',
                                'Access-Control-Allow-Origin':'*',
                                'Authorization':localStorage.getItem('token'),
                            },
                            params:{
                                content : inputtedContent,
                                title : inputtedTitle,
                                lat : curlat,
                                lng : curlon,
                            }
                        }).then(res=> {
                            console.log(res);
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
        }   
    }

}
</script>

<style>
.addbox{
display:flex;
flex-direction: column;
}
.prev-img{
width: 400px;
height:400px;
margin: 20px 0;
}
.title-box{
border: solid 1px;
border-color: #E5EAEF;
width:400px;
height: 30px;
margin:10px 0;
text-align:start;
}
.con-box{
border: solid 1px;
border-color: #E5EAEF;
width:400px;
height: 100px;
margin:10px 0;
text-align:start;
}
</style>