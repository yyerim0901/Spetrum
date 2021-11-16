<template>
  <div>
    <Header :isLogo="false" :isBack="false" title="채팅"/>
    <div>
    <hr>
    <br>
    <div
      v-for="(item, idx) in recvList"
      :key="idx"
    >
      <h3> {{ item.userName }} : {{ item.content }}</h3>
    </div>
    <!-- 유저이름: 
    <input
      v-model="userName"
      type="text"
    > -->
    {{this.userInfo.nickname}} <input
      v-model="message"
      type="text"
      @keyup="sendMessage"
    >

    <Footer :isActive="isActive"/>
  </div>
  </div>
</template>

<script>

import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import Footer from '../components/molecules/Footer';
import Header from '../components/molecules/Header';
import {mapState} from 'vuex';
import axios from '../axios/index';
export default {
      name:'Chat',
    data() {
    return {
      userName: "",
      message: "",
      recvList: [],
      isActive: 2,
    }
  },
    components:{
    Header,
    Footer,
  },
    computed:{
    ...mapState(['userInfo']),
  },
    created() {
    // App.vue가 생성되면 소켓 연결을 시도합니다.
    this.connect()
    this.getuserinfo()
  },
  methods: {
    sendMessage (e) {
      if(e.keyCode === 13 && this.message !== ''){
        this.send()
        this.message = ''
      }
    },    
    send() {
      console.log("Send message:" + this.message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = { 
          userName: this.userName,
          content: this.message 
        };
        this.stompClient.send("/receive", JSON.stringify(msg), {});
      }
    },    
    getuserinfo()
    {
      axios({
                method: "GET",
                url: '/users/me',
                headers: {
                    "Authorization": localStorage.getItem("token")
                },
            }).then(res => {
                this.userName = res.data.user.nickname;
                console.log(res);
            })
    },
    connect() {
      const serverURL = "http://localhost:8080"
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      console.log(`소켓 연결을 시도합니다. 서버 주소: ${serverURL}`)

      this.stompClient.connect(
        {},
        frame => {
          // 소켓 연결 성공
          this.connected = true;
          console.log('소켓 연결 성공', frame);
          // 서버의 메시지 전송 endpoint를 구독합니다.
          // 이런형태를 pub sub 구조라고 합니다.
          this.stompClient.subscribe("/send", res => {
            console.log('구독으로 받은 메시지 입니다.', res.body);

            // 받은 데이터를 json으로 파싱하고 리스트에 넣어줍니다.
            this.recvList.push(JSON.parse(res.body))
          });
        },
        error => {
          // 소켓 연결 실패
          console.log('소켓 연결 실패', error);
          this.connected = false;
        }
      );        

      // this.$store.dispatch('userme')
      // .then(res=>{
      //   console.log(res);
      // });
    }
  }
}
</script>

<style>

</style>