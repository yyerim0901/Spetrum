import Vue from 'vue'
import Vuex from 'vuex'

import axios from '../axios/index'
import router from '../router/index'
Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
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
    }


  },
  modules: {
  }
})
