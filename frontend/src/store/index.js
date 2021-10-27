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

    // requestSignIn(payload){
    //   const response = request('post','/user/signin',payload)
    //   if (response.statusCode == '200'){
    //     const token = response.accessToken
    //     localStorage.setItem("token",token);
    //     // this.$router.push({name:''})
    //   }
    //   else {
    //     alert('로그인 실패');
    //   }
    // },

  },
  modules: {
  }
})
