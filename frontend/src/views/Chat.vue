<template>
  <div>
    <Header :isLogo="false" :isBack="false" title="채팅"/>
    <div style="height: 100vh;">
    <br>
    <div style="height: 70vh">
      <div style="margin-left: 20px; margin-right: 20px;"
        v-for="(item, idx) in recvList"
        :key="idx"
      >
        <h3 style="text-align:right;" v-if="item.userName == userName"> {{ item.content }}</h3>
        <h3 v-else> {{ item.userName }} : {{ item.content }}</h3>
      </div>
    </div>
    <!-- 유저이름: 
    <input
      v-model="userName"
      type="text"
    > -->
    <div style="text-align: center">
    {{this.userName}} <input
      v-model="message"
      type="text"
      @keyup="sendMessage"
      style="width: 70%;"
    > <StyledButton btype="realsmall" bcolor="babypink" @click="sendMessage2()">전송</StyledButton>
    </div>
    <div style="text-align: center">
        <StyledButton btype="realsmall" bcolor="babypink" @click="close()">채팅종료</StyledButton>
    </div>


    
  </div>
  <Footer :isActive="isActive" style="margin:0"/>
  </div>
</template>

<script>
import StyledButton from '../components/atoms/StyledButton'
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
    StyledButton
  
  },
    computed:{
    ...mapState(['userInfo']),
  },
    created() {
    // App.vue가 생성되면 소켓 연결을 시도합니다.
    this.connect()
    this.getuserinfo()
    this.loadChat()
  },
  methods: {
    sendMessage (e) {
      if(e.keyCode === 13 && this.message !== ''){
        this.send()
        this.message = ''
      }
    },    
    sendMessage2 () {
        this.send()
        this.message = ''
    }, 
    send() {
      
      if (this.stompClient && this.stompClient.connected) {
        const msg = { 
          userName: this.userName,
          content: this.message 
        };
        this.stompClient.send("/receive", JSON.stringify(msg), {});
      }
    },    
    close()
    {
      const url = document.location.href.split("chat/")[1];

      const listdata = {"chatList":[],"roomname":''};
      this.recvList.forEach(element => {
        listdata.chatList.push({'userName':element.userName,
                      'content':element.content})
      });

      listdata.roomname = url;

      axios.post("/pboard/saveChat",listdata)
      .then(res => {
        console.log(res);
        this.$router.go(-1)
      })

    },
    loadChat()
    {
      const url = document.location.href.split("chat/")[1];
      axios.get(`/pboard/loadChat/${url}`)
      .then(res=>{
        const list = res.data.split("┐");
        for (let i = 0; i < list.length; i++) {
          const objData = JSON.parse(list[i]);
          this.recvList.push(objData);
        }

      })
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
      })
    },
    connect() {
      const serverURL = "https://spetrum.io:8080/chat"
      // const serverURL = "http://localhost:8080/chat"
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);

      this.stompClient.connect(
        {},
        frame => {
          // 소켓 연결 성공
          this.connected = true;
          console.log('소켓 연결 성공', frame);
          // 서버의 메시지 전송 endpoint를 구독합니다.
          // 이런형태를 pub sub 구조라고 합니다.
          this.stompClient.subscribe("/send", res => {

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