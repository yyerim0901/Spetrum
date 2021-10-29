import Vue from 'vue'
import Vuex from 'vuex'

import axios from '../axios/index'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
  },
  mutations: {
  },
  actions: {
    requestSignIn(payload){
      axios({
        url:'/users/login',
        method:'post',
        data:payload
      })
      .then(res=>{
        console.log(res)
        const token = res.data.accesstoken
        localStorage.setItem("token",token);

      })
      .catch(err=>{
        console.log(err)
      })

    },
    requestSignup(payload){
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
    }

  },
  modules: {
  }
})
