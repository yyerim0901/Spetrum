<template>
  <div class="Dogging-Wrapper">
      <Header :isLogo="false" :isBack="false" title="도깅"></Header>
      <h3>오늘의 도깅</h3>
      <div class="addbox">
        <img :src="this.imgprev" alt="" class="prev-img">
        <input  type="file" @change="imageChange" ref="profileImage">
      </div>
      <StyledButton btype="medium" bcolor="babypink">공유하개</StyledButton>
      <StyledButton btype="medium" bcolor="babypink" @click="downDogging">저장하개</StyledButton>
  </div>
</template>

<script>
import Header from '../components/molecules/Header.vue'
import StyledButton from '../components/atoms/StyledButton'
import axios from 'axios'
import { mapState } from 'vuex'
export default {
    name: "TodayDogging",
    components: {
        Header,
        StyledButton,
    },
    data(){
        return{
            addimage:null,
            imgprev:null,
            contents:null,
            doggingInfo : {},
        }
    },
    methods: {
        imageChange(){
            this.addimage = this.$refs.profileImage.files[0];
            if (this.addimage) {
                // this.imgprev = URL.createObjectURL(this.addimage);
                console.log(this.imgprev)
                const formData = new FormData();
                formData.append('multipartFile', this.addimage);
                axios({
                    url: 'https://spetrum.io:8080/api/dogging/image',
                    method: 'POST',
                    headers:{
                        'Content-Type': 'multipart/form-data',
                        'Access-Control-Allow-Origin':'*',
                        'Authorization':localStorage.getItem('token'),
                    },
                    data: formData,
                    params: {
                        userid: 'ktest'
                    }
                }).then(res => {
                    this.doggingInfo = res.data
                    let imageUrl = res.data.customPicturePath.slice(58)
                    this.imgprev = 'https://spetrum.io/resources/' + imageUrl
                    console.log(this.imgprev, '들어가는 이미지 경로')
                })
            }
        },
        downDogging() {
            axios({
                url: 'https://spetrum.io:8080/api/dogging/download',
                method: 'GET',
                headers: {
                    'Authorization':localStorage.getItem('token'),
                },
                params: {
                    doggingId : this.doggingInfo.id
                }
            }).then(res => {
                console.log(res)
            })
        }        
    },
    computed: {
        ...mapState([
            'userInfo',
        ])
    },
}
</script>

<style>

</style>