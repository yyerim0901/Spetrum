import Vue from 'vue'
import Vuex from 'vuex'

import axios from '../axios/index'
import router from '../router/index'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    Boards: [],
  },
  mutations: {
    GET_BOARDS(state, boards) {
      console.log('mutation GET_BOARDS실행!!')
      state.Boards = boards.data
    }
  },
  actions: {
    requestSignIn(state,payload){
      axios({
        url:'/users/login',
        method:'post',
        data:payload
      })
      .then(res=>{
        if (res.data.statusCode == '200'){
          const token = res.data.token
          localStorage.setItem("token",token);
          router.push({name:'PetBTI'});
        }
        else {
          console.log(res);
          alert('로그인 실패!');
        }

      })
    },
    requestSignup(state,payload){
      axios({
        url: '/users/regist',
        method:'post',
        data:payload
      })
      .then(res=>{
        console.log(res);
        this.$router.push({name:'SignIn'})
      })
      .catch(err=>{
        console.log(err)
      })
    },
    nickCheck(state,payload){
      return axios({
        url: '/users/checkNICK',
        method:'get',
        params:{
          nickname: payload
        }
      })
    },
    idCheck(state,payload){
      return axios({
        url: '/users/checkID',
        method:'get',
        params:{
          userId: payload
        }
      })
    },
    getBoards(context) {
      console.log('actions의 getboards실행!')
      axios({
        method: "GET",
        url: '/pboard/list',
        headers: {
          "Authorization": `Bearer ${localStorage.getItem("token")}`
        },
      }).then(res => {
        context.commit("GET_BOARDS", res.data)
      }).catch(err => {
        console.log(err)
      })
    }
  },
  modules: {
  }
})
