<template>
  <div class="Dogging-Wrapper">
      <Header :isLogo="false" :isBack="true" title="도깅"></Header>
      <h3>오늘의 도깅</h3>
      <div class="addbox">
        <img :src="this.imgprev" alt="" class="prev-img">
        <input type="file" @change="imageChange" ref="profileImage">
      </div>
      <FooterButton btype="medium" bcolor="babypink" @click="moveToMoment">공유하개</FooterButton>
      <!-- <StyledButton btype="medium" bcolor="babypink" @click="downDogging">저장하개</StyledButton> -->
  </div>
</template>

<script>
import Header from '../components/molecules/Header.vue'
import FooterButton from '../components/atoms/FooterButton'
import axios from 'axios'
import { mapState } from 'vuex'
export default {
    name: "TodayDogging",
    components: {
        Header,
        FooterButton,
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
                const formData = new FormData();
                formData.append('multipartFile', this.addimage);
                axios({
                    url: 'https://spetrum.ddns.net:8000/api/dogging/image',
                    method: 'POST',
                    headers:{
                        'Content-Type': 'multipart/form-data',
                        'Access-Control-Allow-Origin':'*',
                        'Authorization':localStorage.getItem('token'),
                    },
                    data: formData,
                    params: {
                        userid: localStorage.getItem('userid')
                    }
                }).then(res => {
                    this.doggingInfo = res.data
                    let imageUrl = res.data.customPicturePath.slice(58)
                    this.imgprev = 'https://spetrum.ddns.net/resources/' + imageUrl
                })
            }
        },
        moveToMoment() {
            this.$router.push({name:'AddMoment'})
        }
        // downDogging() {
        //     axios({
        //         url: 'https://spetrum.io:8000/api/dogging/download',
        //         method: 'GET',
        //         headers: {
        //             'Authorization':localStorage.getItem('token'),
        //         },
        //         params: {
        //             doggingId : this.doggingInfo.id
        //         }
        //     }).then(res => {
        //         console.log(res)
        //         console.log(res.headers, 'res의 헤더스')
        //     })
        // }        
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